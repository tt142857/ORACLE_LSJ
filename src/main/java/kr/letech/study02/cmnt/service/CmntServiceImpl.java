package kr.letech.study02.cmnt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.letech.study02.cmnt.dao.CmntDao;
import kr.letech.study02.cmnt.vo.CmntVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CmntServiceImpl implements CmntService {

	private final CmntDao cmntDao;

	@Override
	public void createCmnt(CmntVo cmnt) {
		this.cmntDao.insertCmnt(cmnt);
	}

	@Override
	public void modifyCmnt(CmntVo cmnt) {
		this.cmntDao.updateCmnt(cmnt);
	}

	@Override
	public void removeCmnt(CmntVo cmnt) {
		this.cmntDao.deleteCmnt(cmnt);
	}

	@Override
	public List<CmntVo> retrieveBoardOneCmntList(int boardNo) {
		return this.cmntDao.selectBoardOneCmntList(boardNo);
	}

	@Override
	public String retrieveBoardOneCmntRgstId(CmntVo cmnt) {
		return this.cmntDao.selectBoardOneCmntRgstId(cmnt);
	}

}
