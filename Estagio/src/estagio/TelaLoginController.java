/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrAcessos;
import estagio.controladores.ctrParametrizacao;
import estagio.controladores.ctrUsuario;
import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaLoginController implements Initializable
{
    private Alert alert;
    private final ctrUsuario ctr = ctrUsuario.instancia();
    private final ctrAcessos ctr_acessos = ctrAcessos.instancia();
    private final ctrParametrizacao ctrPara = ctrParametrizacao.instancia();
    private TelaPrincipalController tela;

    @FXML
    private AnchorPane panePrincipal;
    @FXML
    private Label lbTitulo;
    @FXML
    private Line line1;
    @FXML
    private Circle circle;
    @FXML
    private Line line2;
    @FXML
    private JFXTextField tfUsuario;
    @FXML
    private JFXPasswordField tfSenha;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btSair;
    @FXML
    private Label lbErroUsuario;
    @FXML
    private Label lbErroSenha;
    @FXML
    private FontAwesomeIconView faPassword;
    @FXML
    private FontAwesomeIconView faUsuario;

    /**
     * Initializes the controller class.
     */
    private void inicializa()
    {
        lbErroSenha.setText("");
        lbErroUsuario.setText("");
        /*Utils.carregaDesign();
        
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(lbTitulo);
        nodes.add(tfSenha);
        nodes.add(tfUsuario);
        nodes.add(faPassword);
        nodes.add(faUsuario);
        nodes.add(btConfirmar);
        nodes.add(btSair);
        Utils.setDesign(1, nodes);
        
        tfSenha.setStyle(tfSenha.getStyle() + ";-fx-font-size: 15;");
        faPassword.setSize("20");
        tfUsuario.setStyle(tfUsuario.getStyle() + ";-fx-font-size: 15");
        faUsuario.setSize("20");*/
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializa();
        
        Platform.runLater(() -> 
        {
            tfUsuario.requestFocus();
        });
    }    

    @FXML
    private void keyPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickConfirmar(new ActionEvent());
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        lbErroSenha.setText("");
        lbErroUsuario.setText("");
        
        if(ctr.isUsuario(tfUsuario.getText()))//nome de usuario existente
        {
            if(ctr.matchPassword(tfUsuario.getText(), tfSenha.getText()))//senha bate
            {
                alert = null;
                TelaPrincipalController.usuario_logado = ctr.get(tfUsuario.getText(),tfSenha.getText());;
                TelaPrincipalController.data_login = new Timestamp(new java.util.Date().getTime());
                abrirTela();
            }
            else
            {
                lbErroSenha.setText("Senha incorreta");
                alert = new Alert(Alert.AlertType.ERROR, "Senha inccoreta", ButtonType.OK);
            }
        }
        else
        {
            lbErroUsuario.setText("Usuário não cadastrado");
            alert = new Alert(Alert.AlertType.ERROR, "Usuário não cadastrado", ButtonType.OK);
        }
        if(alert != null)
            alert.showAndWait();
    }

    private void abrirTela()
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/estagio/TelaPrincipal.fxml"));
            Stage stage = new Stage();

            JFXDecorator decorator = new JFXDecorator(stage , root);
            decorator.setStyle("-fx-decorator-color: #040921;");
            decorator.setMaximized(true);
            decorator.setCustomMaximize(true);  

            Scene scene = new Scene(decorator);
            scene.setFill(Paint.valueOf("black"));

            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("Nome");
            stage.setAlwaysOnTop(false);
            stage.setOnCloseRequest((WindowEvent event) ->
            {
                alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja fechar a aplicação?", ButtonType.YES,ButtonType.NO);
                alert.showAndWait();
                
                if(alert.getResult() == ButtonType.YES)
                {
                    if(TelaPrincipalController.alteracoes)
                        Banco.realizaBackupNoMessage("bkp\\copiar.bat");
                    ctr_acessos.salvar
                        (TelaPrincipalController.data_login,new Timestamp(new java.util.Date().getTime()), 
                            TelaPrincipalController.usuario_logado);
                    Platform.exit();
                    System.exit(0);
                }
                else
                    stage.showAndWait();
            });

            Stage login = (Stage) btConfirmar.getScene().getWindow();
            login.close();

            stage.show();
        }
        catch (IOException ex)
        {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickSair(ActionEvent event)
    {
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Deseja sair da aplicação?", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        
        if(alert.getResult() == ButtonType.YES)
        {
            Stage stage = (Stage)btConfirmar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void confirmarExit(MouseEvent event)
    {
        btConfirmar.setStyle("-fx-cursor: default;" + btConfirmar.getStyle());
    }

    @FXML
    private void confirmarEnter(MouseEvent event)
    {
        btConfirmar.setStyle("-fx-cursor: hand;" + btConfirmar.getStyle());
    }

    @FXML
    private void sairExit(MouseEvent event)
    {
        btSair.setStyle("-fx-cursor: default;" + btSair.getStyle());
    }

    @FXML
    private void sairEnter(MouseEvent event)
    {
        btSair.setStyle("-fx-cursor: hand;" + btSair.getStyle());
    }
    
}
