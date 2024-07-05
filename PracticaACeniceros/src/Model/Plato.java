/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cloro
 */
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.util.List;

public class Plato {

    private StringProperty nombre;
    private StringProperty tipo;
    private List<String> ingredientes;
    private BooleanProperty alergenos;
    private DoubleProperty precio;

    public Plato(String nombre, String tipo, List<String> ingredientes, boolean alergenos, double precio) {
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo = new SimpleStringProperty(tipo);
        this.ingredientes = new ArrayList<>(ingredientes);
        this.alergenos = new SimpleBooleanProperty(alergenos);
        this.precio = new SimpleDoubleProperty(precio);
    }

    public Plato() {
        this.nombre = new SimpleStringProperty();
        this.tipo = new SimpleStringProperty();
        this.ingredientes = new ArrayList<>();
        this.alergenos = new SimpleBooleanProperty();
        this.precio = new SimpleDoubleProperty();
    }

    public String getNombre() {
        return this.nombre.get();
    }

    public String getTipo() {
        return this.tipo.get();
    }

    public List<String> getIngredientes() {
        return new ArrayList<>(ingredientes);
    }

    public String getIngredientesString() {
        StringBuilder sb = new StringBuilder();
        for (String ingrediente : ingredientes) {
            sb.append(ingrediente).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
    }

    public Boolean getAlergenos() {
        return this.alergenos.get();
    }

    public Double getPrecio() {
        return this.precio.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = new ArrayList<>(ingredientes);
    }

    public void setAlergenos(Boolean alergenos) {
        this.alergenos.set(alergenos);
    }

    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }

    public StringProperty Nombre() {
        return this.nombre;
    }

    public StringProperty Tipo() {
        return this.tipo;
    }

    public BooleanProperty Alergenos() {
        return this.alergenos;
    }

    public DoubleProperty Precio() {
        return this.precio;
    }

    public boolean agregarIngrediente(String ingrediente) {
        boolean b = false;
        if (!ingredientes.contains(ingrediente)) {
            ingredientes.add(ingrediente);
            b = true;
        }
        return b;
    }

    public void mostrarIngredientes() {
        if (ingredientes.isEmpty()) {
            System.out.println("Aún no hay ingredientes en el plato.");
        } else {
            System.out.println("Ingredientes en el plato:");
            for (String ingrediente : ingredientes) {
                System.out.println(ingrediente);
            }
        }
    }

    public boolean borrarIngrediente(String ingrediente) {
        return ingredientes.remove(ingrediente);
    }

    @Override
    public String toString() {
        return "Plato{" +
                "nombre=" + nombre +
                ", tipo=" + tipo +
                ", ingredientes=" + ingredientes +
                ", alergenos=" + alergenos +
                ", precio=" + precio +
                '}';
    }
}