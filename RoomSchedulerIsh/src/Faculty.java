
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
public class Faculty {
    private static Connection connection;
    private static ArrayList<String> facultyList= new ArrayList<String> ();
    private static PreparedStatement insertNewFaculty;
    private static PreparedStatement getAllFacultyList;
    private static ResultSet resultSet;
    
    public static void addNewFaculty (String faculty){
        try{
            connection = DBConnection.getConnection();
            insertNewFaculty = connection.prepareStatement("insert into Faculty (name) values (?)");
            insertNewFaculty.setString(1, faculty);
            insertNewFaculty.executeUpdate();
        
        }catch (SQLException ex){
            System.out.printf("Unable to add %s to the database.%n", faculty);
            ex.printStackTrace();
            
        }
    }
    
    public static ArrayList<String> getFacultyList(){
        ArrayList<String> facultyList = new ArrayList<String> ();
        try{
            connection = DBConnection.getConnection();
            getAllFacultyList = connection.prepareStatement("select * from Faculty");
            resultSet = getAllFacultyList.executeQuery();
            
            
            while (resultSet.next()){
                facultyList.add(resultSet.getString("Name"));
            }
            
        } catch (SQLException ex){
            System.out.println("Unnable to get faculty list from database.");
            ex.printStackTrace();
        } 
        return facultyList;
    }
}
