package estagio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrAcesso;
import estagio.controladores.ctrParametrizacao;
import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class TelaPrincipalController implements Initializable
{
    public static Objeto usuario_logado;
    public static Timestamp data_login;
    public static boolean alteracoes = false;
    private Tooltip tooltip;
    Thread timerThread;
    private final ctrAcesso ctr_acessos = ctrAcesso.instancia();
    private final ctrParametrizacao ctr_para = ctrParametrizacao.instancia();
    public static Image logo;
    
    @FXML
    private BorderPane painelPrincipal;
    @FXML
    private VBox painelLateral;
    @FXML
    private VBox painelCentral;
    @FXML
    private JFXButton btConfig;
    @FXML
    private Pane btHome;
    @FXML
    private Pane btGerenciamento;
    @FXML
    private Pane btTransporte;
    @FXML
    private Pane btFinancas;
    @FXML
    private Pane btRelatorios;
    @FXML
    private Pane btLogout;
    @FXML
    private Label lbFantasia;
    @FXML
    private Label lbHome;
    @FXML
    private Label lbGerencimento;
    @FXML
    private Label lbTransporte;
    @FXML
    private Label lbFinancas;
    @FXML
    private Label lbRelatorios;
    @FXML
    private Label lbLogout;
    @FXML
    private FontAwesomeIconView faHome;
    @FXML
    private FontAwesomeIconView faCogs;
    @FXML
    private FontAwesomeIconView faTruck;
    @FXML
    private FontAwesomeIconView faMoney;
    @FXML
    private FontAwesomeIconView faReport;
    @FXML
    private FontAwesomeIconView faLogout;
    @FXML
    private FontAwesomeIconView faCog;
    @FXML
    private Label lbUsuario;
    @FXML
    private Label lbData;
    @FXML
    private Label lbHora;
    @FXML
    private ImageView imgLogo;
    
    private void setDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(painelCentral);
        nodes.add(painelPrincipal);
        nodes.add(painelLateral);
        
        nodes.add(btConfig);
        
        nodes.add(lbFantasia);
        nodes.add(lbFinancas);
        nodes.add(lbGerencimento);
        nodes.add(lbHome);
        nodes.add(lbLogout);
        nodes.add(lbRelatorios);
        nodes.add(lbTransporte);
        nodes.add(lbData);
        nodes.add(lbUsuario);
        nodes.add(lbHora);
        
        nodes.add(faHome);
        nodes.add(faCog);
        nodes.add(faCogs);
        nodes.add(faLogout);
        nodes.add(faMoney);
        nodes.add(faReport);
        nodes.add(faTruck);
        
        Utils.setDesign(2,nodes);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setDesign();
        tooltip = new Tooltip();
        
        String meses[] = {"Janeiro","Feveiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro",
            "Outubro","Novembro","Dezembro",};
        
        lbData.setText(LocalDate.now().getDayOfMonth() + " de " + meses[LocalDate.now().getMonthValue() - 1]
         + " de " + LocalDate.now().getYear());
        
        inicializaHora();
        lbUsuario.setText(lbUsuario.getText() + usuario_logado.getParam2());        
        
        //VERIFICAR SE DATA É ULTIMA DO MES PARA GERAR CONTAS A PAGAR DAS DESPESAS
        
        if(ctr_acessos.firstOfDay(LocalDate.now()))
            Banco.realizaBackupNoMessage("bkp\\copiar.bat");
        
        if(!usuario_logado.getParam4().equals("alto"))
            btConfig.setDisable(true);
        
        alteracoes = false;
        if(ctr_para.carregaLogoPequeno() != null)
            atualizaLogo(SwingFXUtils.toFXImage(ctr_para.carregaLogoPequeno(),null));
        lbFantasia.setText(ctr_para.carregaFantasia());
    }    

    private void inicializaHora()
    {
        timerThread = new Thread(() ->
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            while (true)
            {
                try
                {
                    Thread.sleep(1000); //1 second
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() ->
                {
                    lbHora.setText(time);
                });
            }
        });   timerThread.start();//start the thread and its ok
    }

    private void atualizaLogo(Image imagem)
    {
        if(imgLogo != null && imagem != null)
        {
            imgLogo.setImage(imagem);
            logo = imagem;
        }
    }

    @FXML
    private void clickConfig(ActionEvent event)
    {
        try
        {
            Stage stage = (Stage) painelCentral.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/configuracao/TelaConfiguracoes.fxml"));
            painelCentral.getChildren().clear();
            painelCentral.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }

    @FXML
    private void clickHome(MouseEvent event)
    {
        
    }

    @FXML
    private void clickGerenciamento(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) painelCentral.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/TelaGerenciamento.fxml"));
            painelCentral.getChildren().clear();
            painelCentral.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }

    @FXML
    private void clickTransporte(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) painelCentral.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/fundamentais/TelaTransporte.fxml"));
            painelCentral.getChildren().clear();
            painelCentral.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }

    @FXML
    private void clickFinancas(MouseEvent event)
    {
        try
        {
            Stage stage = (Stage) painelCentral.getScene().getWindow();
            stage.setResizable(false);

            Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/TelaFinancas.fxml"));
            painelCentral.getChildren().clear();
            painelCentral.getChildren().add(root);
        }
        catch (IOException er)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Dashboard!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
            System.out.println(er.getMessage());
        }
    }

    @FXML
    private void clickRelatorios(MouseEvent event)
    {
    }

    @FXML
    private void clickLogout(MouseEvent event)
    {
    }

    @FXML
    private void exitHome(MouseEvent event)
    {
        btHome.setStyle(btHome.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void enterHome(MouseEvent event)
    {
        btHome.setStyle(btHome.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void gerenciamentoExit(MouseEvent event)
    {
        btGerenciamento.setStyle(btGerenciamento.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void gerenciamentoEnter(MouseEvent event)
    {
        btGerenciamento.setStyle(btGerenciamento.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void transporteExit(MouseEvent event)
    {
        btTransporte.setStyle(btTransporte.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void transporteEnter(MouseEvent event)
    {
        btTransporte.setStyle(btTransporte.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void financasExit(MouseEvent event)
    {
        btFinancas.setStyle(btFinancas.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void financasEnter(MouseEvent event)
    {
        btFinancas.setStyle(btFinancas.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void relatoriosExit(MouseEvent event)
    {
        btRelatorios.setStyle(btRelatorios.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void relatoriosEnter(MouseEvent event)
    {
        btRelatorios.setStyle(btRelatorios.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void logoutExit(MouseEvent event)
    {
        btLogout.setStyle(btLogout.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void logoutEnter(MouseEvent event)
    {
        btLogout.setStyle(btLogout.getStyle() + ";-fx-cursor: hand;");
    }

    @FXML
    private void configExit(MouseEvent event)
    {
        btConfig.setStyle(btConfig.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void configEnter(MouseEvent event)
    {
        btConfig.setStyle(btConfig.getStyle() + ";-fx-cursor: hand;");
    }
    
}
