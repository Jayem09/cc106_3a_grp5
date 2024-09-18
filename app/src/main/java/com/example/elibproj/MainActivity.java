package com.example.elibproj;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Edge-to-Edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup login button
        Button button = findViewById(R.id.loginButton);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, dashboard.class);
            startActivity(intent);
        });

        // Setup clickable "Sign Up" text
        TextView textView = findViewById(R.id.tvSignUp);
        String text = "Don't have an account? Sign Up";
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Navigate to SignUpActivity
                Intent intent = new Intent(MainActivity.this, activity_signup.class);
                startActivity(intent);
            }
        };

        // Set the clickable span to the "Sign Up" part of the text
        spannableString.setSpan(clickableSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Apply the SpannableString and make it clickable
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
