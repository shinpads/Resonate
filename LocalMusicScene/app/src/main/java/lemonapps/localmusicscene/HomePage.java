package lemonapps.localmusicscene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    TextView changeLocationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        changeLocationBtn = (TextView) findViewById(R.id.taxbarLocationText);
        changeLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placesAutoComplete();
            }
        });
    }
    private void placesAutoComplete(){
        Toast.makeText(getApplicationContext(),"Change Location!",Toast.LENGTH_SHORT).show();

    }
}