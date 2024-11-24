package com.example.projetoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        ImageView logoImageView = findViewById(R.id.logoImageView);
        TextView textViewTop = findViewById(R.id.textViewTop);
        TextView textViewBottom = findViewById(R.id.textViewBottom);
        Button backToMainButton = findViewById(R.id.backToMainButton);
        logoImageView.setImageResource(R.drawable.logo_old);
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreditsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
