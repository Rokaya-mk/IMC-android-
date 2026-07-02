package com.example.myapplication;

import static android.widget.Toast.makeText;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editPoids, editTaille;
    private Button btnCalculer;
    private TextView textResultat, textCategorie;
    private ImageView imageCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison avec les vues du layout
        editPoids = findViewById(R.id.editPoids);
        editTaille = findViewById(R.id.editTaille);
        btnCalculer = findViewById(R.id.btnCalculer);
        textResultat = findViewById(R.id.textResultat);
        textCategorie = findViewById(R.id.textCategorie);
        imageCategorie = findViewById(R.id.imageCategorie);

        btnCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculerIMC();
            }
        });
    }

    private void calculerIMC() {
        String poidsStr = editPoids.getText().toString().trim();
        String tailleStr = editTaille.getText().toString().trim();

        if (poidsStr.isEmpty() || tailleStr.isEmpty()) {
            makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        double poids = Double.parseDouble(poidsStr);
        double tailleCm = Double.parseDouble(tailleStr);
        double tailleM = tailleCm / 100.0;

        double imc = poids / (tailleM * tailleM);

        // Arrondi à 2 décimales
        double imcArrondi = Math.round(imc * 100.0) / 100.0;

        textResultat.setText("Votre IMC est: " + imcArrondi);

        afficherCategorie(imcArrondi);
    }

    private void afficherCategorie(double imc) {
        imageCategorie.setVisibility(View.VISIBLE);

        if (imc < 18.5) {
            textCategorie.setText("Maigreur");
            imageCategorie.setImageResource(R.drawable.maigreur);
        } else if (imc < 25) {
            textCategorie.setText("Normal");
            imageCategorie.setImageResource(R.drawable.normal);
        } else if (imc < 30) {
            textCategorie.setText("Surpoids");
            imageCategorie.setImageResource(R.drawable.surpoids);
        } else if (imc < 40) {
            textCategorie.setText("Obésité modérée");
            imageCategorie.setImageResource(R.drawable.obesite_moderee);
        } else {
            textCategorie.setText("Obésité sévère");
            imageCategorie.setImageResource(R.drawable.obesite_severe);
        }
    }
}