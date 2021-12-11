package com.quangnv.uet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quangnv.uet.dto.CartProductDto;
import com.quangnv.uet.entities.CartEntity;
import com.quangnv.uet.entities.CartProductEntity;
import com.quangnv.uet.entities.ProductEntity;
import com.quangnv.uet.entities.ids.CartProductId;
import com.quangnv.uet.repository.CartProductRepository;
import com.quangnv.uet.repository.CartRepository;
import com.quangnv.uet.repository.ProductRepository;
import com.quangnv.uet.service.CartProductService;

@Service
public class CartProductServiceImpl implements CartProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartProductRepository cartProductRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CartProductDto addProductToCart(CartProductDto cartProductDto) {
		CartEntity cartEntity = cartRepository.findById(cartProductDto.getCartId()).get();
		ProductEntity productEntity = productRepository.findById(cartProductDto.getProductId()).get();

		CartProductEntity cartProductEntity = modelMapper.map(cartProductDto, CartProductEntity.class);
		CartProductId cartProductId = new CartProductId(cartEntity.getCartId(), productEntity.getProductId(),
				cartProductDto.getColorname());

		cartProductEntity.setCartProductId(cartProductId);
		cartProductEntity.setCartEntity(cartEntity);
		cartProductEntity.setProductEntity(productEntity);

		cartProductRepository.save(cartProductEntity);

		String image = "http://localhost:8080/image/" + productEntity.getImageEntities().get(0).getImageId();

		cartProductDto.setImage(image);
		cartProductDto.setProductName(productEntity.getProductName());

		return cartProductDto;
	}

	@Override
	public List<CartProductDto> getCartByCartId(String cardId) {
		List<CartProductEntity> cartProductEntities = cartProductRepository.findByCartId(cardId);
		List<CartProductDto> cartProductDtos = new ArrayList<CartProductDto>();
		for (CartProductEntity cartProductEntity : cartProductEntities) {
			CartProductDto cartProductDto = modelMapper.map(cartProductEntity, CartProductDto.class);
			String image = "http://localhost:8080/image/"
					+ cartProductEntity.getProductEntity().getImageEntities().get(0).getImageId();

			cartProductDto.setImage(image);
			cartProductDto.setProductName(cartProductEntity.getProductEntity().getProductName());
			cartProductDto.setProductId(cartProductEntity.getProductEntity().getProductId());
			cartProductDto.setCartId(cartProductEntity.getCartEntity().getCartId());
			cartProductDto.setColorname(cartProductEntity.getCartProductId().getColorname());

			cartProductDtos.add(cartProductDto);
		}
		return cartProductDtos;
	}

	@Override
	public void deleteProduct(CartProductId cartProductId) {
		cartProductRepository.deleteById(cartProductId);
	}

	@Override
	public void deleteListProduct(List<CartProductId> cartProductIds) {
		for (CartProductId cartProductId : cartProductIds) {
			cartProductRepository.deleteById(cartProductId);
		}
	}

}
