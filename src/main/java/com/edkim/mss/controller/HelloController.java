package com.edkim.mss.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edkim.mss.model.ApiResponse;
import com.edkim.mss.model.UrlInfo;
import com.edkim.mss.service.UrlShorteningService;

@RestController
public class HelloController {
	@Autowired
	UrlShorteningService service;

	Map<String, UrlInfo> originUrls = new HashMap<String, UrlInfo>();
	Map<String, String> shortUrls = new HashMap<String, String>();

	@PostMapping("/url")
	public ApiResponse<String> send(@RequestParam String url) {
		String validResult = valid(url);
		if(!StringUtils.isEmpty(validResult)) {
			return new ApiResponse<String>(HttpStatus.BAD_REQUEST.value(), validResult);
		} else { 
			String result = service.add(originUrls, shortUrls, url);
			return new ApiResponse<String>(result);
		}
	}
	
	@GetMapping("/all")
	public ApiResponse<Map<String, UrlInfo>> getall() throws IOException {
		return new ApiResponse<Map<String, UrlInfo>>(originUrls);
	}
	
	@GetMapping("/{url}")
	public ApiResponse<String> get(@PathVariable("url") String url, 
			HttpServletResponse httpServletResponse) throws IOException {
		ApiResponse<String> response = null;
		if(StringUtils.isEmpty(url)) {
			response = new ApiResponse<String>(HttpStatus.BAD_REQUEST.value(), "url값을 입력해주세요.");
		} else if(!shortUrls.containsKey(url)) {
			response = new ApiResponse<String>(HttpStatus.BAD_REQUEST.value(), "등록되지 않은 URL입니다.");
		} else {
	       httpServletResponse.sendRedirect(shortUrls.get(url));
		}
		return response;
	}
	
	// url 형식인지 검사
	private String valid(String userUrl) {
		if(StringUtils.isEmpty(userUrl)) {
			return "url값을 입력해주세요.";
		} 
		try {
		    URL url = new URL(userUrl);
		    URLConnection conn = url.openConnection();
		    conn.connect();
		} catch (MalformedURLException e) {
			return "URL 형식이 아닙니다.";
		} catch (IOException e) {
			return "연결할 수 없는 URL입니다.";
		}
		return null;
	}
}
