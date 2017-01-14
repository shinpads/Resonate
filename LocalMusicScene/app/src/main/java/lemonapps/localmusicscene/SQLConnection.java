package lemonapps.localmusicscene;
import android.database.CursorJoiner;
import android.util.Log;

import java.sql.*;
/**
 * Created by Rob on 1/12/2017.
 */
public class SQLConnection {
    //public Connection connection = null;
    private static final String ip = "localhost"; //192.68.2.183
    private static final String dataBaseName = "LOCALMUSICSCENE";
    private static final String username = "Java";
    private static final String password = "thesolohoarder@123";
    private static final String driverClass = "net.sourceforge.jtds.jdbc.Driver";
    private static final String url = "jdbc:jtds:sqlserver://" + ip + ";" + "databaseName=" + dataBaseName + ";user=" + username + ";password=" + password + ";";

    public static Connection ConnectToDataBase(){
        Connection conn = null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();
            ResultSet resultSet =  statement.executeQuery("");
            return conn;

        }catch(Exception ex) {
            Log.e("SQLERROR",ex.getLocalizedMessage());
        }
        return conn;
    }
}

