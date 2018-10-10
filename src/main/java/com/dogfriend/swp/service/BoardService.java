package com.dogfriend.swp.service;

import java.util.List;

import com.dogfriend.swp.domain.Board;
import com.dogfriend.swp.domain.Criteria;

public interface BoardService {
	void regist(Board board) throws Exception;

	Board read(Integer bno) throws Exception;

	void modify(Board board) throws Exception;

	void remove(Integer bno) throws Exception;

	List<Board> listAll() throws Exception;
	
	List<Board> listCriteria(Criteria cri) throws Exception;

	int listCountCriteria(Criteria cri) throws Exception;

	int countPaging(Criteria cri) throws Exception;

}
