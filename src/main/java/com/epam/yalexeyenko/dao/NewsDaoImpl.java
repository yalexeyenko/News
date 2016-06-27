package com.epam.yalexeyenko.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.model.News;

public class NewsDaoImpl implements NewsDao {
	private static final Logger log = LoggerFactory.getLogger(NewsDaoImpl.class);

	private static final String INSERT_NEWS = "INSERT INTO NEWSBLOCK (TITLE, NEWSDATE, BRIEF, NEWSCONTENT) VALUES (?, ?, ?, ?)";
	private static final String FIND_NEWS_BY_ID = "SELECT ID, TITLE, NEWSDATE, BRIEF, NEWSCONTENT FROM NEWSBLOCK WHERE ID = ?";
	private static final String FIND_ALL_NEWS = "SELECT * FROM NEWSBLOCK ORDER BY NEWSDATE DESC";
	private static final String UPDATE_NEWS = "UPDATE NEWSBLOCK SET TITLE = ?, NEWSDATE = ?, BRIEF = ?, NEWSCONTENT = ? WHERE ID = ?";
	private static final String DELETE_NEWS_BY_ID = "DELETE FROM NEWSBLOCK WHERE ID = ?";
	
	private static final String ID = "ID";
	private static final String TITLE = "TITLE";
	private static final String NEWSDATE = "NEWSDATE";
	private static final String BRIEF = "BRIEF";
	private static final String NEWSCONTENT = "NEWSCONTENT";

	private final Connection connection;

	public NewsDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public News insert(News news) {
		log.debug("insert()...");
		try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWS, new String[]{ID});) {
			preparedStatement.setString(1, news.getNewsTitle());
			preparedStatement.setDate(2, new java.sql.Date(news.getDate().getTime()));
			preparedStatement.setString(3, news.getBrief());
			preparedStatement.setString(4, news.getContent());
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			news.setId(resultSet.getInt(1));
			return news;
		} catch (SQLException e) {
			throw new DaoException("SQL INSERT_NEWS error.", e);
		}
	}

	@Override
	public News findById(int id) {
		log.debug("findById()...");
		News news = new News();
		try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_NEWS_BY_ID);) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			news.setId(resultSet.getInt(ID));
			news.setNewsTitle(resultSet.getString(TITLE));
			news.setDate(new java.util.Date((resultSet.getDate(NEWSDATE).getTime())));
			news.setBrief(resultSet.getString(BRIEF));
			news.setContent(resultSet.getString(NEWSCONTENT));
			return news;
		} catch (SQLException e) {
			throw new DaoException("SQL FIND_NEWS_BY_ID error.", e);
		}
	}

	@Override
	public List<News> findAll() {
		List<News> newsList = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(FIND_ALL_NEWS);
			while (resultSet.next()) {
				News news = new News();
				news.setId(resultSet.getInt(ID));
				news.setNewsTitle(resultSet.getString(TITLE));
				news.setDate(new java.util.Date((resultSet.getDate(NEWSDATE).getTime())));
				news.setBrief(resultSet.getString(BRIEF));
				news.setContent(resultSet.getString(NEWSCONTENT));
				newsList.add(news);
			}
			return newsList;
		} catch (SQLException e) {
			throw new DaoException("SQL FIND_ALL_NEWS error.", e);
		}
	}

	@Override
	public void update(News news) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NEWS)) {
			preparedStatement.setString(1, news.getNewsTitle());
			preparedStatement.setDate(2, new java.sql.Date(news.getDate().getTime()));
			preparedStatement.setString(3, news.getBrief());
			preparedStatement.setString(4, news.getContent());
			preparedStatement.setInt(5, news.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
            throw new DaoException("SQL UPDATE_NEWS error.", e);
        }
	}

	@Override
	public boolean delete(int id) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NEWS_BY_ID)) {
			preparedStatement.setInt(1, id);
			return (preparedStatement.executeUpdate() != 0);
		} catch (SQLException e) {
            throw new DaoException("SQL DELETE_NEWS_BY_ID error.", e);
        }
	}
	
	

}
