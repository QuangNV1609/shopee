package com.quangnv.uet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quangnv.uet.entities.CommentEntity;
import com.quangnv.uet.entities.ids.CommentId;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, CommentId>{
	public Page<CommentEntity> findByProductEntityProductId(String productId, Pageable pageable);
	
	public Page<CommentEntity> findByProductEntityProductIdAndRate(String productId, Integer rate, Pageable pageable);
	
	public long countByProductEntityProductId(String productId);
	
	public long countByRateAndProductEntityProductId(Integer rate, String productId);
}
