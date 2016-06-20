package com.epam.yalexeyenko.dao;

import java.util.List;

import com.epam.yalexeyenko.model.BaseEntity;

public interface Dao<T extends BaseEntity> {
    T insert(T t);
    T findById(int id);
    List<T> findAll();
    void update(T t);
    boolean delete(int id);
}
