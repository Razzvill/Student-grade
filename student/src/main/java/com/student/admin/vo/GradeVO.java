package com.student.admin.vo;

import org.springframework.stereotype.Component;

@Component("adminVO")
public class GradeVO {
	private int studentNO;
	private String name;
	private int korean;
	private int english;
	private int math;
	private int history;
	private int total;
	private double avrg;
	private int rank;
	
	public GradeVO() {
	}
	
	public int getStudentNO() {
		return studentNO;
	}
	public void setStudentNO(int studentNO) {
		this.studentNO = studentNO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKorean() {
		return korean;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getHistory() {
		return history;
	}
	public void setHistory(int history) {
		this.history = history;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getAvrg() {
		return avrg;
	}
	public void setAvrg(double avrg) {
		this.avrg = avrg;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}
