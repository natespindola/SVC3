package core;

import java.lang.*;

public class ConnectionSingleton
{
    /**
     * lazy init
     */
    private static ConnectionSingleton ourInstance = null;

    private static String driver;
    private static String host;
    private static String dataBase;
    private static String user;
    private static String passWord;

    public static ConnectionSingleton getInstance() {
        if(ourInstance == null){
            ourInstance = new ConnectionSingleton();
        }

        return ourInstance;
    }

    /**
     * Use your database configuration here
     */
    private ConnectionSingleton()
    {
        driver   = "jdbc:mysql://";
        host     = "127.0.0.1/";
        dataBase = "svc2";
        user     = "root";
        passWord = "1234";
    }

    public String getDriver()
    {
        return driver;
    }
    public String getHost()
    {
        return host;
    }
    public String getDataBase()
    {
        return dataBase;
    }
    public String getUser()
    {
        return user;
    }
    public String getPassWord()
    {
        return passWord;
    }
}
