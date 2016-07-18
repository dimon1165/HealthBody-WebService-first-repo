package edu.softserveinc.healthbody.dto;

import java.util.List;

public class CompetitionDTO {

	private String name;
	private String count;
	private String startDate;
	private String finishDate;
	private String description;
	private String nameCriteria;
	private List<String> groups;
	private List<String> logins;

	public CompetitionDTO() {}
	
	public CompetitionDTO(String name, String count, String startDate, String finishDate, String description,
			String nameCriteria, List<String> groups, List<String> logins) {
		this.name = name;
		this.count = count;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.description = description;
		this.nameCriteria = nameCriteria;
		this.groups = groups;
		this.logins = logins;
	}

	public String getName() {
		return name;
	}

	public String getCount() {
		return count;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public List<String> getGroups() {
		return groups;
	}

	public List<String> getLogins() {
		return logins;
	}

	public String getDescription() {
		return description;
	}

	public String getNameCriteria() {
		return nameCriteria;
	}

	//setters
	public void setName(String name) {
		this.name = name;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setNameCriteria(String nameCriteria) {
		this.nameCriteria = nameCriteria;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public void setLogins(List<String> logins) {
		this.logins = logins;
	}

	@Override
	public String toString() {
		return "CompetitionDTO [name=" + name + ", count=" + count + ", startDate=" + startDate + ", finishDate="
				+ finishDate + "]" + System.lineSeparator();
	}

}