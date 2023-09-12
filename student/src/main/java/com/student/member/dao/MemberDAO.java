package com.student.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.student.member.vo.MemberVO;

@Mapper
@Repository("memberDAO")
public interface MemberDAO {
	public int insertMember(Map memberMap) throws DataAccessException;
	public int deleteMember(String id) throws DataAccessException;
	public int updateMember(Map memberMap) throws DataAccessException;
	public int selectStdNOById(String id) throws DataAccessException;
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	public MemberVO selectMemberById(String id) throws DataAccessException;
}
