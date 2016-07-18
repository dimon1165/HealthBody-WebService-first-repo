package edu.softserveinc.healthbody.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DBPopulateManager;
import edu.softserveinc.healthbody.db.DataSourceRepository;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;

/**
 * Servlet implementation class DatabaseCreationServlet
 */
@WebServlet("/PleaseCreateDatabase")
public class DatabaseCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DatabaseCreationServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection con;
//		try {
//			ds = new DataSource(DriverRepository.getInstance().getPostgresDriver(), url, user, password);
//			con = ConnectionManager.getInstance(ds).getConnection();
//		} catch (JDBCDriverException e) {
//			e.printStackTrace(out);
//			return;
//		}
//		try (Statement st = con.createStatement()) {
//			DBCreationManager.getInstance().createDatabase(st, database);
//		} catch (SQLException e) {
//			e.printStackTrace(out);
//			return;
//		}
		try {
//			ds = new DataSource(DriverRepository.getInstance().getPostgresDriver(), url + database, user, password);
//			con = ConnectionManager.getInstance(ds).getConnection();
			con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresOpenShift()).getConnection();
		} catch (JDBCDriverException e) {
			e.printStackTrace(out);
			out.flush();
			return;
		}
		try (Statement st = con.createStatement()) {
			for (String query : DBCreationManager.getInstance().getListOfQueries()) {
				DBCreationManager.getInstance().createTable(st, query);
			}
		} catch (SQLException e) {
			e.printStackTrace(out);
			out.flush();
			return;
		}
		DBPopulateManager.getInstance().populateUsersTable();
		DBPopulateManager.getInstance().populateGroupsTable();
		DBPopulateManager.getInstance().populateUserGroupsTable();
		DBPopulateManager.getInstance().populateAwardsTable();
		DBPopulateManager.getInstance().populateCompetitionsTable();
		DBPopulateManager.getInstance().populateCriteriaTable();
		DBPopulateManager.getInstance().populateGroupCompetitionsTable();
		DBPopulateManager.getInstance().populateMetaDataTable();
		DBPopulateManager.getInstance().populateRolesTable();
		DBPopulateManager.getInstance().populateUserCompetitionsTable();

		out.append("Database successfully created and populated at: ")
				.append(request.getContextPath());
		out.flush();
	}

}
