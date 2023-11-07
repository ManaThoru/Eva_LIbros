package com.example.eva_libros;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Libro> lista;
    daoLibro dao;
    Libro li;
    Activity a;
    int id=0;
    public Adaptador(Activity a, ArrayList<Libro> lista, daoLibro dao ){

        this.lista = lista;
        this.a= a;
        this.dao= dao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {li=lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        li=lista.get(i);
        return li.getId();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {

        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }

        li=lista.get(posicion);

        TextView editorial=v.findViewById(R.id.editorial);
        TextView titulo=v.findViewById(R.id.titulo);
        TextView sku=v.findViewById(R.id.sku);
        TextView isbn=v.findViewById(R.id.isbn);
        TextView idioma=v.findViewById(R.id.idioma);
        ImageButton editar= v.findViewById(R.id.btn_editar);
        ImageButton eliminar= v.findViewById(R.id.btn_eliminar);
        editorial.setText(li.getEditorial());
        titulo.setText(li.getTitulo());
        sku.setText(li.getSku());
        isbn.setText(li.getIsbn());
        idioma.setText(li.getIdioma());
        editar.setTag(posicion);
        eliminar.setTag(posicion);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar Registro");
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
                li=lista.get(pos);
                setId(li.getId());
                editorial.setText(li.getEditorial());
                titulo.setText(li.getTitulo());
                sku.setText(li.getSku());
                isbn.setText(li.getIsbn());
                idioma.setText(li.getIdioma());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            li = new Libro(getId(), editorial.getText().toString(),
                                    titulo.getText().toString(),
                                    sku.getText().toString(),
                                    isbn.getText().toString(),
                                    idioma.getText().toString());
                            dao.editar(li);
                            lista=dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a, "Error", Toast.LENGTH_SHORT).show();
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

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos=Integer.parseInt(view.getTag().toString());
                li=lista.get(posicion);
                setId(li.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estas seguro de eliminar");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista=dao.verTodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
