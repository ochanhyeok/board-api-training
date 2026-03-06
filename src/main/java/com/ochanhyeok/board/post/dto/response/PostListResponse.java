package com.ochanhyeok.board.post.dto.response;

import java.time.LocalDateTime;

import com.ochanhyeok.board.post.entity.Post;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostListResponse {

	private Long id;
	private String title;
	private String nickname;
	private int viewCnt;
	private LocalDateTime createdAt;

	public static PostListResponse from(Post post) {
		return PostListResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.nickname(post.getMember().getNickname())
			.viewCnt(post.getViewCount())
			.createdAt(post.getCreatedAt())
			.build();
	}
}
