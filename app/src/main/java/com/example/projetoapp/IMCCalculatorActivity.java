package com.example.projetoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class IMCCalculatorActivity extends AppCompatActivity {

    private EditText weightInput;
    private EditText heightInput;
    private TextView imcResult;
    private TextView imcDetails; // Novo TextView para exibir os detalhes da faixa de peso e riscos
    private Button calculateButton;
    private Button resetButton;
    private Button backToStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc_calculator);
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        imcResult = findViewById(R.id.imcResult);
        imcDetails = findViewById(R.id.imcDetails); // Ligando o TextView aos detalhes
        calculateButton = findViewById(R.id.calculateButton);
        resetButton = findViewById(R.id.resetButton);
        backToStartButton = findViewById(R.id.backToStartButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateIMC();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });

        backToStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void calculateIMC() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);

            if (height > 0) {
                double imc = weight / (height * height);
                imcResult.setText(String.format("Seu IMC é: %.2f", imc));
                displayIMCDetails(imc);
            } else {
                imcResult.setText("Altura inválida.");
            }
        } else {
            imcResult.setText("Peso e altura não podem estar vazios.");
        }
    }

    private void displayIMCDetails(double imc) {
        String details = "";

        if (imc < 18.5) {
            details = "Abaixo do peso: Possível desnutrição, deficiências nutricionais, e outras condições.";
        } else if (imc >= 18.5 && imc <= 24.9) {
            details = "Peso normal: Baixo risco para problemas de saúde relacionados ao peso.";
        } else if (imc >= 25 && imc <= 29.9) {
            details = "Sobrepeso: Aumento do risco de doenças como hipertensão e doenças cardíacas.";
        } else if (imc >= 30 && imc <= 34.9) {
            details = "Obesidade Grau I: Risco moderado de complicações relacionadas à obesidade.";
        } else if (imc >= 35 && imc <= 39.9) {
            details = "Obesidade Grau II (severa): Risco alto de doenças cardíacas, diabetes tipo 2, entre outras.";
        } else if (imc >= 40) {
            details = "Obesidade Grau III (mórbida): Risco muito alto de complicações graves à saúde.";
        }

        imcDetails.setText(details); // Define o texto com os detalhes
    }

    private void resetFields() {
        weightInput.setText("");
        heightInput.setText("");
        imcResult.setText("Seu IMC será exibido aqui");
        imcDetails.setText("A faixa de peso e riscos será exibida aqui");
    }
}
