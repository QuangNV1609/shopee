package com.quangnv.uet.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCommentDto {
	
	private List<CommentDto> commentDtos;
	private int totalPage;
	private List<Integer> rates;
}
