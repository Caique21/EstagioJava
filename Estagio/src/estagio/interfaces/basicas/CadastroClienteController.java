/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
public class CadastroClienteController implements Initializable
{

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneInfoBasica;
    @FXML
    private Pane paneEndereco;
    @FXML
    private Pane paneContato;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbInfoBasica;
    @FXML
    private Label lbNascimento;
    @FXML
    private Label lbEndereco;
    @FXML
    private Label lbContato;
    @FXML
    private Label lbTelefones;
    @FXML
    private Label lbPesquisa;
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
    private FontAwesomeIconView faSearchCEP;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfCPF;
    @FXML
    private JFXTextField tfRG;
    @FXML
    private JFXDatePicker dpData;
    @FXML
    private JFXTextField tfCEP;
    @FXML
    private JFXTextField tfRua;
    @FXML
    private JFXTextField tfNumero;
    @FXML
    private JFXTextField tfBairro;
    @FXML
    private JFXTextField tfComplemento;
    @FXML
    private JFXTextField tfCidade;
    @FXML
    private JFXComboBox<String> cbEstado;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfTelefone;
    @FXML
    private JFXTextField tfNomePesquisa;
    @FXML
    private JFXTextField tfCpfPesquisa;
    @FXML
    private JFXRadioButton rbNome;
    @FXML
    private JFXRadioButton rbCPF;
    @FXML
    private TableView<?> tvClientes;
    @FXML
    private TableColumn<?, ?> tcNome;
    @FXML
    private TableColumn<?, ?> tcCPF;
    @FXML
    private TableColumn<?, ?> tcRG;
    @FXML
    private TableColumn<?, ?> tcData;
    @FXML
    private TableColumn<?, ?> tcEndereco;
    @FXML
    private TableColumn<?, ?> tcEmail;
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
    private JFXButton btAddTelefone;
    @FXML
    private JFXButton btDelTelefone;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private Label lbErroNome;
    @FXML
    private Label lbErroCPF;
    @FXML
    private Label lbErroRG;
    @FXML
    private Label lbErroData;
    @FXML
    private Label lbErroRua;
    @FXML
    private Label lbErroCEP;
    @FXML
    private Label lbErroNumero;
    @FXML
    private Label lbErroBairro;
    @FXML
    private Label bErroCidade;
    @FXML
    private Label lbErroEstado;
    @FXML
    private Label lbErroEmail;
    @FXML
    private Label lbErroTelefone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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
    private void clickPesquisarCEP(MouseEvent event)
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
    private void cepExit(MouseEvent event)
    {
    }

    @FXML
    private void cepEnter(MouseEvent event)
    {
    }

    @FXML
    private void addTelExit(MouseEvent event)
    {
    }

    @FXML
    private void addTelEnter(MouseEvent event)
    {
    }

    @FXML
    private void delTelExit(MouseEvent event)
    {
    }

    @FXML
    private void delTelEnter(MouseEvent event)
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
    
}
