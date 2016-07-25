package com.epam.yalexeyenko.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.repository.NewsRepository;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
	private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
	
	@Autowired
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
	public News find(Long id) {
		return newsRepository.findOne(id);
	}
	
	@Override
	public Page<News> findAll(Pageable pageRequest) {
		return newsRepository.findAll(pageRequest);
	}

	@Override
	public void update(News news) {
		newsRepository.saveAndFlush(news);
	}

	@Override
	public void delete(Long id) {
		newsRepository.delete(id);
	}
}
