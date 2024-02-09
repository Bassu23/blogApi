package com.springboot.blogapi2.service;

import java.util.List;

import com.springboot.blogapi2.entities.Post;
import com.springboot.blogapi2.payload.PostDto;
import com.springboot.blogapi2.payload.PostResponse;

public interface PostService {
	public PostDto createPost(PostDto postDto);

//	PostResponse gettAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDto getPostById(int id);

	PostDto updatePost(PostDto postDto, int id);

	void deletePostById(int id);

	public List<Post> gettAllPosts();

}
