/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.relatorios;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrTransporte;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TransporteController implements Initializable
{
    private final ctrTransporte ctr = ctrTransporte.instancia();
    
    private final Tooltip tooltip = new Tooltip();
    private final ToggleGroup goup = new ToggleGroup();

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane panePesquisa;
    @FXML
    private JFXTextField tfFuncionarioVeiculo;
    @FXML
    private JFXDatePicker dpInicio;
    @FXML
    private JFXDatePicker dpFim;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private JFXRadioButton rbFuncionario;
    @FXML
    private JFXRadioButton rbVeiculo;
    @FXML
    private JFXRadioButton rbPeriodo;
    @FXML
    private Label lbAte;
    @FXML
    private JFXCheckBox cbFinalizada;
    @FXML
    private Label lbTitulo;
    @FXML
    private TableView<Objeto> tvTransportes;
    @FXML
    private TableColumn<Objeto, String> tcMotorista;
    @FXML
    private TableColumn<Objeto, String> tcPlacaTransporte;
    @FXML
    private TableColumn<Objeto, String> tcSaida;
    @FXML
    private TableColumn<Objeto, String> tcChegada;
    @FXML
    private TableColumn<Objeto, String> tcStatus;
    @FXML
    private TableColumn<Objeto, String> tcTipo;
    
    @FXML
    private TableView<Objeto> tvVeiculos;
    @FXML
    private TableColumn<Objeto, String> tcPlaca;
    @FXML
    private TableColumn<Objeto, String> tcMarca;
    @FXML
    private TableColumn<Objeto, String> tcModelo;
    @FXML
    private TableColumn<Objeto, String> tcChassi;
    @FXML
    private TableColumn<Objeto, String> tcAno;
    @FXML
    private TableColumn<Objeto, String> tcCor;
    @FXML
    private Label lbVeiculos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(panePesquisa);
        
        nodes.add(btPesquisar);
        
        nodes.add(tfFuncionarioVeiculo);
        
        nodes.add(lbAte);
        nodes.add(lbTitulo);
        nodes.add(lbVeiculos);
        
        nodes.add(rbPeriodo);
        nodes.add(rbFuncionario);
        nodes.add(rbVeiculo);
        
        nodes.add(faSearch);
        
        nodes.add(cbFinalizada);
        
        Utils.setDesign(1, nodes);
        
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
        
        rbFuncionario.setToggleGroup(goup);
        rbVeiculo.setToggleGroup(goup);
        rbPeriodo.setToggleGroup(goup);
        rbFuncionario.setSelected(true);
        cbFinalizada.setVisible(false);
        
        rbFuncionario.setOnAction((event) ->
        {
            tfFuncionarioVeiculo.setPromptText("Funcionário");
            tfFuncionarioVeiculo.setVisible(true);
            tfFuncionarioVeiculo.clear();
            
            dpFim.setVisible(false);
            dpInicio.setVisible(false);
            lbAte.setVisible(false);
            cbFinalizada.setVisible(false);
        });
        
        rbVeiculo.setOnAction((event) ->
        {
            tfFuncionarioVeiculo.setPromptText("Marca, Modelo e/ou Placa");
            tfFuncionarioVeiculo.setVisible(true);
            tfFuncionarioVeiculo.clear();
            
            dpFim.setVisible(false);
            dpInicio.setVisible(false);
            lbAte.setVisible(false);
            cbFinalizada.setVisible(false);
        });
        
        rbPeriodo.setOnAction((event) ->
        {
            dpFim.setVisible(true);
            dpInicio.setVisible(true);
            lbAte.setVisible(true);
            cbFinalizada.setVisible(true);
            
            tfFuncionarioVeiculo.setVisible(false);
        });
        
        cbFinalizada.setOnAction((event) ->
        {
            pesquisar();
        });
        
        btPesquisar.setOnAction((event) ->
        {
            pesquisar();
        });
        
        tvTransportes.setOnMouseClicked((event) ->
        {
            tvVeiculos.getItems().clear();
            if(!tvTransportes.getItems().isEmpty() && tvTransportes.getSelectionModel().getSelectedIndex() >= 0)
                tvVeiculos.setItems(FXCollections.observableArrayList(ctr.getVeiculos(Integer.parseInt
                    (tvTransportes.getSelectionModel().getSelectedItem().getParam1()))));
        });
        
        tcMotorista.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcPlacaTransporte.setCellValueFactory(new PropertyValueFactory<>("param6"));
        tcSaida.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcChegada.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("param5"));
        
        tcMarca.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("param1"));
        tcModelo.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcChassi.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcAno.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcCor.setCellValueFactory(new PropertyValueFactory<>("param6"));
    }    

    private void pesquisar()
    {
        if(rbPeriodo.isSelected())
        {
            if(!dpFim.getEditor().getText().trim().equals("") && !dpInicio.getEditor().getText().trim().equals(""))
                if(dpFim.getValue().compareTo(dpInicio.getValue()) >= 0)
                    tvTransportes.setItems(FXCollections.observableArrayList(ctr.getByPeriodo
                        (dpInicio.getValue(),dpFim.getValue(), cbFinalizada.isSelected())));
                else
                    new Alert(Alert.AlertType.ERROR, "Data incorreta, data do fim do período deve ser maior que início", ButtonType.OK).showAndWait();
            else if(dpFim.getEditor().getText().trim().equals(""))
                new Alert(Alert.AlertType.ERROR, "Data inválida, selecione data do fim do período", ButtonType.OK).showAndWait();
            else
                new Alert(Alert.AlertType.ERROR, "Data inválida, selecione data do início do período", ButtonType.OK).showAndWait();
        }
        else
            tvTransportes.setItems(FXCollections.observableArrayList(ctr.getTransporte
                (tfFuncionarioVeiculo.getText(), rbFuncionario.isSelected())));
    }
    
}
