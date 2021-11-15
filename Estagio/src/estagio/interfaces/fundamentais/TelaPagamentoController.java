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
import estagio.controladores.ctrDespesa;
import estagio.controladores.ctrPagamento;
import estagio.controladores.ctrParcela;
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
public class TelaPagamentoController implements Initializable
{
    private final Tooltip tooltip = new Tooltip();
    private final ToggleGroup group = new ToggleGroup();
    
    private final ctrPagamento ctrPag = ctrPagamento.instancia();
    private final ctrDespesa ctrDesp = ctrDespesa.instancia();
    private final ctrParcela ctrPar = ctrParcela.instancia();
    
    private Objeto pagamento;
    
    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbPagamentos;
    @FXML
    private Label lbParcelas;
    @FXML
    private Label lbDespesas;
    @FXML
    private Label lbAte;
    @FXML
    private JFXTextField tfFornecedorDespesa;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btEstornar;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faRestore;
    @FXML
    private JFXCheckBox cbPagas;
    @FXML
    private JFXDatePicker dpVencimento;
    @FXML
    private JFXDatePicker dpInicial;
    @FXML
    private JFXDatePicker dpFinal;
    @FXML
    private JFXRadioButton rbTodos;
    @FXML
    private JFXRadioButton rbNotaFiscal;
    @FXML
    private JFXRadioButton rbFornecedor;
    @FXML
    private JFXRadioButton rbDespesa;
    @FXML
    private JFXRadioButton rbData;
    @FXML
    private JFXRadioButton rbPeriodo;
    
    @FXML
    private TableView<Objeto> tvPagamentos;
    @FXML
    private TableColumn<Objeto, String> tcNome;
    @FXML
    private TableColumn<Objeto, String> tcNumero;
    @FXML
    private TableColumn<Objeto, String> tcValor;
    @FXML
    private TableColumn<Objeto, String> tcVencimento;
    @FXML
    private TableColumn<Objeto, String> tcPaga;
    @FXML
    private TableColumn<Objeto, String> tcFormaPagamento;
    
    @FXML
    private TableView<Objeto> tvParcelas;
    @FXML
    private TableColumn<Objeto, String> tcNotaFiscal;
    @FXML
    private TableColumn<Objeto, String> tcNumeroParcela;
    @FXML
    private TableColumn<Objeto, String> tcValorPagoParcela;
    @FXML
    private TableColumn<Objeto, String> tcDataCompra;
    
    @FXML
    private TableView<Objeto> tvDespesa;
    @FXML
    private TableColumn<Objeto, String> tcDespesa;
    @FXML
    private TableColumn<Objeto, String> tcFixa;
    @FXML
    private TableColumn<Objeto, String> tcValorPagoDespesa;
    @FXML
    private TableColumn<Objeto, String> tcObsDespesa;

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
        nodes.add(lbDespesas);
        nodes.add(lbPagamentos);
        nodes.add(lbParcelas);
        
        nodes.add(tfFornecedorDespesa);
        
        nodes.add(cbPagas);
        
        nodes.add(faCheck);
        nodes.add(faRestore);
        nodes.add(faSearch);
        
        nodes.add(btEstornar);
        nodes.add(btConfirmar);
        nodes.add(btPesquisar);
        
        nodes.add(rbData);
        nodes.add(rbNotaFiscal);
        nodes.add(rbPeriodo);
        nodes.add(rbDespesa);
        nodes.add(rbFornecedor);
        nodes.add(rbTodos);
        
        Utils.setDesign(1, nodes);
        lbDespesas.setStyle(lbDespesas.getStyle() + ";" + Utils.getFundo2withOpacity());
        lbParcelas.setStyle(lbParcelas.getStyle() + ";" + Utils.getFundo2withOpacity());
        lbPagamentos.setStyle(lbPagamentos.getStyle() + ";" + Utils.getFundo2withOpacity());
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: default;");
        btEstornar.setStyle(btEstornar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
    }
    
