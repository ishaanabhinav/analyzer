package com.example.analyzer;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingCriteria {

	private static final String LOWER_BOUND_PAGE_NUMBER_MESSAGE = "Page number cannot be less than zero.";
	private static final String UPPER_BOUND_PAGE_SIZE_MESSAGE = "Page size must not be greater than ";

	private int page = 0;

	private int size = 100;

	private String sortOrder = "ASC";

	private String sortBy = "id";

	public Pageable getPageable() {
		if (this.page < 0) {
			throw new BadRequestException(LOWER_BOUND_PAGE_NUMBER_MESSAGE);
		}

		if (this.size > 1000) {
			throw new BadRequestException(UPPER_BOUND_PAGE_SIZE_MESSAGE + 1000);
		}
		return PageRequest.of(this.getPage(), this.getSize(), Sort.Direction.fromString(this.getSortOrder()),
				this.getSortBy());
	}

}
