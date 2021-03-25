
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
public class ReservationQueries {
    
    private static Connection connection;
    private static PreparedStatement roomsReservedByDate;
    private static PreparedStatement addReservation;
    private static PreparedStatement reservationsByDate;
    private static PreparedStatement deleteReservation;
    private static PreparedStatement reservationsByFaculty;
    private static ResultSet resultSet;
    private static ArrayList<String> roomsReservedByDateList = new ArrayList<String>();
    private static ArrayList<ReservationEntry> reservationsByDateList = new ArrayList<ReservationEntry>();
    private static ArrayList<ReservationEntry> reservationsByFacultyList = new ArrayList<ReservationEntry>();
    
    
    public static ArrayList<String> getRoomsReservedByDate(String date){
        ArrayList<String> roomsReservedByDateList = new ArrayList<String>();
        try{
            connection = DBConnection.getConnection();
            roomsReservedByDate = connection.prepareStatement("select room from reservations where date = ?");
            roomsReservedByDate.setString(1, date);
            resultSet = roomsReservedByDate.executeQuery();
            
            while (resultSet.next()){
                roomsReservedByDateList.add(resultSet.getString("room"));
            }
        
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return roomsReservedByDateList;
    }
    
    public static void addReservsationEntry (ReservationEntry newEntry){
        try{
            connection = DBConnection.getConnection();
            addReservation = connection.prepareStatement("insert into reservations (faculty, room, date, seats, timestamp) values (?,?,?,?,?)");
            addReservation.setString(1, newEntry.getName());
            addReservation.setString(2, newEntry.getRoom());
            addReservation.setString(3, newEntry.getDate());
            addReservation.setInt(4, newEntry.getSeats());
            addReservation.setTimestamp(5, newEntry.getTimestamp());
            addReservation.executeUpdate();
            
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<ReservationEntry> getReservationsByDate(String date){
        ArrayList<ReservationEntry> reservationsByDateList = new ArrayList<ReservationEntry>();
        try{
            connection = DBConnection.getConnection();
            reservationsByDate = connection.prepareStatement("select * from reservations where date = ?");
            reservationsByDate.setString(1, date);
            resultSet = reservationsByDate.executeQuery();
            
            while(resultSet.next()){
                reservationsByDateList.add(new ReservationEntry(resultSet.getString("faculty"), resultSet.getString("room"), 
                        resultSet.getString("date"), resultSet.getInt("seats"), resultSet.getTimestamp("timestamp")));
            }
            
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return reservationsByDateList;
    }
    
    public static void deleteReservation (String date, String faculty){
        try{
            connection = DBConnection.getConnection();
            deleteReservation = connection.prepareStatement("delete from reservations where date=? and faculty=?");
            deleteReservation.setString(1, date);
            deleteReservation.setString(2, faculty);
            deleteReservation.executeUpdate();
            
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<ReservationEntry> getReservationsByFaculty(String faculty){
        ArrayList<ReservationEntry> reservationsByFacultyList = new ArrayList<ReservationEntry>();
        try {
            connection = DBConnection.getConnection();
            reservationsByFaculty = connection.prepareStatement("select * from reservations where faculty=?");
            reservationsByFaculty.setString(1, faculty);
            resultSet = reservationsByFaculty.executeQuery();
            
            while (resultSet.next()){
                reservationsByFacultyList.add(new ReservationEntry(resultSet.getString("faculty"),
                    resultSet.getString("room"), resultSet.getString("date"), resultSet.getInt("seats"), 
                        resultSet.getTimestamp("timestamp")));
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return reservationsByFacultyList;
    }
}
