package edu.softserveinc.healthbody.dto;

public class AwardDTO {
	
	private String idAward;
	private String name;

	public AwardDTO() {}
	
	public AwardDTO(String idAward, String name) {
		this.idAward = idAward;
		this.name = name;
	}

	public String getIdAward() {
		return idAward;
	}
	public String getName() {
		return name;
	}

}