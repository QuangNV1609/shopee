package com.quangnv.uet.service;

import java.util.List;

import com.quangnv.uet.dto.CartProductDto;
import com.quangnv.uet.entities.ids.CartProductId;

public interface CartProductService {

	public CartProductDto addProductToCart(CartProductDto cartProductDto);
	
	public List<CartProductDto> getCartByCartId(String cardId);
	
	public void deleteProduct(CartProductId cartProductId);
	
	public void deleteListProduct(List<CartProductId> cartProductIds);
}
