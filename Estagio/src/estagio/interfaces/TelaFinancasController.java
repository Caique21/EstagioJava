/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces;

import estagio.TelaPrincipalController;
import estagio.utilidades.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaFinancasController implements Initializable
{

    @FXML
    private Pane folderCompra;
    @FXML
    private Pane paneCompra;
    @FXML
    private Pane folderVenda;
    @FXML
    private Pane paneVenda;
    @FXML
    private ImageView imgClientes1;
    @FXML
    private Pane folderPagamento;
    @FXML
    private Pane panePagamento;
    @FXML
    private Pane folderRecebimento;
    @FXML
    private Pane paneRecebimento;
    @FXML
    private Pane folderBalanco;
    @FXML
    private Pane paneBalanco;
    @FXML
    private Pane panePrincipal;
    @FXML
    private Label lbTitulo;
    @FXML
    private GridPane gridPane;
    @FXML
    private Pane folderDespesas;
    @FXML
    private Pane paneDespesas;
    @FXML
    private ImageView imgDespesas;

    /**
     * Initializes the controller class.
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
        gridPane.setPrefWidth(TelaPrincipalController.screenBounds.getMaxX() - 200);
    }    

    @FXML
    private void clickCompra(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/fundamentais/TelaCompra.fxml"));
            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }

    @FXML
    private void clickVenda(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/fundamentais/TelaVenda.fxml"));
            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }

    @FXML
    private void clickPagamento(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/fundamentais/TelaPagamento.fxml"));
            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }

    @FXML
    private void clickRecebimento(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/fundamentais/TelaRecebimento.fxml"));
            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }
    
    @FXML
    private void clickDespesas(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/basicas/CadastroDespesa.fxml"));
            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }

    @FXML
    private void clickBalanco(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/fundamentais/TelaBalanco.fxml"));
            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }

    @FXML
    private void compraExit(MouseEvent event)
    {
        folderCompra.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void compraEnter(MouseEvent event)
    {
        folderCompra.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void vendaExit(MouseEvent event)
    {
        folderVenda.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void vendaEnter(MouseEvent event)
    {
        folderVenda.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void pagamentoExit(MouseEvent event)
    {
        folderPagamento.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void pagamentoEnter(MouseEvent event)
    {
        folderPagamento.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void recebimenoExit(MouseEvent event)
    {
        folderRecebimento.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void recebimentoEnter(MouseEvent event)
    {
        folderRecebimento.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void balanceExit(MouseEvent event)
    {
    }

    @FXML
    private void balancoEnter(MouseEvent event)
    {
    }

    @FXML
    private void despesasExit(MouseEvent event)
    {
        folderDespesas.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void despesasEnter(MouseEvent event)
    {
        folderDespesas.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    
    
}
