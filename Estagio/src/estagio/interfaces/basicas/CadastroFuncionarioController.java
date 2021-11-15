/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrEndereco;
import estagio.controladores.ctrFuncionario;
import estagio.controladores.ctrUsuario;
import estagio.utilidades.Banco;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.TooltippedTableCell;
import estagio.utilidades.Utils;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import static json.Json.consultaCep;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastroFuncionarioController implements Initializable
{
    private int acao;
    private String nivel;
    private String frente;
    private String verso;
    public String nome;

    public static final String PROP_NOME = "nome";
    
    private final ctrFuncionario ctrFunc = ctrFuncionario.instancia();
    private final ctrUsuario ctrUsu = ctrUsuario.instancia();
    private final ctrEndereco ctrEnder = ctrEndereco.instancia();
    
    private Objeto funcionario;
    private JSONObject endereco_pesquisado;
    
    private final Tooltip tooltip = new Tooltip();
    private final ToggleGroup goup = new ToggleGroup();
    
    private PopOver pop;
    private Pane fotos_cnh;

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneInfo;
    @FXML
    private Pane paneEndereco;
    @FXML
    private Pane paneContato;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbInfo;
    @FXML
    private Label lbCadastro;
    @FXML
    private Label lbVencimentoCNH;
    @FXML
    private Label lbCNH;
    @FXML
    private Label lbEndereco;
    @FXML
    private Label lbContato;
    @FXML
    private Label lbTelefone;
    @FXML
    private Label lbAlteracao;
    @FXML
    private Label lbPesquisa;
    @FXML
    private Label lbErroNome;
    @FXML
    private Label lbErroCPF;
    @FXML
    private Label lbErroRG;
    @FXML
    private Label lbErroCadastro;
    @FXML
    private Label lbErroVencimento;
    @FXML
    private Label lbErroCEP;
    @FXML
    private Label lbErroRua;
    @FXML
    private Label lbErroNumero;
    @FXML
    private Label lbErroBairro;
    @FXML
    private Label lbErroCidade;
    @FXML
    private Label lbErroEstado;
    @FXML
    private Label lbErroEmail;
    @FXML
    private Label lbErroTelefone;
    @FXML
    private Label lbErroFuncao;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfCPF;
    @FXML
    private JFXTextField tfRG;
    @FXML
    private JFXDatePicker dpCadastro;
    @FXML
    private JFXDatePicker dpVencimento;
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
    private JFXComboBox<String> cbFuncao;
    @FXML
    private JFXTextField tfNomePesquisa;
    @FXML
    private JFXTextField tfCpfPesquisa;
    @FXML
    private JFXRadioButton rbNome;
    @FXML
    private JFXRadioButton rbCPF;
    @FXML
    private JFXRadioButton rbTodos;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btAlterar;
    @FXML
    private JFXButton btExcluir;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btCarregar;
    @FXML
    private JFXButton btAdicionar;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private JFXButton btPesquisarCEP;
    @FXML
    private JFXButton btRemover;
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
    private FontAwesomeIconView faView;
    @FXML
    private FontAwesomeIconView faSearchCEP;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private FontAwesomeIconView faClean;
    @FXML
    private ImageView imgFrente;
    @FXML
    private ImageView imgVerso;
    @FXML
    private Separator sep1;
    @FXML
    private Separator sep2;
    @FXML
    private Separator sep3;
    @FXML
    private Separator sep4;
    @FXML
    private Separator sep5;
    @FXML
    private Separator sep6;
    @FXML
    private Separator sep7;
    @FXML
    private Separator sep8;
    @FXML
    private VBox painelCentral;
    @FXML
    private HBox hb;
    @FXML
    private TableView<Objeto> tvFuncionarios;
    @FXML
    private TableColumn<Objeto, String> tcNome;
    @FXML
    private TableColumn<Objeto, String> tcCPF;
    @FXML
    private TableColumn<Objeto, String> tcRG;
    @FXML
    private TableColumn<Objeto, String> tcEndereco;
    @FXML
    private TableColumn<Objeto, String> tcEmail;
    @FXML
    private TableColumn<Objeto, String> tcVencimento;
    @FXML
    private JFXListView<String> lvTelefones;

    /**
     * Initializes the controller class.
     */
    private void inicializaLabels()
    {
        lbErroBairro.setText("");
        lbErroCEP.setText("");
        lbErroCPF.setText("");
        lbErroCidade.setText("");
        lbErroCadastro.setText("");
        lbErroEmail.setText("");
        lbErroEstado.setText("");
        lbErroNome.setText("");
        lbErroFuncao.setText("");
        lbErroNumero.setText("");
        lbErroRG.setText("");
        lbErroRua.setText("");
        lbErroTelefone.setText("");
        lbErroVencimento.setText("");
    }
    
    private void limpaLabelsEndereco()
    {
        lbErroBairro.setText("");
        lbErroCEP.setText("");
        lbErroCidade.setText("");
        lbErroNumero.setText("");
        lbErroRua.setText("");
        lbErroEstado.setText("");
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6,Boolean b7,
            Boolean b8,Boolean b9)
    {
        paneInfo.setDisable(b1);
        paneEndereco.setDisable(b2);
        paneContato.setDisable(b3);
        panePesquisa.setDisable(b4);
        btNovo.setDisable(b5);
        btConfirmar.setDisable(b6);
        btAlterar.setDisable(b7);
        btExcluir.setDisable(b8);
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
        dpCadastro.setValue(LocalDate.now());
        dpVencimento.setValue(LocalDate.now());
        imgFrente.setImage(null);
        imgVerso.setImage(null);
        tfTelefone.clear();
        cbEstado.getSelectionModel().select(-1);
        cbFuncao.getSelectionModel().select(-1);
    }
    
    private void carregaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneInfo);
        nodes.add(paneEndereco);
        nodes.add(paneContato);
        nodes.add(panePesquisa);
        
        nodes.add(tfBairro);
        nodes.add(tfCEP);
        nodes.add(tfCPF);
        nodes.add(tfCidade);
        nodes.add(tfComplemento);
        nodes.add(tfCpfPesquisa);
        nodes.add(tfEmail);
        nodes.add(tfNome);
        nodes.add(tfNomePesquisa);
        nodes.add(tfNumero);
        nodes.add(tfRG);
        nodes.add(tfRua);
        nodes.add(tfTelefone);
        
        nodes.add(lbCNH);
        nodes.add(lbCadastro);
        nodes.add(lbContato);
        nodes.add(lbEndereco);
        nodes.add(lbInfo);
        nodes.add(lbTelefone);
        nodes.add(lbTitulo);
        nodes.add(lbVencimentoCNH);
        nodes.add(lbPesquisa);
        nodes.add(lbAlteracao);
        
        nodes.add(btAdicionar);
        nodes.add(btExcluir);
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btCarregar);
        nodes.add(btConfirmar);
        nodes.add(btNovo);
        nodes.add(btPesquisar);
        nodes.add(btPesquisarCEP);
        nodes.add(btRemover);
        
        nodes.add(rbCPF);
        nodes.add(rbNome);
        nodes.add(rbTodos);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faSearch);
        nodes.add(faSearchCEP);
        nodes.add(faTrash);
        nodes.add(faView);
        nodes.add(faClean);
        
        nodes.add(cbEstado);
        nodes.add(cbFuncao);
        
        Utils.setDesign(1, nodes);
        
        lbErroBairro.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroCEP.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroCPF.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroCidade.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroFuncao.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroEmail.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroEstado.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroNome.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroNumero.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroRG.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroRua.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroTelefone.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroCadastro.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        lbErroVencimento.setStyle(lbErroBairro.getStyle() + ";-fx-text-fill: red;");
        faView.setSize("20");
        faClean.setSize("20");
    }
    
    private void redimensionaTela()
    {
        acao = (int)(paneContato.getPrefHeight() + paneEndereco.getPrefHeight()
            + paneInfo.getPrefHeight() + 30/*espaços do vbx*/);
        panePrincipal.setPrefWidth(TelaPrincipalController.screenBounds.getMaxX() - 200);
        panePrincipal.setPrefHeight(TelaPrincipalController.screenBounds.getMaxY() - 60);
        lbTitulo.setPrefHeight(45);
        
        painelCentral.setPrefHeight(panePrincipal.getPrefHeight() - 47 - 45);
        panePesquisa.setPrefHeight(painelCentral.getPrefHeight() - acao);
        tvFuncionarios.setPrefHeight(panePesquisa.getPrefHeight() - 60);
        tvFuncionarios.setPrefWidth(panePesquisa.getPrefWidth() - 40);
        acao = (int)((tvFuncionarios.getPrefWidth() - 1087)/3);
        tcEmail.setPrefWidth(tcEmail.getPrefWidth() + acao);
        tcNome.setPrefWidth(tcNome.getPrefWidth() + acao);
        tcEndereco.setPrefWidth(tcEndereco.getPrefWidth() + acao);
    }
    
    private void setListeners()
    {
        tfNome.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(!tfNome.getText().equals(""))
                    lbErroNome.setText("");
                else
                    lbErroNome.setText("Campo requerido");
            }
            else
                lbErroNome.setText("");
        });
        
        tfCPF.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(tfCPF.getText().length() == 14)
                lbErroCPF.setText(Utils.validadorCPF2(tfCPF.getText(), funcionario, "funcionario"));
            /*if(tfCPF.getText().length() == 14)
                lbErroCPF.setText(validarCPF());
            else
                lbErroCPF.setText("");*/
        });
        
        tfCPF.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfCPF.getText().length() == 14)
                    //lbErroCPF.setText(validarCPF());
                    lbErroCPF.setText(Utils.validadorCPF2(tfCPF.getText(), funcionario, "funcionario"));
                else
                    lbErroCPF.setText("CPF incompleto");
            }
            else
            {
                if(tfCPF.getText().length() == 14)
                    //lbErroCPF.setText(validarCPF());
                    lbErroCPF.setText(Utils.validadorCPF2(tfCPF.getText(), funcionario, "funcionario"));
                else
                    lbErroCPF.setText("");
            }
        });
        
        dpVencimento.getEditor().textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue.equals("") && dpVencimento.getValue().compareTo(LocalDate.now()) <= 0)
                lbErroVencimento.setText("CNH vencida");
            else
                lbErroVencimento.setText("");
        });
        
        tfEmail.focusedProperty().addListener((observable, oldValue, newValue) -> 
        {
            if(!newValue)
            {
                if(!tfEmail.getText().equals("") && !Utils.validaEmail(tfEmail.getText()))
                    lbErroEmail.setText("Formato de email inválido");
                else
                    lbErroEmail.setText("");
            }
        });
        
        tfEmail.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue != null && newValue.length() > 8)
            {
                if(!newValue.contains("@"))
                    lbErroEmail.setText("Email inválido, faltando @");
                else 
                {
                    if(!newValue.substring(newValue.indexOf("@") + 1).contains(".") ||
                        newValue.substring(newValue.indexOf("@") + 1).indexOf(".") < 2)
                        lbErroEmail.setText("Email com formato inválido");
                    else
                    {
                        if(newValue.substring(newValue.lastIndexOf(".") + 1).length() < 1)
                            lbErroEmail.setText("Email com formato inválido");
                        else
                            lbErroEmail.setText("");
                    }  
                }
            }
        });
        
        tfRua.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(funcionario != null || endereco_pesquisado == null || endereco_pesquisado.getString("resultado").equals("0"))
                lbErroRua.setText("");
            else if(endereco_pesquisado != null && !endereco_pesquisado.getString("resultado").equals("0"))
            {
                if(endereco_pesquisado.getString("tipo_logradouro").equals("") &&
                    endereco_pesquisado.getString("logradouro").equals(""))
                    lbErroRua.setText("");
                else
                {
                    if(tfRua.getText().equals(endereco_pesquisado.getString("tipo_logradouro") + " " + 
                        endereco_pesquisado.getString("logradouro")))
                        lbErroRua.setText("");
                    else
                        lbErroRua.setText("Rua diferente da pesquisa online");
                }
            }
        });
        
        tfRua.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfRua.getText().equals(""))
                    lbErroRua.setText("Digite o nome da rua");
            }
            else 
                lbErroRua.setText("");
        });
        
        tfNumero.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfNumero.getText().equals(""))
                    lbErroNumero.setText("Digite o número");
            }
            else
                lbErroNumero.setText("");
        });
        
        tfBairro.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(funcionario != null || endereco_pesquisado == null || endereco_pesquisado.getString("resultado").equals("0"))
                lbErroBairro.setText("");
            else if(endereco_pesquisado != null && !endereco_pesquisado.getString("resultado").equals("0"))
            {
                if(endereco_pesquisado.getString("bairro").equals(""))
                    lbErroBairro.setText("");
                else
                {
                    if(tfBairro.getText().equals(endereco_pesquisado.getString("bairro")))
                        lbErroBairro.setText("");
                    else
                        lbErroBairro.setText("Bairro diferente da pesquisa online");
                }
            }
        });
        
        tfBairro.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfBairro.getText().equals(""))
                    lbErroBairro.setText("Digite o nome do bairro");
            }
            else
                lbErroBairro.setText("");
        });
        
        tfCidade.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(funcionario != null || endereco_pesquisado == null || endereco_pesquisado.getString("resultado").equals("0"))
                lbErroCidade.setText("");
            else if(endereco_pesquisado != null && !endereco_pesquisado.getString("resultado").equals("0"))
            {
                if(endereco_pesquisado.getString("cidade").equals(""))
                    lbErroCidade.setText("");
                else
                {
                    if(tfCidade.getText().equals(endereco_pesquisado.getString("cidade")))
                        lbErroCidade.setText("");
                    else
                        lbErroCidade.setText("Cidade diferente da pesquisa online");
                }
            }
        });
        
        tfCidade.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfCidade.getText().equals(""))
                    lbErroCidade.setText("Digite o nome da cidade");
            }
            else
                lbErroCidade.setText("");
        });
        
        imgFrente.imageProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue != null)
            {
                sep1.setVisible(false); sep2.setVisible(false); sep3.setVisible(false);
                sep4.setVisible(false); sep5.setVisible(false); sep6.setVisible(false);
                sep7.setVisible(false);
            }
            else
            {
                sep1.setVisible(true); sep2.setVisible(true); sep3.setVisible(true);
                sep4.setVisible(true); sep5.setVisible(true); sep6.setVisible(true);
                sep7.setVisible(true);
            }
        });
    }
    
    private void inicializa()
    {
        limparCampos();
        inicializaLabels();
        acao = -1;
        endereco_pesquisado = null;
        setEstado(true, true, true, false, false, true, true, true, true);
        clickPesquisar(new ActionEvent());
        lbAlteracao.setText("Data da Última Alteração: ");
        dpVencimento.getEditor().clear();
        fotos_cnh = null;
        lvTelefones.getItems().clear();
        funcionario = null;
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", 
            "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO");
        cbFuncao.getItems().addAll("Proprietário","Gerente","T.I","Motorista Fixo","Motorista Free-Lancer");
        
        tcNome.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcCPF.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcRG.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcEndereco.setCellValueFactory(new PropertyValueFactory<>("param11"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcVencimento.setCellValueFactory(new PropertyValueFactory<>("param15"));
        
        tcEndereco.setCellFactory(TooltippedTableCell.forTableColumn());
        tcNome.setCellFactory(TooltippedTableCell.forTableColumn());
        tcEmail.setCellFactory(TooltippedTableCell.forTableColumn());
        
        rbCPF.setToggleGroup(goup);
        rbNome.setToggleGroup(goup);
        rbTodos.setToggleGroup(goup);
        rbTodos.setSelected(true);
        
        MaskFieldUtil.cepField(tfCEP);
        MaskFieldUtil.cpfField(tfCPF);
        MaskFieldUtil.cpfField(tfCpfPesquisa);
        MaskFieldUtil.numericField(tfNumero);
        MaskFieldUtil.foneField(tfTelefone);
        MaskFieldUtil.rgField(tfRG);
        
        carregaDesign();
        inicializa();
        setListeners();
        redimensionaTela();
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        inicializa();
        setEstado(false, false, false, true, true, false, true, true, false);
        acao = 0;
        tfNome.requestFocus();
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(validaFuncionario())
        {
            Alert alerta;
            if(acao == 0 || acao == 2)//NOVO
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja confirmar cadastro do funcionário " + 
                    tfNome.getText() + "?", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(cbFuncao.getSelectionModel().getSelectedItem().equals("Motorista Free-Lancer"))
                    {///MOTORISTA FREE-LANCER TEM CADASTRO MÍNIMO
                        if(tfCPF.getText().length() < 14 || !validarCPF().equals(""))
                            tfCPF.setText("");
                        if(!Utils.validaEmail(tfEmail.getText()))
                            tfEmail.setText("");
                        
                        if(ctrFunc.salvarMinimo(tfNome,tfCPF,tfRG,tfEmail,cbFuncao,dpCadastro,tfCEP,tfRua,tfNumero,
                            tfBairro,tfComplemento,tfCidade,cbEstado,lvTelefones,dpVencimento,frente,verso))
                        {
                            new Alert(Alert.AlertType.INFORMATION, "Funcionáio cadastrado com sucesso!!!", 
                                    ButtonType.OK).showAndWait();
                            
                            if(acao == 2)
                            {
                                Stage stage = (Stage) btCancelar.getScene().getWindow();
                                stage.close();
                            }
                            inicializa();
                        }
                        else
                            new Alert(Alert.AlertType.ERROR, "Erro no cadastro do funcionário\n" + 
                                Banco.getCon().getMensagemErro(), ButtonType.OK).showAndWait();
                    }
                    else if(ctrFunc.salvar(tfNome,tfCPF,tfRG,tfEmail,cbFuncao,dpCadastro,tfCEP,tfRua,tfNumero,tfBairro
                       ,tfComplemento,tfCidade,cbEstado,lvTelefones,dpVencimento,frente,verso))
                    {
                        alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja autogerar um cadastro de usuário "
                            + "para este funcionário?", ButtonType.YES,ButtonType.NO);
                        alerta.showAndWait();
                        
                        if(alerta.getResult() == ButtonType.YES)
                        {
                            if(ctrUsu.salvar(tfNome.getText(), nivel))
                                Notifications.create()
                                    .darkStyle()
                                    //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                                    .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                                    .text("Usuário gerado com sucesso!!!")
                                    .showInformation();
                            else
                                Notifications.create()
                                    .darkStyle()
                                    //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                                    .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                                    .text("Erro na geração do usuário")
                                    .showError();
                        }
                        new Alert(Alert.AlertType.INFORMATION, "Funcionário cadastrado com sucesso!!!", ButtonType.OK)
                            .showAndWait();
                        if(acao == 2)
                        {
                            Stage stage = (Stage) btCancelar.getScene().getWindow();
                            stage.close();
                        }
                    }
                    else
                        new Alert(Alert.AlertType.ERROR, "Erro no cadastro do Funcionário\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK).showAndWait();
                    inicializa();
                }
            }
            else if(acao == 1 && funcionario != null)//ALTERAR
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar funcionário " + 
                        tfNome.getText() + "?", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrFunc.alterar(Integer.parseInt(funcionario.getParam1()),tfNome,tfCPF,tfRG,tfEmail,cbFuncao,
                       dpCadastro,tfCEP,tfRua,tfNumero,tfBairro,tfComplemento,tfCidade,cbEstado,lvTelefones,
                       dpVencimento,frente,verso))
                    {
                        inicializa();
                        new Alert(Alert.AlertType.INFORMATION, "Funcionário alterado com sucesso!!!", ButtonType.OK)
                            .showAndWait();
                    }
                    else
                        new Alert(Alert.AlertType.ERROR, "Erro na alteração do Funcionário\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK).showAndWait();
                }
            }
        }
    }

    private boolean validaFuncionario()
    {
        String erros = "";
        Alert a = null;
        inicializaLabels();
        
        if(tfNome.getText().trim().equals(""))
        {
            erros += "Digite o nome do funcionário\n";
            lbErroNome.setText("Digite o nome do funcionário");
        }
        
        if(!tfNome.getText().contains(" "))
        {
            a = new Alert(Alert.AlertType.WARNING, "Nome incompleto ou mal escrito\nDeixar nome incompleto "
                + "irá mudar a senha autogerada, deseja continuar?", ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            
            if(a.getResult() == ButtonType.NO)
            {
                erros += "Nome incompleto ou mal escrito\n";
                lbErroNome.setText("Nome incompleto ou mal escrito");
            }
        }
        
        if(cbFuncao.getSelectionModel().getSelectedIndex() < 0)
        {
            erros += "Selecione a função do funcionário\n";
            lbErroFuncao.setText("Seleciona a função");
        }
        else 
        {
            if(!cbFuncao.getSelectionModel().getSelectedItem().equals("Motorista Free-Lancer"))
            {
                if(tfCPF.getText().length() == 14)
                    lbErroCPF.setText(validarCPF());
                else if(tfCPF.getText().length() > 0)
                    lbErroCPF.setText("CPF incompleto");
                else
                    lbErroCPF.setText("Digite o CPF");
                erros += lbErroCPF.getText() + "\n";

                erros += validaAlteracaoEndereco(a);
                if(!validaEndereco())
                    erros += lbErroCEP.getText() + "\n" + lbErroRua.getText() + "\n" + lbErroNumero.getText() + "\n"
                        + lbErroBairro.getText() + "\n" + lbErroCidade.getText() + "\n" + lbErroEstado.getText()+"\n";
            }
            
            if(cbFuncao.getSelectionModel().getSelectedItem().equals("Motorista Fixo"))
            {
                if(dpVencimento.getEditor().getText().equals(""))
                {
                    erros += "Selecione o vencimento da CNH\n";
                    lbErroVencimento.setText("Selecione o vencimento");
                }
            }
        }
        
        if(!tfRG.getText().equals("") && tfRG.getText().length() < 12)
        {
            erros+= "Número do RG incompleto\n";
            lbErroRG.setText("RG incompleto");
        }
        
        if(!lbErroEmail.getText().equals(""))
        {
            erros += lbErroEmail.getText() + "\n";
        }
        
        if(!dpVencimento.getEditor().getText().equals(""))
        {
            if(dpVencimento.getValue().compareTo(LocalDate.now()) <= 0)
            {
                a = new Alert(Alert.AlertType.WARNING, "Cuidado, CNH vencida. Deseja continuar?",ButtonType.YES,
                    ButtonType.NO);
                a.showAndWait();
                
                if(a.getResult() == ButtonType.NO)
                    erros += "CHH vencida";
            }
        }
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        return erros.trim().equals("");
    }

    private boolean validaEndereco()
    {
        boolean flag = true;
        if(tfRua.getText().equals(""))
        {
            flag = false;
            lbErroRua.setText("Digite o nome da rua");
        }

        if(tfBairro.getText().equals(""))
        {
            flag = false;
            lbErroBairro.setText("Digite o nome do bairro");
        }

        if(tfCidade.getText().equals(""))
        {
            flag = false;
            lbErroCidade.setText("Digite o nome da Cidade");
        }

        if(cbEstado.getSelectionModel().getSelectedIndex() < 0)
        {
            flag = false;
            lbErroEstado.setText("Selecione estado");
        }

        if(tfNumero.getText().equals(""))
        {
            flag = false;
            lbErroNumero.setText("Digite o número");
        }

        if(endereco_pesquisado != null && endereco_pesquisado.getString("resultado").equals("0"))
        {
            flag = false;
            lbErroCEP.setText("CEP inválido");
        }
        else if(tfCEP.getText().equals(""))
        {
            flag = false;
            lbErroCEP.setText("Digite um CEP");
        }
        
        return flag;
    }
    
    private String validaAlteracaoEndereco(Alert alerta)
    {
        String avisos = "";
        
        if(endereco_pesquisado != null && !endereco_pesquisado.getString("resultado").equals("0"))
        {
            if(!endereco_pesquisado.getString("tipo_logradouro").equals("") &&
                !endereco_pesquisado.getString("logradouro").equals(""))
                if(!tfRua.getText().equals(endereco_pesquisado.getString("tipo_logradouro") + " " + 
                    endereco_pesquisado.getString("logradouro")))
                {
                    lbErroRua.setText("Rua diferente da pesquisa online");
                    avisos += "Rua diferente da pesquisa online\n";
                }
                else
                    lbErroRua.setText("");
            else
                lbErroRua.setText("");
            
            if(!endereco_pesquisado.getString("bairro").equals("") && 
                !tfBairro.getText().equals(endereco_pesquisado.getString("bairro")))
                {
                    lbErroBairro.setText("Bairro diferente da pesquisa online");
                    avisos += "Bairro diferente da pesquisa online\n";
                }
            else
                lbErroBairro.setText("");
            
            if(!endereco_pesquisado.getString("cidade").equals("") && 
                !tfCidade.getText().equals(endereco_pesquisado.getString("cidade")))
                {
                    lbErroCidade.setText("Cidade diferente da pesquisa online");
                    avisos += "Cidade diferente da pesquisa online\n";
                }
            else
                lbErroCidade.setText("");
            
            if(!endereco_pesquisado.getString("uf").equals("") && 
                !cbEstado.getSelectionModel().getSelectedItem().equals(endereco_pesquisado.getString("uf")))
                {
                    lbErroEstado.setText("Estado diferente da pesquisa online");
                    avisos += "Estado diferente da pesquisa online\n";
                }
            else
                lbErroEstado.setText("");
        }
        
        if(!avisos.equals(""))
        {
            alerta = new Alert(Alert.AlertType.WARNING, "Avisos:\n" + avisos + 
                "Deseja confirmar alteração dos dados?", ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();
            
            if(alerta.getResult() == ButtonType.YES)
                avisos = "";
        }
        return avisos;
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        if(!tvFuncionarios.getItems().isEmpty() && tvFuncionarios.getSelectionModel().getFocusedIndex() >= 0)
        {
            setEstado(false, false, false, true, true, false, true, true, false);
            funcionario = tvFuncionarios.getSelectionModel().getSelectedItem();
            acao = 1;
            fotos_cnh = carregarFotos();
        }
        else 
            if(tvFuncionarios.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Não há funcionários cadastrados para ser alterado", ButtonType.OK)
                    .showAndWait();
        else
            new Alert(Alert.AlertType.ERROR, "Selecione um funcionário para ser alterado", ButtonType.OK)
                    .showAndWait();
    }

    private Pane carregarFotos()
    {
        if(imgFrente.getImage() != null && imgVerso.getImage() != null)
        {
            Pane pane = new Pane();
            pane.setPrefHeight(700);
            pane.setPrefWidth(700);

            VBox vbox = new VBox(10);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPrefHeight(700);
            vbox.setPrefWidth(700);

            ImageView img1 = new ImageView(), img2 = new ImageView();
            img1.setFitHeight(300);
            img2.setFitHeight(300);
            img1.setFitWidth(300);
            img2.setFitWidth(300);
            img1.setImage(imgFrente.getImage());
            img2.setImage(imgVerso.getImage());
            Utils.centerImage(img2);
            Utils.centerImage(img1);

            vbox.getChildren().addAll(img1,img2);
            pane.getChildren().addAll(vbox);

            return pane;
        }
        else
            return null;
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        Alert alerta;
        if(!tvFuncionarios.getItems().isEmpty() && tvFuncionarios.getSelectionModel().getFocusedIndex() >= 0)
        {
            funcionario = tvFuncionarios.getSelectionModel().getSelectedItem();
            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover funcionário " + funcionario.getParam2()
                + "?", ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();
            
            if(alerta.getResult() == ButtonType.YES)
            {
                if(ctrFunc.inativar(Integer.parseInt(funcionario.getParam1())))
                {
                    alerta = new Alert(Alert.AlertType.INFORMATION, "Funcionário removido com sucesso!!!", 
                        ButtonType.OK);
                    inicializa();
                }
                else
                    alerta = new Alert(Alert.AlertType.ERROR, "Erro na remoção do funcionário", ButtonType.OK);
            }
        }
        else if(tvFuncionarios.getItems().isEmpty())
        {
            alerta = new Alert(Alert.AlertType.ERROR, "Não há funcionários cadastrados para ser alterado", 
                ButtonType.OK);
            funcionario = null;
        }
        else
        {
            alerta = new Alert(Alert.AlertType.ERROR, "Selecione um funcionário para ser alterado", ButtonType.OK);
            funcionario = null;
        }
        alerta.showAndWait();
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        if(acao == 2)
        {
            Stage stage = (Stage) btCancelar.getScene().getWindow();
            stage.close();
        }
        inicializa();
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        tvFuncionarios.getItems().clear();
        if(rbTodos.isSelected())
            tvFuncionarios.setItems(FXCollections.observableArrayList(ctrFunc.getByName("")));
        if(rbNome.isSelected())
            tvFuncionarios.setItems(FXCollections.observableArrayList(ctrFunc.getByName(tfNomePesquisa.getText())));
        else if(rbCPF.isSelected())
        {
            if(tfCpfPesquisa.getText().equals(""))
                new Alert(Alert.AlertType.ERROR, "Digite o CPF para pesquisar", ButtonType.OK).showAndWait();
            else if(tfCpfPesquisa.getText().length() < 14)
                new Alert(Alert.AlertType.ERROR, "Digite o CPF completo para pesquisar", ButtonType.OK).showAndWait();
            else
                tvFuncionarios.setItems(FXCollections.observableArrayList(ctrFunc.getByCPF(tfCpfPesquisa.getText())));
        }
    }

    @FXML
    private void clickPesquisarCEP(ActionEvent event)
    {
        limpaLabelsEndereco();
        String str = consultaCep(tfCEP.getText().replace("-", ""), "json");
        JSONObject my_obj = new JSONObject(str);
        if(!my_obj.getString("resultado").equals("0"))
        {
            endereco_pesquisado = my_obj;
            if(!my_obj.getString("tipo_logradouro").equals("") && !my_obj.getString("logradouro").equals(""))
                tfRua.setText(my_obj.getString("tipo_logradouro") + " " + my_obj.getString("logradouro"));
            else
                tfRua.setText("");
            tfCidade.setText(my_obj.getString("cidade"));
            tfBairro.setText(my_obj.getString("bairro"));
            cbEstado.getSelectionModel().select(my_obj.getString("uf"));
        }
        else
        {
            endereco_pesquisado = null;
            new Alert(Alert.AlertType.ERROR, "CEP não encontrado", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void selecionaFuncao(ActionEvent event)
    {
        if(cbFuncao.getSelectionModel().getSelectedIndex() >= 0)
        {
            lbErroFuncao.setText("");
            if(cbFuncao.getSelectionModel().getSelectedIndex() == 0)
                nivel = "alto";
            else if(cbFuncao.getSelectionModel().getSelectedIndex() <= 2)
                nivel = "medio";
            else if(cbFuncao.getSelectionModel().getSelectedIndex() > 2)
                nivel = "baixo";
        }
    }

    @FXML
    private void clickAddTelefone(ActionEvent event)
    {
        String tel = tfTelefone.getText().replace("(", "").replace(")", "").replace("-", "");
        if(!tel.equals(""))
            if(tel.length() < 10)
                if(tel.length() == 8)
                    lbErroTelefone.setText("Número inválido, insira o DDD");
                else
                    lbErroTelefone.setText("Número de telefone/celular inválido");
            else
            {
                lbErroTelefone.setText("");
                for(String telefone : lvTelefones.getItems())
                    if(telefone.replace("(", "").replace(")", "").replace("-", "").equals(tel))
                    {
                        lbErroTelefone.setText("Número de telefone/celular já cadastrado");
                        break;
                    }
                
                if(lbErroTelefone.getText().equals(""))
                {
                    lvTelefones.getItems().add(tfTelefone.getText());
                    tfTelefone.clear();
                    tfTelefone.requestFocus();
                }
            }
        else
            lbErroTelefone.setText("Digite um número de telefone/celular para inserir");
    }

    @FXML
    private void clickDelTelefone(ActionEvent event)
    {
        Alert alerta;
        if(!lvTelefones.getItems().isEmpty() && lvTelefones.getSelectionModel().getSelectedIndex() >= 0)
        {
            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover telefone/celular " 
                + lvTelefones.getSelectionModel().getSelectedItem() + "?", ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();
            
            if(alerta.getResult() == ButtonType.YES)
                lvTelefones.getItems().remove(lvTelefones.getSelectionModel().getSelectedItem());
        }
        else if(lvTelefones.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Não há telefone(s)/celular(es) a serem removidos", ButtonType.OK)
                .showAndWait();
        else
            new Alert(Alert.AlertType.ERROR, "Selecione um telefone/celular para remover", ButtonType.OK)
                .showAndWait();
    }

    @FXML
    private void selecionaTelefone(MouseEvent event)
    {
        Alert alerta;
        if(event.getClickCount() == 2)
        {
            if(!lvTelefones.getItems().isEmpty() && lvTelefones.getSelectionModel().getSelectedIndex() >= 0)
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar telefone/celular " + 
                    lvTelefones.getSelectionModel().getSelectedItem() + "?", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    tfTelefone.setText(lvTelefones.getSelectionModel().getSelectedItem());
                    lvTelefones.getItems().remove(lvTelefones.getSelectionModel().getSelectedItem());
                }
            }
            else 
            {
                alerta = new Alert(Alert.AlertType.ERROR);
                alerta.getButtonTypes().addAll(ButtonType.YES,ButtonType.NO);
                if(lvTelefones.getItems().isEmpty())
                    alerta.setContentText("Não há telefone(s)/celular(es) para alterar");
                else 
                    alerta.setContentText("Seleciona um telefone/celular para alterar");
                alerta.showAndWait();
            }
        }
    }

    @FXML
    private void selecionaFuncionario(MouseEvent event)
    {
        if(!tvFuncionarios.getItems().isEmpty() && tvFuncionarios.getSelectionModel().getSelectedIndex() >= 0)
        {
            limparCampos();
            funcionario = tvFuncionarios.getSelectionModel().getSelectedItem();
            fillFields(funcionario);
            setEstado(true, true, true, false, false, true, false, false, false);
        }
    }

    private void fillFields(Objeto funcionario)
    {
        tfNome.setText(funcionario.getParam2());
        tfCPF.setText(funcionario.getParam3());
        tfRG.setText(funcionario.getParam4());
        tfEmail.setText(funcionario.getParam5());
        cbFuncao.getSelectionModel().select(funcionario.getParam6());
        dpCadastro.setValue(Utils.convertToLocalDate(Utils.convertStringToDate(funcionario.getParam7())));
        lbAlteracao.setText("Data da Última Alteração: " + funcionario.getParam9());
        if(!funcionario.getParam15().equals("CNH não cadastrada"))
            dpVencimento.setValue(Utils.convertToLocalDate(Utils.convertStringToDate(funcionario.getParam12())));
        else
            dpVencimento.getEditor().clear();
        
        if(funcionario.getParam13() != null && funcionario.getParam14() != null)
        {
            imgFrente.setImage(
                SwingFXUtils.toFXImage(ctrFunc.carregaFrenteCNH(Integer.parseInt(funcionario.getParam1())), null));
            imgVerso.setImage(
                SwingFXUtils.toFXImage(ctrFunc.carregaVersoCNH(Integer.parseInt(funcionario.getParam1())), null));
            
            frente = verso = "atualizando";
        }
        else
        {
            imgFrente.setImage(null);
            imgVerso.setImage(null);
        }
        /*private int codigo;
        private String nome;
        private String cpf;
        private String rg;
        private String email;
        private String funcao;
        private Date data;
        private boolean ativo;
        private Timestamp alteracao;
        private Endereco endereco;
        private String endereco_completo;
        private Date vencimento;
        private BufferedImage cnh_frente;
        private BufferedImage cnh_verso;*/
        
        Objeto ender = ctrEnder.getByCodigo(Integer.parseInt(funcionario.getParam10()));
        if(!ender.getParam1().equals("0"))
        {
            tfCEP.setText(ender.getParam2());
            tfRua.setText(ender.getParam3());
            tfNumero.setText(ender.getParam4());
            tfBairro.setText(ender.getParam5());
            tfComplemento.setText(ender.getParam6());
            tfCidade.setText(ender.getParam7());
            cbEstado.getSelectionModel().select(ender.getParam8());
        }
        
        lvTelefones.getItems().clear();
        for(String telefone : ctrFunc.getTelefones(Integer.parseInt(funcionario.getParam1())))
        {
            tfTelefone.setText(telefone);
            lvTelefones.getItems().add(tfTelefone.getText());
        }
        tfTelefone.clear();
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
        tooltip.setText("Alterar Funcionário");
        ToolTip.bindTooltip(btAlterar, tooltip);
    }
    
    @FXML
    private void removerExit(MouseEvent event)
    {
        btExcluir.setStyle("-fx-cursor: default;" + btExcluir.getStyle());
    }

    @FXML
    private void removerEnter(MouseEvent event)
    {
        btExcluir.setStyle("-fx-cursor: hand;" + btExcluir.getStyle());
        tooltip.setText("Remover Funcionário");
        ToolTip.bindTooltip(btExcluir, tooltip);
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
    private void carregarExit(MouseEvent event)
    {
        btCarregar.setStyle("-fx-cursor: default;" + btCarregar.getStyle());
    }

    @FXML
    private void carregarEnter(MouseEvent event)
    {
        btCarregar.setStyle("-fx-cursor: hand;" + btCarregar.getStyle());
        tooltip.setText("Carregar Fotos da CNH");
        ToolTip.bindTooltip(btCarregar, tooltip);
    }

    @FXML
    private void cnhExit(MouseEvent event)
    {
        faView.setStyle("-fx-cursor: default;" + faView.getStyle());
    }

    @FXML
    private void cnhEnter(MouseEvent event)
    {
        faView.setStyle("-fx-cursor: hand;" + faView.getStyle());
        tooltip.setText("Clique para exibir");
        ToolTip.bindTooltip(faView, tooltip);
    }

    @FXML
    private void cnhClicked(MouseEvent event)
    {
        if(fotos_cnh != null && (pop == null || !pop.isShowing()))
        {
            pop = new PopOver(fotos_cnh);
            pop.setTitle("Fotos da CNH");
            pop.setArrowLocation(PopOver.ArrowLocation.RIGHT_TOP);
            pop.setDetachable(true);
            pop.show(faView);
        }
        else if(fotos_cnh == null)
        {
            pop = null;
            tooltip.setText("Sem fotos da CNH");
            ToolTip.bindTooltip(faView, tooltip);
        }
        else
            pop.hide();
    }

    @FXML
    private void cleanExit(MouseEvent event)
    {
        faClean.setStyle("-fx-cursor: default;" + faClean.getStyle());
    }

    @FXML
    private void cleanEnter(MouseEvent event)
    {
        faClean.setStyle("-fx-cursor: hand;" + faClean.getStyle());
        tooltip.setText("Clique para limpar fotos");
        ToolTip.bindTooltip(faClean, tooltip);
    }

    @FXML
    private void cleanClicked(MouseEvent event)
    {
        imgFrente.setImage(null);
        imgVerso.setImage(null);
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
    private void pesquisarExit(MouseEvent event)
    {
        btPesquisar.setStyle("-fx-cursor: default;" + btPesquisar.getStyle());
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
    {
        btPesquisar.setStyle("-fx-cursor: hand;" + btPesquisar.getStyle());
    }

    @FXML
    private void addExit(MouseEvent event)
    {
        btAdicionar.setStyle("-fx-cursor: default;" + btAdicionar.getStyle());
    }

    @FXML
    private void addEnter(MouseEvent event)
    {
        btAdicionar.setStyle("-fx-cursor: hand;" + btAdicionar.getStyle());
    }

    @FXML
    private void delExit(MouseEvent event)
    {
        btRemover.setStyle("-fx-cursor: default;" + btRemover.getStyle());
    }

    @FXML
    private void delEnter(MouseEvent event)
    {
        btRemover.setStyle("-fx-cursor: hand;" + btRemover.getStyle());
    }

    @FXML
    private void alteracaoExit(MouseEvent event)
    {
        lbAlteracao.setStyle("-fx-cursor: default;" + lbAlteracao.getStyle());
    }

    @FXML
    private void alteracaoEnter(MouseEvent event)
    {
        lbAlteracao.setStyle(lbAlteracao.getStyle() + ";-fx-cursor: hand;");
        tooltip.setText("Pesquisar data");
        ToolTip.bindTooltip(lbAlteracao, tooltip);
    }
    
    private String validarCPF()
    {
        if(!Utils.validaCPF(tfCPF.getText().replace(".", "").replace("-", "")))
            return "CPF inválido";
        else
        {
            int ret = ctrFunc.cpfExists(tfCPF.getText());
            if(ret > 0)
                if(funcionario == null || funcionario != null && ret != Integer.parseInt(funcionario.getParam1()))
                    return "CPF já cadastrado";
            return "";
        }
    }

    @FXML
    private void clickCarregar(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        List<File> file = fileChooser.showOpenMultipleDialog(null);
        if(file != null && !file.isEmpty() && file.size() == 2)
        {
            frente = file.get(0).getAbsolutePath();
            verso = file.get(1).getAbsolutePath();
            
            imgFrente.setImage(new Image(file.get(0).toURI().toString()));
            imgVerso.setImage(new Image(file.get(1).toURI().toString()));
            
            Utils.centerImage(imgFrente);
            Utils.centerImage(imgVerso);
            
            fotos_cnh = carregarFotos();
            
            sep1.setVisible(false); sep2.setVisible(false); sep3.setVisible(false);
            sep4.setVisible(false); sep5.setVisible(false); sep6.setVisible(false);
            sep7.setVisible(false);
        }
        else if(file != null && file.size() < 2)
            new Alert(Alert.AlertType.ERROR, "Selecione as duas fotos da CNH, frente e verso", ButtonType.OK)
                    .showAndWait();
        else
        {
            frente = "";
            verso = "";
            imgFrente = null;
        }
    }

    @FXML
    private void foneEnter(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickAddTelefone(new ActionEvent());
    }

    @FXML
    private void cepClicked(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickPesquisarCEP(new ActionEvent());
    }

    @FXML
    private void enterPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickConfirmar(new ActionEvent());
    }

    @FXML
    private void keyPressedPesquisar(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickPesquisar(new ActionEvent());
    }

    private void abrirCadastroUsuario()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/basicas/CadastroUsuario.fxml"));     
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            JFXDecorator decorator = new JFXDecorator(stage, root);

            decorator.setStyle("-fx-decorator-color: #040921;");

            Scene scene = new Scene(decorator);
            CadastroUsuarioController controller = fxmlLoader.<CadastroUsuarioController>getController();
            controller.setFuncionario(tfNome.getText(),nivel);
            
            stage.setTitle("Cadastro de Usuário");
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.showAndWait();

        } 
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Usuários! \nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void clickTodos(ActionEvent event)
    {
        clickPesquisar(new ActionEvent());
    }

    @FXML
    private void clickNome(ActionEvent event)
    {
        tfNomePesquisa.setVisible(true);
        tfCpfPesquisa.setVisible(false);
        tfNomePesquisa.clear();
    }

    @FXML
    private void clickCPF(ActionEvent event)
    {
        tfNomePesquisa.setVisible(false);
        tfCpfPesquisa.setVisible(true);
        tfCpfPesquisa.clear();
    }

        /**
     * Get the value of nome
     *
     * @return the value of nome
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Set the value of nome
     *
     * @param nome new value of nome
     */
    public void setNome(String nome)
    {
        clickNovo(new ActionEvent());
        String oldNome = this.nome;
        this.nome = nome;
        propertyChangeSupport.firePropertyChange(PROP_NOME, oldNome, nome);
        tfNome.setText(nome);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @FXML
    private void pesquisarAlteracao(MouseEvent event)
    {
        if(lbAlteracao.getText().substring(lbAlteracao.getText().indexOf(":") + 1).length() > 1)
        {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja pesquisar data de acesso?", ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();

            if(alerta.getResult() == ButtonType.YES)
            {
                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/basicas/CadastroUsuario.fxml"));     
                    Parent root = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    JFXDecorator decorator = new JFXDecorator(stage, root);

                    decorator.setStyle("-fx-decorator-color: #040921;");

                    Scene scene = new Scene(decorator);
                    CadastroUsuarioController controller = fxmlLoader.<CadastroUsuarioController>getController();
                    controller.pesquisarData(lbAlteracao.getText().substring(lbAlteracao.getText().indexOf(":") + 1).trim());

                    stage.setTitle("Cadastro de Usuário");
                    stage.setScene(scene);
                    stage.showAndWait();

                } 
                catch (IOException er)
                {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Usuários! \nErro: " + er.getMessage(), ButtonType.OK);
                    a.showAndWait();
                }
            }
        }
    }

    public void setFuncionario(String... nome)
    {
        clickNovo(new ActionEvent());
        acao = 2;
        if(nome.length > 0)
        {
            tfNome.setText(nome[0]);
            tfCPF.requestFocus();
        }
        else
            tfNome.requestFocus();
    }
}
