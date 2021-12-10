package com.quangnv.uet.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quangnv.uet.dto.ImageDto;
import com.quangnv.uet.entities.ImageEntity;
import com.quangnv.uet.entities.ProductEntity;
import com.quangnv.uet.repository.ImageRepository;
import com.quangnv.uet.repository.ProductRepository;
import com.quangnv.uet.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ImageDto> saveImageToDb(String productId, MultipartFile[] images) {
		ProductEntity productEntity = productRepository.findById(productId).get();
		
		List<ImageDto> imageDtos = new ArrayList<ImageDto>();
		for (int i = 0; i < images.length; i++) {
			try {
				ImageEntity imageEntity = new ImageEntity();
				imageEntity.setImageName(images[i].getOriginalFilename());
				imageEntity.setImageType(images[i].getContentType());
				imageEntity.setImageData(images[i].getBytes());
				imageEntity.setProductId(productEntity);
				imageDtos.add(modelMapper.map(imageRepository.save(imageEntity), ImageDto.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imageDtos;
	}

	@Override
	public ImageEntity getImageById(String imageId) {
		Optional<ImageEntity> optional = imageRepository.findById(imageId);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

}
