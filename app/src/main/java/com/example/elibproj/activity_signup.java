package com.example.elibproj;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_signup extends AppCompatActivity {

    EditText username, password, confirmPassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.signUpUsernameId);
        password = (EditText) findViewById(R.id.signUpPasswordId);
        confirmPassword = (EditText) findViewById(R.id.signUpCpasswordId);
        signup = (Button) findViewById(R.id.signUpbutton);
        DB = new DBHelper(this);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText() .toString();
                String pass = password.getText() . toString();
                String Cpass = confirmPassword.getText() .toString();

                if(user.equals("") || pass.equals("") || Cpass.equals(""))
                    Toast.makeText(activity_signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(Cpass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false){
                            Boolean insert = DB.inserData(user, pass);
                            if (insert == true ){
                                Toast.makeText(activity_signup.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), homeDashboard.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(activity_signup.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(activity_signup.this, "Password not match!", Toast.LENGTH_SHORT).show();
                        }

                    }

                }


            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), activity_login.class);
                startActivity(intent);
            }
        });

        // Setup clickable "Login" text
        TextView textView = findViewById(R.id.tvLogin);
        String text = "Existing user? Go to Login";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Navigate back to MainActivity (Login screen)
                Intent intent = new Intent(activity_signup.this, activity_login.class);
                startActivity(intent);
            }
        };

        // Set the clickable span to the "Login" part of the text
        spannableString.setSpan(clickableSpan, 20, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Apply the SpannableString and make it clickable
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
