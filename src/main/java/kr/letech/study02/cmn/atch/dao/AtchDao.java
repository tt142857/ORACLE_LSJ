package kr.letech.study02.cmn.atch.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.letech.study02.cmn.atch.vo.AtchVo;

@Mapper
public interface AtchDao {

	// 첨부파일 상세 조회
	public AtchVo selectAtch(AtchVo atchVo);

	// 첨부파일 등록
	public void insertAtch(AtchVo atchVo);

	// 첨부파일 삭제
	public void deleteAtch(AtchVo atchVo);

}
