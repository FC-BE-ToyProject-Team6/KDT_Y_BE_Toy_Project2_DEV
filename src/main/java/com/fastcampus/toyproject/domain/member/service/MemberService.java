package com.fastcampus.toyproject.domain.member.service;

import com.fastcampus.toyproject.domain.member.entity.Member;
import com.fastcampus.toyproject.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void insertTestMember() {
        Member member = Member.builder().memberId(1L).nickName("김종훈").build();
        memberRepository.save(member);
    }

}
