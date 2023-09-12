package com.student.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.student.admin.dao.AdminDAO;
import com.student.admin.vo.GradeVO;
import com.student.admin.vo.TotalVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDAO adminDAO;

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = adminDAO.selectAllMemberList();
		return membersList;
	}
	
	@Override
	public List listGrades() throws DataAccessException {
		List adminList = null;
		adminList = adminDAO.selectAllGradeList();
		return adminList;
	}

	@Override
	public TotalVO selectTotalList() throws DataAccessException {
		TotalVO totalVO = adminDAO.selectTotalList();
		return totalVO;
	}

	@Override
	public int updateGrade(GradeVO adminVO) throws DataAccessException {
		return adminDAO.updateGrade(adminVO);
	}

	@Override
	public int removeGrade(int studentNO) throws DataAccessException {
		return adminDAO.deleteGrade(studentNO);
	}

	@Override
	public GradeVO modGrade(int studentNO) throws DataAccessException {
		GradeVO adminVO = adminDAO.selectByStdNO(studentNO);
		return adminVO;
	}
	
}
