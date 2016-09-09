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
	private static final String ERROR_CONNECTION = "Error while getting connection";
	private static final String ERROR_FREE_CONNECTION =  "At this moment there is no free connection";
	private static final int MAX_POOL_SIZE = 50;
	private static final int MAX_PERMITED_POOL_SIZE = 500;
	private static final int WAIT_TIME_FOR_CONNECTION = 500;
	private static final int WAIT_ALL_TIME_FOR_CONNECTION = 30000;
	private int counter = 0;

	private static volatile ConnectionManager instance;

	private DataSource dataSource;
	private final List<Connection> connections;
	private Connection testConnection = null;

	private ConnectionManager() {
		this.connections = new ArrayList<>();
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

	private void populateConnectionPool() {		
		if (counter < MAX_PERMITED_POOL_SIZE){
			connections.add(createNewConnection());
		} else {
			waitFreeConnection();
		}		
	}
	
	private void waitFreeConnection() {
		int count = 0; 
		while(count <= WAIT_ALL_TIME_FOR_CONNECTION ){
			try {
				Thread.sleep(WAIT_TIME_FOR_CONNECTION);
				if (connections.size() > 0){
					return;
				}
				if (counter < MAX_PERMITED_POOL_SIZE){
					connections.add(createNewConnection());
					return;
				} 
				count += WAIT_TIME_FOR_CONNECTION;				
			} catch (InterruptedException e) {
				Log4jWrapper.get().error(ERROR_FREE_CONNECTION, e);
			}					
		}		
	}

	private synchronized Connection getConnectionFromPool() {
	  if(connections.isEmpty()) {
		  populateConnectionPool();
	  } 
	  if(!connections.isEmpty()) {
		  int lastElement = connections.size() - 1;
		  Connection connection = connections.get(lastElement);
		  connections.remove(lastElement);	 
		  return connection;		  
	  } else {
		  Log4jWrapper.get().error(ERROR_FREE_CONNECTION);
		  return null;
	  }
	}
	
	public synchronized Connection getConnection() {
		return getConnectionFromPool();
	}
	
	public synchronized void setUpConnectionForTest(Connection connection) {
		if (testConnection == null){			
				testConnection = connection;								
		} 
	}
	
	public synchronized Connection getConnectionForTest() {
		if (testConnection == null){	
			testConnection = getConnectionFromPool();
		}
		return testConnection;
	}
	
	public synchronized void closeTestConnection(Connection connection) {
		if (connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				Log4jWrapper.get().error(FAILED_REGISTRATE_DRIVER, e);
			}
			testConnection = null;
		}
	}
	
	private synchronized void returnConnectionToPool(Connection connection) throws SQLException {
		if ((connection != null) && (!connection.isClosed()) && (connections.size() < MAX_POOL_SIZE)){
			connections.add(connection);
		} else {
			closeConnection(connection);
		}
	}

	private void closeConnection(Connection connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				Log4jWrapper.get().error(FAILED_REGISTRATE_DRIVER, e);
			}
			counter--;
	}

	private final Connection createNewConnection() {
		Connection connection = null;		
		try {
			connection = DriverManager.getConnection(getDataSource().getConnectionUrl(), getDataSource().getUser(),
					getDataSource().getPasswrd());
		} catch (SQLException e) {
			Log4jWrapper.get().error(ERROR_CONNECTION, e);
		}	
		counter++;
		return connection;
	}
		
	public final Connection beginTransaction() throws SQLException, JDBCDriverException {
		Connection connection = getConnection();
		connection.setAutoCommit(false);
		return connection;
	}

	public final void commitTransaction(Connection connection) throws SQLException, JDBCDriverException {
		connection.commit();
		connection.setAutoCommit(true);
		returnConnectionToPool(connection);
	}

	public final void rollbackTransaction(Connection connection) throws SQLException, JDBCDriverException {
		connection.rollback();
		connection.setAutoCommit(true);
		returnConnectionToPool(connection);
	}

	private List<Connection> getAllConections() {
		return this.connections;
	}
	
	private void closeAllConnections() throws JDBCDriverException {
		if (instance != null) {
			for (Iterator<Connection> iterator = instance.getAllConections().iterator(); iterator.hasNext();) {
			    Connection connection = iterator.next();
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						throw new JDBCDriverException(FAILED_REGISTRATE_DRIVER, e);
					}
					iterator.remove();
				}
			}
		}
	}

}
