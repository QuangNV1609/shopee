package com.quangnv.uet.entities.ids;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "cart_id")
	private String cartId;

	@Column(name = "product_id")
	private String productId;
	
	@Column(name = "color_name")
	private String colorname;

}