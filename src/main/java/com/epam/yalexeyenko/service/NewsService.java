package com.epam.yalexeyenko.service;

import java.util.List;

import com.epam.yalexeyenko.model.News;

public interface NewsService extends Service<News> {
	List findAllSortByDate();
}
