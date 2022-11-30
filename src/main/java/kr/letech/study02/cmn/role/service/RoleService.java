package kr.letech.study02.cmn.role.service;

import java.util.List;

import kr.letech.study02.cmn.role.vo.RoleVo;

public interface RoleService {

	// 권한 목록 조회
	public List<RoleVo> retrieveRoleList(String memId);

	// 권한 등록
	public void createDefaultRole(String memId);
}
