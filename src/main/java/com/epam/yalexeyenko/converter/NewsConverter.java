package com.epam.yalexeyenko.converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.yalexeyenko.dto.NewsDTO;
import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.repository.StatusRepository;

@Repository
public class NewsConverter {
	@Autowired
	private StatusRepository statusRepository;
	
	public NewsDTO newsToDTO(News news) {
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setId(news.getId());
		newsDTO.setTitle(news.getTitle());
		newsDTO.setBrief(news.getBrief());
		newsDTO.setDate(news.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		newsDTO.setContent(news.getContent());
		newsDTO.setStatus(news.getStatus().getName());
		return newsDTO;
	}
	
	public News newsDTOToNews(NewsDTO newsDTO) {
		News news = new News();
		news.setId(newsDTO.getId());
		news.setTitle(newsDTO.getTitle());
		news.setBrief(newsDTO.getBrief());
		news.setDate(LocalDate.parse(newsDTO.getDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		news.setContent(newsDTO.getContent());
		news.setStatus(statusRepository.findByName(newsDTO.getStatus()));
		return news;
	}
	
	public List<NewsDTO> newsToDTO(List<News> newsList) {
		List<NewsDTO> newsDTOList = new ArrayList<>();
		for (News news : newsList) {
			newsDTOList.add(newsToDTO(news));
		}
		return newsDTOList;
	}
	
	public List<News> newsDTOToNews(List<NewsDTO> newsDTOList) {
		List<News> newsList = new ArrayList<>();
		for (NewsDTO newsDTO : newsDTOList) {
			newsList.add(newsDTOToNews(newsDTO));
		}
		return newsList;
	}
}
