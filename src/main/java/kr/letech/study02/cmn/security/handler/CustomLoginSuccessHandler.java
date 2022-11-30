package kr.letech.study02.cmn.security.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import kr.letech.study02.cmn.security.vo.UserDetailsVO;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
    		HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsVO detailsVO = (UserDetailsVO) authentication.getPrincipal();
        request.getSession(true).setAttribute("userInfo", detailsVO.getMemVo());
        response.sendRedirect("/about");
    }
}
