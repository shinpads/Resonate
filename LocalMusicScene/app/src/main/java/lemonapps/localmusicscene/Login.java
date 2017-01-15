package lemonapps.localmusicscene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText emailTxt;
    EditText passwordTxt;
    Button loginBtn;
    TextView register;
    TextView forgotPassword;
    SQLConnection sqlCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //Start new Connection to database
        sqlCon = new SQLConnection();
        //Load EditTexts and Buttons and TextViews
        emailTxt =(EditText) findViewById(R.id.loginEmailField);
        passwordTxt = (EditText) findViewById(R.id.loginPassField);
        loginBtn =(Button) findViewById(R.id.loginButton);
        register = (TextView) findViewById(R.id.loginNoAcc);
        forgotPassword = (TextView) findViewById(R.id.loginNoPass);
        //Setup Listener for login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { TryLogin(); }});
        //Setup listener for Register Button Text
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {GoRegister();}});

    }
    private void GoRegister(){
        Intent i = new Intent(this,Register.class);
        startActivity(i);
    }
    private void TryLogin(){
        String email = emailTxt.getText().toString();
        String password = passwordTxt.getText().toString();
        if(sqlCon.CheckLogin(email,password)){
            //Go To home Page <------------
            Toast.makeText(getApplicationContext(), R.string.loginSuccess, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), R.string.loginInvalid, Toast.LENGTH_LONG).show();
        }
    }
}
