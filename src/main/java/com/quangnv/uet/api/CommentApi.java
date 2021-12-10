package com.quangnv.uet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.dto.CommentDto;
import com.quangnv.uet.dto.ListCommentDto;
import com.quangnv.uet.service.CommentService;

@RestController
@RequestMapping(value = "/comment")
@CrossOrigin(value = "http://localhost:3000/")
public class CommentApi {
	@Autowired
	private CommentService commentService;

	@GetMapping
	public ResponseEntity<ListCommentDto> getCommentByProductId(@RequestParam("page") int page,
			@RequestParam("size") int size, @RequestParam(value = "rate", required = false) Integer rate,
			@RequestParam("productId") String productId) {
		ListCommentDto listCommentDto = commentService.getListCommentDto(productId, rate, page, size);
		return new ResponseEntity<ListCommentDto>(listCommentDto, HttpStatus.OK);
	}

	@PostMapping(value = "/{userId}/{productId}")
	public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto,
			@PathVariable("userId") String userId, @PathVariable("productId") String productId) {
		commentDto = commentService.addCommentForProduct(commentDto, userId, productId);
		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.CREATED);
	}
}
