package com.quangnv.uet.filters;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchSort {
	private Boolean date;
	private Boolean price;
	private Boolean sold;

	public Sort getSort() {
		if (date) {
			return Sort.by("createAt").descending();
		} else if (sold) {
			return Sort.by("sold").descending();
		} else if (price) {
			return Sort.by("price").descending();
		} else if (price == false) {
			return Sort.by("price").ascending();
		}
		return null;
	}
}
