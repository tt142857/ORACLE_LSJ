package kr.letech.study02.cmn.code.vo;

import kr.letech.study02.cmn.base.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// callSuper: 상위 객체만 봄
//@ToString(callSuper = true)
public class CodeGrpSearchVO extends BaseVO {

	private static final long serialVersionUID = -8758679090510832979L;

	private String srchCdGrp;	// 코드 그룹
	private String srchCdGrpNm; // 코드 그룹 명
}
