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
    private Connection con;
    private static final String ip = "184.146.28.136:1433";
    private static final String database = "master";
    private static final String username = "Java";
    private static final String password ="thesolohoarder@123";
    private static final String driverClass = "net.sourceforge.jtds.jdbc.Driver";
    private static final String url = "jdbc:jtds:sqlserver://"+ip+"/"+database+";integratedSecurity=true";

    public SQLConnection() {
        con = ConnectToDataBase();
    }

    @SuppressLint("NewAPI")
    private static Connection ConnectToDataBase(){
        Connection conn = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url,username,password);
        }catch(Exception ex) {
            Log.e("SQLERROR",ex.getLocalizedMessage());
        }
        return conn;
    }

    public static boolean CheckLogin(String user, String pass) {

        return false;
    }
}

