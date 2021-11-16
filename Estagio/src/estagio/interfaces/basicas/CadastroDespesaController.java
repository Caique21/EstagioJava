    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import estagio.utilidades.ToolTip;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private Objeto transporte = null;
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
    @FXML
    private JFXCheckBox cbRepetidas;

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
        
        if(transporte == null)
        {
            tfPesquisa.setDisable(b2);
            btPesquisar.setDisable(b2);
            rbFixo.setDisable(b1);
        }
        else
        {
            if(transporte.getParam5() != null && transporte.getParam5().equals(""))
                dpData.setDisable(true);
            else
                dpData.setDisable(false);
            tfPesquisa.setDisable(true);
            btPesquisar.setDisable(true);
            rbFixo.setDisable(true);
        }
        panePesquisa.setDisable(b2);
        btConfirmar.setDisable(b4);
        btCancelar.setDisable(b7);
    }
    
    private void limparCampos()
    {
        tfPesquisa.clear();
        tfValor.clear();
        taDescricao.clear();
        tfNome.clear();
        
        if(transporte != null && transporte.getParam5() != null && !transporte.getParam5().equals("") 
                && !transporte.getParam5().equals("null"))
            dpData.setValue(LocalDate.parse(transporte.getParam5()));
        else
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
        
        nodes.add(cbRepetidas);
        
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
        
        tvDespesa.setPrefHeight(panePesquisa.getPrefHeight() - (tvDespesa.getLayoutY() + 15));
        tvDespesa.setPrefWidth(panePesquisa.getPrefWidth() - (tvDespesa.getLayoutX() * 2));
        
        //REORGANIZA OS TAMANHOS DAS COLUNAS DA TABELA
        int sobra = (int)((tvDespesa.getPrefWidth() - 1049)/2);
        tcDespesa.setPrefWidth(tcDespesa.getPrefWidth() + sobra);
        tcDescricao.setPrefWidth(tcDescricao.getPrefWidth() + sobra);
    }
    
    private void inicializa()
    {
        acao = -1;
        despesa = null;
        clickPesquisar(new ActionEvent());
        limparCampos();
        inicializaLabels();
        setEstado(true, false, false, true, true, true, true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesign();
        redimensiona();
        
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("param6"));
        tcDespesa.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcFixo.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcVencimento.setCellValueFactory(new PropertyValueFactory<>("param8"));
        
        MaskFieldUtil.monetaryField(tfValor);
        
        inicializa();
        
        cbRepetidas.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            clickPesquisar(new ActionEvent());
        });
        
        rbFixo.setOnKeyPressed((event) ->
        {
            if(event.getCode() == KeyCode.ENTER)
            {
                if(rbFixo.isSelected())
                    rbFixo.setSelected(false);
                else
                    rbFixo.setSelected(true);
                tfValor.requestFocus();
            }
        });
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
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
                    boolean result;
                    
                    if(transporte != null)
                        result = ctrDesp.salvar(tfNome, rbFixo, tfValor, dpData, taDescricao, 
                                Integer.parseInt(transporte.getParam1()));
                    else
                        result = ctrDesp.salvar(tfNome, rbFixo, tfValor, dpData, taDescricao);
                    
                    if(result)
                    {
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Despesa cadastrada com sucesso!!!", 
                                ButtonType.OK);
                        if(acao == 2)
                        {
                            Stage stage = (Stage) btConfirmar.getScene().getWindow();
                            stage.close();
                        }
                        inicializa();
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
                    Notifications.create()
                        .darkStyle()
                        //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                        .hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_CENTER)
                        .text("Despesa já foi paga, altera-la poderá gerar estorno")
                        .showInformation();
                
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    boolean result;
                    
                    if(this.transporte == null)
                        if(despesa.getParam9() != null && !despesa.getParam9().equals("") && 
                            !despesa.getParam9().equals("null"))
                            transporte = new Objeto(despesa.getParam9());
                    
                    if(Integer.parseInt(transporte.getParam1()) > 0 || ctrDesp.count(despesa.getParam2()) == 0)
                        result = ctrDesp.alterar(Integer.parseInt(despesa.getParam1()), tfNome, rbFixo, tfValor, 
                            dpData,taDescricao,Integer.parseInt(transporte.getParam1()));
                    //ALTERAÇÃO DE UMA DESPESA FIXA COM MAIS DE 1 REGISTRO, NOME ENVIADO PARA ALTERAR TODOS
                    else
                        result = ctrDesp.alterar(Integer.parseInt(despesa.getParam1()), tfNome, rbFixo, tfValor, dpData,
                            taDescricao,Integer.parseInt(transporte.getParam1()),despesa.getParam2());
                        
                    
                    if(result)
                    {   
                        inicializa();
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Despesa alterada com sucesso!!!", 
                                ButtonType.OK);
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro na alteracao da despesa", ButtonType.OK);
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
        
        if(transporte == null)
        {
            if(dpData.getEditor().getText().trim().equals(""))
            {
                erros += "Selecione uma data\n";
                lbErroData.setText("Selecione uma data");
            }
        }
        else
        {
            if(Integer.parseInt(transporte.getParam1()) == 0 || 
                (Integer.parseInt(transporte.getParam1()) > 0 && transporte.getParam6().equals("Finalizado")))
            {
                if(dpData.getEditor().getText().trim().equals(""))
                {
                    erros += "Selecione uma data\n";
                    lbErroData.setText("Selecione uma data");
                }
            }
        }
        
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        
        return erros.trim().equals("");
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
                if(ctrDesp.count(tvDespesa.getSelectionModel().getSelectedItem().getParam2()) > 1)
                {
                    ButtonType uma = new ButtonType("Uma"),todas = new ButtonType("Todas");
                    boolean result;
                    al = new Alert(Alert.AlertType.CONFIRMATION, "Remover esta despesa ou todas as despesas " + 
                        tvDespesa.getSelectionModel().getSelectedItem().getParam2() + "?", uma,todas);
                    al.showAndWait();
                    
                    if(al.getResult() == uma)
                        result = ctrDesp.apagar
                            (Integer.parseInt(tvDespesa.getSelectionModel().getSelectedItem().getParam1()));
                    else
                        result = ctrDesp.apagar(tvDespesa.getSelectionModel().getSelectedItem().getParam2());
                    
                    if(result)
                    {
                        inicializa();
                        al = new Alert(Alert.AlertType.INFORMATION, "Despesa removida com sucesso!!", ButtonType.OK);
                    }
                    else
                        al = new Alert(Alert.AlertType.ERROR, "Erro na remoção da despesa\n" + 
                                Banco.getCon().getMensagemErro(), ButtonType.OK);
                }
                else 
                {
                    if(ctrDesp.apagar(Integer.parseInt(tvDespesa.getSelectionModel().getSelectedItem().getParam1())))
                    {
                        inicializa();
                        al = new Alert(Alert.AlertType.INFORMATION, "Despesa removida com sucesso!!", ButtonType.OK);
                    }
                    else
                        al = new Alert(Alert.AlertType.ERROR, "Erro na remoção da despesa\n" + 
                                Banco.getCon().getMensagemErro(), ButtonType.OK);
                }
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
        tvDespesa.getItems().clear();
        if(transporte != null && transporte.getParam1() != null && Integer.parseInt(transporte.getParam1()) > 0)
            tvDespesa.setItems(FXCollections.observableArrayList(ctrDesp.getByTransporte(Integer.parseInt(transporte.getParam1()))));
        else if(!cbRepetidas.isSelected())
            tvDespesa.setItems(FXCollections.observableArrayList(ctrDesp.getByNomeDistinct(tfPesquisa.getText())));
        else
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
        tfNome.setText(desp.getParam2());
        rbFixo.setSelected(desp.getParam7().equals("Sim"));
        taDescricao.setText(desp.getParam6());
        
        if(desp.getParam4().substring(desp.getParam4().indexOf(".") + 1).length() == 1)
            tfValor.setText(desp.getParam4() + "0");
        else
            tfValor.setText(desp.getParam4());
        
        if(!desp.getParam5().equals(""))
            dpData.setValue(LocalDate.parse(desp.getParam5()));
    }

    @FXML
    private void novoExit(MouseEvent event)
    {
        btNovo.setStyle(btNovo.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void novoEnter(MouseEvent event)
    {
        btNovo.setStyle(btNovo.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Iniciar Novo Cadastro");
        ToolTip.bindTooltip(btNovo, tooltip);
    }

    @FXML
    private void confirmarExit(MouseEvent event)
    {
        btConfirmar.setStyle(btConfirmar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void confirmarEnter(MouseEvent event)
    {
        btConfirmar.setStyle(btConfirmar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Confirmar Ação");
        ToolTip.bindTooltip(btConfirmar, tooltip);
    }

    @FXML
    private void alterarExit(MouseEvent event)
    {
        btAlterar.setStyle(btAlterar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void alterarEnter(MouseEvent event)
    {
        btAlterar.setStyle(btAlterar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Remover Despesa");
        ToolTip.bindTooltip(btAlterar, tooltip);
    }
    
    @FXML
    private void removerExit(MouseEvent event)
    {
        btRemover.setStyle(btRemover.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void removerEnter(MouseEvent event)
    {
        btRemover.setStyle(btRemover.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Remover Despesa");
        ToolTip.bindTooltip(btRemover, tooltip);
    }

    @FXML
    private void cancelarExit(MouseEvent event)
    {
        btCancelar.setStyle(btCancelar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void cancelarEnter(MouseEvent event)
    {
        btCancelar.setStyle(btCancelar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Cancelar Ação");
        ToolTip.bindTooltip(btCancelar, tooltip);
    }

    @FXML
    private void pesquisarExit(MouseEvent event)
    {
        btPesquisar.setStyle(btPesquisar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
    {
        btPesquisar.setStyle(btPesquisar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Pesquisar Despesa");
        ToolTip.bindTooltip(btPesquisar, tooltip);
    }

    @FXML
    private void confirmarPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickConfimar(new ActionEvent());
    }

    @FXML
    private void pesquisarPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickPesquisar(new ActionEvent());
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
            String aux[] = result.get().split(":"),aux2;
            if(aux.length == 4)
                aux2 = aux[3];
            else if(aux.length < 3)
                aux2 = "";
            else
                aux2 = aux[2];
            
            if(ctrPag.pagarDespesa(LocalDate.now(),aux[0],aux[1],aux2))
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

    public void setTransporte(Objeto transporte)
    {
        this.transporte = transporte;
        inicializa();
        
        panePrincipal.setPrefHeight(550);
        painelCentral.setPrefHeight(550);
        painelCentral.setPrefWidth(600);
        //PAINEL DE PESQUISA FICA COM O RESTANTE DA TELA
        panePesquisa.setPrefHeight(painelCentral.getPrefHeight()- paneInfo.getPrefHeight() - 10);
        
        tvDespesa.setPrefHeight(panePesquisa.getPrefHeight() - (tvDespesa.getLayoutY() + 15)-40);
        tvDespesa.setPrefWidth(panePesquisa.getPrefWidth() - (tvDespesa.getLayoutX() * 2));
        
        //REORGANIZA OS TAMANHOS DAS COLUNAS DA TABELA
        int sobra = (int)((tvDespesa.getPrefWidth() - 1049)/2);
        tcDespesa.setPrefWidth(tcDespesa.getPrefWidth() + sobra);
        tcDescricao.setPrefWidth(tcDescricao.getPrefWidth() + sobra);
        
        tfNome.setLayoutY(tfNome.getLayoutY() - 20);
        rbFixo.setLayoutY(rbFixo.getLayoutY() - 20);
        tfValor.setLayoutY(tfValor.getLayoutY() - 20);
        dpData.setLayoutY(dpData.getLayoutY() - 20);
        lbData.setLayoutY(lbData.getLayoutY() - 20);
        taDescricao.setLayoutY(taDescricao.getLayoutY() - 30);
        taDescricao.setPrefHeight(taDescricao.getPrefHeight() - 10);
        lbErroData.setLayoutY(lbErroData.getLayoutY() - 20);
        lbErroDespesa.setLayoutY(lbErroDespesa.getLayoutY() - 20);
        lbErroValor.setLayoutY(lbErroValor.getLayoutY() - 20);
    }
}
