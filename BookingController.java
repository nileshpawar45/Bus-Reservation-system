package com.bus.controller;

import com.bus.entity.Booking;
import com.bus.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("/book")
    public Booking book(@RequestParam int userId,
                        @RequestParam int busId,
                        @RequestParam int seats) {
        return service.bookTicket(userId, busId, seats);
    }
}
