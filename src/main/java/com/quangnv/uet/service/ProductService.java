package com.quangnv.uet.service;

import com.quangnv.uet.dto.ProductDto;
import com.quangnv.uet.dto.ProductListDto;

public interface ProductService {
	public ProductDto getProductById(String productId);

	public ProductDto addProduct(ProductDto productDto);

	public ProductListDto getProducts(int page, int size, String productName);

	public ProductListDto getProductsSort(int page, int size, String productName, Boolean date, Boolean sold,
			Boolean ascending);

	public ProductListDto getProductsByFiltersSort(int page, int size, String productName, Boolean date, Boolean sold,
			Boolean ascending, Double rate, Double priceMin, Double priceMax, String[] categories);

	public ProductListDto getProductsByCategoryName(int page, int size, String categoryName);
	
	public ProductListDto getProductsTopSold(int page, int size, String categoryName);
}
