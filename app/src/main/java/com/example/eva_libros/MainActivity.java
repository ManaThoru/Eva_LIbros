package com.example.eva_libros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageButton ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresar = findViewById(R.id.btn_ingresar);
    }
    public void Ingreso(View view){
        Intent usu = new Intent(MainActivity.this, Ingreso.class);
        startActivity(usu);
    }

}