package kr.letech.study02.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.letech.study02.board.service.BoardService;
import kr.letech.study02.board.vo.BoardVo;
import kr.letech.study02.cmn.base.vo.BaseController;
import kr.letech.study02.utils.paging.vo.PagingVo;
import kr.letech.study02.utils.paging.vo.SearchVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardApiController extends BaseController {

	private final BoardService boardService;

	// 게시판 목록 조회
	@GetMapping("/api/v1/boards")
	@ResponseBody
	public ResponseEntity<?> boardList(@RequestParam(defaultValue = "1", required = false) int currentPage
			  					     , @RequestParam(defaultValue = "10", required = false) int screenSize
									 , @ModelAttribute SearchVo searchVo
									 ) {
		PagingVo<BoardVo> paging = new PagingVo<BoardVo>(currentPage, screenSize);
		paging.setSearchVo(searchVo);
		int boardTotalCount = boardService.retrieveBoardTotalCount(paging);
		paging.setPaging(boardTotalCount);

		List<BoardVo> boardList = boardService.retrieveBoardList(paging);
		paging.setItemList(boardList);

		return ResponseEntity.ok(paging);
	}

	// 게시판 상세 조회
	@GetMapping("/api/v1/boards/{boardNo}")
	@ResponseBody
	public ResponseEntity<?> boardView(@PathVariable int boardNo) {
		BoardVo board = boardService.retrieveBoardOne(boardNo);
		return ResponseEntity.ok(board);
	}


	// 게시판 등록 프로세스
	@PostMapping("/api/v1/boards")
	@ResponseBody
	public ResponseEntity<?> boardWriteProcess(BoardVo board) {
		if (userId() != null) {
			board.setRgstId(userId());
			boardService.createBoard(board);
			int boardNo = board.getBoardNo();
			return ResponseEntity.ok(boardNo);
		} else {
			return ResponseEntity.ok("failed");
		}
	}

	// 게시판 삭제 프로세스
	@DeleteMapping("/api/v1/boards")
	@ResponseBody
	public ResponseEntity<?> boardDeleteProcess(@RequestParam int boardNo) {
		BoardVo board = boardService.retrieveBoardOne(boardNo);

		if(board != null) {
			// 작성자 검증
			String userId = userId();
			String rgstId = board.getRgstId();

			// 작성자 검증 성공
			if(rgstId.equals(userId)) {
				boardService.removeBoard(board);

				return ResponseEntity.ok("success");
			} else {
				// 작성자 검증 실패
				return ResponseEntity.ok("failed");
			}
		} else {
			// 없는 게시판
			return ResponseEntity.ok("none");
		}
	}

	// 게시판 수정 프로세스
	@PutMapping("/api/v1/boards")
	@ResponseBody
	public ResponseEntity<?> boardUpdateProcess(@ModelAttribute BoardVo board) {
		boardService.modifyBoard(board);
		int boardNo = board.getBoardNo();
		return ResponseEntity.ok(boardNo);
	}
}
