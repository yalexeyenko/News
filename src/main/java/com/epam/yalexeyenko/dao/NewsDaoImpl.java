package com.epam.yalexeyenko.dao;

import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.model.News;

public class NewsDaoImpl implements NewsDao {
	private static final Logger log = LoggerFactory.getLogger(NewsDaoImpl.class);
	
	private EntityManager em = Persistence.createEntityManagerFactory("newsblock").createEntityManager();

	private final Connection connection;

	public NewsDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public News insert(News news) {
		log.debug("insert()...");
		News createdNews;
		EntityTransaction transaction = null;
		try {
		transaction = em.getTransaction();
		transaction.begin();
		createdNews = em.merge(news);
		transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException("Failed to insert news.", e);
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return createdNews;
	}

	@Override
	public News findById(int id) {
		log.debug("findById()...");
		return em.find(News.class, id);
	}

	@Override
	public List<News> findAll() {
		TypedQuery<News> namedQuery = em.createNamedQuery("News.findAll", News.class);
		return namedQuery.getResultList();
	}

	@Override
	public void update(News news) {
		EntityTransaction transaction = null;
		try {
		transaction = em.getTransaction();
		transaction.begin();
		em.merge(news);
		transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException("Failed to update news.", e);
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public void delete(int id) {
		EntityTransaction transaction = null;
		try {
		transaction = em.getTransaction();
		transaction.begin();
		em.remove(findById(id));
		transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException("Failed to update news.", e);
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}
	
	

}
