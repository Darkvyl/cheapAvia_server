package com.darkvyl.cheapavia.service;

import com.darkvyl.cheapavia.models.Date;
import com.darkvyl.cheapavia.models.Trip;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseService {
    private final static String url ="jdbc:mysql://zpj83vpaccjer3ah.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/egbjlvzs8zky4nk6";
    private final static String username ="wzs0hhl871kk08sr";
    private final static String password ="cv2azf0pg0xsoehw";
    private final static String driver = "com.mysql.cj.jdbc.Driver";

    public static boolean InsertTrip(Trip trip){

        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO users (origin, destination," +
                    "departDate, price) VALUES("+ trip.getOrigin() +", "+
                    trip.getDestination()+", "+ trip.getDeparture_date() +", "+trip.getPrice() +", "+");");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean UpdateTrip(Trip trip){
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = conn.prepareStatement("UPDATE users set departDate = ?, " +
                    "price = ? WHERE id = " + trip.getId());
            statement.setString(1, trip.getDeparture_date().toString());
            statement.setDouble(2, trip.getPrice());
            statement.executeUpdate();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<Trip> getTrips(){
        ArrayList<Trip> trips = new ArrayList<>();
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next())
            {
                String origin = resultSet.getString("origin");
                String destination = resultSet.getString("destination");
                Date departDate = new Date(resultSet.getString("departDate"));
                double price = resultSet.getDouble("price");
                int id = resultSet.getInt("id");
                Trip trip = new Trip();
                trip.setOrigin(origin);
                trip.setDestination(destination);
                trip.setDeparture_date(departDate);
                trip.setPrice(price);
                trip.setId(id);
                trips.add(trip);
            }
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return trips;
    }
}
