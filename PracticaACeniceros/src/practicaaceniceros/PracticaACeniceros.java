/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaaceniceros;

import Model.Comanda;
import Model.Plato;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author cloro
 */
public class PracticaACeniceros extends Application {
    
    public static Comanda platos = new Comanda();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        generarPlatos();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void generarPlatos(){
        
        
       List<String> ingredientes1 = new ArrayList<>();

        ingredientes1.add("Harina");
        ingredientes1.add("Azúcar");
        ingredientes1.add("Mantequilla");
        ingredientes1.add("Huevos");
        ingredientes1.add("Leche");
        
        List<String> ingredientes2 = new ArrayList<>();
        ingredientes2.add("Lechuga");
        ingredientes2.add("Tomate");
        ingredientes2.add("Pepino");
        ingredientes2.add("Aceitunas");
        ingredientes2.add("Feta");

        List<String> ingredientes3 = new ArrayList<>();
        ingredientes3.add("Pan");
        ingredientes3.add("Mostaza");
        ingredientes3.add("Cheddar");
        ingredientes3.add("Carne");
        ingredientes3.add("Bacon");

        List<String> ingredientes4 = new ArrayList<>();
        ingredientes4.add("Plátano");
        ingredientes4.add("Leche");
        ingredientes4.add("Miel");
        ingredientes4.add("Avena");
        ingredientes4.add("Yogur");
        
        platos.agregarPlato(new Plato ("Bizcocho", "Postre", ingredientes1, true, 4.5));
        platos.agregarPlato(new Plato ("Ensalada", "Primero", ingredientes2, false, 12.5));
        platos.agregarPlato(new Plato ("Hamburguesa", "Segundo", ingredientes3, true, 9.5));
        platos.agregarPlato(new Plato ("Batido", "Bebida", ingredientes4, true, 3.5));
       
    }
    
}
