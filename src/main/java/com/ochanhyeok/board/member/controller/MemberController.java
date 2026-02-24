package com.ochanhyeok.board.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ochanhyeok.board.member.entity.Member;
import com.ochanhyeok.board.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/login")
	public Member login(@RequestBody Member member, HttpSession session) {
		Member loginMember = memberService.login(member.getEmail(), member.getPassword());
		session.setAttribute("loginMember", loginMember);
		return loginMember;
	}

	@PostMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@PostMapping("/members")
	public Member save(@RequestBody Member member) {
		return memberService.save(member);
	}
}
