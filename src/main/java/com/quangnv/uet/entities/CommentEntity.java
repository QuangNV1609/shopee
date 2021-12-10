package com.quangnv.uet.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.quangnv.uet.entities.ids.CommentId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
	@EmbeddedId
	private CommentId commentId;
	
	@Column(name = "rate")
	private Integer rate;
	
	@Column(name = "content")
	private String content;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	private Date lastModifiedDate;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;
}
