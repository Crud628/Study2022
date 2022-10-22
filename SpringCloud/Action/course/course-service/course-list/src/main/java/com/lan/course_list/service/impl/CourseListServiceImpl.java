package com.lan.course_list.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lan.course_list.dao.CourseMapper;
import com.lan.course_list.entity.Course;
import com.lan.course_list.service.CourseListService;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午3:01:22
 * @TODO 课程服务实现类
 * 
 */
@Service
public class CourseListServiceImpl implements CourseListService{

	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public List<Course> getCurseList() {
		
		return courseMapper.findValidCourse();
	}

}
