/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrAcesso;
import estagio.controladores.ctrCliente;
import estagio.controladores.ctrFornecedor;
import estagio.controladores.ctrFuncionario;
import estagio.controladores.ctrUsuario;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.TooltippedTableCell;
import estagio.utilidades.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastrosInativosController implements Initializable
{
    private final ToggleGroup group_cliente = new ToggleGroup();
    private final ToggleGroup group_forn = new ToggleGroup();
    private final ToggleGroup group_func = new ToggleGroup();
    private final ToggleGroup group_usr = new ToggleGroup();
    private final Tooltip tooltip = new Tooltip();
    
    private final ctrCliente ctrCli = ctrCliente.instancia();
    private final ctrFornecedor ctrForn = ctrFornecedor.instancia();
    private final ctrFuncionario ctrFunc = ctrFuncionario.instancia();
    private final ctrUsuario ctrUsr = ctrUsuario.instancia();
    private final ctrAcesso ctrAce = ctrAcesso.instancia();

    @FXML
    private JFXButton btRestaurar;
    @FXML
    private JFXButton btApagar;
    @FXML
    private FontAwesomeIconView faRestore;
    @FXML
    private FontAwesomeIconView faTrash;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Label lbTitulo;
    
    @FXML
    private Tab tabClientes;
    @FXML
    private JFXTextField tfNomeCliente;
    @FXML
    private JFXTextField tfCpfCliente;
    @FXML
    private JFXButton btPesquisarCliente;
    @FXML
    private JFXRadioButton rbTodosClientes;
    @FXML
    private JFXRadioButton rbNomeCliente;
    @FXML
    private JFXRadioButton rbCpfCliente;
    @FXML
    private Label lbClientes;
    @FXML
    private TableView<Objeto> tvClientes;
    @FXML
    private TableColumn<Objeto, String> tcNomeCliente;
    @FXML
    private TableColumn<Objeto, String> tcCpfCliente;
    @FXML
    private TableColumn<Objeto, String> tcRgCliente;
    @FXML
    private TableColumn<Objeto, String> tcEmailCliente;
    @FXML
    private TableColumn<Objeto, String> tcEnderecoCliente;
    @FXML
    private BorderPane panePrincipal;
    
    @FXML
    private Tab tabFuncionarios;
    @FXML
    private JFXTextField tfNomeFuncionario;
    @FXML
    private JFXTextField tfCpfFuncionario;
    @FXML
    private JFXButton btPesquisarFuncionario;
    @FXML
    private JFXRadioButton rbTodosFuncionarios;
    @FXML
    private JFXRadioButton rbNomeFuncionario;
    @FXML
    private JFXRadioButton rbCpfFuncionario;
    @FXML
    private Label lbFuncionarios;
    @FXML
    private TableView<Objeto> tvFuncionarios;
    @FXML
    private TableColumn<Objeto, String> tcNomeFuncionario;
    @FXML
    private TableColumn<Objeto, String> tcCpfFuncionario;
    @FXML
    private TableColumn<Objeto, String> tcRgFuncionario;
    @FXML
    private TableColumn<Objeto, String> tcEmailFuncionario;
    @FXML
    private TableColumn<Objeto, String> tcEnderecoFuncionario;
    
    @FXML
    private Tab tabUsuarios;
    @FXML
    private JFXTextField tfNomeUsuario;
    @FXML
    private JFXComboBox<String> cbNivel;
    @FXML
    private JFXButton btPesquisarUsuario;
    @FXML
    private JFXRadioButton rbTodosUsuarios;
    @FXML
    private JFXRadioButton rbNomeUsuario;
    @FXML
    private JFXRadioButton rbNivel;
    @FXML
    private Label lbUsuarios;
    @FXML
    private TableView<Objeto> tvUsuarios;
    @FXML
    private TableColumn<Objeto, String> tcFuncionario;
    @FXML
    private TableColumn<Objeto, String> tcUsuario;
    @FXML
    private TableColumn<Objeto, String> tcNivel;
    
    @FXML
    private Tab tabFornecedores;
    @FXML
    private JFXTextField tfNomeFornecedor;
    @FXML
    private JFXButton btPesquisarFornecedor;
    @FXML
    private JFXRadioButton rbTodosFornecedores;
    @FXML
    private JFXRadioButton rbNomeFornecedor;
    @FXML
    private JFXRadioButton rbCNPJ;
    @FXML
    private JFXTextField tfCNPJ;
    @FXML
    private Label lbFornecedores;
    @FXML
    private TableView<Objeto> tvFornecedor;
    @FXML
    private TableColumn<Objeto, String> tcNome;
    @FXML
    private TableColumn<Objeto, String> tcCNPJ;
    @FXML
    private TableColumn<Objeto, String> tcEmailFornecedor;
    @FXML
    private TableColumn<Objeto, String> tcEnderecoFornecedor;
    @FXML
    private FontAwesomeIconView faSearchCliente;
    @FXML
    private FontAwesomeIconView faSearchFuncionario;
    @FXML
    private FontAwesomeIconView faSearchUsuario;
    @FXML
    private FontAwesomeIconView faSearchFornecedor;

    /**
     * Initializes the controller class.
     */
    private void limplaCampos()
    {
        tfNomeCliente.clear();
        tfNomeFornecedor.clear();
        tfNomeFuncionario.clear();
        tfNomeUsuario.clear();
    }
    
    private void inicializa()
    {
        limplaCampos();
        rbTodosClientes.setSelected(true);
        rbTodosFornecedores.setSelected(true);
        rbTodosFuncionarios.setSelected(true);
        rbTodosUsuarios.setSelected(true);
        
        if(tabClientes.isSelected())
            clickPesquisarCliente(new ActionEvent());
        else if(tabFornecedores.isSelected())
            clickPesquisarFornecedor(new ActionEvent());
        else if(tabFuncionarios.isSelected())
            clickPesquisarFuncionario(new ActionEvent());
        else
            clickPesquisarUsuario(new ActionEvent());
        
        btApagar.setDisable(true);
        btRestaurar.setDisable(true);
    }
    
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        
        nodes.add(lbTitulo);
        nodes.add(lbClientes);
        nodes.add(lbFornecedores);
        nodes.add(lbFuncionarios);
        nodes.add(lbUsuarios);
        
        nodes.add(tfCNPJ);
        nodes.add(tfCpfCliente);
        nodes.add(tfCpfFuncionario);
        nodes.add(tfNomeCliente);
        nodes.add(tfNomeFornecedor);
        nodes.add(tfNomeFuncionario);
        nodes.add(tfNomeUsuario);
        
        nodes.add(faRestore);
        nodes.add(faSearchCliente);
        nodes.add(faSearchFornecedor);
        nodes.add(faSearchFuncionario);
        nodes.add(faSearchUsuario);
        nodes.add(faTrash);
        
        nodes.add(btApagar);
        nodes.add(btPesquisarCliente);
        nodes.add(btPesquisarFornecedor);
        nodes.add(btPesquisarFuncionario);
        nodes.add(btPesquisarUsuario);
        nodes.add(btRestaurar);
        
        nodes.add(rbCNPJ);
        nodes.add(rbCpfCliente);
        nodes.add(rbCpfFuncionario);
        nodes.add(rbNivel);
        nodes.add(rbNomeCliente);
        nodes.add(rbNomeFornecedor);
        nodes.add(rbNomeFuncionario);
        nodes.add(rbNomeUsuario);
        nodes.add(rbTodosClientes);
        nodes.add(rbTodosFornecedores);
        nodes.add(rbTodosFuncionarios);
        nodes.add(rbTodosUsuarios);
        
        Utils.setDesign(1, nodes);
        btApagar.setStyle(btApagar.getStyle() + ";-fx-cursor: default;");
        btPesquisarCliente.setStyle(btPesquisarCliente.getStyle() + ";-fx-cursor: default;");
        btPesquisarFornecedor.setStyle(btPesquisarFornecedor.getStyle() + ";-fx-cursor: default;");
        btPesquisarFuncionario.setStyle(btPesquisarFuncionario.getStyle() + ";-fx-cursor: default;");
        btPesquisarUsuario.setStyle(btPesquisarUsuario.getStyle() + ";-fx-cursor: default;");
        btRestaurar.setStyle(btRestaurar.getStyle() + ";-fx-cursor: default;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        EventHandler handler = (EventHandler) (Event event) ->
        {
            if(tabClientes.isSelected())
                clickPesquisarCliente(new ActionEvent());
            else if(tabFornecedores.isSelected())
                clickPesquisarFornecedor(new ActionEvent());
            else if(tabFuncionarios.isSelected())
                clickPesquisarFuncionario(new ActionEvent());
            else
                clickPesquisarUsuario(new ActionEvent());
            
            btApagar.setDisable(true);
            btRestaurar.setDisable(true);
        };
        tabClientes.setOnSelectionChanged(handler);
        tabFornecedores.setOnSelectionChanged(handler);
        tabFuncionarios.setOnSelectionChanged(handler);
        tabUsuarios.setOnSelectionChanged(handler);
                
        tcNomeCliente.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcCpfCliente.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcRgCliente.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcEmailCliente.setCellValueFactory(new PropertyValueFactory<>("param8"));
        tcEnderecoCliente.setCellValueFactory(new PropertyValueFactory<>("param7"));
        
        tcEnderecoCliente.setCellFactory(TooltippedTableCell.forTableColumn());
        tcNomeCliente.setCellFactory(TooltippedTableCell.forTableColumn());
        
        tcNome.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcCNPJ.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcEmailFornecedor.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcEnderecoFornecedor.setCellValueFactory(new PropertyValueFactory<>("param7"));
        
        tcEnderecoFornecedor.setCellFactory(TooltippedTableCell.forTableColumn());
        tcNome.setCellFactory(TooltippedTableCell.forTableColumn());
        
        tcUsuario.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcFuncionario.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcNivel.setCellValueFactory(new PropertyValueFactory<>("param4"));
        
        tcFuncionario.setCellFactory(TooltippedTableCell.forTableColumn());
        
        tcNomeFuncionario.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcCpfFuncionario.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcRgFuncionario.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcEmailFuncionario.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcEnderecoFuncionario.setCellValueFactory(new PropertyValueFactory<>("param11"));
        
        tcNomeFuncionario.setCellFactory(TooltippedTableCell.forTableColumn());
        tcEnderecoFuncionario.setCellFactory(TooltippedTableCell.forTableColumn());
        
        rbTodosClientes.setToggleGroup(group_cliente);
        rbCpfCliente.setToggleGroup(group_cliente);
        rbNomeCliente.setToggleGroup(group_cliente);
        rbTodosClientes.setSelected(true);
        MaskFieldUtil.cpfField(tfCpfCliente);
        
        rbNomeFuncionario.setToggleGroup(group_func);
        rbTodosFuncionarios.setToggleGroup(group_func);
        rbCpfFuncionario.setToggleGroup(group_func);
        rbTodosFuncionarios.setSelected(true);
        MaskFieldUtil.cpfField(tfCpfFuncionario);
        
        rbNomeFornecedor.setToggleGroup(group_forn);
        rbTodosFornecedores.setToggleGroup(group_forn);
        rbCNPJ.setToggleGroup(group_forn);
        rbTodosFornecedores.setSelected(true);
        MaskFieldUtil.cnpjField(tfCNPJ);
        
        rbTodosUsuarios.setToggleGroup(group_usr);
        rbNomeUsuario.setToggleGroup(group_usr);
        rbNivel.setToggleGroup(group_usr);
        rbTodosUsuarios.setSelected(true);
        
        cbNivel.getItems().addAll("baixo","medio","alto");
        cbNivel.getSelectionModel().select(0);
        
        inicializaDesign();
        inicializa();
    }    

    @FXML
    private void clickRestaurar(ActionEvent event)
    {
        Alert alerta;
        Objeto obj;
        
        if(tabClientes.isSelected())
        {
            if(tvClientes.getSelectionModel().getSelectedIndex() >= 0)
            {
                obj = tvClientes.getSelectionModel().getSelectedItem();
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja restaurar cliente " + 
                    obj.getParam2(), ButtonType.YES,ButtonType.NO);
                
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrCli.restaura(Integer.parseInt(obj.getParam1())))
                    {
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Restauração do cliente concluída com "
                                + "sucesso!!!", ButtonType.OK);
                        inicializa();
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro na restauração do cliente", 
                                ButtonType.OK);
                    
                    alerta.showAndWait();
                }
            }
            else
                new Alert(Alert.AlertType.ERROR, "Por favor selecione um cliente para restaurar", 
                        ButtonType.OK).showAndWait();
        }
        else if(tabFornecedores.isSelected())
        {
            if(tvFornecedor.getSelectionModel().getSelectedIndex() >= 0)
            {
                obj = tvFornecedor.getSelectionModel().getSelectedItem();
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja restaurar fornecedor " + 
                    obj.getParam2(), ButtonType.YES,ButtonType.NO);
                
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrForn.restaura(Integer.parseInt(obj.getParam1())))
                    {
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Restauração do fornecedor "
                                + "concluída com sucesso!!!", ButtonType.OK);
                        inicializa();
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro na restauração do fornecedor", 
                                ButtonType.OK);
                    
                    alerta.showAndWait();
                }
            }
            else
                new Alert(Alert.AlertType.ERROR, "Por favor selecione um fornecedor para restaurar", 
                        ButtonType.OK).showAndWait();
        }
        else if(tabFuncionarios.isSelected())
        {
            if(tvFuncionarios.getSelectionModel().getSelectedIndex() >= 0)
            {
                obj = tvFuncionarios.getSelectionModel().getSelectedItem();
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja restaurar funcionário " + 
                    obj.getParam2(), ButtonType.YES,ButtonType.NO);
                
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrFunc.restaura(Integer.parseInt(obj.getParam1())))
                    {
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Restauração do funcionário "
                                + "concluída com sucesso!!!", ButtonType.OK);
                        inicializa();
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro na restauração do funionário", 
                                ButtonType.OK);
                    
                    alerta.showAndWait();
                }
            }
            else
                new Alert(Alert.AlertType.ERROR, "Por favor selecione um fornecedor para restaurar", 
                        ButtonType.OK).showAndWait();
        }
        else
        {
            if(tvUsuarios.getSelectionModel().getSelectedIndex() >= 0)
            {
                obj = tvUsuarios.getSelectionModel().getSelectedItem();
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja restaurar usuário " + 
                    obj.getParam2(), ButtonType.YES,ButtonType.NO);
                
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrUsr.restaura(Integer.parseInt(obj.getParam1())))
                    {
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Restauração do usuário concluída "
                                + "com sucesso!!!", ButtonType.OK);
                        inicializa();
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro na restauração do usuário", 
                                ButtonType.OK);
                    
                    alerta.showAndWait();
                }
            }
            else
                new Alert(Alert.AlertType.ERROR, "Por favor selecione um usuário para restaurar", 
                        ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void clickApagar(ActionEvent event)
    {
        Alert alerta;
        Objeto obj;
        
        if (Utils.confirmarUsuario("Confirmar usuário", "Para concluir exclusão", 
                "Usuário: " + TelaPrincipalController.usuario_logado.getParam3())) 
        {
            if(tabClientes.isSelected())
            {
                if(tvClientes.getSelectionModel().getSelectedIndex() >= 0)
                {
                    obj = tvClientes.getSelectionModel().getSelectedItem();
                    alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover cliente " + 
                        obj.getParam2(), ButtonType.YES,ButtonType.NO);

                    alerta.showAndWait();

                    if(alerta.getResult() == ButtonType.YES)
                    {
                        if(ctrCli.apagar_fisico(Integer.parseInt(obj.getParam1())))
                        {
                            alerta = new Alert(Alert.AlertType.INFORMATION, "Exclusão do cliente concluída com "
                                    + "sucesso!!!", ButtonType.OK);
                            inicializa();
                        }
                        else
                            alerta = new Alert(Alert.AlertType.ERROR, "Erro na exclusão do cliente", 
                                    ButtonType.OK);

                        alerta.showAndWait();
                    }
                }
                else
                    new Alert(Alert.AlertType.ERROR, "Por favor selecione um cliente para remover", 
                            ButtonType.OK).showAndWait();
            }
            else if(tabFornecedores.isSelected())
            {
                if(tvFornecedor.getSelectionModel().getSelectedIndex() >= 0)
                {
                    obj = tvFornecedor.getSelectionModel().getSelectedItem();
                    alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover fornecedor " + 
                        obj.getParam2(), ButtonType.YES,ButtonType.NO);

                    alerta.showAndWait();

                    if(alerta.getResult() == ButtonType.YES)
                    {
                        if(ctrForn.apagar_fisico(Integer.parseInt(obj.getParam1())))
                        {
                            alerta = new Alert(Alert.AlertType.INFORMATION, "Exclusão do fornecedor "
                                    + "concluída com sucesso!!!", ButtonType.OK);
                            inicializa();
                        }
                        else
                            alerta = new Alert(Alert.AlertType.ERROR, "Erro na exclusão do fornecedor", 
                                    ButtonType.OK);

                        alerta.showAndWait();
                    }
                }
                else
                    new Alert(Alert.AlertType.ERROR, "Por favor selecione um fornecedor para remover", 
                            ButtonType.OK).showAndWait();
            }
             else if(tabFuncionarios.isSelected())
            {
                if(tvFuncionarios.getSelectionModel().getSelectedIndex() >= 0)
                {
                    obj = tvFuncionarios.getSelectionModel().getSelectedItem();
                    alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover funcionário " + 
                        obj.getParam2(), ButtonType.YES,ButtonType.NO);

                    alerta.showAndWait();

                    if(alerta.getResult() == ButtonType.YES)
                    {
                        if(ctrFunc.apagar_fisico(Integer.parseInt(obj.getParam1())))
                        {
                            alerta = new Alert(Alert.AlertType.INFORMATION, "Exclusão do funcionário "
                                    + "concluída com sucesso!!!", ButtonType.OK);
                            inicializa();
                        }
                        else
                            alerta = new Alert(Alert.AlertType.ERROR, "Erro na exclusão do funcionário", 
                                    ButtonType.OK);

                        alerta.showAndWait();
                    }
                }
                else
                    new Alert(Alert.AlertType.ERROR, "Por favor selecione um fornecedor para remover", 
                            ButtonType.OK).showAndWait();
            }
            else
            {
                if(tvUsuarios.getSelectionModel().getSelectedIndex() >= 0)
                {
                    obj = tvUsuarios.getSelectionModel().getSelectedItem();
                    alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover usuário " + 
                        obj.getParam2(), ButtonType.YES,ButtonType.NO);

                    alerta.showAndWait();

                    if(alerta.getResult() == ButtonType.YES)
                    {
                        if(ctrUsr.apagar_fisico(Integer.parseInt(obj.getParam1())))
                        {
                            alerta = new Alert(Alert.AlertType.INFORMATION, "Exclusão do usuário concluída "
                                    + "com sucesso!!!", ButtonType.OK);
                            inicializa();
                        }
                        else
                            alerta = new Alert(Alert.AlertType.ERROR, "Erro na exclusão do usuário", 
                                    ButtonType.OK);

                        alerta.showAndWait();
                    }
                }
                else
                    new Alert(Alert.AlertType.ERROR, "Por favor selecione um usuário para remover", 
                            ButtonType.OK).showAndWait();
            }
        }
        else
            new Alert(Alert.AlertType.ERROR, "Senha incorreta", ButtonType.OK).showAndWait();
    }

    @FXML
    private void clickPesquisarCliente(ActionEvent event)
    {
        tvClientes.getItems().clear();
        if(rbTodosClientes.isSelected())
            tvClientes.setItems(FXCollections.observableArrayList(ctrCli.getByNameInativo("")));
        else if(rbNomeCliente.isSelected())
            tvClientes.setItems(FXCollections.observableArrayList(ctrCli.getByNameInativo(tfNomeCliente.getText())));
        else
            tvClientes.setItems(FXCollections.observableArrayList(ctrCli.getByCPFInativo(tfCpfCliente.getText())));
    }

    @FXML
    private void selecionaCliente(MouseEvent event)
    {
        if(tabClientes.isSelected() && tvClientes.getSelectionModel().getSelectedIndex() >= 0)
        {
            btApagar.setDisable(false);
            btRestaurar.setDisable(false);
        }
        else
        {
            btApagar.setDisable(true);
            btRestaurar.setDisable(true);
        }
    }

    @FXML
    private void clickPesquisarFuncionario(ActionEvent event)
    {
        tvFuncionarios.getItems().clear();
        if(rbTodosFuncionarios.isSelected())
            tvFuncionarios.setItems(FXCollections.observableArrayList(ctrFunc.getByNameInativo("")));
        else if(rbNomeFuncionario.isSelected())
            tvFuncionarios.setItems(FXCollections.observableArrayList(ctrFunc.getByNameInativo(tfNomeFuncionario.getText())));
        else
            tvFuncionarios.setItems(FXCollections.observableArrayList(ctrFunc.getByCPFInativo(tfCpfFuncionario.getText())));
    }

    @FXML
    private void selecionaFuncionario(MouseEvent event)
    {
        if(tabFuncionarios.isSelected() && tvFuncionarios.getSelectionModel().getSelectedIndex() >= 0)
        {
            btApagar.setDisable(false);
            btRestaurar.setDisable(false);
        }
        else
        {
            btApagar.setDisable(true);
            btRestaurar.setDisable(true);
        }
    }

    @FXML
    private void clickPesquisarUsuario(ActionEvent event)
    {
        tvUsuarios.getItems().clear();
        if(rbTodosUsuarios.isSelected())
            tvUsuarios.setItems(FXCollections.observableArrayList(ctrUsr.getAllInativo()));
        else if(rbNomeUsuario.isSelected())
            tvUsuarios.setItems(FXCollections.observableArrayList(ctrUsr.getByNameInativo(tfNomeUsuario.getText())));
        else
            tvUsuarios.setItems(FXCollections.observableArrayList(ctrUsr.getByNivelInativo(cbNivel.getSelectionModel().getSelectedItem())));
    }

    @FXML
    private void selecionaUsuario(MouseEvent event)
    {
        if(tabUsuarios.isSelected() && tvUsuarios.getSelectionModel().getSelectedIndex() >= 0)
        {
            btApagar.setDisable(false);
            btRestaurar.setDisable(false);
        }
        else
        {
            btApagar.setDisable(true);
            btRestaurar.setDisable(true);
        }
    }

    @FXML
    private void clickPesquisarFornecedor(ActionEvent event)
    {
        tvFornecedor.getItems().clear();
        if(rbTodosFornecedores.isSelected())
            tvFornecedor.setItems(FXCollections.observableArrayList(ctrForn.getAllInativo()));
        else if(rbNomeFornecedor.isSelected())
            tvFornecedor.setItems(FXCollections.observableArrayList(ctrForn.getByNameInativo(tfNomeFornecedor.getText())));
        else
            tvFornecedor.setItems(FXCollections.observableArrayList(ctrForn.getByCNPJInativo(tfCNPJ.getText())));
    }

    @FXML
    private void selecionaFornecedor(MouseEvent event)
    {
        if(tabFornecedores.isSelected() && tvFornecedor.getSelectionModel().getSelectedIndex() >= 0)
        {
            btApagar.setDisable(false);
            btRestaurar.setDisable(false);
        }
        else
        {
            btApagar.setDisable(true);
            btRestaurar.setDisable(true);
        }
    }

    @FXML
    private void restaurarExit(MouseEvent event)
    {
        btRestaurar.setStyle(btRestaurar.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void restaurarEnter(MouseEvent event)
    {
        btRestaurar.setStyle(btRestaurar.getStyle() + ";-fx-cursor: hand;");
        if(tabClientes.isSelected())
            tooltip.setText("Restaurar Cliente");
        else if(tabFornecedores.isSelected())
            tooltip.setText("Restaurar Fornecedor");
        else if(tabFuncionarios.isSelected())
            tooltip.setText("Restaurar Funionário");
        else
            tooltip.setText("Restaurar Usuário");
        ToolTip.bindTooltip(btRestaurar, tooltip);
    }

    @FXML
    private void apagarExit(MouseEvent event)
    {
        btApagar.setStyle(btApagar.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void apagarEnter(MouseEvent event)
    {
        btApagar.setStyle(btApagar.getStyle() + ";-fx-cursor: hand;");
        if(tabClientes.isSelected())
            tooltip.setText("Apagar Cliente");
        else if(tabFornecedores.isSelected())
            tooltip.setText("Apagar Fornecedor");
        else if(tabFuncionarios.isSelected())
            tooltip.setText("Apagar Funionário");
        else
            tooltip.setText("Apagar Usuário");
        ToolTip.bindTooltip(btApagar, tooltip);
    }

    @FXML
    private void pesquisarClienteExit(MouseEvent event)
    {
        btPesquisarCliente.setStyle(btPesquisarCliente.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void pesquisarClienteEnter(MouseEvent event)
    {
        btPesquisarCliente.setStyle(btPesquisarCliente.getStyle() + ";-fx-cursor: hand;");
        tooltip.setText("Pesquisar Cliente");
        ToolTip.bindTooltip(btPesquisarCliente, tooltip);
    }

    @FXML
    private void pesquisarFuncionarioExit(MouseEvent event)
    {
        btPesquisarFuncionario.setStyle(btPesquisarFuncionario.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void pesquisarFuncionarioEnter(MouseEvent event)
    {
        btPesquisarFuncionario.setStyle(btPesquisarFuncionario.getStyle() + ";-fx-cursor: hand;");
        tooltip.setText("Pesquisar Funcionário");
        ToolTip.bindTooltip(btPesquisarFuncionario, tooltip);
    }

    @FXML
    private void pesquisarUsuarioExit(MouseEvent event)
    {
        btPesquisarUsuario.setStyle(btPesquisarUsuario.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void pesquisarUsuarioEnter(MouseEvent event)
    {
        btPesquisarUsuario.setStyle(btPesquisarUsuario.getStyle() + ";-fx-cursor: hand;");
        tooltip.setText("Pesquisar Usuário");
        ToolTip.bindTooltip(btPesquisarUsuario, tooltip);
    }

    @FXML
    private void pesquisarFornecedorExit(MouseEvent event)
    {
        btPesquisarFornecedor.setStyle(btPesquisarFornecedor.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void pesquisarFornecedorEnter(MouseEvent event)
    {
        btPesquisarFornecedor.setStyle(btPesquisarFornecedor.getStyle() + ";-fx-cursor: hand;");
        tooltip.setText("Pesquisar Fornecedor");
        ToolTip.bindTooltip(btPesquisarFornecedor, tooltip);
    }
}
