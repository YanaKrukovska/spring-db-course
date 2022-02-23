package com.krukovska.task3.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(name = "page_amount")
    private long pageAmount;

    @Column(name = "cover_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CoverType coverType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String publisher;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "book_genres",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private List<Genre> genres;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "book_authors",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authors;

}
