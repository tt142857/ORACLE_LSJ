package kr.letech.study02.mem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.letech.study02.mem.service.MemService;
import kr.letech.study02.mem.vo.MemVo;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemApiController {

	private final MemService memService;

	// 동일 회원 아이디 검증
	@GetMapping("/api/v1/member/check")
	public ResponseEntity<?> checkMemId(String memId) {
		int count = 0;
		count = memService.verifyMemId(memId);
		return ResponseEntity.ok(count);
	}

	@PostMapping("/api/v1/member")
	public ResponseEntity<?> insertMember(MemVo member) {
		int count = 0;
		count = memService.createMember(member);
		if(count > 0) {
			return ResponseEntity.ok("success");
		} else {
			return ResponseEntity.ok("failed");
		}
	}
}
