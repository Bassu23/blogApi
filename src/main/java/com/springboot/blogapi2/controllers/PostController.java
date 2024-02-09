package com.springboot.blogapi2.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapi2.entities.Post;
import com.springboot.blogapi2.payload.PostDto;
import com.springboot.blogapi2.payload.PostResponse;
import com.springboot.blogapi2.service.PostService;
import com.springboot.blogapi2.serviceImpl.PostServiceImpl;
import com.springboot.blogapi2.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private static final Logger logger= LoggerFactory.getLogger(PostServiceImpl.class);
	
	@Autowired
	private PostService postServices;

	@PostMapping("/")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<>(postServices.createPost(postDto), HttpStatus.CREATED);

	}

	@GetMapping("/posts")
	public List<Post> getAllPosts() {
		return postServices.gettAllPosts();
	}
	
	@GetMapping()
	public PostResponse getAllPost(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAUlT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAUlT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortby", defaultValue = AppConstants.DEFAUlT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAUlT_SORT_DIRECTION, required = false) String sortDir

	) {
		return postServices.gettAllPost(pageNo, pageSize, sortBy, sortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int id) {
		return ResponseEntity.ok(postServices.getPostById(id));
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int postId) {
		PostDto postResponse = postServices.updatePost(postDto, postId);
		
		logger.info(":::::: inside updatePost controller ::: " +postId);
		logger.info(":::::: inside updatePost controller ::: " +postResponse);
		logger.info(":::::: inside updatePost controller ::: " +postDto);
		
		
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable int id) {
		postServices.deletePostById(id);
		return new ResponseEntity<>("post entity deleted successfullu", HttpStatus.OK);

	}
}
