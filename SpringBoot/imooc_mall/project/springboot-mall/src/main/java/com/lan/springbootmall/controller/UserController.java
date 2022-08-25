package com.lan.springbootmall.controller;

import com.lan.springbootmall.common.ApiRestResponse;
import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.exception.MallExceptionEnum;
import com.lan.springbootmall.model.pojo.User;
import com.lan.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Keason
 * @Description: 用户控制器
 * @date 2022/8/24 23:07
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 数据测试
     * @return 虚假数据信息
     */
    @GetMapping("/test")
    @ResponseBody
    public User personalPage() {
        return userService.getUser();
    }

    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam("username") String userName, @RequestParam("password")String password) throws MallException {
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_TOO_SHORT);
        }

        userService.register(userName, password);
        return new ApiRestResponse();
    }

}
