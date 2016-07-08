package com.epam.yalexeyenko.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.model.News;

public class NewsDaoImpl implements NewsDao {
	private static final Logger log = LoggerFactory.getLogger(NewsDaoImpl.class);
	
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public News insert(News news) {
		log.debug("insert()...");
		News createdNews;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		createdNews = entityManager.merge(news);
		transaction.commit();
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

	@Override
	public void update(News news) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(news);
		transaction.commit();
	}

	@Override
	public void delete(int id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(findById(id));
		transaction.commit();
	}
	
	

}
