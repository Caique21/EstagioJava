    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrDespesa;
import estagio.controladores.ctrPagamento;
import estagio.utilidades.Banco;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastroDespesaController implements Initializable
{
    private int acao;
    private Objeto despesa;
    private final Tooltip tooltip = new Tooltip();
    
    private final ctrDespesa ctrDesp = ctrDespesa.instancia();
    private final ctrPagamento ctrPag = ctrPagamento.instancia();

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneInfo;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbInfo;
    @FXML
    private Label lbData;
    @FXML
    private Label lbPesquisa;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfValor;
    @FXML
    private JFXDatePicker dpData;
    @FXML
    private JFXTextArea taDescricao;
    @FXML
    private JFXTextField tfPesquisa;
    @FXML
    private JFXRadioButton rbFixo;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faEdit;
    @FXML
    private FontAwesomeIconView faTrash;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private FontAwesomeIconView faSearch;
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
    @FXML
    private TableView<Objeto> tvDespesa;
    @FXML
    private TableColumn<Objeto,String> tcDespesa;
    @FXML
    private TableColumn<Objeto,String> tcFixo;
    @FXML
    private TableColumn<Objeto,String> tcValor;
    @FXML
    private TableColumn<Objeto,String> tcVencimento;
    @FXML
    private TableColumn<Objeto,String> tcDescricao;
    @FXML
    private Label lbErroDespesa;
    @FXML
    private Label lbErroValor;
    @FXML
    private Label lbErroData;
    @FXML
    private VBox painelCentral;

    /**
     * Initializes the controller class.
     */
    private void inicializaLabels()
    {
        lbErroData.setText("");
        lbErroDespesa.setText("");
        lbErroValor.setText("");
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6,Boolean b7)
    {
        if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            paneInfo.setDisable(true);
            btNovo.setDisable(true);
            btAlterar.setDisable(true);
            btRemover.setDisable(true);
        }
        else
        {
            paneInfo.setDisable(b1);
            btNovo.setDisable(b3);
            btAlterar.setDisable(b5);
            btRemover.setDisable(b6);
        }
        panePesquisa.setDisable(b2);
        btConfirmar.setDisable(b4);
        btCancelar.setDisable(b7);
    }
    
    private void limparCampos()
    {
        tfPesquisa.clear();
        tfValor.clear();
        taDescricao.clear();;
        tfNome.clear();
        dpData.getEditor().clear();
    }
    
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneInfo);
        nodes.add(panePesquisa);
        
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btNovo);
        nodes.add(btPesquisar);
        nodes.add(btRemover);
        
        nodes.add(tfPesquisa);
        nodes.add(tfValor);
        nodes.add(tfNome);
        
        nodes.add(lbData);
        nodes.add(lbInfo);
        nodes.add(lbPesquisa);
        nodes.add(lbTitulo);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faSearch);
        nodes.add(faTrash);
        
        nodes.add(taDescricao);
        
        nodes.add(rbFixo);
        
        Utils.setDesign(1, nodes);
        btNovo.setStyle(btNovo.getStyle() + ";-fx-cursor: default;");
        btAlterar.setStyle(btAlterar.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
        btRemover.setStyle(btRemover.getStyle() + ";-fx-cursor: default;");
    }
    
    private void redimensiona()
    {
        ////////Tamanho da Tela
        panePrincipal.setPrefWidth(TelaPrincipalController.screenBounds.getMaxX() - 200);
        panePrincipal.setPrefHeight(TelaPrincipalController.screenBounds.getMaxY() - 80);
        
        //PAINEL CENTRAL TIRANDO TITULO E BOTÕES DE COMANDOS
        painelCentral.setPrefHeight(panePrincipal.getPrefHeight() - 47 - 45);
        //PAINEL DE PESQUISA FICA COM O RESTANTE DA TELA
        panePesquisa.setPrefHeight(painelCentral.getPrefHeight()- paneInfo.getPrefHeight() - 10);
        
        tvDespesa.setPrefHeight(panePesquisa.getPrefHeight() - 142);
        tvDespesa.setPrefWidth(panePesquisa.getPrefWidth() - 80);
        
        //REORGANIZA OS TAMANHOS DAS COLUNAS DA TABELA
        int sobra = (int)((tvDespesa.getPrefWidth() - 1026)/2);
        tcDespesa.setPrefWidth(tcDespesa.getPrefWidth() + sobra);
        tcDescricao.setPrefWidth(tcDescricao.getPrefWidth() + sobra);
    }
    
    private void inicializa()
    {
        acao = -1;
        despesa = null;
        //clickPesquisar(new ActionEvent());
        limparCampos();
        inicializaLabels();
        setEstado(true, false, false, true, true, true, true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesign();
        redimensiona();
        
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcDespesa.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcFixo.setCellValueFactory(new PropertyValueFactory<>("param6"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcVencimento.setCellValueFactory(new PropertyValueFactory<>("param8"));
        
        MaskFieldUtil.monetaryField(tfValor);
        
        inicializa();
        //setListeners();
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        pagarDespesa();
        inicializa();
        acao = 0;
        setEstado(false, true, true, false, true, true, false);
        tfNome.requestFocus();
    }

    @FXML
    private void clickConfimar(ActionEvent event)
    {
        if(valida())
        {
            Alert alerta;
            if(acao == 0)
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cadastrar despesa " + tfNome.getText() + 
                    "?", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrDesp.salvar(tfNome, rbFixo, tfValor, dpData, taDescricao))
                    {
                        if(dpData.getValue().compareTo(LocalDate.now()) < 0)
                        {
                            alerta.setContentText("Despesa vencida, deseja pagar?");
                            alerta.showAndWait();
                            
                            if(alerta.getResult() == ButtonType.YES)
                                pagarDespesa();
                        }
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Despesa cadastrada com sucesso!!!", 
                                ButtonType.OK);
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro no cadastro da despesa", ButtonType.OK);
                    alerta.showAndWait();
                }
            }
            else if(acao == 1 && despesa != null)
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar despesa " + tfNome.getText() + 
                    "?", ButtonType.YES,ButtonType.NO);
                
                boolean paga = ctrPag.wasPaid(Integer.parseInt(despesa.getParam1()));
                
                if(paga)
                    alerta.setContentText(alerta.getContentText() + "\nDespesa já foi paga, altera-la poderá gerar"
                        + " estorno");
                
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrDesp.alterar(Integer.parseInt(despesa.getParam1()), tfNome, rbFixo, tfValor, dpData, 
                            taDescricao))
                    {
                        if(paga)
                            ctrPag.estornarDespesa(Integer.parseInt(despesa.getParam1()));
                        
                        if(dpData.getValue().compareTo(LocalDate.now()) < 0)
                        {
                            alerta.setContentText("Despesa vencida, deseja pagar?");
                            alerta.showAndWait();
                            
                            if(alerta.getResult() == ButtonType.YES)
                                pagarDespesa();
                        }
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Despesa cadastrada com sucesso!!!", 
                                ButtonType.OK);
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro no cadastro da despesa", ButtonType.OK);
                    alerta.showAndWait();
                }
            }
        }
    }

    private boolean valida()
    {
        String erros = "";
        inicializaLabels();
        
        if(tfNome.getText().trim().equals(""))
        {
            erros += "Digite o nome da despesa\n";
            lbErroDespesa.setText("Campo requerido");
        }
        
        if(tfValor.getText().trim().equals(""))
        {
            erros += "Digite o valor da despesa\n";
            lbErroValor.setText("Campo requerido");
        }
        else if(Double.parseDouble(tfValor.getText().replace(".", "").replace(",", ".")) < 0)
        {
            erros += "Digite um valor válido\n";
            lbErroValor.setText("Valor inválido");
        }
        
        if(dpData.getEditor().getText().trim().equals(""))
        {
            erros += "Selecione uma data\n";
            lbErroData.setText("Selecione uma data");
        }
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        
        return erros.trim().equals("");
    }

    private void pagarDespesa()
    {
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
        dialog.setTitle("Pagar Despesa");
        dialog.setHeaderText("Digite as informações do pagamento da despesa " + tfNome.getText());
        dialog.getDialogPane().setPrefWidth(500);

        VBox vbox = new VBox();
        vbox.setPrefWidth(500);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);
        
        TextField valor = new TextField();
        valor.setPromptText("Valor á pagar(*)");
        MaskFieldUtil.monetaryField(valor);
        valor.setText(tfValor.getText());
        
        TextField outro = new TextField();
        outro.setVisible(false);
        
        HBox hbox = new HBox(10);
        
        ComboBox forma_pagamento = new ComboBox(), parcelas = new ComboBox();
        forma_pagamento.getItems().addAll("Cartão","Cheque","Dinheiro","Pix","Transferência");
        parcelas.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        forma_pagamento.getSelectionModel().select(0);
        parcelas.getSelectionModel().select(0);
        forma_pagamento.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue.equals("Cheque") || newValue.equals("Transferência"))
            {
                if(newValue.equals("Cheque"))
                    outro.setPromptText("Número do Cheque");
                else
                    outro.setPromptText("Informações da Transferência");
                outro.setVisible(true);
            }
            else
                outro.setVisible(false);
            
            if(newValue.equals("Cartão"))
                parcelas.setVisible(true);
            else
                parcelas.setVisible(false);
        });
        
        hbox.getChildren().addAll(forma_pagamento,parcelas);
        vbox.getChildren().addAll(valor,hbox,outro);
        dialog.getDialogPane().setContent(vbox);
        
        dialog.setResultConverter(dialogButton -> 
        {
            if (dialogButton == ButtonType.YES && !valor.getText().trim().equals(""))
            {
                //double val = Double.parseDouble(tfValor.getText().replace(".", "").replace(",", "."));
                double val = Double.parseDouble(valor.getText().replace(".", "").replace(",", "."));
                double val_pago = Double.parseDouble(valor.getText().replace(".", "").replace(",", "."));
                if(val_pago > 0 && val_pago <= val)
                {
                    if(forma_pagamento.getSelectionModel().getSelectedItem().equals("Cheque") && 
                        outro.getText().trim().equals(""))
                        return null;
                    if(parcelas.isVisible())
                        return valor.getText() + ":" + forma_pagamento.getSelectionModel().getSelectedItem() + 
                            ":" + outro.getText() + ":" + parcelas.getSelectionModel().getSelectedItem();
                    return valor.getText() + ":" + forma_pagamento.getSelectionModel().getSelectedItem() + 
                            ":" + outro.getText();
                }
            }
            return null;
        });
        Optional<String> result = dialog.showAndWait();
        
        if(result.isPresent())
        {
            String aux[] = result.get().split(":");
            if(aux.length == 4)
                aux[2] = aux[3];
            
            if(ctrPag.pagarDespesa(LocalDate.now(),aux[0],aux[1],aux[2]))
                Notifications.create()
                    .darkStyle()
                    //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                    .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                    .text("Despesa paga com sucesso!!!")
                    .showInformation();
            else
                Notifications.create()
                    .darkStyle()
                    //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                    .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                    .text("Erro no pagamento da despesa")
                    .showError();
        }
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        if(!tvDespesa.getItems().isEmpty() && tvDespesa.getSelectionModel().getSelectedIndex() >= 0)
        {
            acao = 1;
            setEstado(false, true, true, false, true, true, false);
            despesa = tvDespesa.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        if(!tvDespesa.getItems().isEmpty() && tvDespesa.getSelectionModel().getSelectedIndex() >= 0)
        {
            Alert al = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover despesa " + 
                 tvDespesa.getSelectionModel().getSelectedItem().getParam2() + "?", ButtonType.YES,ButtonType.NO);
            al.showAndWait();
            
            if(al.getResult() == ButtonType.YES)
            {
                if(ctrDesp.apagar(Integer.parseInt(tvDespesa.getSelectionModel().getSelectedItem().getParam1())))
                {
                    inicializa();
                    al = new Alert(Alert.AlertType.INFORMATION, "Despesa cadastrada com sucesso!!", ButtonType.OK);
                }
                else
                    al = new Alert(Alert.AlertType.ERROR, "Erro no cadastramento da despesa\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK);
                al.showAndWait();
            }
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        inicializa();
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        tvDespesa.setItems(FXCollections.observableArrayList(ctrDesp.getByNome(tfPesquisa.getText())));
    }

    @FXML
    private void selecionaDespesa(MouseEvent event)
    {
        if(!tvDespesa.getItems().isEmpty() && tvDespesa.getSelectionModel().getSelectedIndex() >= 0)
        {
            setEstado(true, false, false, true, false, false, false);
            fillFields(tvDespesa.getSelectionModel().getSelectedItem());
        }
    }

    private void fillFields(Objeto desp)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @FXML
    private void novoExit(MouseEvent event)
    {
    }

    @FXML
    private void novoEnter(MouseEvent event)
    {
    }

    @FXML
    private void confirmarExit(MouseEvent event)
    {
    }

    @FXML
    private void confirmarEnter(MouseEvent event)
    {
    }

    @FXML
    private void alterarExit(MouseEvent event)
    {
    }

    @FXML
    private void alterarEnter(MouseEvent event)
    {
    }

    @FXML
    private void removerExit(MouseEvent event)
    {
    }

    @FXML
    private void removerEnter(MouseEvent event)
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

    @FXML
    private void pesquisarExit(MouseEvent event)
    {
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
    {
    }
}
