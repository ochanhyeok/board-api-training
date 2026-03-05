package com.ochanhyeok.board.member.dto.request;

public record MemberLoginRequest(
	String email,
	String password
) {}
