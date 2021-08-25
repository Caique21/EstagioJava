/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces;

import com.jfoenix.controls.JFXDecorator;
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
    @FXML
    private Pane folderFabricante;
    @FXML
    private Pane paneFabricante;
    @FXML
    private Label lbTitulo;

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
    private void clickFabricante(MouseEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/basicas/CadastroFabricante.fxml"));     
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            JFXDecorator decorator = new JFXDecorator(stage, root);

            decorator.setStyle("-fx-decorator-color: #040921;");

            Scene scene = new Scene(decorator);
            
            
            stage.setTitle("Cadastro de Veículo");
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.showAndWait();

        } catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Veículos! \nErro: " + er.getMessage(), ButtonType.OK);
            System.out.println(er.getMessage());
            a.showAndWait();
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

    @FXML
    private void fabricanteExit(MouseEvent event)
    {
        folderFabricante.setStyle("-fx-cursor: default; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }

    @FXML
    private void fabricanteEnter(MouseEvent event)
    {
        folderFabricante.setStyle("-fx-cursor: hand; "
                + "-fx-background-color: white;"
                + "-fx-background-radius: 7px;"
                + "-fx-border-color:  D1D1D1;"
                + "-fx-border-radius: 5px;"
                + "-fx-border-width: 2px;");
    }
}
