package com.quangnv.uet.service;

import com.quangnv.uet.dto.CommentDto;
import com.quangnv.uet.dto.ListCommentDto;

public interface CommentService {
	public CommentDto addCommentForProduct(CommentDto commentDto, String userId, String productId);
	
	public ListCommentDto getListCommentDto(String productId, Integer rate,  int page, int size); 
}
