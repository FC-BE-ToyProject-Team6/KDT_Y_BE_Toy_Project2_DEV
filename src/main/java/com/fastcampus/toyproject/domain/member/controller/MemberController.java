package com.fastcampus.toyproject.domain.member.controller;

import com.fastcampus.toyproject.common.dto.ResponseDTO;
import com.fastcampus.toyproject.domain.member.dto.MemberRequestDTO;
import com.fastcampus.toyproject.domain.member.dto.MemberResponseDTO;
import com.fastcampus.toyproject.domain.member.entity.Member;
import com.fastcampus.toyproject.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseDTO<MemberResponseDTO> insert(@RequestBody MemberRequestDTO memberRequestDTO) {
        Member savedMember = memberService.insertMember(memberRequestDTO);
        MemberResponseDTO responseData = new MemberResponseDTO(savedMember.getMemberId(),
            savedMember.getNickName());
        return ResponseDTO.ok("회원 등록 성공!", responseData);
    }


}

