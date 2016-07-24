package edu.softserveinc.healthbody.entity;

import java.sql.Date;

public class Competition implements IEntity {

    private Integer idCompetition;
    private String name;
    private String description;
    private Date start;
    private Date finish;
    private Integer idCriteria;

    public Competition(final Integer idCompetition, final String name, final String description,
    		final Date start, final Date finish, final Integer idCriteria) {
        this.idCompetition = idCompetition;
        this.name = name;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.idCriteria = idCriteria;
    }

    // setters
    public final void setIdCompetitions(final Integer idCompetition) {
        this.idCompetition = idCompetition;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public final void setStart(final Date start) {
        this.start = start;
    }

    public final void setFinish(final Date finish) {
        this.finish = finish;
    }

    public final void setIdCriteria(final Integer idCriteria) {
        this.idCriteria = idCriteria;
    }

    // getters
    @Override
    public final Integer getId() {
        return getIdCompetition();
    }

    public final Integer getIdCompetition() {
        return idCompetition;
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final Date getStart() {
        return start;
    }

    public final Date getFinish() {
        return finish;
    }

    public final Integer getIdCriteria() {
        return idCriteria;
    }

}
