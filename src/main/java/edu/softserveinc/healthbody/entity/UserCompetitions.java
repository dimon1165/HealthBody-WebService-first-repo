package edu.softserveinc.healthbody.entity;

public class UserCompetitions implements IEntity {
	
	private String idUserCompetition;
    private String idUser;
	private String idCompetition;
	private Integer userScore;
	private String idAwards;
	private String timeReceived;
	
	public UserCompetitions(String idUserCompetition, String idUser, String idCompetition, Integer userScore,
			String idAwards, String timeReceived) {		
		this.idUserCompetition = idUserCompetition;
		this.idUser = idUser;
		this.idCompetition = idCompetition;
		this.userScore = userScore;
		this.idAwards = idAwards;
		this.timeReceived = timeReceived;
	}

	// Setters
	public void setIdUserCompetition(String idUserCompetition) {
		this.idUserCompetition = idUserCompetition;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public void setIdCompetition(String idCompetition) {
		this.idCompetition = idCompetition;
	}

	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}

	public void setIdAwardsTypes(String idAwards) {
		this.idAwards = idAwards;
	}

	public void setTimeReceived(String timeReceived) {
		this.timeReceived = timeReceived;
	}	
	
	// Getters
	public String getIdUserCompetition() {
		return idUserCompetition;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getIdCompetition() {
		return idCompetition;
	}

	public Integer getUserScore() {
		return userScore;
	}

	public String getIdAwards() {
		return idAwards;
	}

	public String getTimeReceived() {
		return timeReceived;
	}
	
	@Override
	public String getId() {
		return getIdUserCompetition();
	}
}
