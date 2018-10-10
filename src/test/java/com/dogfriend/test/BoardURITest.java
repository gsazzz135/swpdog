package com.dogfriend.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class BoardURITest {
	
	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void testURI2() throws Exception{
		int bno = 207;
		int perPageNum = 20;
		UriComponents uriComponents = null;
		for(int i =0; i<20; i++) {
			uriComponents = UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno",bno)
				.queryParam("perPageNum", perPageNum)
				.queryParam("keyword", "�? 강원?�� 값싼 빠삐�? !@#$%^&*")
				.build()
				.expand("board","read")
				.encode();
		}
		String uri = "/board/read?bno=" + bno + "&perPageNum=" + perPageNum;
		logger.info(uri);
		logger.info(uri, uriComponents.toString());
				
	}
}