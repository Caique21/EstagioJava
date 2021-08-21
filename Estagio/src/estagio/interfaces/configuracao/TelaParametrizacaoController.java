/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.configuracao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaParametrizacaoController implements Initializable
{

    @FXML
    private Pane painel_central;
    @FXML
    private ImageView imgGrande;
    @FXML
    private JFXButton btCarregarGrande;
    @FXML
    private ImageView imgPequeno;
    @FXML
    private JFXButton btCarregarPequeno;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private Label lbErroNome;
    @FXML
    private JFXTextField tfFantasia;
    @FXML
    private Label lbErroFantasia;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private Label lbErroEmail;
    @FXML
    private JFXTextField tfRazao;
    @FXML
    private Label lbErroRazao;
    @FXML
    private Pane paneEndereco;
    @FXML
    private JFXTextField tfCEP;
    @FXML
    private JFXButton btCEP;
    @FXML
    private Label lbErroCep;
    @FXML
    private JFXTextField tfRua;
    @FXML
    private Label lbErroRua;
    @FXML
    private JFXTextField tfNumero;
    @FXML
    private Label lbErroNumero;
    @FXML
    private JFXTextField tfBairro;
    @FXML
    private Label lbErroBairro;
    @FXML
    private JFXTextField tfCidade;
    @FXML
    private Label lbErroCidade;
    @FXML
    private JFXComboBox<?> cbEstado;
    @FXML
    private ListView<?> lvEnderecos;
    @FXML
    private JFXButton btAddEndereco;
    @FXML
    private JFXButton btAlterarEndereco;
    @FXML
    private JFXButton btDelEndereco;
    @FXML
    private Pane paneTelefones;
    @FXML
    private ListView<?> lvTelefones;
    @FXML
    private JFXTextField tfTelefone;
    @FXML
    private JFXButton btAddTelefone;
    @FXML
    private JFXButton btDelTelefone;
    @FXML
    private JFXButton btSalvar;
    @FXML
    private JFXButton btCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickCarregarGrande(ActionEvent event)
    {
    }

    @FXML
    private void clickCarregarPequeno(ActionEvent event)
    {
    }

    @FXML
    private void keyPressedMain(KeyEvent event)
    {
    }

    @FXML
    private void clickPesquisarCep(ActionEvent event)
    {
    }

    @FXML
    private void keyPressedAddress(KeyEvent event)
    {
    }

    @FXML
    private void clickAddEndereco(ActionEvent event)
    {
    }

    @FXML
    private void clickAlterarEndereco(ActionEvent event)
    {
    }

    @FXML
    private void clickRemoverEndereco(ActionEvent event)
    {
    }

    @FXML
    private void keyPressedFone(KeyEvent event)
    {
    }

    @FXML
    private void clickAddTelefone(ActionEvent event)
    {
    }

    @FXML
    private void clickRemoverTelefone(ActionEvent event)
    {
    }

    @FXML
    private void salvarExit(MouseEvent event)
    {
    }

    @FXML
    private void salvarEnter(MouseEvent event)
    {
    }

    @FXML
    private void clickSalvar(ActionEvent event)
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
    private void clickCancelar(ActionEvent event)
    {
    }
    
}
