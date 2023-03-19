package com.lan.wiki.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * Get, POST, Put,Delete
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
