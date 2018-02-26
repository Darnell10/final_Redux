package c4q.com.final_redux;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String SHARED_PREF_KEY = "login_key";
    private SharedPreferences sharedPreferences;
    private EditText username;
    private EditText password;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        sharedPreferences = getSharedPreferences(SHARED_PREF_KEY,MODE_PRIVATE);

            Intent logoff = new Intent(LoginActivity.this,BreedsActivity.class);
            if (!sharedPreferences.getString("username","").equals("")
                    && !sharedPreferences.getString("password","").equals("")
                    && !logoff.getBooleanExtra("logoff",false)){
                Intent intent = new Intent(LoginActivity.this,BreedsActivity.class);
                startActivity(intent);
            } else {
                username.setText("");
                password.setText("");
            }



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if username blank, set error message of "please enter username" on the username EditText.
                if (username.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter Username", Toast.LENGTH_SHORT).show();
                }
                //if password blank, set error message of "please enter password" on password EditText.
                if (password.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter Password ", Toast.LENGTH_SHORT).show();
                }
                //
                if (password.getText().toString().contains(username.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Password cannnot contain Username", Toast.LENGTH_SHORT).show();
                }

                if (!username.getText().toString().equals("")
                    && !password.getText().toString().equals("")
                        && !password.getText().toString().contains(username.getText().toString())){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.putString("username",username.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this,BreedsActivity.class);
                    startActivity(intent);

                }
            }
        });

    }
}
