package lemonapps.localmusicscene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText firstTxt;
    EditText lastTxt;
    EditText emailTxt;
    EditText passwordTxt;
    EditText passwordConfirmTxt;
    Button signupBtn;
    SQLConnection sqlCon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        firstTxt = (EditText)findViewById(R.id.signUpFnameField);
        lastTxt = (EditText)findViewById(R.id.signUpLnameField);
        emailTxt = (EditText)findViewById(R.id.signUpEmailField);
        passwordTxt = (EditText)findViewById(R.id.signUpPassField);
        passwordConfirmTxt = (EditText)findViewById(R.id.signUpChekPass);
        signupBtn = (Button)findViewById(R.id.signUpButton);
        sqlCon = new SQLConnection();
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {RegisterAccount(); }});

    }
    public void RegisterAccount(){
        //make sure no fields are empty
        EditText[] fields = {firstTxt,lastTxt,emailTxt,passwordTxt,passwordConfirmTxt};
        for(EditText field : fields){
            if(field.getText().toString().trim().equals("")){
                Toast.makeText(getApplicationContext(),"Fill in All Fields",Toast.LENGTH_SHORT).show();
                field.requestFocus();
                break;
            }
        }
        if(!passwordTxt.getText().toString().equals(passwordConfirmTxt.getText().toString())){
            Toast.makeText(getApplicationContext(),"Passwords Don't Match",Toast.LENGTH_SHORT).show();
            passwordTxt.requestFocus();
        }

    }
}
