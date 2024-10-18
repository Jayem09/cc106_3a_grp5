package com.example.elibproj;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity {

       EditText username, password;
       Button btnLogin;
       DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameLoginId);
        password = findViewById(R.id.passwordLoginId);
        btnLogin = findViewById(R.id.Loginbtn);
        DB = new DBHelper(this);

        btnLogin.setOnClickListener(view -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                Toast.makeText(activity_login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean checkUserPass = DB.checkusernamepassword(user, pass);
                if (checkUserPass) {
                    Toast.makeText(activity_login.this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), activity_home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(activity_login.this, "Wrong Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView textViewLogin = findViewById(R.id.texttoSignup);

        String text = "Doesn't have account? Sign up";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(activity_login.this, activity_signup.class));
            }
        };

        spannableString.setSpan(clickableSpan, 21, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textViewLogin.setText(spannableString);
        textViewLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
