package com.epam.yalexeyenko.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.epam.yalexeyenko.model.BaseEntity;

public interface Service<T extends BaseEntity> {
	T create(T t);
	T find(int id);
	Page<T> findAll(Pageable pageRequest);
	void update(T t);
	void delete(int id);
}
