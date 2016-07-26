package edu.softserveinc.healthbody.dto;

public class AwardDTO {
	
	private String idAward;
	private String name;

	public AwardDTO() {}
	
	public AwardDTO(final String idAward, final String name) {
		this.idAward = idAward;
		this.name = name;
	}

	public String getIdAward() {
		return idAward;
	}
	public final String getName() {
		return name;
	}

}