package com.darkvyl.cheapavia.models;

public class Trip {
    private double price;
    private Date departure_date;
    private String destination;
    private String origin;
    private int id;

    public Trip() {
    }

    Trip(int price,
         Date departure_date, String destination, String origin) {
        this.price = price;
        this.departure_date = departure_date;
        this.destination = destination;
        this.origin = origin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
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


    int day(){
        return departure_date.day;
    }

    @Override
    public String toString() {
        return  "Price: " + price +
                "\nDeparture date: " + departure_date +
                "\nOrigin: " + origin +
                "\nDestination: " + destination;
    }
    @Override
    public boolean equals(Object obj) {
        Trip trip = (Trip)obj;
        return this.price==trip.price && this.id == trip.id && this.destination.equals(trip.destination)
                && this.origin.equals(trip.origin) && this.departure_date.equals(trip.departure_date);
    }
}
