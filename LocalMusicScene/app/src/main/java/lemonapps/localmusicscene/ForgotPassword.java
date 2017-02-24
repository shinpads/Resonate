package lemonapps.localmusicscene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class ForgotPassword extends AppCompatActivity {
    EditText emailTxt;
    Button sndEmailBtn;
    public static String code = "";
    public static String email = "";
    SQLConnection con;
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

        con = new SQLConnection();
        }
    private void SendEmailCode() {
        if (!con.checkEmailInDB(emailTxt.getText().toString())){
            Toast.makeText(getApplicationContext(),"Invalid Email.",Toast.LENGTH_SHORT).show();
            return;
        }
        email = emailTxt.getText().toString();
        code = makeCode();
        if(EmailSend.sendEmail(emailTxt.getText().toString(),"Confirmation Email",code)){
            Toast.makeText(getApplicationContext(),"Email Sent",Toast.LENGTH_SHORT).show();
            Intent i  = new Intent(this,ForgotPasswordCode.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Failed to Send",Toast.LENGTH_SHORT).show();
        }
    }
    public static String makeCode(){
        String code = "";
        Random ran = new Random();
        for(int i=0; i<10; i++){
            int rand = ran.nextInt(26);
            code += String.valueOf(rand);
        }
        return code;
    }
    }


