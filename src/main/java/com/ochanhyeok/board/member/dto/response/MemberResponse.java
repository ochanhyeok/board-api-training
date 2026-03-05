package com.ochanhyeok.board.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponse {

	private Long id;
	private String email;
	private String nickname;
}
