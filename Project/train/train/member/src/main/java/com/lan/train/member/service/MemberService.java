package com.lan.train.member.service;

import com.lan.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return memberMapper.count();
    }
}
