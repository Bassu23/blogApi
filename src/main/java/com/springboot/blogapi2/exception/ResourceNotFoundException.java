package com.springboot.blogapi2.exception;

public class ResourceNotFoundException extends RuntimeException{

	private String resourceName;
	private String fieldName;
	private int fielddValue;
	public ResourceNotFoundException(String resourceName, String fieldName, int fielddValue) {
		super(String.format("%s is not found with %s:%s", resourceName, fieldName,fielddValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fielddValue = fielddValue;
	}
	public String getResourceName() {
		return resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public int getFielddValue() {
		return fielddValue;
	}
	
	
}
