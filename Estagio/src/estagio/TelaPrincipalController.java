package estagio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.utilidades.Objeto;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
    public static Objeto usuario_logado;
    public static Timestamp data_login;
    public static boolean alteracoes = false;
    
    @FXML
    private BorderPane painelPrincipal;
    @FXML
    private VBox painelLateral;
    @FXML
    private VBox painelCentral;
    @FXML
    private JFXButton btConfig;
    @FXML
    private Pane btHome;
    @FXML
    private Pane btGerenciamento;
    @FXML
    private Pane btTransporte;
    @FXML
    private Pane btFinancas;
    @FXML
    private Pane btRelatorios;
    @FXML
    private Pane btLogout;
    @FXML
    private Label lbFantasia;
    @FXML
    private Label lbHome;
    @FXML
    private Label lbGerencimento;
    @FXML
    private Label lbTransporte;
    @FXML
    private Label lbFinancas;
    @FXML
    private Label lbRelatorios;
    @FXML
    private Label lbLogout;
    @FXML
    private FontAwesomeIconView faHome;
    @FXML
    private FontAwesomeIconView faCogs;
    @FXML
    private FontAwesomeIconView faTruck;
    @FXML
    private FontAwesomeIconView faMoney;
    @FXML
    private FontAwesomeIconView faReport;
    @FXML
    private FontAwesomeIconView faLogout;
    @FXML
    private FontAwesomeIconView faCog;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    

    @FXML
    private void clickConfig(ActionEvent event)
    {
        try
        {
            Stage stage = (Stage) painelCentral.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/configuracao/TelaConfiguracoes.fxml"));
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
    private void clickHome(MouseEvent event)
    {
        
    }

    @FXML
    private void clickGerenciamento(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) painelCentral.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/TelaGerenciamento.fxml"));
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
    private void clickTransporte(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) painelCentral.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/fundamentais/TelaTransporte.fxml"));
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
    private void clickFinancas(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) painelCentral.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/TelaFinancas.fxml"));
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
    private void clickRelatorios(MouseEvent event)
    {
    }

    @FXML
    private void clickLogout(MouseEvent event)
    {
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

    @FXML
    private void gerenciamentoExit(MouseEvent event)
    {
        btGerenciamento.setStyle(btHome.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void gerenciamentoEnter(MouseEvent event)
    {
        btGerenciamento.setStyle(btHome.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void transporteExit(MouseEvent event)
    {
        btGerenciamento.setStyle(btHome.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void transporteEnter(MouseEvent event)
    {
        btTransporte.setStyle(btHome.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void financasExit(MouseEvent event)
    {
        btTransporte.setStyle(btHome.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void financasEnter(MouseEvent event)
    {
        btFinancas.setStyle(btHome.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void relatoriosExit(MouseEvent event)
    {
        btFinancas.setStyle(btHome.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void relatoriosEnter(MouseEvent event)
    {
        btRelatorios.setStyle(btHome.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void logoutExit(MouseEvent event)
    {
        btLogout.setStyle(btHome.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void logoutEnter(MouseEvent event)
    {
        btLogout.setStyle(btHome.getStyle() + ";-fx-cursor: hand;");
    }
    
}
