package com.quangnv.uet.service.impl;

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
		if(optional.isPresent()) {
			ProductEntity productEntity = optional.get();
			productColorEntity.setProductEntity(productEntity);
		}else {
			log.info(productId + "not found");
		}
		productColorEntity = colorRepository.save(productColorEntity);
		return modelMapper.map(productColorEntity, ColorDto.class);
	}

}
