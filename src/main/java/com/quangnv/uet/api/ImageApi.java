package com.quangnv.uet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.quangnv.uet.dto.ImageDto;
import com.quangnv.uet.entities.ImageEntity;
import com.quangnv.uet.service.ImageService;

@RestController
@RequestMapping(value = "/image")
public class ImageApi {
	@Autowired
	private ImageService imageService;

	@GetMapping(value = "/{imageId}")
	public ResponseEntity<Resource> downloadUri(@PathVariable("imageId") String imageId) {
		ImageEntity imageEntity = imageService.getImageById(imageId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(imageEntity.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + imageEntity.getImageName())
				.body(new ByteArrayResource(imageEntity.getImageData()));
	}

	@PostMapping(value = "/{productId}")
	public ResponseEntity<List<ImageDto>> saveImageForProduct(@PathVariable("productId") String productId,
			@RequestParam("images") MultipartFile[] images) {
		List<ImageDto> imageDtos = imageService.saveImageToDb(productId, images);
		return new ResponseEntity<List<ImageDto>>(imageDtos, HttpStatus.CREATED);
	}
}
