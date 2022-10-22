package com.lan.course_list.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lan.course_list.entity.Course;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午2:59:56
 * @TODO 课程列表服务
 * 
 */
public interface CourseListService {
	
	List<Course> getCurseList();
}
