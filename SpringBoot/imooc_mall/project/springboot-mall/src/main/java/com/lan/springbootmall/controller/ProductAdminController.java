package com.lan.springbootmall.controller;

import com.lan.springbootmall.common.ApiRestResponse;
import com.lan.springbootmall.common.Constants;
import com.lan.springbootmall.exception.MallException;
import com.lan.springbootmall.exception.MallExceptionEnum;
import com.lan.springbootmall.model.pojo.Product;
import com.lan.springbootmall.model.request.AddProductReq;
import com.lan.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * 
 * @author Keason
 * @Description: 商品管理员
 * @date 2022/8/30 20:15
 */
@Controller
public class ProductAdminController {

    @Autowired
    ProductService productService;

    /**
     *
     * @param addProductReq
     * @return
     */
    @PostMapping("admin/product/add")
    @ResponseBody
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq) throws MallException {
        productService.add(addProductReq);
        return ApiRestResponse.success();
    }

    /**
     * 文件上传
     * @param httpServletRequest request
     * @param file 文件
     * @return
     * @throws MallException
     */
    @PostMapping("/admin/product/file")
    public ApiRestResponse upload(HttpServletRequest httpServletRequest, @RequestParam("file")MultipartFile file) throws MallException {
        String filename = file.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));
        // 生成文件名称UUID
        UUID uuid = UUID.randomUUID();
        String newFileName =  uuid.toString() + suffixName;
        File fileDirectory = new File(Constants.FILE_UPLOAD_DIR);
        File destFile = new File(Constants.FILE_UPLOAD_DIR + newFileName);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()) {
                throw new MallException(MallExceptionEnum.MKDIR_FAILED);
            }
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return ApiRestResponse.success(getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/images/" + newFileName);
        } catch (URISyntaxException e) {
            return ApiRestResponse.error(MallExceptionEnum.UPLOAD_FAILED);
        }
    }
    private URI getHost(URI uri) {
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }
}