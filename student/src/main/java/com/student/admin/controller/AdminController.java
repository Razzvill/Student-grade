package com.student.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.student.admin.vo.GradeVO;

public interface AdminController {
	public ModelAndView listMembers(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView listGrades(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView removeGrade(@RequestParam("studentNO") String studentNO, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView modGrade(@RequestParam("studentNO") String studentNO, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView updateGrade(@ModelAttribute("info") GradeVO adminVO,  HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ResponseEntity removeMember(@RequestParam("id") String id, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ResponseEntity updateMember(MultipartHttpServletRequest multipartReq,  HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
