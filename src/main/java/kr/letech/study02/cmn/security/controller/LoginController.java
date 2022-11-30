package kr.letech.study02.cmn.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.letech.study02.cmn.security.vo.UserDetailsVO;

@Controller
public class LoginController {

	@GetMapping("/loginForm.do")
	public String loginForm() {
		return "login/loginForm.login";
	}

	@GetMapping("/about")
	public String about(Model model) {
		// 로그인 성공 시
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsVO userDetailsVO = (UserDetailsVO) authentication.getPrincipal();
		model.addAttribute("userVO", userDetailsVO.getMemVo());
		return "login/about.base";

		// 로그인 실패 시
	}

	@GetMapping("/member/regist")
	public String registerForm() {
		return "login/registerForm.login";
	}
}
