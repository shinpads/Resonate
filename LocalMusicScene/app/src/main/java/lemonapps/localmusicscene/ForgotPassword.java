package lemonapps.localmusicscene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {
    EditText emailTxt;
    Button sndEmailBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass);

        emailTxt = (EditText)findViewById(R.id.forgotEmailField);
        sndEmailBtn = (Button)findViewById(R.id.forgotButton);
        sndEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendEmailCode();
            }
        });
        });
    }
    private void SendEmailCode() {
        EmailSend.SendEmail(emailTxt.getText().toString(),"Confirmation Email","Ronald Wesley");
    }
}
