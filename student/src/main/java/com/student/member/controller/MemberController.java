package com.student.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.student.member.vo.MemberVO;


public interface MemberController {
	public ResponseEntity addMember(MultipartHttpServletRequest multipartReq, HttpServletResponse resp) throws Exception;
	public ResponseEntity removeMember(@RequestParam("id") String id, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView logout(RedirectAttributes rAttr, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView modMember(@RequestParam("id") String id, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ModelAndView viewDetail(@RequestParam("id") String id, HttpServletRequest req, HttpServletResponse resp) throws Exception;
	public ResponseEntity updateMember(MultipartHttpServletRequest multipartReq,  HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
