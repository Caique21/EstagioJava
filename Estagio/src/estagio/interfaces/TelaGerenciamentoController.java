/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class TelaGerenciamentoController implements Initializable
{

    @FXML
    private Pane folderClientes;
    @FXML
    private Pane paneClientes;
    @FXML
    private ImageView imgClientes;
    @FXML
    private Pane folderUsuarios;
    @FXML
    private Pane paneUsuarios;
    @FXML
    private ImageView imgClientes1;
    @FXML
    private Pane folderFornecedores;
    @FXML
    private Pane paneFornecedores;
    @FXML
    private ImageView imgFornecedores;
    @FXML
    private ImageView imgUsuarios;
    @FXML
    private Pane folderDespesas;
    @FXML
    private Pane paneDespesas;
    @FXML
    private ImageView imgDespesas;
    @FXML
    private Pane folderDados;
    @FXML
    private Pane paneDados;
    @FXML
    private Pane folderFuncionarios;
    @FXML
    private Pane paneFuncionarios;
    @FXML
    private Pane folderVeiculos;
    @FXML
    private Pane paneVeiculos;
    @FXML
    private BorderPane panePrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickCliente(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/basicas/CadastroCliente.fxml"));
            root.getStylesheets().add(("/oficina/utilidades/CadastroCliente.css"));
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
    private void clickFuncionarios(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/basicas/CadastroFuncionario.fxml"));
            root.getStylesheets().add(("/oficina/utilidades/CadastroCliente.css"));
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
    private void clickUsuario(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/basicas/CadastroUsuario.fxml"));
            root.getStylesheets().add(("/oficina/utilidades/CadastroCliente.css"));
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
    private void clickFornecedor(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/basicas/CadastroFornecedor.fxml"));
            root.getStylesheets().add(("/oficina/utilidades/CadastroCliente.css"));
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
    private void clickVeiculos(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/basicas/CadastroVeiculo.fxml"));
            root.getStylesheets().add(("/oficina/utilidades/CadastroCliente.css"));
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
            root.getStylesheets().add(("/oficina/utilidades/CadastroCliente.css"));
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
    private void clickDados(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) panePrincipal.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/basicas/CadastrosInativos.fxml"));
            root.getStylesheets().add(("/oficina/utilidades/CadastroCliente.css"));
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
    private void clientesExit(MouseEvent event)
    {
        folderClientes.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void clientesEnter(MouseEvent event)
    {
        folderClientes.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void usuariosExit(MouseEvent event)
    {
        folderUsuarios.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void usuariosEnter(MouseEvent event)
    {
        folderUsuarios.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void fornecedoresExit(MouseEvent event)
    {
        folderFornecedores.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void fornecedoresEnter(MouseEvent event)
    {
        folderFornecedores.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
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

    @FXML
    private void dadosExit(MouseEvent event)
    {
        folderDados.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void dadosEnter(MouseEvent event)
    {
        folderDados.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void funcionariosExit(MouseEvent event)
    {
        folderFuncionarios.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void funcionariosEnter(MouseEvent event)
    {
        folderFuncionarios.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void veiculosExit(MouseEvent event)
    {
        folderVeiculos.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void veiculosEnter(MouseEvent event)
    {
        folderVeiculos.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }
}