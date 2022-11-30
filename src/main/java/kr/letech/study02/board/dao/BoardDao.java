package kr.letech.study02.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.letech.study02.board.vo.BoardVo;
import kr.letech.study02.cmn.atch.vo.AtchVo;
import kr.letech.study02.cmnt.vo.CmntVo;
import kr.letech.study02.utils.paging.vo.PagingVo;

@Mapper
public interface BoardDao {
	// 게시판 조회
	public List<BoardVo> selectBoardList(PagingVo paging);

	// 총 게시판 수 조회
	public int selectBoardTotalCount(PagingVo paging);

	// 게시판 상세 조회
	public BoardVo selectBoardOne(int boardNo);

	// 게시판 등록
	public void insertBoard(BoardVo board);

	// 게시판 삭제
	public void deleteBoard(BoardVo board);

	// 게시판 수정
	public void updateBoard(BoardVo board);

	// 게시판 조회수 증가
	public void updateBoardHit(int boardNo);

	// 게시판 첨부파일 조회
	public List<AtchVo> selectBoardOneAtchList(int boardNo);

	// 게시판 댓글 조회
	public List<CmntVo> selectBoardOneCmntList(int boardNo);

	// 게시판 첨부파일 아이디 수정
	public void updateBoardAtchId(BoardVo board);
}
