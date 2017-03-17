package lemonapps.localmusicscene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);

 Button profChgPassButton = (Button)findViewById(R.id.profchgpass);
    //
       profChgPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {
                Intent i = new Intent(Profile.this, ChangePasswords.class);
                startActivity(i);

           }
       });

    }


}
