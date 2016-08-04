package com.epam.yalexeyenko.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.yalexeyenko.controller.NewsController;
import com.epam.yalexeyenko.converter.NewsConverter;
import com.epam.yalexeyenko.dto.NewsDTO;
import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.repository.NewsRepository;
import com.epam.yalexeyenko.repository.StatusRepository;
import com.epam.yalexeyenko.repository.UserRepository;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
	private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
	
	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NewsConverter newsConverter;

	public NewsRepository getNewsRepository() {
		return newsRepository;
	}

	public void setNewsRepository(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}
	
	public NewsConverter getNewsConverter() {
		return newsConverter;
	}

	public void setNewsConverter(NewsConverter newsConverter) {
		this.newsConverter = newsConverter;
	}

	@Override
	public NewsDTO create(NewsDTO newsDTO, String email) {
		News news = newsConverter.newsDTOToNews(newsDTO);
		log.debug("news.getStatus().getName(): {}", news.getStatus().getName());
		news.setUser(userRepository.findByEmail(email));
		return newsConverter.newsToDTO(newsRepository.saveAndFlush(news));
	}

	@Override
	public NewsDTO find(Long id) {
		return newsConverter.newsToDTO(newsRepository.findOne(id));
	}
	
	@Override
	public Page<NewsDTO> findAll(Pageable pageRequest) {
		Page<News> pageNews = newsRepository.findAll(pageRequest);
		log.debug("pageNews.getContent().get(0).getUser().getEmail(): {}", pageNews.getContent().get(0).getUser().getEmail());
		return new PageImpl<NewsDTO>(newsConverter.newsToDTO(pageNews.getContent()), pageRequest, pageNews.getTotalElements());
	}
	
	@Override
	public Page<NewsDTO> findAllByUser(Pageable pageRequest, String userEmail) {
		Page<News> pageNews = newsRepository.findByUser(pageRequest, userRepository.findByEmail(userEmail));
		log.debug("pageNews.getContent().size(): {}", pageNews.getContent().size());
		return new PageImpl<NewsDTO>(newsConverter.newsToDTO(pageNews.getContent()), pageRequest, pageNews.getTotalElements());
	}
	
	@Override
	public Page<NewsDTO> findAllByStatus(Pageable pageRequest, String status) {
		Page<News> pageNews = newsRepository.findByStatus(pageRequest, statusRepository.findByName(status));
		log.debug("pageNews.getContent().size(): {}", pageNews.getContent().size());
		return new PageImpl<NewsDTO>(newsConverter.newsToDTO(pageNews.getContent()), pageRequest, pageNews.getTotalElements());
	}
	
	@Override
	public Page<NewsDTO> findByDateBetween(Pageable pageRequest, LocalDate start, LocalDate end, String userEmail) {
		Page<News> pageNews = newsRepository.findByDateBetweenAndUser(pageRequest, start, end, userRepository.findByEmail(userEmail));
		log.debug("pageNews.getContent().size(): {}", pageNews.getContent().size());
		return new PageImpl<NewsDTO>(newsConverter.newsToDTO(pageNews.getContent()), pageRequest, pageNews.getTotalElements());
	}

	@Override
	public void update(NewsDTO newsDTO, String email) {
		News news = newsConverter.newsDTOToNews(newsDTO);
		news.setUser(userRepository.findByEmail(email));
		newsRepository.saveAndFlush(news);
	}

	@Override
	public void delete(Long id) {
		newsRepository.delete(id);
	}

	@Override
	public List<NewsDTO> findAll() {
		return newsConverter.newsToDTO(newsRepository.findAll());
	}
	
	@Override
	public NewsDTO create(NewsDTO t) {
		throw new UnsupportedOperationException("Use create(NewsDTO newsDTO, String email) method to create news.");
	}

	@Override
	public void update(NewsDTO t) {
		throw new UnsupportedOperationException("Use update(NewsDTO newsDTO, String email) method to update news.");		
	}
}
