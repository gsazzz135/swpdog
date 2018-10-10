package com.dogfriend.swp.service;

import com.dogfriend.swp.domain.MessageVO;

public interface MessageService {
	
	public void addMessage(MessageVO message) throws Exception;
	
	public MessageVO readMessage(String uid, Integer mid) throws Exception;
}
