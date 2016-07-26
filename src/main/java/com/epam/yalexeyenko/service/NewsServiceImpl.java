package com.epam.yalexeyenko.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.yalexeyenko.converter.NewsConverter;
import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.repository.NewsRepository;

import dto.NewsDTO;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
	private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
	
	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private NewsConverter newsConverter;

	public NewsRepository getNewsRepository() {
		return newsRepository;
	}

	public void setNewsRepository(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	@Override
	public NewsDTO create(NewsDTO newsDTO) {
		News news = newsConverter.newsDTOToNews(newsDTO);
		return newsConverter.newsToDTO(newsRepository.saveAndFlush(news));
	}

	@Override
	public NewsDTO find(Long id) {
		return newsConverter.newsToDTO(newsRepository.findOne(id));
	}
	
	@Override
	public Page<NewsDTO> findAll(Pageable pageRequest) {
		Page<News> pageNews = newsRepository.findAll(pageRequest);
		return new PageImpl<NewsDTO>(newsConverter.newsToDTO(pageNews.getContent()), pageRequest, pageNews.getTotalElements());
	}

	@Override
	public void update(NewsDTO newsDTO) {
		News news = newsConverter.newsDTOToNews(newsDTO);
		newsRepository.saveAndFlush(news);
	}

	@Override
	public void delete(Long id) {
		newsRepository.delete(id);
	}
}
