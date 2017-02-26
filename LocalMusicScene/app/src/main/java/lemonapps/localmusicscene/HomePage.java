package lemonapps.localmusicscene;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import org.w3c.dom.Text;

public class HomePage extends AppCompatActivity {
    TextView locationTxt;
    ImageButton sideBarLines;
    static final int placeAutoCompleteReqestCode = 1;
    String location;
    DrawerLayout navDrawer;
    SQLConnection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        navDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        con = new SQLConnection();
        NavigationView navigationView = (NavigationView)findViewById(R.id.navDrawer);
        RelativeLayout navHeaderLayout = (RelativeLayout)navigationView.getHeaderView(0);
        TextView navDrawerUserName = (TextView)navHeaderLayout.getChildAt(0);
        navDrawerUserName.setText(con.getName(Global.email));

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
        //super.onActivityResult(requestCode,resultCode,data);
    }


}