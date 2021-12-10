package com.quangnv.uet.api;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendMailDemo {
	@Autowired
	private JavaMailSender emailSender;

	@ResponseBody
	@RequestMapping("/sendSimpleEmail")
	public String sendSimpleEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		int code = new Random().nextInt(100000) + 1;

		message.setTo("lk3456392@gmail.com");
		message.setSubject("Code");
		message.setText("Mã xác thực của bạn là: " + code);
		
		emailSender.send(message);

		return "Email Sent!";
	}
}
