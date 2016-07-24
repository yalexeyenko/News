package com.epam.yalexeyenko.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.yalexeyenko.model.News;

@Repository("newsRepository")
public interface NewsRepository extends JpaRepository<News, Integer> {
	Page<News> findAll(Pageable pageable);
}
