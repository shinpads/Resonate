package lemonapps.localmusicscene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEvent extends AppCompatActivity {
    EditText eTitle,eArtist,eLocation,eDesc,eCost,eAddress;
    TimePicker eTime;
    DatePicker eDate;
    Button btnAddEvent;
    SQLConnection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        con = new SQLConnection();
        eCost = (EditText)findViewById(R.id.priceField);
        eTitle = (EditText)findViewById(R.id.showTitleField);
        eArtist = (EditText)findViewById(R.id.bandNameField);
        eAddress = (EditText)findViewById(R.id.venueAddressField);
        eTime = (TimePicker) findViewById(R.id.timeField);
        eDate = (DatePicker) findViewById(R.id.dateField);
        eLocation = (EditText)findViewById(R.id.venueField);
        eDesc = (EditText) findViewById(R.id.showDescField);
        btnAddEvent = (Button)findViewById(R.id.addEventButton);
        final Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        final String dateStr = new SimpleDateFormat("MMM dd, yyyy").format(date);
        calendar.set(eDate.getDayOfMonth(),eDate.getMonth(),eDate.getYear());
        final String time = ""+eTime.getCurrentHour() + ":"+ eTime.getCurrentMinute();
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(con.saveEvent(eTitle.getText().toString(),eArtist.getText().toString(),dateStr,time,eLocation.getText().toString(),eCost.getText().toString(),eDesc.getText().toString(),eAddress.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Event Saved",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
