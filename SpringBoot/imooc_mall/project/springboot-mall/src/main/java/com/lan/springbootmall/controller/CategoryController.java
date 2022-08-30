package com.lan.springbootmall.controller;

import com.github.pagehelper.PageInfo;
import com.lan.springbootmall.common.ApiRestResponse;
import com.lan.springbootmall.common.Constants;
import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.exception.MallExceptionEnum;
import com.lan.springbootmall.model.pojo.Category;
import com.lan.springbootmall.model.pojo.User;
import com.lan.springbootmall.model.request.AddCategoryReq;
import com.lan.springbootmall.model.request.UpdateCategoryReq;
import com.lan.springbootmall.model.vo.CategoryVO;
import com.lan.springbootmall.service.CategoryService;
import com.lan.springbootmall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Keason
 * @Description: 目录
 * @date 2022/8/29 21:42
 */
@Controller
public class CategoryController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    /**
     * 添加品类
     *
     * @param session session
     * @param addCategoryReq 简单品类对象
     * @return 统一返回对象
     */
    @ApiOperation("后台添加目录")
    @PostMapping("admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq addCategoryReq) throws MallException {
        // 数据为空检查
        if (ObjectUtils.isEmpty(addCategoryReq.getName())
        || ObjectUtils.isEmpty(addCategoryReq.getType())
                || ObjectUtils.isEmpty(addCategoryReq.getParentId())
        || ObjectUtils.isEmpty(addCategoryReq.getOrderNumber())) {
            return ApiRestResponse.error(MallExceptionEnum.PARA_NOT_NULL);
        }

        // 用户登录检查
        User currentUser = (User)session.getAttribute(Constants.SESSION_KEY_MALL_USER);
        if (ObjectUtils.isEmpty(currentUser)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }

        // 管理员权限
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (adminRole) {
            categoryService.add(addCategoryReq);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }

    @ApiOperation("后台更新目录")
    @PostMapping("admin/category/update")
    @ResponseBody
    public ApiRestResponse updateCategory(@Valid @RequestBody UpdateCategoryReq updateCategoryReq,
                                          HttpSession session) throws MallException {
        User currentUser = (User) session.getAttribute(Constants.SESSION_KEY_MALL_USER);
        if (currentUser == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        //校验是否是管理员
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (adminRole) {
            //是管理员，执行操作
            Category category = new Category();
            BeanUtils.copyProperties(updateCategoryReq, category);
            categoryService.update(category);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }

    /**
     * 后台目录删除
     * @param id
     * @return
     */
    @ApiOperation("后台目录删除")
    @PostMapping("admin/category/delete")
    @ResponseBody
    public ApiRestResponse deleteCategory(@RequestParam Integer id) throws MallException {
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    /**
     *
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("后台目录列表")
    @PostMapping("admin/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = categoryService.ListForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    /**
     * 前台目录列表
     * @return
     */
    @ApiOperation("前台目录列表")
    @PostMapping("category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForCustomer() {
        List<CategoryVO> categoryForCustomerList = categoryService.getCategoryForCustomerList();
        return ApiRestResponse.success(categoryForCustomerList);
    }
}
