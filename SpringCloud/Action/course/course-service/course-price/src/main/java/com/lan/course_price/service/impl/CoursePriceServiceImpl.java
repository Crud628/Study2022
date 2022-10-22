package com.lan.course_price.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lan.course_list.entity.Course;
import com.lan.course_price.client.CourseListClient;
import com.lan.course_price.dao.CoursePriceMapper;
import com.lan.course_price.entity.CourseAndPrice;
import com.lan.course_price.entity.CoursePrice;
import com.lan.course_price.service.CoursePriceService;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午3:25:00
 * @TODO
 * 
 */
@Service
public class CoursePriceServiceImpl implements CoursePriceService{

	@Autowired
	CoursePriceMapper coursePriceMapper;
	
	@Autowired
	CourseListClient courseListClient;
	
	@Override
	public CoursePrice getCoursePrice(Integer id) {
		return coursePriceMapper.findCourcePrice(id);
	}

	@Override
	public List<CourseAndPrice> getCourseAndPrice() {
		// TODO Auto-generated method stub
		List<Course> courseList = courseListClient.courseList();
		List<CourseAndPrice> list = new ArrayList<CourseAndPrice>();
		for (int i = 0;i < courseList.size(); i++) {
			Course course = courseList.get(i);
			if (course != null) {
				CoursePrice coursePrice = getCoursePrice(course.getCourseId());
				CourseAndPrice courseAndPrice = new CourseAndPrice();
				if ( coursePrice == null) continue;
				courseAndPrice.setPrice(coursePrice.getPrice());
				courseAndPrice.setName(course.getCourseName());
				courseAndPrice.setCourseId(course.getCourseId());
				courseAndPrice.setId(course.getId());
				list.add(courseAndPrice);
			}
		}
		return list;
	}

}
