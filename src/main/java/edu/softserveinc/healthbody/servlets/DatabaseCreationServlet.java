package edu.softserveinc.healthbody.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

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
 * Servlet implementation class DatabaseCreationServlet.
 */
@WebServlet("/PleaseCreateDatabase")
public class DatabaseCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DatabaseCreationServlet() {
	}

	@Override
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection conn;
		try {
			conn = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresDatabase())
					.getConnection();
		} catch (JDBCDriverException e) {
			e.printStackTrace(out);
			out.flush();
			return;
		}
		try {
			DBCreationManager.getInstance().dropAllDatabaseTables(conn);
			DBCreationManager.getInstance().createDatabaseTables(conn);
			DBPopulateManager.getInstance().populateDatabaseTables(conn);
		} catch (SQLException e) {
			e.printStackTrace(out);
			out.flush();
			return;
		}

		out.append("Database successfully created and populated at: ")
				.append(request.getContextPath());
		out.flush();
	}

}
