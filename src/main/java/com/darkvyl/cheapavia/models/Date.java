package com.darkvyl.cheapavia.models;

public class Date {
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    int year;
    int month;
    int day;

    @Override
    public String toString() {
        return year+"-"+month+"-"+day;
    }
}
