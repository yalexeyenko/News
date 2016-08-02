package com.epam.yalexeyenko.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.epam.yalexeyenko.dto.NewsDTO;
import com.epam.yalexeyenko.model.User;

public interface NewsService extends Service<NewsDTO> {
	Page<NewsDTO> findAll(Pageable pageRequest);
	NewsDTO create(NewsDTO newsDTO, String email);
	void update(NewsDTO newsDTO, String email);
	Page<NewsDTO> findAllByUser(Pageable pageRequest, String userEmail);
}
