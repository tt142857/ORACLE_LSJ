package kr.letech.study02.cmn.code.vo;

import kr.letech.study02.cmn.base.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeGrpVO extends BaseVO {

	private static final long serialVersionUID = 6181158223889307843L;

	private String cdGrp;	// 코드 그룹
	private String cdGrpNm; // 코드 그룹 명
	private String ord;
	private String useYn;
	private String rgstId;
	private String rgstDt;
	private String updtId;
	private String updtDt;
	private String delYn;
}
