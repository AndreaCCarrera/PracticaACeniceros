/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Comanda;
import Model.Plato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import practicaaceniceros.PracticaACeniceros;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class FXMLAnadirController implements Initializable {

    private Comanda listaAnadir;

    @FXML
    private Label label;

    @FXML
    private TextField textnombre;

    @FXML
    private TextField textTipo;

    @FXML
    private TextField textIngredientes;

    @FXML
    private CheckBox textAlergenos;

    @FXML
    private TextField textPrecio;

    @FXML
    private void handleButtonVolver(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleButtonReset(ActionEvent event) {
        textnombre.setText("");
        textTipo.setText("");
        textIngredientes.setText("");
        textAlergenos.setSelected(false);
        textPrecio.setText("");
        label.setText("");
    }

    @FXML
    private void handleButtonAnadir(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

        if (textnombre != null && !textnombre.getText().isEmpty()
                && textTipo != null && !textTipo.getText().isEmpty()
                && textIngredientes != null && !textIngredientes.getText().isEmpty()
                && textPrecio != null && !textPrecio.getText().isEmpty()) {

            String nombre = textnombre.getText();
            String tipo = textTipo.getText();
            String[] ingredientesArray = textIngredientes.getText().split(","); 
            List<String> ingredientes = new ArrayList<>();
            for (String ingrediente : ingredientesArray) {
                String[] parts = ingrediente.split(":");
                if (parts.length >= 1) { 
                    ingredientes.add(parts[0].trim());
                }
            }
            boolean alergenos = textAlergenos.isSelected();
            double precio = Double.parseDouble(textPrecio.getText());

            Plato plato = new Plato(nombre, tipo, ingredientes, alergenos, precio);

            alerta.setTitle("CONFIRMAR AÑADIR");
            alerta.setHeaderText("¿Está seguro de que desea añadir este plato?");
            alerta.setContentText("El plato será incluido en la base de datos.");
            alerta.initStyle(StageStyle.UTILITY);
            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                if (listaAnadir.agregarPlato(plato)) {
                    label.setTextFill(Color.GREEN);
                    label.setText("Plato añadido correctamente");
                    textnombre.setText("");
                    textTipo.setText("");
                    textIngredientes.setText("");
                    textAlergenos.setSelected(false);
                    textPrecio.setText("");
                } else {
                    label.setText("El plato ya existe en la lista");
                }
            } else {
                label.setText("Operación cancelada");
            }
        } else {
            label.setTextFill(Color.RED);
            label.setText("Por favor, rellena todos los campos.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaAnadir = PracticaACeniceros.platos;
    }
}