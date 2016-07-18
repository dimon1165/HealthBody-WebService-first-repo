package edu.softserveinc.healthbody.entity;

public class UserCompetitions implements IEntity {
	
	private Integer idUserCompetition;
    private Integer idUser;
	private Integer idCompetition;
	private Integer userScore;
	private Integer idAwards;
	private String timeReceived;
	
	public UserCompetitions(Integer idUserCompetition, Integer idUser, Integer idCompetition, Integer userScore,
			Integer idAwards, String timeReceived) {
		
		this.idUserCompetition = idUserCompetition;
		this.idUser = idUser;
		this.idCompetition = idCompetition;
		this.userScore = userScore;
		this.idAwards = idAwards;
		this.timeReceived = timeReceived;
	}

	//setters
	public void setIdUserCompetition(Integer idUserCompetition) {
		this.idUserCompetition = idUserCompetition;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setIdCompetition(Integer idCompetition) {
		this.idCompetition = idCompetition;
	}

	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}

	public void setIdAwardsTypes(Integer idAwards) {
		this.idAwards = idAwards;
	}

	public void setTimeReceived(String timeReceived) {
		this.timeReceived = timeReceived;
	}
	
	
	@Override
	public Integer getId() {
		return getIdUserCompetition();
	}
	
	//getters
	public Integer getIdUserCompetition() {
		return idUserCompetition;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public Integer getIdCompetition() {
		return idCompetition;
	}

	public Integer getUserScore() {
		return userScore;
	}

	public Integer getIdAwards() {
		return idAwards;
	}

	public String getTimeReceived() {
		return timeReceived;
	}

	
	

}
