package com.epam.yalexeyenko.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.dao.DaoFactory;
import com.epam.yalexeyenko.dao.NewsDao;
import com.epam.yalexeyenko.model.News;

public class NewsService implements AutoCloseable {
	private static final Logger log = LoggerFactory.getLogger(NewsService.class);

	private DaoFactory hiberDaoFactory;
	private NewsDao newsDao;

	public NewsService() {
		hiberDaoFactory = DaoFactory.newInstance(DaoFactory.HIBER);
		newsDao = (NewsDao) hiberDaoFactory.createDao(News.class);
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
	
	public void updateNews(News news) {
		newsDao.update(news);
	}
	
	public void deleteNewsById(int id) {
		newsDao.delete(id);
	}
	
	@Override
	public void close() throws Exception {
		if (hiberDaoFactory != null) {
			hiberDaoFactory.close();
		}
		
	}
}
