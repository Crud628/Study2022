package com.lan.train.member.service;
import java.util.Date;

import com.lan.train.member.domain.Member;
import com.lan.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(String monile) {
        Member member = new Member();
        member.setMemberId(System.currentTimeMillis());
        member.setUsername("");
        member.setPassword("");
        member.setSalt(0);
        member.setCreateTime(new Date());
        member.setNickname("");
        memberMapper.insert(member);
        return member.getMemberId();
    }
}
