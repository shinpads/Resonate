package lemonapps.localmusicscene;



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
<<<<<<< HEAD
        setContentView(R.layout.change_pass);
        ChangeButton = (Button)findViewById(R.id.ChgPassButton);
        OldPass = (EditText)findViewById(R.id.ChgPassOld);
        NewPass = (EditText)findViewById(R.id.ChgPassNew);
        ConfirmNewPass = (EditText)findViewById(R.id.ChgPassNewConfirm);
=======
        setContentView(R.layout.activity_change_passwords);
        changeButton = (Button)findViewById(R.id.ChgPassButton);
        oldPass = (EditText)findViewById(R.id.ChgPassOld);
        newPass = (EditText)findViewById(R.id.ChgPassNew);
        confirmNewPass = (EditText)findViewById(R.id.ChgPassNewConfirm);
>>>>>>> origin/master


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
