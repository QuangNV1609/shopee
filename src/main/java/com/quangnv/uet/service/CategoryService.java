package com.quangnv.uet.service;

import java.util.List;

import com.quangnv.uet.dto.CategoryDto;

public interface CategoryService {
	public List<CategoryDto> getCategoryHighlights(int page, int size);
}
