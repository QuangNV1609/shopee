package com.quangnv.uet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String imageId;
	
	@Column(name = "image_name")
	private String imageName;
	
	@Column(name = "type")
	private String imageType;
	
	@Lob
	private byte[] imageData;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@EqualsAndHashCode.Exclude
	private ProductEntity productId;
}
