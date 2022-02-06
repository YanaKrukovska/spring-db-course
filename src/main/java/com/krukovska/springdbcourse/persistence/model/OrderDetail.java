package com.krukovska.springdbcourse.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "storage_product_id", nullable = false)
    private StorageProduct storageProduct;

    @Column
    private long amount;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", storageProductId=" + storageProduct.getId() +
                ", amount=" + amount +
                '}';
    }
}
