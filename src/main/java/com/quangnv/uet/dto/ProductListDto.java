package com.quangnv.uet.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {
	private List<ProductDto> productDtos;
	private int totalPage;
}
