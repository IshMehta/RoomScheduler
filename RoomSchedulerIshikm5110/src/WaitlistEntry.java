
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ish
 */
public class WaitlistEntry {
    private String faculty;
    private String date;
    private int seats;
    private Timestamp timestamp;
    
    public WaitlistEntry(){}
    
    public WaitlistEntry(String faculty, String date, int seats, Timestamp timestamp){
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }
    
    public String getName(){
        return faculty;
    }
    
    public String getDate(){
        return date;
    }
    
    public int getSeats(){
        return seats;
    }
    
    public Timestamp getTimestamp(){
        return timestamp;
    }
    
}
