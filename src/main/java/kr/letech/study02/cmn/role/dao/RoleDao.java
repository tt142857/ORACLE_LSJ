package kr.letech.study02.cmn.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.letech.study02.cmn.role.vo.RoleVo;

@Mapper
public interface RoleDao {

	// 권한 조회
	public List<RoleVo> selectRoleList(String memId);

	// 권한 등록
	public void insertDefaultRole(String memId);
}
