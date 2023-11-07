package com.example.eva_libros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    private TextView Resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Resultado = findViewById(R.id.txt_resul);
        String nombreUsuario = getIntent().getStringExtra("Usuario");
        Resultado.setText("Bienvenido(a) " + nombreUsuario);

    }
    public void Visitar(View view){
        Intent visitar = new Intent(Menu.this, Visitanos.class);
        startActivity(visitar);
    }
    public void Reserva(View view){
        Intent reserva = new Intent(Menu.this, Reserva.class);
        startActivity(reserva);
    }
}