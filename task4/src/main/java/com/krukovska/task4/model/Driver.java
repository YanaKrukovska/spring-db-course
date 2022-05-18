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

    public Driver(String fullName, String country, Team team, int salary) {
        this.fullName = fullName;
        this.country = country;
        this.team = team;
        this.salary = salary;
    }

    public Driver(String fullName, String country, Team team) {
        this(fullName, country, team, 0);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "country")
    private String country;

    @Column(name = "salary")
    private int salary;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


}
