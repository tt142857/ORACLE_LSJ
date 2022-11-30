package kr.letech.study02.mem.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@RequiredArgsConstructor
@Slf4j
public class MemVo {

	private String memId;
	private String memPswd;
	private String memNm;
	private String memEml;
}


