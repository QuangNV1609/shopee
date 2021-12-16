package com.quangnv.uet.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEntity {
	@Id
	private String rolename;
	
	@ManyToMany
	@EqualsAndHashCode.Exclude
	@JoinTable(name = "user_role",
	joinColumns = @JoinColumn(name = "role_name"),
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserEntity> userEntities;
}
