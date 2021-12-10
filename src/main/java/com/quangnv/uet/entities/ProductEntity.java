package com.quangnv.uet.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductEntity {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Double price;

	@Column(name = "sold")
	private int sold;

	@Column(name = "sale")
	private int sale;

	@Column(name = "ram", length = 5)
	private String ram;

	@Column(name = "rom", length = 5)
	private String rom;

	@Column(name = "is_like")
	private boolean isLiked;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	private Date lastModifiedDate;

	@Column(name = "rate")
	private double rate;

	@OneToMany(mappedBy = "productId")
	@EqualsAndHashCode.Exclude
	private List<ImageEntity> imageEntities;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@EqualsAndHashCode.Exclude
	private CategoryEntity category;

	@OneToMany(mappedBy = "productEntity")
	@EqualsAndHashCode.Exclude
	private List<ProductColorEntity> productColors;

	@OneToMany(mappedBy = "productEntity")
	private List<CommentEntity> listComment;

	@OneToMany(mappedBy = "productEntity")
	@EqualsAndHashCode.Exclude
	private List<CartProductEntity> cartProductDtos;

}
