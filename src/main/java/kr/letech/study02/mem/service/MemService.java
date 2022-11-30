package kr.letech.study02.mem.service;

import kr.letech.study02.mem.vo.MemVo;

public interface MemService {

	// 동일 회원 아이디 검증
	public int verifyMemId(String memId);

	// 회원 등록
	public int createMember(MemVo mem);

	// 로그인
	public MemVo retrieveMemberByMemId(String memId);
}
