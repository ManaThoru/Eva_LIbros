package com.example.eva_libros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoLibro {
    SQLiteDatabase bd;
    ArrayList<Libro> lista= new ArrayList<Libro>();
    Libro li;
    Context ct;
    String nombreBD= "BDLibros";

    String tabla = "create table if not exists libros(id integer primary key autoincrement, editorial text, titulo text, sku text, isbn text,idioma text)";

    public daoLibro(Context li){
        this.ct=li;
        bd=li.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        bd.execSQL(tabla);

    }
    public boolean insertar(Libro li){
        ContentValues contenedor = new ContentValues();
        contenedor.put("editorial", li.getEditorial());
        contenedor.put("titulo", li.getTitulo());
        contenedor.put("sku", li.getSku());
        contenedor.put("isbn", li.getIsbn());
        contenedor.put("idioma", li.getIdioma());
        return (bd.insert("libros", null, contenedor))>0;
    }
    public  boolean eliminar(int id){
        return (bd.delete("libros", "id="+id, null))>0;
    }
    public boolean editar(Libro li){
        ContentValues contenedor = new ContentValues();
        contenedor.put("editorial", li.getEditorial());
        contenedor.put("titulo", li.getTitulo());
        contenedor.put("sku", li.getSku());
        contenedor.put("isbn", li.getIsbn());
        contenedor.put("idioma", li.getIdioma());
        return (bd.update("libros", contenedor,"id="+li.getId(),null ))>0;
    }
    public ArrayList<Libro>verTodo(){
        lista.clear();
        Cursor cursor = bd.rawQuery("select * from libros",null);
        if (cursor!=null && cursor.getCount()>0 ){
            cursor.moveToFirst();
            do {
                lista.add(new Libro(cursor.getInt(  0),
                        cursor.getString(  1), cursor.getString( 2),
                        cursor.getString(  3), cursor.getString( 4),
                        cursor.getString( 5)));
            }while (cursor.moveToNext());
        }
        return lista;
    }
    public Libro verUno(int posicion){
        Cursor cursor = bd.rawQuery("select * from libros",null);
        cursor.moveToPosition(posicion);
         li =new Libro(cursor.getInt(0),
                cursor.getString(  1), cursor.getString( 2),
                cursor.getString(  3), cursor.getString( 4),
                cursor.getString( 5));
        return li;
    }
}
