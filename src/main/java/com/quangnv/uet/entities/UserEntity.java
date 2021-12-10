package com.quangnv.uet.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
public class UserEntity {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String userId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "user")
	@EqualsAndHashCode.Exclude
	private List<CommentEntity> listCommets;

	@ManyToMany(mappedBy = "userEntities")
	@EqualsAndHashCode.Exclude
	private List<RoleEntity> roleEntities;
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	@EqualsAndHashCode.Exclude
	private CustomerEntity customerEntity;
}
