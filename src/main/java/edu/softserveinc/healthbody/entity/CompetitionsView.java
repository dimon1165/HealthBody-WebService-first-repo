package edu.softserveinc.healthbody.entity;

	/**
	 * Class  edu.softserveinc.healthbody.entity.CompetiotionsView is POJO. 
	 * Called by:
	 * edu.softserveinc.healthbody.dao.CompetitionDao
	 * Return info about Start, finish dates, users count in competitions
	 * and info about competitions.
	 *  
	 * @version 9 August 2016 	
	 * 
	 * */
public class CompetitionsView implements IEntity {
	
	private String idCompetition;
	private String name;
	private String description;
	private String start;
	private String finish;
	private Integer usersCount;

	
	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.CompetitionsView  
     */
	public CompetitionsView(final String idCompetition, final String name, final String description, final String start,
			final String finish, final Integer usersCount) {
		this.idCompetition = idCompetition;
		this.name = name;
		this.description = description;
		this.start = start;
		this.finish = finish;
		this.usersCount = usersCount;
	}
	
	 /**
     * Setters  
     */
	public final void setIdCompetitions(final String idCompetition) {
		this.idCompetition = idCompetition;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	public final void setDescription(final String description) {
		this.description = description;
	}

	public final void setStart(final String start) {
		this.start = start;
	}

	public final void setFinish(final String finish) {
		this.finish = finish;
	}
	
	public final void setUsersCount(final Integer usersCount) {
		this.usersCount = usersCount;
	}

	 /**
     * Getters  
     */
	public final String getId() {
		return getIdCompetition();
	}
	
	public final String getIdCompetition() {
		return idCompetition;
	}

	public final String getName() {
		return name;
	}

	public final String getDescription() {
		return description;
	}

	public final String getStart() {
		return start;
	}

	public final String getFinish() {
		return finish;
	}

	public final Integer getUsersCount() {
		return usersCount;
	}
}

