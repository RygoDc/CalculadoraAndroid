package com.rygodc.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonMasMenos = findViewById(R.id.buttonMasMenos);
        Button buttonPunto = findViewById(R.id.buttonPunto);
        Button buttonSuma = findViewById(R.id.buttonSuma);
        Button buttonResta = findViewById(R.id.buttonResta);
        Button buttonDividir = findViewById(R.id.buttonDividir);
        Button buttonMultiplicar = findViewById(R.id.buttonMultiplicar);
        Button buttonCalcular = findViewById(R.id.buttonCacular);

        TextView pantalla = findViewById(R.id.pantalla);
        pantalla.setText("");

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoPantalla = (String) pantalla.getText();
                textoPantalla += "0";
                pantalla.setText(textoPantalla);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoPantalla = (String) pantalla.getText();
                textoPantalla += "1";
                pantalla.setText(textoPantalla);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoPantalla = (String) pantalla.getText();
                textoPantalla += "2";
                pantalla.setText(textoPantalla);
            }
        });
        buttonSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoPantalla = (String) pantalla.getText();
                textoPantalla += "+";
                pantalla.setText(textoPantalla);
            }
        });

        buttonResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoPantalla = (String) pantalla.getText();
                textoPantalla += "-";
                pantalla.setText(textoPantalla);
            }
        });

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoPantallaAux = (String) pantalla.getText().toString();
                if(textoPantallaAux.contains("+")) {
                    String[] partes = textoPantallaAux.split("\\+");
                    if (partes.length == 2) {
                        try {
                            double num1 = Double.parseDouble(partes[0].trim());
                            double num2 = Double.parseDouble(partes[1].trim());
                            double result = num1 + num2;
                            // Do something with the result, e.g., display it in another TextView
                            //textViewResult.setText(String.valueOf(result));
                        } catch (NumberFormatException e) {
                            // Handle the case where the input is not valid numbers
                            //Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Handle the case where there is no "+" symbol or multiple "+" symbols
                    }
                }
                if(textoPantallaAux.contains("-")) {
                    String[] partes = textoPantallaAux.split("\\-");
                    int resultado = 0;
                    for (String parte : partes) {
                        resultado -= Integer.parseInt(parte);
                    }
                    pantalla.setText(String.valueOf(resultado));
                }
            }
        });

    }
}