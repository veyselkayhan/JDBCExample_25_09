package com.veysel.utility;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class ConnectionProvider {

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public boolean openConnection(){

        try {
//            Driver.class.forName("org.postgresql.Driver");
            DataBaseConnection.getConnection();
            System.out.println(DataBaseConnection.dbName + "veritabanina basariyla baglanildi.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

}

    public boolean closeConnection(){
        try {
            if (!DataBaseConnection.getConnection().isClosed()){
                DataBaseConnection.getConnection().close();
                System.out.println("Baglantı Kesildi");
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public  PreparedStatement getPreparedStatement(String sql){
        if (openConnection()) {
            try {
                preparedStatement = DataBaseConnection.getConnection().prepareStatement(sql);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                closeConnection();
                throw new RuntimeException(e);
            }

        }
        return preparedStatement;
    }

    public Optional<ResultSet> getData(/*String sql,*/PreparedStatement preparedStatement){
//        if(openConnection()){
            try {
//                preparedStatement = DataBaseConnection.getConnection().prepareStatement(sql);
                resultSet=preparedStatement.executeQuery();
                closeConnection();
            } catch (Exception e) {
                System.out.println();
                throw new RuntimeException(e);
            }

//        }
        return Optional.ofNullable(resultSet);
    }
}



