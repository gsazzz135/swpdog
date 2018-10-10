package com.dogfriend.swp.domain;

import java.util.Date;

import com.dogfriend.swp.domain.Product.ProductBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductUser {
	private int uid;
	private String serialNumber;
}
