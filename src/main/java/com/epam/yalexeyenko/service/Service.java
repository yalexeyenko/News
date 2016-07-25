package com.epam.yalexeyenko.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.epam.yalexeyenko.model.BaseEntity;

public interface Service<T extends BaseEntity> {
	T create(T t);
	T find(Long id);
	Page<T> findAll(Pageable pageRequest);
	void update(T t);
	void delete(Long id);
}
