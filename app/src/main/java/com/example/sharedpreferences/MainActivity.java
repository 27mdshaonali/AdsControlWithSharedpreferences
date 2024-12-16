package com.example.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name;
    TextView adYes, adNo;
    Button next;
    LinearLayout adContainer;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        next = findViewById(R.id.next);
        adYes = findViewById(R.id.adYes);
        adNo = findViewById(R.id.adNo);
        adContainer = findViewById(R.id.adContainer);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Check if the user has already opted for premium
        boolean isPremium = sharedPreferences.getBoolean("isPremium", false);

        if (!isPremium) {
            // Show the popup since the user has not gone premium
            adContainer.setVisibility(View.VISIBLE);
            adYes.setVisibility(View.VISIBLE);
            adNo.setVisibility(View.VISIBLE);

            // Handle "Yes" button click
            adYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Save the premium status
                    editor.putBoolean("isPremium", true);
                    editor.apply();

                    // Hide the popup permanently
                    adContainer.setVisibility(View.GONE);
                    adYes.setVisibility(View.GONE);
                    adNo.setVisibility(View.GONE);

                    // Inform the user
                    name.setHint("Premium Activated! Enjoy ads-free.");
                }
            });

            // Handle "X" button click
            adNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Temporarily hide the popup
                    adContainer.setVisibility(View.GONE);
                    adYes.setVisibility(View.GONE);
                    adNo.setVisibility(View.GONE);
                }
            });
        } else {
            // If the user is already premium, hide the popup
            adContainer.setVisibility(View.GONE);
            adYes.setVisibility(View.GONE);
            adNo.setVisibility(View.GONE);

            // Optionally, set a hint for premium users
            name.setHint("Welcome back, Premium User!");
        }

        // "Next" button functionality to move to MainActivity2
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = name.getText().toString();

                // Save user's name in SharedPreferences if not empty
                if (!stringName.isEmpty()) {
                    editor.putString("shaon", stringName);
                    editor.apply();
                }

                // Navigate to MainActivity2
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
    }
}
