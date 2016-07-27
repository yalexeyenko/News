package com.epam.yalexeyenko.service;

import java.util.List;

import dto.Dto;

public interface Service<T extends Dto> {
	T create(T t);
	T find(Long id);
	List<T> findAll();
	void update(T t);
	void delete(Long id);
}
