package com.dogfriend.swp.persistence;

import java.util.List;

import com.dogfriend.swp.domain.Criteria;
import com.dogfriend.swp.domain.ReplyVO;

public interface ReplyDAO {
	
	void create(ReplyVO reply) throws Exception;
	
	void update(ReplyVO reply) throws Exception;
	
	void delete(Integer rno) throws Exception;
	
	List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;

	int getToalCount(Integer bno);

	//EEE
	ReplyVO readRno(Integer rno);
	
	int getBno(Integer rno) throws Exception;
}
