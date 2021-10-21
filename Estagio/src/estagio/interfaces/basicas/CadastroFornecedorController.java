/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrEndereco;
import estagio.controladores.ctrFornecedor;
import static estagio.interfaces.configuracao.TelaParametrizacaoController.bt;
import estagio.utilidades.Banco;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.TooltippedTableCell;
import estagio.utilidades.Utils;
import java.io.IOException;
import java.net.URL;
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
public class CadastroFornecedorController implements Initializable
{
    private int acao;
    private Objeto fornecedor;
    private JSONObject endereco_pesquisado;
    private final Tooltip tooltip = new Tooltip();
    private final ToggleGroup goup = new ToggleGroup();
    
    private final ctrFornecedor ctrForn = ctrFornecedor.instancia();
    private final ctrEndereco ctrEnder = ctrEndereco.instancia();

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneInfoBasica;
    @FXML
    private Pane paneEndereco;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbInfo;
    @FXML
    private Label lbTelefones;
    @FXML
    private Label lbEndereco;
    @FXML
    private Label lbPesquisa;
    @FXML
    private Label lbAlteracao;
    @FXML
    private Label lbErroNome;
    @FXML
    private Label lbErroCNPJ;
    @FXML
    private Label lbErroEMail;
    @FXML
    private Label lbErroTelefone;
    @FXML
    private Label lbErroCep;
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
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfCNPJ;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfTelefone;
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
    private JFXTextField tfNomePesquisa;
    @FXML
    private JFXTextField tfCnpjPesquisa;
    @FXML
    private JFXRadioButton rbNome;
    @FXML
    private JFXRadioButton rbCNPJ;
    @FXML
    private JFXRadioButton rbTodos;
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
    private JFXButton btDelTelefone;
    @FXML
    private JFXButton btAddTelefone;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private JFXButton btPesquisarCep;
    @FXML
    private TableView<Objeto> tvFornecedores;
    @FXML
    private TableColumn<Objeto,String> tcNome;
    @FXML
    private TableColumn<Objeto,String> tcCNPJ;
    @FXML
    private TableColumn<Objeto,String> tcEmail;
    @FXML
    private TableColumn<Objeto,String> tcEndereco;
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
    private VBox painelCentral;
    @FXML
    private JFXListView<String> lvTelefones;

    /**
     * Initializes the controller class.
     */
    private void inicializaLabels()
    {
        lbErroCNPJ.setText("");
        lbErroEMail.setText("");
        lbErroNome.setText("");
        lbErroTelefone.setText("");
        limpaLabelsEndereco();
    }
    
