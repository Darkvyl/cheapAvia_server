package com.darkvyl.cheapavia.component;

import com.darkvyl.cheapavia.service.AndroidPushNotificationsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
@Component
public class ScheduledTasks {

    private final String TOPIC = "trip";
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @Scheduled(fixedRate = 3600000)
    public void check(){

    }



    public void send(){
        JSONObject body = new JSONObject();
        body.put("to", "/topics/" + TOPIC);
        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", "CheapAvia");
        notification.put("body", "Найден дешевый авиабилет для вас");

        JSONObject data = new JSONObject();
        data.put("Key-1", "JSA Data 1");
        data.put("Key-2", "JSA Data 2");

        body.put("notification", notification);
        body.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();
    }
}
