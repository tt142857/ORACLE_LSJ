package kr.letech.study02.cmnt.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.letech.study02.cmn.base.vo.BaseController;
import kr.letech.study02.cmnt.service.CmntService;
import kr.letech.study02.cmnt.vo.CmntVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CmntApiController extends BaseController {

	public final CmntService cmntService;

	// 댓글 목록 조회
	@GetMapping("/api/v1/cmnts")
	@ResponseBody
	public ResponseEntity<?> cmntList(@RequestParam("no") int boardNo) {
		List<CmntVo> cmntList = this.cmntService.retrieveBoardOneCmntList(boardNo);
		return ResponseEntity.ok(cmntList);
	}

	// 댓글 등록
	@PostMapping("/api/v1/cmnts")
	@ResponseBody
	public ResponseEntity<?> cmntWriteProcess(@ModelAttribute CmntVo cmntVo) {
		String userId = userId();
		cmntVo.setRgstId(userId);

		String rgstId = cmntVo.getRgstId();
		if(rgstId != null) {
			// 작성자 검증 성공
			this.cmntService.createCmnt(cmntVo);
			return ResponseEntity.ok("success");
		} else {
			// 작성자 검증 실패
			return ResponseEntity.ok("failed");
		}
	}

	// 댓글 수정
	@PutMapping("/api/v1/cmnts/{cmntSeq}")
	@ResponseBody
	public ResponseEntity<?> cmntUpdateProcess(@ModelAttribute CmntVo cmntVo) {
		String userId = userId();
		cmntVo.setUpdtId(userId);


		String rgstId = this.cmntService.retrieveBoardOneCmntRgstId(cmntVo);
		if(rgstId != null && rgstId.equals(userId)) {
			// 작성자 검증 성공
			this.cmntService.modifyCmnt(cmntVo);
			return ResponseEntity.ok("success");
		} else {
			// 작성자 검증 실패
			return ResponseEntity.ok("failed");
		}
	}

	// 댓글 삭제
	@DeleteMapping("/api/v1/cmnts/{cmntSeq}")
	@ResponseBody
	public ResponseEntity<?> cmntDeleteProcess(@ModelAttribute CmntVo cmntVo) {
		String userId = userId();
		cmntVo.setUpdtId(userId);

		String rgstId = this.cmntService.retrieveBoardOneCmntRgstId(cmntVo);
		if(rgstId != null && rgstId.equals(userId)) {
			// 작성자 검증 성공
			this.cmntService.removeCmnt(cmntVo);
			return ResponseEntity.ok("success");
		} else {
			// 작성자 검증 실패
			return ResponseEntity.ok("failed");
		}
	}

}
