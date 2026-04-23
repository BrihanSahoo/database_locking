package com.example.database_locking.services;


import com.example.database_locking.entity.Seat;
import com.example.database_locking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieTicketBookingService {

    @Autowired
    private SeatRepository seatRepository;

    @Transactional
    public Seat bookSeat(Long seatId) {

        try {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new RuntimeException("Seat not found: " + seatId));

            System.out.println(Thread.currentThread().getName() +
                    " fetched seat with version " + seat.getVersion());

            if (seat.isBooked()) {
                throw new RuntimeException("Seat already booked.");
            }

            seat.setBooked(true);

            return seatRepository.save(seat);

        } catch (ObjectOptimisticLockingFailureException e) {
            throw new RuntimeException("Seat booking failed due to concurrent access. Try again.");
        }
    }
}
