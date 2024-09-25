package com.example.elibproj;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent; // <--- Add this line

import androidx.appcompat.app.AppCompatActivity;

public class activity_signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView textViewLogin = findViewById(R.id.textviewLogin); // assume this is the ID of your TextView

        String text = "Existing user? Go to Log in";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                // navigate to LoginActivity
                startActivity(new Intent(activity_signup.this, activity_login.class));
            }
        };

        spannableString.setSpan(clickableSpan, 21, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // set the span for the "Log in" text

        textViewLogin.setText(spannableString);
        textViewLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }
}