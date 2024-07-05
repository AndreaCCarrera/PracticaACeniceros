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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import practicaaceniceros.PracticaACeniceros;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class FXMLEditarController implements Initializable {

    private Comanda listaEditar;

    @FXML
    private Label label;

    @FXML
    private TextField textnombre;

    @FXML
    private TextField texttipo;

    @FXML
    private TextField textprecio;

    @FXML
    private TextField listaingredientes;

    @FXML
    private CheckBox checkalergenos;

    @FXML
    private TableView<Plato> tablaPlatos;

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

    @FXML
    private void handleButtonVolver(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleButtonReset(ActionEvent event) {
        
      
        textnombre.setText("");
        texttipo.setText("");
        textprecio.setText("");
        listaingredientes.setText("");
        checkalergenos.setSelected(false);
        label.setText("");
    }

    @FXML
    private void handleButtonModificar(ActionEvent event) {
        Plato selectedPlato = tablaPlatos.getSelectionModel().getSelectedItem();

        if (selectedPlato != null) {
            if (validarCampos()) {
                try {
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setTitle("CONFIRMAR CAMBIOS");
                    alerta.setHeaderText("¿Está seguro de que desea modificar?");
                    alerta.setContentText("Los datos serán actualizados.");
                    alerta.initStyle(StageStyle.UTILITY);

                    Optional<ButtonType> resultado = alerta.showAndWait();
                    if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                        String nombre = textnombre.getText();
                        String tipo = texttipo.getText();
                        String[] ingredientesArray = listaingredientes.getText().split(",");
                         List<String> ingredientes = new ArrayList<>();
                            for (String ingrediente : ingredientesArray) {
                                String[] parts = ingrediente.split(":");
                                if (parts.length >= 1) { 
                                    ingredientes.add(parts[0].trim());
                                }
                            }
                        boolean alergenos = checkalergenos.isSelected();
                        double precio = Double.parseDouble(textprecio.getText());

                        selectedPlato.setNombre(nombre);
                        selectedPlato.setTipo(tipo);
                        selectedPlato.setIngredientes(ingredientes);
                        selectedPlato.setAlergenos(alergenos);
                        selectedPlato.setPrecio(precio);

                        if (listaEditar.modificarPlato(selectedPlato)) {
                            label.setTextFill(Color.GREEN);
                            label.setText("Plato modificado correctamente.");
                            textnombre.setText("");
                            texttipo.setText("");
                            textprecio.setText("");
                            listaingredientes.setText("");
                            checkalergenos.setSelected(false);
                            actualizarTabla();
                        } else {
                            label.setTextFill(Color.RED);
                            label.setText("No se pudo modificar el plato");
                        }
                    } else {
                        label.setTextFill(Color.RED);
                        label.setText("Operación cancelada");
                    }
                } catch (NumberFormatException e) {
                    label.setTextFill(Color.RED);
                    label.setText("Error: El precio debe ser un número válido.");
                } catch (IllegalArgumentException e) {
                    label.setTextFill(Color.RED);
                    label.setText("Error: Formato de ingredientes incorrecto.");
                }
            } else {
                label.setTextFill(Color.RED);
                label.setText("Por favor, rellene todos los campos");
            }
        } else {
            label.setTextFill(Color.RED);
            label.setText("Por favor, seleccione un elemento de la lista");
        }
    }

    @FXML
    private void handleButtonBorrar(ActionEvent event) {
        Plato selectedPlato = tablaPlatos.getSelectionModel().getSelectedItem();

        if (selectedPlato != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("CONFIRMAR BORRADO");
            alerta.setHeaderText("¿Está seguro de que desea borrar?");
            alerta.setContentText("Esta operación es irreversible.");
            alerta.initStyle(StageStyle.UTILITY);

            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("CONFIRMACIÓN DE SEGURIDAD");
                dialog.setHeaderText("Nombre del plato a borrar");
                dialog.setContentText("Por favor, introduzca el nombre del plato");

                Optional<String> respuesta = dialog.showAndWait();
                if (respuesta.isPresent() && respuesta.get().equals(selectedPlato.getNombre())) {
                    if (listaEditar.borrarPlato(selectedPlato)) {
                        label.setTextFill(Color.GREEN);
                        label.setText("Plato borrado correctamente.");
                        listaPlatos.remove(selectedPlato);
                        textnombre.setText("");
                        texttipo.setText("");
                        textprecio.setText("");
                        listaingredientes.setText("");
                        checkalergenos.setSelected(false);
                    } else {
                        label.setTextFill(Color.RED);
                        label.setText("No se pudo borrar el plato.");
                    }
                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("ERROR DE CONFIRMACIÓN");
                    error.setHeaderText("Nombre del plato incorrecto");
                    error.setContentText("Los datos no coinciden.");
                    error.showAndWait();
                }
            } else {
                label.setTextFill(Color.RED);
                label.setText("Operación cancelada.");
            }
        } else {
            label.setTextFill(Color.RED);
            label.setText("Por favor, seleccione un elemento de la lista");
        }
    }

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

        listaEditar = PracticaACeniceros.platos;
        listaPlatos = FXCollections.observableArrayList(listaEditar.obtenerPlatos().values());

        tablaPlatos.setItems(listaPlatos);
        tablaPlatos.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> mostrarDetallesPlato(newValue)
        );
    }

    private void mostrarDetallesPlato(Plato plato) {
    
        if (plato != null) {
            textnombre.setText(plato.getNombre());
            texttipo.setText(plato.getTipo());
            textprecio.setText(Double.toString(plato.getPrecio()));
            listaingredientes.setText(plato.getIngredientes().toString());
            checkalergenos.setSelected(plato.getAlergenos());
        } else {
            handleButtonReset(null);
        }
    }

    private boolean validarCampos() {
        return textnombre.getText() != null && !textnombre.getText().isEmpty() &&
               texttipo.getText() != null && !texttipo.getText().isEmpty() &&
               textprecio.getText() != null && !textprecio.getText().isEmpty() &&
               listaingredientes.getText() != null && !listaingredientes.getText().isEmpty();
    }

    private void actualizarTabla() {
        listaPlatos.setAll(listaEditar.obtenerPlatos().values());
    }
}