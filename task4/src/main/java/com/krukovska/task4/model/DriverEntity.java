package com.krukovska.task4.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
public class DriverEntity extends Entity {

    public static final String TABLE_NAME = "driver";


    public DriverEntity(String fullName, String country, TeamEntity team) {
        this.fullName = fullName;
        this.country = country;
        this.team = team;
    }

    private String fullName;

    private String country;

    private TeamEntity team;

}
