package com.quangnv.uet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProductDto {
	private String productId;
	private String cartId;
	private String productName;
	private String colorname;
	private Double price;
	private Integer quantity;
	private String image;
}
