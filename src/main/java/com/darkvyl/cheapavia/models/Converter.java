package com.darkvyl.cheapavia.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Converter {
    private final static String baseFile = "trip.json";

    public static void toJSON(Trip trip) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), trip);
        System.out.println("json created!");
    }

    public static Trip toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), Trip.class);
    }
}
