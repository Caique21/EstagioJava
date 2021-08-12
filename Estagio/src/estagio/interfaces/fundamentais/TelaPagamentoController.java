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
public class TelaPagamentoController implements Initializable
{

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbPagamentos;
    @FXML
    private Label lbParcelas;
    @FXML
    private Label lbDespesas;
    @FXML
    private Label lbAte;
    @FXML
    private JFXTextField tfFornecedorDespesa;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private JFXCheckBox cbPagas;
    @FXML
    private JFXRadioButton rbTodos;
    @FXML
    private JFXRadioButton rbFornecedor;
    @FXML
    private JFXRadioButton rbDespesa;
    @FXML
    private JFXRadioButton rbData;
    @FXML
    private JFXRadioButton rbPeriodo;
    
    @FXML
    private TableView<?> tvPagamentos;
    @FXML
    private TableColumn<?, ?> tcNome;
    @FXML
    private TableColumn<?, ?> tcNumero;
    @FXML
    private TableColumn<?, ?> tcValor;
    @FXML
    private TableColumn<?, ?> tcVencimento;
    @FXML
    private TableColumn<?, ?> tcPaga;
    @FXML
    private TableColumn<?, ?> tcFormaPagamento;
    
    @FXML
    private TableView<?> tvParcelas;
    @FXML
    private TableColumn<?, ?> tcNotaFiscal;
    @FXML
    private TableColumn<?, ?> tcNumeroParcela;
    @FXML
    private TableColumn<?, ?> tcValorPagoParcela;
    @FXML
    private TableColumn<?, ?> tcDataCompra;
    
    @FXML
    private TableView<?> tvDespesa;
    @FXML
    private TableColumn<?, ?> tcDespesa;
    @FXML
    private TableColumn<?, ?> tcFixa;
    @FXML
    private TableColumn<?, ?> tcValorPagoDespesa;
    @FXML
    private TableColumn<?, ?> tcObsDespesa;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private JFXButton btEstornar;
    @FXML
    private FontAwesomeIconView faRestore;

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
    private void selecionaPagamento(MouseEvent event)
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
    
}
