/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.utilidades.Objeto;
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
    private Pane paneFabricantes;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbModelos;
    @FXML
    private Label lbErroMarca;
    @FXML
    private Label lbErroModelo;
    @FXML
    private JFXTextField tfMarca;
    @FXML
    private JFXTextField tfModelo;
    @FXML
    private HBox hbModelo;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faEdit;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private TableView<Objeto> tvFabricantes;
    @FXML
    private TableColumn<Objeto,String> tcMarca;
    @FXML
    private TableColumn<Objeto,String> tcModelo;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
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
    
}
