package kr.letech.study02.cmn.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.letech.study02.cmn.role.service.RoleService;
import kr.letech.study02.cmn.role.vo.RoleVo;
import kr.letech.study02.cmn.security.vo.UserDetailsVO;
import kr.letech.study02.mem.service.MemService;
import kr.letech.study02.mem.vo.MemVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	private final MemService memService;
	private final RoleService roleService;

    @Override
    public UserDetailsVO loadUserByUsername(String id) {
    	UserDetailsVO result = null;
    	List<GrantedAuthority> authorities = null;
    	MemVo member = memService.retrieveMemberByMemId(id);
    	member.setMemPswd(new BCryptPasswordEncoder().encode(member.getMemPswd()));
    	if(member != null) {
    		authorities = new ArrayList<GrantedAuthority>();
    		List<RoleVo> roleList = roleService.retrieveRoleList(id);
    		for(RoleVo role : roleList) {
    			authorities.add(new SimpleGrantedAuthority(role.getRoleNm()));
    		}
    		result = new UserDetailsVO(member, authorities);
    	}

    	return result;
    }

}