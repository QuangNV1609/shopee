package com.quangnv.uet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.ImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String>{
	
}
