package edu.softserveinc.healthbody.services;

import java.sql.SQLException;
import java.util.List;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public interface IGroupService extends IBaseFilterService<GroupDTO>{
	
	String getDescriptionOfGroup(GroupDTO groupDTO);

	GroupDTO getGroup(String name) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException;

	List<GroupDTO> getAll(int partNumber, int partSize) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, 
															EmptyResultSetException, CloseStatementException;
	
	void update(GroupDTO groupDTO) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException, SQLException, EmptyResultSetException, TransactionException;
}
