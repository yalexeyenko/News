package com.epam.yalexeyenko.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.yalexeyenko.connection.DBConnectionPool;
import com.epam.yalexeyenko.model.BaseEntity;

public class HibDaoFactory extends DaoFactory {
    private static final Logger log = LoggerFactory.getLogger(HibDaoFactory.class);
    private final Connection connection;

    public HibDaoFactory() {
        connection = DBConnectionPool.getConnection();
        log.debug("CONNECTION CREATED: {}", connection);
    }

    @Override
    public <T extends BaseEntity> Dao<T> createDao(Class<T> clazz) {
        switch (clazz.getSimpleName()) {
            case "News":
                return (Dao<T>) new NewsDaoImpl(connection);
            default:
                return null;
        }
    }

    @Override
    public void close() throws DaoException {
        if (connection != null) {
            try {
                connection.close();
                log.debug("CONNECTION CLOSED: {}", connection.isClosed());
            } catch (SQLException e) {
                throw new DaoException("Failed to close factory", e);
            }
        }
    }

    @Override
    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException("Failed to setAutoCommit to false", e);
        }
    }

    @Override
    public void finishTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("Failed to commit transaction", e);
        }
    }

    @Override
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("Failed to rollback", e);
        }
    }
}
