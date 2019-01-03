package com.darkvyl.cheapavia.service;

import com.darkvyl.cheapavia.models.Trip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {
    private final static String url ="db4free.net";
    private final static String username ="darkvyl";
    private final static String password ="testpasswordfordb";

    static boolean InsertTrip(Trip trip, String lastResult, String topic){
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO trips (origin, destination," +
                    "date, last_result, topic) VALUES("+ trip.getOrigin() +", "+
                    trip.getDestination()+", "+ trip.getDeparture_date() +", "+lastResult +", " +
                     topic+ ") ");
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
