/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrAcesso;
import estagio.controladores.ctrFuncionario;
import estagio.controladores.ctrUsuario;
import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.Utils;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastroUsuarioController implements Initializable
{
    private int acao;
    private Objeto usuario; 
    private ArrayList<String>funcionarios = new ArrayList<>();
    JFXAutoCompletePopup<String> autoCompletePopup;
    
    private final ctrFuncionario ctrFunc = ctrFuncionario.instancia();
    private final ctrUsuario ctrUsu = ctrUsuario.instancia();
    private final ctrAcesso ctrAcc = ctrAcesso.instancia();
    
    private final Tooltip tooltip = new Tooltip();
    private final ToggleGroup goup = new ToggleGroup();

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneInfo;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbInfo;
    @FXML
    private Label lbPesquisa;
    @FXML
    private Label lbErroFuncionario;
    @FXML
    private Label lbErroUsuario;
    @FXML
    private Label lbErroNivel;
    @FXML
    private Label lbErroSenha;
    @FXML
    private Label lbErroConfirmarSenha;
    @FXML
    private JFXTextField tfUsuario;
    @FXML
    private JFXComboBox<String> cbNivel;
    @FXML
    private JFXTextField tfFuncionario;
    @FXML
    private JFXPasswordField tfSenha;
    @FXML
    private JFXPasswordField tfConfirmarSenha;
    @FXML
    private JFXTextField tfFuncionarioPesquisa;
    @FXML
    private JFXTextField tfUsuarioPesquisa;
    @FXML
    private JFXComboBox<String> cbNivelPesquisa;
    @FXML
    private JFXRadioButton rbFuncionario;
    @FXML
    private JFXRadioButton rbUsuario;
    @FXML
    private JFXRadioButton rbNivel;
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
    private JFXButton btPesquisar;
    @FXML
    private TableView<Objeto> tvUsuarios;
    @FXML
    private TableColumn<Objeto,String> tcFuncionario;
    @FXML
    private TableColumn<Objeto,String> tcUsuario;
    @FXML
    private TableColumn<Objeto,String> tcNivel;
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
    private FontAwesomeIconView faSearch;
    @FXML
    private JFXRadioButton rbTodos;
    @FXML
    private JFXRadioButton rbAcessos;
    @FXML
    private Label lbUsuarios;
    @FXML
    private Label lbAcessos;
    @FXML
    private TableView<Objeto> tvAcessos;
    @FXML
    private TableColumn<Objeto,String> tcLogin;
    @FXML
    private TableColumn<Objeto,String> tcLogout;
    @FXML
    private JFXDatePicker dpAcesso;

    /**
     * Initializes the controller class.
     */
    private void inicializaLabels()
    {
        lbErroConfirmarSenha.setText("");
        lbErroUsuario.setText("");
        lbErroFuncionario.setText("");
        lbErroNivel.setText("");
        lbErroSenha.setText("");
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6,Boolean b7)
    {
        ///PERMISSÃO MAIOR QUE BAIXO OU FOR O PRÓPRIO USUÁRIO ALTERANDO SEUS DADOS
        if(tvUsuarios.getSelectionModel().getSelectedIndex() >= 0 && 
                TelaPrincipalController.usuario_logado.getParam3().equals(
                    tvUsuarios.getSelectionModel().getSelectedItem().getParam2()) || 
                !TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            paneInfo.setDisable(b1);
            tfFuncionario.setDisable(b1);
            btNovo.setDisable(b3);
            btAlterar.setDisable(b5);
            btRemover.setDisable(b6);
        }
        else
        {
            paneInfo.setDisable(true);
            tfFuncionario.setDisable(true);
            btNovo.setDisable(true);
            btAlterar.setDisable(true);
            btRemover.setDisable(true);
        }
       
        panePesquisa.setDisable(b2);
        
        btConfirmar.setDisable(b4);
        
        btCancelar.setDisable(b7);
    }
    
    private void limparCampos()
    {
        tfConfirmarSenha.clear();
        tfFuncionario.clear();
        tfSenha.clear();
        tfUsuario.clear();
        cbNivel.getSelectionModel().select(-1);
    }
    
    private void carregaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneInfo);
        nodes.add(panePesquisa);
        
        nodes.add(tfConfirmarSenha);
        nodes.add(tfFuncionario);
        nodes.add(tfFuncionarioPesquisa);
        nodes.add(tfSenha);
        nodes.add(tfUsuario);
        nodes.add(tfUsuarioPesquisa);
        
        nodes.add(lbInfo);
        nodes.add(lbTitulo);
        nodes.add(lbPesquisa);
        nodes.add(lbUsuarios);
        nodes.add(lbAcessos);
        
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btNovo);
        nodes.add(btPesquisar);
        nodes.add(btRemover);
        
        nodes.add(rbFuncionario);
        nodes.add(rbNivel);
        nodes.add(rbUsuario);
        nodes.add(rbAcessos);
        nodes.add(rbTodos);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faSearch);
        nodes.add(faTrash);
        
        nodes.add(cbNivel);
        nodes.add(cbNivelPesquisa);
        
        Utils.setDesign(1, nodes);
        lbErroConfirmarSenha.setStyle(lbErroConfirmarSenha.getStyle() + ";-fx-text-fill: red;");
        lbErroFuncionario.setStyle(lbErroFuncionario.getStyle() + ";-fx-text-fill: red;");
        lbErroNivel.setStyle(lbErroNivel.getStyle() + ";-fx-text-fill: red;");
        lbErroSenha.setStyle(lbErroSenha.getStyle() + ";-fx-text-fill: red;");
        faSearch.setSize("18");
    }
    
    private void setListeners()
    {
        autoCompletePopup = new JFXAutoCompletePopup<>();
        autoCompletePopup.getSuggestions().add("Novo Funcionário");
        autoCompletePopup.getSuggestions().addAll(funcionarios);

        autoCompletePopup.setSelectionHandler(event ->
        {
            tfFuncionario.setText(event.getObject());

            // you can do other actions here when text completed
        });

        // filtering options
        tfFuncionario.textProperty().addListener(observable ->
        {
            autoCompletePopup.filter(string -> string.toLowerCase().contains(tfFuncionario.getText().toLowerCase()));
            if (autoCompletePopup.getFilteredSuggestions().isEmpty() || tfFuncionario.getText().isEmpty())
            {
                autoCompletePopup.hide();
                // if you remove textField.getText.isEmpty() when text field is empty it suggests all options
                // so you can choose
            }
            else
            {
                autoCompletePopup.show(tfFuncionario);
            }
        });
        
        tfFuncionario.focusedProperty().addListener((ov, oldV, newV) ->
        {
            if(newV)
                autoCompletePopup.show(tfFuncionario);
            else
                autoCompletePopup.hide();
        });
        
        tfFuncionario.setOnMouseClicked((event) ->
        {
            if(!autoCompletePopup.isShowing())
                autoCompletePopup.show(tfFuncionario);
        });
        
        tfFuncionario.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(tfFuncionario.getText().equals("Novo Funcionário"))
            {
                 Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Cadastrar novo Funcionário?", 
                    ButtonType.YES,ButtonType.NO);
                a.showAndWait();
                
                if(a.getResult() == ButtonType.YES)
                    abrirCadastroFuncionario();
                else
                    tfFuncionario.clear();
            }
        });
        
        tfFuncionario.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(!tfFuncionario.getText().trim().equals("") && !funcionarios.contains(tfFuncionario.getText())
                        && !tfFuncionario.getText().equals("Novo Funcionário"))
                {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Funcionário não cadastrado, deseja "
                         + "cadastrar?", ButtonType.YES,ButtonType.NO);
                    a.showAndWait();
                    
                    if(a.getResult() == ButtonType.YES)
                    {
                        abrirCadastroFuncionario(tfFuncionario.getText());
                    }
                    else
                        tfFuncionario.clear();
                }
                else
                {
                    if(ctrUsu.getUsuarioByFuncionario(tfFuncionario.getText()) != null)
                    {
                        if(acao == 0)
                            lbErroFuncionario.setText("Funcionário já possui usuário cadastrado");
                    }
                    else
                        lbErroFuncionario.setText("");
                }
            }
            else
                lbErroFuncionario.setText("");
        });
    }
    
    private void inicializa()
    {
        dpAcesso.getEditor().clear();
        cbNivel.setDisable(false);
        limparCampos();
        inicializaLabels();
        setEstado(true, false, false, true, true, true, true);
        acao = -1;
        usuario = null;
        funcionarios = ctrFunc.getAllNames("");
        clickPesquisar(new ActionEvent());
        tfFuncionario.setDisable(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbNivel.getItems().addAll("alto","medio","baixo");
        cbNivelPesquisa.getItems().addAll("alto","medio","baixo");
        rbFuncionario.setToggleGroup(goup);
        rbNivel.setToggleGroup(goup);
        rbTodos.setToggleGroup(goup);
        rbUsuario.setToggleGroup(goup);
        rbAcessos.setToggleGroup(goup);
        rbTodos.setSelected(true);
        
        tcFuncionario.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcNivel.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcUsuario.setCellValueFactory(new PropertyValueFactory<>("param2"));
        
        tcLogin.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcLogout.setCellValueFactory(new PropertyValueFactory<>("param3"));
        
        inicializa();
        
        carregaDesign();
        
        setListeners();
        
        if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
            Notifications.create()
                .darkStyle()
                //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                .hideAfter(Duration.seconds(4)).position(Pos.BOTTOM_CENTER)
                .text("Usuário logado não possui permissão de alterar dados dos usuário além do seu próprio")
                .showWarning();
        
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        inicializa();
        acao = 0;
        setEstado(false, true, true, false, true, true, false);
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(validaUsuario())
        {
            Alert alerta = null;
            if(acao == 0 || acao == 2)
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cadastrar usuário " + tfUsuario.getText() + 
                    "?", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrUsu.salvar(tfFuncionario, tfUsuario, tfSenha, cbNivel))
                    {
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Usuário cadastrado com sucesso!!!", 
                                ButtonType.YES);
                        if(acao == 0)
                            inicializa();
                        else
                        {
                            Stage stage = (Stage) btConfirmar.getScene().getWindow();
                            stage.close();
                        }
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro no cadastro do usuário\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK);
                }
            }
            else if(acao == 1 && usuario != null)
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar usuário " + tfUsuario.getText() + 
                    "?", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrUsu.alterar(Integer.parseInt(usuario.getParam1()), tfFuncionario, tfUsuario, tfSenha, cbNivel))
                    {
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Usuário alterado com sucesso!!!", 
                                ButtonType.YES);
                        inicializa();
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro na alteração do usuário\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK);
                }
            }
            alerta.showAndWait();
        }
    }

    private boolean validaUsuario()
    {
        String erros = "";
        inicializaLabels();
        
        if(tfUsuario.getText().equals(""))
        {
            lbErroUsuario.setText("Campo requerido");
            erros += "Usuário inválido\n";
        }
        
        if(tfSenha.getText().equals(""))
        {
            lbErroSenha.setText("Campo requerido");
            erros += "Senha inválido\n";
        }
        
        if(tfConfirmarSenha.getText().equals(""))
        {
            lbErroConfirmarSenha.setText("Repita a senha");
            erros += "Repita a senha\n";
        }
        
        if(!tfSenha.getText().equals(tfConfirmarSenha.getText()))
        {
            lbErroConfirmarSenha.setText("Senha incorreta");
            erros += "Repita a senha corretamente\n";
        }
        
        if(cbNivel.getSelectionModel().getSelectedIndex() < 0)
        {
            lbErroNivel.setText("Selecione o nível");
            erros += "Selecione o nível de acesso do usuário\n";
        }
        
        if(tfFuncionario.getText().trim().equals(""))
        {
            lbErroFuncionario.setText("Campo requerido");
            erros += "Digite o nome do Funcionário\n";
        }
        else if(!funcionarios.contains(tfFuncionario.getText()))
        {
            lbErroFuncionario.setText("Funcionário não cadastrado");
            erros += "Funcionário não cadastrado\n";
        }
        else
        {
            if(acao == 0)
            {
                if(ctrUsu.getUsuarioByFuncionario(tfFuncionario.getText()) != null)
                {
                    lbErroFuncionario.setText("Funcionário já possui usuário cadastrado");
                    erros += "Funcionário já possui usuário cadastrado\n";
                }
            }
        }
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        
        return erros.trim().equals("");
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        if(!tvUsuarios.getItems().isEmpty() && tvUsuarios.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(TelaPrincipalController.usuario_logado.getParam5().equals("alto") || 
                TelaPrincipalController.usuario_logado.getParam3().equals(
                    tvUsuarios.getSelectionModel().getSelectedItem().getParam2()))
            {
                setEstado(false, true, true, false, true, true, false);
                acao = 1;
                usuario = tvUsuarios.getSelectionModel().getSelectedItem();
                tfFuncionario.setDisable(true);
            }
            else if(!TelaPrincipalController.usuario_logado.getParam2().equals(
                    tvUsuarios.getSelectionModel().getSelectedItem().getParam2()))
                new Alert(Alert.AlertType.ERROR, "Usuário não possui permissão para alterar este usuário", ButtonType.OK)
                    .showAndWait();
        }
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        if(!tvUsuarios.getItems().isEmpty() && tvUsuarios.getSelectionModel().getSelectedIndex() >= 0)
        {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover usuário " + tfUsuario.getText() + "?", 
                ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();

            if(alerta.getResult() == ButtonType.YES)
            {
                usuario = tvUsuarios.getSelectionModel().getSelectedItem();
                if(ctrUsu.inativar(Integer.parseInt(usuario.getParam1())))
                {
                    new Alert(Alert.AlertType.INFORMATION, "Usuário removido com sucesso!!!", ButtonType.OK).showAndWait();
                    inicializa();
                }
                else
                    new Alert(Alert.AlertType.ERROR, "Erro na remoção do usário\n" + Banco.getCon().getMensagemErro(),
                            ButtonType.OK).showAndWait();
            }
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        inicializa();
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        tvUsuarios.getItems().clear();
        if(rbTodos.isSelected())
            tvUsuarios.setItems(FXCollections.observableArrayList(ctrUsu.getAll()));
        else if(rbFuncionario.isSelected())
            tvUsuarios.setItems(FXCollections.observableArrayList(ctrUsu.getByFuncionario(tfFuncionarioPesquisa.getText())));
        else if(rbUsuario.isSelected())
            tvUsuarios.setItems(FXCollections.observableArrayList(ctrUsu.getByName(tfUsuarioPesquisa.getText())));
        else if(rbNivel.isSelected())
            tvUsuarios.setItems(FXCollections.observableArrayList(ctrUsu.getByNivel(cbNivelPesquisa.getSelectionModel().getSelectedItem())));
        else
        {
            if(!dpAcesso.getEditor().getText().equals("") && LocalDate.now().compareTo(dpAcesso.getValue()) >= 0)
            {
                String data = dpAcesso.getValue().toString();
                tvAcessos.setItems(FXCollections.observableArrayList(ctrAcc.get(data)));
            }
            else
            {
                Notifications.create()
                    .darkStyle()
                    //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                    .hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_CENTER)
                    .text("Selecione uma data válida")
                    .showError();
                tvAcessos.getItems().clear();
                tvUsuarios.getItems().clear();
            }
        }
    }

    @FXML
    private void selecionaUsuario(MouseEvent event)
    {
        if(!tvUsuarios.getItems().isEmpty() && tvUsuarios.getSelectionModel().getFocusedIndex() >= 0)
        {
            fillFields(tvUsuarios.getSelectionModel().getSelectedItem());
            setEstado(true, false, false, true, false, false, false);
            int cod = Integer.parseInt(tvUsuarios.getSelectionModel().getSelectedItem().getParam1());
            tvAcessos.setItems(FXCollections.observableArrayList(ctrAcc.getAcessosUsuario(cod)));
        }
    }

    @FXML
    private void selecionaAcesso(MouseEvent event)
    {
        if(!tvAcessos.getItems().isEmpty() && tvAcessos.getSelectionModel().getFocusedIndex() >= 0 && rbAcessos.isSelected())
        {
            int cod = Integer.parseInt(tvAcessos.getSelectionModel().getSelectedItem().getParam4());
            tvUsuarios.setItems(FXCollections.observableArrayList(ctrUsu.get(cod)));
            
            if(event.getClickCount() == 2)
            {
                usuario = tvUsuarios.getItems().get(0);
                fillFields(usuario);
                setEstado(true, false, false, true, false, false, false);
            }
        }
    }

    private void fillFields(Objeto user)
    {
        if(user != null)
        {
            tfUsuario.setText(user.getParam2());
            cbNivel.getSelectionModel().select(user.getParam4());
            tfFuncionario.setText(ctrFunc.getNameByCode(Integer.parseInt(user.getParam6())));
            autoCompletePopup.hide();
        }
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
        tooltip.setText("Alterar Usuário");
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
        tooltip.setText("Remover Usuário");
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
    private void pesquisarExit(MouseEvent event)
    {
        btPesquisar.setStyle("-fx-cursor: default;" + btPesquisar.getStyle());
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
    {
        btPesquisar.setStyle("-fx-cursor: hand;" + btPesquisar.getStyle());
        tooltip.setText("Buscar Usuário");
        ToolTip.bindTooltip(btPesquisar, tooltip);
    }

    void setFuncionario(String nome,String nivel)
    {
        tfFuncionario.setText(nome);
        tfFuncionario.setDisable(true);
        cbNivel.getSelectionModel().select(nivel);
        cbNivel.setDisable(true);
        acao = 2;
    }

    @FXML
    private void clickTodos(ActionEvent event)
    {
        clickPesquisar(new ActionEvent());
    }

    @FXML
    private void clickFuncionarioRadio(ActionEvent event)
    {
        tfFuncionarioPesquisa.setVisible(true);
        tfFuncionarioPesquisa.clear();
        tfUsuarioPesquisa.setVisible(false);
        cbNivelPesquisa.setVisible(false);
        dpAcesso.setVisible(false);
    }

    @FXML
    private void clickUsuarioRadio(ActionEvent event)
    {
        tfFuncionarioPesquisa.setVisible(false);
        tfUsuarioPesquisa.clear();
        tfUsuarioPesquisa.setVisible(true);
        cbNivelPesquisa.setVisible(false);
        dpAcesso.setVisible(false);
    }

    @FXML
    private void clickNivelRadio(ActionEvent event)
    {
        tfFuncionarioPesquisa.setVisible(false);
        tfUsuarioPesquisa.setVisible(false);
        cbNivelPesquisa.setVisible(true);
        dpAcesso.setVisible(false);
    }

    @FXML
    private void clickAcesso(ActionEvent event)
    {
        tfFuncionarioPesquisa.setVisible(false);
        tfUsuarioPesquisa.setVisible(false);
        cbNivelPesquisa.setVisible(false);
        dpAcesso.setVisible(true);
    }

    private void abrirCadastroFuncionario(String... nome)
    {
        Stage stage = (Stage) btAlterar.getScene().getWindow();
        stage.close();

        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/estagio/TelaPrincipal.fxml"));
            Parent root = (Parent) loader.load();
            // as soon as the load() method has been invoked, the scene graph and the 
            // controller instance are availlable:
            TelaPrincipalController controller = loader.getController();
            if(nome.length == 1)
                controller.abrirTela("/estagio/interfaces/basicas/CadastroFuncionario.fxml",nome[0]);
            else
                controller.abrirTela("/estagio/interfaces/basicas/CadastroFuncionario.fxml");
        }
        catch (IOException ex)
        {
            Logger.getLogger(CadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void pesquisarData(String data)
    {
        dpAcesso.getEditor().setText(data);
        dpAcesso.setValue(LocalDate.parse(data.trim().substring(0, data.indexOf(" "))));
        rbAcessos.setSelected(true);
        tvAcessos.getItems().clear();
        tvUsuarios.getItems().clear();
        clickAcesso(new ActionEvent());
        tvAcessos.setItems(FXCollections.observableArrayList(ctrAcc.getDataCompleta(data)));
    }
}
