package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private double numActual = 0;
    private double numAnterior = 0;
    private String operacion = "";
    private boolean nuevaOperacion = true;

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

        // referencias de botones

        Button boton1 = findViewById(R.id.button1);
        Button boton2 = findViewById(R.id.button2);
        Button boton3 = findViewById(R.id.button3);
        Button boton4 = findViewById(R.id.button4);
        Button boton5 = findViewById(R.id.button5);
        Button boton6 = findViewById(R.id.button6);
        Button boton7 = findViewById(R.id.button7);
        Button boton8 = findViewById(R.id.button8);
        Button boton9 = findViewById(R.id.button9);
        Button boton0 = findViewById(R.id.button0);
        Button botonMas = findViewById(R.id.buttonMas);
        Button botonMenos = findViewById(R.id.buttonMenos);
        Button botonPor = findViewById(R.id.buttonX);
        Button botonEntre = findViewById(R.id.buttonDividir);
        Button botonPorcentaje = findViewById(R.id.buttonPorcentaje);
        Button botonCalcular = findViewById(R.id.buttonCalcular);
        Button botonBorrar = findViewById(R.id.buttonDelete);
        Button botonAC = findViewById(R.id.buttonAC);
        Button botonE = findViewById(R.id.buttonE);
        TextView pantalla = findViewById(R.id.pantalla);
        TextView resultado = findViewById(R.id.resultado);

        // pantalla iniciada

        pantalla.setText("0");
        resultado.setText("=");

        // array de botones
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(boton0);
        buttons.add(boton1);
        buttons.add(boton2);
        buttons.add(boton3);
        buttons.add(boton4);
        buttons.add(boton5);
        buttons.add(boton6);
        buttons.add(boton7);
        buttons.add(boton8);
        buttons.add(boton9);
        buttons.add(botonMas);
        buttons.add(botonMenos);
        buttons.add(botonPorcentaje);
        buttons.add(botonPor);
        buttons.add(botonEntre);
        buttons.add(botonCalcular);
        buttons.add(botonBorrar);
        buttons.add(botonAC);
        buttons.add(botonE);


        for (Button boton : buttons) {
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (nuevaOperacion) {
                        pantalla.setText(boton.getText());
                        nuevaOperacion = false;
                    } else {
                        String textActual = pantalla.getText().toString();
                        pantalla.setText(textActual + boton.getText());
                    }
                }
            });
        }

        View.OnClickListener oL = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numAnterior = Double.parseDouble(pantalla.getText().toString()); // toma el texto de pantalla, lo convierte a double y lo guarda en numAnterior
                operacion = ((Button) view).getText().toString();
                nuevaOperacion = true;
                pantalla.setText(String.valueOf(numAnterior) + " " + operacion); //String.valueOf() --> convierte a string los numeros
            }
        };

        botonMas.setOnClickListener(oL);
        botonMenos.setOnClickListener(oL);
        botonPor.setOnClickListener(oL);
        botonEntre.setOnClickListener(oL);
        botonPorcentaje.setOnClickListener(oL);

        // calcular
        botonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!operacion.isEmpty()) {
                    numActual = Double.parseDouble(pantalla.getText().toString()); // toma el texto de pantalla, lo convierte a double y lo guarda en numActual
                    double resultadoOp = calcular(numAnterior, numActual, operacion);
                    pantalla.setText(String.valueOf(numAnterior) + " " + operacion + " " + String.valueOf(numActual));
                    resultado.setText(String.valueOf(resultadoOp));
                    operacion = "";
                    nuevaOperacion = true;
                }
            }
        });

        botonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantalla.setText("0");
                resultado.setText("0");
                numActual = 0;
                numAnterior = 0;
                operacion = "";
                nuevaOperacion = true;
            }
        });
    }

    // metodo para calcular
    private double calcular(double num1, double num2, String ope) { // primer num para calcular, el segundo y por ultimo la operacion que se realiza
        switch (ope) { // cambia dependiendo la operacion que se va a realizar
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "X":
                return num1 * num2;
            case "/":
                if (num2 != 0) { // se evalua si no es cero el divisor
                    return num1 / num2;
                } else {
                    return 0; // si la division es por cero se devuelve cero
                }
            case "%":
                return num1 * (num2 / 100);
            default:
                return num2;
        }
    }
}