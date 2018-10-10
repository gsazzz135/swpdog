package com.dogfriend.swp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.dogfriend.swp.domain.Board;
import com.dogfriend.swp.domain.Criteria;
import com.dogfriend.swp.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;

	@Override
	public void regist(Board board) throws Exception {
		dao.create(board);
	}
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public Board read(Integer bno) throws Exception {
		dao.plueViewcnt(bno);
		return dao.read(bno);
	}

	@Override
	public void modify(Board board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<Board> listAll() throws Exception {
		return dao.listAll();
	}
	
	@Override
	public List<Board> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return 0;
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return dao.countPagin(cri);
	}	
	
	
}
