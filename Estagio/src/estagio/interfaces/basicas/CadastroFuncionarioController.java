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
import estagio.TelaPrincipalController;
import estagio.controladores.ctrEndereco;
import estagio.controladores.ctrFuncionario;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.Utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import static json.Json.consultaCep;
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
    private ctrFuncionario ctrFunc = ctrFuncionario.instancia();
    private final ctrEndereco ctrEnder = ctrEndereco.instancia();
    private Objeto funcionario;
    private JSONObject endereco_pesquisado;
    private final Tooltip tooltip = new Tooltip();
    private final ToggleGroup goup = new ToggleGroup();

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
    private Label lbPesquisa;
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
    private HBox hb;
    @FXML
    private JFXButton btPesquisarCEP;
    @FXML
    private JFXButton btRemover;
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
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faSearch);
        nodes.add(faSearchCEP);
        nodes.add(faTrash);
        nodes.add(faView);
        
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
        tfCPF.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(tfCPF.getText().length() == 14)
                lbErroCPF.setText(validarCPF());
            else
                lbErroCPF.setText("");
        });
        
        tfCPF.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfCPF.getText().length() == 14)
                    lbErroCPF.setText(validarCPF());
                else
                    lbErroCPF.setText("CPF incompleto");
            }
            else
            {
                if(tfCPF.getText().length() == 14)
                    lbErroCPF.setText(validarCPF());
                else
                    lbErroCPF.setText("");
            }
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
        
        tfRua.textProperty().addListener((observable) ->
        {
            if(tfRua.getText().equals(""))
                lbErroRua.setText("Digite o nome da rua");
            else if(funcionario == null || 
                (endereco_pesquisado != null && !endereco_pesquisado.getString("resultado").equals("0")))
                {
                    if(!endereco_pesquisado.getString("tipo_logradouro").equals("") && 
                        !endereco_pesquisado.getString("logradouro").equals(""))
                        if(!tfRua.getText().equals(endereco_pesquisado.getString("tipo_logradouro") + " " + 
                            endereco_pesquisado.getString("logradouro")))
                            lbErroRua.setText("Rua diferente da pesquisa online");
                }
            else
                lbErroRua.setText("");
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
                    lbErroNumero.setText("Digite o nome da rua");
            }
            else 
                lbErroNumero.setText("");
        });
        
        tfBairro.textProperty().addListener((observable) ->
        {
            if(tfBairro.getText().equals(""))
                lbErroBairro.setText("Digite o nome do bairro");
            else if(funcionario == null || 
                (endereco_pesquisado != null && !endereco_pesquisado.getString("resultado").equals("0")))
                {
                    if(!endereco_pesquisado.getString("cidade").equals("") && 
                            !tfBairro.getText().equals(endereco_pesquisado.getString("bairro")))
                        lbErroBairro.setText("Bairro diferente da pesquisa online");
                }
            else
                lbErroBairro.setText("");
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
        
        tfCidade.textProperty().addListener((observable) ->
        {
            if(tfCidade.getText().equals(""))
                lbErroCidade.setText("Digite o nome da cidade");
            else if(funcionario == null || 
                (endereco_pesquisado != null && !endereco_pesquisado.getString("resultado").equals("0")))
                {
                    if(!endereco_pesquisado.getString("cidade").equals("") && 
                            !tfCidade.getText().equals(endereco_pesquisado.getString("cidade")))
                        lbErroCidade.setText("Cidade diferente da pesquisa online");
                }
            else
                lbErroCidade.setText("");
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
    }
    
    private void inicializa()
    {
        limparCampos();
        inicializaLabels();
        acao = -1;
        clickPesquisar(new ActionEvent());
        lbAlteracao.setText("Data da Última Alteração: ");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        carregaDesign();
        inicializa();
        setListeners();
        
        cbEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", 
            "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO");
        cbFuncao.getItems().addAll("Gerente","Motorista");
        
        tcNome.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcCPF.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcRG.setCellValueFactory(new PropertyValueFactory<>("param4"));
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
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.getButtonTypes().addAll(ButtonType.YES,ButtonType.NO);
            if(acao == 0)//NOVO
            {
                alerta.setContentText("Deseja confirmar cadastro do funcionário " + tfNome.getText() + "?");
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrFunc.salvar(tfNome,tfCPF,tfRG,tfEmail,cbFuncao,dpCadastro,tfCEP,tfRua,tfNumero,tfBairro,
                        tfComplemento,tfCidade,cbEstado,lvTelefones,dpVencimento,frente,verso))
                    {
                        inicializa();
                        new Alert(Alert.AlertType.INFORMATION, "Funcionário cadastrado com sucesso!!!", ButtonType.OK)
                            .showAndWait();
                    }
                    else
                        new Alert(Alert.AlertType.ERROR, "Erro no cadastro do Funcionário", ButtonType.OK)
                            .showAndWait();
                }
            }
            else if(acao == 1 && funcionario != null)//ALTERAR
            {
                alerta.setContentText("Deseja alterar funcionário " + tfNome.getText() + "?");
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
                        new Alert(Alert.AlertType.ERROR, "Erro na alteração do Funcionário", ButtonType.OK)
                            .showAndWait();
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
        
        if(!lbErroCPF.getText().equals(""))
            erros += lbErroCPF.getText() + "\n";
        
        if(!tfRG.getText().equals("") && tfRG.getText().length() < 12)
        {
            erros+= "Número do RG incompleto\n";
            lbErroRG.setText("RG incompleto");
        }
        
        if(!lbErroEmail.getText().equals(""))
        {
            erros += lbErroEmail.getText() + "\n";
        }
        
        erros += validaAlteracaoEndereco(a);
        
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
        
        if(lvTelefones.getItems().isEmpty())
        {
            a = new Alert(Alert.AlertType.WARNING, "Nenhum telefone/celular cadastrado, deseja continuar?", 
                    ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            
            if(a.getResult() == ButtonType.NO)
                erros += "Nenhum telefone/celular cadastrado\n";
        }
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        return erros.trim().equals("");
    }
    
    private String validaAlteracaoEndereco(Alert a)
    {
        String avisos = "";
        if(funcionario == null || endereco_pesquisado != null)
        {
            if(!endereco_pesquisado.getString("resultado").equals("0"))
            {
                if(!lbErroRua.getText().equals(""))
                    avisos += lbErroRua.getText() + "\n";
                if(!lbErroNumero.getText().equals(""))
                    avisos += lbErroNumero.getText() + "\n";
                if(!lbErroBairro.getText().equals(""))
                    avisos += lbErroBairro.getText() + "\n";
                if(!lbErroCidade.getText().equals(""))
                    avisos += lbErroCidade.getText() + "\n";
                if(!lbErroEstado.getText().equals(""))
                    avisos += lbErroEstado.getText() + "\n";
            }
        }
        if(!avisos.trim().equals(""))
        {
            a = new Alert(Alert.AlertType.WARNING, "Aviso: " + avisos + "Deseja confirmar alterações?", 
                    ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            if(a.getResult() == ButtonType.YES)
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
        }
        else if(tvFuncionarios.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Não há funcionários cadastrados para ser alterado", ButtonType.OK)
                    .showAndWait();
        else
            new Alert(Alert.AlertType.ERROR, "Selecione um funcionário para ser alterado", ButtonType.OK)
                    .showAndWait();
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        if(!tvFuncionarios.getItems().isEmpty() && tvFuncionarios.getSelectionModel().getFocusedIndex() >= 0)
        {
            funcionario = tvFuncionarios.getSelectionModel().getSelectedItem();
            if(ctrFunc.inativar(Integer.parseInt(funcionario.getParam1())))
            {
                new Alert(Alert.AlertType.INFORMATION, "Funcionário removido com sucesso!!!", ButtonType.OK).showAndWait();
                inicializa();
            }
            else
                new Alert(Alert.AlertType.ERROR, "Erro na remoção do funcionário", ButtonType.OK).showAndWait();
        }
        else if(tvFuncionarios.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Não há funcionários cadastrados para ser alterado", ButtonType.OK)
                    .showAndWait();
        else
            new Alert(Alert.AlertType.ERROR, "Selecione um funcionário para ser alterado", ButtonType.OK)
                    .showAndWait();
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        inicializa();
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        tvFuncionarios.getItems().clear();
        if(rbNome.isSelected())
        {
            tvFuncionarios.setItems(FXCollections.observableArrayList(ctrFunc.getByName(tfNomePesquisa.getText())));
        }
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

    private void clickPesquisarCEP(MouseEvent event)
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
    private void selecionaFuncao(ActionEvent event)
    {
        if(cbFuncao.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(cbFuncao.getSelectionModel().getSelectedIndex() <= 1)
                nivel = "alto";
            else if(cbFuncao.getSelectionModel().getSelectedIndex() > 1)
                nivel = "baixo";
        }
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
        if(event.getClickCount() == 2 && !lvTelefones.getItems().isEmpty() 
            && lvTelefones.getSelectionModel().getSelectedIndex() >= 0)
        {
            tfTelefone.setText(lvTelefones.getSelectionModel().getSelectedItem());
            lvTelefones.getItems().remove(lvTelefones.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void selecionaFuncionario(MouseEvent event)
    {
        if(!tvFuncionarios.getItems().isEmpty() && tvFuncionarios.getSelectionModel().getSelectedIndex() >= 0)
        {
            funcionario = tvFuncionarios.getSelectionModel().getSelectedItem();
            fillFields(funcionario);
        }
    }

    private void fillFields(Objeto funcionario)
    {
        tfNome.setText(funcionario.getParam2());
        tfCPF.setText(funcionario.getParam3());
        tfRG.setText(funcionario.getParam4());
        tfEmail.setText(funcionario.getParam5());
        cbFuncao.getSelectionModel().select(funcionario.getParam6());
        dpCadastro.setValue(Utils.convertToLocalDate(Utils.convertData(funcionario.getParam7())));
        lbAlteracao.setText("Data da Última Alteração: " + funcionario.getParam8());
        dpVencimento.setValue(Utils.convertToLocalDate(Utils.convertData(funcionario.getParam12())));
        
        if(funcionario.getParam13() != null)
        {
            byte[] bytes = Base64.getDecoder().decode(String.join("", funcionario.getParam12()));
            try
            {
                imgFrente.setImage(SwingFXUtils.toFXImage(ImageIO.read(new ByteArrayInputStream(bytes)), null));
                Utils.centerImage(imgFrente);
            }
            catch (IOException ex)
            {
                Logger.getLogger(CadastroFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, "Erro ao carregar CNH\n" + ex.getMessage(), ButtonType.OK).showAndWait();
            }
        }
        
        if(funcionario.getParam14() != null)
        {
            byte[] bytes = Base64.getDecoder().decode(String.join("", funcionario.getParam13()));
            try
            {
                imgVerso.setImage(SwingFXUtils.toFXImage(ImageIO.read(new ByteArrayInputStream(bytes)), null));
                Utils.centerImage(imgVerso);
            }
            catch (IOException ex)
            {
                Logger.getLogger(CadastroFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, "Erro ao carregar CNH\n" + ex.getMessage(), ButtonType.OK).showAndWait();
            }
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
        tfCEP.setText(ender.getParam2());
        tfRua.setText(ender.getParam3());
        tfNumero.setText(ender.getParam4());
        tfBairro.setText(ender.getParam5());
        tfComplemento.setText(ender.getParam6());
        tfCidade.setText(ender.getParam7());
        cbEstado.getSelectionModel().select(ender.getParam8());
        
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
    }

    @FXML
    private void carregarEnter(MouseEvent event)
    {
    }

    @FXML
    private void cnhExit(MouseEvent event)
    {
    }

    @FXML
    private void cnhEnter(MouseEvent event)
    {
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
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
    {
    }

    @FXML
    private void addExit(MouseEvent event)
    {
    }

    @FXML
    private void addEnter(MouseEvent event)
    {
    }

    @FXML
    private void delExit(MouseEvent event)
    {
    }

    @FXML
    private void delEnter(MouseEvent event)
    {
    }

    @FXML
    private void confirmerExit(MouseEvent event)
    {
    }

    @FXML
    private void clickPesquisarCEP(ActionEvent event)
    {
    }

    @FXML
    private void clickAddTelefone(ActionEvent event)
    {
    }

    @FXML
    private void clickDelTelefone(ActionEvent event)
    {
    }
    
    private String validarCPF()
    {
        if(!Utils.validaCPF(tfCPF.getText().replace(".", "").replace("-", "")))
            return "CPF inválido";
        else if(funcionario == null && ctrFunc.cpfExists(tfCPF.getText()) > 0 || 
            funcionario != null && ctrFunc.cpfExists(tfCPF.getText()) != Integer.parseInt(funcionario.getParam1()))  
            return "CPF já cadastrado";
        else
            return "";
    }

    @FXML
    private void clickCarregar(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        List<File> file = fileChooser.showOpenMultipleDialog(null);
        if(file != null && !file.isEmpty())
        {
            frente = file.get(0).getAbsolutePath();
            verso = file.get(1).getAbsolutePath();
            
            imgFrente.setImage(new Image(file.get(0).toURI().toString()));
            imgVerso.setImage(new Image(file.get(1).toURI().toString()));
            
            Utils.centerImage(imgFrente);
            Utils.centerImage(imgVerso);
            
            sep1.setVisible(false); sep2.setVisible(false); sep3.setVisible(false);
            sep4.setVisible(false); sep5.setVisible(false); sep6.setVisible(false);
            sep7.setVisible(false); sep8.setVisible(false);
        }
        else
        {
            frente = "";
            verso = "";
            sep1.setVisible(true); sep2.setVisible(true); sep3.setVisible(true);
            sep4.setVisible(true); sep5.setVisible(true); sep6.setVisible(true);
            sep7.setVisible(true); sep8.setVisible(true);
        }
    }
}
