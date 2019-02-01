/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

/**
 *
 * @author Nenad
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private static DB instance;
    private String kon = "jdbc:mysql://localhost:3306/mysql"; 
    
    private Connection con;


    private DB() { 

        try {
           Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(kon, "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
            return instance;
    }

    public synchronized Connection getConnection() {
        return con;
    }
    

}