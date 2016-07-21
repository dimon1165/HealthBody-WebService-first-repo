package edu.softserveinc.healthbody.dto;

public class GroupDTO {

    private String name;
    private String count;
    private String descriptions;
    private String scoreGroup;

    // private String status;

    public GroupDTO() {
    }

    public GroupDTO(String name, String count, String descriptions, String scoreGroup) {
        this.name = name;
        this.count = count;
        this.descriptions = descriptions;
        this.scoreGroup = scoreGroup;

    }

    // getters
    public String getName() {
        return name;
    }

    public String getCount() {
        return count;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public String getScoreGroup() {
        return scoreGroup;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setScoreGroup(String scoreGroup) {
        this.scoreGroup = scoreGroup;
    }

}