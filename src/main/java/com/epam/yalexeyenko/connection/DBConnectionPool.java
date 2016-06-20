package com.epam.yalexeyenko.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnectionPool {
	public static Connection getConnection() {
		try {
			return InstanceHolder.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBConnectionPoolException("Failed to getConnection()");
		}
	}
	
	private static class InstanceHolder {
		private static final DataSource DATASOURCE = create();
		
		private static DataSource create() {
			InitialContext initialContext;
			DataSource datasource;		
			try {
				initialContext = new InitialContext();
				datasource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/taskNews");
			} catch (NamingException e) {
				e.printStackTrace();
				throw new DBConnectionPoolException("Database initial context error.");
			}
			return datasource;
		}
		
		public static DataSource getInstance() {
			return DATASOURCE;
		}
	}

}
