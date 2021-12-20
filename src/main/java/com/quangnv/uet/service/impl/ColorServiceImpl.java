package com.quangnv.uet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quangnv.uet.dto.ColorDto;
import com.quangnv.uet.entities.ProductColorEntity;
import com.quangnv.uet.entities.ProductEntity;
import com.quangnv.uet.entities.ids.ProductColorId;
import com.quangnv.uet.repository.ColorRepository;
import com.quangnv.uet.repository.ProductRepository;
import com.quangnv.uet.service.ColorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ColorServiceImpl implements ColorService {
	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ColorDto saveColor(ColorDto colorDto, String productId) {
		Optional<ProductEntity> optional = productRepository.findById(productId);
		ProductColorEntity productColorEntity = modelMapper.map(colorDto, ProductColorEntity.class);
		ProductColorId productColorId = new ProductColorId(colorDto.getColorname(), productId);
		productColorEntity.setProductColorId(productColorId);
		if (optional.isPresent()) {
			ProductEntity productEntity = optional.get();
			productColorEntity.setProductEntity(productEntity);
		} else {
			log.info(productId + "not found");
		}
		productColorEntity = colorRepository.save(productColorEntity);
		return modelMapper.map(productColorEntity, ColorDto.class);
	}

	@Override
	public List<ColorDto> getColorByProductId(String productId) {
		List<ProductColorEntity> productColorEntities = colorRepository.findByProductId(productId);
		List<ColorDto> colorDtos = new ArrayList<ColorDto>();
		for (ProductColorEntity productColorEntity : productColorEntities) {
			ColorDto colorDto = new ColorDto(productColorEntity.getProductColorId().getColorname(), productColorEntity.getQuantity());
			colorDtos.add(colorDto);
		}
		return colorDtos;
	}

}
