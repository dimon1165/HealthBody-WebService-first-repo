package edu.softserveinc.healthbody.users;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.constants.TestConstants;
import edu.softserveinc.healthbody.db.CreateDropTestDatabase;
import edu.softserveinc.healthbody.db.DBPopulateManager;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

public class UsersViewServiceImplTest {

	@BeforeClass
	public void populateTestData(){
		new CreateDropTestDatabase().populateDBTables();
	}
	
	@AfterClass
	public void CleanTableAfterTest() throws SQLException, JDBCDriverException{
		DBPopulateManager.getInstance().deleteAllFromTables();
		Log4jWrapper.get().info("Aftertest block Userviewserviceimpl worked");
	}

	@Test
	public void testUserViewGetAll() {
		// TODO Fix test according to GUID implementation		
//		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
//		List<UserDTO> ud1;
//		try {
//			ud1 = uvs.getAll(1, 2);
//			assertNotNull(ud1);
//			assertEquals(ud1.size(), 2);
//			Log4jWrapper.get().info("testUserViewGetAll");
//			Log4jWrapper.get().info(ud1.toString());
//		} catch (JDBCDriverException | SQLException | TransactionException e) {
//			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
//			fail(TestConstants.EXCEPTION_CATCHED, e);
//		}
	}

	@Test 
	public void testUserViewGetAllbyAdmin() {
		// TODO Fix test according to GUID implementation		
//		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
//		List<UserDTO> ud3;
//		try {
//			ud3 = uvs.getAllbyAdmin(1, 2);
//			assertNotNull(ud3);
//			assertEquals(ud3.size(), 2);
//			Log4jWrapper.get().info("testUserViewGetAllbyAdmin");
//			Log4jWrapper.get().info(ud3.toString());
//		} catch (JDBCDriverException | SQLException | TransactionException e) {
//			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
//			fail(TestConstants.EXCEPTION_CATCHED, e);
//		}
	}

	@Test
	public void testUserViewGetAllinCompetition() {
		// TODO Fix test according to GUID implementation		
//		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
//		List<UserDTO> ud4;
//		try {
//			ud4 = uvs.getAllinCompetition(1, 2);
//			assertNotNull(ud4);
//			assertEquals(ud4.size(), 2);
//			Log4jWrapper.get().info("testUserViewGetAllinCompetition");
//			Log4jWrapper.get().info(ud4.toString());
//		} catch (JDBCDriverException | SQLException | TransactionException e) {
//			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
//			fail(TestConstants.EXCEPTION_CATCHED, e);
//		}
	}

	@Test
	public void testUserViewGetAllinGroup() {
		// TODO Fix test according to GUID implementation		
//		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
//		List<UserDTO> ud5;
//		try {
//			ud5 = uvs.getAllinGroup(1, 2);
//			assertNotNull(ud5);
//			assertEquals(ud5.size(), 2);
//			Log4jWrapper.get().info("testUserViewGetAllinGroup");
//			Log4jWrapper.get().info(ud5.toString());
//		} catch (JDBCDriverException | SQLException | TransactionException e) {
//			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
//			fail(TestConstants.EXCEPTION_CATCHED, e);
//		}
	}

	@Test
	public void testUserViewGetAlltoAddInCompetition() {
		// TODO Fix test according to GUID implementation		
//		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
//		List<UserDTO> ud6;
//		try {
//			ud6 = uvs.getAlltoAddInCompetition(1, 2);
//			assertNotNull(ud6);
//			assertEquals(ud6.size(), 2);
//			Log4jWrapper.get().info("testUserViewGetAlltoAddInCompetition");
//			Log4jWrapper.get().info(ud6.toString());
//		} catch (JDBCDriverException | SQLException | TransactionException e) {
//			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
//			fail(TestConstants.EXCEPTION_CATCHED, e);
//		}
	}


}

