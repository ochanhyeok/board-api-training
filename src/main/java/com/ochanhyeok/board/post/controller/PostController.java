package com.ochanhyeok.board.post.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ochanhyeok.board.member.entity.Member;
import com.ochanhyeok.board.post.entity.Post;
import com.ochanhyeok.board.post.service.PostService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/posts")
	public Post save(HttpSession session, @RequestBody Post post) {
		Member member = (Member)session.getAttribute("loginMember");
		if (member == null) {
			throw new RuntimeException("회원정보가 인증되지 않았습니다.");
		}
		post.setMember(member);
		return postService.save(post);
	}

	@GetMapping("/posts")
	public List<Post> findAll() {
		return postService.findAll();
	}

	@GetMapping("/posts/{id}")
	public Post findOne(@PathVariable Long id) {
		return postService.findOne(id);
	}

	@PutMapping("/posts/{id}")
	public Post update(HttpSession session, @PathVariable Long id, @RequestBody Post post) {
		Member member = (Member)session.getAttribute("loginMember");
		if (member == null) {
			throw new RuntimeException("회원정보가 인증되지 않았습니다.");
		}

		String title = post.getTitle();
		String content = post.getContent();
		Post updatePost = postService.update(id, title, content);

		return updatePost;
	}

	@DeleteMapping("/posts/{id}")
	public void delete(HttpSession session, @PathVariable Long id) {
		Member member = (Member)session.getAttribute("loginMember");
		if (member == null) {
			throw new RuntimeException("회원정보가 인증되지 않았습니다.");
		}

		postService.delete(id);
	}
}
