package com.dogfriend.swp.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dogfriend.swp.domain.Board;
import com.dogfriend.swp.domain.Criteria;

@Repository
public class BoardDAOImp implements BoardDAO {

	@Inject
	private SqlSession session;
	
	private static final String NAMESPACE = "BoardMapper";
	private static final String CREATE = NAMESPACE + ".create";
	private static final String READ = NAMESPACE + ".read";
	private static final String UPDATE = NAMESPACE + ".update";
	private static final String DELETE = NAMESPACE + ".delete";
	private static final String LIST = NAMESPACE + ".listAll";
	private static final String SELECT_PAGE = NAMESPACE + ".listPage";
	private static final String SELECT_CRITERIA = NAMESPACE + ".listCriteria";
	private static final String COUNT_PAGING = NAMESPACE + ".countPaging";
	private static final String GETMAXBNO = NAMESPACE + ".getMaxBno";
	private static final String UPDATE_REPLYCNT = NAMESPACE + ".updateReplycnt";
	private static final String PLUSVIEWCNT = NAMESPACE + ".plusViewcnt";
	
	
	

	

	@Override
	public void create(Board board) throws Exception {
		session.insert(CREATE, board);
	}

	@Override
	public Board read(Integer bno) throws Exception {
		return session.selectOne(READ, bno);
	}

	@Override
	public void update(Board board) throws Exception {
		session.update(UPDATE, board);

	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(DELETE, bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		return session.selectList(LIST);
	}

	@Override
	public List<Board> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page -1) * 10;
		
		return session.selectList(SELECT_PAGE, page);
	}
	
	@Override
	public List<Board> listCriteria(Criteria cri) throws Exception {
		
		return session.selectList(SELECT_CRITERIA, cri);
	}
	
	@Override
	public Integer getMaxBno() throws Exception {
		return session.selectOne(GETMAXBNO);
	}

	@Override
	public int countPagin(Criteria cri) throws Exception {
		return session.selectOne(COUNT_PAGING);
	}

	@Override
	public void updateReply(Integer bno, int amt) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("bno", bno);
		paramMap.put("amt", amt);
		session.update(UPDATE_REPLYCNT, paramMap);		
	}

	@Override
	public void plueViewcnt(Integer bno) {
		session.update(PLUSVIEWCNT, bno);
	}

}