package com.example.eva_libros;public class Libro {
    int id;
    String editorial;
    String titulo;
    String sku;
    String isbn;
    String idioma;

    public Libro(){

    }

    public Libro(int id, String editorial, String titulo, String sku, String isbn, String idioma) {
        this.id = id;
        this.editorial = editorial;
        this.titulo = titulo;
        this.sku = sku;
        this.isbn = isbn;
        this.idioma = idioma;
    }

    public Libro(String editorial, String titulo, String sku, String isbn, String idioma) {
        this.editorial = editorial;
        this.titulo = titulo;
        this.sku = sku;
        this.isbn = isbn;
        this.idioma = idioma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
