package lemonapps.localmusicscene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class ManageBand extends AppCompatActivity {
    EditText nameTxt;
    Spinner genereSpin;
    EditText  bioTxt;
    EditText locationTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_band);
        SQLConnection sqlcon = new SQLConnection();

    }
}
