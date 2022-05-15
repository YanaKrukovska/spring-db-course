package com.krukovska.task4.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class TeamEntity extends Entity {

    public static final String TABLE_NAME = "team";

    public TeamEntity(String name, String country, String series) {
        this.name = name;
        this.country = country;
        this.series = series;
    }

    public TeamEntity(long id) {
        super(id);
    }

    private String name;

    private String country;

    private String series;

}
