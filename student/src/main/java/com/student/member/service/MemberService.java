package com.student.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.student.member.vo.MemberVO;

public interface MemberService {
	public int addMember(Map memberMap) throws DataAccessException;
	public MemberVO modMember(String id) throws DataAccessException;
	public int removeMember(String id) throws DataAccessException;
	public MemberVO login(MemberVO memberVO) throws DataAccessException;
	public int updateMember(Map memberMap) throws DataAccessException;
	public MemberVO viewDetail(String id) throws DataAccessException;
}
