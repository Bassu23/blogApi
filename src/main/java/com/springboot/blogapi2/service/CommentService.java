package com.springboot.blogapi2.service;

import java.util.List;

import com.springboot.blogapi2.payload.CommentDto;

public interface CommentService {
	CommentDto createComment( int postId, CommentDto commentDto);
	List<CommentDto>getCommentsByPostId(int postId);

	CommentDto getCommentById(int postId, int commentId);
	CommentDto updateCommentbyId(int postId,int commentId, CommentDto commentDto  );
	
	
}
