package com.quangnv.uet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {
	private String customerId;
	private String username;
	private String email;
	private String cartId;
	private String role;

}
