package com.dogfriend.swp.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dogfriend.swp.domain.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO{

	@Inject
	private SqlSession session;
	
	private static final String NS = "messageMapper";
	private static final String CREATE = NS + ".create";
	private static final String READ_MESSAGE = NS + ".readMessage";
	private static final String UPDATE_STATE = NS + ".updateState";
	
	@Override
	public void create(MessageVO message) throws Exception{
		session.insert(CREATE, message);
	}
	
	@Override
	public MessageVO readMessage(Integer mid) throws Exception{
		return session.selectOne(READ_MESSAGE, mid);
	}
	
	@Override
	public void updateState(Integer mid) throws Exception{
		session.update(UPDATE_STATE, mid);
	}
}
