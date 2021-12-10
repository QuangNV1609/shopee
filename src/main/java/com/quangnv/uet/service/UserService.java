package com.quangnv.uet.service;

import com.quangnv.uet.dto.UserDto;

public interface UserService {
	public UserDto saveUser(UserDto userDto);
	
	public String mailIsExist(String email);
	
	public UserDto getUserByUserId(String userId);
	
	public UserDto changePassword(UserDto userDto);
}
