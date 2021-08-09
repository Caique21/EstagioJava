package estagio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class Estagio extends Application
{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/TelaPrincipal.fxml"));     
        Parent root = (Parent) fxmlLoader.load();
        stage.setTitle("Parametrização");
        JFXDecorator decorator = new JFXDecorator(stage , root);
        decorator.setStyle("-fx-decorator-color: #040921;");
        decorator.setMaximized(true);
        decorator.setCustomMaximize(true);  

        Scene scene = new Scene(decorator);

        stage.setMaximized(true);
        stage.setTitle("Oficina");
        stage.setAlwaysOnTop(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
