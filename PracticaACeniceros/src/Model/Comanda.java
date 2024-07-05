/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cloro
 */
public class Comanda {
    
    private HashMap<String,Plato> platos;
     
     public Comanda(HashMap<String,Plato> platos) {
        this.platos = platos;
    }
    
    public Comanda() {
         this.platos = new HashMap<String,Plato>();
     }
    
    public boolean agregarPlato(Plato plato) {
        boolean b = false;
        if (platos.containsKey(plato.getNombre())==false) {
            platos.put(plato.getNombre(),plato);
            b = true;
        }
        return b;
    }

    public void mostrarPlatos() {
        if (platos.isEmpty()) {
            System.out.println("Aún no hay platos en la comanda.");
        } else {
            System.out.println("Platos en la comanda:");
            for (Map.Entry<String, Plato> entry : platos.entrySet()) {
                String nombre = entry.getKey();
                Plato plato = entry.getValue();
                System.out.println(plato + ": " + nombre);
            }
        }
    }
    
     public Plato buscarPlato(String nombre) {
        
        Plato plan = null;
        if (platos.containsKey(nombre)==true) {
           for (Map.Entry<String, Plato> entry : platos.entrySet()) {
                if(nombre.equals(entry.getKey())){
                    plan = entry.getValue();
                }
           }
           
        }
        return plan;
    }
     
     public boolean borrarPlato(Plato plato) {
        boolean b = false;
        if (platos.containsKey(plato.getNombre())) {
            platos.remove(plato.getNombre());
            b = true;
       
        }
        return b;
}
     
     public boolean modificarPlato(Plato plan) {
        boolean b = false;
        if (platos.containsKey(plan.getNombre())) {
            platos.put(plan.getNombre(), plan);
            b = true;
        
        }
        return b;
        
    }
     
     public HashMap<String, Plato> obtenerPlatos() {
         return this.platos;
    }
}
    
    

