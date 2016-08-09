package com.epam.yalexeyenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.yalexeyenko.model.HistoryItem;
import com.epam.yalexeyenko.repository.HistoryItemRepository;

@Service
@Transactional
public class HistoryItemServiceImpl implements HistoryItemService {

	@Autowired
	private HistoryItemRepository historyItemRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public HistoryItem create(HistoryItem t) {
		return historyItemRepository.saveAndFlush(t);
	}

	@Override
	@Transactional(readOnly = true)
	public HistoryItem find(Long id) {
		return historyItemRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<HistoryItem> findAll(Pageable pageRequest) {
		return historyItemRepository.findAll(pageRequest);
	}

	@Override
	public void update(HistoryItem t) {
		historyItemRepository.saveAndFlush(t);
	}

	@Override
	public void delete(Long id) {
		historyItemRepository.delete(id);
	}

	public HistoryItemRepository getHistoryItemRepository() {
		return historyItemRepository;
	}

	public void setHistoryItemRepository(HistoryItemRepository historyItemRepository) {
		this.historyItemRepository = historyItemRepository;
	}

}
