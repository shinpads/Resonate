package lemonapps.localmusicscene;



import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

public class ChangePasswords extends AppCompatActivity {
    Button changeButton;
    EditText oldPass;
    EditText newPass;
    EditText confirmNewPass;

    SQLConnection con = new SQLConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pass);
        changeButton = (Button)findViewById(R.id.ChgPassButton);
        oldPass = (EditText)findViewById(R.id.ChgPassOld);
        newPass = (EditText)findViewById(R.id.ChgPassNew);
        confirmNewPass = (EditText)findViewById(R.id.ChgPassNewConfirm);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passCheck();
            }
        });
    }

    private void passCheck () {
        if (con.CheckLogin(Login.emailStr,oldPass.getText().toString())){
            if (newPass.getText().toString().equals(confirmNewPass.getText().toString())){
                con.changeAccountPassword(Login.emailStr,newPass.getText().toString());
            }
        }

    }


}
