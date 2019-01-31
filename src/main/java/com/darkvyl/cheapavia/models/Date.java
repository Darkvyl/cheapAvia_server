package com.darkvyl.cheapavia.models;

public class Date {
    private Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(String date){
        this(Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(5, 7)),
                Integer.parseInt(date.substring(8, 10)));
    }

    int year;
    int month;
    int day;

    @Override
    public String toString() {
        return year+"-"+month+"-"+day;
    }
}
