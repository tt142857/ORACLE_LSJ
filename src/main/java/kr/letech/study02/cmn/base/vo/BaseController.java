package kr.letech.study02.cmn.base.vo;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.letech.study02.mem.vo.MemVo;

public class BaseController {

	public MemVo getMember() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = servletRequestAttributes.getRequest().getSession();

		MemVo memVo = (MemVo) httpSession.getAttribute("userInfo");

		return memVo;
	}

	// 로그인한 userId 반환
	public String userId() {
		MemVo memVo = this.getMember();
		String memId = null;
		if (memVo != null) {
			memId = memVo.getMemId();
		}
		return memId;
	}
}
