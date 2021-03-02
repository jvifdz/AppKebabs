package com.example.donner;

public class Kebab {

    long id;
    String tipo;
    boolean checkbox;
    String precio;

    public Kebab(long id, String tipo, boolean checkbox, String precio) {
        this.id = id;
        this.tipo = tipo;
        this.checkbox = checkbox;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
