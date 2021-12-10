package com.quangnv.uet.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.quangnv.uet.dto.ImageDto;
import com.quangnv.uet.entities.ImageEntity;

public interface ImageService {
	public List<ImageDto> saveImageToDb(String productId, MultipartFile[] images);
	public ImageEntity getImageById(String imageId);
}
