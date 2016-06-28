package com.epam.yalexeyenko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
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
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			session.beginTransaction();
			createdId = (Integer) session.save(news);
			createdNews = session.load(News.class, createdId);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new DaoException("Failed to insert news.", e);
		}
		return createdNews;
	}

	@Override
	public News findById(int id) {
		log.debug("findById()...");
		News receivedNews;
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			session.beginTransaction();
			receivedNews = session.load(News.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new DaoException("Failed to find news by id.", e);
		}
		return receivedNews;
	}

	@Override
	public List<News> findAll() {
		List<News> newsList = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			session.beginTransaction();
			newsList = session.createCriteria(News.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Failed to find all news.", e);
		}
		return newsList;
	}

	@Override
	public void update(News news) {
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			session.beginTransaction();
			session.update(news);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new DaoException("Failed to update news.", e);
		}
	}

	@Override
	public void delete(int id) {
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
			session.beginTransaction();
			session.delete(session.get(News.class, id));
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new DaoException("Failed to delete news.", e);
		}
	}
	
	

}
