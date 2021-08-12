/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
public class TelaRecebimentoController implements Initializable
{

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Pane paneDados;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbRecebimentos;
    @FXML
    private Label lbParcelas;
    @FXML
    private Label lbAte;
    @FXML
    private JFXTextField tfPesquisa;
    @FXML
    private JFXTextField tfCliente;
    @FXML
    private JFXTextField tfCPF;
    @FXML
    private JFXTextField tfPlaca;
    @FXML
    private JFXTextField tfMarca;
    @FXML
    private JFXTextField tfModelo;
    @FXML
    private JFXTextField tfNumeroCheque;
    @FXML
    private JFXRadioButton rbTodos;
    @FXML
    private JFXRadioButton rbCliente;
    @FXML
    private JFXRadioButton rbData;
    @FXML
    private JFXRadioButton rbPeriodo;
    @FXML
    private JFXCheckBox cbConcluidos;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btEstornar;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faRestore;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private FontAwesomeIconView faViewCliente;
    @FXML
    private FontAwesomeIconView faViewVeiculo;
    @FXML
    private TableView<?> tvRecebimentos;
    @FXML
    private TableColumn<?, ?> tcCliente;
    @FXML
    private TableColumn<?, ?> tcPlaca;
    @FXML
    private TableColumn<?, ?> tcValor;
    @FXML
    private TableColumn<?, ?> tcVenvimento;
    @FXML
    private TableColumn<?, ?> tcPaga;
    @FXML
    private TableColumn<?, ?> tcFormaPagamento;
    
    @FXML
    private TableView<?> tvParcelas;
    @FXML
    private TableColumn<?, ?> tcNumero;
    @FXML
    private TableColumn<?, ?> tcValorPago;
    @FXML
    private TableColumn<?, ?> tcNotaFiscal;
    @FXML
    private TableColumn<?, ?> tcDataPagamento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
    }

    @FXML
    private void clickEstornar(ActionEvent event)
    {
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
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
    private void estornarExit(MouseEvent event)
    {
    }

    @FXML
    private void estornarEnter(MouseEvent event)
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

    @FXML
    private void clienteExit(MouseEvent event)
    {
    }

    @FXML
    private void clienteEnter(MouseEvent event)
    {
    }

    @FXML
    private void veiculoExit(MouseEvent event)
    {
    }

    @FXML
    private void veiculoEnter(MouseEvent event)
    {
    }
    
}
