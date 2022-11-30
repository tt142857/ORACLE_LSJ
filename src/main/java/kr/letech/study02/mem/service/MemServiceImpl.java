package kr.letech.study02.mem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.letech.study02.cmn.role.dao.RoleDao;
import kr.letech.study02.mem.dao.MemDao;
import kr.letech.study02.mem.vo.MemVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemServiceImpl implements MemService {

	private final MemDao memDao;
	private final RoleDao roleDao;

	@Override
	public int verifyMemId(String memId) {
		return memDao.checkMemId(memId);
	}

	@Transactional
	@Override
	public int createMember(MemVo member) {
		int count = 0;

		count = memDao.inserMember(member);
		if(count > 0) {
			roleDao.insertDefaultRole(member.getMemId());
		}
		return count;
	}

	@Override
	public MemVo retrieveMemberByMemId(String memId) {
		return memDao.selectMemberByMemId(memId);
	}
}
