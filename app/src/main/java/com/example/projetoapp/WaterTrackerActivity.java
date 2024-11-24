package com.example.projetoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WaterTrackerActivity extends AppCompatActivity {

    private int totalMilliliters = 0;
    private TextView waterCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tracker);

        waterCountText = findViewById(R.id.waterCountText);
        Button button200ml = findViewById(R.id.button200ml);
        Button button500ml = findViewById(R.id.button500ml);
        Button button800ml = findViewById(R.id.button800ml);
        Button button1liter = findViewById(R.id.button1liter);
        Button buttonReset = findViewById(R.id.buttonReset);
        Button buttonBackToMain = findViewById(R.id.buttonBackToMain);


        button200ml.setOnClickListener(v -> addWater(200));
        button500ml.setOnClickListener(v -> addWater(500));
        button800ml.setOnClickListener(v -> addWater(800));
        button1liter.setOnClickListener(v -> addWater(1000)); // 1 litro = 1000 ml


        buttonReset.setOnClickListener(v -> resetWaterCount());

        buttonBackToMain.setOnClickListener(v -> {
            Intent intent = new Intent(WaterTrackerActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void addWater(int amount) {
        totalMilliliters += amount;
        updateWaterCount();
    }

    private void resetWaterCount() {
        totalMilliliters = 0;
        updateWaterCount();
    }

    private void updateWaterCount() {

        int totalLiters = totalMilliliters / 1000; // Parte inteira dos litros
        int remainderMilliliters = totalMilliliters % 1000; // Mililitros restantes


        if (totalMilliliters >= 2000) {
            waterCountText.setText("Meta diária alcançada!");
        } else if (totalLiters > 0) {
            waterCountText.setText(String.format("Total: %d L %d ml", totalLiters, remainderMilliliters));
        } else {
            waterCountText.setText(String.format("Total: %d ml", remainderMilliliters));
        }
    }
}
