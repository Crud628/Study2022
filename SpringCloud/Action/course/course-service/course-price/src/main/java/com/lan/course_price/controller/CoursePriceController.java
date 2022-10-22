package com.lan.course_price.controller;
/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午3:23:57
 * @TODO
 * 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lan.course_list.entity.Course;
import com.lan.course_price.client.CourseListClient;
import com.lan.course_price.entity.CourseAndPrice;
import com.lan.course_price.service.CoursePriceService;

@RestController
public class CoursePriceController {
	
	@Autowired
	CoursePriceService coursePriceService;
	
	@Autowired
	CourseListClient courseListClient;
	
	@GetMapping("/price")
	public Integer getCoursePrice(Integer courseId) {
		return coursePriceService.getCoursePrice(courseId).getPrice();
	}
	
	@GetMapping("/coursesInprice")
	public List<Course> getCourseListInprice(Integer courseId) {
		return courseListClient.courseList();
	}
	
	@GetMapping("/coursesAndPrice")
	public List<CourseAndPrice> getCourseAndPrice() {
		return coursePriceService.getCourseAndPrice();
	}
}
