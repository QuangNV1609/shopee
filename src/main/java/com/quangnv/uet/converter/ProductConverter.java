package com.quangnv.uet.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quangnv.uet.dto.ImageDto;
import com.quangnv.uet.dto.ProductDto;
import com.quangnv.uet.entities.ProductEntity;

@Component
public class ProductConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public List<ProductDto> convertProductEntitesToProductDtos(List<ProductEntity> productEntities) {
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		for (ProductEntity productEntity : productEntities) {
			List<ImageDto> imageDtos = new ArrayList<ImageDto>();
			ImageDto imageDto = modelMapper.map(productEntity.getImageEntities().get(0), ImageDto.class);
			imageDto.setDownloadUri("http://localhost:8080/image/" + imageDto.getImageId());
			imageDtos.add(imageDto);
			ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);
			productDto.setImageDtos(imageDtos);
			productDtos.add(productDto);
		}
		return productDtos;
	}
}
