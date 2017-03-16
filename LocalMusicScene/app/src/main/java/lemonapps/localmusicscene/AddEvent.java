package lemonapps.localmusicscene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddEvent extends AppCompatActivity {
    EditText eTitle,eArtist,eTime,eDate,eLocation,eDesc;
    Button btnAddEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        eTitle = (EditText)findViewById(R.id.showTitleField);
        eArtist = (EditText)findViewById(R.id.bandNameField);
        eTime = (EditText)findViewById(R.id.timeField);
        eDate = (EditText)findViewById(R.id.dateField);
        eLocation = (EditText)findViewById(R.id.venueField);
        eDesc = (EditText) findViewById(R.id.showDescField);
        btnAddEvent = (Button)findViewById(R.id.addEventButton);
    }
}
