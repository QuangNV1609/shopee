package com.quangnv.uet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.dto.ColorDto;
import com.quangnv.uet.service.ColorService;

@RestController
@RequestMapping(value = "/color")
public class ColorApi {
	@Autowired
	private ColorService colorService;

	@PostMapping(value = "/{productId}")
	public ResponseEntity<ColorDto> saveColorForProduct(@RequestBody ColorDto colorDto,
			@PathVariable("productId") String productId) {
		colorDto = colorService.saveColor(colorDto, productId);
		return new ResponseEntity<ColorDto>(colorDto, HttpStatus.CREATED);
	}

}
