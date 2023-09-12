package com.student.member.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.student.admin.vo.TotalVO;
import com.student.member.service.MemberService;
import com.student.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	private static String ARTICLE_IMAGE_REPO = "C:\\studentGrade\\student_image";
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;

	@RequestMapping(value={"/main.do"}, method={RequestMethod.GET, RequestMethod.POST})
	private ModelAndView main(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String viewName = (String)req.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/addMember.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity addMember(MultipartHttpServletRequest multipartReq, HttpServletResponse resp) throws Exception {
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
		System.out.println(memberMap);
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			int studentNO = memberService.addMember(memberMap);
			if(imageFileName!=null&&imageFileName.length()!=0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+studentNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			message = "<script>";
			message += " alert('새로운 학생을 등록했습니다.');";
			message += "location.href='"+multipartReq.getContextPath()+"/member/viewDetail.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			srcFile.delete();
			message = "<script>";
			message += " alert('추가 중 오류가 발생했습니다.');";
			message += "location.href='"+multipartReq.getContextPath()+"/member/memberForm.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@Override
	@RequestMapping(value="/member/removeMember.do", method={RequestMethod.GET, RequestMethod.POST})
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
			message += "location.href='"+req.getContextPath()+"/member/loginForm.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += " alert('삭제 중 오류가 발생했습니다.');";
			message += "location.href='"+req.getContextPath()+"/member/viewDetail.do?id="+id+"';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@Override
	@RequestMapping(value="/member/viewDetail.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView viewDetail(@RequestParam("id") String id, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("html/text;charset=utf-8");
		String viewName = (String)req.getAttribute("viewName");
		MemberVO memberVO = memberService.viewDetail(id);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("member", memberVO);
		return mav;
	}

	@RequestMapping(value={"/member/loginForm.do", "/member/memberForm.do"}, method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView form(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String viewName = getViewName(req);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/login.do", method={RequestMethod.POST})
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if(memberVO!=null) {
			HttpSession session = req.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			String action = (String) session.getAttribute("action");
			String id = memberVO.getId();
			session.removeAttribute("action");
			if(action!=null) {
				mav.setViewName("redirect:"+action);
			} else {
				mav.setViewName("redirect:/member/viewDetail.do?id="+id);
			}
		} else {
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}

	@Override
	@RequestMapping(value="/member/modMember.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modMember(@RequestParam("id") String id, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("Call modMember-method of control");
		req.setCharacterEncoding("utf-8");
		String viewName = (String)req.getAttribute("viewName");
		System.out.println("viewName: "+viewName);
		MemberVO memberVO = memberService.modMember(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("member", memberVO);
		mav.setViewName(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/member/logout.do", method={RequestMethod.GET})
	public ModelAndView logout(RedirectAttributes rAttr, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		HttpSession session = req.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/loginForm.do");
		return mav;
	}

	@RequestMapping(value="/member/*Form.do", method={RequestMethod.GET, RequestMethod.POST})
	private ModelAndView form(@RequestParam(value="result", required=false) String result, @RequestParam(value="action", required=false) String action, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String viewName = (String) req.getAttribute("viewName");
		HttpSession session = req.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/updateMember.do", method={RequestMethod.GET, RequestMethod.POST})
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
		String id = (String)memberMap.get("id");
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
			message += "location.href='"+multipartReq.getContextPath()+"/member/viewDetail.do?id="+id+"';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			srcFile.delete();
			message = "<script>";
			message += " alert('수정 중 오류가 발생했습니다.');";
			message += "location.href='"+multipartReq.getContextPath()+"/member/viewDetail.do?id="+id+"';";
			message += "</script>";
			e.printStackTrace();
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
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
