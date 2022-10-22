package com.lan.course_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lan.course_list.entity.Course;
import com.lan.course_list.service.CourseListService;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午2:59:20
 * @TODO
 * 
 */
@RestController
public class CourseListController {

	@Autowired
	private CourseListService courseListService;
	
	@GetMapping("/courses")
	public List<Course> courseList() {
		return courseListService.getCurseList();
	}
}
