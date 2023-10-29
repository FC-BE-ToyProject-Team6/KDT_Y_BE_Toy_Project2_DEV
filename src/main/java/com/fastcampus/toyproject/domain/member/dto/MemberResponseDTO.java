package com.fastcampus.toyproject.domain.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDTO {

    private Long memberId;
    private String nickName;
}
