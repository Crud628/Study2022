package com.lan.wiki.controller;

import com.lan.wiki.Service.EbookService;
import com.lan.wiki.domain.Ebook;
import com.lan.wiki.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResponse list() {
        CommonResponse<List<Ebook>> response = new CommonResponse<>();
        List<Ebook> list = ebookService.list();
        response.setContent(list);
        return response;
    }
}
