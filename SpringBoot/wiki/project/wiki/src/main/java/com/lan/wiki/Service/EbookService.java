package com.lan.wiki.Service;

import com.lan.wiki.domain.Ebook;
import com.lan.wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;


    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }
}
