package com.example.projetoapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompletedChallengesActivity extends AppCompatActivity {

    private ListView completedChallengesList;
    private ArrayAdapter<String> adapter;
    private List<String> challenges;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "ChallengePrefs";
    private static final String COMPLETED_CHALLENGES_KEY = "completedChallenges";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_challenges);

        completedChallengesList = findViewById(R.id.completedChallengesList);
        challenges = new ArrayList<>();
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        Set<String> completedChallenges = sharedPreferences.getStringSet(COMPLETED_CHALLENGES_KEY, new HashSet<>());
        challenges.addAll(completedChallenges);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, challenges);
        completedChallengesList.setAdapter(adapter);

        Button buttonBackToStart = findViewById(R.id.buttonBackToStart);
        buttonBackToStart.setOnClickListener(v -> finish()); // Volta para a tela anterior
    }
}
