package lemonapps.localmusicscene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ManageBand extends AppCompatActivity {
    EditText nameTxt;
    Spinner genereSpin;
    EditText  bioTxt;
    EditText locationTxt;
    Button saveButton;
    SQLConnection sqlcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_bands);

        sqlcon = new SQLConnection();
        nameTxt = (EditText)findViewById(R.id.manageBandName);
        genereSpin = (Spinner) findViewById(R.id.manageBandGenreSpinner);
        bioTxt = (EditText) findViewById(R.id.manageBandBio);
        //locationTxt = (EditText) findViewById(R.id.manageBandHomeTown);
        saveButton = (Button)findViewById(R.id.saveBandInfoButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { saveBandInfo(); }});

    }

    private void saveBandInfo(){/*
        if(sqlcon.saveBandInfo(nameTxt.getText().toString(),nameTxt.getText().toString(),genereSpin.getSelectedItem().toString(),bioTxt.getText().toString(),locationTxt.getText().toString())){
            Toast.makeText(getApplicationContext(),"Information Saved",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Failed To Save",Toast.LENGTH_SHORT).show();
        }
        */
        //TODO SAVE BAND INFO

    }
}
