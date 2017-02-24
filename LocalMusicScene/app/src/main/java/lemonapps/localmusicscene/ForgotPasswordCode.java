package lemonapps.localmusicscene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordCode extends AppCompatActivity {
    String code = "";
    Button resetBtn;
    EditText codeTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_forgot_pass);
        code = ForgotPassword.code;
        resetBtn = (Button) findViewById(R.id.ResetButton);
        codeTxt =  (EditText)findViewById(R.id.resetCode);
    }
}
