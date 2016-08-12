package edu.softserveinc.healthbody.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.ErrorConstants;
import edu.softserveinc.healthbody.dao.UsersViewDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.entity.UsersView;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.IUsersViewService;

public class UsersViewServiceImpl implements IUsersViewService {

	/*
	 * The getAll method is used for returning all users. Also used as basic
	 * method for following getAllbyAdmin,getAlltoAddInCompetition,
	 * getAllinGroup, getAllinCompetition.
	 **/
	@Override
	public final List<UserDTO> getAll(final int partNumber, final int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		List<UserDTO> userDTO = new ArrayList<>();
		Connection con = ConnectionManager.getInstance().beginTransaction();
		try {
			for (UsersView usersView : UsersViewDao.getInstance().getAllUsersView(partNumber, partSize)) {
				userDTO.add(new UserDTO(usersView.getId(), usersView.getLogin(), usersView.getPasswd(),
						usersView.getFirsName(), usersView.getLastName(), usersView.getMail(), usersView.getAge().toString(),
						usersView.getWeight().toString(), usersView.getGender(), usersView.getAvatar(),
						usersView.getRoleName(), usersView.getStatus(), usersView.getScore().toString(), usersView.getHealth(), null, null));
			}
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(con);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(con);
		return userDTO;
	}

	/*
	 * The getAllbyAdmin method is used for returning extended list of all users
	 * for admin role on a pages where users lists presented.
	 **/
	@Override
	public final List<UserDTO> getAllbyAdmin(final int partNumber, final int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		return getAll(partNumber, partSize);
	}

	/*
	 * The getAlltoAddInCompetition used for returning part of users list in
	 * competitions UI when user path's by competition -> description-> (press
	 * button) invite user.
	 **/
	@Override
	public final List<UserDTO> getAlltoAddInCompetition(final int partNumber, final int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		List<UserDTO> userDTO = new ArrayList<>();
		Connection con = ConnectionManager.getInstance().beginTransaction();
		try {
				for (UsersView usersView : UsersViewDao.getInstance().getAllUsersView(partNumber, partSize)) {
					userDTO.add(new UserDTO(usersView.getId(), usersView.getLogin(), usersView.getPasswd(),
							usersView.getFirsName(), usersView.getLastName(), usersView.getMail(), usersView.getAge().toString(),
							usersView.getWeight().toString(), usersView.getGender(), usersView.getAvatar(),
							usersView.getRoleName(), usersView.getStatus(), usersView.getScore().toString(), usersView.getHealth(), null, null));
				}
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(con);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(con);		
		return userDTO;
	}

	/*
	 * The getAllinGroup used for returning part of users list in Groups UI when
	 * user path's by main -> groups-> description of groups-> ->(press button)
	 * invite user to group.
	 **/
	@Override
	public final List<UserDTO> getAllinGroup(final int partNumber, final int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		return getAlltoAddInCompetition(partNumber, partSize);
	}

	/*
	 * The getAllinCompetition used for returning part of users list in
	 * competitions UI
	 **/
	@Override
	public final List<UserDTO> getAllinCompetition(final int partNumber, final int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		List<UserDTO> userDTO = new ArrayList<>();
		Connection con = ConnectionManager.getInstance().beginTransaction();
		try {
				for (UsersView usersView : UsersViewDao.getInstance().getAllUsersView(partNumber, partSize)) {
					userDTO.add(new UserDTO(usersView.getId(), usersView.getLogin(), usersView.getPasswd(),
							usersView.getFirsName(), usersView.getLastName(), usersView.getMail(), usersView.getAge().toString(),
							usersView.getWeight().toString(), usersView.getGender(), usersView.getAvatar(),
							usersView.getRoleName(), usersView.getStatus(), usersView.getScore().toString(), usersView.getHealth(), null, null));
				}
		} catch (QueryNotFoundException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction(con);
			throw new TransactionException(ErrorConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction(con);	
		return userDTO;
	}
}