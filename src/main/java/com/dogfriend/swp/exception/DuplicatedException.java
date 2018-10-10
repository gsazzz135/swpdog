package com.dogfriend.swp.exception;

public class DuplicatedException extends Exception {
	
	private static final long serialVersionUID = 684604463662203527L;
	private String msg;
	
	public DuplicatedException(Integer key) {
		this("t"+key);
	}
	
	public DuplicatedException(String tableName) {
		super(tableName);
		this.msg = tableName + "key Duplicated!";
	}
	
	public String getMessage() {
		return this.msg;
	}
}
