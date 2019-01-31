package com.darkvyl.cheapavia.controller;

import com.darkvyl.cheapavia.models.Trip;
import com.darkvyl.cheapavia.service.DatabaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DatabaseController {

    @PostMapping("/insert")
    public boolean insert(Trip trip){
        return trip != null && DatabaseService.InsertTrip(trip);
    }

    @PostMapping("/update")
    public boolean update(Trip trip) {
        return trip != null && DatabaseService.UpdateTrip(trip);
    }

    @GetMapping("/get")
    public ArrayList<Trip> get(){
        return DatabaseService.getTrips();
    }
}
