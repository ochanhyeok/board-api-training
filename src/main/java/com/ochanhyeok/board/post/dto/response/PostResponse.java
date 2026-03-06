package com.ochanhyeok.board.post.dto.response;

import java.time.LocalDateTime;

import com.ochanhyeok.board.post.entity.Post;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostResponse {

	private Long id;
	private String title;
	private String content;
	private String nickname;
	private int viewCnt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static  PostResponse from(Post post) {
		return PostResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.nickname(post.getMember().getNickname())
			.viewCnt(post.getViewCount())
			.createdAt(post.getCreatedAt())
			.updatedAt(post.getUpdatedAt())
			.build();
	}
}
