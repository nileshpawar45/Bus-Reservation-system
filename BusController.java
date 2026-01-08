package com.bus.controller;

import com.bus.entity.Bus;
import com.bus.service.BusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buses")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/search")
    public List<Bus> searchBuses(@RequestParam String source,
                                 @RequestParam String destination) {
        return busService.searchBuses(source, destination);
    }
}
