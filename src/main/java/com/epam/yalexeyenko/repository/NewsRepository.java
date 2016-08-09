package com.epam.yalexeyenko.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.yalexeyenko.model.HistoryItem;
import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.model.Status;
import com.epam.yalexeyenko.model.User;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
	Page<News> findAll(Pageable pageable);
	Page<News> findByUser(Pageable pageable, User user);
	Page<News> findByStatus(Pageable pageable, Status status);
	@Query("select n from News n where n.date between ?1 and ?2")
	Page<News> findByDateBetween(Pageable pageable, LocalDate start, LocalDate end);
	@Query("SELECT n FROM News n JOIN HistoryItem h ON n.id = h.newsId")
	Page<News> findAllHistory(Pageable pageRequest);
	@Query("SELECT n FROM News n JOIN HistoryItem h ON n.id = h.newsId where n.date between ?1 and ?2")
	Page<News> findAllHistoryByDateBetween(Pageable pageable, LocalDate start, LocalDate end);
}