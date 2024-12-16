package com.example.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sharedpreferences.monetize.AdMob;

public class MainActivity2 extends AppCompatActivity {

    TextView name, adStatus;
    SharedPreferences sharedPreferences;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Initialize views
        LinearLayout adContainerView = findViewById(R.id.adContainerView);
        name = findViewById(R.id.name);
        adStatus = findViewById(R.id.adStatus);

        // Retrieve SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("shaon", "Guest");
        boolean isPremium = sharedPreferences.getBoolean("isPremium", false);

        // Set greeting text
        name.setText("Hello, " + userName + "!");

        // Check if user is premium
        if (isPremium) {
            // Hide ads for premium users
            adContainerView.setVisibility(LinearLayout.GONE);
            adStatus.setText("You are using the Ads-free version. Enjoy!");
        } else {
            // Show ads for non-premium users
            AdMob.sdkInitialize(this);
            AdMob.setBannerAd(adContainerView, this);
            adStatus.setText("You are using the Ads version.");
        }
    }
}
