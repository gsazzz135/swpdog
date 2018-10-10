package com.dogfriend.service.test;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dogfriend.swp.domain.Board;
import com.dogfriend.swp.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class BoardServiceTest {

	@Inject
	private BoardService service;

	private static Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);
	@Test
	public void testRead() throws Exception {
		Board board = service.read(150);
		logger.debug(board.toString());
		assertEquals("123 100009", board.getTitle());

	}
}
