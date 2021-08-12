/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

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
public class TelaCompraController implements Initializable
{

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneVeiculo;
    @FXML
    private Pane paneDadosCompra;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbDadosVeiculos;
    @FXML
    private Label lbVeiculos;
    @FXML
    private Label lbVencimento;
    @FXML
    private Label lbEmissao;
    @FXML
    private Label lbSubTotal;
    @FXML
    private Label lbErroPlaca;
    @FXML
    private Label lbErroMarca;
    @FXML
    private Label lbErroModelo;
    @FXML
    private Label lbErroChassi;
    @FXML
    private Label lbErroAno;
    @FXML
    private Label lbErroCor;
    @FXML
    private Label lbErroValorVeiculo;
    @FXML
    private Label lbErroTipoPagamento;
    @FXML
    private Label lbErroNumeroParcelas;
    @FXML
    private Label lbErroValorReajuste;
    @FXML
    private Label lbErroFornecedor;
    @FXML
    private Label lbErroNotaFiscal;
    @FXML
    private Label lbErroVendedor;
    @FXML
    private Label lbErroFormaPagamento;
    @FXML
    private Label lbErroNumeroCheque;
    @FXML
    private JFXTextField tfPlaca;
    @FXML
    private JFXRadioButton rbMercosul;
    @FXML
    private JFXTextField tfMarca;
    @FXML
    private JFXTextField tfModelo;
    @FXML
    private JFXTextField tfChassi;
    @FXML
    private JFXTextField tfAno;
    @FXML
    private JFXTextField tfCor;
    @FXML
    private JFXTextField tfValorVeiculo;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private JFXButton btRemover;
    @FXML
    private FontAwesomeIconView faMinus;
    @FXML
    private TableView<?> tvVeiculos;
    @FXML
    private TableColumn<?, ?> tcMarca;
    @FXML
    private TableColumn<?, ?> tcModelo;
    @FXML
    private TableColumn<?, ?> tcPlaca;
    @FXML
    private TableColumn<?, ?> tcValor;
    @FXML
    private TableColumn<?, ?> tcChassi;
    @FXML
    private TableColumn<?, ?> tcAno;
    @FXML
    private TableColumn<?, ?> tcCor;
    @FXML
    private JFXRadioButton rbAvista;
    @FXML
    private JFXRadioButton rbParcelado;
    @FXML
    private JFXTextField tfNumeroParcelas;
    @FXML
    private JFXRadioButton rbJuros;
    @FXML
    private JFXRadioButton rbDesconto;
    @FXML
    private JFXRadioButton rbValor;
    @FXML
    private JFXRadioButton rbPorcentagem;
    @FXML
    private JFXTextField tfValorReajuste;
    @FXML
    private JFXDatePicker dpVencimento;
    @FXML
    private JFXButton btParcelasManuais;
    @FXML
    private JFXDatePicker dpEmissao;
    @FXML
    private JFXTextField tfFornecedor;
    @FXML
    private JFXTextField tfNotaFiscal;
    @FXML
    private JFXTextField tfVendedor;
    @FXML
    private JFXComboBox<?> cbFormaPagamento;
    @FXML
    private JFXTextField tfNumeroCheque;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private JFXButton btAlterar;
    @FXML
    private FontAwesomeIconView faEdit;
    @FXML
    private FontAwesomeIconView faTrash;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private JFXButton btAdicionarVeiculo;
    @FXML
    private JFXButton btRemoverVeiculo;
    @FXML
    private FontAwesomeIconView faView;

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
    private void clickPesquisar(ActionEvent event)
    {
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
    }

    @FXML
    private void clickAdicionarVeiculo(ActionEvent event)
    {
    }

    @FXML
    private void clickRemoverVeiculo(ActionEvent event)
    {
    }

    @FXML
    private void selecionaVeiculo(MouseEvent event)
    {
    }

    @FXML
    private void addVeiculoExit(MouseEvent event)
    {
    }

    @FXML
    private void addVeiculoEnter(MouseEvent event)
    {
    }

    @FXML
    private void delVeiculoExit(MouseEvent event)
    {
    }

    @FXML
    private void delVeiculoEnter(MouseEvent event)
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
    private void pesquisarExit(MouseEvent event)
    {
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
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
    private void fornecedorExit(MouseEvent event)
    {
    }

    @FXML
    private void fornecedorEnter(MouseEvent event)
    {
    }

    @FXML
    private void gerarParcelasExit(MouseEvent event)
    {
    }

    @FXML
    private void gerarParcelasEnter(MouseEvent event)
    {
    }

    @FXML
    private void clickGerarParcelas(ActionEvent event)
    {
    }
    
}
