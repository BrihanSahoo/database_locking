package com.example.database_locking.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String movieName;

    @Column(nullable = false)
    private boolean booked;

    @Version
    private Long version;
}