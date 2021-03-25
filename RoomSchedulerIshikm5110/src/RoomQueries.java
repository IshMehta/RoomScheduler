
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
public class RoomQueries {
    private static Connection connection;
    private static PreparedStatement getRooms;
    private static PreparedStatement addRoom;
    private static PreparedStatement dropRoom;
    private static ResultSet resultSet;
    private static ArrayList<RoomEntry> allRoomsList = new ArrayList<RoomEntry>();
    
    public static ArrayList<RoomEntry> getAllPossibleRooms (){
        ArrayList<RoomEntry> allRoomsList = new ArrayList<RoomEntry>();
        try{
            connection = DBConnection.getConnection();
            getRooms = connection.prepareStatement("select * from rooms");
            resultSet = getRooms.executeQuery();
            
            while(resultSet.next()){
                allRoomsList.add(new RoomEntry(resultSet.getString("name"), resultSet.getInt("seats")));
            }
        
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return allRoomsList;
    }
    
    public static void addRoom(RoomEntry entry){
        try{
            connection = DBConnection.getConnection();
            addRoom = connection.prepareStatement("insert into rooms (name, seats) values (?,?)");
            addRoom.setString(1, entry.getName());
            addRoom.setInt(2, entry.getSeats());
            addRoom.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void dropRoom(String room){
        try{
            connection = DBConnection.getConnection();
            dropRoom = connection.prepareStatement("delete from rooms where name = ?");
            dropRoom.setString(1, room);
            dropRoom.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
}
