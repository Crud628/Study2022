package com.lan.course_price.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;

import com.lan.course_list.entity.Course;


/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午4:53:25
 * @TODO
 * 
 */
@Primary
@FeignClient(value = "course-list", fallback = CourseListClientHystrixClient.class)
public interface CourseListClient {

	@GetMapping("/courses")
	List<Course> courseList();
}
