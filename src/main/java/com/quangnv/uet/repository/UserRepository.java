package com.quangnv.uet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	public Optional<UserEntity> findByCustomerEntityEmail(String email);
	
	@Query("Select u From UserEntity u Where u.userId = :username Or u.username = :username Or u.customerEntity.email = :username")
	public Optional<UserEntity> findUser(@Param("username") String username);
}
