package com.example.exception;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 2238716360193763154L;
	
	public ResourceNotFoundException(){
		
	}
	public ResourceNotFoundException(String message){
		super(message);
	}
	public ResourceNotFoundException(String message, Throwable cause){
		
	}
}
