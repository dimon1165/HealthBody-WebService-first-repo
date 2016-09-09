package edu.softserveinc.healthbody.dto;

	/**
	 * Class  edu.softserveinc.healthbody.dto.Award is POJO.
	 * 
	 * 
	 * @version 9 August 2016 	
	 * 
	 * */
public class AwardDTO {
	
	private String idAward;
	private String name;

	 /**
     *  Default constructor of edu.softserveinc.healthbody.dto.Award   
     */
	public AwardDTO() {}

	 /**
     *  Constructor of edu.softserveinc.healthbody.dto.Award   
     */
	public AwardDTO(final String idAward, final String name) {
		this.idAward = idAward;
		this.name = name;
	}

	 /** Getter*/
	public String getIdAward() {
		return idAward;
	}
	
	public final String getName() {
		return name;
	}
	
	/** Setter*/
	public void setIdAward(String idAward) {
		this.idAward = idAward;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}