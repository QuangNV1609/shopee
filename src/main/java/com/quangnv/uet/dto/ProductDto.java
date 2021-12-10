package com.quangnv.uet.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDto {
	
	private String productId;
	private String productName;
	private String description;
	private Double price;
	private Integer sold;
	private Integer sale;
	private boolean isLiked;
	private Date createAt;
	private double rate;
	private int quantity;
	private int totalComment;
	private String ram;
	private String rom;
	
	private String categoryName;
	
	
	@EqualsAndHashCode.Exclude
	private List<ImageDto> imageDtos;
	
	@EqualsAndHashCode.Exclude
	private List<ColorDto> listColor;
}
