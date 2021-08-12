/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaTransporteController implements Initializable
{

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private VBox paneLateral;
    @FXML
    private Pane paneDados;
    @FXML
    private Pane paneVeiculos;
    @FXML
    private ListView<?> lvTransportes;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbTransporte;
    @FXML
    private Label lbDados;
    @FXML
    private Label lbSaida;
    @FXML
    private Label lbChegada;
    @FXML
    private Label lbVeiculos;
    @FXML
    private Label lbUltimaAlteracao;
    @FXML
    private Label lbErroMotorista;
    @FXML
    private Label lbErroPlacaCegonha;
    @FXML
    private Label lbErroSaida;
    @FXML
    private Label lbErroChegada;
    @FXML
    private Label lbErroStatus;
    @FXML
    private Label lbErroTipo;
    @FXML
    private Label lbErroMarca;
    @FXML
    private Label lbErroModelo;
    @FXML
    private Label lbErroPlaca;
    @FXML
    private Label lbErroChassi;
    @FXML
    private Label lbErroAno;
    @FXML
    private Label lbErroCor;
    @FXML
    private JFXTextField tfMotorista;
    @FXML
    private JFXTextField tfPlacaCegonha;
    @FXML
    private JFXDatePicker dpSaida;
    @FXML
    private JFXDatePicker dpChegada;
    @FXML
    private JFXTextField tfMarca;
    @FXML
    private JFXTextField tfModelo;
    @FXML
    private JFXTextField tfPlaca;
    @FXML
    private JFXTextField tfChassi;
    @FXML
    private JFXTextField tfAno;
    @FXML
    private JFXTextField tfCor;
    @FXML
    private JFXComboBox<?> cbStatus;
    @FXML
    private JFXComboBox<?> cbTipo;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private JFXButton btAdicionarVeiculo;
    @FXML
    private JFXButton btRemoverVeiculo;
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
    private FontAwesomeIconView faSearch;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private FontAwesomeIconView faMinus;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faEdit;
    @FXML
    private FontAwesomeIconView faTrash;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private TableView<?> tvVeiculos;
    @FXML
    private TableColumn<?, ?> tcMarca;
    @FXML
    private TableColumn<?, ?> tcModelo;
    @FXML
    private TableColumn<?, ?> tcPlaca;
    @FXML
    private TableColumn<?, ?> tcChassi;
    @FXML
    private TableColumn<?, ?> tcAno;
    @FXML
    private TableColumn<?, ?> tcCor;

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
    private void clickPesquisar(ActionEvent event)
    {
    }

    @FXML
    private void clickAdd(ActionEvent event)
    {
    }

    @FXML
    private void clickDel(ActionEvent event)
    {
    }

    @FXML
    private void selecionaTransporte(MouseEvent event)
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
    private void transporteExit(MouseEvent event)
    {
    }

    @FXML
    private void transporteEnter(MouseEvent event)
    {
    }
    
}
