/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.relatorios;

import com.jfoenix.controls.JFXDatePicker;
import estagio.controladores.ctrPagamento;
import estagio.controladores.ctrRecebimento;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
                tvMovimentacao.getItems().clear();
                if(dpFim.getValue().compareTo(dpInicio.getValue()) < 0)
                    Notifications.create()
                        .darkStyle()
                        //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                        .hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_CENTER)
                        .text("Período incorreto")
                        .showError();
                else
                {
                    tvMovimentacao.getItems().addAll(ctrPag.getMovimentacao(dpInicio.getValue(),dpFim.getValue()));
                    tvMovimentacao.getItems().addAll(ctrRec.getMovimentacao(dpInicio.getValue(),dpFim.getValue()));
                    tvMovimentacao.refresh();
                }
            }
            else
                Notifications.create()
                .darkStyle()
                //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                .hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_CENTER)
                .text("Selecione a data do início do período")
                .showWarning();
        });
        
        dpInicio.valueProperty().addListener((observable) ->
        {
            if(!dpFim.getEditor().getText().trim().equals(""))
            {
                tvMovimentacao.getItems().clear();
                if(dpFim.getValue().compareTo(dpInicio.getValue()) < 0)
                    Notifications.create()
                        .darkStyle()
                        //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                        .hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_CENTER)
                        .text("Período incorreto")
                        .showError();
                else
                {
                    tvMovimentacao.getItems().clear();
                    tvMovimentacao.getItems().addAll(ctrPag.getMovimentacao(dpInicio.getValue(),dpFim.getValue()));
                    tvMovimentacao.getItems().addAll(ctrRec.getMovimentacao(dpInicio.getValue(),dpFim.getValue()));
                    tvMovimentacao.refresh();
                }
            }
            else
                Notifications.create()
                .darkStyle()
                //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                .hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_CENTER)
                .text("Selecione a data do fim do período")
                .showWarning();
        });
    }    
    
}
