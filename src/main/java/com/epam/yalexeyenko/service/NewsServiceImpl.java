package com.epam.yalexeyenko.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.repository.NewsRepository;

@Repository
@Transactional
public class NewsServiceImpl implements NewsService {
	private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Autowired
	@Qualifier("newsRepository")
	private NewsRepository newsRepository;

	public NewsRepository getNewsRepository() {
		return newsRepository;
	}

	public void setNewsRepository(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}
	
	@Override
	public News create(News news) {
		return newsRepository.saveAndFlush(news);
	}

	@Override
	public News find(int id) {
		return newsRepository.findOne(id);
	}

	public List<News> findAll() {		
		return newsRepository.findAll();
	}

	@Override
	public List<News> findAllSortByDate() {
		return newsRepository.findAllSortByDate();
	}

	@Override
	public void update(News news) {
		newsRepository.saveAndFlush(news);
	}

	@Override
	public void delete(int id) {
		newsRepository.delete(id);
	}
}
