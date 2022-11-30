package kr.letech.study02.cmn.code.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.letech.study02.cmn.base.vo.BaseController;
import kr.letech.study02.cmn.code.service.CodeService;
import kr.letech.study02.cmn.code.vo.CodeGrpSearchVO;
import kr.letech.study02.cmn.code.vo.CodeGrpVO;
import kr.letech.study02.mem.vo.MemVo;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CodeController extends BaseController {

	private final CodeService codeService;

	@GetMapping("/cmn/code/codeGrpList.do")
	public String codeList(CodeGrpSearchVO searchVO, Model model) {
		List<CodeGrpVO> list = this.codeService.selectCodeGrpList(searchVO);
		model.addAttribute("list", list);
		model.addAttribute("searchVO", searchVO);
		return "cmn/code/codeGrpList.base";
	}

	@GetMapping("/cmn/code/codeGrpDtl.do")
	public String codeDtl(CodeGrpVO codeGrpVO, Model model) {
		CodeGrpVO data = this.codeService.selectCodeGrp(codeGrpVO);
		model.addAttribute("data", data);
		return "cmn/code/codeGrpDtl.base";
	}

	@GetMapping("/cmn/code/insertCodeGrp.do")
	public String insertCodeGrpForm(Model model) {
		return "cmn/code/insertCodeGrpForm.base";
	}

	@PostMapping("/cmn/code/insertCodeGrp.do")
	public String insertCodeGrp(CodeGrpVO codeGrpVO, Model model) {
		MemVo memVo = this.getMember();
		if (memVo != null) {
			String memId = memVo.getMemId();
			codeGrpVO.setRgstId(memId);
			codeGrpVO.setUpdtId(memId);
		}

		this.codeService.insertCodeGrp(codeGrpVO);
		CodeGrpVO data = this.codeService.selectCodeGrp(codeGrpVO);
		model.addAttribute("data", data);
		return "redirect:/cmn/code/codeGrpDtl.do?cdGrp" + codeGrpVO.getCdGrp();
	}
}
