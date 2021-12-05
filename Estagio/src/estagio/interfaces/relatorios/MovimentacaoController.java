/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.relatorios;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrPagamento;
import estagio.controladores.ctrRecebimento;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class MovimentacaoController implements Initializable
{
    private final ctrPagamento ctrPag = ctrPagamento.instancia();
    private final ctrRecebimento ctrRec = ctrRecebimento.instancia();

    @FXML
    private JFXDatePicker dpInicio;
    @FXML
    private JFXDatePicker dpFim;
    @FXML
    private Label lbAte;
    @FXML
    private Label lbTitulo;
    @FXML
    private TableView<Objeto> tvMovimentacao;
    @FXML
    private TableColumn<Objeto, String> tcNome;
    @FXML
    private TableColumn<Objeto, String> tcNumero;
    @FXML
    private TableColumn<Objeto, String> tcValor;
    @FXML
    private TableColumn<Objeto, String> tcData;
    @FXML
    private TableColumn<Objeto, String> tcForma;
    @FXML
    private BorderPane painelPrincipal;
    @FXML
    private Pane painelBusca;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private FontAwesomeIconView faSarch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(painelPrincipal);
        nodes.add(painelBusca);
        
        nodes.add(btPesquisar);
        
        nodes.add(faSarch);
        
        nodes.add(lbAte);
        nodes.add(lbTitulo);
        
        Utils.setDesign(1, nodes);
        
        tcData.setCellValueFactory(new PropertyValueFactory<>("param6"));
        tcForma.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("param1"));
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param3"));
        
        tvMovimentacao.getItems().addAll(ctrPag.getMovimentacao());
        tvMovimentacao.getItems().addAll(ctrRec.getMovimentacao());
        
        dpFim.valueProperty().addListener((observable) ->
        {
            if(!dpInicio.getEditor().getText().trim().equals(""))
            {
                if(dpFim.getValue().compareTo(dpInicio.getValue()) < 0)
                    new Alert(Alert.AlertType.ERROR, "Período de data inválida", ButtonType.OK).showAndWait();
            }
        });
        
        dpInicio.valueProperty().addListener((observable) ->
        {
            if(!dpFim.getEditor().getText().trim().equals(""))
                if(dpFim.getValue().compareTo(dpInicio.getValue()) < 0)
                    new Alert(Alert.AlertType.ERROR, "Período de data inválida", ButtonType.OK).showAndWait();
        });
    }    

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        tvMovimentacao.getItems().clear();
        if(!dpFim.getEditor().getText().trim().equals("") && !dpInicio.getEditor().getText().trim().equals(""))
        {
            if(dpFim.getValue().compareTo(dpInicio.getValue()) >= 0)
                tvMovimentacao.getItems().addAll(ctrPag.getMovimentacao(dpInicio.getValue(),dpFim.getValue()));
            else
                new Alert(Alert.AlertType.ERROR, "Período de data inválida", ButtonType.OK).showAndWait();
        } 
    }
    
}
