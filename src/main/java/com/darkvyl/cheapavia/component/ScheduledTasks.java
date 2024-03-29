package com.darkvyl.cheapavia.component;

import com.darkvyl.cheapavia.models.Airport;
import com.darkvyl.cheapavia.models.Trip;
import com.darkvyl.cheapavia.service.AndroidPushNotificationsService;
import com.darkvyl.cheapavia.service.DatabaseService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final
    AndroidPushNotificationsService androidPushNotificationsService;

    @Autowired
    public ScheduledTasks(AndroidPushNotificationsService androidPushNotificationsService) {
        this.androidPushNotificationsService = androidPushNotificationsService;
    }

    @Scheduled(fixedRate = 3600000)
    public void check() {
        ArrayList<Trip> trips = DatabaseService.getTrips();
        try {
            for (Trip trip : trips) {
                Airport airport = new Airport(trip.getOrigin());
                Trip tmp_trip = airport.findCheapestOneWayTripFromHere(new Airport(trip.getDestination()),
                        trip.getDeparture_date());
                int id;
                if(tmp_trip != null && (tmp_trip.getPrice() < trip.getPrice() || trip.getPrice() == 0)){
                    tmp_trip.setId(trip.getId());
                    if((id = DatabaseService.UpdateTrip(tmp_trip)) >= 0) {
                        send("ticket_n" + String.valueOf(id),
                                "We have found a new cheap ticket for you from: "+tmp_trip.getOrigin()
                                        +" to: "+tmp_trip.getDestination()+"! " +
                                "Check out in our application!");
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 300000) //Special code to prevent app from sleeping on heroku
    public void stayAwake(){
        try {
            URL url = new URL("https://cheapavia.herokuapp.com/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void send(String topic, String mess){
        JSONObject body = new JSONObject();
        body.put("to", "/topics/" + topic);
        body.put("priority", "high");
        body.put("Content-Type", "application/json");


        JSONObject notification = new JSONObject();
        notification.put("title", "CheapAvia");
        notification.put("body", mess);

        JSONObject data = new JSONObject();
        data.put("Key-1", "JSA Data 1");
        data.put("Key-2", "JSA Data 2");

        body.put("notification", notification);
        body.put("data", data);

        log.info(topic + ": " + notification.getString("body"));

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();
    }
}
