package com.epam.yalexeyenko.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.epam.yalexeyenko.dto.NewsDTO;

public interface NewsService extends Service<NewsDTO> {
	Page<NewsDTO> findAll(Pageable pageRequest);
}
