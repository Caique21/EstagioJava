/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.configuracao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import estagio.TelaLoginController;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrDesign;
import estagio.utilidades.Utils;
import static estagio.utilidades.Utils.toRGB;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaDesignController implements Initializable
{
    private String style = "";
    private final ctrDesign ctrDes = ctrDesign.instancia();
    private boolean existe;
    public static Pane bt;

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
    @FXML
    private JFXTextField tfTeste2;

    /**
     * Initializes the controller class.
     */
    private void altera()
    {
        alterarBotoes();
        alterarInputText();
        alterarInterface();
    }
    
    private void alterarInputText()
    {
        style = tfTeste.getStyle() + ";-fx-prompt-text-fill: " + Utils.toRGBCode(cpFonteTexto.getValue()) + ";";
        style += "-fx-text-fill: " + Utils.toRGBCode(cpFonteTexto.getValue()) + ";";
        style += "-jfx-focus-color: " + Utils.toRGBCode(cpFoco.getValue()) + ";";
        style += "-jfx-unfocus-color: " + Utils.toRGBCode(cpFoco.getValue()) + ";";
        tfTeste.setStyle(tfTeste.getStyle() + ";" + style);
        tfTeste2.setStyle(tfTeste2.getStyle() + ";" + style);
    }
    
    private void alterarBotoes()
    {
        style = btCancelar.getStyle() + "; -fx-text-fill: " + Utils.toRGBCode(cpFonteBotao.getValue()) + "; "
                + "-fx-font-size: " + (int) slFonteBotao.getValue() + ";";
        if(rbFundoBotao.isSelected())
            style += ";-fx-background-color: " + Utils.toRGBCode(cpFundoBotao.getValue()) + ";";
        else
            style += ";-fx-background-color: transparent;";
        
        btTeste.setStyle(style);
        btSalvar.setStyle(style);
        btCancelar.setStyle(style);
        btTeste.setRipplerFill(cpPreenchimento.getValue());
        btSalvar.setRipplerFill(cpPreenchimento.getValue());
        btCancelar.setRipplerFill(cpPreenchimento.getValue());
    }
    
    private void alterarInterface()
    {
        java.awt.Color cor = toRGB(Utils.toRGBCode(cpFundo2.getValue()));
        style = "-fx-background-color: rgba(" + + cor.getRed() + "," + cor.getGreen() + 
             "," + cor.getBlue() + "," + ((double)((int) slOpacidade.getValue())/100) + ");" +
            "-fx-background-radius:5px;";
        
        panePrincipal.setStyle("-fx-background-color: " + Utils.toRGBCode(cpFundo1.getValue()));
        PaneInterface.setStyle(style);
        PaneBotoes.setStyle(style);
        paneTexto.setStyle(style);
        
        lbBotoes.setTextFill(cpFonte.getValue());
        lbInterface.setTextFill(cpFonte.getValue());
        lbTexto.setTextFill(cpFonte.getValue());
        lbTitulo.setTextFill(cpFonte.getValue());
        lb1.setTextFill(cpFonte.getValue());
        lb10.setTextFill(cpFonte.getValue());
        lb2.setTextFill(cpFonte.getValue());
        lb3.setTextFill(cpFonte.getValue());
        lb4.setTextFill(cpFonte.getValue());
        lb5.setTextFill(cpFonte.getValue());
        lb6.setTextFill(cpFonte.getValue());
        lb7.setTextFill(cpFonte.getValue());
        lb8.setTextFill(cpFonte.getValue());
        lb9.setTextFill(cpFonte.getValue());
        lbOpacidade.setTextFill(cpFonte.getValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        carrega();
        
        EventHandler<ActionEvent> interfaceEvent = (ActionEvent e) ->
        {
            alterarInterface();
        };
        
        EventHandler<ActionEvent> botoesEvent = (ActionEvent e) ->
        {
            alterarBotoes();
        };
        
        EventHandler<ActionEvent> textEvent = (ActionEvent e) ->
        {
            alterarInputText();
        };
  
        // set listener
        cpFundo1.setOnAction(interfaceEvent);
        cpFundo2.setOnAction(interfaceEvent);
        cpFonte.setOnAction(interfaceEvent);
        cpFonteBotao.setOnAction(botoesEvent);
        cpPreenchimento.setOnAction(botoesEvent);
        cpFundoBotao.setOnAction(botoesEvent);
        cpFonteTexto.setOnAction(textEvent);
        cpFoco.setOnAction(textEvent);
        
        slFonteBotao.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
        {
            btTeste.setStyle(btTeste.getStyle() + ";-fx-font-size: " + (int) slFonteBotao.getValue());
            btCancelar.setStyle(btTeste.getStyle() + ";-fx-font-size: " + (int) slFonteBotao.getValue());
            btSalvar.setStyle(btTeste.getStyle() + ";-fx-font-size: " + (int) slFonteBotao.getValue());
        });
        
        slFonteTexto.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
        {
            tfTeste.setStyle(tfTeste.getStyle() + ";-fx-font-size: " + (int) slFonteTexto.getValue());
        });
        
        slOpacidade.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            java.awt.Color cor = toRGB(Utils.toRGBCode(cpFundo2.getValue()));
            style = "-fx-background-color: rgba(" + + cor.getRed() + "," + cor.getGreen() + 
                 "," + cor.getBlue() + "," + ((double)((int) slOpacidade.getValue())/100) + ");" +
                "-fx-background-radius:5px;";
            //"rgba(" + cor.getRed() + "," + cor.getGreen() + "," + cor.getBlue() + ",0.5)"
            PaneInterface.setStyle(style);
            PaneBotoes.setStyle(style);
            paneTexto.setStyle(style);
        });
        
        rbFundoBotao.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->
        {
            if(newValue)
                rbFundoBotao.setText("Habilitado");
            else
                rbFundoBotao.setText("Desabilitado");
            altera();
        });
    }    
    
    private void carrega()
    {
        ArrayList<String>list = ctrDes.carrega();
        if(list == null || list.isEmpty())
        {
            existe = false;
            cpFonte.setValue(Color.web("#212121"));
            cpFonteBotao.setValue(Color.web("#212121"));
            cpFonteTexto.setValue(Color.web("#212121"));
            cpPreenchimento.setValue(Color.web("#212121"));
            cpFoco.setValue(Color.web("#212121"));
        }
        else
        {
            if(list.get(3).equals("transparent"))
                rbFundoBotao.setSelected(false);
            else 
                rbFundoBotao.setSelected(true);
            
            cpFundo1.setValue(Color.web(list.get(0)));
            cpFundo2.setValue(Color.web(list.get(1)));
            cpFonte.setValue(Color.web(list.get(2)));
            cpFundoBotao.setValue(Color.web(list.get(3)));
            cpPreenchimento.setValue(Color.web(list.get(4)));
            cpFonteBotao.setValue(Color.web(list.get(5)));
            slFonteBotao.setValue(Double.parseDouble(list.get(6)));
            cpFonteTexto.setValue(Color.web(list.get(7)));
            cpFoco.setValue(Color.web(list.get(8)));
            slFonteTexto.setValue(Double.parseDouble(list.get(9)));
            slOpacidade.setValue(Double.parseDouble(list.get(10)));
            altera();

            existe = true;
        }
    }

    @FXML
    private void clickSalvar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Deseja salvar alterações?", ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        
        if(a.getResult() == ButtonType.YES)
        {
            String fundo;
            if(rbFundoBotao.isSelected())
                fundo = Utils.toRGBCode(cpFundoBotao.getValue());
            else
                fundo = "transparent";
            
            if(!existe)
            {
                if(ctrDes.salvar(cpFundo1,cpFundo2,cpFonte,fundo,cpPreenchimento,cpFonteBotao,slFonteBotao,
                cpFonteTexto,cpFoco,slFonteTexto,slOpacidade))
                {
                    Utils.carregaDesign();
                    new Alert(Alert.AlertType.INFORMATION, "Design criado com sucesso!!!", ButtonType.OK)
                        .showAndWait();
                    carregaTelaLogin();
                }
                else
                   new Alert(Alert.AlertType.ERROR, "Erro na criação do Design", ButtonType.OK)
                        .showAndWait();
            }
            else
            {
                if(ctrDes.alterar(cpFundo1,cpFundo2,cpFonte,fundo,cpPreenchimento,cpFonteBotao,slFonteBotao,
                cpFonteTexto,cpFoco,slFonteTexto,slOpacidade))
                {
                    Utils.carregaDesign();
                    Notifications.create()
                        .darkStyle()
                        //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                        .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                        .text("Design alterado com sucesso!!!")
                        .showInformation();
                    new Alert(Alert.AlertType.WARNING, "Alguns componentes podem não ser alterados, caso ocorra "
                            + "reinicie o programa", ButtonType.OK)
                        .showAndWait();
                    
                    Stage stage = (Stage) bt.getScene().getWindow();
                    stage.close();
                    
                    reload();
                }
                else
                    new Alert(Alert.AlertType.ERROR, "Erro na alteração do Design", ButtonType.OK)
                        .showAndWait();
            }
        }
    }
    private void carregaTelaLogin()
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/estagio/TelaLogin.fxml"));
            Stage stage = new Stage();

            JFXDecorator decorator = new JFXDecorator(stage , root);
            decorator.setStyle("-fx-decorator-color: #040921;");

            Scene scene = new Scene(decorator);
            scene.setFill(Paint.valueOf("black"));

            stage.setScene(scene);
            stage.setTitle("Login");

            Stage login = (Stage) btSalvar.getScene().getWindow();
            login.close();

            stage.show();
        }
        catch (IOException ex)
        {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void reload()
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
            File file = new File("/estagio/utilidades/CSS/Style.css");
            URL url = file.toURI().toURL();
            scene.getStylesheets().add(url.toExternalForm());
            //scene.getStylesheets().add("\\estagio\\utilidades\\CSS\\Style.css");
            //scene.getStylesheets().add(getClass().getClassLoader().getResource("C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\EstagioJava\\Estagio\\src\\estagio\\utilidades\\CSS\\Style.css").toExternalForm());

            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("LGD");
            stage.setOnCloseRequest((WindowEvent event) ->
            {
                Platform.exit();
                System.exit(0);
            });

            Stage login = (Stage) btCancelar.getScene().getWindow();
            login.close();
            
            stage.show();
        }
        catch (IOException ex)
        {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cancelar e sair?", ButtonType.YES,ButtonType.NO);
        alerta.showAndWait();
        
        if(alerta.getResult() == ButtonType.YES)
        {
            if(existe)
            {
                Stage stage = (Stage) btCancelar.getScene().getWindow();
                stage.close();
            } 
            else
                carregaTelaLogin();
        }
    }

    @FXML
    private void salvarExit(MouseEvent event)
    {
        btSalvar.setStyle(btSalvar.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void salvarEnter(MouseEvent event)
    {
        btSalvar.setStyle(btSalvar.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void cancelarExit(MouseEvent event)
    {
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void cancelarEnter(MouseEvent event)
    {
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void testeExit(MouseEvent event)
    {
        btTeste.setStyle(btTeste.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void testeEnter(MouseEvent event)
    {
        btTeste.setStyle(btTeste.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void clickResetarInterface(ActionEvent event)
    {
        carregaInterface();
        altera();
    }

    @FXML
    private void clickResetarBotoes(ActionEvent event)
    {
        carregaBotoes();
        altera();
    }

    @FXML
    private void clickResetarTexto(ActionEvent event)
    {
        carregaTexto();
        altera();
    }

    @FXML
    private void clickResetar(ActionEvent event)
    {
        carrega();
        altera();
    }

    private void carregaTexto()
    {
        ArrayList<String>list = ctrDes.carrega();
        if(list != null || !list.isEmpty())
        {
            cpFonteTexto.setValue(Color.web(list.get(7)));
            cpFoco.setValue(Color.web(list.get(8)));
            slFonteTexto.setValue(Double.parseDouble(list.get(9)));
        }
    }

    private void carregaBotoes()
    {
        ArrayList<String>list = ctrDes.carrega();
        if(list != null || !list.isEmpty())
        {
            if(list.get(3).equals("transparent"))
                rbFundoBotao.setSelected(false);
            else 
                rbFundoBotao.setSelected(true);
            cpFundoBotao.setValue(Color.web(list.get(3)));
            cpPreenchimento.setValue(Color.web(list.get(4)));
            cpFonteBotao.setValue(Color.web(list.get(5)));
            slFonteBotao.setValue(Double.parseDouble(list.get(6)));
        }
    }

    private void carregaInterface()
    {
        ArrayList<String>list = ctrDes.carrega();
        if(list != null || !list.isEmpty())
        {
            cpFundo1.setValue(Color.web(list.get(0)));
            cpFundo2.setValue(Color.web(list.get(1)));
            cpFonte.setValue(Color.web(list.get(2)));
            slOpacidade.setValue(Double.parseDouble(list.get(10)));
        }
    }
}
