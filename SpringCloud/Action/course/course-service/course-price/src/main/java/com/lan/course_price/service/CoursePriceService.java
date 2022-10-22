package com.lan.course_price.service;

import java.util.List;

import com.lan.course_price.entity.CourseAndPrice;
import com.lan.course_price.entity.CoursePrice;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午3:24:15
 * @TODO
 * 
 */
public interface CoursePriceService {
	CoursePrice getCoursePrice(Integer id);

	List<CourseAndPrice> getCourseAndPrice();
}
