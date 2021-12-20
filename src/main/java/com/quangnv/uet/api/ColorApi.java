package com.quangnv.uet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.dto.ColorDto;
import com.quangnv.uet.service.ColorService;

@RestController
@RequestMapping(value = "/color")
@CrossOrigin(value = "http://localhost:3000/")
public class ColorApi {
	@Autowired
	private ColorService colorService;

	@GetMapping(value = "/{productId}")
	public ResponseEntity<List<ColorDto>> getListolorbyProductId(@PathVariable("productId") String productId) {
		List<ColorDto> colorDtos = colorService.getColorByProductId(productId);
		return new ResponseEntity<List<ColorDto>>(colorDtos, HttpStatus.OK);
	}

	@PostMapping(value = "/{productId}")
	public ResponseEntity<ColorDto> saveColorForProduct(@RequestBody ColorDto colorDto,
			@PathVariable("productId") String productId) {
		colorDto = colorService.saveColor(colorDto, productId);
		return new ResponseEntity<ColorDto>(colorDto, HttpStatus.CREATED);
	}

}
