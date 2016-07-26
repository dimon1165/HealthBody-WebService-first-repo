package edu.softserveinc.healthbody.services;

public class KeysForFilters {

	public enum CompetitionsServiceKeys {

		NAME("name"),
		COUNT("count"),
		START_DATE("startDate"),
		FINISH_DATE("finishDate"),
		GROUPS("groups"),
		LOGINS("logins");

		private String keys;

		CompetitionsServiceKeys(final String keys) {
			this.keys = keys;
		}

		@Override
		public String toString() {
			return keys;
		}

	}
	
	public enum UsersServiceKeys {

		FIRST_NAME("firstname"),
		LAST_NAME("lastname"),
		LOGIN("login"),
		PASSWORD("password"),
		EMAIL("email"),
		AGE("age"),
		WEIGHT("weight"),
		GENDER("gender"),
		PHOTO_URL("photoURL"),
		ROLE_NAME("roleName"),
		STATUS("status"),
		SCORE("score"),
		GROUPS("groups");

		private String keys;

		UsersServiceKeys(final String keys) {
			this.keys = keys;
		}

		@Override
		public String toString() {
			return keys;
		}

	}


}
