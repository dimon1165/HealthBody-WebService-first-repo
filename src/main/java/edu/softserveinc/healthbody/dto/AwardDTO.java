package edu.softserveinc.healthbody.dto;

public class AwardDTO {

    private String name;

    public AwardDTO() {
    }

    public AwardDTO(final String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

}