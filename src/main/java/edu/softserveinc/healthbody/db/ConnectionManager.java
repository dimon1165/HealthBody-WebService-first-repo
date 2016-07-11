package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;

public class ConnectionManager {
	private final static String FAILED_REGISTRATE_DRIVER = "Failed to Registrate JDBC Driver";

	private static volatile ConnectionManager instance = null;

	private DataSource dataSource;
	private final HashMap<Long, Connection> connections;

	public ConnectionManager() {
		this.connections = new HashMap<Long, Connection>();
	}

	public static ConnectionManager getInstance() throws JDBCDriverException {
		return getInstance(null);
	}

	public static ConnectionManager getInstance(DataSource dataSource) throws JDBCDriverException {
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
	private void checkStatus(DataSource dataSource) throws JDBCDriverException {
		if (dataSource == null) {
			if (getDataSource() == null) {
				setDataSource(DataSourceRepository.getInstance().getPostgresLocalHost());
			}
		} else if ((getDataSource() == null) || (!getDataSource().equals(dataSource))) {
			setDataSource(dataSource);
		}

	}

	private DataSource getDataSource() {
		return this.dataSource;
	}

	private void setDataSource(DataSource dataSource) throws JDBCDriverException {
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

	private HashMap<Long, Connection> getAllConections() {
		return this.connections;
	}

	private void addConnection(Connection connection) {
		getAllConections().put(Thread.currentThread().getId(), connection);
	}

	public Connection getConnection() throws JDBCDriverException {
		Connection connection = getAllConections().get(Thread.currentThread().getId());
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(getDataSource().getConnectionUrl(), getDataSource().getUser(),
						getDataSource().getPasswrd());
			} catch (SQLException e) {
				throw new JDBCDriverException(FAILED_REGISTRATE_DRIVER, e);
			}
			addConnection(connection);
		}
		return connection;
	}

	public void beginTransaction() throws SQLException, JDBCDriverException {
		getConnection().setAutoCommit(false);
	}

	public void commitTransaction() throws SQLException, JDBCDriverException {
		getConnection().commit();
		getConnection().setAutoCommit(true);
	}

	public void rollbackTransaction() throws SQLException, JDBCDriverException {
		getConnection().rollback();
		getConnection().setAutoCommit(true);
	}

	public static void closeAllConnections() throws JDBCDriverException {
		if (instance != null) {
			for (Long key : instance.getAllConections().keySet()) {
				if (instance.getAllConections().get(key) != null) {
					try {
						instance.getAllConections().get(key).close();
					} catch (SQLException e) {
						throw new JDBCDriverException(FAILED_REGISTRATE_DRIVER, e);
					}
					instance.getAllConections().put(key, null);
				}
			}

		}
	}

}
