package com.edkim.mss.model;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
	private T result;
	private int status;
	private String message;
	
	public ApiResponse(T result) {
		this.result = result;
		this.status = HttpStatus.OK.value();
		this.message = "success";
	}
	
	public ApiResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
