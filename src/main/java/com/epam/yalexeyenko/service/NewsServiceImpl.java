package com.epam.yalexeyenko.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.dao.NewsDao;
import com.epam.yalexeyenko.model.News;

public class NewsServiceImpl implements NewsService {
	private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

	private NewsDao newsDao;

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public News create(News news) {
		return newsDao.insert(news);
	}

	@Override
	public News find(int id) {
		return newsDao.findById(id);
	}
	
	public List<News> findAll() {
		return newsDao.findAll();
	}
	
	@Override
	public List<News> findAllSortByDate() {
		return newsDao.findAllSortByDate();
	}
	
	@Override
	public void update(News news) {
		newsDao.update(news);
	}
	
	@Override
	public void delete(int id) {
		newsDao.delete(id);
	}
}
