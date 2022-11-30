package kr.letech.study02.board.service;

import java.util.List;

import kr.letech.study02.board.vo.BoardVo;
import kr.letech.study02.utils.paging.vo.PagingVo;

public interface BoardService {
	// 게시판 조회
	public List<BoardVo> retrieveBoardList(PagingVo paging);

	// 총 게시판 수 조회
	public int retrieveBoardTotalCount(PagingVo paging);

	// 게시판 상세 조회
	public BoardVo retrieveBoardOne(int boardNo);

	// 게시판 등록
	public void createBoard(BoardVo board);

	// 게시판 삭제
	public void removeBoard(BoardVo board);

	// 게시판 수정
	public void modifyBoard(BoardVo board);
}
