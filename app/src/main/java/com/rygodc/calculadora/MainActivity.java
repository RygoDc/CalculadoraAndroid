package com.rygodc.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView pantalla;
    private StringBuilder expresion = new StringBuilder();
    private boolean esDecimal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Button buttonCalcular = findViewById(R.id.buttonCalcular);
        Button buttonPorcentaje = findViewById(R.id.buttonPorcentaje);
        Button buttonCE = findViewById(R.id.buttonCE);
        Button buttonC = findViewById(R.id.buttonC);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        pantalla = findViewById(R.id.pantalla);

        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);
        buttons.add(buttonMasMenos);
        buttons.add(buttonPunto);
        buttons.add(buttonSuma);
        buttons.add(buttonResta);
        buttons.add(buttonDividir);
        buttons.add(buttonMultiplicar);
        buttons.add(buttonCalcular);
        buttons.add(buttonPorcentaje);
        buttons.add(buttonCE);
        buttons.add(buttonC);
        buttons.add(buttonDelete);

        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view.getId() == R.id.button0) {
                        agregarNumero("0");
                    } else if (view.getId() == R.id.button1) {
                        agregarNumero("1");
                    } else if (view.getId() == R.id.button2) {
                        agregarNumero("2");
                    } else if (view.getId() == R.id.button3) {
                        agregarNumero("3");
                    } else if (view.getId() == R.id.button4) {
                        agregarNumero("4");
                    } else if (view.getId() == R.id.button5) {
                        agregarNumero("5");
                    } else if (view.getId() == R.id.button6) {
                        agregarNumero("6");
                    } else if (view.getId() == R.id.button7) {
                        agregarNumero("7");
                    } else if (view.getId() == R.id.button8) {
                        agregarNumero("8");
                    } else if (view.getId() == R.id.button9) {
                        agregarNumero("9");
                    } else if (view.getId() == R.id.buttonPunto) {
                        if (!esDecimal) {
                            agregarNumero(".");
                            esDecimal = true;
                        }
                    } else if (view.getId() == R.id.buttonSuma) {
                        agregarOperador("+");
                    } else if (view.getId() == R.id.buttonResta) {
                        agregarOperador("-");
                    } else if (view.getId() == R.id.buttonDividir) {
                        agregarOperador("/");
                    } else if (view.getId() == R.id.buttonMultiplicar) {
                        agregarOperador("*");
                    } else if (view.getId() == R.id.buttonCalcular) {
                        calcular();
                    } else if (view.getId() == R.id.buttonC || view.getId() == R.id.buttonCE) {
                        limpiarCalculadora();
                    } else if (view.getId() == R.id.buttonPorcentaje) {
                        calcularPorcentaje();
                    } else if (view.getId() == R.id.buttonMasMenos) {
                        cambiarSigno();
                    } else if (view.getId() == R.id.buttonDelete) {
                        eliminarUltimoDigito();
                    }
                    pantalla.setText(expresion.toString().isEmpty() ? "0" : expresion.toString());
                }
            });
        }
    }

    private void agregarNumero(String digito) {
        if (expresion.length() == 0 && digito.equals("0")) {
            return;  // No permitir múltiples ceros iniciales
        }
        expresion.append(digito);
    }

    private void agregarOperador(String op) {
        if (!expresion.toString().isEmpty() && Character.isDigit(expresion.charAt(expresion.length() - 1))) {
            expresion.append(op);
            esDecimal = false;
        }
    }

    private void calcular() {
        try {
            double resultado = evaluarExpresion(expresion.toString());
            pantalla.setText((resultado % 1 == 0) ? String.valueOf((int) resultado) : String.valueOf(resultado));
            expresion.setLength(0);
            expresion.append(pantalla.getText().toString());
            esDecimal = expresion.toString().contains(".");
        } catch (Exception e) {
            pantalla.setText("Error");
            expresion.setLength(0);
        }
    }

    private void limpiarCalculadora() {
        expresion.setLength(0);
        pantalla.setText("0");
        esDecimal = false;
    }

    private void eliminarUltimoDigito() {
        if (expresion.length() > 0) {
            expresion.deleteCharAt(expresion.length() - 1);
            esDecimal = expresion.toString().contains(".");
        }
    }

    private void cambiarSigno() {
        if (!expresion.toString().isEmpty()) {
            double num = evaluarExpresion(expresion.toString());
            num = -num;
            expresion.setLength(0);
            expresion.append(num);
            pantalla.setText(expresion.toString());
        }
    }

    private void calcularPorcentaje() {
        if (!expresion.toString().isEmpty()) {
            double num = evaluarExpresion(expresion.toString()) / 100;
            expresion.setLength(0);
            expresion.append(num);
            pantalla.setText(expresion.toString());
        }
    }

    private double evaluarExpresion(String expr) {
        ArrayList<Double> valores = new ArrayList<>();
        ArrayList<Character> operadores = new ArrayList<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();

                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                i--;
                valores.add(Double.parseDouble(sb.toString()));
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operadores.isEmpty() && prioridad(operadores.get(operadores.size() - 1)) >= prioridad(c)) {
                    double b = valores.remove(valores.size() - 1);
                    double a = valores.remove(valores.size() - 1);
                    valores.add(operar(operadores.remove(operadores.size() - 1), a, b));
                }
                operadores.add(c);
            }
        }

        while (!operadores.isEmpty()) {
            double b = valores.remove(valores.size() - 1);
            double a = valores.remove(valores.size() - 1);
            valores.add(operar(operadores.remove(operadores.size() - 1), a, b));
        }

        return valores.get(0);
    }

    private int prioridad(char operador) {
        if (operador == '+' || operador == '-') return 1;
        if (operador == '*' || operador == '/') return 2;
        return 0;
    }

    private double operar(char operador, double a, double b) {
        switch (operador) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: return 0;
        }
    }
}
