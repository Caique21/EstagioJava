/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrCliente;
import estagio.controladores.ctrEndereco;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import estagio.utilidades.ToolTip;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastroClienteController implements Initializable
{
    private final Tooltip tooltip = new Tooltip();
    private final ToggleGroup goup = new ToggleGroup();
    private Alert alert;
    private int acao;
    private final ctrEndereco ctrEnder = ctrEndereco.instancia();
    private final ctrCliente ctrCli = ctrCliente.instancia();
    private Objeto cliente;

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneInfoBasica;
    @FXML
    private Pane paneEndereco;
    @FXML
    private Pane paneContato;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbInfoBasica;
    @FXML
    private Label lbNascimento;
    @FXML
    private Label lbEndereco;
    @FXML
    private Label lbContato;
    @FXML
    private Label lbTelefones;
    @FXML
    private Label lbPesquisa;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faEdit;
    @FXML
    private FontAwesomeIconView faTrash;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private FontAwesomeIconView faSearchCEP;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfCPF;
    @FXML
    private JFXTextField tfRG;
    @FXML
    private JFXDatePicker dpData;
    @FXML
    private JFXTextField tfCEP;
    @FXML
    private JFXTextField tfRua;
    @FXML
    private JFXTextField tfNumero;
    @FXML
    private JFXTextField tfBairro;
    @FXML
    private JFXTextField tfComplemento;
    @FXML
    private JFXTextField tfCidade;
    @FXML
    private JFXComboBox<String> cbEstado;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfTelefone;
    @FXML
    private JFXTextField tfNomePesquisa;
    @FXML
    private JFXTextField tfCpfPesquisa;
    @FXML
    private JFXRadioButton rbNome;
    @FXML
    private JFXRadioButton rbCPF;
    @FXML
    private TableView<Objeto> tvClientes;
    @FXML
    private TableColumn<Objeto, String> tcNome;
    @FXML
    private TableColumn<Objeto, String> tcCPF;
    @FXML
    private TableColumn<Objeto, String> tcRG;
    @FXML
    private TableColumn<Objeto, String> tcData;
    @FXML
    private TableColumn<Objeto, String> tcEndereco;
    @FXML
    private TableColumn<Objeto, String> tcEmail;
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
    private JFXButton btAddTelefone;
    @FXML
    private JFXButton btDelTelefone;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private Label lbErroNome;
    @FXML
    private Label lbErroCPF;
    @FXML
    private Label lbErroRG;
    @FXML
    private Label lbErroData;
    @FXML
    private Label lbErroRua;
    @FXML
    private Label lbErroCEP;
    @FXML
    private Label lbErroNumero;
    @FXML
    private Label lbErroBairro;
    @FXML
    private Label lbErroEstado;
    @FXML
    private Label lbErroEmail;
    @FXML
    private Label lbErroTelefone;
    @FXML
    private Label lbErroCidade;
    @FXML
    private JFXListView<?> lvTelefones;
    @FXML
    private JFXButton btPesquisarCEP;

    /**
     * Initializes the controller class.
     */
    private void inicializaLabels()
    {
        lbErroBairro.setText("");
        lbErroCEP.setText("");
        lbErroCPF.setText("");
        lbErroCidade.setText("");
        lbErroData.setText("");
        lbErroEmail.setText("");
        lbErroNome.setText("");
        lbErroNumero.setText("");
        lbErroRG.setText("");
        lbErroRua.setText("");
        lbErroTelefone.setText("");
        lbErroEstado.setText("");
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6,Boolean b7,
            Boolean b8,Boolean b9)
    {
        paneInfoBasica.setDisable(b1);
        paneEndereco.setDisable(b2);
        paneContato.setDisable(b3);
        panePesquisa.setDisable(b4);
        btNovo.setDisable(b5);
        btConfirmar.setDisable(b6);
        btAlterar.setDisable(b7);
        btRemover.setDisable(b8);
        btCancelar.setDisable(b9);
    }
    
    private void limparCampos()
    {
        tfBairro.clear();
        tfCEP.clear();
        tfCPF.clear();
        tfCidade.clear();
        tfEmail.clear();
        tfNome.clear();
        tfNumero.clear();
        tfRG.clear();
        tfRua.clear();
        dpData.setValue(LocalDate.now());
        lvTelefones.getItems().clear();
        tfTelefone.clear();
    }
    
    private void setListeners()
    {
        
        InvalidationListener listenerCPF = (Observable observable) ->
        {
            Utils.validadorCPF(tfCPF.getText(), Integer.parseInt(cliente.getParam1()),ctrCli);
        };
        
        tfCPF.textProperty().addListener(listenerCPF);
        tfCPF.focusedProperty().addListener(listenerCPF);
        
        tfEmail.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            if(tfEmail.getText().length() >= 10 && !Utils.validaEmail(tfEmail.getText()))
                lbErroEmail.setText("Formato de email inválido");
            else
                lbErroEmail.setText("");
        });
        
        tfEmail.focusedProperty().addListener((observable, oldValue, newValue) -> 
        {
            if(!newValue)
            {
                if(!Utils.validaEmail(tfEmail.getText()))
                    lbErroEmail.setText("Formato de email inválido");
                else
                    lbErroEmail.setText("");
            }
        });
        
        tfCEP.setOnKeyPressed(e ->
        {
            if(e.getCode() == KeyCode.ENTER)
                clickPesquisarCEP(new ActionEvent());
        });
    }
    
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneInfoBasica);
        nodes.add(paneContato);
        nodes.add(paneEndereco);
        nodes.add(panePesquisa);
        
        nodes.add(btAlterar);
        nodes.add(btPesquisar);
        nodes.add(btPesquisarCEP);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btNovo);
        nodes.add(btRemover);
        nodes.add(btAddTelefone);
        nodes.add(btDelTelefone);
        
        nodes.add(lbInfoBasica);
        nodes.add(lbEndereco);
        nodes.add(lbContato);
        nodes.add(lbPesquisa);
        nodes.add(lbTitulo);
        nodes.add(lbNascimento);
        nodes.add(lbTelefones);
        
        /*nodes.add(tfBairro);
        nodes.add(tfCEP);
        nodes.add(tfCPF);
        nodes.add(tfCidade);
        nodes.add(tfEmail);
        nodes.add(tfNome);
        nodes.add(tfNumero);
        nodes.add(tfComplemento);
        nodes.add(tfRG);
        nodes.add(tfRua);
        nodes.add(tfTelefone);
        nodes.add(tfNomePesquisa);
        nodes.add(tfCpfPesquisa);*/
        
        nodes.add(rbCPF);
        nodes.add(rbNome);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faSearch);
        nodes.add(faSearchCEP);
        nodes.add(faTrash);
        nodes.add(cbEstado);
        Utils.setDesign(1, nodes);
    }
    
    private void inicializa()
    {
        inicializaLabels();
        rbNome.setSelected(true);
        
        setEstado(true, true, true, false, false, true, true, true, false);
        limparCampos();
        
        acao = -1;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", 
            "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO");
        cbEstado.setOnAction((event) ->
        {
            cbEstado.setStyle("-fx-background-color:" + Utils.getFonte() + 
                    ";-fx-prompt-text-fill: " + Utils.getPromptColor());
        });
     
        rbCPF.setToggleGroup(goup);
        rbNome.setToggleGroup(goup);
        
        MaskFieldUtil.cepField(tfCEP);
        MaskFieldUtil.cpfField(tfCPF);
        MaskFieldUtil.numericField(tfNumero);
        MaskFieldUtil.foneField(tfTelefone);
        MaskFieldUtil.rgField(tfRG);
        
        inicializaDesign();
        
        inicializa();
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        setEstado(false, false, false, true, true, false, true, true, false);
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        if(!tvClientes.getItems().isEmpty() && tvClientes.getSelectionModel().getFocusedIndex() >= 0)
        {
            cliente = tvClientes.getSelectionModel().getSelectedItem();
        }
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
    private void clickPesquisarCEP(ActionEvent event)
    {
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
    }

    @FXML
    private void novoExit(MouseEvent event)
    {
        btNovo.setStyle(btNovo.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void novoEnter(MouseEvent event)
    {
        btNovo.setStyle(btNovo.getStyle() + ";-fx-cursor: hand;");
        tooltip.setText("Iniciar Novo Cadastro");
        ToolTip.bindTooltip(btNovo, tooltip);
    }

    @FXML
    private void confirmarExit(MouseEvent event)
    {
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void confirmarEnter(MouseEvent event)
    {
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: hand;");
        tooltip.setText("Confirmar Ação");
        ToolTip.bindTooltip(btConfirmar, tooltip);
    }

    @FXML
    private void alterarExit(MouseEvent event)
    {
        btAlterar.setStyle(btAlterar.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void alterarEnter(MouseEvent event)
    {
        btAlterar.setStyle("-fx-cursor: hand;" + btAlterar.getStyle());
        tooltip.setText("Alterar Cliente");
        ToolTip.bindTooltip(btAlterar, tooltip);
    }
    
    @FXML
    private void removerExit(MouseEvent event)
    {
        btRemover.setStyle("-fx-cursor: default;" + btRemover.getStyle());
    }

    @FXML
    private void removerEnter(MouseEvent event)
    {
        btRemover.setStyle("-fx-cursor: hand;" + btRemover.getStyle());
        tooltip.setText("Remover Cliente");
        ToolTip.bindTooltip(btRemover, tooltip);
    }

    @FXML
    private void cancelarExit(MouseEvent event)
    {
        btCancelar.setStyle("-fx-cursor: default;" + btCancelar.getStyle());
    }

    @FXML
    private void cancelarEnter(MouseEvent event)
    {
        btCancelar.setStyle("-fx-cursor: hand;" + btCancelar.getStyle());
        tooltip.setText("Cancelar Ação");
        ToolTip.bindTooltip(btCancelar, tooltip);
    }

    @FXML
    private void cepExit(MouseEvent event)
    {
        btPesquisarCEP.setStyle(btPesquisarCEP.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void cepEnter(MouseEvent event)
    {
        btPesquisarCEP.setStyle(btPesquisarCEP.getStyle() + ";-fx-cursor: hand;");
        tooltip.setText("Pesquisar CEP online");
        ToolTip.bindTooltip(btPesquisarCEP, tooltip);
    }

    @FXML
    private void addTelEnter(MouseEvent event)
    {
        btAddTelefone.setStyle("-fx-cursor: hand;" + btAddTelefone.getStyle());
        tooltip.setText("Adiconar Telefone/Celular");
        ToolTip.bindTooltip(btAddTelefone, tooltip);
    }

    @FXML
    private void addTelExit(MouseEvent event)
    {
        btAddTelefone.setStyle("-fx-cursor: default;" + btAddTelefone.getStyle());
    }

    @FXML
    private void delTelEnter(MouseEvent event)
    {
        btDelTelefone.setStyle("-fx-cursor: hand;" + btDelTelefone.getStyle());
        tooltip.setText("Remover Telefone/Celular selecionado");
        ToolTip.bindTooltip(btDelTelefone, tooltip);
    }

    @FXML
    private void delTelExit(MouseEvent event)
    {
        btDelTelefone.setStyle("-fx-cursor: default;" + btDelTelefone.getStyle());
    }

    @FXML
    private void pesquisarExit(MouseEvent event)
    {
        btPesquisar.setStyle("-fx-cursor: default;" + btPesquisar.getStyle());
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
    {
        btPesquisar.setStyle("-fx-cursor: hand;" + btPesquisar.getStyle());
        tooltip.setText("Buscar Cliente");
        ToolTip.bindTooltip(btPesquisar, tooltip);
    }
}
