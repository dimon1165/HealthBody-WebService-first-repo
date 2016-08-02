package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.log.Log4jWrapper;

public class ConnectionManager {
	private static final String FAILED_REGISTRATE_DRIVER = "Failed to Registrate JDBC Driver";
	private static final int MAX_POOL_SIZE = 15;

	private static volatile ConnectionManager instance;

	private DataSource dataSource;
	private final List<Connection> connections;

	public ConnectionManager() {
		this.connections = new ArrayList<Connection>();
	}

	public static ConnectionManager getInstance() throws JDBCDriverException {
		return getInstance(null);
	}

	public static ConnectionManager getInstance(final DataSource dataSource) throws JDBCDriverException {
		if (instance == null) {
			synchronized (ConnectionManager.class) {
				if (instance == null) {
					instance = new ConnectionManager();
				}
			}
		}
		instance.checkStatus(dataSource);
		return instance;
	}

	/*-		dataSource		this.dataSource		Action
	 * 			null			null				create default
	 * 			null			not null			nothing
	 * 			not null		null				save dataSource
	 * 			not null		not null			if equals then nothing 
	 */
	private void checkStatus(final DataSource dataSource) throws JDBCDriverException {
		if (dataSource == null) {
			if (getDataSource() == null) {
				setDataSource(DataSourceRepository.getInstance().getPostgresDatabase());
			}
		} else if ((getDataSource() == null) || (!getDataSource().equals(dataSource))) {
			setDataSource(dataSource);
		}
	}

	private DataSource getDataSource() {
		return this.dataSource;
	}

	private void setDataSource(final DataSource dataSource) throws JDBCDriverException {
		synchronized (ConnectionManager.class) {
			this.dataSource = dataSource;
			registerDriver();
			closeAllConnections();
		}
	}

	private void registerDriver() throws JDBCDriverException {
		try {
			DriverManager.registerDriver(getDataSource().getJdbcDriver());
		} catch (SQLException e) {
			throw new JDBCDriverException(FAILED_REGISTRATE_DRIVER, e);
		}
	}

	private void addConnection(Connection connection) {
		while(!checkIfConnectionPoolIsFull()) {
		  connections.add(connection);
		}
	}
		 
	private synchronized boolean checkIfConnectionPoolIsFull() {
		if (connections.size() < MAX_POOL_SIZE) {
			return false;
		}
		return true;
	}
	
	private synchronized Connection getConnectionFromPool()
	 {
	  Connection connection = null;
	  if(connections.size() > 0) {
	   connection = connections.get(0);
	   connections.remove(0);
	   returnConnectionToPool(connection);
	  }
	  return connection;
	 }
	
	private synchronized void returnConnectionToPool(Connection connection) {
		  connections.add(connection);
		 }

	public final Connection getConnection() throws JDBCDriverException {
		Connection connection = getConnectionFromPool();
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(getDataSource().getConnectionUrl(), getDataSource().getUser(),
						getDataSource().getPasswrd());
			} catch (SQLException e) {
				Log4jWrapper.get().error("url" + getDataSource().getConnectionUrl());
				Log4jWrapper.get().error("user" + getDataSource().getUser());
				Log4jWrapper.get().error("password" + getDataSource().getPasswrd());
				Log4jWrapper.get().error("Error while getting connection.", e);
				throw new JDBCDriverException(FAILED_REGISTRATE_DRIVER, e);
			}
			addConnection(connection);
		}
		return connection;
	}
		
	public final void beginTransaction() throws SQLException, JDBCDriverException {
		getConnection().setAutoCommit(false);
	}

	public final void commitTransaction() throws SQLException, JDBCDriverException {
		getConnection().commit();
		getConnection().setAutoCommit(true);
	}

	public final void rollbackTransaction() throws SQLException, JDBCDriverException {
		getConnection().rollback();
		getConnection().setAutoCommit(true);
	}

	private List<Connection> getAllConections() {
		return this.connections;
	}

	private void closeAllConnections() throws JDBCDriverException {
		if (instance != null) {
			for (Iterator<Connection> iterator = instance.getAllConections().iterator(); iterator.hasNext();) {
			    Connection conn = iterator.next();
// Let's hope it will close closed connection without exception :)			    
//				if (conn != null && !conn.isClosed()) {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						throw new JDBCDriverException(FAILED_REGISTRATE_DRIVER, e);
					}
					iterator.remove();
				}
			}
		}
}
}
