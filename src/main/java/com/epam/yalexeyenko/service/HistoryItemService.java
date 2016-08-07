package com.epam.yalexeyenko.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.epam.yalexeyenko.model.HistoryItem;

public interface HistoryItemService {
	HistoryItem create(HistoryItem t);
	HistoryItem find(Long id);
	Page<HistoryItem> findAll(Pageable pageRequest);
	void update(HistoryItem t);
	void delete(Long id);
}
