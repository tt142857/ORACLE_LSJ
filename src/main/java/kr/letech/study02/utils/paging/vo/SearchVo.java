package kr.letech.study02.utils.paging.vo;

import kr.letech.study02.cmn.base.vo.BaseVO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchVo extends BaseVO {

	private static final long serialVersionUID = -2733927085845475071L;

	private String searchType;
	private String searchWord;
}
