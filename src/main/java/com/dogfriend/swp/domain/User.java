package com.dogfriend.swp.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private int uid;
	private String email;
	private String password;
	private String serialNumber;

}
