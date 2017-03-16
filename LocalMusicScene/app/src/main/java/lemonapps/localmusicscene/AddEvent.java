package lemonapps.localmusicscene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEvent extends AppCompatActivity {
    EditText eTitle,eArtist,eTime,eDate,eLocation,eDesc;
    Button btnAddEvent;
    SQLConnection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        con = new SQLConnection();
        eTitle = (EditText)findViewById(R.id.showTitleField);
        eArtist = (EditText)findViewById(R.id.bandNameField);
        eTime = (EditText)findViewById(R.id.timeField);
        eDate = (EditText)findViewById(R.id.dateField);
        eLocation = (EditText)findViewById(R.id.venueField);
        eDesc = (EditText) findViewById(R.id.showDescField);
        btnAddEvent = (Button)findViewById(R.id.addEventButton);
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(con.saveEvent(eTitle.getText().toString(),eArtist.getText().toString(),eDate.getText().toString(),eTime.getText().toString(),eDate.getText().toString(),eLocation.getText().toString(),eDesc.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Event Saved",Toast.LENGTH_SHORT);
                    finish();
                }
            }
        });
    }
}
