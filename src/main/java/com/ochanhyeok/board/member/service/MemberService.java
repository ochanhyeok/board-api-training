package com.ochanhyeok.board.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ochanhyeok.board.member.entity.Member;
import com.ochanhyeok.board.member.repository.MemberRepository;

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
		if (memberRepository.existsByEmail(member.getEmail())) {
			throw new RuntimeException("이미 존재하는 이메일입니다.");
		}
		return memberRepository.save(member);
	}

	/**
	 * 로그인 처리
	 */
	@Transactional(readOnly = true)
	public Member login(String email, String password) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

		if (!password.equals(member.getPassword())) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}

		return member;
	}
}
