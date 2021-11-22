/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.relatorios;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class InadimplentesController implements Initializable
{
    private final ctrRecebimento ctr = ctrRecebimento.instancia();

    @FXML
    private Label lbTitulo;
    @FXML
    private TableView<Objeto> tvContas;
    @FXML
    private TableColumn<Objeto,String> tcFornecedor;
    @FXML
    private TableColumn<Objeto,String> tcNotaFiscal;
    @FXML
    private TableColumn<Objeto,String> tcNumero;
    @FXML
    private TableColumn<Objeto,String> tcValor;
    @FXML
    private TableColumn<Objeto,String> tcVencimento;
    @FXML
    private Pane panePrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(lbTitulo);
        
        Utils.setDesign(1, nodes);
        
        tcFornecedor.setCellValueFactory(new PropertyValueFactory<>("param1"));
        tcNotaFiscal.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcVencimento.setCellValueFactory(new PropertyValueFactory<>("param6"));
        
        tvContas.setItems(FXCollections.observableArrayList(ctr.getInadimplentes()));
    }    
    
}
