/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrTransporte;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
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
    private final Tooltip tooltip = new Tooltip();
    
    private ctrTransporte ctrTran = ctrTransporte.instancia();
    
    private int acao;
    private Objeto transporte;
    private String transporte_selecionado;

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private VBox paneLateral;
    @FXML
    private Pane paneDados;
    @FXML
    private Pane paneVeiculos;
    @FXML
    private ListView<String> lvTransportes;
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
    private JFXTextField tfMotorista;
    @FXML
    private JFXTextField tfPlacaCegonha;
    @FXML
    private JFXDatePicker dpSaida;
    @FXML
    private JFXDatePicker dpChegada;
    @FXML
    private JFXComboBox<String> cbStatus;
    @FXML
    private JFXComboBox<String> cbTipo;
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
    private TableView<Objeto> tvVeiculos;
    @FXML
    private TableColumn<Objeto,String> tcMarca;
    @FXML
    private TableColumn<Objeto,String> tcModelo;
    @FXML
    private TableColumn<Objeto,String> tcPlaca;
    @FXML
    private TableColumn<Objeto,String> tcChassi;
    @FXML
    private TableColumn<Objeto,String> tcAno;
    @FXML
    private TableColumn<Objeto,String> tcCor;
    @FXML
    private VBox painelCentral;
    @FXML
    private VBox vboxTable;
    @FXML
    private FontAwesomeIconView faPlus2;
    @FXML
    private JFXCheckBox cbFinalizados;

    /**
     * Initializes the controller class.
     */
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneLateral);
        nodes.add(paneDados);
        nodes.add(paneVeiculos);
        
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btNovo);
        nodes.add(btPesquisar);
        nodes.add(btRemover);
        nodes.add(btAdicionarVeiculo);
        nodes.add(btRemoverVeiculo);
        
        nodes.add(tfMotorista);
        nodes.add(tfPlacaCegonha);
        
        nodes.add(cbStatus);
        nodes.add(cbTipo);
        
        nodes.add(lvTransportes);
        
        nodes.add(cbFinalizados);
       
        nodes.add(lbChegada);
        nodes.add(lbSaida);
        nodes.add(lbVeiculos);
        nodes.add(lbTitulo);
        nodes.add(lbDados);
        nodes.add(lbTransporte);
        nodes.add(lbUltimaAlteracao);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faPlus2);
        nodes.add(faSearch);
        nodes.add(faMinus);
        nodes.add(faTrash);
        
        Utils.setDesign(1, nodes);
        
        btNovo.setStyle(btNovo.getStyle() + ";-fx-cursor: default;");
        btAlterar.setStyle(btAlterar.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
        btRemover.setStyle(btRemover.getStyle() + ";-fx-cursor: default;");
        btRemoverVeiculo.setStyle(btRemoverVeiculo.getStyle() + ";-fx-cursor: default;");
        btAdicionarVeiculo.setStyle(btAdicionarVeiculo.getStyle() + ";-fx-cursor: default;");
    }
    
    private void redimensiona()
    {
        ////////Tamanho da Tela
        panePrincipal.setPrefWidth(TelaPrincipalController.screenBounds.getMaxX() - 200);
        panePrincipal.setPrefHeight(TelaPrincipalController.screenBounds.getMaxY() - 80);
        
        //PAINEL CENTRAL TIRANDO TITULO E BOTÕES DE COMANDOS
        painelCentral.setPrefHeight(panePrincipal.getPrefHeight() - 47 - 45);
        //PAINEL DE PESQUISA FICA COM O RESTANTE DA TELA
        paneVeiculos.setPrefHeight(painelCentral.getPrefHeight() - paneDados.getPrefHeight() - 10);
        
        vboxTable.setPrefHeight(paneVeiculos.getPrefHeight() - 70);
        vboxTable.setPrefWidth(paneVeiculos.getPrefWidth());
        tvVeiculos.setPrefHeight(painelCentral.getPrefHeight() - 30);
        
        //REORGANIZA OS TAMANHOS DAS COLUNAS DA TABELA
        int sobra = (int)((tvVeiculos.getPrefWidth() - 1130)/2);
        tcMarca.setPrefWidth(tcMarca.getPrefWidth() + sobra);
        tcModelo.setPrefWidth(tcModelo.getPrefWidth() + sobra);
    }
    
    private void limpaLabels()
    {
        lbErroChegada.setText("");
        lbErroMotorista.setText("");
        lbErroPlacaCegonha.setText("");
        lbErroSaida.setText("");
        lbErroStatus.setText("");
        lbErroTipo.setText("");
    }
    
    private void limpaCampos()
    {
        tfMotorista.clear();
        tfPlacaCegonha.clear();
        dpSaida.setValue(LocalDate.now());
        dpChegada.getEditor().clear();
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6)
    {
        if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            painelCentral.setDisable(true);
            btNovo.setDisable(true);
            btConfirmar.setDisable(true);
            btRemover.setDisable(true);
            btAlterar.setDisable(true);
        }
        else
        {
            painelCentral.setDisable(b1);
            btNovo.setDisable(b2);
            btConfirmar.setDisable(b3);
            btRemover.setDisable(b5);
            btAlterar.setDisable(b4);
        }
        btCancelar.setDisable(b6);
    }
    
    private void inicializa()
    {
        limpaLabels();
        limpaCampos();
        
        lvTransportes.getItems().clear();
        
        ctrTran.getAll().forEach((objeto) ->
        {
            lvTransportes.getItems().add("Código de Transporte: " + objeto.getParam1() + " - " + objeto.getParam6() + 
                            "Motorista " + objeto.getParam9()  + " Com "
                            + objeto.getList1() != null && !objeto.getList1().isEmpty() ? objeto.getList1().size() + "" : "0" + "Veículos");
        });
        
        tvVeiculos.getItems().clear();
        setEstado(true, false, true, true, true, false);
        
        acao = -1;
        transporte = null;
        transporte_selecionado = null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesign();
        redimensiona();
        
        cbStatus.getItems().addAll("Á Iniciar","Em Progresso","Parado","Finalizado");
        
        cbTipo.getItems().addAll("Agregado","Terceirizado");
        
        inicializa();
        setlisteners();
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        inicializa();
        acao = 0;
        setEstado(false, true, false, true, true, false);
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        acao = 1;
        setEstado(false, true, false, true, true, false);
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        inicializa();
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
        if(!lvTransportes.getItems().isEmpty() && lvTransportes.getSelectionModel().getSelectedIndex() >= 0)
        {
            transporte_selecionado = lvTransportes.getSelectionModel().getSelectedItem();
            
            transporte = ctrTran.getByCodigo(Integer.parseInt
            (transporte_selecionado.substring
                (transporte_selecionado.indexOf(":") + 1),transporte_selecionado.indexOf(" -")));
            
            if(transporte != null)
            {
                tfMotorista.setText(transporte.getParam9());
                tfPlacaCegonha.setText(transporte.getParam10());
                dpChegada.setValue(LocalDate.parse(transporte.getParam5()));
                dpSaida.setValue(LocalDate.parse(transporte.getParam4()));
                cbStatus.getSelectionModel().select(transporte.getParam6());
                cbTipo.getSelectionModel().select(transporte.getParam7());
                
                lbUltimaAlteracao.setText("Data da Última Alteração: " + transporte.getParam8());
                
                tvVeiculos.setItems(FXCollections.observableArrayList(transporte.getList1()));
            }
        }
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

    private void setlisteners()
    {
        cbFinalizados.setOnAction((event) ->
        {
            lvTransportes.getItems().clear();
            
            if(cbFinalizados.isSelected())
                ctrTran.getAll(true).forEach((objeto) ->
                {
                    lvTransportes.getItems().add("Código de Transporte: " + objeto.getParam1() + " - " + objeto.getParam6() + 
                            "Motorista " + objeto.getParam9()  + " Com "
                            + objeto.getList1() != null && !objeto.getList1().isEmpty() ? objeto.getList1().size() + "" : "0" + "Veículos");
                });
            else
                ctrTran.getAll().forEach((objeto) ->
                {
                    lvTransportes.getItems().add("Código de Transporte: " + objeto.getParam1() + " - " + objeto.getParam6() + 
                            "Motorista " + objeto.getParam9()  + " Com "
                            + objeto.getList1() != null && !objeto.getList1().isEmpty() ? objeto.getList1().size() + "" : "0" + "Veículos");
                });
        });
    }
    
}
