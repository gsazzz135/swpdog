package com.dogfriend.swp.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Product {
	private Date registDate;
	private String serialNumber;
}
