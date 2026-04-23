package com.example.database_locking.controller;


import com.example.database_locking.services.OptimisticSeatBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class SeatBookController {

    @Autowired
    private OptimisticSeatBookingService optimisticSeatBookingService;

    @GetMapping("/optimistic/{seatId}")
    public void bookSeat(@PathVariable Long seatId) throws Exception{
        optimisticSeatBookingService.test(seatId);

    }
}
