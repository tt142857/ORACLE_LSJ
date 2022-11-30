package kr.letech.study02.cmnt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.letech.study02.cmnt.vo.CmntVo;

@Mapper
public interface CmntDao {

	// 댓글 등록
	public void insertCmnt(CmntVo cmnt);

	// 댓글 수정
	public void updateCmnt(CmntVo cmnt);

	// 댓글 삭제
	public void deleteCmnt(CmntVo cmnt);

	// 댓글 전체 삭제(게시판 삭제 시 사용)
	public void deleteCmntAll(int boardNo);

	// 게시글 댓글 목록 조회
	public List<CmntVo> selectBoardOneCmntList(int boardNo);

	// 게시글 댓글 작성자 조회
	public String selectBoardOneCmntRgstId(CmntVo cmnt);
}
