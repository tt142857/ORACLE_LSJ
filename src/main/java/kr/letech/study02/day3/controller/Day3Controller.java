package kr.letech.study02.day3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Day3Controller {
	
	@GetMapping("/index")
	public String index() {
		return "index/index.base";
	}
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home() {
		return "home/home.base";
	}
}
