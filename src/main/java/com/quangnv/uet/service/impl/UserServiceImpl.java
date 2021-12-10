package com.quangnv.uet.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quangnv.uet.dto.UserDto;
import com.quangnv.uet.entities.CartEntity;
import com.quangnv.uet.entities.CustomerEntity;
import com.quangnv.uet.entities.UserEntity;
import com.quangnv.uet.repository.CartRepository;
import com.quangnv.uet.repository.CustomerRepository;
import com.quangnv.uet.repository.UserRepository;
import com.quangnv.uet.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto saveUser(UserDto userDto) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setEmail(userDto.getEmail());
		customerEntity = customerRepository.save(customerEntity);

		CartEntity cartEntity = CartEntity.builder().customerEntity(customerEntity).build();
		cartRepository.save(cartEntity);

		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		userEntity.setCustomerEntity(customerEntity);
		userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userEntity = userRepository.save(userEntity);
		userEntity.setPassword(null);

		return modelMapper.map(userEntity, UserDto.class);
	}

	@Override
	public String mailIsExist(String email) {
		Optional<UserEntity> optional = Optional.ofNullable(userRepository.findByCustomerEntityEmail(email))
				.orElse(null);
		if (optional.isPresent()) {
			return optional.get().getCustomerEntity().getEmail();
		} else {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> optional = userRepository.findUser(username);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException(username + " not found!");
		}

		UserEntity userEntity = optional.get();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		List<RoleEntity> roles = userEntity.getRoleEntities();

//		for (RoleEntity userRole : roles) {
//			authorities.add(new SimpleGrantedAuthority(userRole.getRolename()));
//		}

		return new User(userEntity.getUserId(), userEntity.getPassword(), authorities);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		Optional<UserEntity> optional = userRepository.findById(userId);

		if (optional.isPresent()) {
			UserEntity userEntity = optional.get();
			UserDto userDto = modelMapper.map(userEntity, UserDto.class);
			userDto.setPassword("");
			return userDto;
		}
		return null;
	}

	@Override
	public UserDto changePassword(UserDto userDto) {
		Optional<UserEntity> optional = userRepository.findByCustomerEntityEmail(userDto.getEmail());
		UserEntity userEntity = optional.get();
		userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userRepository.save(userEntity);
		return modelMapper.map(userEntity, UserDto.class);
	}

}
