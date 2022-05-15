package com.krukovska.task4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "driver")
public class Driver {

    public Driver(String fullName, String country, Team team) {
        this.fullName = fullName;
        this.country = country;
        this.team = team;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "country")
    private String country;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
