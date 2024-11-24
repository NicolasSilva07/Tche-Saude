package com.example.projetoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StopwatchActivity extends AppCompatActivity {

    private TextView stopwatchTextView;
    private Button buttonStart, buttonStop, buttonReset, buttonBack;

    private Handler handler;
    private Runnable runnable;
    private int seconds = 0;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        stopwatchTextView = findViewById(R.id.stopwatchTextView);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);
        buttonReset = findViewById(R.id.resetButton);
        buttonBack = findViewById(R.id.buttonBack);

        handler = new Handler();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    running = true;
                    startTimer();
                }
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0;
                updateStopwatch();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StopwatchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startTimer() {
        runnable = new Runnable() {
            @Override
            public void run() {
                if (running) {
                    seconds++;
                    updateStopwatch();
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.post(runnable);
    }

    private void updateStopwatch() {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        stopwatchTextView.setText(String.format("%02d:%02d", minutes, secs));
    }
}
