package com.edkim.mss.model;

public class UrlInfo {
	private String shortenUrl;
	private int count;
	
	public void setShortenUrl(String url) {
		this.shortenUrl = url;
	}
	public void setCount(int c) {
		this.count = c;
	}
	public String getShortenUrl() {
		return this.shortenUrl;
	}
	public int  getCount() {
		return this.count;
	}
	
	public UrlInfo(String url, int i) {
		this.shortenUrl = url;
		this.count = i;
	}
}
