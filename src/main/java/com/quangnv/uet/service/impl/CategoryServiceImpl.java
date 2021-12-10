package com.quangnv.uet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quangnv.uet.dto.CategoryDto;
import com.quangnv.uet.entities.CategoryEntity;
import com.quangnv.uet.repository.CategoryRepository;
import com.quangnv.uet.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CategoryDto> getCategoryHighlights(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
		List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();
		for (CategoryEntity categoryEntity : categoryEntities.getContent()) {
			categoryDtos.add(modelMapper.map(categoryEntity, CategoryDto.class));
		}
		return categoryDtos;
	}

}
