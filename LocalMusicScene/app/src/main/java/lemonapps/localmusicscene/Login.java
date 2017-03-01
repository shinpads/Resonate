package lemonapps.localmusicscene;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;

public class Login extends AppCompatActivity {
    EditText emailTxt;
    EditText passwordTxt;
    Button loginBtn;
    TextView register;
    TextView forgotPassword;
    SQLConnection sqlCon;
    public static String emailStr;

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
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ForgotPasswordActivity();} });
        checkIfLoggedIn();

    }
    private void ForgotPasswordActivity(){
        Intent i = new Intent(this,ForgotPassword.class);
        startActivity(i);
    }
    private void GoRegister(){
        Intent i = new Intent(this,Register.class);
        startActivity(i);
    }
    private void TryLogin(){
        String email = emailTxt.getText().toString();
        String emailStr = email;
        String password = passwordTxt.getText().toString();
        if(sqlCon.CheckLogin(email,password)){
            //Go To home Page <------------
            saveLogged();
            Intent i = new Intent(this,HomePage.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Global.email = email;
            startActivity(i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), R.string.loginInvalid, Toast.LENGTH_SHORT).show();
        }
    }
    private void checkIfLoggedIn(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.masonjar.app",Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("logged",false)){
            Intent i = new Intent(Login.this,HomePage.class);
            startActivity(i);
            finish();
        }
    }
    private void saveLogged(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.masonjar.app",Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("logged",true).apply();

    }

}
