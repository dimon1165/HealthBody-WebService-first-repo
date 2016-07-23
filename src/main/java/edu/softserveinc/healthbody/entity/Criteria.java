package edu.softserveinc.healthbody.entity;

public class Criteria implements IEntity {

    private Integer idCriteria;
    private String name;
    private Double metrics;
    private String getGoogle;

    public Criteria(final Integer idCriteria, final String name, final Double metrics, final String getGoogle) {
        this.idCriteria = idCriteria;
        this.name = name;
        this.metrics = metrics;
        this.getGoogle = getGoogle;
    }

    // setters
    public final void setIdCriteria(final Integer idCriteria) {
        this.idCriteria = idCriteria;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final void setMetrics(final Double metrics) {
        this.metrics = metrics;
    }

    public final void setGetGoogle(final String getGoogle) {
        this.getGoogle = getGoogle;
    }

    // getters
    public final Integer getId() {
        return getIdCriteria();
    }

    public final Integer getIdCriteria() {
        return idCriteria;
    }

    public final String getName() {
        return name;
    }

    public final Double getMetrics() {
        return metrics;
    }

    public final String getGetGoogle() {
        return getGoogle;
    }
}
