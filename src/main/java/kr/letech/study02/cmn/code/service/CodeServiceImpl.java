package kr.letech.study02.cmn.code.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.letech.study02.cmn.code.dao.CodeDAO;
import kr.letech.study02.cmn.code.vo.CodeGrpSearchVO;
import kr.letech.study02.cmn.code.vo.CodeGrpVO;
import kr.letech.study02.cmn.code.vo.CodeSearchVO;
import kr.letech.study02.cmn.code.vo.CodeVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

	private final CodeDAO codeDAO;

	@Override
	public List<CodeGrpVO> selectCodeGrpList(CodeGrpSearchVO searchVO) {
		return this.codeDAO.selectCodeGrpList(searchVO);
	}

	@Override
	public CodeGrpVO selectCodeGrp(CodeGrpVO codeGrpVO) {
		return this.codeDAO.selectCodeGrp(codeGrpVO);
	}

	@Override
	public int insertCodeGrp(CodeGrpVO codeGrpVO) {
		return this.codeDAO.insertCodeGrp(codeGrpVO);
	}

	@Override
	public int updateCodeGrp(CodeGrpVO codeGrpVO) {
		return this.codeDAO.updateCodeGrp(codeGrpVO);
	}

	@Override
	public int deleteCodeGrp(CodeGrpVO codeGrpVO) {
		return this.codeDAO.deleteCodeGrp(codeGrpVO);
	}

	@Override
	public List<CodeVO> selectCodeList(CodeSearchVO searchVO) {
		return this.codeDAO.selectCodeList(searchVO);
	}

	@Override
	public CodeVO selectCode(CodeVO codeVO) {
		return this.codeDAO.selectCode(codeVO);
	}

	@Override
	public int insertCode(CodeVO codeVO) {
		return this.codeDAO.insertCode(codeVO);
	}

	@Override
	public int updateCode(CodeVO codeVO) {
		return this.codeDAO.updateCode(codeVO);
	}

	@Override
	public int deleteCode(CodeVO codeVO) {
		return this.codeDAO.deleteCode(codeVO);
	}


}
