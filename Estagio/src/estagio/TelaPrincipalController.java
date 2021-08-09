package estagio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXDecorator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class TelaPrincipalController implements Initializable
{
    
    @FXML
    private BorderPane painelPrincipal;
    @FXML
    private VBox painelLateral;
    @FXML
    private VBox painelCentral;
    @FXML
    private Pane btHome;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    

    @FXML
    private void clickHome(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) painelCentral.getScene().getWindow();
            //painel_central.getScene().getStylesheets().add(("@../oficina/utilidades/CadastroCliente.css"));
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/basicas/CadastroDespesa.fxml"));
            root.getStylesheets().add(("/oficina/utilidades/CadastroCliente.css"));
            painelCentral.getChildren().clear();
            painelCentral.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }

    @FXML
    private void exitHome(MouseEvent event)
    {
        btHome.setStyle(btHome.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void enterHome(MouseEvent event)
    {
        btHome.setStyle(btHome.getStyle() + ";-fx-cursor: hand;");
    }
    
}
