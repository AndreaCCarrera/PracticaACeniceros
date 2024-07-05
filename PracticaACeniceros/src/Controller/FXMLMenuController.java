package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLMenuController implements Initializable {


    @FXML
    private void handleButtonAnadir(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FXMLAnadir.fxml"));
        Parent root = loader.load();
        FXMLAnadirController controller = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    @FXML
    private void handleButtonBuscar(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FXMLEditar.fxml"));
        Parent root = loader.load();
        FXMLEditarController controller = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    @FXML
    private void handleButtonMostrar(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FXMLMostrar.fxml"));
        Parent root = loader.load();
        FXMLMostrarController controller = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    @FXML
    private void handleButtonSalir(ActionEvent event) {
        
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("CONFIRMAR SALIR");
        alerta.setHeaderText("¿Está seguro de que desea salir");
        alerta.setContentText("Su sesión será cerrada.");
        alerta.initStyle(StageStyle.UTILITY);
        Optional<ButtonType> resultado = alerta.showAndWait();
        if(resultado.get()==ButtonType.OK){
          System.exit(0);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
