package edu.softserveinc.healthbody.services;

import java.sql.SQLException;
import java.util.List;

import edu.softserveinc.healthbody.dto.AwardDTO;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.dto.GroupCompetitionsDTO;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserCompetitionsDTO;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public interface ICompetitionsViewService {

	List<CompetitionDTO> getAll(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	List<CompetitionDTO> getAllActive(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	List<CompetitionDTO> getAllByUser(int partNumber, int partSize, String login)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException;

	List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException;

	CompetitionDTO getCompetition(String name) throws JDBCDriverException, SQLException, TransactionException;

	boolean addUserInCompetition(String nameCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException;

	boolean removeUserFromCompetition(String nameCompetition, String nameUser) throws SQLException, JDBCDriverException, TransactionException;

	UserCompetitionsDTO getUserCompetition(String nameCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException;

	void deleteUserCompetition(String nameCompetition, String nameUser)
			throws SQLException, JDBCDriverException, TransactionException;

	CompetitionDTO getCompetitionByName(String name) throws JDBCDriverException, SQLException, TransactionException;

	void updateUserCompetition(UserCompetitionsDTO userCompetition)
			throws SQLException, JDBCDriverException, TransactionException, QueryNotFoundException, DataBaseReadingException;

	boolean addGroupInCompetition(String idCompetition, String idGroup)
			throws SQLException, JDBCDriverException, TransactionException;

	void deleteGroupCompetition(String idCompetition, String idGroup)
			throws SQLException, JDBCDriverException, TransactionException;

	List<GroupDTO> getAllGroupsByCompetition(int partNumber, int partSize, String idCompetition)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException;

	GroupCompetitionsDTO getGroupCompetition(String idCompetition, String idGroup)
			throws SQLException, JDBCDriverException, TransactionException;

	List<CompetitionDTO> getAllByGroup(int partNumber, int partSize, String idGroup)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException;

	List<AwardDTO> getAllAwards()
			throws JDBCDriverException, SQLException, TransactionException;

	List<UserCompetitionsDTO> getAllUserCompetitions() throws JDBCDriverException, SQLException, TransactionException;

}
