package com.student.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {
	private static String ARTICLE_IMAGE_REPO = "C:\\studentGrade\\student_image";
	
	@RequestMapping("/download.do")
	protected void download(@RequestParam("imageFileName") String imageFileName, @RequestParam("studentNO") int studentNO, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		OutputStream out = resp.getOutputStream();
		String downFile = ARTICLE_IMAGE_REPO + "\\" + studentNO+"\\"+imageFileName;
		File file = new File(downFile);
		resp.setHeader("Cache-Control", "no-cache");
		resp.addHeader("Content-disposition", "attachment: fileName="+imageFileName);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count = in.read(buffer);
			if(count==-1) break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}
}
