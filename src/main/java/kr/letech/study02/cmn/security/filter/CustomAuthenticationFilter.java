package kr.letech.study02.cmn.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	String id = request.getParameter("id");
    	String pw = request.getParameter("pw");
    	
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(id, pw);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
