/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrFuncionario;
import estagio.controladores.ctrUsuario;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastroUsuarioController implements Initializable
{
    private int acao;
    private ArrayList<String>funcionarios = new ArrayList<>();
    
    private ctrFuncionario ctrFunc = ctrFuncionario.instancia();
    private ctrUsuario ctrUsu = ctrUsuario.instancia();

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

    /**
     * Initializes the controller class.
     */
    private void inicializaLabels()
    {
        lbErroConfirmarSenha.setText("");
        lbErroFuncionario.setText("");
        lbErroNivel.setText("");
        lbErroSenha.setText("");
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6,Boolean b7)
    {
        paneInfo.setDisable(b1);
        panePesquisa.setDisable(b2);
        btNovo.setDisable(b3);
        btConfirmar.setDisable(b4);
        btAlterar.setDisable(b5);
        btRemover.setDisable(b6);
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
        
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btNovo);
        nodes.add(btPesquisar);
        nodes.add(btRemover);
        
        nodes.add(rbFuncionario);
        nodes.add(rbNivel);
        nodes.add(rbUsuario);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faSearch);
        nodes.add(faTrash);
        
        nodes.add(cbNivel);
        nodes.add(cbNivelPesquisa);
        
        Utils.setDesign(1, nodes);
        faSearch.setSize("18");
    }
    
    private void inicializa()
    {
        cbNivel.setDisable(false);
        limparCampos();
        inicializaLabels();
        setEstado(true, false, false, true, true, true, true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbNivel.getItems().addAll("alto","medio","baixo");
        inicializa();
        
        carregaDesign();
        
        funcionarios = ctrFunc.getAllNames("");
        
        JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
        autoCompletePopup.getSuggestions().add("Novo Funcionário");
        autoCompletePopup.getSuggestions().addAll("");

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
        
        tfFuncionario.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(funcionarios.contains(newValue))
            {
                if(acao == 0)
                {
                    Alert a = new Alert(Alert.AlertType.WARNING, "Funcionário já possui usuário cadastrado\n"
                        + "Deseja selecionar funcionário para alteração/remoção?", ButtonType.YES,ButtonType.NO);
                    a.showAndWait();
                    
                    if(a.getResult() == ButtonType.YES)
                    {
                        rbFuncionario.setSelected(true);
                        tfFuncionarioPesquisa.setVisible(true);
                        tfFuncionarioPesquisa.setText(tfFuncionario.getText());
                        clickPesquisar(new ActionEvent());
                    }
                    else
                        lbErroFuncionario.setText("Funcionário já possui usuário cadastrado");
                }
            }
        });
        
        tfFuncionario.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(acao == 0)
                    lbErroFuncionario.setText("Funcionário já possui usuário cadastrado");
                else
                    lbErroFuncionario.setText("");
            }
            else
                lbErroFuncionario.setText("");
        });
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
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
    private void clickPesquisar(ActionEvent event)
    {
    }

    @FXML
    private void selecionaUsuario(MouseEvent event)
    {
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

    void setFuncionario(String nome,String nivel)
    {
        tfFuncionario.setText(nome);
        tfFuncionario.setDisable(true);
        cbNivel.getSelectionModel().select(nivel);
        cbNivel.setDisable(true);
        acao = 2;
    }
    
}
