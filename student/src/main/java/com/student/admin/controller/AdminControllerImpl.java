package com.student.admin.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.student.admin.service.AdminService;
import com.student.admin.vo.GradeVO;
import com.student.admin.vo.TotalVO;
import com.student.member.service.MemberService;

@Controller("adminController")
public class AdminControllerImpl implements AdminController {
	private static String ARTICLE_IMAGE_REPO = "C:\\studentGrade\\student_image";
	@Autowired
	private MemberService memberService;
	@Autowired
	private AdminService adminService;
	
	@Override
	@RequestMapping(value="/admin/listGrades.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listGrades(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("html/text;charset=utf-8");
		String viewName = (String)req.getAttribute("viewName");
		List gradesList = adminService.listGrades();
		TotalVO totalVO = adminService.selectTotalList();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("gradesList", gradesList);
		mav.addObject("totalVO", totalVO);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/admin/listMembers.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listMembers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("html/text;charset=utf-8");
		String viewName = (String)req.getAttribute("viewName");
		List membersList = adminService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/admin/removeGrade.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView removeGrade(@RequestParam("studentNO") String studentNO, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf-8");
		adminService.removeGrade(Integer.parseInt(studentNO));
		ModelAndView mav = new ModelAndView("redirect:/admin/listGrades.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/admin/modGrade.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modGrade(@RequestParam("studentNO") String studentNO, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("Call modGrade-method of control");
		req.setCharacterEncoding("utf-8");
		String viewName = (String)req.getAttribute("viewName");
		System.out.println("viewName: "+viewName);
		GradeVO gradeVO = adminService.modGrade(Integer.parseInt(studentNO));
		ModelAndView mav = new ModelAndView();
		mav.addObject("grade", gradeVO);
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/admin/updateGrade.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateGrade(@ModelAttribute("info") GradeVO grade, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("Call updateGrade-method of control");
		req.setCharacterEncoding("utf-8");
		int result = 0;
		result = adminService.updateGrade(grade);
		ModelAndView mav = new ModelAndView("redirect:/admin/listGrades.do");
		return mav;
	}

	@Override
	@RequestMapping(value="/admin/removeMember.do", method={RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity removeMember(@RequestParam("id") String id, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			int studentNO = memberService.removeMember(id);
			File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+studentNO);
			FileUtils.deleteDirectory(destDir);
			message = "<script>";
			message += " alert('학생 정보를 삭제했습니다.');";
			message += "location.href='"+req.getContextPath()+"/admin/listMembers.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('삭제 중 오류가 발생했습니다.');";
			message += "location.href='"+req.getContextPath()+"/admin/listMembers.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
	

	@Override
	@RequestMapping(value="/admin/updateMember.do", method={RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity updateMember(MultipartHttpServletRequest multipartReq,  HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		multipartReq.setCharacterEncoding("utf-8");
		Map<String, Object> memberMap = new HashMap<String, Object>();
		Enumeration enu = multipartReq.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = multipartReq.getParameter(name);
			memberMap.put(name, value);
		}
		
		String imageFileName = upload(multipartReq);
		memberMap.put("imageFileName", imageFileName);
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			int studentNO = memberService.updateMember(memberMap);
			if(imageFileName!=null&&imageFileName.length()!=0) {
				String originalFileName = (String)memberMap.get("originalFileName");
				File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+originalFileName);
				oldFile.delete();
				
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+studentNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			message = "<script>";
			message += " alert('학생 정보를 수정했습니다.');";
			message += "location.href='"+multipartReq.getContextPath()+"/admin/listMembers.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			srcFile.delete();
			message = "<script>";
			message += " alert('수정 중 오류가 발생했습니다.');";
			message += "location.href='"+multipartReq.getContextPath()+"/admin/listMembers.do;";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}

	@RequestMapping(value="/admin/*Form.do", method={RequestMethod.GET, RequestMethod.POST})
	private ModelAndView form(@RequestParam(value="result", required=false) String result, @RequestParam(value="action", required=false) String action, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String viewName = (String) req.getAttribute("viewName");
		HttpSession session = req.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}

	private String getViewName(HttpServletRequest req) throws Exception {
		String contextPath = req.getContextPath();
		String uri = (String) req.getAttribute("javax.servlet.include.request_uri");
		if(uri==null||uri.trim().equals("")) {
			uri = req.getRequestURI();
		}
		
		int begin = 0;
		if(!((contextPath==null)||("".equals(contextPath)))) {
			begin = contextPath.length();
		}
		
		int end;
		if(uri.indexOf(";")!=-1) {
			end = uri.indexOf(";");
		} else if(uri.indexOf("?")!=-1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}
		
		String viewName = uri.substring(begin, end);
		if(viewName.indexOf(".")!=-1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if(viewName.indexOf("/")!=-1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}
	
	private String upload(MultipartHttpServletRequest multipartReq) throws Exception {
		String imageFileName = null;
		Iterator<String> fileNames = multipartReq.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartReq.getFile(fileName);
			imageFileName = mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+fileName);
			if(mFile.getSize()!=0) {
				if(!file.exists()) {
					file.getParentFile().mkdirs();
					mFile.transferTo(new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName));
				}
			}
		}
		return imageFileName;
	}
}
