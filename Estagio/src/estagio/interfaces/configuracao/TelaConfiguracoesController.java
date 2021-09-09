/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.configuracao;

import com.jfoenix.controls.JFXDecorator;
import estagio.TelaPrincipalController;
import estagio.utilidades.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaConfiguracoesController implements Initializable
{

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Label lbTitulo;
    @FXML
    private Pane folderParametrizacao;
    @FXML
    private Pane paneParametrizacao;
    @FXML
    private ImageView imgClientes;
    @FXML
    private Pane folderDesign;
    @FXML
    private Pane paneDesign;
    @FXML
    private ImageView imgClientes1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(lbTitulo);
        Utils.setDesign(1, nodes);
        
        panePrincipal.setPrefWidth(TelaPrincipalController.screenBounds.getMaxX() - 200);
        panePrincipal.setPrefHeight(TelaPrincipalController.screenBounds.getMaxY() - 40);
    }    

    @FXML
    private void clickParametrizacao(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/configuracao/TelaParametrizacao.fxml"));
            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(root);
        }
        catch (IOException ex)
        {
            Logger.getLogger(TelaParametrizacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickDesign(MouseEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/configuracao/TelaDesign.fxml"));     
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            JFXDecorator decorator = new JFXDecorator(stage, root);
            TelaDesignController.bt = paneDesign;

            decorator.setStyle("-fx-decorator-color: #040921;");

            Scene scene = new Scene(decorator);            
            
            stage.setTitle("Tela de Design");
            stage.setScene(scene);
            stage.showAndWait();

        } 
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Design! \nErro: " + er.getMessage(), ButtonType.OK);
            System.out.println(er.getMessage());
            a.showAndWait();
        }
    }

    @FXML
    private void parametrizacaoExit(MouseEvent event)
    {
    }

    @FXML
    private void parametrizacaoEnter(MouseEvent event)
    {
    }

    @FXML
    private void designExit(MouseEvent event)
    {
    }

    @FXML
    private void designEnter(MouseEvent event)
    {
    }
}
