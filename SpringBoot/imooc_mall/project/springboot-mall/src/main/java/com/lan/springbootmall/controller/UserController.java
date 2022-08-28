package com.lan.springbootmall.controller;

import com.lan.springbootmall.common.ApiRestResponse;
import com.lan.springbootmall.common.Constants;
import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.exception.MallExceptionEnum;
import com.lan.springbootmall.model.pojo.User;
import com.lan.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

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

    /**
     * 注册
     * @param userName 账号
     * @param password 密码
     * @return 用户信息
     * @throws MallException 自定义异常
     * @throws NoSuchAlgorithmException MD5生成失败异常
     */
    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam("username") String userName, @RequestParam("password")String password) throws MallException, NoSuchAlgorithmException {
        // 用户账号密码格式检查
        CheckUser(userName, password);
        // 注册
        userService.register(userName, password);
        return new ApiRestResponse();
    }

    /**
     * 登录
     * @param userName 账号
     * @param password 密码
     * @return 用户信息
     * @throws MallException 自定义异常
     */
    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestParam("username") String userName, @RequestParam("password")String password, HttpSession session) throws MallException{
        // 用户账号密码格式检查
        CheckUser(userName, password);
        // 登录
        User user = userService.login(userName, password);
        // 保存用户信息时不保存密码
        user.setPassword(null);
        session.setAttribute(Constants.SESSION_KEY_MALL_USER, user);
        return ApiRestResponse.success(user);
    }

    /**
     * 用户账号密码格式检查
     * @param userName 账号
     * @param password 密码
     * @throws MallException 自定义异常
     */
    private void CheckUser(@RequestParam("username") String userName, @RequestParam("password") String password) throws MallException {
        ApiRestResponse apiRestResponse = null;
        if (StringUtils.isEmpty(userName)) {
            throw  new MallException(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            throw new MallException(MallExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < Constants.PASSWORD_MAX_LENGTH) {
            throw new MallException(MallExceptionEnum.PASSWORD_TOO_SHORT);
        }
    }

}
