/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaFinancasController implements Initializable
{

    @FXML
    private Pane folderCompra;
    @FXML
    private Pane paneCompra;
    @FXML
    private Pane folderVenda;
    @FXML
    private Pane paneVenda;
    @FXML
    private ImageView imgClientes1;
    @FXML
    private Pane folderPagamento;
    @FXML
    private Pane panePagamento;
    @FXML
    private Pane folderRecebimento;
    @FXML
    private Pane paneRecebimento;
    @FXML
    private Pane folderBalanco;
    @FXML
    private Pane paneBalanco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickCompra(MouseEvent event)
    {
    }

    @FXML
    private void clickVenda(MouseEvent event)
    {
    }

    @FXML
    private void clickPagamento(MouseEvent event)
    {
    }

    @FXML
    private void clickRecebimento(MouseEvent event)
    {
    }

    @FXML
    private void clickBalanco(MouseEvent event)
    {
    }

    @FXML
    private void compraExit(MouseEvent event)
    {
    }

    @FXML
    private void compraEnter(MouseEvent event)
    {
    }

    @FXML
    private void vendaExit(MouseEvent event)
    {
    }

    @FXML
    private void vendaEnter(MouseEvent event)
    {
    }

    @FXML
    private void pagamentoExit(MouseEvent event)
    {
    }

    @FXML
    private void pagamentoEnter(MouseEvent event)
    {
    }

    @FXML
    private void recebimenoExit(MouseEvent event)
    {
    }

    @FXML
    private void recebimentoEnter(MouseEvent event)
    {
    }

    @FXML
    private void balanceExit(MouseEvent event)
    {
    }

    @FXML
    private void balancoEnter(MouseEvent event)
    {
    }
    
}
