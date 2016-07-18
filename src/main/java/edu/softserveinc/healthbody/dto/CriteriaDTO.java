package edu.softserveinc.healthbody.dto;

import java.util.List;

public class CriteriaDTO {

	private String name;
	private String metrics;
	private String getGoogle;
	private List<CompetitionDTO> competitions;
	
	public CriteriaDTO() {}
	
	public CriteriaDTO(String name, String metrics, String getGoogle, List<CompetitionDTO> competitions) {
		this.name = name;
		this.metrics = metrics;
		this.getGoogle = getGoogle;
		this.competitions = competitions;
	}

	public String getName() {
		return name;
	}

	public String getMetrics() {
		return metrics;
	}

	public String getGetGoogle() {
		return getGoogle;
	}

	public List<CompetitionDTO> getCompetitions() {
		return competitions;
	}

}