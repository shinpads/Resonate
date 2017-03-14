package lemonapps.localmusicscene;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ForkJoinTask;

public class ForgotPasswordCode extends AppCompatActivity {
    String code = "";
    String email = "";
    Button resetBtn;
    EditText codeTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_forgot_pass);
        code = ForgotPassword.code;
        email = ForgotPassword.email;
        resetBtn = (Button) findViewById(R.id.ResetButton);
        codeTxt =  (EditText)findViewById(R.id.resetCode);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryCode();
            }
        });
    }
    private void tryCode(){
        String entCode = codeTxt.getText().toString();
        if(entCode.equals(code)){
            Intent i = new Intent(this,ForgotPaswordChangePassword.class);
            i.putExtra("email",email);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Invalid Code",Toast.LENGTH_SHORT).show();
        }
    }
}
