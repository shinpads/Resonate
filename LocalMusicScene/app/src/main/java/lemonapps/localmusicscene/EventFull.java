package lemonapps.localmusicscene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventFull extends AppCompatActivity {
    TextView title,artist,date,time,location,cost,desc,address;
    ImageView picture;
    ImageButton ticket;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_show);
        Intent intent = getIntent();
        List<String> values = intent.getStringArrayListExtra("Values");
        title = (TextView)findViewById(R.id.fullShowTitle);
        artist = (TextView)findViewById(R.id.fullShowBand);
        date = (TextView)findViewById(R.id.fullShowDate);
        picture = (ImageView)findViewById(R.id.fullShowImage);
        time = (TextView)findViewById(R.id.fullShowTime);
        location = (TextView)findViewById(R.id.fullShowVenue);
        cost = (TextView)findViewById(R.id.fullShowCost);
        desc =  (TextView)findViewById(R.id.fullShowDesc);
        ticket = (ImageButton)findViewById(R.id.fullShowSaveEvent);
        title.setText(values.get(0));
        artist.setText(values.get(1));
        date.setText(values.get(2));
        time.setText(values.get(3));
        location.setText(values.get(4));
        cost.setText(values.get(5));
        desc.setText(values.get(6));
        address.setText(values.get(7));
        id = Integer.parseInt(values.get(8));
        ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                
            }
        });

    }
}
