package core;

import app.model.entity.Exemplos.User;

import java.sql.*;
import java.lang.*;

public class ConnectionFactory {

    public Connection getConnection() {
        ConnectionSingleton cs = ConnectionSingleton.getInstance();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    (cs.getDriver() + cs.getHost() + cs.getDataBase()), cs.getUser(),cs.getPassWord()
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}