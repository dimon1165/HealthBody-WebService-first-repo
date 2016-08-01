package edu.softserveinc.healthbody.competitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.constants.TestConstants;
import edu.softserveinc.healthbody.db.TestDatabaseManager;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.ICompetitionsViewService;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

public class CompetitionsViewServiceImplTest {
	
	@BeforeClass
	public void populateTestData(){
		new TestDatabaseManager().repopulateTestDatabase();
	}
	
	@AfterClass
	public void cleanTestData() {
		new TestDatabaseManager().cleanTestDatabaseTables();
	}
	
	@Test
	public void testGetAll() {
		ICompetitionsViewService cv = CompetitionsViewServiceImpl.getInstance();
		List<CompetitionDTO> result;
		try {
			result = cv.getAll(1, 2);
			assertNotNull(result);
			assertEquals(result.size(), 2);
//			LoggerWrapper.info("testGetAll");
//			LoggerWrapper.info(result.toString());
//			System.out.println("testGetAll");
//			System.out.println(result);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test
	public void testGetAllActive() {
		ICompetitionsViewService cv = CompetitionsViewServiceImpl.getInstance();  
		List<CompetitionDTO> result;
		try {
			result = cv.getAllActive(1, 10);
			assertNotNull(result);
//			LoggerWrapper.info("testGetAllActive");
//			LoggerWrapper.info(result.toString());
//			System.out.println("testGetAllActive");
//			System.out.println(result);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllByUser(@Optional("Login 7") String userlogin) {
		ICompetitionsViewService cv = CompetitionsViewServiceImpl.getInstance();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllByUser(1, 10, userlogin);
			assertNotNull(result);
//			LoggerWrapper.info("testGetAllByUser");
//			LoggerWrapper.info(result.toString());
//			System.out.println("testGetAllByUser");
//			System.out.println(result);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllByUserNullLogin() throws IllegalAgrumentCheckedException {
		ICompetitionsViewService cv = CompetitionsViewServiceImpl.getInstance();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllByUser(1, 10, null);
			assertNull(result);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllByUserEmptyLogin() throws IllegalAgrumentCheckedException {
		ICompetitionsViewService cv = CompetitionsViewServiceImpl.getInstance();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllByUser(1, 10, "");
			assertNull(result);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllByUserBadPaginationParams(@Optional("Login 7") String userlogin) {
		ICompetitionsViewService cv = CompetitionsViewServiceImpl.getInstance();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllByUser(1, 100, userlogin);
			assertNotNull(result);
			int getAllSize = result.size();
			result = cv.getAllByUser(-1, 1, userlogin);
			assertEquals(getAllSize, result.size());
			result = cv.getAllByUser(1, -1, userlogin);
			assertEquals(getAllSize, result.size());
			result = cv.getAllByUser(-1, -1, userlogin);
			assertEquals(getAllSize, result.size());
			result = cv.getAllByUser(0, 0, userlogin);
			assertEquals(getAllSize, result.size());
			result = cv.getAllByUser(2_000_000_000, 2_000_000_000, userlogin);
			assertEquals(0, result.size());
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllActiveByUser(@Optional("Login 7") String userlogin) {
		ICompetitionsViewService cv = CompetitionsViewServiceImpl.getInstance();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllActiveByUser(1, 10, userlogin);
			assertNotNull(result);
//			LoggerWrapper.info("testGetAllActiveByUser");
//			LoggerWrapper.info(result.toString());
//			System.out.println("testGetAllActiveByUser");
//			System.out.println(result);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
//		System.out.println("testGetAllActiveByUser");
//		System.out.println(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllActiveByUserNullLogin() throws IllegalAgrumentCheckedException {
		ICompetitionsViewService cv = CompetitionsViewServiceImpl.getInstance();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllActiveByUser(1, 10, null);
			assertNull(result);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllActiveByUserEmptyLogin() throws IllegalAgrumentCheckedException {
		ICompetitionsViewService cv = CompetitionsViewServiceImpl.getInstance();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllActiveByUser(1, 10, "");
			assertNull(result);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
	
}
