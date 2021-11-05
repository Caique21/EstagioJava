/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.buscas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrVenda;
import estagio.utilidades.Objeto;
import estagio.utilidades.TooltippedTableCell;
import estagio.utilidades.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class BuscarVendaController implements Initializable
{
    private final ctrVenda ctrVen = ctrVenda.instancia();
    
    private Objeto venda;
    private final ToggleGroup goup = new ToggleGroup();

    @FXML
    private JFXButton btConfirmar;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfNotaFiscal;
    @FXML
    private JFXTextField tfVeiculo;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private JFXRadioButton rbTodos;
    @FXML
    private JFXRadioButton rbNome;
    @FXML
    private JFXRadioButton rbNotaFiscal;
    @FXML
    private JFXRadioButton rbVeiculo;
    @FXML
    private Label lbTabela;
    @FXML
    private TableView<Objeto> tvVenda;
    @FXML
    private TableColumn<Objeto, String> tcNome;
    @FXML
    private TableColumn<Objeto, String> tcNotaFiscal;
    @FXML
    private TableColumn<Objeto, String> tcEmissao;
    @FXML
    private TableColumn<Objeto, String> tcVeiculos;
    @FXML
    private Label lbTitulo;
    @FXML
    private BorderPane panePrincipal;

    /**
     * Initializes the controller class.
     */
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btPesquisar);
        
        nodes.add(tfNome);
        nodes.add(tfNotaFiscal);
        nodes.add(tfVeiculo);
        
        nodes.add(rbNome);
        nodes.add(rbNotaFiscal);
        nodes.add(rbTodos);
        nodes.add(rbVeiculo);
        
        nodes.add(lbTabela);
        nodes.add(lbTitulo);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faSearch);
        
        Utils.setDesign(1, nodes);
        
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tcEmissao.setCellValueFactory(new PropertyValueFactory<>("param12"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcNotaFiscal.setCellValueFactory(new PropertyValueFactory<>("param9"));
        tcVeiculos.setCellValueFactory(new PropertyValueFactory<>("list2ToString"));
        tcNome.setCellFactory(TooltippedTableCell.forTableColumn());
        tcVeiculos.setCellFactory(TooltippedTableCell.forTableColumn());
        
        rbNome.setToggleGroup(goup);
        rbNotaFiscal.setToggleGroup(goup);
        rbTodos.setToggleGroup(goup);
        rbVeiculo.setToggleGroup(goup);
        tfNome.setEditable(false);
        rbTodos.setSelected(true);
        
        inicializaDesign();
        clickPesquisar(new ActionEvent());
    }    

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(!tvVenda.getItems().isEmpty() && tvVenda.getSelectionModel().getSelectedIndex() >= 0)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Confirmar seleção da compra", ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            
            if(a.getResult() == ButtonType.YES)
            {
                venda = tvVenda.getSelectionModel().getSelectedItem();
                Stage stage = (Stage) btConfirmar.getScene().getWindow();
                stage.close();
            }
        }
        else if(tvVenda.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Nenhuma venda cadastrada", ButtonType.OK).showAndWait();
        else
            new Alert(Alert.AlertType.ERROR, "Nenhuma venda selecionada", ButtonType.OK).showAndWait();
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        venda = null;
        Stage stage = (Stage) btConfirmar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        tvVenda.getItems().clear();
        if(rbNome.isSelected())
            tvVenda.setItems(FXCollections.observableArrayList(ctrVen.getByNome(tfNome.getText())));
        else if(rbNotaFiscal.isSelected())
            tvVenda.setItems(FXCollections.observableArrayList(ctrVen.getByNotaFiscal(tfNotaFiscal.getText())));
        else if(rbVeiculo.isSelected())
            tvVenda.setItems(FXCollections.observableArrayList(ctrVen.getByVeiculo(tfVeiculo.getText())));
        else if(rbTodos.isSelected())
            tvVenda.setItems(FXCollections.observableArrayList(ctrVen.getAll()));
    }

    @FXML
    private void clickTodos(ActionEvent event)
    {
        tfNome.setEditable(false);
        tfNotaFiscal.setEditable(false);
        tfVeiculo.setEditable(false);
        clickPesquisar(new ActionEvent());
    }

    @FXML
    private void clickNome(ActionEvent event)
    {
        tfNome.setEditable(true);
        tfNome.clear();
        tfNome.setVisible(true);
        tfNotaFiscal.setVisible(false);
        tfVeiculo.setVisible(false);
    }

    @FXML
    private void clickNotaFiscal(ActionEvent event)
    {
        tfNome.setVisible(false);
        tfNotaFiscal.setVisible(true);
        tfVeiculo.setVisible(false);
        
        tfNotaFiscal.setEditable(true);
        tfNotaFiscal.clear();
    }

    @FXML
    private void clickVeiculo(ActionEvent event)
    {
        tfNome.setVisible(false);
        tfNotaFiscal.setVisible(false);
        tfVeiculo.setVisible(true);
        
        tfVeiculo.setEditable(true);
        tfVeiculo.clear();
    }
    
    public Objeto getResposta()
    {
        return venda;
    }
}
