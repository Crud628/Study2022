package com.imooc.consumer.service;

import com.imooc.consumer.entity.CourseAndPrice;
import com.imooc.consumer.entity.CoursePrice;
import java.util.List;

/**
 * 描述：     课程价格服务
 */
public interface CoursePriceService {

    CoursePrice getCoursePrice(Integer courseId);

    List<CourseAndPrice> getCoursesAndPrice();
}
