package com.epam.yalexeyenko.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.dao.NewsDao;
import com.epam.yalexeyenko.model.News;

public class NewsService {
	private static final Logger log = LoggerFactory.getLogger(NewsService.class);

	private NewsDao newsDao;

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public News createNews(News news) {
		return newsDao.insert(news);
	}

	public News findNewsById(int id) {
		return newsDao.findById(id);
	}
	
	public List<News> findAllNews() {
		return newsDao.findAll();
	}
	
	public List<News> findAllNewsSortByDate() {
		return newsDao.findAllSortByDate();
	}
	
	public void updateNews(News news) {
		newsDao.update(news);
	}
	
	public void deleteNewsById(int id) {
		newsDao.delete(id);
	}
}
