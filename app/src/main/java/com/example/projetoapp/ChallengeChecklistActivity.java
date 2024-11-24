package com.example.projetoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ChallengeChecklistActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "ChallengePrefs";
    private static final String COMPLETED_CHALLENGES_KEY = "completedChallenges";
    private final float textSize = 18;
    private CheckBox[] challengeCheckBoxes;
    private TextView congratulationsMessage;
    private List<String> challengeList = new ArrayList<>();
    private Random random = new Random();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_checklist);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        challengeCheckBoxes = new CheckBox[]{
                findViewById(R.id.checkBoxChallenge1),
                findViewById(R.id.checkBoxChallenge2),
                findViewById(R.id.checkBoxChallenge3),
                findViewById(R.id.checkBoxChallenge4)
        };

        congratulationsMessage = findViewById(R.id.congratulationsMessage);

        Button buttonReset = findViewById(R.id.buttonReset);
        Button buttonBackToMain = findViewById(R.id.buttonBackToMain);
        Button buttonViewCompleted = findViewById(R.id.buttonViewCompletedChallenges);

        populateChallengeList();
        generateRandomChallenges();

        buttonReset.setOnClickListener(v -> generateRandomChallenges());
        buttonBackToMain.setOnClickListener(v -> {
            Intent intent = new Intent(ChallengeChecklistActivity.this, MainActivity.class);
            startActivity(intent);
        });

        buttonViewCompleted.setOnClickListener(v -> {
            Intent intent = new Intent(ChallengeChecklistActivity.this, CompletedChallengesActivity.class);
            startActivity(intent);
        });

        for (CheckBox checkBox : challengeCheckBoxes) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                checkChallenges();
                saveCompletedChallenges();
            });
            checkBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        }
    }

    private void populateChallengeList() {
        challengeList.clear();
        challengeList.add("Beber 8 copos de água: Complete a meta de hidratação diária.");
        challengeList.add("Fazer 30 minutos de atividade física: Caminhada, corrida, ou qualquer exercício.");
        challengeList.add("Comer uma porção de frutas: Inclua uma fruta na sua dieta.");
        challengeList.add("Incluir uma porção de vegetais: Adicione vegetais às suas refeições.");
        // Adicione todos os outros desafios aqui
    }

    private void generateRandomChallenges() {
        Collections.shuffle(challengeList);
        for (int i = 0; i < challengeCheckBoxes.length; i++) {
            String challenge = challengeList.get(i);
            String displayText = challenge.split(":")[0];
            challengeCheckBoxes[i].setText(displayText);
            challengeCheckBoxes[i].setChecked(false);
        }
        congratulationsMessage.setText("");
    }

    private void checkChallenges() {
        boolean allChecked = true;
        for (CheckBox checkBox : challengeCheckBoxes) {
            if (!checkBox.isChecked()) {
                allChecked = false;
                break;
            }
        }
        if (allChecked) {
            congratulationsMessage.setText("Parabéns! Desafios diários concluídos com sucesso.");
        } else {
            congratulationsMessage.setText("");
        }
    }

    private void saveCompletedChallenges() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> completedChallenges = new HashSet<>();
        for (int i = 0; i < challengeCheckBoxes.length; i++) {
            if (challengeCheckBoxes[i].isChecked()) {
                completedChallenges.add(challengeList.get(i));
            }
        }
        editor.putStringSet(COMPLETED_CHALLENGES_KEY, completedChallenges);
        editor.apply();
    }
}
