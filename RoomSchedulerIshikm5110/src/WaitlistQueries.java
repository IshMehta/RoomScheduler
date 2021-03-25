import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ish
 */
public class WaitlistQueries {
    private static Connection connection;
    private static PreparedStatement addWaitlist;
    private static PreparedStatement getWaitlist_date;
    private static PreparedStatement getWaitlist_faculty;
    private static PreparedStatement deleteEntry;
    private static ResultSet resultSet;
    private static ArrayList<WaitlistEntry> waitlistByDateList = new ArrayList<WaitlistEntry>();
    private static ArrayList<WaitlistEntry> waitlistByFacultyList = new ArrayList<WaitlistEntry>();
    
    public static void addWaitlistEntry(WaitlistEntry entry){
        try{
            connection = DBConnection.getConnection();
            addWaitlist = connection.prepareStatement("insert into waitlist (faculty, date, seats, timestamp) values (?,?,?,?) ");
            addWaitlist.setString(1, entry.getName());
            addWaitlist.setString(2,entry.getDate());
            addWaitlist.setInt(3, entry.getSeats());
            addWaitlist.setTimestamp(4, entry.getTimestamp());
            addWaitlist.executeUpdate();
            
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<WaitlistEntry> getWaitlistByDate(String date){
        ArrayList<WaitlistEntry> waitlistByDateList = new ArrayList<WaitlistEntry>();
        try{
            connection = DBConnection.getConnection();
            getWaitlist_date = connection.prepareStatement("select * from waitlist where date = ? order by timestamp ASC");
            getWaitlist_date.setString(1, date);
            resultSet = getWaitlist_date.executeQuery();
            
            while (resultSet.next()){
                waitlistByDateList.add(new WaitlistEntry(resultSet.getString("faculty"), resultSet.getString("date"), 
                        resultSet.getInt("seats"), resultSet.getTimestamp("timestamp")));
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return waitlistByDateList;
    }
    
    public static void deleteWaitlistEntry(String faculty, String date){
        try{
            connection = DBConnection.getConnection();
            deleteEntry = connection.prepareStatement("delete from waitlist where faculty=? and date=?");
            deleteEntry.setString(1, faculty);
            deleteEntry.setString(2, date);
            deleteEntry.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty){
        ArrayList<WaitlistEntry> waitlistByFacultyList = new ArrayList<WaitlistEntry>();
        try{
            connection = DBConnection.getConnection();
            getWaitlist_faculty = connection.prepareStatement("select * from waitlist where faculty=?");
            getWaitlist_faculty.setString(1, faculty);
            resultSet = getWaitlist_faculty.executeQuery();
            
            while(resultSet.next()){
                waitlistByFacultyList.add(new WaitlistEntry(faculty, resultSet.getString("date"), resultSet.getInt("seats"), 
                        resultSet.getTimestamp("timestamp")));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return waitlistByFacultyList;
    }
}