package com.student.admin.vo;

import org.springframework.stereotype.Component;

@Component("t_totalVO")
public class TotalVO {
	private int t_korean;
	private int t_english;
	private int t_math;
	private int t_history;
	private int t_total;
	private double t_avrg;
	private int t_count;
	
	public TotalVO() {
	}
	
	public int getT_korean() {
		return t_korean;
	}
	public void setT_korean(int t_korean) {
		this.t_korean = t_korean;
	}
	public int getT_english() {
		return t_english;
	}
	public void setT_english(int t_english) {
		this.t_english = t_english;
	}
	public int getT_math() {
		return t_math;
	}
	public void setT_math(int t_math) {
		this.t_math = t_math;
	}
	public int getT_history() {
		return t_history;
	}
	public void setT_history(int t_history) {
		this.t_history = t_history;
	}
	public int getT_total() {
		return t_total;
	}
	public void setT_total(int t_total) {
		this.t_total = t_total;
	}
	public double getT_avrg() {
		return t_avrg;
	}
	public void setT_avrg(double t_avrg) {
		this.t_avrg = t_avrg;
	}
	public int getT_count() {
		return t_count;
	}
	public void setT_count(int t_count) {
		this.t_count = t_count;
	}
}
