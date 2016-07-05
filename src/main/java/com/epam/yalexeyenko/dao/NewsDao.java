package com.epam.yalexeyenko.dao;

import java.util.List;

import com.epam.yalexeyenko.model.News;

public interface NewsDao extends Dao<News> {
	List<News> findAllSortByDate();
}
