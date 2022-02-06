package com.krukovska.springdbcourse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private BigDecimal price;

    @Formula("(select sum(sp.amount) from storage_products sp where sp.product_id = id)")
    private long amount;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

}
