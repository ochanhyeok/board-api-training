package com.ochanhyeok.board.member.dto.response;

import com.ochanhyeok.board.member.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponse {

	private Long id;
	private String email;
	private String nickname;

	public static MemberResponse from(Member member) {
		return MemberResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.nickname(member.getNickname())
			.build();
	}
}
