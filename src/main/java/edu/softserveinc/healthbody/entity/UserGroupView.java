package edu.softserveinc.healthbody.entity;

import edu.softserveinc.healthbody.dao.IBasicDao.DaoQueries;

public class UserGroupView implements IEntity {

		public enum UserGroupViewFields {
			USERSGROUPS_ID("usersgroups.id_user_group"),
			USERS_ID("usersgroups.id_user"),
			GROUPS_ID("usersgroups.id_group");
	    private String field;

		UserGroupViewFields(final String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return field;
		}
	}

		public enum UserGroupViewQueries {
	        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT users.id_user, users.login, roles.name FROM users INNER JOIN roles ON users.id_role = roles.id_role WHERE users.id_user = ?;"),
	        GET_ID_BY_FIELDS(DaoQueries.GET_ID_BY_FIELDS, "SELECT usersgroups.id_user_group FROM usersgroups, users, groups "
	        		+ "WHERE usersgroups.id_user = ? AND usersgroups.id_group = ?;"),	
	        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE usersgroup SET ? = ? WHERE ? = ?;"),
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

	    private String idUserGroup;
	    private String idUser;
		private String idGroup;
		private Boolean memberGgoup;

		public UserGroupView(final String idUserGroup, final String idUser, final String idGroup, final Boolean memberGgoup) {
			this.idUserGroup = idUserGroup;
			this.idUser = idUser;
			this.idGroup = idGroup;
			this.memberGgoup = memberGgoup;
		}

		// Setters
		public final void setIdUserGroup(final String idUserGroup) {
			this.idUserGroup = idUserGroup;
		}

		public final void setIdUser(final String idUser) {
			this.idUser = idUser;
		}

		public final void setIdGroup(final String idGroup) {
			this.idGroup = idGroup;
		}

		// Getters
		public final void setMemberGgoup(final Boolean memberGgoup) {
			this.memberGgoup = memberGgoup;
		}

		public final String getIdUserGroup() {
			return idUserGroup;
		}

		public final String getIdUser() {
			return idUser;
		}

		public final String getIdGroup() {
			return idGroup;
		}

		public final Boolean getMemberGgoup() {
			return memberGgoup;
		}

		@Override
		public final String getId() {
			return idUserGroup;
		}

	
}
