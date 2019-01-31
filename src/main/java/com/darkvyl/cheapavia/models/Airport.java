package com.darkvyl.cheapavia.models;

import com.darkvyl.cheapavia.CheapaviaApplication;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Airport {
    String getCode() {
        return code;
    }
    private String code;

    public Airport(String code){
        this.code = code;
    }

    public Trip findCheapestOneWayTripFromHere(Airport destination, Date date) throws IOException {
        String url = "http://api.travelpayouts.com/v2/prices/latest" +
                "?currency=uah&origin=" + code +
                "&destination=" + destination.code +
                "&period_type=month&beginning_of_period=" + date.year + "-" + date.month + "-01" +
                "&page=1&one_way=true&token=" + CheapaviaApplication.TOKEN;

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");


        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject object = new JSONObject(response.toString());
        JSONArray JSONTrips = object.getJSONArray("data");
        ArrayList<Trip> trips = new ArrayList<>();
        for (int i = 0; i < JSONTrips.length(); i++) {
            JSONObject JSONTrip = JSONTrips.getJSONObject(i);
            int price = JSONTrip.getInt("value");
            String trip_date = JSONTrip.getString("depart_date");
            String jsonDest = JSONTrip.getString("destination");
            String jsonOrigin = JSONTrip.getString("origin");
            trips.add(new Trip(price, new Date(trip_date), jsonDest, jsonOrigin));
        }

        final Trip[] goodTrip = new Trip[1];
        trips.forEach(trip -> {
            if (trip.day() == date.day) {
                goodTrip[0] = trip;
            }
        });
        if(goodTrip[0] == null){
            trips.forEach(trip -> {
                if (trip.day() == date.day-1) {
                    goodTrip[0] = trip;
                }
            });
        }
        if(goodTrip[0] == null){
            trips.forEach(trip -> {
                if (trip.day() == date.day+1) {
                    goodTrip[0] = trip;
                }
            });
        }

        return goodTrip[0];
    }

    @Override
    public String toString() {
        return code;
    }
}
