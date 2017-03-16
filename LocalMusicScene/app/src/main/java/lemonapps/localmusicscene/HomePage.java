package lemonapps.localmusicscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import org.w3c.dom.Text;

import java.util.*;

public class HomePage extends AppCompatActivity {
    TextView locationTxt;
    ImageButton sideBarLines;
    static final int placeAutoCompleteReqestCode = 1;
    String location;
    DrawerLayout navDrawer;
    private SQLConnection con;
    private List<FeedItem> feedslist;


    private RecyclerView recyclerView;
    private FeedAdapter adapter;
    private int totalXScroll = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedslist = new ArrayList<>();
        adapter = new FeedAdapter(getApplicationContext(),feedslist);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy){
                super.onScrolled(rv,dx,dy);
                totalXScroll += dy;
                Log.i("Scroll",""+recyclerView.computeVerticalScrollOffset()+" "+recyclerView.computeVerticalScrollRange());
            }
        });
        for(int i = 0; i < 10; i ++) {
            feedslist.add(new FeedItem("test one two"));
        }
        navDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);

        con = new SQLConnection();
        NavigationView navigationView = (NavigationView)findViewById(R.id.navDrawer);
        RelativeLayout navHeaderLayout = (RelativeLayout)navigationView.getHeaderView(0);
        
        TextView navDrawerUserName = (TextView)navHeaderLayout.getChildAt(0);
        ImageButton profileButton = (ImageButton)navHeaderLayout.getChildAt(1);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, Profile.class);
                startActivity(i);
            }
        });

        navDrawerUserName.setText(con.getName(Global.email));
        Menu navMen = navigationView.getMenu();
        //HOME
        navMen.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        //SAVED EVENTS
        navMen.getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        //FAVOURITE BANDS
        navMen.getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        //MANAGE BAND
        navMen.getItem(3).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(HomePage.this, ManageBand.class);
                startActivity(i);
                return false;


            }
        });
        //NOTIFICATIONS
        navMen.getItem(4).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        //LOGOUT
        navMen.getItem(5).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(HomePage.this,Login.class);
                Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = HomePage.this.getSharedPreferences("com.masonjar.app", Context.MODE_PRIVATE);
                sharedPreferences.edit().putBoolean("logged",false).apply();
                startActivity(i);
                finish();
                return false;
            }
        });
        sideBarLines = (ImageButton)findViewById(R.id.sidebarLines);
        sideBarLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navDrawer.openDrawer(Gravity.LEFT);
            }
        });
        locationTxt = (TextView) findViewById(R.id.taxbarLocationText);
        locationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placesAutoComplete();
            }
        });
    }
    private void placesAutoComplete(){

        try {
            Intent i = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(this);
            startActivityForResult(i,placeAutoCompleteReqestCode);

        }catch (Exception ex){
            Log.e("Places Error",ex.getMessage());
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == placeAutoCompleteReqestCode){
            if(resultCode == RESULT_OK){
                Place place = PlaceAutocomplete.getPlace(this, data);
                location = place.getName().toString();
                locationTxt.setText(location);
            }
        }
    }




    


}