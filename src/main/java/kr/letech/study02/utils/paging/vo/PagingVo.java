package kr.letech.study02.utils.paging.vo;

import java.util.List;

import kr.letech.study02.cmn.base.vo.BaseVO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingVo<T> extends BaseVO {

	private static final long serialVersionUID = -1413594488412468307L;

	private int totalRecord;  	// 게시글 총 수
	private int totalPage;		// 총 페이지 수
	private int screenSize;		// 화면에서 보여질 목록 수
	private int blockSize;		// 페이지네이션 수
	private int currentPage;	// 현재 페이지
	private int startPage;		// 시작 페이지
	private int endPage;		// 끝 페이지
	private int startRow;		// 게시글 시작 번호
	private int endRow;			// 게시글 끝 번호

	private boolean hasPrev;	// 이전 페이지가 가능한 지
	private boolean hasNext;	// 다음 페이지가 가능한 지

	private int prevPage;		// 이전 페이지 번호
	private int nextPage;		// 다음 페이지 번호


	private SearchVo searchVo;
	private List<T> itemList;

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}

	public void setSearchVo(SearchVo searchVo) {
		this.searchVo = searchVo;
	}

	public PagingVo(int currentPage, int screenSize) {
		this.currentPage = currentPage;
		this.screenSize = screenSize;
	}

	public void setPaging(int totalRecord) {
		this.totalRecord = totalRecord;

		this.blockSize = 5;

		totalPage = (int)(Math.ceil(totalRecord / (double) screenSize));

		startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
		endPage = startPage + blockSize - 1;

		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);

		if(totalPage < endPage) {
			endPage = totalPage;
		}

		hasPrev = (startPage == 1) ? false : true;
		hasNext = (endPage == totalPage) ? false : true;

		prevPage = startPage - 1;
		nextPage = endPage + 1;
	}
}
