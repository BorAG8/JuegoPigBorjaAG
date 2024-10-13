package com.example.juegopigborjaag;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.juegopigborjaag.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ImageView dadoImagen;
    TextView resultadoTexto;
    TextView jugador1Puntos;
    TextView jugador2Puntos;
    TextView jugador1Fondo;
    TextView jugador2Fondo;
    Button botonLanzar;
    Button botonPasar;
    Random random;
    boolean jugador1Turno = true;
    int puntosTotalesJugador1 = 0;
    int puntosTotalesJugador2 = 0;
    int puntosRonda = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dadoImagen = (ImageView) findViewById(R.id.dado);
        resultadoTexto = (TextView) findViewById(R.id.tirada);
        jugador1Puntos = (TextView) findViewById(R.id.global1);
        jugador2Puntos = (TextView) findViewById(R.id.global2);
        jugador1Fondo = (TextView) findViewById(R.id.textView);
        jugador2Fondo = (TextView) findViewById(R.id.textView2);
        botonLanzar = (Button) findViewById(R.id.tirarDado);
        botonPasar = (Button) findViewById(R.id.lootear);
        random = new Random();

        Toast.makeText(this, "Comienza jugador 1!!", Toast.LENGTH_SHORT).show();

        botonLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDado();
            }
        });
        botonPasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarTurno();
            }
        });
    }

    private void lanzarDado() {
        int valorDado = random.nextInt(6) + 1;

        switch (valorDado) {
            case 1:
                dadoImagen.setImageResource(R.drawable.dado1);
                botonLanzar.setText("FALLO!");
                botonLanzar.setBackgroundColor(Color.RED);
                puntosRonda = 0;
                cambiarTurno();
                break;
            case 2:
                dadoImagen.setImageResource(R.drawable.dado2);
                botonLanzar.setText("Lanzar");
                botonLanzar.setBackgroundColor(Color.BLUE);
                actualizarPuntosRonda(valorDado);
                break;
            case 3:
                dadoImagen.setImageResource(R.drawable.dado3);
                botonLanzar.setText("Lanzar");
                botonLanzar.setBackgroundColor(Color.BLUE);
                actualizarPuntosRonda(valorDado);
                break;
            case 4:
                dadoImagen.setImageResource(R.drawable.dado4);
                botonLanzar.setText("Lanzar");
                botonLanzar.setBackgroundColor(Color.BLUE);
                actualizarPuntosRonda(valorDado);
                break;
            case 5:
                dadoImagen.setImageResource(R.drawable.dado5);
                botonLanzar.setText("Lanzar");
                botonLanzar.setBackgroundColor(Color.BLUE);
                actualizarPuntosRonda(valorDado);
                break;
            case 6:
                dadoImagen.setImageResource(R.drawable.dado6);
                botonLanzar.setText("Lanzar");
                botonLanzar.setBackgroundColor(Color.BLUE);
                actualizarPuntosRonda(valorDado);
                break;
        }
        resultadoTexto.setText(String.valueOf(puntosRonda));
    }

    private void pasarTurno() {
        if (jugador1Turno) {
            puntosTotalesJugador1 += puntosRonda;
            puntosRonda = 0;
            if (puntosTotalesJugador1 >= 20) {
                Toast.makeText(this, "Jugador 1 ha ganado!!!", Toast.LENGTH_LONG).show();
                reiniciarJuego();
                return;
            }
        } else {
            puntosTotalesJugador2 += puntosRonda;
            puntosRonda = 0;
            if (puntosTotalesJugador2 >= 20) {
                Toast.makeText(this, "Jugador 2 ha ganado!!!", Toast.LENGTH_LONG).show();
                reiniciarJuego();
                return;
            }
        }
        cambiarTurno();
        actualizar();
    }

    private void actualizarPuntosRonda(int valorDado) {
        puntosRonda += valorDado;
    }

    private void cambiarTurno() {
        if (jugador1Turno) {
            jugador2Fondo.setBackgroundColor(Color.parseColor("#4CAF50"));
            jugador1Fondo.setBackgroundColor(Color.parseColor("#FF5722"));
            jugador1Turno = false;
            Toast.makeText(this, "Turno del Jugador 2", Toast.LENGTH_SHORT).show();
        } else {
            jugador2Fondo.setBackgroundColor(Color.parseColor("#FF5722"));
            jugador1Fondo.setBackgroundColor(Color.parseColor("#4CAF50"));
            jugador1Turno = true;
            Toast.makeText(this, "Turno del Jugador 1", Toast.LENGTH_SHORT).show();
        }
    }

    private void actualizar() {
        jugador1Puntos.setText(String.valueOf(puntosTotalesJugador1));
        jugador2Puntos.setText(String.valueOf(puntosTotalesJugador2));
        resultadoTexto.setText(String.valueOf(puntosRonda));
    }

    private void reiniciarJuego() {
        puntosTotalesJugador1 = 0;
        puntosTotalesJugador2 = 0;
        puntosRonda = 0;
        jugador1Turno = true;
        actualizar();
    }
}