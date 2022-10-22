package com.lan.course_price.entity;
/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午3:25:38
 * @TODO
 * 
 */
/**
 * @author Keason
 *
 */
/**
 * @author Keason
 *
 */
public class CoursePrice {
	Integer id;
	Integer courseId;
	Integer price;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "CoursePrice [id=" + id + ", courseId=" + courseId + ", price=" + price + "]";
	}

	
}
