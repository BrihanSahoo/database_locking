package com.example.database_locking.repository;


import com.example.database_locking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
