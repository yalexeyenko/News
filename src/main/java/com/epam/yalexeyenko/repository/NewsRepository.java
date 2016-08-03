package com.epam.yalexeyenko.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.model.User;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
	Page<News> findAll(Pageable pageable);
	Page<News> findByUser(Pageable pageable, User user);
	Page<News> findByDateBetweenAndUser(Pageable pageable, LocalDate start, LocalDate end, User user);
}