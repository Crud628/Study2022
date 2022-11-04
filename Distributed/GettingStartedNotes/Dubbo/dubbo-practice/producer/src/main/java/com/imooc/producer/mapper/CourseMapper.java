package com.imooc.producer.mapper;

import com.imooc.producer.entity.Course;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 描述：     Mapper类
 */
@Mapper
@Repository
public interface CourseMapper {

    @Select("SELECT * FROM course WHERE valid = 1")
    List<Course> findValidCourses();
}
