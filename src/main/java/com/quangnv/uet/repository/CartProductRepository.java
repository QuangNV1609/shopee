package com.quangnv.uet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.CartProductEntity;
import com.quangnv.uet.entities.ids.CartProductId;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, CartProductId>{
	
	@Query("Select p From CartProductEntity p Where p.cartEntity.cartId = :cartId")
	public List<CartProductEntity> findByCartId(@Param("cartId") String cartId);
}
