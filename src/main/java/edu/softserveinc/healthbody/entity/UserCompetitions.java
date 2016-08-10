package edu.softserveinc.healthbody.entity;

	/**
	 * Class  edu.softserveinc.healthbody.entity.UserCompetitions is POJO.
	 * Called by: 
	 * edu.softserveinc.healthbody.dao.UserCompetitionsDao - create instance
	 * Return info about all users in particular competition
	 *
	 * @version 9 August 2016 	
	 * 
	 * */
public class UserCompetitions implements IEntity {
	
	private String idUserCompetition;
    private String idUser;
	private String idCompetition;
	private Integer userScore;
	private String idAwards;
	private String timeReceived;
	
	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.UserCompetitions  
     */
	public UserCompetitions(final String idUserCompetition, final String idUser, final String idCompetition, 
			final Integer userScore, final String idAwards, final String timeReceived) {		
		this.idUserCompetition = idUserCompetition;
		this.idUser = idUser;
		this.idCompetition = idCompetition;
		this.userScore = userScore;
		this.idAwards = idAwards;
		this.timeReceived = timeReceived;
	}

	 /**
     * Setters  
     */
	public void setIdUserCompetition(final String idUserCompetition) {
		this.idUserCompetition = idUserCompetition;
	}

	public void setIdUser(final String idUser) {
		this.idUser = idUser;
	}

	public void setIdCompetition(final String idCompetition) {
		this.idCompetition = idCompetition;
	}

	public final void setUserScore(final Integer userScore) {
		this.userScore = userScore;
	}

	public void setIdAwardsTypes(final String idAwards) {
		this.idAwards = idAwards;
	}

	public final void setTimeReceived(final String timeReceived) {
		this.timeReceived = timeReceived;
	}	
	
	 /**
     * Getters  
     */
	public final String getIdUserCompetition() {
		return idUserCompetition;
	}

	public final String getIdUser() {
		return idUser;
	}

	public final String getIdCompetition() {
		return idCompetition;
	}

	public final Integer getUserScore() {
		return userScore;
	}

	public final String getIdAwards() {
		return idAwards;
	}

	public final String getTimeReceived() {
		return timeReceived;
	}
	
	@Override
	public final String getId() {
		return getIdUserCompetition();
	}

}
