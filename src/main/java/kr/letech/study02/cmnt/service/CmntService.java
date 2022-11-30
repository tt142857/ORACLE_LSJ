package kr.letech.study02.cmnt.service;

import java.util.List;

import kr.letech.study02.cmnt.vo.CmntVo;

public interface CmntService {

	// 댓글 등록
	public void createCmnt(CmntVo cmnt);

	// 댓글 수정
	public void modifyCmnt(CmntVo cmnt);

	// 댓글 삭제
	public void removeCmnt(CmntVo cmnt);

	// 게시글 댓글 목록 조회
	public List<CmntVo> retrieveBoardOneCmntList(int boardNo);

	// 게시글 댓글 작성자 조회
	public String retrieveBoardOneCmntRgstId(CmntVo cmnt);
}
