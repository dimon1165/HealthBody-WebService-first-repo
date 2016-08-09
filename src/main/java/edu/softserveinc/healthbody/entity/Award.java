package edu.softserveinc.healthbody.entity;

	/**
	 * Class  edu.softserveinc.healthbody.entity.Award is POJO.
	 * Called by edu.softserveinc.healthbody.entity.AwardDao
	 * which nobody use then
	 * 
	 * Return info about Awards
	 * 
	 * @version 9 August 2016 	
	 * 
	 * */
public class Award implements IEntity {
	
	/** Award id  is primary key*/
	private String idAward;
	
	/** Name of Award */
	private String name;

	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.Award   
     */
	public Award(final String idAward, final String name) {
		this.idAward = idAward;
		this.name = name;
	}

	@Override
	public final String getId() {
		return getIdAward();
	}
	
	 /**
     *  Getters 
     */	
	public final String getIdAward() {
		return idAward;
	}
	public final String getName() {
		return name;
	}
	
	 /**
     * Setters  
     */
	public final void setIdAward(final String idAward) {
		this.idAward = idAward;
	}
	public final void setName(final String name) {
		this.name = name;
	}
}