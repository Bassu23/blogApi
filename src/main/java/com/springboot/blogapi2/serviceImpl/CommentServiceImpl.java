package com.springboot.blogapi2.serviceImpl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blogapi2.entities.Comment;
import com.springboot.blogapi2.entities.Post;
import com.springboot.blogapi2.exception.BlogApiException;
import com.springboot.blogapi2.exception.ResourceNotFoundException;
import com.springboot.blogapi2.payload.CommentDto;
import com.springboot.blogapi2.repository.CommentRepository;
import com.springboot.blogapi2.repository.PostRepository;
import com.springboot.blogapi2.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;



	@Override
	public CommentDto createComment(int postId, CommentDto commentDto) {
		Comment comment =maptoEntity(commentDto);
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
	comment.setPost(post);
	
	Comment newComment  = commentRepository.save(comment);
		return mapToDto(newComment)
				;
	}
	
	
	//get all comments
	
	@Override
	public List<CommentDto> getCommentsByPostId(int postId) {
			List<Comment> comments = commentRepository.findByPostId(postId);
			
			
			
		return comments.stream().map(comment-> mapToDto(comment)).collect(Collectors.toList());
 
	}


	@Override
	public CommentDto getCommentById(int postId, int commentId) {
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));
	if (!(comment.getPost().getId()==(post.getId()))) {
		throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment doesnt belong to the post");
		
	}
		return mapToDto(comment);
	
	}

	@Override
	public CommentDto updateCommentbyId(int postId, int commentId, CommentDto commentDto) {
	
		
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));
		if (!(comment.getPost().getId()==(post.getId()))) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "comment doesnt belong to the post");
			
		}
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		 Comment updatedComment = commentRepository.save(comment);
		
		return mapToDto(updatedComment);
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());

		return commentDto;

	}

	private Comment maptoEntity(CommentDto commentDto) {

		Comment comment = new Comment();

		comment.setId(commentDto.getId());
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());

		return comment;

	}






























}
