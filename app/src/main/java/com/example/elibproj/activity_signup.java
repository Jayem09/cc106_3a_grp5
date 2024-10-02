package com.example.elibproj;


import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_signup extends AppCompatActivity {

    EditText username, password, confimPassword;
    Button signUp;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.signUpUsernameId);
        password = (EditText) findViewById(R.id.signUpPasswordId);
        confimPassword = (EditText) findViewById(R.id.confimPassword);
        signUp = (Button)  findViewById(R.id.signupButtonId);
        DB = new DBHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText() .toString();
                String pass = password.getText() .toString();
                String repass = confimPassword.getText() .toString();

                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(activity_signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false){
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true){
                                Toast.makeText(activity_signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),activity_home.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(activity_signup.this, "Register failed", Toast.LENGTH_SHORT).show();
                            }
                            
                        }
                        else{
                            Toast.makeText(activity_signup.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                        
                    }else{
                        Toast.makeText(activity_signup.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }

                }


;            }
        });

        TextView textViewLogin = findViewById(R.id.textviewLogin);

        String text = "Existing user? Go to Log in";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                // navigate to activity_login
                startActivity(new Intent(activity_signup.this, activity_login.class));
            }
        };

        spannableString.setSpan(clickableSpan, 21, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 

        textViewLogin.setText(spannableString);
        textViewLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
