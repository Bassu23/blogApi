package com.springboot.blogapi2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blogapi2.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
