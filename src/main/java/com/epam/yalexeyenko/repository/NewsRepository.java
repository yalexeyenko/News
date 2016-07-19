package com.epam.yalexeyenko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.yalexeyenko.model.News;

@Repository("newsRepository")
public interface NewsRepository extends JpaRepository<News, Integer> {
	@Query("SELECT n FROM NEWSBLOCK n ORDER BY n.date DESC")
	public List<News> findAllSortByDate();
}
