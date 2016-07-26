package com.epam.yalexeyenko.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dto.NewsDTO;

public interface NewsService {
	NewsDTO create(NewsDTO newdDTO);
	NewsDTO find(Long id);
	Page<NewsDTO> findAll(Pageable pageRequest);
	void update(NewsDTO newdDTO);
	void delete(Long id);
}
