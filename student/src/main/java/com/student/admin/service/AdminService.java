package com.student.admin.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.student.admin.vo.GradeVO;
import com.student.admin.vo.TotalVO;

public interface AdminService {
	public List listMembers() throws DataAccessException;
	public List listGrades() throws DataAccessException;
	public TotalVO selectTotalList() throws DataAccessException;
	public int updateGrade(GradeVO adminVO) throws DataAccessException;
	public int removeGrade(int studentNO) throws DataAccessException;
	public GradeVO modGrade(int studentNO) throws DataAccessException;
}
