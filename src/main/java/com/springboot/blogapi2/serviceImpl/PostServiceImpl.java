package com.springboot.blogapi2.serviceImpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blogapi2.entities.Post;
import com.springboot.blogapi2.exception.ResourceNotFoundException;
import com.springboot.blogapi2.payload.PostDto;
import com.springboot.blogapi2.payload.PostResponse;
import com.springboot.blogapi2.repository.PostRepository;
import com.springboot.blogapi2.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private static final Logger logger= LoggerFactory.getLogger(PostServiceImpl.class);
	
	 @Autowired
	 private PostRepository postRepository;
	 
	@Override
	public PostDto createPost(PostDto postDto) {
		
		
		Post post= mapToEntity(postDto);
		 Post newPost = postRepository.save(post);
		/*
		 * Post post= new Post();
		 *  post.setTitle(postDto.getTitle());
		 * post.setDescription(postDto.getDescription());
		 * post.setContent(postDto.getContent()); Post newPost =
		 * postRepository.save(post);
		 */
		
		 PostDto postRespose= mapToDto(newPost);
		
		 
		 /*
			 * PostDto postRespose= new PostDto(); postRespose.setId(newPost.getId());
			 * postRespose.setTitle(newPost.getTitle());
			 * postRespose.setDescription(newPost.getDescription());
			 * postRespose.setContent(newPost.getContent());
			 */
		 
		 return postRespose;	 
		 
		 
		 
		 
	}

//	@Override
//	public PostResponse gettAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
//		
//		Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
//			Sort.by(sortBy).descending()
//			;
//		Pageable pageable= PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//		
//		
//		Page<Post> posts = postRepository.findAll(pageable);
//		//get content for page 
//		List<Post>listOfPost= posts.getContent();
//		
//		 List<PostDto>content= listOfPost.stream().map(post-> mapToDto(post)).collect(Collectors.toList());
//		PostResponse postResponse=new PostResponse();
//		postResponse.setContent(content);
//		postResponse.setPageNo(posts.getNumber());
//		postResponse.setPageSize(posts.getSize());
//		postResponse.setTotalElements(posts.getTotalElements());
//		postResponse.setTotalPages(posts.getTotalPages());
//		postResponse.setLast(posts.isLast());
//		
//		return postResponse;
//			
//				
//	}
	

	@Override
	public PostDto getPostById(int id) {
		Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post"," id", id));
	return mapToDto(post);	
	}
	
	
	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post"," id", postId));

		logger.info(":::::: inside updatePost service ::: " +postId);
		
		logger.info(":::::: inside updatePost service ::: " +post);
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post UpdatedPost = postRepository.save(post);
		logger.info(":::::: inside updatePost controller ::: " +UpdatedPost);
		
		return mapToDto(UpdatedPost);
	}
	

	@Override
	public void deletePostById(int id) {
		
		Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post"," id", id));

		postRepository.delete(post);
	}


	
	
	
	
	
	
	
	
	//entity to Dto
	private PostDto mapToDto(Post post){
	PostDto postDto = new PostDto();
	postDto.setId(post.getId());
	postDto.setTitle(post.getTitle());
	postDto.setDescription(post.getDescription());
	postDto.setContent(post.getContent());
	return postDto;
	}
	
	//dto to entity
	private Post mapToEntity(PostDto postDto) {
		
		Post post= new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		 return post;
	}

	@Override
	public List<Post> gettAllPosts() {
		// TODO Auto-generated method stub
		
		logger.info("::::::::::::: inside gettAllPosts :::"+ postRepository.findAll());
		return postRepository.findAll();
	}

	
	
}
