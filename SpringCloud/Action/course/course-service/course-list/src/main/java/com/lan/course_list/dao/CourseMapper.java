package com.lan.course_list.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.lan.course_list.entity.Course;

/**
 * @author Keason
 * @version 创建时间：2022年10月22日 下午3:05:20
 * @TODO 课程Mapper类
 * 
 */
@Mapper
@Repository
public interface CourseMapper {

	@Select("SELECT * FROM course WHERE valid = 1")
	List<Course> findValidCourse();
}
