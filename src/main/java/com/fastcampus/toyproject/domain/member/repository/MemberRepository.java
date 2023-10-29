package com.fastcampus.toyproject.domain.member.repository;

import com.fastcampus.toyproject.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
