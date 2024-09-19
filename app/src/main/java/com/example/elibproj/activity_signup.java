package com.example.elibproj;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

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
