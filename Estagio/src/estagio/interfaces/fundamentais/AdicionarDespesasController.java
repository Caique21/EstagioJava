/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class AdicionarDespesasController implements Initializable
{

    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfValor;
    @FXML
    private JFXTextArea taObs;
    @FXML
    private TableView<?> tvDespesas;
    @FXML
    private TableColumn<?, ?> tcNome;
    @FXML
    private TableColumn<?, ?> tcValor;
    @FXML
    private TableColumn<?, ?> tcObs;
    @FXML
    private TableColumn<?, ?> tcAdd;
    @FXML
    private TableColumn<?, ?> tcDel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
