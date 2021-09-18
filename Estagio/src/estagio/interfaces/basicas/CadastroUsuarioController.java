/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastroUsuarioController implements Initializable
{
    private int acao;

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneInfo;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbInfo;
    @FXML
    private Label lbPesquisa;
    @FXML
    private Label lbErroFuncionario;
    @FXML
    private Label lbErroNivel;
    @FXML
    private Label lbErroSenha;
    @FXML
    private Label lbErroConfirmarSenha;
    @FXML
    private JFXTextField tfUsuario;
    @FXML
    private JFXComboBox<String> cbNivel;
    @FXML
    private JFXTextField tfFuncionario;
    @FXML
    private JFXPasswordField tfSenha;
    @FXML
    private JFXPasswordField tfConfirmarSenha;
    @FXML
    private JFXTextField tfFuncionarioPesquisa;
    @FXML
    private JFXTextField tfUsuarioPesquisa;
    @FXML
    private JFXComboBox<?> cbNivelPesquisa;
    @FXML
    private JFXRadioButton rbFuncionario;
    @FXML
    private JFXRadioButton rbUsuario;
    @FXML
    private JFXRadioButton rbNivel;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btAlterar;
    @FXML
    private JFXButton btRemover;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private TableView<?> tvUsuarios;
    @FXML
    private TableColumn<?, ?> tcFuncionario;
    @FXML
    private TableColumn<?, ?> tcUsuario;
    @FXML
    private TableColumn<?, ?> tcNivel;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faEdit;
    @FXML
    private FontAwesomeIconView faTrash;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private FontAwesomeIconView faSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbNivel.setDisable(false);
        cbNivel.getItems().addAll("alto","medio","baixo");
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
    }

    @FXML
    private void novoExit(MouseEvent event)
    {
    }

    @FXML
    private void novoEnter(MouseEvent event)
    {
    }

    @FXML
    private void confirmarExit(MouseEvent event)
    {
    }

    @FXML
    private void confirmarEnter(MouseEvent event)
    {
    }

    @FXML
    private void alterarExit(MouseEvent event)
    {
    }

    @FXML
    private void alterarEnter(MouseEvent event)
    {
    }

    @FXML
    private void removerExit(MouseEvent event)
    {
    }

    @FXML
    private void removerEnter(MouseEvent event)
    {
    }

    @FXML
    private void cancelarExit(MouseEvent event)
    {
    }

    @FXML
    private void cancelarEnter(MouseEvent event)
    {
    }

    @FXML
    private void pesquisarExit(MouseEvent event)
    {
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
    {
    }

    void setFuncionario(String nome,String nivel)
    {
        tfFuncionario.setText(nome);
        tfFuncionario.setDisable(true);
        cbNivel.getSelectionModel().select(nivel);
        cbNivel.setDisable(true);
        acao = 2;
    }
    
}
