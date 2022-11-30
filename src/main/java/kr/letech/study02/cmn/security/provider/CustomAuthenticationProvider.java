package kr.letech.study02.cmn.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.letech.study02.cmn.security.vo.UserDetailsVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        String id = token.getName();
        String userPw = (String) token.getCredentials();
        // UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        UserDetailsVO userDetailsVO = (UserDetailsVO) userDetailsService.loadUserByUsername(id);

        if (!passwordEncoder.matches(userPw, userDetailsVO.getPassword())) {
            throw new BadCredentialsException(userDetailsVO.getUsername() + "Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetailsVO, userPw, userDetailsVO.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
