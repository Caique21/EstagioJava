/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class CadastrosInativosController implements Initializable
{

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneClientes;
    @FXML
    private Pane paneFuncionarios;
    @FXML
    private Pane paneUsuarios;
    @FXML
    private Pane paneFornecedores;
    @FXML
    private Label lbTitulo;
    @FXML
    private JFXButton btRestaurar;
    @FXML
    private JFXButton btApagar;
    @FXML
    private FontAwesomeIconView faRestore;
    @FXML
    private FontAwesomeIconView faTrash;
    @FXML
    private JFXTextField tfNomeCliente;
    @FXML
    private JFXTextField tfCPFCliente;
    @FXML
    private JFXRadioButton rbTodosCliente;
    @FXML
    private JFXRadioButton rbNomeCliente;
    @FXML
    private JFXRadioButton rbCPFCliente;
    @FXML
    private JFXButton btPesquisarCliente;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private Label lbClientes;
    @FXML
    private TableView<?> tvClientes;
    @FXML
    private TableColumn<?, ?> tcNomeCliente;
    @FXML
    private TableColumn<?, ?> tcCPFCliente;
    @FXML
    private TableColumn<?, ?> tcRGCliente;
    @FXML
    private TableColumn<?, ?> tcEmailCliente;
    @FXML
    private TableColumn<?, ?> tcEnderecoCliente;
    @FXML
    private Label lbFuncionarios;
    @FXML
    private JFXTextField tfNomeFuncionario;
    @FXML
    private JFXTextField tfCpfFuncionario;
    @FXML
    private JFXRadioButton rbTodosFuncionario;
    @FXML
    private JFXRadioButton rbNomeFuncionario;
    @FXML
    private JFXRadioButton rbCpfFuncionario;
    @FXML
    private JFXButton btPesquisarFuncionario;
    @FXML
    private TableView<?> tvFuncionarios;
    @FXML
    private TableColumn<?, ?> tcNomeFuncionario;
    @FXML
    private TableColumn<?, ?> tcCPFFuncionario;
    @FXML
    private TableColumn<?, ?> tcRGFuncionario;
    @FXML
    private TableColumn<?, ?> tcEmailFuncionario;
    @FXML
    private TableColumn<?, ?> tcEnderecoFuncionario;
    @FXML
    private JFXTextField tfNomeUsuario;
    @FXML
    private JFXComboBox<?> cbNivel;
    @FXML
    private Label lbUsuarios;
    @FXML
    private JFXRadioButton rbTodosUsuario;
    @FXML
    private JFXRadioButton rbNomeUsuario;
    @FXML
    private JFXRadioButton rbNivel;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private TableView<?> tvUsuarios;
    @FXML
    private TableColumn<?, ?> tcFuncionario;
    @FXML
    private TableColumn<?, ?> tcUsuario;
    @FXML
    private TableColumn<?, ?> tcNivel;
    @FXML
    private Label lbFornecedores;
    @FXML
    private JFXTextField tfNomeFornecedor;
    @FXML
    private JFXTextField tfCNPJ;
    @FXML
    private JFXRadioButton rbTodosFornecedores;
    @FXML
    private JFXRadioButton rbNomeFornecedor;
    @FXML
    private JFXRadioButton rbCNPJ;
    @FXML
    private JFXButton btPesquisarFornecedor;
    @FXML
    private TableView<?> tvFornecedores;
    @FXML
    private TableColumn<?, ?> tcNomeFornecedor;
    @FXML
    private TableColumn<?, ?> tcCNPJ;
    @FXML
    private TableColumn<?, ?> tcEmailFornecedor;
    @FXML
    private TableColumn<?, ?> tcEnderecoFornecedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickRestaurar(ActionEvent event)
    {
    }

    @FXML
    private void clickApagar(ActionEvent event)
    {
    }

    @FXML
    private void clickPesquisarCliente(ActionEvent event)
    {
    }

    @FXML
    private void selecionaCliente(MouseEvent event)
    {
    }

    @FXML
    private void btClienteExit(MouseEvent event)
    {
    }

    @FXML
    private void btClienteEnter(MouseEvent event)
    {
    }

    @FXML
    private void clickPesquisarFuncionario(ActionEvent event)
    {
    }

    @FXML
    private void selecionaFuncionario(MouseEvent event)
    {
    }

    @FXML
    private void btFuncionarioExit(MouseEvent event)
    {
    }

    @FXML
    private void btFuncionarioEnter(MouseEvent event)
    {
    }

    @FXML
    private void selecionaUsuario(MouseEvent event)
    {
    }

    @FXML
    private void clickPesquisarUsuario(ActionEvent event)
    {
    }

    @FXML
    private void btUsuarioExit(MouseEvent event)
    {
    }

    @FXML
    private void btUsuarioEnter(MouseEvent event)
    {
    }

    @FXML
    private void clickPesquisarFornecedor(ActionEvent event)
    {
    }

    @FXML
    private void selecionaFornecedor(MouseEvent event)
    {
    }

    @FXML
    private void btFornecedorExit(MouseEvent event)
    {
    }

    @FXML
    private void btForncedorEnter(MouseEvent event)
    {
    }
    
}
