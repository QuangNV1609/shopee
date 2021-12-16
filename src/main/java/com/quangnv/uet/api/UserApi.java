package com.quangnv.uet.api;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quangnv.uet.dto.UserDto;
import com.quangnv.uet.jwt.JwtTokenProvider;
import com.quangnv.uet.service.UserService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(value = "/user")
public class UserApi {
	@Autowired
	private UserService userService;

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@GetMapping(value = "/check/mail")
	public ResponseEntity<String> checkMail(@RequestParam("email") String email) {
		String result = userService.mailIsExist(email);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<UserDto> getUserLogined() {
		String userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		UserDto userDto = userService.getUserByUserId(userId);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}")
	public ResponseEntity<UserDto> getUserByUserId(@PathVariable("userId") String userId) {
		UserDto userDto = userService.getUserByUserId(userId);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@GetMapping(value = "/get/code")
	public ResponseEntity<String> getCode(@RequestParam("email") String email) {
		Integer code = new Random().nextInt(100000) + 100000;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Code");
		message.setText("Mã xác thực của bạn là: " + code);
		emailSender.send(message);
		return new ResponseEntity<String>(code.toString(), HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
		UserDto dto = userService.saveUser(userDto);
		return new ResponseEntity<UserDto>(dto, HttpStatus.CREATED);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		userDto.setUserId(((User) authentication.getPrincipal()).getUsername());
		userDto.setPassword(null);

		String jwt = jwtTokenProvider.generateToken(userDto);
		
		userDto.setJwt(jwt);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@PutMapping(value = "/edit")
	public ResponseEntity<UserDto> changePassword(@RequestBody UserDto userDto) {
		userDto = userService.changePassword(userDto);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
}
