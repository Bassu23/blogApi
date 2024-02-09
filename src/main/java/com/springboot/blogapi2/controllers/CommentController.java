package com.springboot.blogapi2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapi2.payload.CommentDto;
import com.springboot.blogapi2.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable int postId, @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);

	}
	
	@GetMapping("/posts/{postId}/comments")
public List<CommentDto>getCommentsByPostId(@PathVariable int  postId){
	
	return commentService.getCommentsByPostId(postId);
}
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto>getCommentById(@PathVariable int postId, @PathVariable int commentId){
		CommentDto commentDto = commentService.getCommentById(postId, commentId);
		return new ResponseEntity<>(commentDto,HttpStatus.OK);
	}
	@PutMapping("posts/{postId}/Comment/{commentId}")
	public ResponseEntity<CommentDto>udpateComment(@PathVariable int postId,
													@PathVariable int commentId	,
													@RequestBody CommentDto commentDto
															){
		CommentDto updatedComment = commentService.updateCommentbyId(postId, commentId, commentDto);
		return new ResponseEntity<>	(updatedComment, HttpStatus.OK);}
}
