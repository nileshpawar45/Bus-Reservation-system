package com.bus.service;

import com.bus.entity.*;
import com.bus.exception.BadRequestException;
import com.bus.exception.ResourceNotFoundException;
import com.bus.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final BusRepository busRepo;
    private final UserRepository userRepo;

    public BookingService(BookingRepository bookingRepo,
                          BusRepository busRepo,
                          UserRepository userRepo) {
        this.bookingRepo = bookingRepo;
        this.busRepo = busRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Booking bookTicket(int userId, int busId, int seats) {

        if (seats <= 0)
            throw new BadRequestException("Seats must be greater than 0");

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Bus bus = busRepo.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));

        if (bus.getAvailableSeats() < seats)
            throw new BadRequestException("Not enough seats");

        bus.setAvailableSeats(bus.getAvailableSeats() - seats);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBus(bus);
        booking.setSeats(seats);
        booking.setStatus("BOOKED");

        return bookingRepo.save(booking);
    }
}