    private void limpaLabelsEndereco()
    {
        lbErroBairro.setText("");
        lbErroCep.setText("");
        lbErroCidade.setText("");
        lbErroNumero.setText("");
        lbErroRua.setText("");
        lbErroEstado.setText("");
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6,Boolean b7,
            Boolean b8)
    {
        if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            paneInfoBasica.setDisable(true);
            paneEndereco.setDisable(true);
            btNovo.setDisable(true);
            btAlterar.setDisable(true);
            btRemover.setDisable(true);
        }
        else
        {
            paneInfoBasica.setDisable(b1);
            paneEndereco.setDisable(b2);
            btNovo.setDisable(b4);
            btAlterar.setDisable(b6);
            btRemover.setDisable(b7);
        }
        panePesquisa.setDisable(b3);
        btConfirmar.setDisable(b5);
        btCancelar.setDisable(b8);
    }
    
    private void limparCampos()
    {
        tfBairro.clear();
        tfCEP.clear();
        tfCNPJ.clear();
        tfCidade.clear();
        tfEmail.clear();
        tfNome.clear();
        tfNumero.clear();
        tfRua.clear();
        lvTelefones.getItems().clear();
        tfTelefone.clear();
    }
    
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneInfoBasica);
        nodes.add(paneEndereco);
        nodes.add(panePesquisa);
        
        nodes.add(btAddTelefone);
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btDelTelefone);
        nodes.add(btNovo);
        nodes.add(btPesquisar);
        nodes.add(btPesquisarCep);
        nodes.add(btRemover);
        
        nodes.add(tfBairro);
        nodes.add(tfCEP);
        nodes.add(tfCNPJ);
        nodes.add(tfCidade);
        nodes.add(tfComplemento);
        nodes.add(tfCnpjPesquisa);
        nodes.add(tfEmail);
        nodes.add(tfNome);
        nodes.add(tfNomePesquisa);
        nodes.add(tfNumero);
        nodes.add(tfRua);
        nodes.add(tfTelefone);
        
        nodes.add(lbEndereco);
        nodes.add(lbInfo);
        nodes.add(lbPesquisa);
        nodes.add(lbTelefones);
        nodes.add(lbTitulo);
        nodes.add(lbAlteracao);
        
        nodes.add(rbCNPJ);
        nodes.add(rbTodos);
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
        btAddTelefone.setStyle(btAddTelefone.getStyle() + ";-fx-cursor: default;");
        btAlterar.setStyle(btAlterar.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: default;");
        btDelTelefone.setStyle(btDelTelefone.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
        btRemover.setStyle(btRemover.getStyle() + ";-fx-cursor: default;");
    }
    
    private void redimensiona()
    {
        ////////Tamanho da Tela
        panePrincipal.setPrefWidth(TelaPrincipalController.screenBounds.getMaxX() - 200);
        panePrincipal.setPrefHeight(TelaPrincipalController.screenBounds.getMaxY() - 80);
        
        //PAINEL CENTRAL TIRANDO TITULO E BOTÕES DE COMANDOS
        painelCentral.setPrefHeight(panePrincipal.getPrefHeight() - 47 - 45);
        //PAINEL DE PESQUISA FICA COM O RESTANTE DA TELA
        panePesquisa.setPrefHeight(painelCentral.getPrefHeight() - paneEndereco.getPrefHeight() -
                paneInfoBasica.getPrefHeight() - 20);
        
        tvFornecedores.setPrefHeight(panePesquisa.getPrefHeight() - 104);
        tvFornecedores.setPrefWidth(panePesquisa.getPrefWidth() - 82);
        
        //REORGANIZA OS TAMANHOS DAS COLUNAS DA TABELA
        int sobra = (int)((tvFornecedores.getPrefWidth() - 1045)/3);
        tcNome.setPrefWidth(tcNome.getPrefWidth() + sobra);
        tcEndereco.setPrefWidth(tcEndereco.getPrefWidth() + sobra);
        tcEmail.setPrefWidth(tcEmail.getPrefWidth() + sobra);
    }
    
    private void setListeners()
    {
        tfNome.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfNome.getText().trim().equals(""))
                    lbErroNome.setText("Campo requerido");
                else
                    lbErroNome.setText("");
            }
        });
        
        tfCNPJ.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue.length() < 18)
                lbErroCNPJ.setText("CNPJ incompleto");
            else if(Utils.isValidCPForCNPJ(tfCNPJ.getText()) == 2 && !ctrForn.exiteCNPJ(tfCNPJ.getText(),fornecedor))
                lbErroCNPJ.setText("");
            else if (Utils.isValidCPForCNPJ(tfCNPJ.getText()) != 2)
                lbErroCNPJ.setText("CNPJ inválido");
            else if(ctrForn.exiteCNPJ(tfCNPJ.getText(),fornecedor))
                lbErroCNPJ.setText("CNPJ já cadastrado");
        });
        
        tfEmail.focusedProperty().addListener((observable, oldValue, newValue) -> 
        {
            if(!newValue)
            {
                if(!tfEmail.getText().equals("") && !Utils.validaEmail(tfEmail.getText()))
                    lbErroEMail.setText("Formato de email inválido");
                else
                    lbErroEMail.setText("");
            }
        });
        
        tfEmail.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue != null && newValue.length() > 8)
            {
                if(!newValue.contains("@"))
                    lbErroEMail.setText("Email inválido, faltando @");
                else 
                {
                    if(!newValue.substring(newValue.indexOf("@") + 1).contains(".") ||
                        newValue.substring(newValue.indexOf("@") + 1).indexOf(".") < 2)
                        lbErroEMail.setText("Email com formato inválido");
                    else
                    {
                        if(newValue.substring(newValue.lastIndexOf(".") + 1).length() < 1)
                            lbErroEMail.setText("Email com formato inválido");
                        else
                            lbErroEMail.setText("");
                    }  
                }
            }
        });
        
        tfRua.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(fornecedor != null || endereco_pesquisado == null || endereco_pesquisado.getString("resultado").equals("0"))
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
            if(fornecedor != null || endereco_pesquisado == null || endereco_pesquisado.getString("resultado").equals("0"))
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
            if(fornecedor != null || endereco_pesquisado == null || endereco_pesquisado.getString("resultado").equals("0"))
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
    }
    
    private void inicializa()
    {
        setEstado(true, true, false, false, true, true, true, true);
        limparCampos();
        lbAlteracao.setText("Data da Última Alteração: ");
        
        acao = -1;
        fornecedor = null;
        endereco_pesquisado = null;
        clickPesquisar(new ActionEvent());
        inicializaLabels();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        redimensiona();
        cbEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", 
            "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO");
        
        tcNome.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcCNPJ.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcEndereco.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("param4"));
     
        rbCNPJ.setToggleGroup(goup);
        rbNome.setToggleGroup(goup);
        rbTodos.setToggleGroup(goup);
        rbTodos.setSelected(true);
        
        MaskFieldUtil.cepField(tfCEP);
        MaskFieldUtil.cnpjField(tfCNPJ);
        MaskFieldUtil.cnpjField(tfCnpjPesquisa);
        MaskFieldUtil.numericField(tfNumero);
        MaskFieldUtil.foneField(tfTelefone);
        
        inicializaDesign();
        inicializa();
        setListeners();
        
        tcEndereco.setCellFactory(TooltippedTableCell.forTableColumn());
        tcNome.setCellFactory(TooltippedTableCell.forTableColumn());
        tcEmail.setCellFactory(TooltippedTableCell.forTableColumn());
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        inicializa();
        acao = 0;
        setEstado(false, false, true, true, false, true, true, false);
        tfNome.requestFocus();
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(validaFornecedor())
        {
            Alert alerta;
            if(acao == 0 || acao == 2)
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cadastrar fornecedor " + tfNome.getText()
                    + "?", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrForn.salvar(tfNome,tfCNPJ,tfEmail,lvTelefones,tfCEP,tfRua,tfNumero,tfBairro,tfComplemento,
                        tfCidade,cbEstado))
                    {
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Fornecedor cadastrado com sucesso!!!", 
                                ButtonType.OK);
                        
                        if(acao == 2)
                        {
                            Stage stage = (Stage) btNovo.getScene().getWindow();
                            stage.close();
                        }
                        inicializa();
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro no cadastro do fornecedor\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK);
                    
                    alerta.showAndWait();
                }
            }
            else if(acao == 1 && fornecedor != null)
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar fornecedor " + fornecedor.getParam2()
                    + "?", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrForn.alterar(Integer.parseInt(fornecedor.getParam1()),tfNome,tfCNPJ,tfEmail,lvTelefones,tfCEP,
                        tfRua,tfNumero,tfBairro,tfComplemento,tfCidade,cbEstado))
                    {
                        inicializa();
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Fornecedor alterado com sucesso!!!", 
                                ButtonType.OK);
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro na alteração do fornecedor\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK);
                    
                    alerta.showAndWait();
                }
            }
        }
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        if(!tvFornecedores.getItems().isEmpty() && tvFornecedores.getSelectionModel().getSelectedIndex() >= 0)
        {
            fornecedor = tvFornecedores.getSelectionModel().getSelectedItem();
            acao = 1;
            setEstado(false, false, true, true, false, true, true, false);
        }
    }
    
    private boolean validaFornecedor()
    {
        String erros = "";
        if(tfNome.getText().equals(""))
        {
            erros += "Digite o nome do Fornecedor\n";
            lbErroNome.setText("Campo requerido");
        }
        
        if(tfCNPJ.getText().equals(""))
        {
            erros += "Digite o CNPJ do Fornecedor\n";
            lbErroCNPJ.setText("Campo Requerido");
        }
        else
        {
            if(tfCNPJ.getText().length() < 18)
            {
                erros += "CNPJ incompleto\n";
                lbErroCNPJ.setText("CNPJ incompleto");
            }
            else
            {
                if(Utils.isValidCPForCNPJ(tfCNPJ.getText()) != 2)
                {
                    erros += "Digite um CNPJ válido\n";
                    lbErroCNPJ.setText("CNPJ inválido");
                }

                if(ctrForn.exiteCNPJ(tfCNPJ.getText(),fornecedor))
                {
                    erros += "CNPJ já cadastrado\n";
                    lbErroCNPJ.setText("CNPJ já cadastrado");
                }
            }
        }
        
        if(!tfCEP.getText().equals("") && tfCEP.getText().length() < 9)
        {
            erros += "Digite um CEP válido\n";
            lbErroCep.setText("Digite um CEP válido");
        }
        
        if(tfRua.getText().equals(""))
        {
            erros += "Digite a rua do Fornecedor\n";
            lbErroRua.setText("Campo requerido");
        }
        
        if(tfBairro.getText().equals(""))
        {
            erros += "Digite o bairro do Fornecedor\n";
            lbErroBairro.setText("Campo requerido");
        }
        
        if(tfNumero.getText().equals(""))
        {
            erros += "Digite o número do Fornecedor\n";
            lbErroNumero.setText("Campo requerido");
        }
        
        if(tfCidade.getText().equals(""))
        {
            erros += "Digite a cidade do Fornecedor\n";
            lbErroCidade.setText("Campo requerido");
        }
        
        if(!tfEmail.getText().equals("") && !Utils.validaEmail(tfEmail.getText()))
        {
            erros += "Digite um email válido\n";
            lbErroEMail.setText("Campo requerido");
        }
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        
        return erros.trim().equals("");
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        if(!tvFornecedores.getItems().isEmpty() && tvFornecedores.getSelectionModel().getSelectedIndex() >= 0)
        {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover fornecedor " + tvFornecedores
                .getSelectionModel().getSelectedItem().getParam2(), ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();
            
            if(alerta.getResult() == ButtonType.YES)
            {
                if(ctrForn.inativar(Integer.parseInt(tvFornecedores.getSelectionModel().getSelectedItem().getParam1())))
                {
                    inicializa();
                    alerta = new Alert(Alert.AlertType.INFORMATION, "Fornecedor removido com sucesso!!", ButtonType.OK);
                }
                else
                    alerta = new Alert(Alert.AlertType.ERROR, "Erro na remoção do fornecedor\n" +
                        Banco.getCon().getMensagemErro(), ButtonType.OK);
                
                alerta.showAndWait();
            }
        }
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
    private void clickPesquisar(ActionEvent event)
    {
        if(rbTodos.isSelected())
            tvFornecedores.setItems(FXCollections.observableArrayList(ctrForn.getAll()));
        else if(rbNome.isSelected())
            tvFornecedores.setItems(FXCollections.observableArrayList(ctrForn.getByName(tfNomePesquisa.getText())));
        else if(rbCNPJ.isSelected())
            tvFornecedores.setItems(FXCollections.observableArrayList(ctrForn.getByCNPJ(tfCnpjPesquisa.getText())));
    }

    @FXML
    private void clickDel(ActionEvent event)
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
    private void clickAdd(ActionEvent event)
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
    private void selecionaFornecedor(MouseEvent event)
    {
        if(!tvFornecedores.getItems().isEmpty() && tvFornecedores.getSelectionModel().getFocusedIndex() >= 0)
        {
            setEstado(true, true, false, true, true, false, false, false);
            fillFields(tvFornecedores.getSelectionModel().getSelectedItem());
        }
        else
            fornecedor = null;
    }

    private void fillFields(Objeto fornecedor)
    {
        limparCampos();
        this.fornecedor = fornecedor;
        tfNome.setText(fornecedor.getParam2());
        tfCNPJ.setText(fornecedor.getParam3());
        tfEmail.setText(fornecedor.getParam4());
        lbAlteracao.setText("Data da Última Alteração: " + fornecedor.getParam5());
        
        Objeto ender = ctrEnder.getByCodigo(Integer.parseInt(fornecedor.getParam6()));
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
        for(String telefone : ctrForn.getTelefones(Integer.parseInt(fornecedor.getParam1())))
        {
            tfTelefone.setText(telefone);
            lvTelefones.getItems().add(tfTelefone.getText());
        }
        tfTelefone.clear();
        
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
    private void clickAlteracao(MouseEvent event)
    {
        if(lbAlteracao.getText().substring(lbAlteracao.getText().indexOf(":") + 1).trim().length() > 0)
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

    @FXML
    private void novoExit(MouseEvent event)
    {
        btNovo.setStyle(btNovo.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
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
        btConfirmar.setStyle(btConfirmar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
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
        btAlterar.setStyle(btAlterar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void alterarEnter(MouseEvent event)
    {
        btAlterar.setStyle(btAlterar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Remover Fornecedor");
        ToolTip.bindTooltip(btAlterar, tooltip);
    }
    
    @FXML
    private void removerExit(MouseEvent event)
    {
        btRemover.setStyle(btRemover.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void removerEnter(MouseEvent event)
    {
        btRemover.setStyle(btRemover.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Remover Fornecedor");
        ToolTip.bindTooltip(btRemover, tooltip);
    }

    @FXML
    private void cancelarExit(MouseEvent event)
    {
        btCancelar.setStyle(btCancelar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void cancelarEnter(MouseEvent event)
    {
        btCancelar.setStyle(btCancelar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Cancelar Ação");
        ToolTip.bindTooltip(btCancelar, tooltip);
    }

    @FXML
    private void delExit(MouseEvent event)
    {
        btDelTelefone.setStyle(btDelTelefone.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void delEnter(MouseEvent event)
    {
        btDelTelefone.setStyle(btDelTelefone.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void addExit(MouseEvent event)
    {
        btAddTelefone.setStyle(btAddTelefone.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void addEnter(MouseEvent event)
    {
        btAddTelefone.setStyle(btAddTelefone.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void cepExit(MouseEvent event)
    {
        btPesquisarCep.setStyle(btPesquisarCep.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void cepEnter(MouseEvent event)
    {
        btPesquisarCep.setStyle(btPesquisarCep.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Pesquisar CEP");
        ToolTip.bindTooltip(btCancelar, tooltip);
    }

    @FXML
    private void pesquisarExit(MouseEvent event)
    {
        btPesquisar.setStyle(btPesquisar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
    {
        btPesquisar.setStyle(btPesquisar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Pesquisar Fornecedor");
        ToolTip.bindTooltip(btPesquisar, tooltip);
    }

    @FXML
    private void alteracaoExit(MouseEvent event)
    {
        lbAlteracao.setStyle(lbAlteracao.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void alteracaoEnter(MouseEvent event)
    {
        lbAlteracao.setStyle(lbAlteracao.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        if(lbAlteracao.getText().substring(lbAlteracao.getText().indexOf(":") + 1).trim().length() > 0)
        {
            tooltip.setText("Pesquisar Fornecedor");
            ToolTip.bindTooltip(lbAlteracao, tooltip);
        }
    }

    @FXML
    private void mainPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickConfirmar(new ActionEvent());
    }

    @FXML
    private void fonePressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickAdd(new ActionEvent());
    }

    @FXML
    private void pesquisaPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickPesquisar(new ActionEvent());
    }

    @FXML
    private void cepPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickPesquisarCEP(new ActionEvent());
    }

    public void setForn(String forn)
    {
        clickNovo(new ActionEvent());
        acao = 2;
        tfNome.setText(forn);
        tfCNPJ.requestFocus();
    }
}
