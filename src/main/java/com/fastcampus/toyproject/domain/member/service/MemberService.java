package com.fastcampus.toyproject.domain.member.service;

import com.fastcampus.toyproject.domain.member.dto.MemberRequestDTO;
import com.fastcampus.toyproject.domain.member.entity.Member;
import com.fastcampus.toyproject.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member insertMember(MemberRequestDTO memberRequestDTO) {
        Member member = Member.builder().nickName(memberRequestDTO.getNickName()).build();
        return memberRepository.save(member);
    }

}
