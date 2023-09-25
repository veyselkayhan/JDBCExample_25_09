package com.veysel.utility;

import java.sql.*;
import java.util.Optional;

//ctrl+d klonlama yapma
public class DataBaseConnection {

    private static DataBaseConnection instance;

    public static final String dbName="futbolapp";

    public static final String url= "jdbc:postgresql://localhost:5432/"+dbName;

    public  static final  String username="postgres";

    public static final String password="root";

    private static Connection connection;



    private DataBaseConnection(){

    }

    private  static DataBaseConnection getInstance(){
        if(instance==null){
            instance=new DataBaseConnection();

        }
        return instance;
    }

 ;
    public static Connection getConnection() throws Exception{
        if(connection==null){
            try {
                connection= DriverManager.getConnection(url,username,password);
            }catch (SQLException e){
                throw new Exception(e);
            }

        }
        return connection;
    }



}
