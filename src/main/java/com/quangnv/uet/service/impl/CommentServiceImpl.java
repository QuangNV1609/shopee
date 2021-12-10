package com.quangnv.uet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quangnv.uet.dto.CommentDto;
import com.quangnv.uet.dto.ListCommentDto;
import com.quangnv.uet.dto.UserDto;
import com.quangnv.uet.entities.CommentEntity;
import com.quangnv.uet.entities.ProductEntity;
import com.quangnv.uet.entities.UserEntity;
import com.quangnv.uet.entities.ids.CommentId;
import com.quangnv.uet.repository.CommentRepository;
import com.quangnv.uet.repository.ProductRepository;
import com.quangnv.uet.repository.UserRepository;
import com.quangnv.uet.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto addCommentForProduct(CommentDto commentDto, String userId, String productId) {
		CommentEntity commentEntity = modelMapper.map(commentDto, CommentEntity.class);
		ProductEntity productEntity = productRepository.findById(productId).get();
		UserEntity userEntity = userRepository.findById(userId).get();
		productEntity.setRate((commentDto.getRate() + productEntity.getRate()) / 2);
		productRepository.save(productEntity);
		CommentId commentId = new CommentId(productId, userId);
		commentEntity.setCommentId(commentId);
		commentEntity.setUser(userEntity);
		commentEntity.setProductEntity(productEntity);
		commentEntity.setCreateAt(new Date());
		commentRepository.save(commentEntity);
		return commentDto;
	}

	@Override
	public ListCommentDto getListCommentDto(String productId, Integer rate, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<CommentEntity> comments;
		ListCommentDto listCommentDto = new ListCommentDto();
		if (rate != null) {
			comments = commentRepository.findByProductEntityProductIdAndRate(productId, rate, pageable);
			listCommentDto.setTotalPage((int) (Math
					.ceil((double) commentRepository.countByRateAndProductEntityProductId(rate, productId) / size)));
		} else {
			comments = commentRepository.findByProductEntityProductId(productId, pageable);
			listCommentDto.setTotalPage(
					(int) (Math.ceil((double) commentRepository.countByProductEntityProductId(productId) / size)));
		}
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		for (CommentEntity commentEntity : comments.getContent()) {
			CommentDto commentDto = modelMapper.map(commentEntity, CommentDto.class);
			UserDto userDto = modelMapper.map(commentEntity.getUser(), UserDto.class);
			commentDto.setUserDto(userDto);
			commentDtos.add(commentDto);
		}

		listCommentDto.setCommentDtos(commentDtos);
		List<Integer> rates = new ArrayList<Integer>();

		rates.add((int) commentRepository.countByRateAndProductEntityProductId(5, productId));
		rates.add((int) commentRepository.countByRateAndProductEntityProductId(4, productId));
		rates.add((int) commentRepository.countByRateAndProductEntityProductId(3, productId));
		rates.add((int) commentRepository.countByRateAndProductEntityProductId(2, productId));
		rates.add((int) commentRepository.countByRateAndProductEntityProductId(1, productId));

		listCommentDto.setRates(rates);

		return listCommentDto;
	}

}
