package edu.softserveinc.healthbody.dto;

/**
 * Class edu.softserveinc.healthbody.dto.UserCompetitionsDTO is POJO. Used by:
 * edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl
 * edu.softserveinc.healthbody.services.impl.GroupServiceImpl to transfer data
 * about groups, users, users in groups.
 * 
 * @version 9 August 2016
 * 
 */
public class UserCompetitionsDTO {

	private String idUserCompetition;
	private String idUser;
	private String idCompetition;
	private String userScore;
	private String idAwards;
	private String timeReceived;

	/**
	 * Default constructor of
	 * edu.softserveinc.healthbody.dto.UserCompetitionsDTO
	 */
	public UserCompetitionsDTO() {
	}

	/**
	 * Constructor of edu.softserveinc.healthbody.dto.UserCompetitionsDTO
	 */
	public UserCompetitionsDTO(String idUserCompetition, String idUser, String idCompetition, String userScore,
			String idAwards, String timeReceived) {
		this.idUserCompetition = idUserCompetition;
		this.idUser = idUser;
		this.idCompetition = idCompetition;
		this.userScore = userScore;
		this.idAwards = idAwards;
		this.timeReceived = timeReceived;
	}

	/**
	 * Getters
	 */
	public String getIdUserCompetition() {
		return idUserCompetition;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getIdCompetition() {
		return idCompetition;
	}

	public String getUserScore() {
		return userScore;
	}

	public String getIdAwards() {
		return idAwards;
	}

	public String getTimeReceived() {
		return timeReceived;
	}
	
	/**
	 * Setters
	 */
	public void setIdUserCompetition(String idUserCompetition) {
		this.idUserCompetition = idUserCompetition;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public void setIdCompetition(String idCompetition) {
		this.idCompetition = idCompetition;
	}

	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}

	public void setIdAwards(String idAwards) {
		this.idAwards = idAwards;
	}

	public void setTimeReceived(String timeReceived) {
		this.timeReceived = timeReceived;
	}
}
