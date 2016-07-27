package com.epam.yalexeyenko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		newsRepository.saveAndFlush(newsConverter.newsDTOToNews(newsDTO));
	}

	@Override
	public void delete(Long id) {
		newsRepository.delete(id);
	}

	@Override
	public List<NewsDTO> findAll() {
		return newsConverter.newsToDTO(newsRepository.findAll());
	}
}
