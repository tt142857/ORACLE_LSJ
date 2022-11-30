package kr.letech.study02.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.letech.study02.board.service.BoardService;
import kr.letech.study02.board.vo.BoardVo;
import kr.letech.study02.cmn.base.vo.BaseController;
import kr.letech.study02.mem.vo.MemVo;
import kr.letech.study02.utils.paging.vo.PagingVo;
import kr.letech.study02.utils.paging.vo.SearchVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController extends BaseController {

	private final BoardService boardService;

	@GetMapping("/board")
	public String boardList(@RequestParam(defaultValue = "1", required = false) int currentPage
						  , @RequestParam(defaultValue = "10", required = false) int screenSize
			 			  , @ModelAttribute SearchVo searchVo
			 			  , Model model
			 			  ) {
		PagingVo<BoardVo> paging = new PagingVo<BoardVo>(currentPage, screenSize);
		paging.setSearchVo(searchVo);
		int boardTotalCount = boardService.retrieveBoardTotalCount(paging);
		paging.setPaging(boardTotalCount);

		model.addAttribute("paging", paging);
		return "board/boardList.base";
	}

	@GetMapping("/board/view")
	public String boardView(@RequestParam("no") int boardNo, Model model) {
		BoardVo board = boardService.retrieveBoardOne(boardNo);
		model.addAttribute("board", board);
		return "board/boardView.base";
	}

	@GetMapping("/board/write")
	public String boardWrite() {
		MemVo memVo = this.getMember();
		if (memVo != null) {
			return "board/boardWrite.base";
		} else {
			return "redirect:/loginForm.do";
		}
	}

	@PostMapping("/board/writeProcess")
	public String boardWriteProcess(@Validated @ModelAttribute BoardVo board, BindingResult bindingResult) {
		MemVo memVo = this.getMember();
		if (memVo != null) {
			String memId = memVo.getMemId();
			board.setRgstId(memId);
		} else {
			return "redirect:/loginForm.do";
		}

		// vaildation 안됨
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(v -> {
				log.info("error : {}", v.toString());
			});
			return "redirect:/board/write";
		} else {
			boardService.createBoard(board);
			int boardNo = board.getBoardNo();
			return "redirect:/board/view?no=" + boardNo;
		}
	}

	@PostMapping("/board/deleteProcess")
	public String boardDeleteProcess(@RequestParam("no") int boardNo, RedirectAttributes redirectAttributes) {
		BoardVo board = boardService.retrieveBoardOne(boardNo);

		if(board != null) {
			// 작성자 검증
			MemVo memVo = this.getMember();
			String memId = null;
			if (memVo != null) {
				memId = memVo.getMemId();
			}

			String rgstId = board.getRgstId();
			// 작성자 검증 성공
			if(rgstId.equals(memId)) {
				boardService.removeBoard(board);
				return "redirect:/board";
			} else {
				// 작성자 검증 실패
				String message = "작성자가 아닙니다.";
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:/board/view?no=" + boardNo;
			}
		} else {
			// 없는 게시판
			String message = "없는 게시판입니다.";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/board";
		}
	}

	@GetMapping("/board/update")
	public String boardUpdate(@RequestParam("no") int boardNo, Model model, RedirectAttributes redirectAttributes) {
		BoardVo board = boardService.retrieveBoardOne(boardNo);

		if(board != null) {
			MemVo memVo = this.getMember();
			String memId = null;
			if (memVo != null) {
				memId = memVo.getMemId();
			}

			String rgstId = board.getRgstId();
			// 작성자 검증 성공
			if(rgstId.equals(memId)) {
				model.addAttribute("board", board);
				return "board/boardUpdate.base";
			} else {
				// 작성자 검증 실패
				String message = "작성자가 아닙니다.";
				redirectAttributes.addFlashAttribute("message", message);
				return "redirect:/board/view?no=" + boardNo;
			}
		} else {
			// 없는 게시판
			String message = "없는 게시판입니다.";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/board";
		}
	}

	@PostMapping("/board/updateProcess")
	public String boardUpdateProcess(@ModelAttribute BoardVo board) {
		boardService.modifyBoard(board);
		int boardNo = board.getBoardNo();
		return "redirect:/board/view?no=" + boardNo;
	}
}