    private void inicializa()
    {
        tvParcelas.setVisible(false);
        lbParcelas.setVisible(false);
        tvDespesa.setVisible(false);
        lbDespesas.setVisible(false);
        
        tfFornecedorDespesa.setEditable(false);
        dpFinal.setEditable(false);
        dpInicial.setEditable(false);
        dpVencimento.setEditable(false);
        
        clickPesquisar(new ActionEvent());
        btConfirmar.setDisable(true);
        btEstornar.setDisable(true);
        pagamento = null;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesign();
        
        rbData.setToggleGroup(group);
        rbDespesa.setToggleGroup(group);
        rbFornecedor.setToggleGroup(group);
        rbPeriodo.setToggleGroup(group);
        rbTodos.setToggleGroup(group);
        rbNotaFiscal.setToggleGroup(group);
        rbTodos.setSelected(true);
        
        tcNome.setCellValueFactory(new PropertyValueFactory<>("param1"));
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcVencimento.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcPaga.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("param6"));
        
        tcNotaFiscal.setCellValueFactory(new PropertyValueFactory<>("param8"));
        tcNumeroParcela.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tcValorPagoParcela.setCellValueFactory(new PropertyValueFactory<>("param6"));
        tcDataCompra.setCellValueFactory(new PropertyValueFactory<>("param10"));
        
        tcDespesa.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcFixa.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcValorPagoDespesa.setCellValueFactory(new PropertyValueFactory<>("param8"));
        tcObsDespesa.setCellValueFactory(new PropertyValueFactory<>("param6"));
        
