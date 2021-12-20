package com.quangnv.uet.service;

import java.util.List;

import com.quangnv.uet.dto.ColorDto;

public interface ColorService {
	public ColorDto saveColor(ColorDto colorDto, String productId);
	
	public List<ColorDto> getColorByProductId(String productId);
}
