
import java.sql.Connection;
import java.sql.Date;
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
public class Dates {
    private static Connection connection;
    private static PreparedStatement getDates;
    private static PreparedStatement addDate;
    private static ResultSet resultSet;
    private static ArrayList<String> dateList = new ArrayList<String>();
    
    public static ArrayList<String> getAllDates(){
        ArrayList<String> dateList = new ArrayList<String>();
        try{
            connection = DBConnection.getConnection();
            getDates = connection.prepareStatement("select * from dates");
            resultSet = getDates.executeQuery();
            while (resultSet.next()){
                dateList.add(resultSet.getDate("Date").toString());
            }
            
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return dateList;
    }
    
    public static void addDate(Date date){
        try {
            connection = DBConnection.getConnection();
            addDate = connection.prepareStatement("insert into dates (date) values (?)");
            addDate.setDate(1, date);
            addDate.executeUpdate();
        
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
