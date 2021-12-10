package com.quangnv.uet.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quangnv.uet.dto.CustomerDto;
import com.quangnv.uet.entities.CustomerEntity;
import com.quangnv.uet.repository.CustomerRepository;
import com.quangnv.uet.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CustomerDto getCustomerByUserId(String userId) {
		CustomerEntity customerEntity = customerRepository.findByUserEntityUserId(userId).get();
		CustomerDto customerDto = modelMapper.map(customerEntity, CustomerDto.class);
		customerDto.setCartId(customerEntity.getCartEntity().getCartId());
		customerDto.setUsername(customerEntity.getUserEntity().getUsername());
		return customerDto;
	}

}
