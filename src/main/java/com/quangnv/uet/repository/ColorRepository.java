package com.quangnv.uet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.ProductColorEntity;
import com.quangnv.uet.entities.ids.ProductColorId;

@Repository
public interface ColorRepository extends JpaRepository<ProductColorEntity, ProductColorId> {
	@Query("Select pc.quantity From ProductColorEntity pc Where pc.productColorId.colorname = :colorname")
	public Integer getQuantityByColorname(String colorname);

	@Query("Select pc From ProductColorEntity pc Where pc.productEntity.productId = :productId")
	public List<ProductColorEntity> findByProductId(@Param("productId") String productId);
}
