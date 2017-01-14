package lemonapps.localmusicscene;
import android.annotation.SuppressLint;
import android.database.CursorJoiner;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;

import java.sql.*;
/**
 * Created by Rob on 1/12/2017.
 */
public class SQLConnection {
    //public Connection connection = null;
    private static final String ip = "192.168.2.183:1433"  ; //192.68.2.183
    private static final String dataBaseName = "LOCALMUSICSCENE";
    private static final String username = "Java";
    private static final String password ="thesolohoarder@123";
    private static  String driverClass = "net.sourceforge.jtds.jdbc.Driver";
    private static final String url = "jdbc:jtds:sqlserver://192.168.2.183:1433/master;integratedSecurity=true";
    //private static final String url = "jdbc:jtds:sqlserver://" + ip + ";" + "databaseName=" + dataBaseName + ";user=" + username + ";password=" + password + ";";
    public SQLConnection() {
        ConnectToDataBase();
    }

    @SuppressLint("NewAPI")
    public static Connection ConnectToDataBase(){
        Connection conn = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url,username,password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Signup");
            if(resultSet.isBeforeFirst()){
                Log.e("SQL","NO DATA");
            }else{
                Object resObj = resultSet.getObject(1);
                if(resObj instanceof String){
                    Log.i("SQLTABLE",(String)resObj);
                }
            }

        }catch(Exception ex) {
            Log.e("SQLERROR",ex.getLocalizedMessage());
        }
        return conn;
    }
}

