package com.krukovska.springdbcourse.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "storages")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "address", nullable = false, unique = true)
    private String address;

    @OneToMany
    @JoinColumn(name = "storage_id")
    private List<StorageProduct> storageProducts;
}
