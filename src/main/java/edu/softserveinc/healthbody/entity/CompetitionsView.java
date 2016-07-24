package edu.softserveinc.healthbody.entity;

public class CompetitionsView implements IEntity {

    private Integer idCompetition;
    private String name;
    private String description;
    private String start;
    private String finish;
    private Integer usersCount;

    public CompetitionsView(final Integer idCompetition, final String name, final String description,
    		final String start, final String finish, final Integer usersCount) {
        this.idCompetition = idCompetition;
        this.name = name;
        this.description = description;
        this.start = start;
        this.finish = finish;
        this.usersCount = usersCount;
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

    public final void setStart(final String start) {
        this.start = start;
    }

    public final void setFinish(final String finish) {
        this.finish = finish;
    }

    public final void setUsersCount(final Integer usersCount) {
        this.usersCount = usersCount;
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

    public final String getStart() {
        return start;
    }

    public final String getFinish() {
        return finish;
    }

    public final Integer getUsersCount() {
        return usersCount;
    }
}
