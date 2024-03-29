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
import estagio.controladores.ctrCliente;
import estagio.controladores.ctrEndereco;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import estagio.utilidades.ToolTip;
import estagio.utilidades.TooltippedTableCell;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static json.Json.consultaCep;
import org.json.JSONObject;

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
    private JSONObject endereco_pesquisado;

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
    private JFXListView<String> lvTelefones;
    @FXML
    private JFXButton btPesquisarCEP;
    @FXML
    private Label lbDataAlteracao;
    @FXML
    private VBox painelCentral;

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
        if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            paneInfoBasica.setDisable(true);
            paneEndereco.setDisable(true);
            paneContato.setDisable(true);
            btNovo.setDisable(true);
            btAlterar.setDisable(true);
            btRemover.setDisable(true);
        }
        else
        {
            paneInfoBasica.setDisable(b1);
            paneEndereco.setDisable(b2);
            paneContato.setDisable(b3);
            btNovo.setDisable(b5);
            btAlterar.setDisable(b7);
            btRemover.setDisable(b8);
        }
        panePesquisa.setDisable(b4);
        btConfirmar.setDisable(b6);
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
        tfCPF.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue.length() == 14)
                lbErroCPF.setText(Utils.validadorCPF(newValue, cliente, ctrCli));
            else
                lbErroCPF.setText("");
        });
        
        tfCPF.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfCPF.getText().length() == 14)
                    lbErroCPF.setText(Utils.validadorCPF(tfCPF.getText(), cliente, ctrCli));
                else
                    lbErroCPF.setText("CPF incompleto");
            }
            else
                if(tfCPF.getText().length() == 14)
                    lbErroCPF.setText(Utils.validadorCPF(tfCPF.getText(), cliente, ctrCli));
                else
                    lbErroCPF.setText("");
        });
        
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
                if(!tfEmail.getText().equals("") && !Utils.validaEmail(tfEmail.getText()))
                    lbErroEmail.setText("Formato de email inválido");
                else
                    lbErroEmail.setText("");
            }
        });
        
        tfEmail.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue.length() > 8)
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
            if(cliente != null || endereco_pesquisado == null || endereco_pesquisado.getString("resultado").equals("0"))
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
            if(cliente != null || endereco_pesquisado == null || endereco_pesquisado.getString("resultado").equals("0"))
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
            if(cliente != null || endereco_pesquisado == null || endereco_pesquisado.getString("resultado").equals("0"))
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
        
        cbEstado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if(cliente != null || endereco_pesquisado == null || endereco_pesquisado.getString("resultado").equals("0"))
                lbErroEstado.setText("");
            else if(endereco_pesquisado != null && !endereco_pesquisado.getString("resultado").equals("0"))
            {
                if(endereco_pesquisado.getString("cidade").equals(""))
                    lbErroEstado.setText("");
                else
                {
                    if(endereco_pesquisado.getString("uf").equals("") || 
                        cbEstado.getSelectionModel().getSelectedItem().equals(endereco_pesquisado.getString("uf")))
                        lbErroEstado.setText("");
                    else
                        lbErroEstado.setText("Estado diferente da pesquisa online");
                }
            }
        });
        
        dpData.getEditor().setEditable(false);
        dpData.getEditor().textProperty().addListener((observable, oldValue, newValue) ->
        {
            dpData.setValue(LocalDate.now());
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
        
        nodes.add(btAddTelefone);
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btDelTelefone);
        nodes.add(btNovo);
        nodes.add(btPesquisarCEP);
        nodes.add(btPesquisar);
        nodes.add(btRemover);
        
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
        
        nodes.add(lbContato);
        nodes.add(lbDataAlteracao);
        nodes.add(lbEndereco);
        nodes.add(lbInfoBasica);
        nodes.add(lbNascimento);
        nodes.add(lbPesquisa);
        nodes.add(lbTelefones);
        nodes.add(lbTitulo);
        
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
        
        btNovo.setStyle(btNovo.getStyle() + ";-fx-cursor: default;");
        btAlterar.setStyle(btAlterar.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
        btRemover.setStyle(btRemover.getStyle() + ";-fx-cursor: default;");
        btAddTelefone.setStyle(btAddTelefone.getStyle() + ";-fx-cursor: default;");
        btDelTelefone.setStyle(btDelTelefone.getStyle() + ";-fx-cursor: default;");
        btPesquisarCEP.setStyle(btPesquisarCEP.getStyle() + ";-fx-cursor: default;");
        lbDataAlteracao.setStyle(btPesquisarCEP.getStyle() + ";-fx-cursor: default;");
    }
    
    private void redimensiona()
    {
        panePrincipal.setPrefWidth(TelaPrincipalController.screenBounds.getMaxX() - 200);
        panePrincipal.setPrefHeight(TelaPrincipalController.screenBounds.getMaxY() - 80);
        lbTitulo.setPrefHeight(45);
        painelCentral.setPrefHeight(panePrincipal.getPrefHeight() - 47);
        panePesquisa.setPrefHeight(painelCentral.getPrefHeight() - 407);
        tvClientes.setPrefHeight(panePesquisa.getPrefHeight() - 60);
        tvClientes.setPrefWidth(panePesquisa.getPrefWidth() - 80);
        int sobra = (int)((tvClientes.getPrefWidth() - 1073)/3);
        tcNome.setPrefWidth(tcNome.getPrefWidth() + sobra);
        tcEndereco.setPrefWidth(tcEndereco.getPrefWidth() + sobra);
        tcEmail.setPrefWidth(tcEmail.getPrefWidth() + sobra);
    }
    
    private void inicializa()
    {
        setEstado(true, true, true, false, false, true, true, true, false);
        limparCampos();
        dpData.setValue(LocalDate.now());
        lbDataAlteracao.setText("Data da última alteração: ");
        
        acao = -1;
        cliente = null;
        endereco_pesquisado = null;
        clickPesquisar(new ActionEvent());
        inicializaLabels();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", 
            "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO");
        
        tcNome.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcCPF.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcRG.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcEndereco.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("param8"));
     
        rbCPF.setToggleGroup(goup);
        rbNome.setToggleGroup(goup);
        rbNome.setSelected(true);
        
        MaskFieldUtil.cepField(tfCEP);
        MaskFieldUtil.cpfField(tfCPF);
        MaskFieldUtil.cpfField(tfCpfPesquisa);
        MaskFieldUtil.numericField(tfNumero);
        MaskFieldUtil.foneField(tfTelefone);
        MaskFieldUtil.rgField(tfRG);
        
        inicializaDesign();
        inicializa();
        setListeners();
        redimensiona();
        
        tcEndereco.setCellFactory(TooltippedTableCell.forTableColumn());
        tcNome.setCellFactory(TooltippedTableCell.forTableColumn());
        tcEmail.setCellFactory(TooltippedTableCell.forTableColumn());
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
        if(validaCliente())
        {
            Alert alerta;
            if(acao == 0 || acao == 2)//INSERT
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION,"Deseja cadastrar cliente " + tfNome.getText() + "?",
                        ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrCli.salvar(tfNome,tfCPF,tfRG,dpData,tfCEP,tfRua,tfNumero,tfBairro,tfComplemento,tfCidade,
                    cbEstado,tfEmail,lvTelefones))
                    {
                        new Alert(Alert.AlertType.INFORMATION, "Cliente cadastrado com sucesso!!!", ButtonType.OK)
                            .showAndWait();
                        if(acao == 2)
                        {
                            Stage stage = (Stage) btNovo.getScene().getWindow();
                            stage.close();
                        }
                        inicializa();
                    }
                    else
                        new Alert(Alert.AlertType.ERROR, "Erro no cadastro do cliente", ButtonType.OK).showAndWait();
                }
            }
            else if(acao == 1 && cliente != null)//UPDATE
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION,"Deseja alterar cliente " + tfNome.getText() + "?",
                        ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrCli.alterar(Integer.parseInt(cliente.getParam1()),tfNome,tfCPF,tfRG,dpData,tfCEP,tfRua,tfNumero,tfBairro,tfComplemento,tfCidade,
                        cbEstado,tfEmail,lvTelefones))
                    {
                        new Alert(Alert.AlertType.INFORMATION, "Cliente alterado com sucesso!!!", ButtonType.OK)
                            .showAndWait();
                        inicializa();
                    }
                    else
                        new Alert(Alert.AlertType.ERROR, "Erro na alteração do cliente", ButtonType.OK).showAndWait();
                }
            }
        }
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        if(!tvClientes.getItems().isEmpty() && tvClientes.getSelectionModel().getFocusedIndex() >= 0)
        {
            setEstado(false, false, false, true, true, false, true, true, false);
            cliente = tvClientes.getSelectionModel().getSelectedItem();
            acao = 1;
        }
        else if(tvClientes.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Não há clientes cadastrados para ser alterado", ButtonType.OK)
                    .showAndWait();
        else
            new Alert(Alert.AlertType.ERROR, "Selecione um cliente para ser alterado", ButtonType.OK)
                    .showAndWait();
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        if(!tvClientes.getItems().isEmpty() && tvClientes.getSelectionModel().getFocusedIndex() >= 0)
        {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,"Deseja remover cliente " + 
                 tvClientes.getSelectionModel().getSelectedItem().getParam2() + "?",ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();
                
            if(alerta.getResult() == ButtonType.YES)
            {
                cliente = tvClientes.getSelectionModel().getSelectedItem();
                if(ctrCli.inativar(Integer.parseInt(cliente.getParam1())))
                {
                    new Alert(Alert.AlertType.INFORMATION, "Cliente removido com sucesso!!!", ButtonType.OK).showAndWait();
                    inicializa();
                }
                else
                    new Alert(Alert.AlertType.ERROR, "Erro na remoção do cliente", ButtonType.OK).showAndWait();
            }
        }
        else if(tvClientes.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Não há clientes cadastrados para ser alterado", ButtonType.OK)
                    .showAndWait();
        else
            new Alert(Alert.AlertType.ERROR, "Selecione um cliente para ser alterado", ButtonType.OK)
                    .showAndWait();
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        if(acao == 2)
        {
            Stage stage = (Stage) btNovo.getScene().getWindow();
            stage.close();
        }
        inicializa();
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
            tfRua.setText(my_obj.getString("tipo_logradouro") + " " + my_obj.getString("logradouro"));
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
    private void clickPesquisar(ActionEvent event)
    {
        tvClientes.getItems().clear();
        if(rbNome.isSelected())
        {
            tvClientes.setItems(FXCollections.observableArrayList(ctrCli.getByName(tfNomePesquisa.getText())));
        }
        else if(rbCPF.isSelected())
        {
            if(tfCpfPesquisa.getText().equals(""))
                new Alert(Alert.AlertType.ERROR, "Digite o CPF para pesquisar", ButtonType.OK).showAndWait();
            else if(tfCpfPesquisa.getText().length() < 14)
                new Alert(Alert.AlertType.ERROR, "Digite o CPF completo para pesquisar", ButtonType.OK).showAndWait();
            else
                tvClientes.setItems(FXCollections.observableArrayList(ctrCli.getByCPF(tfCpfPesquisa.getText())));
        }
    }

    @FXML
    private void selecionaCliente(MouseEvent event)
    {
        if(!tvClientes.getItems().isEmpty() && tvClientes.getSelectionModel().getFocusedIndex() >= 0)
        {
            setEstado(true, true, true, false, false, true, false, false, false);
            fillFields(tvClientes.getSelectionModel().getSelectedItem());
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
       if(event.getClickCount() == 2 && !lvTelefones.getItems().isEmpty() && 
            lvTelefones.getSelectionModel().getSelectedIndex() >= 0)
       {
           alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar telefone/celular " + 
                lvTelefones.getSelectionModel().getSelectedItem() + "?", ButtonType.YES,ButtonType.NO);
           alerta.showAndWait();
           
           if(alerta.getResult() == ButtonType.YES)
           {
               tfTelefone.setText(lvTelefones.getSelectionModel().getSelectedItem().replace("(", "")
                    .replace(")", "").replace("-", ""));
               lvTelefones.getItems().remove(lvTelefones.getSelectionModel().getSelectedItem());
               tfTelefone.requestFocus();
           }
       }
       else if(lvTelefones.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Não há telefone(s)/celular(es) a serem altreados", ButtonType.OK)
                .showAndWait();
       else if(lvTelefones.getSelectionModel().getSelectedIndex() < 0)
            new Alert(Alert.AlertType.ERROR, "Selecione um telefone/celular para remover", ButtonType.OK)
                .showAndWait();
    }

    @FXML
    private void pesquisaData(MouseEvent event)
    {
        if(lbDataAlteracao.getText().substring(lbDataAlteracao.getText().indexOf(":") + 1).trim().length() > 0)
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
                    controller.pesquisarData(lbDataAlteracao.getText().
                        substring(lbDataAlteracao.getText().indexOf(":") + 1).trim());

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
        }
    }

    @FXML
    private void novoExit(MouseEvent event)
    {
        btNovo.setStyle(btNovo.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
    }

    @FXML
    private void novoEnter(MouseEvent event)
    {
        btNovo.setStyle(btNovo.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Iniciar Novo Cadastro");
        ToolTip.bindTooltip(btNovo, tooltip);
    }

    @FXML
    private void confirmarExit(MouseEvent event)
    {
        btConfirmar.setStyle(btConfirmar.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
    }

    @FXML
    private void confirmarEnter(MouseEvent event)
    {
        btConfirmar.setStyle(btConfirmar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Confirmar Ação");
        ToolTip.bindTooltip(btConfirmar, tooltip);
    }

    @FXML
    private void alterarExit(MouseEvent event)
    {
        btAlterar.setStyle(btAlterar.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
    }

    @FXML
    private void alterarEnter(MouseEvent event)
    {
        btAlterar.setStyle(btAlterar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Alterar Cliente");
        ToolTip.bindTooltip(btAlterar, tooltip);
    }
    
    @FXML
    private void removerExit(MouseEvent event)
    {
        btRemover.setStyle(btRemover.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
    }

    @FXML
    private void removerEnter(MouseEvent event)
    {
        btRemover.setStyle(btRemover.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Remover Cliente");
        ToolTip.bindTooltip(btRemover, tooltip);
    }

    @FXML
    private void cancelarExit(MouseEvent event)
    {
        btCancelar.setStyle(btCancelar.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
    }

    @FXML
    private void cancelarEnter(MouseEvent event)
    {
        btCancelar.setStyle(btCancelar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Cancelar Ação");
        ToolTip.bindTooltip(btCancelar, tooltip);
    }

    @FXML
    private void cepExit(MouseEvent event)
    {
        btPesquisarCEP.setStyle(btPesquisarCEP.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
    }

    @FXML
    private void cepEnter(MouseEvent event)
    {
        btPesquisarCEP.setStyle(btPesquisarCEP.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Pesquisar CEP online");
        ToolTip.bindTooltip(btPesquisarCEP, tooltip);
    }

    @FXML
    private void addTelEnter(MouseEvent event)
    {
        btAddTelefone.setStyle(btAddTelefone.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Adiconar Telefone/Celular");
        ToolTip.bindTooltip(btAddTelefone, tooltip);
    }

    @FXML
    private void addTelExit(MouseEvent event)
    {
        btAddTelefone.setStyle(btAddTelefone.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
    }

    @FXML
    private void delTelEnter(MouseEvent event)
    {
        btDelTelefone.setStyle(btDelTelefone.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Remover Telefone/Celular selecionado");
        ToolTip.bindTooltip(btDelTelefone, tooltip);
    }

    @FXML
    private void delTelExit(MouseEvent event)
    {
        btDelTelefone.setStyle(btDelTelefone.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
    }

    @FXML
    private void pesquisarExit(MouseEvent event)
    {
        btPesquisar.setStyle(btPesquisar.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
    {
        btPesquisar.setStyle(btPesquisar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Buscar Cliente");
        ToolTip.bindTooltip(btPesquisar, tooltip);
    }

    @FXML
    private void alteracaoExit(MouseEvent event)
    {
        lbDataAlteracao.setStyle(lbDataAlteracao.getStyle().replace("-fx-cursor: hand;", "-fx-cursor: default;"));
    }

    @FXML
    private void alteracaoEnter(MouseEvent event)
    {
        lbDataAlteracao.setStyle(lbDataAlteracao.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        if(lbDataAlteracao.getText().substring(lbDataAlteracao.getText().indexOf(":") + 1).trim().length() > 0)
        {
            tooltip.setText("Pesquisar data");
            ToolTip.bindTooltip(lbDataAlteracao, tooltip);
        }
    }

    @FXML
    private void mainAction(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickConfirmar(new ActionEvent());
    }

    @FXML
    private void cepAction(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickPesquisarCEP(new ActionEvent());
    }

    @FXML
    private void foneAction(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickAddTelefone(new ActionEvent());
    }

    @FXML
    private void searchAction(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickPesquisar(new ActionEvent());
    }

    @FXML
    private void clickNome(ActionEvent event)
    {
        tfNomePesquisa.setVisible(true);
        tfNomePesquisa.clear();
        tfCpfPesquisa.setVisible(false);
    }

    @FXML
    private void clickCPF(ActionEvent event)
    {
        tfNomePesquisa.setVisible(false);
        tfCpfPesquisa.clear();
        tfCpfPesquisa.setVisible(true);
    }

    private boolean validaCliente()
    {
        String erros = "";
        inicializaLabels();
        Alert alerta = null;
        
        if(tfNome.getText().equals(""))
        {
            erros += "Nome do Cliente inválido\n";
            lbErroNome.setText("Digite o nome do cliente");
        }
        
        if(!tfCPF.getText().equals("") && tfCPF.getText().length() == 14)
        {
            lbErroCPF.setText(Utils.validadorCPF(tfCPF.getText(), cliente, ctrCli));
            erros += Utils.validadorCPF(tfCPF.getText(), cliente, ctrCli) + "\n";
        }
        else if(tfCPF.getText().equals(""))
        {
            lbErroCPF.setText("Digite o CPF do Cliente");
            erros += "Digite o CPF do Cliente\n";
        }
        else
        {
            lbErroCPF.setText("Número de CPF incompleto");
            erros += "Número de CPF incompleto\n";
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
        
        erros += validaAlteracaoEndereco(alerta);
        
        if(tfRua.getText().equals(""))
        {
            erros += "Rua inválida\n";
            lbErroRua.setText("Digite o nome da rua");
        }

        if(tfBairro.getText().equals(""))
        {
            erros += "Bairro inválido\n";
            lbErroBairro.setText("Digite o nome do bairro");
        }

        if(tfCidade.getText().equals(""))
        {
            erros += "Cidade inválida\n";
            lbErroCidade.setText("Digite o nome da Cidade");
        }

        if(cbEstado.getSelectionModel().getSelectedIndex() < 0)
        {
            erros += "Selecione um estado\n";
            lbErroEstado.setText("Selecione estado");
        }
        
        if(tfNumero.getText().equals(""))
        {
            erros += "Número inválido\n";
            lbErroNumero.setText("Digite o número");
        }
        
        if(endereco_pesquisado != null && endereco_pesquisado.getString("resultado").equals("0"))
        {
            erros += "CEP inválido\n";
            lbErroCEP.setText("CEP inválido");
        }
        else if(tfCEP.getText().equals(""))
        {
            erros += "CEP inválido\n";
            lbErroCEP.setText("Digite um CEP");
        }
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        return erros.trim().equals("");
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

    private void fillFields(Objeto selectedItem)
    {
        tfNome.setText(selectedItem.getParam2());
        tfCPF.setText(selectedItem.getParam3());
        tfRG.setText(selectedItem.getParam4());
        dpData.setValue(Utils.convertToLocalDate(Utils.convertData(selectedItem.getParam5())));
        tfEmail.setText(selectedItem.getParam8());
        lbDataAlteracao.setText("Data da última alteração: " + selectedItem.getParam10());
        
        Objeto ender = ctrEnder.getByCodigo(Integer.parseInt(selectedItem.getParam6()));
        tfCEP.setText(ender.getParam2());
        tfRua.setText(ender.getParam3());
        tfNumero.setText(ender.getParam4());
        tfBairro.setText(ender.getParam5());
        tfComplemento.setText(ender.getParam6());
        tfCidade.setText(ender.getParam7());
        cbEstado.getSelectionModel().select(ender.getParam8());
        
        lvTelefones.getItems().clear();
        if(selectedItem.getList1() != null)
            for(Objeto telefone : selectedItem.getList1())
            {
                tfTelefone.setText(telefone.getParam1());
                lvTelefones.getItems().add(tfTelefone.getText());
            }
        tfTelefone.clear();
        inicializaLabels();
    }

    public void setCli(String cli)
    {
        clickNovo(new ActionEvent());
        acao = 2;
        tfNome.setText(cli);
        tfCPF.requestFocus();
    }
}
