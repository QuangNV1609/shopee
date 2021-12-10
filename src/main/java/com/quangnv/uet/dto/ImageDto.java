package com.quangnv.uet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ImageDto {
	private String imageId;
	private String imageType;
	private String downloadUri;
	private String imageName;
}
