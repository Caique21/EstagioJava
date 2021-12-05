/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaRelatoriosController implements Initializable
{

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Label lbTitulo;
    @FXML
    private GridPane gridPane;
    @FXML
    private Pane folderContasPagar;
    @FXML
    private Pane paneContasPagar;
    @FXML
    private Pane folderInadimplentes;
    @FXML
    private Pane paneInadimplementes;
    @FXML
    private Pane folderBalanco;
    @FXML
    private Pane paneBalanco;
    @FXML
    private Pane folderContasReceber;
    @FXML
    private Pane paneContasReceber;
    @FXML
    private Pane folderMovimentacao;
    @FXML
    private Pane paneMovimentacao;
    @FXML
    private ImageView imgDespesas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickApagar(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/relatorios/ContasPagar.fxml"));
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
    private void clickIndadimplentes(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/relatorios/Inadimplentes.fxml"));
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
    private void clickContasReceber(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/relatorios/ContasReceber.fxml"));
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
    private void clickTransporte(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/relatorios/Transporte.fxml"));
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
    private void clickMovimentacao(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/relatorios/Movimentacao.fxml"));
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
    private void aPagarExit(MouseEvent event)
    {
        folderContasPagar.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void aPagarEnter(MouseEvent event)
    {
        folderContasPagar.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void inadimpleteExit(MouseEvent event)
    {
        folderInadimplentes.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void inadimpleteEnter(MouseEvent event)
    {
        folderInadimplentes.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void contasReceberExit(MouseEvent event)
    {
        folderContasReceber.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void contasReceberEnter(MouseEvent event)
    {
        folderContasReceber.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void movimentacaoExit(MouseEvent event)
    {
        folderMovimentacao.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void movimentacaoEnter(MouseEvent event)
    {
        folderMovimentacao.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void transporteExit(MouseEvent event)
    {
        folderBalanco.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void transporteEnter(MouseEvent event)
    {
        folderBalanco.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }
    
}
