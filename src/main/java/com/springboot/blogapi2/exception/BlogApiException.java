package com.springboot.blogapi2.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException{
 private HttpStatus status;
 private String message;
public BlogApiException(HttpStatus status, String message) {
	super(message);
	this.status = status;
	this.message = message;
}
public HttpStatus getStatus() {
	return status;
}
public String getMessage() {
	return message;
}

 
 
}
