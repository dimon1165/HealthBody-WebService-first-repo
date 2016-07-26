package edu.softserveinc.healthbody.dto;

import java.util.List;

public class UserCompetitionsDTO {
	
	private String idUserCompetition;
	private String login;
	private List<String> competitions;
	private String userScore;
	private String awardsName;
	private String timeReceivedAward;
	
	public UserCompetitionsDTO() { }
	
	public UserCompetitionsDTO(final String idUserCompetition, final String login, final List<String> competitions, 
			final String userScore, final String awardsName, final String timeReceivedAward) {
		this.idUserCompetition = idUserCompetition;
		this.login = login;
		this.competitions = competitions;
		this.userScore = userScore;
		this.awardsName = awardsName;
		this.timeReceivedAward = timeReceivedAward;
	}

	// Getters
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
