package com.epam.yalexeyenko.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.yalexeyenko.dto.NewsBoardDTO;
import com.epam.yalexeyenko.model.NewsHistoryItem;
import com.epam.yalexeyenko.repository.NewsHistoryItemRepository;

@Service
@Transactional
public class NewsHistoryItemServiceImpl implements NewsHistoryItemService {
	private static final Logger log = LoggerFactory.getLogger(NewsHistoryItemServiceImpl.class);
	
	@Autowired
	private NewsHistoryItemRepository newsBoardRepository;

	public NewsHistoryItemRepository getNewsBoardRepository() {
		return newsBoardRepository;
	}
	
	public void setNewsBoardRepository(NewsHistoryItemRepository newsBoardRepository) {
		this.newsBoardRepository = newsBoardRepository;
	}

	@Override
	public NewsHistoryItem create(NewsHistoryItem t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsHistoryItem find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NewsHistoryItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(NewsHistoryItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	

}
