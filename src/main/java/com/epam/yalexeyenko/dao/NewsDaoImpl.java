package com.epam.yalexeyenko.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.epam.yalexeyenko.model.News;

public class NewsDaoImpl implements NewsDao {
	private static final Logger log = LoggerFactory.getLogger(NewsDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	@Override
	public News insert(News news) {
		log.debug("insert()...");
		News createdNews;
		createdNews = entityManager.merge(news);
		return createdNews;
	}

	@Override
	public News findById(int id) {
		log.debug("findById()...");
		return entityManager.find(News.class, id);
	}

	@Override
	public List<News> findAll() {
		TypedQuery<News> namedQuery = entityManager.createNamedQuery("News.findAll", News.class);
		return namedQuery.getResultList();
	}

	@Override
	public List<News> findAllSortByDate() {
		TypedQuery<News> namedQuery = entityManager.createNamedQuery("News.findAllOrderedByDate", News.class);
		return namedQuery.getResultList();
	}

	@Transactional
	@Override
	public void update(News news) {
		entityManager.merge(news);
	}

	@Transactional
	@Override
	public void delete(int id) {
		entityManager.remove(findById(id));
	}

}
