package lemonapps.localmusicscene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventFull extends AppCompatActivity {
    TextView title,artist,date,time,location,cost,desc,address;
    ImageView picture;
    ImageButton ticket;
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


    }
}
