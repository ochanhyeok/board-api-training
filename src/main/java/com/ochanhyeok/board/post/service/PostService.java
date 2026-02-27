package com.ochanhyeok.board.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ochanhyeok.board.post.entity.Post;
import com.ochanhyeok.board.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	/**
	 * 게시글 작성
	 */
	@Transactional
	public Post save(Post post) {
		return postRepository.save(post);
	}

	/**
	 * 게시글 목록
	 */
	@Transactional(readOnly = true)
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	/**
	 * 게시글 상세
	 */
	@Transactional
	public Post findOne(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
		post.setViewCount(post.getViewCount() + 1);
		return post;
	}

	/**
	 * 게시글 수정
	 */
	@Transactional
	public Post update(Long id, Long memberId, String newTitle, String newContent) {
		Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
		if (!post.getMember().getId().equals(memberId)) {
			throw new RuntimeException("해당 게시글에 권한이 없습니다.");
		}

		post.setTitle(newTitle);
		post.setContent(newContent);
		return post;
	}

	/**
	 * 게시글 삭제
	 */
	@Transactional
	public void delete(Long id, Long memberId) {
		Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
		if (!post.getMember().getId().equals(memberId)) {
			throw new RuntimeException("해당 게시글에 권한이 없습니다.");
		}

		postRepository.deleteById(id);
	}
}
