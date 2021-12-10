package com.quangnv.uet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {

}
