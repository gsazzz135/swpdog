package com.dogfriend.swp.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {
	private String uid;
	private String upw;
	private String uname;
	private Integer upoint;
	
}
