package com.bus.controller;

import com.bus.entity.*;
import com.bus.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Admin login(@RequestParam String username,
                       @RequestParam String password) {
        return service.login(username, password);
    }

    @PostMapping("/bus")
    public Bus addBus(@RequestBody Bus bus) {
        return service.addBus(bus);
    }

    @GetMapping("/buses")
    public List<Bus> buses() {
        return service.getAllBuses();
    }

    @GetMapping("/bookings")
    public List<Booking> bookings() {
        return service.getAllBookings();
    }
}
