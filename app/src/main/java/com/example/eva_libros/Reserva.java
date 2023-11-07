package com.example.eva_libros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Reserva extends AppCompatActivity {
    daoLibro dao;
    Adaptador adaptador;
    ArrayList<Libro> lista;
    Libro li;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        dao= new daoLibro(Reserva.this);
        lista=dao.verTodo();
        adaptador= new Adaptador(this, lista, dao);
        ListView list = findViewById(R.id.lista);
        ImageButton insertar = findViewById(R.id.btn_insertar);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Reserva.this);
                dialog.setTitle("Nuevo Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText editorial = dialog.findViewById(R.id.et_editorial);
                final EditText titulo = dialog.findViewById(R.id.et_titulo);
                final EditText sku = dialog.findViewById(R.id.et_sku);
                final EditText isbn = dialog.findViewById(R.id.et_isbn);
                final EditText idioma = dialog.findViewById(R.id.et_idioma);
                ImageButton guardar = dialog.findViewById(R.id.btn_agregar);
                ImageButton cancelar = dialog.findViewById(R.id.btn_cancelar);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            li = new Libro(editorial.getText().toString(),
                                    titulo.getText().toString(),
                                    sku.getText().toString(),
                                    isbn.getText().toString(),
                                    idioma.getText().toString());
                            dao.insertar(li);
                            lista = dao.verTodo();
                            adaptador.notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(getApplication(),"ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}