        inicializa();
    }    

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(pagamento != null && pagamento.getParam5().equals("Não"))
        {
            Dialog<String> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
            dialog.getDialogPane().setPrefWidth(300);
            dialog.getDialogPane().setPrefHeight(180);
            dialog.setTitle("Confirmar valor do pagamento");
            
            VBox vbox = new VBox(20);
            vbox.setPrefHeight(dialog.getDialogPane().getPrefHeight());
            vbox.setPrefWidth(dialog.getDialogPane().getPrefWidth());
            vbox.setPadding(new Insets(15, 10, 10, 10));

            JFXTextField valor = new JFXTextField();
            valor.setLabelFloat(true);
            valor.setPromptText("Valor da parcela(*)");
            MaskFieldUtil.monetaryField(valor);
            valor.setText(Utils.exibeCentavos(Double.parseDouble(pagamento.getParam3())));
            
            JFXTextField obs = new JFXTextField();
            obs.setLabelFloat(true);
            obs.setVisible(false);
            
            JFXComboBox<String>formas_pag = new JFXComboBox<>();
            formas_pag.setLabelFloat(true);
            formas_pag.setPromptText("Selecione a forma de pagamento");
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
                    
                    if(val > 0 && val <= Double.parseDouble(pagamento.getParam3()))
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
                
                if(ctrPag.pagar(pagamento,aux[0],aux[1],aux[2]))
                {
                    inicializa();
                    a = new Alert(Alert.AlertType.INFORMATION, "Pagamento efetuado com sucesso!!", ButtonType.OK);
                }
                else
                    a = new Alert(Alert.AlertType.ERROR, "Erro no pagamento\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK);
                a.showAndWait();
            }
        }
        
    }

    @FXML
    private void clickEstornar(ActionEvent event)
    {
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        tvDespesa.setVisible(false);
        lbDespesas.setVisible(false);
        tvParcelas.setVisible(false);
        lbParcelas.setVisible(false);
        
        if(rbTodos.isSelected())
            tvPagamentos.setItems(FXCollections.observableArrayList(ctrPag.getAll(cbPagas.isSelected())));
        else if(rbNotaFiscal.isSelected())
            tvPagamentos.setItems(FXCollections.observableArrayList(ctrPag.getByNotaFiscal(tfFornecedorDespesa.getText(),cbPagas.isSelected())));
        else if(rbFornecedor.isSelected())
            tvPagamentos.setItems(FXCollections.observableArrayList(ctrPag.getByFornecedor(tfFornecedorDespesa.getText(),cbPagas.isSelected())));
        else if(rbDespesa.isSelected())
            tvPagamentos.setItems(FXCollections.observableArrayList(ctrPag.getByDespesa(tfFornecedorDespesa.getText(),cbPagas.isSelected())));
        else if(rbData.isSelected())
            tvPagamentos.setItems(FXCollections.observableArrayList(ctrPag.getByData(dpVencimento.getValue(),cbPagas.isSelected())));
        else if(rbPeriodo.isSelected())
            tvPagamentos.setItems(FXCollections.observableArrayList(ctrPag.getByPeriodo(dpInicial.getValue(),dpFinal.getValue(),cbPagas.isSelected())));
    }

    @FXML
    private void selecionaPagamento(MouseEvent event)
    {
        if(!tvPagamentos.getItems().isEmpty() && tvPagamentos.getSelectionModel().getSelectedIndex() >= 0)
        {
            Objeto aux;
            pagamento = tvPagamentos.getSelectionModel().getSelectedItem();
            
            if(pagamento.getParam2().equals(""))
            {
                aux = pagamento.getList1(0);
                tvDespesa.setVisible(true);
                lbDespesas.setVisible(true);
                
                tvParcelas.setVisible(false);
                lbParcelas.setVisible(false);
                
                tvDespesa.getItems().clear();
                tvDespesa.getItems().add(aux);
            }
            else
            {
                aux = pagamento.getList2(0);
                tvDespesa.setVisible(false);
                lbDespesas.setVisible(false);
                
                tvParcelas.setVisible(true);
                lbParcelas.setVisible(true);
                
                tvParcelas.getItems().clear();
                tvParcelas.getItems().add(aux);
            }
            
            if(pagamento.getParam5().equals("Sim"))
            {
                btEstornar.setDisable(false);
                btConfirmar.setDisable(true);
            }
            else if(pagamento.getParam5().equals("Não"))
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
        else
        {
            tvDespesa.setVisible(false);
            lbDespesas.setVisible(false);
            tvParcelas.setVisible(false);
            lbParcelas.setVisible(false);
            
            if(tvPagamentos.getItems().isEmpty())
                new Alert(Alert.AlertType.ERROR, "Tabela de pagamento vazia", ButtonType.OK).showAndWait();
            else if(tvPagamentos.getSelectionModel().getSelectedIndex() < 0)
                new Alert(Alert.AlertType.ERROR, "Selecione uma despesa/parcela", ButtonType.OK).showAndWait();
        }
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
        tfFornecedorDespesa.setEditable(false);
        dpFinal.setEditable(false);
        dpInicial.setEditable(false);
        dpVencimento.setEditable(false);
        
        clickPesquisar(new ActionEvent());
    }

    @FXML
    private void clickNotaFiscal(ActionEvent event)
    {
        tfFornecedorDespesa.setVisible(true);
        tfFornecedorDespesa.setEditable(true);
        tfFornecedorDespesa.setPromptText("Digite nota fiscal da compra para pesquisar");
        
        dpFinal.setVisible(false);
        dpInicial.setVisible(false);
        dpVencimento.setVisible(false);
        lbAte.setVisible(false);
    }

    @FXML
    private void clickFornecedor(ActionEvent event)
    {
        tfFornecedorDespesa.setVisible(true);
        tfFornecedorDespesa.setEditable(true);
        tfFornecedorDespesa.setPromptText("Digite nome do Fornecedor para pesquisar");
        
        dpFinal.setVisible(false);
        dpInicial.setVisible(false);
        dpVencimento.setVisible(false);
        lbAte.setVisible(false);
    }

    @FXML
    private void clickDespesa(ActionEvent event)
    {
        tfFornecedorDespesa.setVisible(true);
        tfFornecedorDespesa.setEditable(true);
        tfFornecedorDespesa.setPromptText("Digite nome da Despesa para pesquisar");
        
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
        
        tfFornecedorDespesa.setVisible(false);
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
        
        tfFornecedorDespesa.setVisible(false);
        dpVencimento.setVisible(false);
    }
    
}
