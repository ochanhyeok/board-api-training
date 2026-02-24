package com.ochanhyeok.board.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ochanhyeok.board.member.entity.Member;
import com.ochanhyeok.board.member.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	/**
	 * 회원가입
	 */
	@Transactional
	public Member save(Member member) {
		Member savedMember = memberRepository.save(member);
		return savedMember;
	}

	@Transactional
	public String login(String email, String password) {
		Member member = new Member();
		member.setEmail(email);
		member.setPassword(password);


	}
}
