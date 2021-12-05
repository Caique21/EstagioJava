/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrRecebimento;
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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaRecebimentoController implements Initializable
{
    private final Tooltip tooltip = new Tooltip();
    private final ToggleGroup group = new ToggleGroup();
    
    private final ctrRecebimento ctrRec = ctrRecebimento.instancia();
    
    private Objeto recebimento;

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbRecebimentos;
    @FXML
    private Label lbAte;
    @FXML
    private JFXTextField tfPesquisa;
    @FXML
    private JFXRadioButton rbTodos;
    @FXML
    private JFXRadioButton rbNotaFiscal;
    @FXML
    private JFXRadioButton rbCliente;
    @FXML
    private JFXRadioButton rbData;
    @FXML
    private JFXRadioButton rbPeriodo;
    @FXML
    private JFXCheckBox cbConcluidos;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btEstornar;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faRestore;
    @FXML
    private FontAwesomeIconView faSearch;
    
    @FXML
    private TableView<Objeto> tvRecebimentos;
    @FXML
    private TableColumn<Objeto, String> tcNotaFiscal;
    @FXML
    private TableColumn<Objeto, String> tcNumero;
    @FXML
    private TableColumn<Objeto, String> tcValor;
    @FXML
    private TableColumn<Objeto, String> tcVenvimento;
    @FXML
    private TableColumn<Objeto, String> tcPaga;
    @FXML
    private TableColumn<Objeto, String> tcFormaPagamento;
    
    @FXML
    private TableView<Objeto> tvParcelas;
    @FXML
    private TableColumn<Objeto, String> tcCliente;
    @FXML
    private TableColumn<Objeto,String> tcIndentificao;
    @FXML
    private TableColumn<Objeto, String> tcValorPago;
    @FXML
    private TableColumn<Objeto, String> tcDataPagamento;
    @FXML
    private JFXDatePicker dpVencimento;
    @FXML
    private JFXDatePicker dpInicial;
    @FXML
    private JFXDatePicker dpFinal;

    /**
     * Initializes the controller class.
     */
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);        
        nodes.add(panePesquisa);        
        
        nodes.add(lbTitulo);
        nodes.add(lbAte);
        nodes.add(lbRecebimentos);
        
        nodes.add(tfPesquisa);
        
        nodes.add(cbConcluidos);
        
        nodes.add(faCheck);
        nodes.add(faRestore);
        nodes.add(faSearch);
        
        nodes.add(btEstornar);
        nodes.add(btConfirmar);
        nodes.add(btPesquisar);
        
        nodes.add(rbData);
        nodes.add(rbNotaFiscal);
        nodes.add(rbPeriodo);
        nodes.add(rbCliente);
        nodes.add(rbTodos);
        
        Utils.setDesign(1, nodes);
        lbRecebimentos.setStyle(lbRecebimentos.getStyle() + ";" + Utils.getFundo2withOpacity());
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: default;");
        btEstornar.setStyle(btEstornar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
    }
    
    private void inicializa()
    {
        tfPesquisa.setEditable(false);
        dpFinal.setEditable(false);
        dpInicial.setEditable(false);
        dpVencimento.setEditable(false);
        
        clickPesquisar(new ActionEvent());
        btConfirmar.setDisable(true);
        btEstornar.setDisable(true);
        recebimento = null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesign();
        
        rbData.setToggleGroup(group);
        rbNotaFiscal.setToggleGroup(group);
        rbPeriodo.setToggleGroup(group);
        rbTodos.setToggleGroup(group);
        rbCliente.setToggleGroup(group);
        rbTodos.setSelected(true);
        
        tcNotaFiscal.setCellValueFactory(new PropertyValueFactory<>("param1"));
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcVenvimento.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcPaga.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("param8"));
        
        tcCliente.setCellValueFactory(new PropertyValueFactory<>("param11"));
        tcValorPago.setCellValueFactory(new PropertyValueFactory<>("param6"));
        tcDataPagamento.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcIndentificao.setCellValueFactory(new PropertyValueFactory<>("param12"));
        
        inicializa();
        cbConcluidos.setOnAction((event) ->
        {
            clickPesquisar(new ActionEvent());
        });
    }    

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(recebimento != null && recebimento.getParam5().equals("Não"))
        {
            Dialog<String> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
            dialog.getDialogPane().setPrefWidth(300);
            dialog.getDialogPane().setPrefHeight(180);
            dialog.setTitle("Confirmar valor do recebimento");
            
            VBox vbox = new VBox(20);
            vbox.setPrefHeight(dialog.getDialogPane().getPrefHeight());
            vbox.setPrefWidth(dialog.getDialogPane().getPrefWidth());
            vbox.setPadding(new Insets(15, 10, 10, 10));

            JFXTextField valor = new JFXTextField();
            valor.setLabelFloat(true);
            valor.setPromptText("Valor da parcela(*)");
            MaskFieldUtil.monetaryField(valor);
            valor.setText(Utils.exibeCentavos(Double.parseDouble(recebimento.getParam3())));
            
            JFXTextField obs = new JFXTextField();
            obs.setLabelFloat(true);
            obs.setVisible(false);
            
            JFXComboBox<String>formas_pag = new JFXComboBox<>();
            formas_pag.setLabelFloat(true);
            formas_pag.setPromptText("Selecione a forma de recebimento");
            formas_pag.setPrefWidth(180);
            formas_pag.getItems().addAll("Cartão","Cheque","Dinheiro","Pix","Transferência");
            formas_pag.getSelectionModel().selectedItemProperty().addListener((observable) ->
            {
                if(!formas_pag.getSelectionModel().getSelectedItem().equals("Cartão") && 
                    !formas_pag.getSelectionModel().getSelectedItem().equals("Dinheiro"))
                {
                    obs.setVisible(true);
                    
                    if(formas_pag.getSelectionModel().getSelectedItem().equals("Cheque"))
                        obs.setPromptText("Número do cheque(*)");
                    else
                        obs.setPromptText("Observações");
                }
                else
                    obs.setVisible(false);
            });
            formas_pag.getSelectionModel().select(0);
            
            vbox.getChildren().addAll(valor,formas_pag,obs);
            dialog.getDialogPane().setContent(vbox);
            dialog.setResultConverter(dialogButton ->
            {
                if (dialogButton == ButtonType.YES && !valor.getText().trim().equals(""))
                {
                    double val = Utils.convertStringToDouble(valor.getText());
                    
                    if(val > 0 && val <= Double.parseDouble(recebimento.getParam3()))
                    {
                        if(formas_pag.getSelectionModel().getSelectedItem().equals("Cheque"))
                        {
                            if(!obs.getText().trim().equals(""))
                                return valor.getText() + ":" + formas_pag.getSelectionModel().getSelectedItem() 
                                    + ":" + obs.getText();
                        }
                        else
                            return valor.getText() + ":" + formas_pag.getSelectionModel().getSelectedItem() 
                                    + ":" + (obs.getText().trim().equals("")? " " : obs.getText());
                    }
                        
                }
                return null;
            });
            Optional<String> result = dialog.showAndWait();
            
            if(result.isPresent())
            {
                String aux[] = result.get().split(":");
                Alert a;
                
                if(ctrRec.pagar(recebimento,aux[0],aux[1],aux[2]))
                {
                    inicializa();
                    a = new Alert(Alert.AlertType.INFORMATION, "Recebimento efetuado com sucesso!!", ButtonType.OK);
                }
                else
                    a = new Alert(Alert.AlertType.ERROR, "Erro no recebimento\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK);
                a.showAndWait();
            }
        }
    }

    @FXML
    private void clickEstornar(ActionEvent event)
    {
        if(Utils.confirmarUsuario("Confirmar usuário", "Para estornar recebimento da venda", 
                "Usuário: " + TelaPrincipalController.usuario_logado.getParam3()))
        {
            Alert a;
            if(ctrRec.estornar(recebimento,Integer.parseInt(TelaPrincipalController.usuario_logado.getParam1())))
            {
                inicializa();
                a = new Alert(Alert.AlertType.INFORMATION, "Pagamento estornado com sucesso!!", ButtonType.OK);
            }
            else
                a = new Alert(Alert.AlertType.ERROR, "Erro no estorno do recebimento\n" + 
                        Banco.getCon().getMensagemErro(), ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        if(rbTodos.isSelected())
            tvRecebimentos.setItems(FXCollections.observableArrayList(ctrRec.getAll(cbConcluidos.isSelected())));
        else if(rbNotaFiscal.isSelected())
            tvRecebimentos.setItems(FXCollections.observableArrayList(ctrRec.getByNotaFiscal(tfPesquisa.getText(),cbConcluidos.isSelected())));
        else if(rbCliente.isSelected())
            tvRecebimentos.setItems(FXCollections.observableArrayList(ctrRec.getByFornecedor(tfPesquisa.getText(),cbConcluidos.isSelected())));
        else if(rbData.isSelected())
            tvRecebimentos.setItems(FXCollections.observableArrayList(ctrRec.getByData(dpVencimento.getValue(),cbConcluidos.isSelected())));
        else if(rbPeriodo.isSelected())
            tvRecebimentos.setItems(FXCollections.observableArrayList(ctrRec.getByPeriodo(dpInicial.getValue(),dpFinal.getValue(),cbConcluidos.isSelected())));
    }

    @FXML
    private void selecionaRecebimento(MouseEvent event)
    {
        if(!tvRecebimentos.getItems().isEmpty() && tvRecebimentos.getSelectionModel().getSelectedIndex() >= 0)
        {
            Objeto aux;
            recebimento = tvRecebimentos.getSelectionModel().getSelectedItem();
            
            tvParcelas.getItems().clear();
            tvParcelas.getItems().add(recebimento.getList1().get(0));
            
            
            if(recebimento.getParam5().equals("Sim"))
            {
                btEstornar.setDisable(false);
                btConfirmar.setDisable(true);
            }
            else if(recebimento.getParam5().equals("Não"))
            {
                btEstornar.setDisable(true);
                btConfirmar.setDisable(false);
            }
            else
            {
                btEstornar.setDisable(false);
                btConfirmar.setDisable(false);
            }
        }
        else if(tvRecebimentos.getItems().isEmpty())
                new Alert(Alert.AlertType.ERROR, "Tabela de recebimento vazia", ButtonType.OK).showAndWait();
            else if(tvRecebimentos.getSelectionModel().getSelectedIndex() < 0)
                new Alert(Alert.AlertType.ERROR, "Selecione uma despesa/parcela", ButtonType.OK).showAndWait();
        
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
    private void estornarExit(MouseEvent event)
    {
    }

    @FXML
    private void estornarEnter(MouseEvent event)
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
    
    @FXML
    private void clickTodos(ActionEvent event)
    {
        tfPesquisa.setEditable(false);
        dpFinal.setEditable(false);
        dpInicial.setEditable(false);
        dpVencimento.setEditable(false);
        
        clickPesquisar(new ActionEvent());
    }

    @FXML
    private void clickNotaFiscal(ActionEvent event)
    {
        tfPesquisa.setVisible(true);
        tfPesquisa.setEditable(true);
        tfPesquisa.setPromptText("Digite nota fiscal da compra para pesquisar");
        
        dpFinal.setVisible(false);
        dpInicial.setVisible(false);
        dpVencimento.setVisible(false);
        lbAte.setVisible(false);
    }

    @FXML
    private void clickCliente(ActionEvent event)
    {
        tfPesquisa.setVisible(true);
        tfPesquisa.setEditable(true);
        tfPesquisa.setPromptText("Digite nome do Fornecedor para pesquisar");
        
        dpFinal.setVisible(false);
        dpInicial.setVisible(false);
        dpVencimento.setVisible(false);
        lbAte.setVisible(false);
    }

    private void clickDespesa(ActionEvent event)
    {
        tfPesquisa.setVisible(true);
        tfPesquisa.setEditable(true);
        tfPesquisa.setPromptText("Digite nome da Despesa para pesquisar");
        
        dpFinal.setVisible(false);
        dpInicial.setVisible(false);
        dpVencimento.setVisible(false);
        lbAte.setVisible(false);
    }

    @FXML
    private void clickData(ActionEvent event)
    {
        dpVencimento.setVisible(true);
        dpVencimento.setEditable(true);
        dpVencimento.setValue(LocalDate.now());
        
        tfPesquisa.setVisible(false);
        dpFinal.setVisible(false);
        dpInicial.setVisible(false);
        lbAte.setVisible(false);
    }

    @FXML
    private void clickPeriodo(ActionEvent event)
    {
        dpInicial.setVisible(true);
        dpInicial.setEditable(true);
        dpInicial.setValue(LocalDate.now());
        dpFinal.setVisible(true);
        dpFinal.setEditable(true);
        dpFinal.setValue(LocalDate.now());
        lbAte.setVisible(true);
        
        tfPesquisa.setVisible(false);
        dpVencimento.setVisible(false);
    }

}
