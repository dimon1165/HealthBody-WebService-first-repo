package edu.softserveinc.healthbody.entity;

public class CompetitionsView implements IEntity {

	private String idCompetition;
	private String name;
	private String description;
	private String start;
	private String finish;
	private Integer usersCount;

	public CompetitionsView(String idCompetition, String name, String description, String start, 
			String finish, Integer usersCount) {
		this.idCompetition = idCompetition;
		this.name = name;
		this.description = description;
		this.start = start;
		this.finish = finish;
		this.usersCount = usersCount;
	}
	
	// Setters
	public void setIdCompetitions(String idCompetition) {
		this.idCompetition = idCompetition;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}
	
	public void setUsersCount(Integer usersCount) {
		this.usersCount = usersCount;
	}

	// Getters	
	public String getId() {
		return getIdCompetition();
	}
	
	public String getIdCompetition() {
		return idCompetition;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getStart() {
		return start;
	}

	public String getFinish() {
		return finish;
	}

	public Integer getUsersCount() {
		return usersCount;
	}
}
