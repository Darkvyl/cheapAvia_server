package com.darkvyl.cheapavia.controller;

import com.darkvyl.cheapavia.models.Trip;
import com.darkvyl.cheapavia.service.DatabaseService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/get", params = "id")
    public Trip getTrip(@RequestParam(value = "id") int id){
        return DatabaseService.getTrip(id);
    }
}
