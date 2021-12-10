package com.quangnv.uet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.dto.ProductDto;
import com.quangnv.uet.dto.ProductListDto;
import com.quangnv.uet.service.ProductService;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(value = "http://localhost:3000/")
public class ProductApi {
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/{productId}")
	public ResponseEntity<ProductDto> test(@PathVariable("productId") String productId) {
		ProductDto productDto = productService.getProductById(productId);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
	}

	@GetMapping(value = "/find")
	public ResponseEntity<ProductListDto> getProductByProductName(@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size,
			@RequestParam(value = "productName", required = false) String productName) {
		ProductListDto productListDto = productService.getProducts(page, size, productName);
		return new ResponseEntity<ProductListDto>(productListDto, HttpStatus.OK);
	}

	@GetMapping(value = "/find/sort")
	public ResponseEntity<ProductListDto> getProductByProductNameSort(@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size,
			@RequestParam(value = "productName", required = false) String productName,
			@RequestParam(value = "date", required = false) Boolean date,
			@RequestParam(value = "sold", required = false) Boolean sold,
			@RequestParam(value = "price", required = false) Boolean price) {
		ProductListDto productListDto = productService.getProductsSort(page, size, productName, date, sold, price);
		return new ResponseEntity<ProductListDto>(productListDto, HttpStatus.OK);
	}

	@GetMapping(value = "/find/category")
	public ResponseEntity<ProductListDto> getProductByCategoryName(@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size, @RequestParam(value = "categoryName") String categoryName) {
		ProductListDto productListDto = productService.getProductsByCategoryName(page, size, categoryName);
		return new ResponseEntity<ProductListDto>(productListDto, HttpStatus.OK);
	}

	@GetMapping(value = "/find/topSold")
	public ResponseEntity<ProductListDto> getProductByCategoryName(@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size, @RequestParam(value = "categoryName") String categoryName,
			@RequestParam("sold") Boolean sold) {
		ProductListDto productListDto = productService.getProductsTopSold(page, size, categoryName);
		return new ResponseEntity<ProductListDto>(productListDto, HttpStatus.OK);
	}

	@GetMapping(value = "/find/filter")
	public ResponseEntity<ProductListDto> getProductByFilter(@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size,
			@RequestParam(value = "productName", required = false) String productName,
			@RequestParam(value = "date", required = false, defaultValue = "false") Boolean date,
			@RequestParam(value = "sold", required = false, defaultValue = "false") Boolean sold,
			@RequestParam(value = "price", required = false, defaultValue = "false") Boolean price,
			@RequestParam(value = "rate", required = false) Double rate,
			@RequestParam(value = "min", required = false) Double priceMin,
			@RequestParam(value = "max", required = false) Double priceMax,
			@RequestParam(value = "categories", required = false) String[] categories) {
		ProductListDto productListDto = productService.getProductsByFiltersSort(page, size, productName, date, sold,
				price, rate, priceMin, priceMax, categories);
		return new ResponseEntity<ProductListDto>(productListDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
		productDto = productService.addProduct(productDto);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.CREATED);
	}
}
