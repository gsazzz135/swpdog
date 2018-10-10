package com.dogfriend.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.dogfriend.swp.domain.Board;
import com.dogfriend.swp.domain.Criteria;
import com.dogfriend.swp.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class BoardDAOTest {
	@Inject
	private BoardDAO dao;
	
	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	private static int maxbno = 0;	//최�?
	private static boolean flag = false;

	@Before
	public void getMaxBno() throws Exception {
		if (!flag) {
			maxbno = dao.getMaxBno();
			System.out.println("Get maxbno = " + maxbno);
			flag = true;
		}
	}
	
	@Test
	public void testURI() throws Exception{
		int bno = 207;
		int perPageNum = 20;
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno",bno)
				.queryParam("perPageNum", perPageNum)
				.build()
				.expand("board","read")
				.encode();
		
		String uri = "/board/read?bno=" + bno + "&perPageNum=" + perPageNum;
		logger.info(uri);
		logger.info(uri, uriComponents.toString());
				
	}
	
	@Test
	public void testURI2() throws Exception{
		int bno = 207;
		int perPageNum = 20;
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno",bno)
				.queryParam("perPageNum", perPageNum)
				.queryParam("keyword", "�? 강원?�� 값싼 빠삐�? !@#$%^&*")
				.build()
				.expand("board","read")
				.encode();
		
		String uri = "/board/read?bno=" + bno + "&perPageNum=" + perPageNum;
		logger.info(uri);
		logger.info(uri, uriComponents.toString());
				
	}
	
	@Test
	public void testCreate() throws Exception {
		Board board = new Board();
		board.setTitle("add new article");
		board.setContent("add new article");
		board.setWriter("gosang");
		dao.create(board);
	}

	@Test
	public void testRead() throws Exception {
		logger.info(dao.read(1).toString());
	}

	@Test
	public void testUpdate() throws Exception {
		Board board = new Board();
		board.setBno(maxbno);
		board.setTitle("amend article");
		board.setContent("amend article");
		dao.update(board);
	}

	@After
	public void testDelete() throws Exception {
		dao.delete(maxbno);
	}
	
	@Test
	public void testListPage() throws Exception {
		
		List<Board> list = dao.listPage(3);
		assertEquals(10, list.size());
	}
	
	@Test
	public void testListCriteria() throws Exception {
		
		Criteria criteria = new Criteria();
		criteria.setPage(2);
		criteria.setPerPageNum(20);
		
		List<Board> list = dao.listCriteria(criteria);
		assertEquals(criteria.getPerPageNum(), list.size());
	}

}
