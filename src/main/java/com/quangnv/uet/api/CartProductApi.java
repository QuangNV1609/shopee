package com.quangnv.uet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.dto.CartProductDto;
import com.quangnv.uet.entities.ids.CartProductId;
import com.quangnv.uet.service.CartProductService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(value = "/cart")
public class CartProductApi {
	@Autowired
	private CartProductService cartProductService;

	@GetMapping(value = "/{cartId}")
	public ResponseEntity<List<CartProductDto>> getCartByCartId(@PathVariable("cartId") String cartId) {
		List<CartProductDto> cartProductDtos = cartProductService.getCartByCartId(cartId);
		return new ResponseEntity<List<CartProductDto>>(cartProductDtos, HttpStatus.OK);
	}

	@PostMapping(value = "/add")
	public ResponseEntity<CartProductDto> addProductToCart(@RequestBody CartProductDto cartProductDto) {
		cartProductDto = cartProductService.addProductToCart(cartProductDto);
		return new ResponseEntity<CartProductDto>(cartProductDto, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete")
	public void deleteProduct(@RequestBody CartProductDto cartProductDto) {
		CartProductId cartProductId = new CartProductId(cartProductDto.getCartId(), cartProductDto.getProductId(),
				cartProductDto.getColorname());
		cartProductService.deleteProduct(cartProductId);
	}

}
