package lemonapps.localmusicscene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {
    ImageView changeLocationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        changeLocationBtn = (ImageView)findViewById(R.id.taxbarLocationButton);
        changeLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placesAutoComplete();
            }
        });
    }
    private void placesAutoComplete(){

    }
}