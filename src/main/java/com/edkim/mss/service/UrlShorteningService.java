package com.edkim.mss.service;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.edkim.mss.model.UrlInfo;

@Service
public class UrlShorteningService {
	
	/**
	 * full url을 입력받아 줄여진 url을 return 한다.
	 * @param originUrls original URL을 키값으로 가진 Map 
	 * @param shortUrls shorten URL을 키값으로 가진 Map
	 * @param url original URL
	 * @return 
	 */
	public String add(Map<String, UrlInfo> originUrls, Map<String, String> shortUrls, String url) {
		if(originUrls.containsKey(url)) {
			UrlInfo info = originUrls.get(url);
			info.setCount(info.getCount()+1);
			return info.getShortenUrl();
		} else{
			String shUrl = toShort(shortUrls);
			UrlInfo urlInfo = new UrlInfo(shUrl, 1);
			originUrls.put(url, urlInfo);
			shortUrls.put(shUrl, url);
			return shUrl;
		}
	}
	
	/**
	 * shortening URL을 만들어준다.
	 * @param shortUrls shorten URL을 키값으로 가진 Map
	 * @return
	 */
	public String toShort(Map<String, String> shortUrls) {
		String result = "";
		while(true) {
			Random r = new Random();
			StringBuffer buf = new StringBuffer();
			for(int i = 0 ; i < 8 ; i++){
			    buf.append((char) ((int)r.nextInt(26) + (r.nextBoolean() ? 97 : 65)));
			}
			if(!shortUrls.containsKey(buf.toString())) {
				result = buf.toString();
				break;
			}
		}
		return result;
	}
}
