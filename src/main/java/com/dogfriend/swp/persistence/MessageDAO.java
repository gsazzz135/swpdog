package com.dogfriend.swp.persistence;

import com.dogfriend.swp.domain.MessageVO;

public interface MessageDAO {
	
	void create(MessageVO message) throws Exception;
	MessageVO readMessage(Integer mid) throws Exception;
	void updateState(Integer mid) throws Exception;
	
}
