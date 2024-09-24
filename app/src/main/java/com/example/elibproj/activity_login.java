package com.example.elibproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity {

    private EditText username, password;
    private Button btnLogin;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameLoginId);
        password = findViewById(R.id.passwordLoginId);
        btnLogin = findViewById(R.id.Loginbtn);
        DB = new DBHelper(this);
        Button btnSignup = (Button)findViewById(R.id.buttontoSignuppage);



        if (btnSignup != null) {
            btnSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent intent = new Intent(activity_login.this, activity_signup.class);
                        Log.d("LoginActivity", "Starting SignupActivity");
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.e("LoginActivity", "Error starting SignupActivity", e);
                    }
                }
            });
        } else {
            Log.e("LoginActivity", "btnSignup is null");
        }


        btnLogin.setOnClickListener(view -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                Toast.makeText(activity_login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean checkUserPass = DB.checkusernamepassword(user, pass);
                if (checkUserPass) {
                    Toast.makeText(activity_login.this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), homeDashboard.class);
                    startActivity(intent);
                    finish(); // Optional: finish login activity to prevent going back
                } else {
                    Toast.makeText(activity_login.this, "Wrong Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
