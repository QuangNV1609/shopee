package com.quangnv.uet.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.quangnv.uet.entities.ids.CartProductId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProductEntity {
	@EmbeddedId
	private CartProductId cartProductId;

	@Column(name = "price")
	private Double price;

	@Column(name = "quantity")
	private Integer quantity;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;

	@ManyToOne
	@MapsId("cartId")
	@JoinColumn(name = "cart_id")
	private CartEntity cartEntity;
}
