package kr.letech.study02.mem.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.letech.study02.mem.vo.MemVo;

@Mapper
public interface MemDao {

	// 동일 회원 아이디 검증
	public int checkMemId(String memId);

	// 회원 등록
	public int inserMember(MemVo member);

	// 로그인
	public MemVo selectMemberByMemId(String memId);
}
