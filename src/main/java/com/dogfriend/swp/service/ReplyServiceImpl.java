package com.dogfriend.swp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dogfriend.swp.domain.Criteria;
import com.dogfriend.swp.domain.ReplyVO;
import com.dogfriend.swp.persistence.BoardDAO;
import com.dogfriend.swp.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Inject
	ReplyDAO replyDAO;
	
	@Inject
	BoardDAO boardDAO;
	
	@Transactional
	@Override
	public void register(ReplyVO reply) throws Exception {
		replyDAO.create(reply);
		boardDAO.updateReply(reply.getBno(), 1);
		
	}

	@Override
	public void modify(ReplyVO reply) throws Exception {
		replyDAO.update(reply);
	}
	
	@Transactional
	@Override
	public void remove(Integer rno) throws Exception {
		boardDAO.updateReply(replyDAO.getBno(rno), -1);
		replyDAO.delete(rno);
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
		return replyDAO.listPage(bno, cri);
	}

	@Override
	public int getTotalCount(Integer bno) throws Exception {
		return replyDAO.getToalCount(bno);
	}
	
	//EEE
	@Override
	public ReplyVO ReadRno(Integer rno) throws Exception {
		return replyDAO.readRno(rno);
	}
	
}
