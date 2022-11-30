package kr.letech.study02.cmn.role.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.letech.study02.cmn.role.dao.RoleDao;
import kr.letech.study02.cmn.role.vo.RoleVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;

	@Override
	public List<RoleVo> retrieveRoleList(String memId) {
		return roleDao.selectRoleList(memId);
	}

	@Override
	public void createDefaultRole(String memId) {
		roleDao.insertDefaultRole(memId);
	}

}
