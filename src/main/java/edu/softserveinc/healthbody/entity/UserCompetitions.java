package edu.softserveinc.healthbody.entity;

public class UserCompetitions implements IEntity {
	
	private Integer idUserCompetition;
    private Integer idUser;
	private Integer idCompetition;
	private Integer userScore;
	private Integer idAwards;
	private String timeReceived;
	
	public UserCompetitions(final Integer idUserCompetition, final Integer idUser,
			final Integer idCompetition, final Integer userScore,
			final Integer idAwards, final String timeReceived) {
		this.idUserCompetition = idUserCompetition;
		this.idUser = idUser;
		this.idCompetition = idCompetition;
		this.userScore = userScore;
		this.idAwards = idAwards;
		this.timeReceived = timeReceived;
	}

	//setters
	public final void setIdUserCompetition(final Integer idUserCompetition) {
		this.idUserCompetition = idUserCompetition;
	}

	public final void setIdUser(final Integer idUser) {
		this.idUser = idUser;
	}

	public final void setIdCompetition(final Integer idCompetition) {
		this.idCompetition = idCompetition;
	}

	public final void setUserScore(final Integer userScore) {
		this.userScore = userScore;
	}

	public final void setIdAwardsTypes(final Integer idAwards) {
		this.idAwards = idAwards;
	}

	public final void setTimeReceived(final String timeReceived) {
		this.timeReceived = timeReceived;
	}
	
	@Override
	public final Integer getId() {
		return getIdUserCompetition();
	}
	
	//getters
	public final Integer getIdUserCompetition() {
		return idUserCompetition;
	}

	public final Integer getIdUser() {
		return idUser;
	}

	public final Integer getIdCompetition() {
		return idCompetition;
	}

	public final Integer getUserScore() {
		return userScore;
	}

	public final Integer getIdAwards() {
		return idAwards;
	}

	public final String getTimeReceived() {
		return timeReceived;
	}
}
