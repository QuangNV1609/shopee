package com.quangnv.uet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String>{
	public Optional<CustomerEntity> findByUserEntityUserId(String userId);
}
