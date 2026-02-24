package com.ochanhyeok.board.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ochanhyeok.board.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
