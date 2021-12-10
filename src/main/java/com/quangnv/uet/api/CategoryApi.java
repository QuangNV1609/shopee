package com.quangnv.uet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.dto.CategoryDto;
import com.quangnv.uet.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin(value = "http://localhost:3000/")
public class CategoryApi {
	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "/{page}/{size}")
	public ResponseEntity<List<CategoryDto>> getPageCategory(@PathVariable("page") int page,
			@PathVariable("size") int size) {
		List<CategoryDto> categoryDtos = categoryService.getCategoryHighlights(page, size);
		return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.OK);
	}
}
