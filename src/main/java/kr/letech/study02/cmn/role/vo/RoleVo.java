package kr.letech.study02.cmn.role.vo;

import kr.letech.study02.cmn.base.vo.BaseVO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoleVo extends BaseVO {

	private static final long serialVersionUID = 64145025838460714L;

	private String memId;
	private String roleId;
	private String roleNm;
}
