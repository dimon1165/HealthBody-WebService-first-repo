package edu.softserveinc.healthbody.constants;

import edu.softserveinc.healthbody.dao.IBasicDao.DaoQueries;

public class DaoStatementsConstant {

	public enum UserDBQueries {
        INSERT(DaoQueries.INSERT, "INSERT INTO users (id_user, login, password, firstname, lastname, \"e-mail\", age, weight, gender, "
        		+ "health, avatar, google_field, id_role, status, isdisabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"),
        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_user, login, password, firstname, lastname, \"e-mail\", age, weight, gender, "
        		+ "health, avatar, google_field, id_role, status, isdisabled FROM users WHERE id_user = ?;"),
        GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_user, login, password, firstname, lastname, \"e-mail\", age, weight, "
        		+ "gender, health, avatar, google_field, id_role, status, isdisabled FROM users WHERE ? = ?;"),
        GET_BY_FIELD_NAME(DaoQueries.GET_BY_FIELD_NAME, "SELECT id_user, login, password, firstname, lastname, \"e-mail\", "
        		+ "age, weight, gender, health, avatar, google_field, id_role, status, isdisabled FROM users WHERE login = ?;"),
        GET_ALL(DaoQueries.GET_ALL, "SELECT id_user, login, password, firstname, lastname, \"e-mail\", age, weight, gender, "
        		+ "health, avatar, google_field, id_role, status FROM users WHERE isdisabled = 'false';"),
        UPDATE(DaoQueries.UPDATE, "UPDATE users SET login = ?, password = ?, firstname = ?, lastname = ?, \"e-mail\" = ?, age = ?, "
        		+ "weight = ?, gender = ?, health = ?, avatar = ?  WHERE login = ?"),
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE users SET ? = ? WHERE ? = ?;"),
        ISDISABLED(DaoQueries.ISDISABLED, "UPDATE users SET isDisabled = ? WHERE login = ?"),
        DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE FROM users WHERE id_user = ?;"),
        DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE FROM users WHERE login = ?;");
		private DaoQueries daoQuery;
		private String query;
		
		UserDBQueries(final DaoQueries daoQuery, final String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}

		public DaoQueries getDaoQuery() {
			return daoQuery;
		}

		@Override
		public String toString() {
			return query;
		}
	}

	public static enum GroupDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO groups (id_group, name, count, description, scoreGroup, status) VALUES (?, ?, ?, ?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_group, name, count, description, scoreGroup, status FROM groups WHERE id_group = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_group, name, count, description, scoreGroup, status FROM groups WHERE ? = ?;"),
		GET_BY_FIELD_NAME(DaoQueries.GET_BY_FIELD_NAME, "SELECT id_group, name, count, description, scoreGroup, status FROM groups WHERE name = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT id_group, name, count, description, scoreGroup, status FROM groups;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE groups SET ? = ? WHERE ? = ?;"),
		UPDATE(DaoQueries.UPDATE, "UPDATE groups SET count = ?, description = ?, scoreGroup = ? WHERE name = ?"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE FROM groups WHERE id_group = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE FROM groups WHERE name = ?;");
		private DaoQueries daoQuery;
		private String query;
		
		GroupDBQueries(final DaoQueries daoQuery, final String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		
		@Override
		public String toString() {
			return query;
		}
	}

	public static enum UserGroupQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO usergroups (id_user_group, id_user, id_group) VALUES (?, ?, ?);"),
        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_user_group, id_user, id_group FROM usergroups WHERE id_user = ?;"),
        GET_ID_BY_FIELDS(DaoQueries.GET_ID_BY_FIELDS, "SELECT usergroups.id_user_group FROM usergroups WHERE usergroups.id_user = ? AND usergroups.id_group = ?;"),		
        GET_ALL(DaoQueries.GET_ALL, "SELECT usergroups.id_user_group, usergroups.id_user, usergroups.id_groups, usergroups.member_group FROM usergroups;"),
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE usergroups SET ? = ? WHERE ? = ?;"),
        DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE FROM usergroups WHERE id_user = ?;");
		private DaoQueries daoQuery;
        private String query;

        UserGroupQueries(final DaoQueries daoQuery, final String query) {
        	this.daoQuery = daoQuery;
            this.query = query;
        }

        public DaoQueries getDaoQuery() {
            return daoQuery;
        }

        @Override
        public String toString() {
            return query;
        }
    }

	public static enum CompetitionDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO competitions (id_competition, name, description, start, finish, id_criteria) VALUES (?, ?, ?, ?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT id_competition, name, description, start_date, finish_date, id_criteria FROM competitions WHERE id_competition = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_competition, name, description, start_date, finish_date, id_criteria FROM competitions WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL,	"SELECT id_competition, name, description, start_date, finish_date, id_criteria FROM competitions;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD,	"UPDATE competitions SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE FROM competitions WHERE id_competition = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE FROM competitions WHERE name = ?;");
		private DaoQueries daoQuery;
		private String query;

		CompetitionDBQueries(final DaoQueries daoQuery, final String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}

		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		
		@Override
		public String toString() {
			return query;
		}
	}

	public static enum MetaDataDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO metadata (id_metadata, last_synch) VALUES (?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_metadata, last_synch FROM metadata WHERE id_metadata = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_metadata, last_synch FROM metadata WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT * FROM metadata;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE metadata SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE FROM metadata WHERE id_metadata = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE FROM metadata WHERE last_synch = ?;");
		private DaoQueries daoQuery;
		private String query;
		
		MetaDataDBQueries(final DaoQueries daoQuery, final String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		
		@Override
		public String toString() {
			return query;
		}
	}

	public static enum AwardDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO awards (id_award, name) VALUES (?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_award, name FROM awards WHERE id_award = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_award, name FROM awards WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT id_award, name FROM awards;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE awards SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE FROM awards WHERE id_award = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE FROM awards WHERE name = ?;");
		private DaoQueries daoQuery;
		private String query;
		
		AwardDBQueries(final DaoQueries daoQuery, final String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		
		@Override
		public String toString() {
			return query;
		}

}	public static enum GroupCompetitionsDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO groupcompetitions (id_group_competition, id_group, id_competition) VALUES (?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT id_group_competition, id_group, id_competition FROM groupcompetitions WHERE id_group_competition = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_group_competition, id_group, id_competition FROM groupcompetitions WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL,	"SELECT id_group_competition, id_group, id_competition FROM groupcompetitions;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD,	"UPDATE groupcompetitions SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE FROM groupcompetitions WHERE id_group_competition = ?;");
		private DaoQueries daoQuery;
		private String query;

		GroupCompetitionsDBQueries(final DaoQueries daoQuery, final String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}

		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		
		@Override
		public String toString() {
			return query;
		}
	}

	public static enum RoleDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO roles (id_role, name, description) VALUES (?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_role, name, description FROM roles WHERE id_role = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_role, name, description FROM roles WHERE ? = ?;"),
		GET_BY_FIELD_NAME(DaoQueries.GET_BY_FIELD_NAME, "SELECT id_role, name, description FROM roles WHERE name = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT id_role, name, description FROM roles;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE roles SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE FROM roles WHERE id_role = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE FROM roles WHERE name = ?;");
		private DaoQueries daoQuery;
		private String query;
		
		RoleDBQueries(final DaoQueries daoQuery, final String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		
		@Override
		public String toString() {
			return query;
		}
	}
	
    public static enum UserCompetitionsDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO usercompetitions (id_user_competition, id_user, id_competition, user_score, id_award, time_received) VALUES (?, ?, ?, ?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT id_user_competition, id_user, id_competition, user_score, id_award, time_received FROM usercompetitions WHERE id_user_competition = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_user_competition, id_user, id_competition, user_score, id_award, time_received FROM usercompetitions WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL,	"SELECT id_user_competition, id_user, id_competition, user_score, id_award, time_received FROM usercompetitions;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD,	"UPDATE usercompetitions SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE FROM usercompetitions WHERE id_user = ?;");
		private DaoQueries daoQuery;
		private String query;

		UserCompetitionsDBQueries(final DaoQueries daoQuery, final String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		
		@Override
		public String toString() {
			return query;
		}
	}

    public static enum CriteriaDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO criteria (id_criteria, name, metrics, get_google) VALUES (?, ?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_criteria, name, metrics, get_google FROM criteria WHERE id_criteria = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_criteria, name, metrics, get_google FROM criteria WHERE ? = ?;"),
		GET_BY_FIELD_NAME(DaoQueries.GET_BY_FIELD_NAME, "SELECT id_criteria, name, metrics, get_google FROM criteria WHERE name = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT id_criteria, name, metrics, get_google FROM criteria;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE criteria SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE FROM criteria WHERE id_criteria = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE FROM criteria WHERE name = ?;");
		private DaoQueries daoQuery;
		private String query;
		
		CriteriaDBQueries(final DaoQueries daoQuery, final String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		
		@Override
		public String toString() {
			return query;
		}
	}
    
    public enum UserGroupViewQueries {
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT users.id_user, users.login, groups.name FROM users INNER JOIN groups ON users.id_group = groups.id_group "
				+ "WHERE users.id_user = ?;"),
        GET_ID_BY_FIELDS(DaoQueries.GET_ID_BY_FIELDS, "SELECT usergroups.id_user_group FROM usergroups, users, groups WHERE usergroups.id_user = ? AND "
				+"usergroups.id_group = ?;"),		
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE usergroups SET ? = ? WHERE ? = ?;"),
        GET_ALL(DaoQueries.GET_ALL, "SELECT users.id_user, users.login, roles.name FROM users INNER JOIN roles ON users.id_role = roles.id_role;");
        private DaoQueries daoQuery;
        private String query;

        UserGroupViewQueries(final DaoQueries daoQuery, final String query) {
        	this.daoQuery = daoQuery;
            this.query = query;
        }

        public DaoQueries getDaoQuery() {
            return daoQuery;
        }

        @Override
        public String toString() {
            return query;
        }
	}
    
    public enum CompetitionsViewQueries {
		GET_ALL_ACTIVE("SELECT competitions.id_competition, competitions.name, competitions.description, competitions.start, competitions.finish, COUNT(usercompetitions.id_user)"
				+ " FROM competitions"
				+ " LEFT OUTER JOIN usercompetitions ON competitions.id_competition = usercompetitions.id_competition"
				+ " WHERE competitions.finish >= NOW()"
				+ " GROUP BY competitions.id_competition, competitions.name, competitions.start, competitions.finish"
				+ ";"),
		GET_ALL_BY_USER("SELECT DISTINCT competitions.id_competition, competitions.name, competitions.description, competitions.start,competitions.finish, user_competition_count" 
				+ " FROM competitions"
				+ " LEFT OUTER JOIN usercompetitions ON competitions.id_competition = usercompetitions.id_competition" 
				+ " JOIN users ON usercompetitions.id_user = users.id_user" 
				+ " JOIN"
					+ " (SELECT id_competition, COUNT(usercompetitions.id_user_competition) AS user_competition_count" 
					+ " FROM usercompetitions JOIN users ON usercompetitions.id_user = users.id_user"
					+ " GROUP BY id_competition) AS countselect" 
				+ " ON usercompetitions.id_competition = countselect.id_competition"
				+ " WHERE users.login = ?"
				+ " ;"),
		GET_ALL_ACTIVE_BY_USER("SELECT DISTINCT competitions.id_competition, competitions.name, competitions.description,"
				+ " competitions.start, competitions.finish, user_competition_count"
				+ " FROM competitions"
				+ " JOIN usercompetitions ON competitions.id_competition = usercompetitions.id_competition"
				+ " JOIN users ON usercompetitions.id_user = users.id_user"
				+ " JOIN" 
					+ " (SELECT id_competition, COUNT(usercompetitions.id_user_competition) AS user_competition_count"
					+ " FROM usercompetitions JOIN users ON usercompetitions.id_user = users.id_user"
					+ " GROUP BY id_competition) AS countselect"
				+ " ON usercompetitions.id_competition = countselect.id_competition"
				+ " WHERE competitions.finish >= NOW()"
				+ " AND users.login = ?"
				+ " ;"),
		GET_ALL("SELECT competitions.id_competition, competitions.name, competitions.description, competitions.start, competitions.finish, COUNT(usercompetitions.id_user)"
				+ " FROM competitions"
				+ " LEFT OUTER JOIN usercompetitions ON competitions.id_competition = usercompetitions.id_competition"
				+ " GROUP BY competitions.id_competition, competitions.name, competitions.start, competitions.finish"
				+ " ;");
		
		private String query;
		
		CompetitionsViewQueries(final String query) {
			this.query = query;
		}

		public CompetitionsViewQueries getDaoQuery() {
            return this;
        }
		
		@Override
		public String toString() {
			return query;
		}
	}
    
    public enum UsersViewQueries {
 		GET_ALL("SELECT users.id_user, users.login, users.password, users.firstname, users.lastname, users.\"e-mail\", users.age, "
 				+ "users.weight, users.gender, users.health, users.avatar, users.google_field, roles.name, users.status, SUM(usercompetitions.user_score)"
 				+ " FROM users"
 				+ " JOIN roles ON users.id_role = roles.id_role"
 				+ " LEFT OUTER JOIN usercompetitions ON users.id_user = usercompetitions.id_user"
 				+ " GROUP BY users.id_user, users.firstname, users.lastname, users.login, roles.name"
 				+ " ;");
 		
 		private String query;
 		
 		UsersViewQueries(final String query) {
 			this.query = query;
 		}

 		public UsersViewQueries getDaoQuery() {
             return this;
         }
 		
 		@Override
 		public String toString() {
 			return query;
 		}
 	}
    
    
}
 