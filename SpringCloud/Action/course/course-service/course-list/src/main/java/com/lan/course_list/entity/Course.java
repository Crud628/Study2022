package com.lan.course_list.entity;
/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午2:57:34
 * @TODO Course 实体类
 * 
 */
public class Course {
	Integer id;
	Integer courseId;
	String courseName;
	Integer valid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", courseId=" + courseId + ", courseName=" + courseName + ", valid=" + valid + "]";
	}
	
}
