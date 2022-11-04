package com.imooc.producer.service.impl;

import com.imooc.producer.entity.Course;
import com.imooc.producer.mapper.CourseMapper;
import com.imooc.producer.service.CourseListService;
import java.util.List;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述：     课程列表服务实现类
 */
@Service(version = "${demo.service.version}")
public class CourseListServiceImpl implements CourseListService {

    @Autowired
    CourseMapper courseMapper;

    public List<Course> getCourseList() {
        return courseMapper.findValidCourses();
    }

}
