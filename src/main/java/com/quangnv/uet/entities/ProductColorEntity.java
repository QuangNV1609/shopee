package com.quangnv.uet.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.quangnv.uet.entities.ids.ProductColorId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorEntity {
	@EmbeddedId
	private ProductColorId productColorId;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;
}
