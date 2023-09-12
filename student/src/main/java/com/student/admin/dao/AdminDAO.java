package com.student.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.student.admin.vo.GradeVO;
import com.student.admin.vo.TotalVO;

@Mapper
@Repository("adminDAO")
public interface AdminDAO {
	public List selectAllMemberList() throws DataAccessException;
	public List selectAllGradeList() throws DataAccessException;
	public TotalVO selectTotalList() throws DataAccessException;
	public int updateGrade(GradeVO adminVO) throws DataAccessException;
	public int deleteGrade(int studentNO) throws DataAccessException;
	public GradeVO selectByStdNO(int studentNO) throws DataAccessException;
}
