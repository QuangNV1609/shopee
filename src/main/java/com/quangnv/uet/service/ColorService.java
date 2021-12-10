package com.quangnv.uet.service;

import com.quangnv.uet.dto.ColorDto;

public interface ColorService {
	public ColorDto saveColor(ColorDto colorDto, String productId);
}
