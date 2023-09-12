package com.student.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.student.member.dao.MemberDAO;
import com.student.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public int addMember(Map memberMap) throws DataAccessException {
		memberDAO.insertMember(memberMap);
		return memberDAO.selectStdNOById((String)memberMap.get("id"));
	}

	@Override
	public MemberVO modMember(String id) throws DataAccessException {
		MemberVO memberVO = new MemberVO();
		memberVO = memberDAO.selectMemberById(id);
		return memberVO;
	}

	@Override
	public int removeMember(String id) throws DataAccessException {
		int stdNO = memberDAO.selectStdNOById(id);
		memberDAO.deleteMember(id);
		return stdNO;
	}

	@Override
	public MemberVO login(MemberVO memberVO) throws DataAccessException {
		return memberDAO.loginById(memberVO);
	}

	@Override
	public int updateMember(Map memberMap) throws DataAccessException {
		int stdNO = memberDAO.selectStdNOById((String)memberMap.get("id"));
		memberDAO.updateMember(memberMap);
		return stdNO;
	}

	@Override
	public MemberVO viewDetail(String id) throws DataAccessException {
		MemberVO memberVO = new MemberVO();
		memberVO = memberDAO.selectMemberById(id);
		return memberVO;
	}
	
}
