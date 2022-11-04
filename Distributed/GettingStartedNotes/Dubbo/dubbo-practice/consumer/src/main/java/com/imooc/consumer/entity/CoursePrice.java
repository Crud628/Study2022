package com.imooc.consumer.entity;


import java.io.Serializable;

/**
 * 描述：     CoursePrice实体类
 */
public class CoursePrice implements Serializable {

    Integer id;
    Integer courseId;
    Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CoursePrice{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", price=" + price +
                '}';
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
