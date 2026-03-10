package com.ochanhyeok.board.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ochanhyeok.board.global.error.BusinessException;
import com.ochanhyeok.board.global.error.ErrorCode;
import com.ochanhyeok.board.member.dto.request.MemberSignUpRequest;
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
	public Member signUp(MemberSignUpRequest request) {
		if (memberRepository.existsByEmail(request.email())) {
			throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
		}
		Member member = Member.builder()
			.email(request.email())
			.password(request.password())
			.nickname(request.nickname())
			.build();
		return memberRepository.save(member);
	}

	/**
	 * 로그인 처리
	 */
	@Transactional(readOnly = true)
	public Member login(String email, String password) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

		if (!password.equals(member.getPassword())) {
			throw new BusinessException(ErrorCode.INVALID_PASSWORD);
		}

		return member;
	}
}
