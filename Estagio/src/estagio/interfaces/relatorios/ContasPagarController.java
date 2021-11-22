/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.relatorios;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrPagamento;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.Utils;
import java.net.URL;
import java.time.LocalDate;
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
public class ContasPagarController implements Initializable
{
    private final ctrPagamento ctrPag = ctrPagamento.instancia();
    
    private final Tooltip tooltip = new Tooltip();
    private final ToggleGroup goup = new ToggleGroup();

    
    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane panePesquisa;
    @FXML
    private JFXDatePicker dpInicio;
    @FXML
    private Label lbAte;
    @FXML
    private JFXDatePicker dpFim;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private JFXRadioButton rbPeriodo;
    @FXML
    private JFXRadioButton rbNome;
    @FXML
    private Label lbTitulo;
    @FXML
    private TableView<Objeto> tvContas;
    @FXML
    private TableColumn<Objeto, String> tcFornecedor;
    @FXML
    private TableColumn<Objeto, String> tcNome;
    @FXML
    private TableColumn<Objeto, String> tcNumero;
    @FXML
    private TableColumn<Objeto, String> tcValor;
    @FXML
    private TableColumn<Objeto, String> tcVencimento;
    @FXML
    private FontAwesomeIconView faSearch;

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
        
        nodes.add(tfNome);
        
        nodes.add(lbAte);
        nodes.add(lbTitulo);
        
        nodes.add(rbPeriodo);
        nodes.add(rbNome);
        
        nodes.add(faSearch);
        
        Utils.setDesign(1, nodes);
        
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
        
        rbNome.setToggleGroup(goup);
        rbPeriodo.setToggleGroup(goup);
        rbPeriodo.setSelected(true);
        
        tcFornecedor.setCellValueFactory(new PropertyValueFactory<>("param1"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcVencimento.setCellValueFactory(new PropertyValueFactory<>("param6"));
        setlisteners();
    }    

    private void setlisteners()
    {
        rbNome.setOnAction((event) ->
        {
            dpFim.setVisible(false);
            dpInicio.setVisible(false);
            lbAte.setVisible(false);
            
            tfNome.setVisible(true);
            tfNome.clear();
        });
        
        rbPeriodo.setOnAction((event) ->
        {
            dpFim.setVisible(true);
            dpInicio.setVisible(true);
            lbAte.setVisible(true);
            dpFim.getEditor().clear();
            dpInicio.getEditor().clear();
            
            tfNome.setVisible(false);
        });
        
        btPesquisar.setOnAction((event) ->
        {
            tvContas.getItems().clear();
            if(rbNome.isSelected())
                tvContas.setItems(FXCollections.observableArrayList(ctrPag.getContasPagar(tfNome.getText())));
            else if(rbPeriodo.isSelected())
            {
                if(!dpInicio.getEditor().getText().trim().equals("") && !dpFim.getEditor().getText().trim().equals(""))
                {
                    if(dpFim.getValue().compareTo(dpInicio.getValue()) >= 0)
                        tvContas.setItems(FXCollections.observableArrayList(ctrPag.getContasPagar(dpInicio.getValue(), dpFim.getValue())));
                    else
                        new Alert(Alert.AlertType.ERROR, "Selecione um período de datas válido", ButtonType.OK).showAndWait();
                }
                else if(dpInicio.getEditor().getText().trim().equals(""))
                    new Alert(Alert.AlertType.ERROR, "Selecione a data inicial da pesquisa", ButtonType.OK).showAndWait();
                else
                    new Alert(Alert.AlertType.ERROR, "Selecione a data final da pesquisa", ButtonType.OK).showAndWait();
            }
        });
        
        btPesquisar.setOnMouseEntered((event) ->
        {
            btPesquisar.setStyle(btPesquisar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
            tooltip.setText("Pesquisar");
            ToolTip.bindTooltip(btPesquisar, tooltip);
        });
        
        btPesquisar.setOnMouseExited((event) ->
        {
            btPesquisar.setStyle(btPesquisar.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
        });
    }
    
}
