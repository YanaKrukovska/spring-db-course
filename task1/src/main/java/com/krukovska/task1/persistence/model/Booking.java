package com.krukovska.task1.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "storage_product_id", nullable = false)
    private StorageProduct storageProduct;

    @ManyToOne
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;

    @Column
    private long amount;

    @Column(nullable = false, name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(nullable = false, name = "until_date")
    private LocalDateTime untilDate;
}
