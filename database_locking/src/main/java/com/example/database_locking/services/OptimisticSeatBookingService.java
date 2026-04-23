package com.example.database_locking.services;


import com.example.database_locking.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptimisticSeatBookingService {
    @Autowired
    private MovieTicketBookingService movieTicketBookingService;

    public void test(Long seatId) throws Exception {

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is attempting to book seat.");
                Seat seat = movieTicketBookingService.bookSeat(seatId);
                System.out.println(Thread.currentThread().getName() + " booked the seat successfully.");
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " failed: " + e.getMessage());
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
