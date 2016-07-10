package com.epam.yalexeyenko.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.dao.NewsDao;
import com.epam.yalexeyenko.dao.NewsDaoImpl;
import com.epam.yalexeyenko.model.News;

public class NewsServiceImpl implements NewsService {
	private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

	private NewsDao newsDaoImpl;

	public NewsDao getNewsDaoImpl() {
		return newsDaoImpl;
	}

	public void setNewsDaoImpl(NewsDao newsDaoImpl) {
		this.newsDaoImpl = newsDaoImpl;
	}

	@Override
	public News create(News news) {
		return newsDaoImpl.insert(news);
	}

	@Override
	public News find(int id) {
		return newsDaoImpl.findById(id);
	}
	
	public List<News> findAll() {
		return newsDaoImpl.findAll();
	}
	
	@Override
	public List<News> findAllSortByDate() {
		return newsDaoImpl.findAllSortByDate();
	}
	
	@Override
	public void update(News news) {
		newsDaoImpl.update(news);
	}
	
	@Override
	public void delete(int id) {
		newsDaoImpl.delete(id);
	}
}
