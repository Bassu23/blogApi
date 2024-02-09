package com.springboot.blogapi2.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = " posts")
public class Post {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = " title", nullable = false)
	private String title;
	
	@Column(name = " description", nullable = false)
	private String description;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	private int postId;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment>comments= new HashSet<>();
	

	public Post() {
		super();
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

//	public Post(int id, String title, String description, String content) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.description = description;
//		this.content = content;
//	}

	public Post(int id, String title, String description, String content, int postId, Set<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.postId = postId;
		this.comments = comments;
	}

	public int getPostId() {
		return postId;
	}



	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content + "]";
	}
}
