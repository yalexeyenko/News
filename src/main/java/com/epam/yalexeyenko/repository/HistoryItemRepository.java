package com.epam.yalexeyenko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.yalexeyenko.model.HistoryItem;

@Repository
public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
	Page<HistoryItem> findAll(Pageable pageable);
}
