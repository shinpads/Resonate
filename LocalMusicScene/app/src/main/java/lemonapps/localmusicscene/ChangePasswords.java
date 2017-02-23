package lemonapps.localmusicscene;



import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

public class ChangePasswords extends AppCompatActivity {
    Button ChangeButton;
    EditText OldPass;
    EditText NewPass;
    EditText ConfirmNewPass;

    SQLConnection con = new SQLConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pass);
        ChangeButton = (Button)findViewById(R.id.ChgPassButton);
        OldPass = (EditText)findViewById(R.id.ChgPassOld);
        NewPass = (EditText)findViewById(R.id.ChgPassNew);
        ConfirmNewPass = (EditText)findViewById(R.id.ChgPassNewConfirm);


        ChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passCheck();
            }
        });
    }

    private void passCheck () {
        if (con.CheckLogin(Login.emailStr,OldPass.getText().toString())){
            if (NewPass.getText().toString().equals(ConfirmNewPass.getText().toString())){

            }
        }

    }


}
