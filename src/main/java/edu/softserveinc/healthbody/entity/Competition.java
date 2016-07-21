package edu.softserveinc.healthbody.entity;

import java.sql.Date;

public class Competition implements IEntity {

    private Integer idCompetition;
    private String name;
    private String description;
    private Date start;
    private Date finish;
    private Integer idCriteria;

    public Competition(Integer idCompetition, String name, String description, Date start, Date finish,
            Integer idCriteria) {
        this.idCompetition = idCompetition;
        this.name = name;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.idCriteria = idCriteria;
    }

    // setters

    public void setIdCompetitions(Integer idCompetition) {
        this.idCompetition = idCompetition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public void setIdCriteria(Integer idCriteria) {
        this.idCriteria = idCriteria;
    }

    // getters

    public Integer getId() {
        return getIdCompetition();
    }

    public Integer getIdCompetition() {
        return idCompetition;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getStart() {
        return start;
    }

    public Date getFinish() {
        return finish;
    }

    public Integer getIdCriteria() {
        return idCriteria;
    }

}
