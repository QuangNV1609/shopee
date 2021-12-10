package com.quangnv.uet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.quangnv.uet.converter.ProductConverter;
import com.quangnv.uet.dto.ColorDto;
import com.quangnv.uet.dto.ImageDto;
import com.quangnv.uet.dto.ProductDto;
import com.quangnv.uet.dto.ProductListDto;
import com.quangnv.uet.entities.CategoryEntity;
import com.quangnv.uet.entities.ImageEntity;
import com.quangnv.uet.entities.ProductColorEntity;
import com.quangnv.uet.entities.ProductEntity;
import com.quangnv.uet.filters.SearchSort;
import com.quangnv.uet.repository.CategoryRepository;
import com.quangnv.uet.repository.ProductRepository;
import com.quangnv.uet.service.ProductService;

@Service
public class ProductSeviceImpl implements ProductService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductConverter productConverter;

	@Override
	public ProductDto getProductById(String productId) {
		Optional<ProductEntity> optional = productRepository.findById(productId);
		if (optional.isPresent()) {
			ProductEntity productEntity = optional.get();
			ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);
			productDto.setCategoryName(productEntity.getCategory().getCategoryName());
			List<ImageDto> imageDtos = new ArrayList<ImageDto>();
			for (ImageEntity imageEntity : productEntity.getImageEntities()) {
				ImageDto imageDto = modelMapper.map(imageEntity, ImageDto.class);
				imageDto.setDownloadUri("http://localhost:8080/image/" + imageDto.getImageId());
				imageDtos.add(imageDto);
			}
			List<ColorDto> colorDtos = new ArrayList<ColorDto>();
			for (ProductColorEntity productColorEntity : productEntity.getProductColors()) {
				ColorDto colorDto = new ColorDto(productColorEntity.getProductColorId().getColorname(),
						productColorEntity.getQuantity());
				colorDtos.add(colorDto);
				productDto.setQuantity(productDto.getQuantity() + colorDto.getQuantity());
			}
			productDto.setTotalComment(productEntity.getListComment().size());
			productDto.setListColor(colorDtos);
			productDto.setImageDtos(imageDtos);
			return productDto;
		} else {
			return null;
		}
	}

	@Override
	public ProductListDto getProducts(int page, int size, String productName) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<ProductEntity> productEntities = null;
		ProductListDto productListDto = new ProductListDto();
		if (productName != null) {
			productEntities = productRepository.findByKeyword(productName, pageable);
			productListDto
					.setTotalPage((int) (Math.ceil((double) productRepository.countByKeyWord(productName) / size)));
		} else {
			productEntities = productRepository.findAll(pageable);
			productListDto.setTotalPage((int) (Math.ceil((double) productRepository.count() / size)));
		}
		List<ProductDto> productDtos = productConverter
				.convertProductEntitesToProductDtos(productEntities.getContent());
		productListDto.setProductDtos(productDtos);
		return productListDto;
	}

	@Override
	public ProductListDto getProductsByCategoryName(int page, int size, String categoryName) {
		Pageable pageable = PageRequest.of(page - 1, size);
		ProductListDto productListDto = new ProductListDto();
		Page<ProductEntity> productEntities = productRepository.findByCategoryCategoryName(categoryName, pageable);
		productListDto.setTotalPage(
				(int) (Math.ceil((double) productRepository.countByCategoryCategoryName(categoryName) / size)));
		List<ProductDto> productDtos = productConverter
				.convertProductEntitesToProductDtos(productEntities.getContent());
		productListDto.setProductDtos(productDtos);
		return productListDto;
	}

	@Override
	public ProductListDto getProductsSort(int page, int size, String productName, Boolean date, Boolean sold,
			Boolean price) {
		SearchSort searchSort = new SearchSort(date, sold, price);
		Sort sort = searchSort.getSort();
		Pageable pageable = PageRequest.of(page - 1, size, sort);
		Page<ProductEntity> productEntities = productRepository.findByKeyword(productName, pageable);
		ProductListDto productListDto = new ProductListDto();
		List<ProductDto> productDtos = productConverter
				.convertProductEntitesToProductDtos(productEntities.getContent());
		productListDto.setProductDtos(productDtos);
		productListDto.setTotalPage((int) (Math.ceil((double) productRepository.countByKeyWord(productName) / size)));
		return productListDto;
	}

	@Override
	public ProductListDto getProductsByFiltersSort(int page, int size, String productName, Boolean date, Boolean sold,
			Boolean price, Double rate, Double priceMin, Double priceMax, String[] categories) {
		SearchSort filters = new SearchSort(date, price, sold);
		Sort sort = filters.getSort();
		Pageable pageable = PageRequest.of(page - 1, size, sort);
		ProductListDto productListDto = new ProductListDto();
		Page<ProductEntity> productEntities = null;
		if (rate != null && priceMin != null && priceMax != null && categories != null) {
			productEntities = productRepository.findAll(productName, categories, rate, priceMin, priceMax, pageable);
			productListDto.setTotalPage((int) (Math.ceil((double) productRepository
					.countByCategoryNamesAndRateAndPrice(productName, categories, rate, priceMin, priceMax) / size)));
		} else if (rate != null && priceMin != null && priceMax != null) {
			productEntities = productRepository.findAllByRateAndPrice(productName, rate, priceMin, priceMax, pageable);
			productListDto.setTotalPage((int) (Math.ceil(
					(double) productRepository.countByRateAndPrice(productName, rate, priceMin, priceMax) / size)));
		} else if (priceMin != null && priceMax != null && categories != null) {
			productEntities = productRepository.findAllByCategoryNamesAndPrice(productName, categories, priceMin,
					priceMax, pageable);
			productListDto.setTotalPage((int) (Math.ceil(
					(double) productRepository.countByCategoryNamesAndPrice(productName, categories, priceMin, priceMax)
							/ size)));
		} else if (rate != null && priceMax != null && categories != null) {
			productEntities = productRepository.findAllByCategoryNamesAndRateAndPriceMax(productName, categories, rate,
					priceMax, pageable);
			productListDto.setTotalPage((int) (Math.ceil((double) productRepository
					.countByCategoryNamesAndRateAndPriceMax(productName, categories, rate, priceMax) / size)));
		} else if (rate != null && priceMin != null && categories != null) {
			productEntities = productRepository.findAllByCategoryNamesAndRateAndPriceMin(productName, categories, rate,
					priceMin, pageable);
			productListDto.setTotalPage((int) (Math.ceil((double) productRepository
					.countByCategoryNamesAndRateAndPriceMin(productName, categories, rate, priceMin) / size)));
		} else if (rate != null && categories != null) {
			productEntities = productRepository.findAllByCategoryNamesAndRate(productName, categories, rate, pageable);
			productListDto.setTotalPage((int) (Math.ceil(
					(double) productRepository.countByCategoryNamesAndRate(productName, categories, rate) / size)));
		} else if (priceMin != null && categories != null) {
			productEntities = productRepository.findAllByCategoryNamesAndPriceMin(productName, categories, priceMin,
					pageable);
			productListDto.setTotalPage((int) (Math
					.ceil((double) productRepository.countByCategoryNamesAndPriceMin(productName, categories, priceMin)
							/ size)));
		} else if (priceMax != null && categories != null) {
			productEntities = productRepository.findAllByCategoryNamesAndPriceMax(productName, categories, priceMax,
					pageable);
			productListDto.setTotalPage((int) (Math
					.ceil((double) productRepository.countByCategoryNamesAndPriceMax(productName, categories, priceMax)
							/ size)));
		} else if (priceMax != null && rate != null) {
			productEntities = productRepository.findAllByRateAndPriceMax(productName, rate, priceMax, pageable);
			productListDto.setTotalPage((int) (Math
					.ceil((double) productRepository.countByRateAndPriceMax(productName, rate, priceMax) / size)));
		} else if (priceMin != null && rate != null) {
			productEntities = productRepository.findAllByRateAndPriceMin(productName, rate, priceMin, pageable);
			productListDto.setTotalPage((int) (Math
					.ceil((double) productRepository.countByRateAndPriceMin(productName, rate, priceMin) / size)));
		} else if (priceMin != null && priceMax != null) {
			productEntities = productRepository.findAllByPrice(productName, priceMin, priceMax, pageable);
			productListDto.setTotalPage(
					(int) (Math.ceil((double) productRepository.countByPrice(productName, priceMin, priceMax) / size)));
		} else if (priceMin != null) {
			productEntities = productRepository.findAllByPriceMin(productName, priceMin, pageable);
			productListDto.setTotalPage(
					(int) (Math.ceil((double) productRepository.countByPriceMin(productName, priceMin) / size)));
		} else if (priceMax != null) {
			productEntities = productRepository.findAllByPriceMax(productName, priceMax, pageable);
			productListDto.setTotalPage(
					(int) (Math.ceil((double) productRepository.countByPriceMax(productName, priceMax) / size)));
		} else if (rate != null) {
			productEntities = productRepository.findAllByRate(productName, rate, pageable);
			productListDto
					.setTotalPage((int) (Math.ceil((double) productRepository.countByRate(productName, rate) / size)));
		} else if (categories != null) {
			productEntities = productRepository.findAllByCategoryNames(productName, categories, pageable);
			productListDto.setTotalPage(
					(int) (Math.ceil((double) productRepository.countByCategoryNames(productName, categories) / size)));
		} else {
			productEntities = productRepository.findByKeyword(productName, pageable);
			productListDto
					.setTotalPage((int) (Math.ceil((double) productRepository.countByKeyWord(productName) / size)));
		}
		List<ProductDto> productDtos = productConverter
				.convertProductEntitesToProductDtos(productEntities.getContent());
		productListDto.setProductDtos(productDtos);
		return productListDto;
	}

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
		Optional<CategoryEntity> optional = categoryRepository.findByCategoryName(productDto.getCategoryName());
		CategoryEntity categoryEntity = null;
		if (optional.isPresent()) {
			categoryEntity = optional.get();
		} else {
			categoryEntity = new CategoryEntity();
			categoryEntity.setCategoryName(productDto.getCategoryName());
			categoryEntity = categoryRepository.save(categoryEntity);
		}
		productEntity.setCategory(categoryEntity);
		productEntity.setCreateAt(new Date());
		productEntity = productRepository.save(productEntity);
		productDto = modelMapper.map(productEntity, ProductDto.class);
		productDto.setCategoryName(categoryEntity.getCategoryName());
		return productDto;
	}

	@Override
	public ProductListDto getProductsTopSold(int page, int size, String categoryName) {
		Sort sort = Sort.by("sold").descending();
		Pageable pageable = PageRequest.of(page - 1, size, sort);
		Page<ProductEntity> listProductEntity = productRepository.findByCategoryCategoryName(categoryName, pageable);
		List<ProductDto> productDtos = productConverter
				.convertProductEntitesToProductDtos(listProductEntity.getContent());
		ProductListDto productListDto = new ProductListDto();
		productListDto.setProductDtos(productDtos);
		productListDto.setTotalPage((int) (Math.ceil((double) productRepository.countByCategoryCategoryName(categoryName) / size)));
		return productListDto;
	}
}
