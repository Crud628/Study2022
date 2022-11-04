package com.imooc.consumer.dao;

import com.imooc.consumer.entity.CoursePrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 描述：     Mapper类
 */
@Mapper
@Repository
public interface CoursePriceMapper {

    @Select("SELECT * FROM course_price WHERE course_id = #{courseId}")
    CoursePrice findCoursePrices(Integer courseId);
}
