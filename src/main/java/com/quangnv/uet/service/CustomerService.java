package com.quangnv.uet.service;

import com.quangnv.uet.dto.CustomerDto;

public interface CustomerService {
	
	public CustomerDto getCustomerByUserId(String userId);
}
