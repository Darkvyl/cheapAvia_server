package com.darkvyl.cheapavia.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Date {
    public Date(String date) {
        Pattern yearPattern = Pattern.compile("\\d{4}");
        Matcher yearMatcher = yearPattern.matcher(date);
        Pattern dayPattern = Pattern.compile("-\\d{1,2}");
        Matcher dayMatcher = dayPattern.matcher(date);
        Pattern monthPattern = Pattern.compile("-\\d{1,2}-");
        Matcher monthMatcher = monthPattern.matcher(date);

        if (yearMatcher.find() && monthMatcher.find() && dayMatcher.find()) {
            int year = Integer.parseInt(yearMatcher.group());
            int month = Integer.parseInt(monthMatcher.group().substring(1, monthMatcher.group().length()-1));
            int day = Integer.parseInt(dayMatcher.group().substring(1));

            this.year = year;
            this.month = month;
            this.day = day;
        }
    }

    int year;
    int month;
    int day;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return year+"-"+month+"-"+day;
    }
}
