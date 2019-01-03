package com.darkvyl.cheapavia.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trip {
    @JsonProperty("Price")
    private int price;
    @JsonProperty("Airline")
    private String airline;
    @JsonProperty("Date")
    private String departure_date;
    @JsonProperty("Destination")
    private String destination;
    @JsonProperty("Origin")
    private String origin;

    public Trip() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Trip(int price, String airline,
                String departure_date, String destination, String origin) {
        this.price = price;
        this.airline = airline;
        this.departure_date = departure_date;
        this.destination = destination;
        this.origin = origin;
    }

    public int day(){
        return Integer.parseInt(departure_date.substring(departure_date.length()-2));
    }

    @Override
    public String toString() {
        return  "Price: " + price +
                "\nAirline: " + airline +
                "\nDeparture date: " + departure_date +
                "\nOrigin: " + origin +
                "\nDestination: " + destination;
    }
    @Override
    public boolean equals(Object obj) {
        Trip trip = (Trip)obj;
        return this.price==trip.price && this.destination.equals(trip.destination)
                && this.origin.equals(trip.origin) && this.airline.equals(trip.airline)
                && this.departure_date.equals(trip.departure_date);
    }
}
