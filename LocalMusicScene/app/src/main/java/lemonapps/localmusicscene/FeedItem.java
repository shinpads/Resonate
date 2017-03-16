package lemonapps.localmusicscene;



/**
 * Created by Rob on 2017-03-14.
 */

public class FeedItem {
    private String title = "";
    private String artist = "";
    private String date = "";
    private String time = "";
    private String location = "";
    private String cost = "";
    private String desc = "";

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public String getArtist(){return artist;}
    public void setArtist(String artist){this.artist = artist;}

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public String getTime(){return time;}
    public void setTime(String time){this.time = time;}

    public String getCost() {return cost;}
    public void setCost(String cost){this.cost = cost;}

    public String getLocation(){return location;}
    public void setLocation(String location){this.location = location;}

    public String getDesc(){return desc;}
    public void setDesc(String desc){this.desc = desc;}
}
