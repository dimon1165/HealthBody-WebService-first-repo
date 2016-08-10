package edu.softserveinc.healthbody.entity;
	
	/**
	 * Class  edu.softserveinc.healthbody.entity.Group is POJO.
	 * Called by: 
	 * edu.softserveinc.healthbody.dao.GroupDao - create instance
	 * edu.softserveinc.healthbody.services.impl.GroupServiceImpl - fill DTO's
	 * Return info about group
	 * 
	 * @version 9 August 2016 	
	 * 
	 * */
public class Group implements IEntity {

	private String idGroup;
	private String name;
	private Integer count;
	private String description;
	private String scoreGroup;
	private String status;
	
	 /**
     *  Constructor of edu.softserveinc.healthbody.entity.Group  
     */
	public Group(final String idGroup, final String name, final Integer count, final String description, 
			final String scoreGroup, final String status) {
		this.idGroup = idGroup;
		this.name = name;
		this.count = count;
		this.description = description;
		this.scoreGroup = scoreGroup;
		this.status = status;
	}

	@Override
	public final String getId() {
		return getIdGroup();
	}
	
	 /**
     * Getters  
     */
	public final String getIdGroup() {
		return idGroup;
	}
	public final String getName() {
		return name;
	}
	public final Integer getCount() {
		return count;
	}
	public final String getDescription() {
		return description;
	}	
	public final String getScoreGroup() {
		return scoreGroup;
	}
	public final String getStatus() {
		return status;
	}	
	
	 /**
     * Setters  
     */
	public final void setIdGroup(final String idGroup) {
		this.idGroup = idGroup;
	}
	public final void setName(final String name) {
		this.name = name;
	}
	public final void setCount(final Integer count) {
		this.count = count;
	}
	public final void setDescription(final String description) {
		this.description = description;
	}
	public final void setScoreGroup(final String scoreGroup) {
		this.scoreGroup = scoreGroup;
	}
	public final void setStatus(final String status) {
		this.status = status;
	}	

}