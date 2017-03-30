package lemonapps.localmusicscene;
import android.annotation.SuppressLint;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.security.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Rob on 1/12/2017.
 */
public class SQLConnection {
    private Connection con;
    public static  String ip = "";
    private static final String database = "master";
    private static final String username = "Java";
    private static final String password ="thesolohoarder@123";
    private static final String driverClass = "net.sourceforge.jtds.jdbc.Driver";
    public static  String url = "jdbc:jtds:sqlserver://"+ip+"/"+database+";integratedSecurity=true";

    public SQLConnection() {
        con = ConnectToDataBase();
    }

    @SuppressLint("NewAPI")
    private static Connection ConnectToDataBase(){
        Connection conn = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            //get ipaddress
            InetAddress inetAddress = InetAddress.getByName("Resonate.redirectme.net");
            ip = inetAddress.getHostAddress() + ":1433";
            url = "jdbc:jtds:sqlserver://"+ip+"/"+database+";integratedSecurity=true";
            //setup driver
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url,username,password);
        }catch(Exception ex) {
            Log.e("SQLERROR",ex.getLocalizedMessage());
        }
        return conn;
    }

    /*
        INDIVIDUAL USER STUFF

     */

    public  boolean CheckLogin(String email, String pass) {
        //check if email or password is empty
        if(email.trim().equals("") || pass.trim().equals("")){
            return false;
        }
        String salt = getSalt(email);
        pass = hashPassword(pass,email,salt);

        String query = "SELECT * FROM Signup WHERE Client_Email='"+email+"' and Client_Password ='"+pass+"'";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                //con.close(); //close connection to sql server
                return true;
            }
        }catch (Exception ex){
            Log.e("SQL",ex.getLocalizedMessage());
        }
        return false;
    }
    public boolean AddAccountToDB(String fn, String ln, String email, String pass){
        String salt = makeSalt();
        pass = hashPassword(pass,email,salt);
        Log.i("SALTMAKER",salt);
        Log.i("PASSWORD-ENCRYPY",pass);
        String update = "INSERT INTO Signup(Client_FName,Client_LName,Client_Email,Client_Password,SALT) VALUES('"+fn+"','"+ln+"','"+email+"','"+pass+"','"+salt+"')";
        //make sure email isn't already in database
        if(checkEmailInDB(email)){
            return false;
        }
        try{
            Statement statement = con.createStatement();
            statement.executeUpdate(update);
            return true;
        }catch (Exception ex){
            Log.e("SQL-ADD",ex.getLocalizedMessage());
        }

        return false;
    }
    public boolean changeAccountPassword(String email, String newpassword){
        newpassword = hashPassword(newpassword,email,getSalt(email));
        String update = "UPDATE Signup SET Client_Password = '" +newpassword + "' WHERE Client_Email = '"+email+"';";
        try{
            Statement statement = con.createStatement();
            statement.executeUpdate(update);
            return true;
        }catch(Exception ex){
            Log.e("CHANGE PASSWORD ERROR",ex.getMessage());
        }
        return false;
    }
    public String getSalt(String email){
        String query = "SELECT SALT FROM Signup WHERE Client_Email ='" +email+"'";
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                return rs.getString(1);
            }
        }catch(Exception ex){
            Log.e("SQL SALT",ex.getLocalizedMessage());
        }
        return "";
    }
    public String makeSalt(){
        char[] alp = {'a','b','b','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String salt = "";
        Random ran = new Random();
        for(int i=0; i<10; i++){
            int rand = ran.nextInt(26);
            salt += alp[rand];
        }
        return salt;
    }
    public boolean checkEmailInDB(String email){
        String query = "SELECT * FROM Signup WHERE Client_Email='"+email+"'";
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
    public  String getName(String email){
        String query = "SELECT Client_FName,Client_LName FROM Signup WHERE Client_Email = '"+email+"'";

        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                return rs.getString(1) +" "+ rs.getString(2);
            }
        }catch (Exception ex){
            Log.e("SQL GET NAME", ex.getMessage());
        }
        return "Null";
    }
    public  String hashPassword(String password,String email ,String salt){
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

    /*
        BAND DATA BASE STUFF

     */
    public boolean saveBandInfo(String name, String newName,String genere, String bio, String location){
        String update;
        try {
            Statement statement = con.createStatement();
            if (checkBandInDB(name)) {
                //UPDATE
                update = "";

            } else {
                //INSERT
                update = "";
            }
            statement.executeUpdate(update);
            return true;
        }catch (Exception ex){
            Log.e("SQL-SAVEBANDINFO",ex.getLocalizedMessage());
        }
        return false;
    }
    public boolean checkBandInDB(String bandName){
        String query = "";
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                return true;
            }
        }catch (Exception ex){
            Log.e("BANDINDBSQL", ex.getLocalizedMessage());
        }
        return false;
    }
    /*
        HOME PAGE FEED STUFF

     */
    public List<FeedItem> fetchFeed(String location, int offset, int ammount){
        String query = "SELECT * FROM Eventz ORDER BY Event_ID DESC OFFSET " + offset + " ROWS FETCH NEXT "+ ammount +" ROWS ONLY";
        FeedItem curFeedItem;
        List<FeedItem> feedItems = new ArrayList<FeedItem>();
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Log.i("FETCH FEED","FROUND FEED");
            while(rs.next()){
                curFeedItem = new FeedItem();
                curFeedItem.setArtist(rs.getString("Event_artist"));
                curFeedItem.setCost(rs.getString("Event_cost"));
                curFeedItem.setDate(rs.getString("Event_date"));
                curFeedItem.setDesc(rs.getString("Event_desc"));
                curFeedItem.setLocation(rs.getString("Event_location"));
                curFeedItem.setTime(rs.getString("Event_time"));
                curFeedItem.setTitle(rs.getString("Event_title"));
                curFeedItem.setAddress(rs.getString("Event_address"));
                feedItems.add(curFeedItem);
            }
            return feedItems;
        }catch(Exception ex){
            Log.e("FETCH DATA",ex.getMessage());
        }
        return new ArrayList<>();
    }
    public boolean saveEvent(String a,String b,String c,String d,String e,String f,String g,String h){
        String update = "INSERT INTO Eventz(Event_title,Event_artist,Event_date,Event_time,Event_location,Event_cost,Event_desc,Event_address) VALUES('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"')";
        try{
            Statement statement = con.createStatement();
            statement.executeUpdate(update);
            return true;
        }catch (Exception ex){
            Log.e("SAVE EVENT", ex.getMessage());
        }
        return false;
    }


    /*
    GENERAL STUFF

    */
    public void closeConnection(){
        try {
            con.close();
        }catch (Exception ex){
            Log.e("SQLCLOSE ERROR",ex.getMessage());
        }
    }

}

