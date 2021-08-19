/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastroFabricanteController implements Initializable
{

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneMarcas;
    @FXML
    private Pane paneModelos;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbMarcas;
    @FXML
    private Label lbModelos;
    @FXML
    private Label lbErroMarca;
    @FXML
    private Label lbErroModelo;
    @FXML
    private Label lbErroMarcaModelo;
    @FXML
    private JFXTextField tfMarca;
    @FXML
    private JFXTextField tfModelo;
    @FXML
    private JFXTextField tfMarcaModelo;
    @FXML
    private HBox hbMarca;
    @FXML
    private HBox hbModelo;
    @FXML
    private JFXButton btNovoMarca;
    @FXML
    private JFXButton btConfirmarMarca;
    @FXML
    private JFXButton btAlterarMarca;
    @FXML
    private JFXButton btRemoverMarca;
    @FXML
    private JFXButton btCancelarMarca;
    @FXML
    private JFXButton btNovoModelo;
    @FXML
    private JFXButton btConfirmarModelo;
    @FXML
    private JFXButton btAlterarModelo;
    @FXML
    private JFXButton btRemoverModelo;
    @FXML
    private JFXButton btPesquisarMarca;
    @FXML
    private JFXButton btCancelarModelo;
    @FXML
    private JFXButton btPesquisarModelo;
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
    private TableView<?> tvMarcas;
    @FXML
    private TableColumn<?, ?> tcMarca;
    @FXML
    private TableView<?> tvModelos;
    @FXML
    private TableColumn<?, ?> tcMarcaModelo;
    @FXML
    private TableColumn<?, ?> tcModelo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickNovoMarca(ActionEvent event)
    {
    }

    @FXML
    private void clickConfirmarMarca(ActionEvent event)
    {
    }

    @FXML
    private void clickAlterarMarca(ActionEvent event)
    {
    }

    @FXML
    private void clickRemoverMarca(ActionEvent event)
    {
    }

    @FXML
    private void clickCancelarMarca(ActionEvent event)
    {
    }

    @FXML
    private void clickPesquisarMarca(ActionEvent event)
    {
    }

    @FXML
    private void clickNovoModelo(ActionEvent event)
    {
    }

    @FXML
    private void clickConfirmarModelo(ActionEvent event)
    {
    }

    @FXML
    private void clickAlterarModelo(ActionEvent event)
    {
    }

    @FXML
    private void clickRemoverModelo(ActionEvent event)
    {
    }

    @FXML
    private void clickCancelarModelo(ActionEvent event)
    {
    }

    @FXML
    private void clickPesquisarModelo(ActionEvent event)
    {
    }

    @FXML
    private void novaMarcaExit(MouseEvent event)
    {
    }

    @FXML
    private void novaMarcaEnter(MouseEvent event)
    {
    }

    @FXML
    private void confirmarMarcaExit(MouseEvent event)
    {
    }

    @FXML
    private void confirmarMarcaEnter(MouseEvent event)
    {
    }

    @FXML
    private void alterarMarcaExit(MouseEvent event)
    {
    }

    @FXML
    private void alterarMarcaEnter(MouseEvent event)
    {
    }

    @FXML
    private void removerMarcaExit(MouseEvent event)
    {
    }

    @FXML
    private void removerMarcaEnter(MouseEvent event)
    {
    }

    @FXML
    private void cancelarMarcaExit(MouseEvent event)
    {
    }

    @FXML
    private void cancelarMarcaEnter(MouseEvent event)
    {
    }

    @FXML
    private void pesquisarMarcaExit(MouseEvent event)
    {
    }

    @FXML
    private void pesquisarMarcaEnter(MouseEvent event)
    {
    }

    @FXML
    private void pesquisarModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void pesquisarModeloEnter(MouseEvent event)
    {
    }

    @FXML
    private void novoModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void novoModeloEnter(MouseEvent event)
    {
    }

    @FXML
    private void confirmarModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void confirmarModeloEnter(MouseEvent event)
    {
    }

    @FXML
    private void alterarModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void alterarModeloEnter(MouseEvent event)
    {
    }

    @FXML
    private void removerModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void removerModeloEnter(MouseEvent event)
    {
    }

    @FXML
    private void cancelarModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void cancelarModeloEnter(MouseEvent event)
    {
    }
    
}
