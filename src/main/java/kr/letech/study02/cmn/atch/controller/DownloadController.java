package kr.letech.study02.cmn.atch.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.letech.study02.cmn.atch.dao.AtchDao;
import kr.letech.study02.cmn.atch.vo.AtchVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DownloadController {

	@Value("{atch.file.store.path}")
	String savePath;

	private final AtchDao atchDao;

	@GetMapping("/download/{atchId}/{atchSeq}")
	public void download(@PathVariable String atchId, @PathVariable int atchSeq, HttpServletResponse response) {
		AtchVo atch = new AtchVo();
		atch.setAtchId(atchId);
		atch.setAtchSeq(atchSeq);

		AtchVo selectedAtch = this.atchDao.selectAtch(atch);
		String atchOrigNm = selectedAtch.getAtchOrigNm();
		String atchExtn = null;
		if(StringUtils.isNotBlank(selectedAtch.getAtchExtn())) {
			atchExtn = "." + selectedAtch.getAtchExtn();
		} else {
			atchExtn = "";
		}

		File file = new File(selectedAtch.getAtchPath() + "/" + selectedAtch.getAtchSaveNm());
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(file);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(atchOrigNm + atchExtn, "UTF-8")+"\";");
			response.setContentLengthLong(file.length());

			os = response.getOutputStream();
			IOUtils.copy(fis, os);

		} catch (IOException e) {
			String message = "존재하지 않는 파일입니다.";
			throw new kr.letech.study02.cmn.exception.FileNotFoundException(message, e);
		} finally {
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(os);
		}
	}
}
