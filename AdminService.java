package com.bus.service;

import com.bus.entity.*;
import com.bus.exception.BadRequestException;
import com.bus.exception.ResourceNotFoundException;
import com.bus.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepo;
    private final BusRepository busRepo;
    private final BookingRepository bookingRepo;

    public AdminService(AdminRepository adminRepo,
                        BusRepository busRepo,
                        BookingRepository bookingRepo) {
        this.adminRepo = adminRepo;
        this.busRepo = busRepo;
        this.bookingRepo = bookingRepo;
    }

    public Admin login(String username, String password) {

        Admin admin = adminRepo.findByUsername(username);
        if (admin == null)
            throw new ResourceNotFoundException("Admin not found");

        if (!admin.getPassword().equals(password))
            throw new BadRequestException("Invalid password");

        return admin;
    }

    public Bus addBus(Bus bus) {
        bus.setAvailableSeats(bus.getTotalSeats());
        return busRepo.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepo.findAll();
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }
}
