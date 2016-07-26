package edu.softserveinc.healthbody.dto;

public class GroupDTO {

    private String name;
    private String count;
    private String descriptions;
    private String scoreGroup;

    // private String status;

    public GroupDTO() {
    }

    public GroupDTO(final String name, final String count, final String descriptions, final String scoreGroup) {
        this.name = name;
        this.count = count;
        this.descriptions = descriptions;
        this.scoreGroup = scoreGroup;

    }

    // getters
    public final String getName() {
        return name;
    }

    public final String getCount() {
        return count;
    }

    public final String getDescriptions() {
        return descriptions;
    }

    public final String getScoreGroup() {
        return scoreGroup;
    }

    // setters
    public final void setName(final String name) {
        this.name = name;
    }

    public final void setCount(final String count) {
        this.count = count;
    }

    public final void setDescriptions(final String descriptions) {
        this.descriptions = descriptions;
    }

    public final void setScoreGroup(final String scoreGroup) {
        this.scoreGroup = scoreGroup;
    }
}