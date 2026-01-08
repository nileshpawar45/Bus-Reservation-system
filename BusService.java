package com.bus.service;

import com.bus.entity.Bus;
import com.bus.repository.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    private final BusRepository busRepo;

    public BusService(BusRepository busRepo) {
        this.busRepo = busRepo;
    }

    public List<Bus> searchBuses(String source, String destination) {
        return busRepo.findBySourceAndDestination(source, destination);
    }
}
