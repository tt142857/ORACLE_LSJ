package kr.letech.study02.cmn.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.letech.study02.cmn.code.vo.CodeGrpSearchVO;
import kr.letech.study02.cmn.code.vo.CodeGrpVO;
import kr.letech.study02.cmn.code.vo.CodeSearchVO;
import kr.letech.study02.cmn.code.vo.CodeVO;

@Mapper
public interface CodeDAO {

	List<CodeGrpVO> selectCodeGrpList(CodeGrpSearchVO searchVO);
	CodeGrpVO selectCodeGrp(CodeGrpVO codeGrpVO);
	int insertCodeGrp(CodeGrpVO codeGrpVO);
	int updateCodeGrp(CodeGrpVO codeGrpVO);
	int deleteCodeGrp(CodeGrpVO codeGrpVO);

	List<CodeVO> selectCodeList(CodeSearchVO searchVO);
	CodeVO selectCode(CodeVO codeVO);
	int insertCode(CodeVO codeVO);
	int updateCode(CodeVO codeVO);
	int deleteCode(CodeVO codeVO);
}
