package edu.softserveinc.healthbody.constants;

//for method createInstance
public class Constants {
	
	public static class UserCard {
		public static final int ID = 0;
		public static final int LOGIN = 1;
		public static final int PASSWORD = 2;
		public static final int FIRSTNAME = 3;
		public static final int LASTNAME = 4;
		public static final int MAIL = 5;
		public static final int AGE = 6;
		public static final int WEIGHT = 7;
		public static final int GENDER = 8;
		public static final int HEALTH = 9;
		public static final int AVATAR = 10;
		public static final int GOOGLEAPI = 11;
		public static final int IDROLE = 12;
		public static final int STATUS = 13;
		public static final int ISDISABLED = 14;
	}
	
	public static class CompetitionCard {
		public static final int ID = 0;
		public static final int NAME = 1;
		public static final int DESCRIPTION = 2;
		public static final int START = 3;
		public static final int FINISH = 4;
		public static final int IDCRITERIA = 5;
	}
	
	public static class AwardCard {
		public static final int ID = 0;
		public static final int NAME =1;
	}
	
	public static class CompetitionsViewCard {
		public static final int ID = 0;
		public static final int NAME = 1;
		public static final int DESCRIPTION = 2;
		public static final int START = 3;
		public static final int FINISH = 4;
		public static final int USERSCOUNT = 5;
	}
	
	public static class CriteriaCard {
		public static final int ID = 0;
		public static final int NAME = 1;
		public static final int METRICS = 2;
		public static final int GETGOOGLE = 3;
	}
	
	public static class GroupCard {
		public static final int ID = 0;
		public static final int NAME = 1;
		public static final int COUNT = 2;
		public static final int DESCRIPTION = 3;
		public static final int SCOREGROUP = 4;
		public static final int STATUS = 5;
	}
	
	public static class GroupCompetitionsCard {
		public static final int ID = 0;
		public static final int IDGROUP = 1;
		public static final int IDCOMPETITION = 2;
	}
	
	public static class GroupUserViewCard {
		public static final int ID = 0;
		public static final int NAME = 1;
		public static final int COUNT = 2;
		public static final int DESCRIPTION = 3;
		public static final int SCOREGROUP = 4;
		public static final int STATUS = 5;
		public static final int USERS = 6;
		public static final int FIRSTNAME = 7;
		public static final int LASTNAME = 8;
	}
	
	public static class MetaDataCard {
		public static final int ID = 0;
		public static final int LASTSYNCH = 1;
	}
	
	public static class RoleCard {
		public static final int ID = 0;
		public static final int NAME = 1;
		public static final int DESCRIPTION = 2;
	}
	
	public static class UserCompetitionsCard {
		public static final int ID = 0;
		public static final int IDUSER = 1;
		public static final int IDCOMPETITION = 2;
		public static final int USERSCORE = 3;
		public static final int IDAWARD = 4;
		public static final int TIMERECEIVED = 5;
	}
	
	public static class UserGroupCard {
		public static final int ID = 0;
		public static final int IDUSER = 1;
		public static final int IDGROUP = 2;
	}
	
	public static class UsersViewCard {
		public static final int ID = 0;
		public static final int FIRSTNAME = 1;
		public static final int LASTNAME = 2;
		public static final int LOGIN = 3;
		public static final int PASSWORD = 4;
		public static final int MAIL = 5;
		public static final int AGE = 6;
		public static final int WEIGHT = 7;
		public static final int GENDER = 8;
		public static final int AVATAR = 9;
		public static final int ROLENAME = 10;
		public static final int HEALTH = 11;
		public static final int GOOGLEAPI = 12;
		public static final int STATUS = 13;
		public static final int SCORE = 14;
	}
}
