package edu.softserveinc.healthbody.entity;

public class CompetitionsView implements IEntity {

    private Integer idCompetition;
    private String name;
    private String description;
    private String start;
    private String finish;
    private Integer usersCount;

    public CompetitionsView(Integer idCompetition, String name, String description, String start, String finish,
            Integer usersCount) {
        this.idCompetition = idCompetition;
        this.name = name;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.usersCount = usersCount;
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

    public void setStart(String start) {
        this.start = start;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
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

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

}
