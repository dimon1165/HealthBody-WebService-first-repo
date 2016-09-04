package edu.softserveinc.healthbody.services;

import java.sql.SQLException;
import java.util.List;

import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.entity.UsersView;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public interface IUsersViewService {

	
	List<UserDTO> getAll(int partNumber, int partSize) 
			throws JDBCDriverException, SQLException, TransactionException;
	
	List<UserDTO> getAllbyAdmin(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	List<UserDTO> getAlltoAddInCompetition(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	List<UserDTO> getAllinGroup(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;
	
	List<UserDTO> getAllinCompetition(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	List<UsersView> searchUsers(String login) throws SQLException, JDBCDriverException, TransactionException;

	

}