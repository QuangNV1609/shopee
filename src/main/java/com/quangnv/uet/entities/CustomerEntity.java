package com.quangnv.uet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String customerId;

	@Column(name = "email")
	private String email;
	
	@OneToOne(mappedBy = "customerEntity")
	@EqualsAndHashCode.Exclude
	private UserEntity userEntity;
	
	@OneToOne(mappedBy = "customerEntity")
	private CartEntity cartEntity;
}
