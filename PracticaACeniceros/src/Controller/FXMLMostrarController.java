/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Plato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import practicaaceniceros.PracticaACeniceros;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLMostrarController implements Initializable {

    @FXML
    private Button btVolver;

    @FXML
    private TableView<Plato> tablaPersonas;

    @FXML
    private TableColumn<Plato, String> cNombre;

    @FXML
    private TableColumn<Plato, String> cTipo;

    @FXML
    private TableColumn<Plato, String> cIngredientes;

    @FXML
    private TableColumn<Plato, Boolean> cAlergenos;

    @FXML
    private TableColumn<Plato, Double> cPrecio;

    private ObservableList<Plato> listaPlatos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        cIngredientes.setCellValueFactory(cellData -> {
            List<String> ingredientes = cellData.getValue().getIngredientes();
            return new javafx.beans.property.SimpleStringProperty(ingredientes.toString());
        });
        cAlergenos.setCellValueFactory(new PropertyValueFactory<>("alergenos"));
        cPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        listaPlatos = FXCollections.observableArrayList(PracticaACeniceros.platos.obtenerPlatos().values());

        tablaPersonas.setItems(listaPlatos);
    }

    @FXML
    private void handleButtonVolver(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}