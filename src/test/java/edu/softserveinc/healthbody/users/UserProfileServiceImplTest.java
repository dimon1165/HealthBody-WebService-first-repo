package edu.softserveinc.healthbody.users;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.constants.TestConstants;
import edu.softserveinc.healthbody.db.TestDatabaseManager;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.Log4jWrapper;
import edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;

public class UserProfileServiceImplTest {
	
	@BeforeClass
	public void populateTestData(){
		new TestDatabaseManager().repopulateTestDatabase();
	}
	
	@AfterClass
	public void cleanTestData() {
		new TestDatabaseManager().cleanTestDatabaseTables();
	}

	@Test
	public void testGetUserByLogin() {
		UserDTO userDTO1;
		try {
			userDTO1 = UserProfileServiceImpl.getInstance().get("Login 3");
			assertNotNull(userDTO1);
			assertEquals("Name of 3 user", userDTO1.getFirstname());
			assertEquals("LastName of 3 user", userDTO1.getLastname());
			assertEquals("Login 3", userDTO1.getLogin());
			assertEquals("password 3", userDTO1.getPassword());
			assertEquals("SomeMail3@gmail.com", userDTO1.getEmail());
			assertEquals("58.5999985", userDTO1.getWeight());
			assertEquals("28", userDTO1.getAge());
			assertEquals("w", userDTO1.getGender());
			assertEquals("user", userDTO1.getRoleName());
			Log4jWrapper.get().info("\n\nFirstname: "+ userDTO1.getFirstname() + "\nLastname: " +userDTO1.getLastname() +
					"\nLogin: " + userDTO1.getLogin() + "\nPassword: " + userDTO1.getPassword() + "\nE-mail: " + userDTO1.getEmail() +
					"\nWeight: " + userDTO1.getWeight() + "\nAge: " + userDTO1.getAge() + "\nGender: " + userDTO1.getGender() + "\nUserRole: " + userDTO1.getRoleName());
			Log4jWrapper.get().info("User's groups:  ");
			for (GroupDTO group : userDTO1.getGroups()) {
				Log4jWrapper.get().info(group.getName() + "     ");
			}
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
	
	//User Login couldn't be null
	@Test  (expectedExceptions = IllegalArgumentException.class) 
	public void testGetUserByLoginNull() {
		try {
			UserProfileServiceImpl.getInstance().get(null);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
	
	//Such user doesn't exist
	@Test
	public void testGetUserByLoginNotExist() {
		UserDTO userDTO;
		try {
			userDTO = UserProfileServiceImpl.getInstance().get("Marisol");
			assertEquals(null, userDTO);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
  
	@Test
	public void testUpdateUser() {
		UserDTO userDTO2;
		try {
			userDTO2 = UserProfileServiceImpl.getInstance().get("Login 2");
			userDTO2.setAge("56");
			userDTO2.setFirstname("Ivan");
			userDTO2.setPassword("heufrb"); 
			UserProfileServiceImpl.getInstance().update(userDTO2);
			assertNotNull(userDTO2);
			assertEquals("Ivan", userDTO2.getFirstname());
			assertEquals("LastName of 2 user", userDTO2.getLastname());
			assertEquals("Login 2", userDTO2.getLogin());
			assertEquals("heufrb", userDTO2.getPassword());
			assertEquals("SomeMail2@gmail.com", userDTO2.getEmail());
			assertEquals("57.5999985", userDTO2.getWeight());
			assertEquals("56", userDTO2.getAge());
			assertEquals("m", userDTO2.getGender());
			assertEquals("user", userDTO2.getRoleName());
			Log4jWrapper.get().info("\n\nFirstname: "+ userDTO2.getFirstname() + "\nLastname: " +userDTO2.getLastname() +
					"\nLogin: " + userDTO2.getLogin() + "\nPassword: " + userDTO2.getPassword() + "\nE-mail: " + userDTO2.getEmail() +
					"\nWeight: " + userDTO2.getWeight() + " \nAge: " + userDTO2.getAge() + "\nGender: " + userDTO2.getGender() + "\nUserRole: " + userDTO2.getRoleName());
			Log4jWrapper.get().info("User's groups:  ");
			for (GroupDTO group : userDTO2.getGroups()) {
				Log4jWrapper.get().info(group.getName() + "     ");
			}
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
	
	//You didn't enter user
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void testUpdateUserNull() {
		UserDTO userDTO2 = null;
		try {
			UserProfileServiceImpl.getInstance().update(userDTO2);
		} catch (SQLException | JDBCDriverException	| TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
		
	@Test
	public void testInsertUser() {
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
	 	try {
	 		groups.add(GroupServiceImpl.getInstance().getGroup("Name group number 1"));
	 		UserDTO userDTO3 = new UserDTO(UUID.randomUUID().toString(), "President", "password", "Bill", "Klinton", "SomeMail75@gmail.com", "67", "80.5","m", "photourl", "user", "active", "1000", groups, "false");
			UserProfileServiceImpl.getInstance().insert(userDTO3);
		 	UserDTO userDTO4 = UserProfileServiceImpl.getInstance().get("President");
		 	assertNotNull(userDTO4);
		 	assertEquals("Bill", userDTO4.getFirstname());
		 	assertEquals("Klinton", userDTO4.getLastname());
		 	assertEquals("President", userDTO4.getLogin());
		 	assertEquals("password", userDTO4.getPassword());
		 	assertEquals("SomeMail75@gmail.com", userDTO4.getEmail());
		 	assertEquals("80.5", userDTO4.getWeight());
		 	assertEquals("67", userDTO4.getAge());
		 	assertEquals("m", userDTO4.getGender());
		 	Log4jWrapper.get().info("\n\nFirstname: "+ userDTO4.getFirstname() + "\nLastname: " +userDTO4.getLastname() +
		 			"\nLogin: " + userDTO4.getLogin() + "\nPassword: " + userDTO4.getPassword() + "\nE-mail: " + userDTO4.getEmail() +
		 			"\nWeight: " + userDTO4.getWeight() + "\nAge: " + userDTO4.getAge() + "\nGender: " + userDTO4.getGender() + "\nUserRole: " + userDTO4.getRoleName());
		 	Log4jWrapper.get().info("User's groups:  ");
		 	for (GroupDTO group : userDTO4.getGroups()) {
		 		Log4jWrapper.get().info(group.getName() + "     ");
		 	}
		 	UserProfileServiceImpl.getInstance().test_delete(userDTO4);
		 	Log4jWrapper.get().info("Delete user from database for test");
	 	} catch (SQLException | JDBCDriverException | TransactionException | QueryNotFoundException | DataBaseReadingException e) {
	 		Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
	
	//You didn't enter user
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void testInsertUserNull() {
		UserDTO userDTO3 = null;
		try {
			UserProfileServiceImpl.getInstance().insert(userDTO3);
		} catch (SQLException | JDBCDriverException	| TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
					
	@Test
	public void testLockUser() {
		UserDTO userDTO5;
		try {
			userDTO5 = UserProfileServiceImpl.getInstance().get("Login 2");
			userDTO5.setIsDisabled("true");
			UserProfileServiceImpl.getInstance().lock(userDTO5, true);
			assertEquals("true", userDTO5.getIsDisabled());
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
	
	//You didn't enter user
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void testLockUserNull() {
		UserDTO userDTO5 = null;
		try {
			UserProfileServiceImpl.getInstance().lock(userDTO5, true);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			Log4jWrapper.get().error(TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
}
