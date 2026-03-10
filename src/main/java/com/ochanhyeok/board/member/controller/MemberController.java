package com.ochanhyeok.board.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ochanhyeok.board.global.response.ApiResponse;
import com.ochanhyeok.board.member.dto.request.MemberLoginRequest;
import com.ochanhyeok.board.member.dto.request.MemberSignUpRequest;
import com.ochanhyeok.board.member.dto.response.MemberResponse;
import com.ochanhyeok.board.member.entity.Member;
import com.ochanhyeok.board.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/login")
	public ApiResponse<MemberResponse> login(@Valid @RequestBody MemberLoginRequest request , HttpSession session) {
		Member loginMember = memberService.login(request.email(), request.password());
		MemberResponse memberResponse = MemberResponse.from(loginMember);
		session.setAttribute("loginMember", loginMember);
		return ApiResponse.ok(memberResponse);
	}

	@PostMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@PostMapping("/members")
	public ApiResponse<MemberResponse> signUp(@Valid @RequestBody MemberSignUpRequest request) {
		Member member = memberService.signUp(request);
		MemberResponse response = MemberResponse.from(member);
		return ApiResponse.ok(response);
	}
}
