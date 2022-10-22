package com.lan.course_price.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lan.course_price.entity.CoursePrice;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午3:40:15
 * @TODO
 * 
 */
@Mapper
@Repository
public interface CoursePriceMapper {
	@Select("select * from course_price where course_id = #{courseId}")
	CoursePrice findCourcePrice(int courseId);
}
