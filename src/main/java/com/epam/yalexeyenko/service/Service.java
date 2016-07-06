package com.epam.yalexeyenko.service;

import java.util.List;

import com.epam.yalexeyenko.model.BaseEntity;

public interface Service<T extends BaseEntity> {
	T create(T t);
	T find(int id);
	List<T> findAll();
	void update(T t);
	void delete(int id);
}
