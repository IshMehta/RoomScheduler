
import java.sql.Timestamp;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ish
 */
public class ReservationEntry {
    private String name;
    private String room;
    private String date;
    private int seats;
    private Timestamp timestamp;
    
    public ReservationEntry(String name, String room, String date, int seats, Timestamp timestamp){
        this.name = name;
        this.room = room;
        this.seats = seats;
        this.date = date;
        this.timestamp = timestamp;
    }
    
    public String getName(){
        return name;
    }
    
    public String getRoom(){
        return room;
    }
    
    public int getSeats(){
        return seats;
    }
    
    public String getDate(){
        return date;
    }
    
    public Timestamp getTimestamp(){
        return timestamp;
    }
}
