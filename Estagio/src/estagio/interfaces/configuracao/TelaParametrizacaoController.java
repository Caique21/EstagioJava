/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.configuracao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXTextField;
import estagio.TelaLoginController;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrDesign;
import estagio.controladores.ctrParametrizacao;
import estagio.utilidades.Banco;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import static json.Json.consultaCep;
import org.controlsfx.control.Notifications;
import org.json.JSONObject;
import estagio.utilidades.ToolTip;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Separator;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaParametrizacaoController implements Initializable
{
    private final ctrParametrizacao ctrPara = ctrParametrizacao.instancia();
    private final ctrDesign ctrDes = ctrDesign.instancia();
    private String caminhoGrande;
    private String caminhoPequeno;
    Tooltip tooltip = new Tooltip();
    private boolean existe;

    @FXML
    private Pane painel_central;
    @FXML
    private ImageView imgGrande;
    @FXML
    private JFXButton btCarregarGrande;
    @FXML
    private ImageView imgPequeno;
    @FXML
    private JFXButton btCarregarPequeno;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private Label lbErroNome;
    @FXML
    private JFXTextField tfFantasia;
    @FXML
    private Label lbErroFantasia;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private Label lbErroEmail;
    @FXML
    private JFXTextField tfRazao;
    @FXML
    private Label lbErroRazao;
    @FXML
    private Pane paneEndereco;
    @FXML
    private JFXTextField tfCEP;
    @FXML
    private JFXButton btCEP;
    @FXML
    private Label lbErroCep;
    @FXML
    private JFXTextField tfRua;
    @FXML
    private Label lbErroRua;
    @FXML
    private JFXTextField tfNumero;
    @FXML
    private Label lbErroNumero;
    @FXML
    private JFXTextField tfBairro;
    @FXML
    private Label lbErroBairro;
    @FXML
    private JFXTextField tfComplemento;
    @FXML
    private Label lbErroComplemento;
    @FXML
    private JFXTextField tfCidade;
    @FXML
    private Label lbErroCidade;
    @FXML
    private JFXComboBox<String> cbEstado;
    @FXML
    private ListView<String> lvEnderecos;
    @FXML
    private JFXButton btAddEndereco;
    @FXML
    private JFXButton btAlterarEndereco;
    @FXML
    private JFXButton btDelEndereco;
    @FXML
    private Pane paneTelefones;
    @FXML
    private ListView<String> lvTelefones;
    @FXML
    private JFXTextField tfTelefone;
    @FXML
    private JFXButton btAddTelefone;
    @FXML
    private JFXButton btDelTelefone;
    @FXML
    private JFXButton btSalvar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private Label lbLogoGrande;
    @FXML
    private Label lbLogoPequeno;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbTelefone;
    @FXML
    private Label lbEndereco;
    @FXML
    private Separator sepGrande1;
    @FXML
    private Separator sepGrande2;
    @FXML
    private Separator sepGrande3;
    @FXML
    private Separator sepGrande4;
    @FXML
    private Separator sepPequeno1;
    @FXML
    private Separator sepPequeno2;
    @FXML
    private Separator sepPequeno3;
    @FXML
    private Separator sepPequeno4;

    /**
     * Initializes the controller class.
     */
    private void inicializaLabels()
    {
        lbErroEmail.setText("");
        lbErroFantasia.setText("");
        lbErroNome.setText("");
        lbErroNumero.setText("");
        lbErroRazao.setText("");
        lbErroBairro.setText("");
        lbErroComplemento.setText("");
        lbErroCep.setText("");
        lbErroCidade.setText("");
        lbErroNumero.setText("");
        lbErroRua.setText("");
    }
    
    private void limpaEndereco()
    {
        tfBairro.clear();
        tfComplemento.clear();
        tfCEP.clear();
        tfCidade.clear();
        tfNumero.clear();
        tfRua.clear();
        cbEstado.getSelectionModel().select(0);
    }
    
    private void setMascaras()
    {
        MaskFieldUtil.cepField(tfCEP);
        MaskFieldUtil.numericField(tfNumero);
        MaskFieldUtil.foneField(tfTelefone);
    }
    
    private void setListeners()
    {
        tfEmail.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            if(tfEmail.getText().length() >= 10 && !Utils.validaEmail(tfEmail.getText()))
                lbErroEmail.setText("Formato de email inválido");
            else
                lbErroEmail.setText("");
        });
        
        tfCEP.setOnKeyPressed(e ->
        {
            if(e.getCode() == KeyCode.ENTER)
                clickPesquisarCep(new ActionEvent());
        });
    }
    
    private void carregaDesign()
    {
        ArrayList<String>list = ctrDes.carrega();
        if(list != null && !list.isEmpty())
        {
            Utils.carregaDesign();
        
            List<Node>nodes = new ArrayList<>();
            nodes.add(painel_central);
            nodes.add(paneEndereco);
            nodes.add(paneTelefones);
            
            nodes.add(lbTitulo);
            nodes.add(lbTelefone);
            nodes.add(lbTelefone);
            nodes.add(lbLogoPequeno);
            nodes.add(lbLogoGrande);
            nodes.add(lbEndereco);
            
            nodes.add(tfBairro);
            nodes.add(tfCEP);
            nodes.add(tfCidade);
            nodes.add(tfComplemento);
            nodes.add(tfEmail);
            nodes.add(tfFantasia);
            nodes.add(tfNome);
            nodes.add(tfNumero);
            nodes.add(tfRazao);
            nodes.add(tfRua);
            nodes.add(tfTelefone);
                
            nodes.add(btAddEndereco);
            nodes.add(btAddTelefone);
            nodes.add(btAlterarEndereco);
            nodes.add(btCEP);
            nodes.add(btCancelar);
            nodes.add(btCarregarGrande);
            nodes.add(btCarregarPequeno);
            nodes.add(btDelEndereco);
            nodes.add(btDelTelefone);
            nodes.add(btSalvar);
            
            nodes.add(cbEstado);
            
            nodes.add(lvEnderecos);
            nodes.add(lvTelefones);
            Utils.setDesign(1, nodes);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cbEstado.getItems().addAll("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", 
            "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO");
        inicializaLabels();
        carregaDesign();
        setListeners();
        setMascaras();
        carregarParametrizacao();
        
        if(tfNome.getText().equals(""))
        {
            Platform.runLater(() ->
            {
                tfNome.requestFocus();
            });
        }
        cbEstado.getSelectionModel().select(-1);
    }  

    private void carregarParametrizacao()
    {
        Objeto para = ctrPara.carrega();
        if(para == null)
        {
            existe = false;
            btSalvar.setText("SALVAR");
        }
        else
        {
            existe = true;
            btSalvar.setText("ALTERAR");
            tfNome.setText(para.getParam1());
            tfFantasia.setText(para.getParam2());
            tfEmail.setText(para.getParam3());
            tfRazao.setText(para.getParam4());
            ArrayList<String>telefones = ctrPara.carregaTelefones(para.getParam1());
            
            for(String telefone : telefones)
            {
                tfTelefone.setText(telefone);
                clickAddTelefone(new ActionEvent());
            }
            
            for(int i = 0; i < para.getList1().size(); i++)
            {
                //(rua) (numero), (bairro) - (complemento);(cidade) - (estado) - CEP (cep)
                String endereco = para.getList1().get(i).getParam2() + " " + para.getList1().get(i).getParam3()
                    + ", " + para.getList1().get(i).getParam4();
                if(para.getList1().get(i).getParam5() != null && !para.getList1().get(i).getParam5().equals(""))
                    endereco += " - " + para.getList1().get(i).getParam5();
                endereco += ";"  + para.getList1().get(i).getParam6() + " - " + para.getList1().get(i).getParam7()
                    + " - " + para.getList1().get(i).getParam1();

                lvEnderecos.getItems().add(endereco);
                limpaEndereco();
            }
            
            if(para.getParam5() != null)
            {
                imgGrande.setImage(SwingFXUtils.toFXImage(ctrPara.carregaLogoGrande(), null));
                Utils.centerImage(imgGrande);
                setSeparator(sepGrande1,sepGrande2,sepGrande3,sepGrande4,false);
            }
            else
                setSeparator(sepGrande1,sepGrande2,sepGrande3,sepGrande4,true);
            
            if(para.getParam6() != null)
            {
                imgPequeno.setImage(SwingFXUtils.toFXImage(ctrPara.carregaLogoPequeno(), null));
                Utils.centerImage(imgPequeno);
                setSeparator(sepPequeno1,sepPequeno2,sepPequeno3,sepPequeno4,false);
            }
            else
                setSeparator(sepPequeno1,sepPequeno2,sepPequeno3,sepPequeno4,true);
            
            Notifications.create()
                .darkStyle()
                //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                .text("Carregamento dos dados da Parametrização concluído com sucesso")
                .showInformation();
        }
    }  

    @FXML
    private void clickCarregarGrande(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if(file != null)
        {
            caminhoGrande = file.getAbsolutePath();
            Image image1 = new Image(file.toURI().toString());
            imgGrande.setImage(image1);
            Utils.centerImage(imgGrande);
            setSeparator(sepGrande1, sepGrande2, sepGrande3, sepGrande4, false);
        }
        else
        {
            caminhoGrande = "";
            setSeparator(sepGrande1, sepGrande2, sepGrande3, sepGrande4, false);
        }
    }

    @FXML
    private void clickCarregarPequeno(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if(file != null)
        {
            caminhoPequeno = file.getAbsolutePath();
            Image image1 = new Image(file.toURI().toString());
            imgPequeno.setImage(image1);
            Utils.centerImage(imgPequeno);
            setSeparator(sepPequeno1,sepPequeno2,sepPequeno3,sepPequeno4,false);
        }
        else
        {
            caminhoPequeno = "";
            setSeparator(sepPequeno1,sepPequeno2,sepPequeno3,sepPequeno4,true);
        }
    }

    @FXML
    private void clickSalvar(ActionEvent event)
    {
        if(validarParametrizacao())
        {
            Alert a;
            if(!existe)
            {
                a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja salvar os dados da Parametrização", 
                        ButtonType.YES,ButtonType.NO);
                a.showAndWait();
                
                if(a.getResult() == ButtonType.YES)
                {
                    if(ctrPara.salvar(tfNome,tfFantasia,tfEmail,tfRazao,lvEnderecos,lvTelefones,caminhoGrande,caminhoPequeno))
                    {
                        new Alert(Alert.AlertType.INFORMATION, "Parametrização salva com sucesso!!", 
                            ButtonType.OK).showAndWait();
                        abrirDesing();
                    }
                    else
                        new Alert(Alert.AlertType.ERROR, "Erro no salvamento da parametrização", 
                            ButtonType.OK).showAndWait();
                }
            }
            else
            {
                a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar os dados da Parametrização", 
                        ButtonType.YES,ButtonType.NO);
                a.showAndWait();
                
                if(a.getResult() == ButtonType.YES)
                {
                    if(ctrPara.alterar(tfNome,tfFantasia,tfEmail,tfRazao,lvEnderecos,lvTelefones,caminhoGrande,caminhoPequeno))
                    {
                         new Alert(Alert.AlertType.INFORMATION, "Parametrização salva com sucesso!!", 
                            ButtonType.OK).showAndWait();
                        reload();
                    }
                    else
                        new Alert(Alert.AlertType.ERROR, "Erro no salvamento da parametrização", 
                            ButtonType.OK).showAndWait();
                }
            }
        }
    }

    private boolean validarParametrizacao()
    {
        inicializaLabels();
        boolean flag = true;
        String erros = "";
        
        if(tfNome.getText().replace(" ", "").equals(""))
        {
            flag = false;
            erros += "Nome da empresa inválido, não preenchido\n";
            lbErroNome.setText("Nome inválido");
        }
        
        if(tfFantasia.getText().replace(" ", "").equals(""))
        {
            flag = false;
            erros += "Nome Fantasia da empresa inválido, não preenchido\n";
            lbErroFantasia.setText("Nome Fantasia inválido");
        }
        
        if(!Utils.validaEmail(tfEmail.getText()))
        {
            flag = false;
            erros += "Email inválido\n";
            lbErroEmail.setText("Email inválido");
        }
        
        if(tfRazao.getText().replace(" ", "").equals(""))
        {
            flag = false;
            erros += "Razão Social da empresa inválido, não preenchido\n";
            lbErroRazao.setText("Razão Social inválido");
        }
        
        if(lvEnderecos.getItems().isEmpty())
        {
            flag = false;
            erros += "Nenhum endereço cadastrado\n";
        }
        
        if(lvTelefones.getItems().isEmpty())
        {
            flag = false;
            erros += "Nenhum telefone/celular para contato cadastrado\n";
        }
        
        if(imgGrande.getImage() == null || imgPequeno.getImage() == null)
        {
            Alert a = new Alert(Alert.AlertType.WARNING, "Logo(s) grande e/ou pequeno não carregadas, "
                    + "deseja continuar", ButtonType.YES,ButtonType.NO);
            a.setTitle("Aviso");
            a.showAndWait();
            
            if(a.getResult() == ButtonType.NO)
                flag = false;
        }
        
        if(!flag && !erros.equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        
        return flag;
    }

    private void abrirDesing()
    {
        Parent root;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/configuracao/TelaDesign.fxml"));     
            root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            TelaDesignController.bt = painel_central;
            
            JFXDecorator decorator = new JFXDecorator(stage , root);
            decorator.setStyle("-fx-decorator-color: #040921;");

            Scene scene = new Scene(decorator);
            scene.setFill(Paint.valueOf("black"));

            stage.setScene(scene);
            stage.setTitle("Design");

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

            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("Oficina");
            stage.setAlwaysOnTop(false);
            stage.setOnCloseRequest((WindowEvent event) ->
            {
                if(TelaPrincipalController.alteracoes)
                    Banco.realizaBackupNoMessage("bkp\\copiar.bat");
                Platform.exit();
                System.exit(0);
                try
                {
                    Process p = Runtime.getRuntime().exec("Upload.bat");
                }
                catch (IOException ex)
                {
                    Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            Stage login = (Stage) btCancelar.getScene().getWindow();
            login.close();
            
            stage.show();
        }
        catch (IOException ex)
        {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            ex.getMessage();
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cancelar e sair?", ButtonType.YES,ButtonType.NO);
        alerta.showAndWait();
        
        if(alerta.getResult() == ButtonType.YES)
        {
            try
            {
                Stage stage = (Stage) painel_central.getScene().getWindow();
                stage.setResizable(false);

                Parent root = FXMLLoader.load(getClass().getResource("/estagio/interfaces/configuracao/TelaConfiguracao.fxml"));
                painel_central.getChildren().clear();
                painel_central.getChildren().add(root);
            }
            catch (IOException er)
            {
                Alert a = new Alert(Alert.AlertType.ERROR, "Impossível abrir tela de Configuração!\nUm erro inesperado aconteceu!\nErro: " + er.getMessage(), ButtonType.OK);
                a.showAndWait();
                System.out.println(er.getMessage());
            }
        }
    }

    @FXML
    private void clickPesquisarCep(ActionEvent event)
    {
        String str = consultaCep(tfCEP.getText().replace("-", ""), "json");
        JSONObject my_obj = new JSONObject(str);
        tfRua.setText(my_obj.getString("logradouro"));
        tfCidade.setText(my_obj.getString("cidade"));
        tfBairro.setText(my_obj.getString("bairro"));
        cbEstado.getSelectionModel().select(my_obj.getString("uf"));
    }

    @FXML
    private void clickAddEndereco(ActionEvent event)
    {
        if(validaEndereco())
        {
            //(rua) (numero), (bairro) - (complemento);(cidade) - (estado) - CEP (cep)
            String endereco;
            if(!tfComplemento.getText().equals(""))
            endereco = tfRua.getText() + " " + tfNumero.getText() + ", " + tfBairro.getText() + " - " + 
                tfComplemento.getText() + "; " + tfCidade.getText() + " - " + 
                    cbEstado.getSelectionModel().getSelectedItem() + " - " + tfCEP.getText();
            else
                endereco = tfRua.getText() + " " + tfNumero.getText() + ", " + tfBairro.getText() + "; " + 
                    tfCidade.getText() + " - " + cbEstado.getSelectionModel().getSelectedItem() + " - " + 
                        tfCEP.getText();
            
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja confirmar cadastro do endereço ?", 
                    ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();
            
            if(alerta.getResult() == ButtonType.YES)
            {
                lvEnderecos.getItems().add(endereco);
                limpaEndereco();
            }
        }
    }

    private boolean validaEndereco()
    {
        boolean flag = true;
        inicializaLabels();
        String erros = "";
        
        if(tfCEP.getText().length() != 9)
        {
            lbErroCep.setText("CEP inválido");
            erros += "CEP inválido\n";
            flag = false;
        }
        
        if(tfRua.getText().equals(""))
        {
            lbErroRua.setText("Digite nome da Rua");
            erros += "Rua inválida\n";
            flag = false;
        }
        
        if(tfNumero.getText().equals(""))
        {
            lbErroCep.setText("Digite número");
            erros += "Número inválido\n";
            flag = false;
        }
        
        if(tfBairro.getText().equals(""))
        {
            lbErroCep.setText("Digite nome do Bairro");
            erros += "Bairro inválido\n";
            flag = false;
        }
        
        if(tfCidade.getText().equals(""))
        {
            lbErroCep.setText("Digite o nome da Cidade");
            erros += "Cidade inválido\n";
            flag = false;
        }
        
        if(cbEstado.getSelectionModel().getSelectedIndex() < 0)
        {
            erros += "Selecione um Estado\n";
            flag = false;
        }
        
        if(!flag)
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        
        return flag;
    }

    @FXML
    private void clickAlterarEndereco(ActionEvent event)
    {
        Alert alerta;
        if(!lvEnderecos.getItems().isEmpty() && lvEnderecos.getSelectionModel().getSelectedIndex() >= 0)
        {
            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar endereço " + 
                lvEnderecos.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();
            
            if(alerta.getResult() == ButtonType.YES)
            {
                converteEndereco(lvEnderecos.getSelectionModel().getSelectedItem());
                lvEnderecos.getItems().remove(lvEnderecos.getSelectionModel().getSelectedIndex());
            }
        }
        else if(lvEnderecos.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Não há endereços cadastrados", ButtonType.OK).showAndWait();
        else if(lvEnderecos.getSelectionModel().getSelectedIndex() < 0)
            new Alert(Alert.AlertType.ERROR, "Selecione um endereço para remover", ButtonType.OK).showAndWait();
    }
    
    

    private void converteEndereco(String endereco)
    {
        //(rua) (numero), (bairro) - (complemento); (cidade) - (estado) - CEP (cep)
        String aux[] = endereco.split(";");
        //aux[0] == (rua) (numero), (bairro) - (complemento)
        //aux[1] == (cidade) - (estado) - CEP (cep)
        String aux2[] = aux[0].split(", ");
        //aux2[0] == (rua) (numero)
        //aux2[1] == (bairro) - (complemento)
        
        tfRua.setText(aux2[0].substring(0,aux2[0].lastIndexOf(" ")).trim());
        tfNumero.setText(aux2[0].substring(aux2[0].lastIndexOf(" ") + 1).replace(" ", "").trim());
        if(aux2[1].contains("-"))
        {
            tfBairro.setText(aux2[1].substring(0, aux[1].indexOf("-") - 1).trim());
            tfComplemento.setText(aux2[1].substring(aux[1].indexOf("-") + 1).trim());
        }
        else
            tfBairro.setText(aux2[1].trim());
        
        String aux3[] = aux[1].split(" - ");
        //(cidade) 
        //(estado) 
        //CEP (cep)
        tfCidade.setText(aux3[0]);
        tfCEP.setText(aux3[2].replace("CEP ", ""));
        cbEstado.getSelectionModel().select(aux3[1]);
    }

    @FXML
    private void clickRemoverEndereco(ActionEvent event)
    {
        Alert alerta = null;
        if(!lvEnderecos.getItems().isEmpty() && lvEnderecos.getSelectionModel().getSelectedIndex() >= 0)
        {
            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover endereço " + 
                lvEnderecos.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();
            
            if(alerta.getResult() == ButtonType.YES)
                lvEnderecos.getItems().remove(lvEnderecos.getSelectionModel().getSelectedIndex());
        }
        else if(lvEnderecos.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Não há endereços cadastrados", ButtonType.OK).showAndWait();
        else if(lvEnderecos.getSelectionModel().getSelectedIndex() < 0)
            new Alert(Alert.AlertType.ERROR, "Selecione um endereço para remover", ButtonType.OK).showAndWait();
    }

    @FXML
    private void clickAddTelefone(ActionEvent event)
    {
        if(!tfTelefone.equals("") && (tfTelefone.getText().length() == 13 || tfTelefone.getText().length() == 14))
        {
            if(!lvTelefones.getItems().contains(tfTelefone.getText()))
            {
                lvTelefones.getItems().add(tfTelefone.getText());
                tfTelefone.clear();
                tfTelefone.requestFocus();
            }
            else
                new Alert(Alert.AlertType.ERROR, "número de Telefone/Celular já existente", ButtonType.OK).
                        showAndWait();
        }
        else
            new Alert(Alert.AlertType.ERROR, "Erro, por favor digite um número de Telefone/Celular válido", 
                    ButtonType.OK).showAndWait();
    }

    @FXML
    private void clickRemoverTelefone(ActionEvent event)
    {
        Alert alerta = null;
        if(!lvTelefones.getItems().isEmpty() && lvTelefones.getSelectionModel().getSelectedIndex() >= 0)
        {
            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover número " + 
                lvTelefones.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES,ButtonType.NO);
            alerta.showAndWait();
            
            if(alerta.getResult() == ButtonType.YES)
                lvTelefones.getItems().remove(lvTelefones.getSelectionModel().getSelectedIndex());
            alerta = null;
        }
        else if(lvTelefones.getItems().isEmpty())
            alerta = new Alert(Alert.AlertType.ERROR, "Não há número a ser removido", ButtonType.OK);
        else if(lvTelefones.getSelectionModel().getSelectedIndex() < 0)
            alerta = new Alert(Alert.AlertType.ERROR, "Selecione um número para remover", ButtonType.OK);
        alerta.showAndWait();
    }

    @FXML
    private void keyPressedMain(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickSalvar(new ActionEvent());
    }

    @FXML
    private void keyPressedAddress(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickAddEndereco(new ActionEvent());
    }

    @FXML
    private void keyPressedFone(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickAddTelefone(new ActionEvent());
    }

    @FXML
    private void salvarExit(MouseEvent event)
    {
        btSalvar.setStyle(btSalvar.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void salvarEnter(MouseEvent event)
    {
        btSalvar.setStyle(btSalvar.getStyle() + ";-fx-cursor: hand;-fx-font-weight: bold;");
        tooltip.setText("Salvar Alterações da Parametrização");
        ToolTip.bindTooltip(btSalvar, tooltip);
    }

    @FXML
    private void cancelarExit(MouseEvent event)
    {
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void cancelarEnter(MouseEvent event)
    {
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: hand;-fx-font-weight: bold;");
        tooltip.setText("Cancelar Alterações da Parametrização");
        ToolTip.bindTooltip(btCancelar, tooltip);
    }

    @FXML
    private void carregarGrandeExit(MouseEvent event)
    {
        btCarregarGrande.setStyle(btCarregarGrande.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void carregarGrandeEnter(MouseEvent event)
    {
        btCarregarGrande.setStyle(btCarregarGrande.getStyle() + ";-fx-cursor: hand;");
        tooltip.setText("Carregar Logo Grande");
        ToolTip.bindTooltip(btCarregarGrande, tooltip);
    }

    @FXML
    private void carregarPesquenoExit(MouseEvent event)
    {
        btCarregarPequeno.setStyle(btCarregarPequeno.getStyle() + ";-fx-cursor: default;");
    }

    @FXML
    private void carregarPequenoEnter(MouseEvent event)
    {
        btCarregarPequeno.setStyle(btCarregarPequeno.getStyle() + ";-fx-cursor: hand;");
        tooltip.setText("Carregar Logo Pequeno");
        ToolTip.bindTooltip(btCarregarPequeno, tooltip);
    }

    private void setSeparator(Separator sep1, Separator sep2, Separator sep3, Separator sep4, boolean b)
    {
        sep1.setVisible(b);
        sep2.setVisible(b);
        sep3.setVisible(b);
        sep4.setVisible(b);
    }
}
