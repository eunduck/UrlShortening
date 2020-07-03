package com.edkim.mss.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.edkim.mss.model.UrlInfo;
import com.edkim.mss.service.UrlShorteningService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

	@Autowired
	UrlShorteningService service;

	Map<String, UrlInfo> originUrls = new HashMap<String, UrlInfo>();
	Map<String, String> shortUrls = new HashMap<String, String>();
	
	@Test
	public void getShortUrl() {
		String shortUrl = null;
		String originUrl = "https://en.wikipedia.org/wiki/URL_shortening";
		shortUrl = service.add(originUrls, shortUrls, originUrl);
		
		// 결과 
		assertNotNull("is null", shortUrl);
		// character 8자리 제한
		assertTrue(shortUrl.length() <= 8);
		// 동일한 shorten url 의 값 return 비교 
		String secondShortUrl = service.add(originUrls, shortUrls, originUrl);
		assertEquals(shortUrl, secondShortUrl);
		// 동일 url 요청 수
		UrlInfo info = originUrls.get(originUrl);
		assertEquals(2, info.getCount());
	}
}
