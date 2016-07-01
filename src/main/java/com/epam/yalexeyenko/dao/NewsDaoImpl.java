package com.epam.yalexeyenko.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.util.HibernateUtil;

public class NewsDaoImpl implements NewsDao {
	private static final Logger log = LoggerFactory.getLogger(NewsDaoImpl.class);

	private final Connection connection;

	public NewsDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public News insert(News news) {
		log.debug("insert()...");
		News createdNews;
		Integer createdId;
		Session session = null;
		Transaction transaction = null;		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			createdId = (Integer) session.save(news);
			createdNews = session.load(News.class, createdId);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException("Failed to insert news.", e);
		} finally {
			if (session != null && session.isOpen()) {
		        session.close();
			}
		}
		return createdNews;
	}

	@Override
	public News findById(int id) {
		log.debug("findById()...");
		News receivedNews;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			session.beginTransaction();
			receivedNews = session.load(News.class, id);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException("Failed to find news by id.", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return receivedNews;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<News> findAll() {
		List<News> newsList = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			newsList = session.createCriteria(News.class).list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException("Failed to find all news.", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return newsList;
	}

	@Override
	public void update(News news) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			session.update(news);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException("Failed to update news.", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public void delete(int id) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			session.delete(session.get(News.class, id));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new DaoException("Failed to delete news.", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
