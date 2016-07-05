package com.epam.yalexeyenko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.model.News;
import com.epam.yalexeyenko.util.HibernateUtil;

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
		em.getTransaction().begin();
		createdNews = em.merge(news);
		em.getTransaction().commit();
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
	public List<News> findAllSortByDate() {
		TypedQuery<News> namedQuery = em.createNamedQuery("News.findAllOrderedByDate", News.class);
		return namedQuery.getResultList();
	}

	@Override
	public void update(News news) {
		em.getTransaction().begin();
		em.merge(news);
		em.getTransaction().commit();
	}

	@Override
	public void delete(int id) {
		em.getTransaction().begin();
		em.remove(findById(id));
		em.getTransaction().commit();
	}
	
	

}
