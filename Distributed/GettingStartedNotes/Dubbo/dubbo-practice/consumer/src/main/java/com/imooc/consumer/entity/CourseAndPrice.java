package com.imooc.consumer.entity;

import java.io.Serializable;

/**
 * 描述：     CoursePrice实体类
 */
public class CourseAndPrice implements Serializable {

    Integer id;
    Integer courseId;
    String name;
    Integer price;

    @Override
    public String toString() {
        return "CourseAndPrice{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
