package com.quangnv.uet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.dto.CustomerDto;
import com.quangnv.uet.service.CustomerService;

@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping(value = "/customer")
public class CustomerApi {
	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<CustomerDto> getCustomerInfo() {
		String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		CustomerDto customerDto = customerService.getCustomerByUserId(userId);
		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
	}

}
