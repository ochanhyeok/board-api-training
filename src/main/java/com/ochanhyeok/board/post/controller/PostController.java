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

import com.ochanhyeok.board.global.response.ApiResponse;
import com.ochanhyeok.board.member.entity.Member;
import com.ochanhyeok.board.post.dto.request.PostCreateRequest;
import com.ochanhyeok.board.post.dto.request.PostUpdateRequest;
import com.ochanhyeok.board.post.dto.response.PostListResponse;
import com.ochanhyeok.board.post.dto.response.PostResponse;
import com.ochanhyeok.board.post.entity.Post;
import com.ochanhyeok.board.post.service.PostService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/posts")
	public ApiResponse<PostResponse> save(HttpSession session, @Valid @RequestBody PostCreateRequest request) {
		Member member = (Member)session.getAttribute("loginMember");
		if (member == null) {
			throw new RuntimeException("회원정보가 인증되지 않았습니다.");
		}

		Post post = postService.save(request, member);
		PostResponse response = PostResponse.from(post);

		return ApiResponse.ok(response);
	}

	@GetMapping("/posts")
	public ApiResponse<List<PostListResponse>> findAll() {
		List<Post> posts = postService.findAll();
		List<PostListResponse> responses = posts.stream()
			.map(PostListResponse::from)
			.toList();
		return ApiResponse.ok(responses);
	}

	@GetMapping("/posts/{id}")
	public ApiResponse<PostResponse> findOne(@PathVariable Long id) {
		Post post = postService.findOne(id);
		PostResponse response = PostResponse.from(post);
		return ApiResponse.ok(response);
	}

	@PutMapping("/posts/{id}")
	public ApiResponse<PostResponse> update(HttpSession session, @PathVariable Long id, @Valid @RequestBody PostUpdateRequest request) {
		Member member = (Member)session.getAttribute("loginMember");
		if (member == null) {
			throw new RuntimeException("회원정보가 인증되지 않았습니다.");
		}

		String title = request.title();
		String content = request.content();
		Post updatePost = postService.update(id, member.getId(), title, content);

		PostResponse response = PostResponse.from(updatePost);

		return ApiResponse.ok(response);
	}

	@DeleteMapping("/posts/{id}")
	public ApiResponse<Void> delete(HttpSession session, @PathVariable Long id) {
		Member member = (Member)session.getAttribute("loginMember");
		if (member == null) {
			throw new RuntimeException("회원정보가 인증되지 않았습니다.");
		}

		postService.delete(id, member.getId());
		return ApiResponse.ok();
	}
}
