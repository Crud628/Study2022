package com.lan.course_price.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lan.course_list.entity.Course;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午5:35:21
 * @TODO
 * 
 */
@Component
public class CourseListClientHystrixClient implements CourseListClient{

	@Override
	public List<Course> courseList() {
		List<Course> defaultCourse = new ArrayList<>();
		Course course = new Course();
		course.setCourseId(999);
		course.setCourseName("短路");
		course.setId(999);
		defaultCourse.add(course);
		return defaultCourse;
	}

}
