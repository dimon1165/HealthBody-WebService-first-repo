package edu.softserveinc.healthbody.entity;

import edu.softserveinc.healthbody.dao.IBasicDao.DaoQueries;

public class UserGroupView implements IEntity{

		public static enum UserGroupViewFields {
			USERSGROUPS_ID("usersgroups.id_user_group"),
			USERS_ID("usersgroups.id_user"),
			GROUPS_ID("usersgroups.id_group");
	        private String field;

	        private UserGroupViewFields(String field) {
	            this.field = field;
	        }

	        @Override
	        public String toString() {
	            return field;
	        }
	    }

		public static enum UserGroupViewQueries {
	        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT users.id_user, users.login, roles.name FROM users INNER JOIN roles ON users.id_role = roles.id_role WHERE users.id_user = ?;"),
	        GET_ID_BY_FIELDS(DaoQueries.GET_ID_BY_FIELDS, "SELECT usersgroups.id_user_group FROM usersgroups, users, groups "
	        		+ "WHERE usersgroups.id_user = ? AND usersgroups.id_group = ?;"),	
	        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE usersgroup SET ? = ? WHERE ? = ?;"),
	        GET_ALL(DaoQueries.GET_ALL, "SELECT users.id_user, users.login, roles.name FROM users INNER JOIN roles ON users.id_role = roles.id_role;");
	        private DaoQueries daoQuery;
	        private String query;

	        private UserGroupViewQueries(DaoQueries daoQuery, String query) {
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

	    private String idUserGroup;
	    private String idUser;
		private String idGroup;
		private Boolean memberGgoup;

		public UserGroupView(String idUserGroup, String idUser, String idGroup, Boolean memberGgoup) {
			this.idUserGroup = idUserGroup;
			this.idUser = idUser;
			this.idGroup = idGroup;
			this.memberGgoup = memberGgoup;
		}

		// Setters
		public void setIdUserGroup(String idUserGroup) {
			this.idUserGroup = idUserGroup;
		}

		public void setIdUser(String idUser) {
			this.idUser = idUser;
		}

		public void setIdGroup(String idGroup) {
			this.idGroup = idGroup;
		}

		// Getters
		public void setMemberGgoup(Boolean memberGgoup) {
			this.memberGgoup = memberGgoup;
		}

		public String getIdUserGroup() {
			return idUserGroup;
		}

		public String getIdUser() {
			return idUser;
		}

		public String getIdGroup() {
			return idGroup;
		}

		public Boolean getMemberGgoup() {
			return memberGgoup;
		}

		@Override
		public String getId() {
			return idUserGroup;
		}

	
}
