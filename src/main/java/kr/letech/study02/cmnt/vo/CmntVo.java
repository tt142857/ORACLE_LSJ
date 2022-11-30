package kr.letech.study02.cmnt.vo;

import kr.letech.study02.cmn.base.vo.BaseVO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CmntVo extends BaseVO {

	private static final long serialVersionUID = 2316103684626886890L;

	private Integer cmntSeq;
	private String boardNo;
	private String cmntCont;
	private String rgstId;
	private String rgstDt;
	private String updtId;
	private String updtDt;
	private String delYn;
}
