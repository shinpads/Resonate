package lemonapps.localmusicscene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPaswordChangePassword extends AppCompatActivity {
    String email = "";
    EditText passwordTxt;
    EditText confirmPasswordTxt;
    Button changePassBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_resetcode);
        email = getIntent().getStringExtra("email");
        passwordTxt = (EditText)findViewById(R.id.resetChgPass);
        confirmPasswordTxt = (EditText)findViewById(R.id.resetChekPass);
        changePassBtn = (Button)findViewById(R.id.ResetButton);
        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePasswords();
            }
        });

    }
    private void changePasswords(){
        String p1 = passwordTxt.getText().toString();
        String p2 = confirmPasswordTxt.getText().toString();
        if(!p2.equals(p1)){
            Toast.makeText(ForgotPaswordChangePassword.this, "Passwords Don't Match", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            SQLConnection con = new SQLConnection();
            con.changeAccountPassword(email,p1);
            Toast.makeText(getApplicationContext(),"Password changed", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
