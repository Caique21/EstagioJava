package estagio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXDecorator;
import estagio.controladores.ctrParametrizacao;
import estagio.utilidades.Banco;
import estagio.utilidades.Utils;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class Estagio extends Application
{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root;
        Utils.carregaDesign();
        if(ctrParametrizacao.instancia().carrega() != null)
        {
            root = FXMLLoader.load(getClass().getResource("/estagio/TelaLogin.fxml"));
            stage.setTitle("Login");
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/configuracao/TelaParametrizacao.fxml"));     
            root = (Parent) fxmlLoader.load();
            
        }
        JFXDecorator decorator = new JFXDecorator(stage , root);
        if(!Utils.getFundoPrimaria().equals("") && !Utils.getFundoPrimaria().equals(""))
            decorator.setStyle("-fx-decorator-color: " + Utils.getFundoPrimaria() + ";-fx-background-color:" + Utils.getFundoPrimaria());
        else
            decorator.setStyle("-fx-decorator-color:black;-fx-background-color:white;");

        Scene scene = new Scene(decorator);
        //File file = new File("/estagio/utilidades/CSS/Style.css");
        //URL url = file.toURI().toURL();
        //scene.getStylesheets().add(url.toExternalForm());

        stage.setTitle("Nome");
        stage.setAlwaysOnTop(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if (!Banco.conectar())
        {
            JOptionPane.showMessageDialog(null, "Erro: " + Banco.getCon().getMensagemErro());
            if (JOptionPane.showConfirmDialog(null, "Deseja criar uma base de dados?") == JOptionPane.YES_OPTION)
            {
                if (!Banco.criarBD("estagio"))
                {
                    JOptionPane.showMessageDialog(null, "Erro ao criar banco: " + Banco.getCon().getMensagemErro());
                    System.exit(-1);
                } 
                else
                {
                    Banco.realizaRestaure("bkp\\restaurar.bat");
                    if (!Banco.conectar())
                    {
                        JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco");
                        System.exit(-1);
                    }
                }
            } 
            else
                System.exit(-1);
        }
        launch(args);
    }
    
}
