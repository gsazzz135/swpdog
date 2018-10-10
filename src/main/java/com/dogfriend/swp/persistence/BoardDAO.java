package com.dogfriend.swp.persistence;

import java.util.List;

import com.dogfriend.swp.domain.Board;
import com.dogfriend.swp.domain.Criteria;



public interface BoardDAO {
	void create(Board board) throws Exception;

	Board read(Integer bno) throws Exception;

	void update(Board board) throws Exception;

	void delete(Integer board) throws Exception;

	List<Board> listAll() throws Exception;
	
	List<Board> listPage(int page) throws Exception;
	
	List<Board> listCriteria(Criteria cri) throws Exception;
	
	int countPagin(Criteria cri) throws Exception;
	
	void updateReply(Integer bno, int amt) throws Exception;
	
	Integer getMaxBno() throws Exception;

	void plueViewcnt(Integer bno);
	
	
}
