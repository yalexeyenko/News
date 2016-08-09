package com.epam.yalexeyenko.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.epam.yalexeyenko.dto.NewsDTO;

public interface NewsService extends Service<NewsDTO> {
	Page<NewsDTO> findAll(Pageable pageRequest);
	NewsDTO create(NewsDTO newsDTO, String email);
	void update(NewsDTO newsDTO, String email);
	Page<NewsDTO> findAllByUser(Pageable pageRequest, String userEmail);
	Page<NewsDTO> findAllByStatus(Pageable pageRequest, String status);
	Page<NewsDTO> findAllHistory(Pageable pageRequest);
	Page<NewsDTO> findByDateBetween(Pageable pageRequest, LocalDate start, LocalDate end);
	Page<NewsDTO> findAllHistoryByDateBetween(Pageable pageRequest, LocalDate start, LocalDate end);
}
