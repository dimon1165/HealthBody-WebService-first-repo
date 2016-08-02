package edu.softserveinc.healthbody.groups;

import static org.testng.AssertJUnit.*;

import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.db.TestDatabaseManager;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;

public class GroupServiceImplTest {

	@BeforeClass
	public void populateTestData(){
		new TestDatabaseManager().repopulateTestDatabase();
	}
	
	@AfterClass
	public void cleanTestData() {
		new TestDatabaseManager().cleanTestDatabaseTables();
	}
	
	@Test
	public void testGetAll() throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException,
			EmptyResultSetException, CloseStatementException, SQLException, TransactionException {
		GroupServiceImpl groupService = GroupServiceImpl.getInstance();
		int partNumber = 1;
		int partSize = 2;
		List<GroupDTO> groupAll = groupService.getAll(partNumber, partSize);
		Log4jWrapper.get().info("Printing all range of GroupDTO from " + partNumber + " to " + partSize);
		Log4jWrapper.get().info("[");
		for (GroupDTO group:groupAll){
			Log4jWrapper.get().info("  "+group.getName()+"   "+group.getCount()+"   "+group.getDescriptions()+"   "
					+group.getScoreGroup()+",");
		}
		Log4jWrapper.get().info("]");
	}

	 @Test
	 public void testGetDescriptionOfGroup() throws QueryNotFoundException,
	 JDBCDriverException, DataBaseReadingException, CloseStatementException, SQLException, TransactionException {
	 GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup("Name group number 2");
	 String actual = groupDTO.getDescriptions();
	 String expected = "Description of group 2";
	 assertEquals(expected, actual);
	 }

	@Test
	public void testUpdateGroupDTOStringStringString()
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException,
			EmptyResultSetException, SQLException, TransactionException {
		// TODO Fix test according to GUID implementation		
//		GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup("Name group number 3");
//		groupDTO.setCount("44");
//		groupDTO.setDescriptions("New description");
//		groupDTO.setScoreGroup("50");
//		GroupServiceImpl.getInstance().update(groupDTO);
//		assertNotNull(groupDTO);
//		assertEquals("Name group number 3", groupDTO.getName());
//		assertEquals("44", groupDTO.getCount());
//		assertEquals("New description", groupDTO.getDescriptions());
//		assertEquals("50", groupDTO.getScoreGroup());
	}

}
