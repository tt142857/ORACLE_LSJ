package kr.letech.study02.board.vo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.letech.study02.cmn.atch.vo.AtchVo;
import kr.letech.study02.cmn.base.vo.BaseVO;
import kr.letech.study02.cmn.exception.FileUploadException;
import kr.letech.study02.cmnt.vo.CmntVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BoardVo extends BaseVO {

	private static final long serialVersionUID = 7790406301936752351L;

	private Integer boardNo;
	private String boardDiv;
	@NonNull
	private String boardTtl;
	private String boardCat;
	@NonNull
	private String boardCont;
	private String atchId;
	private String rgstId;
	private String rgstDt;
	private String updtId;
	private String updtDt;
	private String delYn;
	private Integer boardHit;

	private String boardDivNm;
	private String boardCatNm;

	private List<CmntVo> cmntList;

	private MultipartFile[] files;
	private List<AtchVo> atchList;
	private Integer[] delSeq;

	public void setFiles(MultipartFile[] files) {
		if(files == null || files.length == 0) {
			return;
		}

		this.files = files;
		this.atchList = new ArrayList<AtchVo>();
		for(int seq = 0; seq < files.length; seq++) {
			if(!files[seq].isEmpty()) {
				AtchVo atch = new AtchVo(files[seq]);
				atch.setAtchSeq(seq);
				atchList.add(atch);
			}
		}
	}

	public void uploadFiles(String savePath) {
		for(int i = 0; i < atchList.size(); i++) {
			AtchVo atch = atchList.get(i);

			File dir = new File(savePath);
			if(!dir.exists()) {
				dir.mkdirs();
			}

			String filePath = savePath + "/" + atch.getAtchSaveNm();
			try {
				files[i].transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				String message = "파일 업로드 중 파일 오류 발생";
				throw new FileUploadException(message, e);
			}
		}
	}
}
