package kr.letech.study02.cmn.atch.vo;

import java.text.DecimalFormat;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AtchVo {
	private String atchId;
	private Integer atchSeq;
	private String atchOrigNm;
	private String atchSaveNm;
	private String atchPath;
	private Long atchSize;
	private String atchExtn;
	private String rgstId;
	private String rgstDt;
	private String updtId;
	private String updtDt;
	private String delYn;

	private String cnvrtSize;

	public AtchVo(MultipartFile file) {
		this.atchOrigNm = file.getOriginalFilename();
		this.atchExtn = "";
		int extnPos = this.atchOrigNm.lastIndexOf(".");

		if(extnPos != -1) {
			this.atchExtn = this.atchOrigNm.substring(extnPos + 1);
			this.atchOrigNm = this.atchOrigNm.substring(0, extnPos);
		}
		this.atchSaveNm = UUID.randomUUID().toString();
		this.atchSize = file.getSize();
	}

	public String getCnvrtSize() {
		String result = "0";

		if(this.atchSize != null) {
			String cnvrtSize = String.valueOf(this.atchSize);
			Double size = Double.parseDouble(cnvrtSize);

			String[] s = { "B", "KB", "MB", "GB", "TB", "PB" };

			if (!"0".equals(cnvrtSize)) {
				int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
				DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
				double formattedData = ((size / Math.pow(1024, Math.floor(idx))));
				result = decimalFormat.format(formattedData) + s[idx];
			} else {
				result += " " + s[0];
			}
		}
		return result;
	}
}
