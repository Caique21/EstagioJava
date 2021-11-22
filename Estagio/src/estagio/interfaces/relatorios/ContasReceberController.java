/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.relatorios;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import estagio.controladores.ctrRecebimento;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ContasReceberController implements Initializable
{
    private final ctrRecebimento ctr = ctrRecebimento.instancia();
    private final ToggleGroup goup = new ToggleGroup();

    @FXML
    private JFXDatePicker dpData;
    @FXML
    private JFXDatePicker dpInicio;
    @FXML
    private JFXDatePicker dpFim;
    @FXML
    private JFXRadioButton rbData;
    @FXML
    private JFXRadioButton rbPeriodo;
    @FXML
    private Label lbTitulo;
    @FXML
    private TableView<Objeto> tvContas;
    @FXML
    private TableColumn<Objeto, String> tcFornecedor;
    @FXML
    private TableColumn<Objeto, String> tcNotaFiscal;
    @FXML
    private TableColumn<Objeto, String> tcNumero;
    @FXML
    private TableColumn<Objeto, String> tcValor;
    @FXML
    private TableColumn<Objeto, String> tcVencimento;
    @FXML
    private BorderPane painelPrincipal;
    @FXML
    private Pane painelBusca;
    @FXML
    private Label lbAte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(painelPrincipal);
        nodes.add(painelBusca);
        
        nodes.add(lbAte);
        nodes.add(lbTitulo);
        
        nodes.add(rbPeriodo);
        nodes.add(rbData);
        
        Utils.setDesign(1, nodes);
        
        rbData.setToggleGroup(goup);
        rbPeriodo.setToggleGroup(goup);
        rbData.setSelected(true);
        
        tcFornecedor.setCellValueFactory(new PropertyValueFactory<>("param1"));
        tcNotaFiscal.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcVencimento.setCellValueFactory(new PropertyValueFactory<>("param6"));
        setlisteners();
    }    

    private void setlisteners()
    {
        rbData.setOnAction((event) ->
        {
            dpData.setVisible(true);
            dpData.getEditor().clear();
            
            dpFim.setVisible(false);
            dpInicio.setVisible(false);
            lbAte.setVisible(false);
        });
        
        rbPeriodo.setOnAction((event) ->
        {
            dpData.setVisible(false);
            
            dpFim.setVisible(true);
            dpInicio.setVisible(true);
            lbAte.setVisible(true);
            dpFim.getEditor().clear();
            dpInicio.getEditor().clear();
        });
        
        dpData.valueProperty().addListener((observable) ->
        {
            if(rbData.isSelected())
            {
                tvContas.getItems().clear();
                tvContas.setItems(FXCollections.observableArrayList(ctr.getContasVencer(dpData.getValue())));
            }
        });
        
        dpFim.valueProperty().addListener((observable) ->
        {
            if(rbPeriodo.isSelected())
            {
                if(!dpInicio.getEditor().getText().trim().equals(""))
                {
                    if(dpFim.getValue().compareTo(dpInicio.getValue()) >= 0)
                    {
                        tvContas.setItems(FXCollections.observableArrayList
                            (ctr.getContasVencer(dpInicio.getValue(),dpFim.getValue())));
                    }
                    else
                    {
                        dpFim.getEditor().clear();
                        new Alert(Alert.AlertType.ERROR, "Período de data inválida", ButtonType.OK).showAndWait();
                    }
                }
                else
                    new Alert(Alert.AlertType.ERROR, "Selecione data inicial do período", ButtonType.OK).showAndWait();
            }
        });
        
        dpInicio.valueProperty().addListener((observable) ->
        {
           if(rbPeriodo.isSelected())
               if(!dpFim.getEditor().getText().trim().equals(""))
                   tvContas.setItems(FXCollections.observableArrayList
                            (ctr.getContasVencer(dpInicio.getValue(),dpFim.getValue())));
        });
    }
    
}
