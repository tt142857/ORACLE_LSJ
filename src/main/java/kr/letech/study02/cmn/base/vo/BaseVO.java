package kr.letech.study02.cmn.base.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseVO implements Serializable {

	// *Serializable을 하는 이유:
	// 	이 UID를 가진 BaseVO는 다 같은 객체다라는 의미를 부여함
	// 	VO를 쓸 때 습관적으로 쓸 것
	private static final long serialVersionUID = 2943108954609350729L;
	
	// 파라미터를 갖는 값들은 인터페이스로 쓸 것
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

	
}
