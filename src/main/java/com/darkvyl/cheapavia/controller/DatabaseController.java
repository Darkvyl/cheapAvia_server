package com.darkvyl.cheapavia.controller;

import com.darkvyl.cheapavia.models.Trip;
import com.darkvyl.cheapavia.service.DatabaseService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;

@RestController
public class DatabaseController {

    @PostMapping("/insert")
    public Trip insert(@RequestBody Trip trip){
        LoggerFactory.getLogger(DatabaseController.class).info(trip.toString());
        int id;
        if((id = DatabaseService.InsertTrip(trip)) >= 0){
            return DatabaseService.getTrip(id);
        }
        return new Trip();
    }

    @PostMapping("/update")
    public boolean update(Trip trip) {
        return trip != null && DatabaseService.UpdateTrip(trip) >=0;
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
