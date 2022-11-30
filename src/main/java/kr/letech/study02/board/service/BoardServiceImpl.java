package kr.letech.study02.board.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.letech.study02.board.dao.BoardDao;
import kr.letech.study02.board.vo.BoardVo;
import kr.letech.study02.cmn.atch.dao.AtchDao;
import kr.letech.study02.cmn.atch.vo.AtchVo;
import kr.letech.study02.cmnt.dao.CmntDao;
import kr.letech.study02.cmnt.vo.CmntVo;
import kr.letech.study02.utils.date.DateUtils;
import kr.letech.study02.utils.paging.vo.PagingVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

	private final BoardDao boardDao;
	private final AtchDao atchDao;
	private final CmntDao cmntDao;

	@Value("${atch.file.store.path}")
	private String savePath;

	// 게시판 조회
	@Override
	public List<BoardVo> retrieveBoardList(PagingVo paging) {
		return this.boardDao.selectBoardList(paging);
	}

	// 총 게시판 수 조회
	@Override
	public int retrieveBoardTotalCount(PagingVo paging) {
		return this.boardDao.selectBoardTotalCount(paging);
	}

	// 게시판 상세 조회
	@Override
	public BoardVo retrieveBoardOne(int boardNo) {
		this.boardDao.updateBoardHit(boardNo);
		BoardVo board = this.boardDao.selectBoardOne(boardNo);
		List<AtchVo> atchList = this.boardDao.selectBoardOneAtchList(boardNo);
		List<CmntVo> cmntList = this.boardDao.selectBoardOneCmntList(boardNo);
		board.setAtchList(atchList);
		board.setCmntList(cmntList);
		return board;
	}

	// 게시판 등록
	@Transactional
	@Override
	public void createBoard(BoardVo board) {
		// 첨부파일이 있는 경우
		uploadProcess(board);

		this.boardDao.insertBoard(board);
	}

	public void uploadProcess(BoardVo board) {
		List<AtchVo> atchList = board.getAtchList();
		if(atchList != null && atchList.size() > 0) {
			String atchId = board.getAtchId();
			if(atchId == null || atchId.isBlank()) {
				atchId =  UUID.randomUUID().toString();
				board.setAtchId(atchId);
				this.boardDao.updateBoardAtchId(board);
			}
			String rgstId = board.getRgstId();
			String saveRealPath = savePath + "/" + DateUtils.getInstance().getFormatDate();

			board.setAtchId(atchId);
			board.uploadFiles(saveRealPath);

			for(AtchVo atch : board.getAtchList()) {
				atch.setAtchId(atchId);
				atch.setRgstId(rgstId);
				atch.setAtchPath(saveRealPath);
				this.atchDao.insertAtch(atch);
			}
		}
	}

	// 게시판 삭제
	@Transactional
	@Override
	public void removeBoard(BoardVo board) {
		// 게시판 첨부파일 삭제
		List<AtchVo> atchList = board.getAtchList();
		if(atchList != null && atchList.size() > 0) {
			for(AtchVo atch : atchList) {
				this.atchDao.deleteAtch(atch);
			}
		}

		// 게시판 댓글 삭제
		this.cmntDao.deleteCmntAll(board.getBoardNo());
		// 게시판 삭제
		this.boardDao.deleteBoard(board);
	}

	// 게시판 수정
	@Transactional
	@Override
	public void modifyBoard(BoardVo board) {
		log.info("수정 게시판: {}", board);
		// 첨부파일 삭제가 있는 경우
		if(board.getDelSeq().length > 0) {
			String atchId = board.getAtchId();

			AtchVo atch = null;
			for(int atchSeq : board.getDelSeq()) {
				atch = new AtchVo();
				atch.setAtchId(atchId);
				atch.setAtchSeq(atchSeq);
				this.atchDao.deleteAtch(atch);
			}
		}

		// 추가된 첨부파일이 있는 경우
		uploadProcess(board);

		// 수정
		this.boardDao.updateBoard(board);
	}


}
