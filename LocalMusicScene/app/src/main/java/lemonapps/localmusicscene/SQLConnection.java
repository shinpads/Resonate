package lemonapps.localmusicscene;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.*;

import java.sql.*;
import java.util.Formatter;

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

    public  boolean CheckLogin(String email, String pass) {
        //check if email or password is empty
        if(email.trim().equals("") || pass.trim().equals("")){
            return false;
        }
        pass = encryptPassword(pass);

        String query = "SELECT * FROM Signup WHERE Client_Email='"+email+"' and Client_Password ='"+pass+"'";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }catch (Exception ex){
            Log.e("SQL",ex.getLocalizedMessage());
        }
        return false;
    }
    public boolean AddAccountToDB(String fn, String ln, String email, String pass){
        pass = encryptPassword(pass);
        Log.i("PASSWORD-ENCRYPY",pass);
        String update = "INSERT INTO Signup(Client_FName,Client_LName,Client_Email,Client_Password) VALUES('"+fn+"','"+ln+"','"+email+"','"+pass+"')";

        try{
            Statement statement = con.createStatement();
            statement.executeUpdate(update);
            return true;
        }catch (Exception ex){
            Log.e("SQL-ADD",ex.getLocalizedMessage());
        }

        return false;
    }

    private static String encryptPassword(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return sha1;
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}

