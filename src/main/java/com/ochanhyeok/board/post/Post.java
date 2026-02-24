package com.ochanhyeok.board.post;

import java.time.LocalDateTime;

import com.ochanhyeok.board.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memnber_id", nullable = false)
	private Member member;

	@Column(nullable = false)
	private int viewCount = 0;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
