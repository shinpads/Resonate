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
        pass = generateHash(pass);

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
        pass = generateHash(pass);
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
    public String GetSalt(String email){
        String query = "SELECT SALT FROM Signup WHERE email ='" +email+"'";
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                return rs.getString(0);
            }
        }catch(Exception ex){
            Log.e("SQL SALT",ex.getLocalizedMessage());
        }
        return "";
    }
    public boolean CheckEmailInDB(String email){
        String query = "SELECT * FROM Signup WHERE email='"+email+"'";
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }catch (Exception ex){
            Log.e("SQL CHECK EMAIL",ex.getLocalizedMessage());
        }
        return false;
    }
    public  String hashPassword(String password,String email){
        String salt = GetSalt(email);
        String pepper = "XA DX GG XG XX XF FX AD FG XD GG DG GG";
        return generateHash(password + salt + pepper);
    }
    public static String generateHash(String string) {
        Keccak keccak = new Keccak(1600);
        String hex;
        try {
            hex = keccak.getHexStringByByteArray(string.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        // 576,64 is values for sha-512
        String hashed = keccak.getHash(hex, 576, 64);
        return hashed;
    }

}

