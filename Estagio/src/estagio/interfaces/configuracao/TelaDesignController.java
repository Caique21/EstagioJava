/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.configuracao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaDesignController implements Initializable
{

    @FXML
    private Pane panePrincipal;
    @FXML
    private Pane PaneInterface;
    @FXML
    private JFXColorPicker cpFundo1;
    @FXML
    private JFXColorPicker cpFundo2;
    @FXML
    private JFXColorPicker cpFonte;
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    private Label lb3;
    @FXML
    private Label lbInterface;
    @FXML
    private JFXButton btResetInterface;
    @FXML
    private Label lbOpacidade;
    @FXML
    private JFXSlider slOpacidade;
    @FXML
    private Label lbTitulo;
    @FXML
    private Pane PaneBotoes;
    @FXML
    private JFXButton btTeste;
    @FXML
    private JFXColorPicker cpFundoBotao;
    @FXML
    private Label lb4;
    @FXML
    private Label lb5;
    @FXML
    private JFXColorPicker cpPreenchimento;
    @FXML
    private Label lb6;
    @FXML
    private Label lbBotoes;
    @FXML
    private Label lb7;
    @FXML
    private JFXColorPicker cpFonteBotao;
    @FXML
    private JFXButton btResetarBotoes;
    @FXML
    private JFXRadioButton rbFundoBotao;
    @FXML
    private JFXSlider slFonteBotao;
    @FXML
    private Pane paneTexto;
    @FXML
    private Label lbTexto;
    @FXML
    private JFXTextField tfTeste;
    @FXML
    private Label lb8;
    @FXML
    private JFXColorPicker cpFonteTexto;
    @FXML
    private Label lb9;
    @FXML
    private JFXColorPicker cpFoco;
    @FXML
    private Label lb10;
    @FXML
    private JFXButton btResetarTexto;
    @FXML
    private JFXSlider slFonteTexto;
    @FXML
    private JFXButton btSalvar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btReset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clickSalvar(ActionEvent event)
    {
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
    }

    @FXML
    private void testeExit(MouseEvent event)
    {
    }

    @FXML
    private void testeEnter(MouseEvent event)
    {
    }

    @FXML
    private void salvarExit(MouseEvent event)
    {
    }

    @FXML
    private void salvarEnter(MouseEvent event)
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
    
}
