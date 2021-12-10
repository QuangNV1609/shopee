package com.quangnv.uet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.ProductColorEntity;
import com.quangnv.uet.entities.ids.ProductColorId;

@Repository
public interface ColorRepository extends JpaRepository<ProductColorEntity, ProductColorId> {

}
