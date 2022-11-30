package kr.letech.study02.cmnt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.letech.study02.cmn.base.vo.BaseController;
import kr.letech.study02.cmnt.service.CmntService;
import kr.letech.study02.cmnt.vo.CmntVo;
import kr.letech.study02.mem.vo.MemVo;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CmntController extends BaseController {

	private final CmntService cmntService;

	@PostMapping("/board/cmnt/write")
	public String cmntWrite(@ModelAttribute CmntVo cmnt, RedirectAttributes redirectAttributes) {
		String boardNo = cmnt.getBoardNo();

		// 사용자 검증
		MemVo memVo = this.getMember();
		String userId = null;
		if (memVo != null) {
			// 검증 완료
			userId = memVo.getMemId();
			cmnt.setRgstId(userId);
			cmntService.createCmnt(cmnt);

			return "redirect:/board/view?no=" + boardNo;
		} else {
			// 검증 실패
			String message = "로그인 후 댓글을 작성할 수 있습니다.";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/board/view?no=" + boardNo;
		}
	}

	@PostMapping("/board/cmnt/updateProcess")
	public String updateProcessing(@ModelAttribute CmntVo cmnt) {
		String boardNo = cmnt.getBoardNo();
		cmntService.modifyCmnt(cmnt);
		return "redirect:/board/view?no=" + boardNo;
	}

	@PostMapping("/board/cmnt/deleteProcess")
	public String deleteProcess(@ModelAttribute CmntVo cmnt) {
		String boardNo = cmnt.getBoardNo();
		cmntService.removeCmnt(cmnt);
		return "redirect:/board/view?no=" + boardNo;
	}
}
