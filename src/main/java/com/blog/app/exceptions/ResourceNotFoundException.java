package com.blog.app.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	
	String resourceName;
	String fileName;
    Integer filedValue;
	public ResourceNotFoundException(String resourceName, String fileName, Integer filedValue) {
		super(String.format("%s not found with %s : %s ", resourceName,fileName,filedValue));
		this.resourceName = resourceName;
		this.fileName = fileName;
		this.filedValue = filedValue;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getFiledValue() {
		return filedValue;
	}
	public void setFiledValue(Integer filedValue) {
		this.filedValue = filedValue;
	}
	
	
	
    
    
}
