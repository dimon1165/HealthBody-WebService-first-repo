package edu.softserveinc.healthbody.dto;

import java.util.List;

	/**
	 * Class  edu.softserveinc.healthbody.dto.UserCompetitionsDTO is POJO.
	 * Used by: 
	 * edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl
	 * edu.softserveinc.healthbody.services.impl.GroupServiceImpl
	 * to transfer data about groups, users, users in groups.
	 *  
	 * @version 9 August 2016 	
	 * 
	 * */
public class UserCompetitionsDTO {
	
	private String idUserCompetition;
	private String login;
	private List<String> competitions;
	private String userScore;
	private String awardsName;
	private String timeReceivedAward;
	
	/**
	 *  Default constructor of edu.softserveinc.healthbody.dto.UserCompetitionsDTO   
	 */
	public UserCompetitionsDTO() { }
	
	/**
	 *  Constructor of edu.softserveinc.healthbody.dto.UserCompetitionsDTO   
	 */
	public UserCompetitionsDTO(final String idUserCompetition, final String login, final List<String> competitions, 
			final String userScore, final String awardsName, final String timeReceivedAward) {
		this.idUserCompetition = idUserCompetition;
		this.login = login;
		this.competitions = competitions;
		this.userScore = userScore;
		this.awardsName = awardsName;
		this.timeReceivedAward = timeReceivedAward;
	}

	/**
	 * Getters   
	 */
	public final String getIdUserCompetition() {
		return idUserCompetition;
	}
	
	public final String getLogin() {
		return login;
	}

	public final List<String> getCompetitions() {
		return competitions;
	}

	public final String getUserScore() {
		return userScore;
	}

	public final String getAwardsName() {
		return awardsName;
	}

	public final String getTimeReceivedAward() {
		return timeReceivedAward;
	}
}
