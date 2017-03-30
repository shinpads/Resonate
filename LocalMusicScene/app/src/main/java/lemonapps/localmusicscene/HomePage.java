package lemonapps.localmusicscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    FloatingActionButton fab;
    ProgressBar loadingCircle;
    private SQLConnection con;
    private List<FeedItem> feedslist;
    private RecyclerView recyclerView;
    private FeedAdapter adapter;
    LinearLayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = false;
    private int offset = 5;
    private static HomePage context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        fab = (FloatingActionButton)findViewById(R.id.fabPlus);
        con = new SQLConnection();
        loadingCircle = (ProgressBar)findViewById(R.id.loadingCircle);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                con = new SQLConnection();
                feedslist.clear();
                offset = 0;
                for(FeedItem i : con.fetchFeed(location,0,5)) {
                    feedslist.add(feedslist.size(),i);
                }
                offset =5;
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedslist = new ArrayList<>();
        adapter = new FeedAdapter(getApplicationContext(),feedslist);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        context = HomePage.this;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy){
                super.onScrolled(rv,dx,dy);

                if (dy > 0){
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                    if (loading == false){
                        if((visibleItemCount + pastVisiblesItems) >= totalItemCount){
                            loading = true;
                            loadingCircle.setVisibility(View.VISIBLE);
                            for(FeedItem i : con.fetchFeed(location,offset,5)) {
                                feedslist.add(feedslist.size(),i);
                            }
                            offset+=5;
                            loading = false;
                            loadingCircle.setVisibility(View.INVISIBLE);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });

        for(FeedItem i : con.fetchFeed(location,0,5)) {
            feedslist.add(feedslist.size(),i);
        }
        loadingCircle.setVisibility(View.INVISIBLE);
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddEvent.class));
            }
        });
        navDrawerUserName.setText(con.getName(Global.email));
        Menu navMen = navigationView.getMenu();
        //HOME
        navMen.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(getApplicationContext(), HomePage.class);
                startActivity(i);
                finish();

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

    public static void viewClicked(int i){
        Intent b = new Intent(context,EventFull.class);
        View v = context.recyclerView.getChildAt(i);
        FeedAdapter feedAdapter = (FeedAdapter)context.recyclerView.getAdapter();
        FeedItem fi = feedAdapter.getItem(i);
        ArrayList<String> values = new ArrayList<>();
        values.add(fi.getTitle()); values.add(fi.getArtist()); values.add(fi.getDate()); values.add(fi.getTime()); values.add(fi.getLocation()); values.add(fi.getCost());
        values.add (fi.getDesc());
        values.add(fi.getAddress());
        b.putStringArrayListExtra("Values",values);
        context.startActivity(b);
    }




    


}