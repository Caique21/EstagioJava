/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrCompra;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ConfirmarTransacaoController implements Initializable
{
    private final ctrCompra ctrComp = ctrCompra.instancia();
    
    private final ToggleGroup goup1 = new ToggleGroup();
    private final ToggleGroup goup2 = new ToggleGroup();
    private final ToggleGroup goup3 = new ToggleGroup();
    
    private boolean compra;
    private boolean resposta = false;
    private Objeto obj;
    private double total;
    
    private ArrayList<Objeto>parcelas;

    @FXML
    private JFXRadioButton rbJuros;
    @FXML
    private JFXRadioButton rbDesconto;
    @FXML
    private JFXRadioButton rbValor;
    @FXML
    private JFXRadioButton rbPorcentagem;
    @FXML
    private JFXTextField tfValorReajuste;
    @FXML
    private JFXTextField tfPorcReajuste;
    @FXML
    private JFXRadioButton rbAvista;
    @FXML
    private JFXRadioButton rbParcelado;
    @FXML
    private JFXTextField tfNumeroParcelas;
    @FXML
    private JFXDatePicker dpVencimento;
    @FXML
    private JFXButton btParcelasManuais;
    @FXML
    private Label lbVencimento;
    @FXML
    private Label lbSubTotal;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbLimparParcelas;
    @FXML
    private JFXButton btVisualizar;
    @FXML
    private FontAwesomeIconView faView;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private JFXTextField tfEntrada;
    @FXML
    private BorderPane panePrincipal;
    @FXML
    private HBox hbJuros;
    @FXML
    private HBox hbValor;
    @FXML
    private HBox hbParcelas;
    @FXML
    private Label lbErroReajuste;
    @FXML
    private Label lbErroEntrada;
    @FXML
    private Label lbErroParcelas;

    /**
     * Initializes the controller class.
     */
     private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(hbJuros);
        nodes.add(hbParcelas);
        nodes.add(hbValor);
        
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btParcelasManuais);
        nodes.add(btVisualizar);
        
        nodes.add(tfEntrada);
        nodes.add(tfNumeroParcelas);
        nodes.add(tfPorcReajuste);
        nodes.add(tfValorReajuste);
        
        nodes.add(lbLimparParcelas);
        nodes.add(lbVencimento);
        nodes.add(lbSubTotal);
        nodes.add(lbTitulo);
        
        nodes.add(rbAvista);
        nodes.add(rbDesconto);
        nodes.add(rbJuros);
        nodes.add(rbParcelado);
        nodes.add(rbPorcentagem);
        nodes.add(rbValor);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faView);
        
        Utils.setDesign(1, nodes);
        
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: default;");
        btParcelasManuais.setStyle(btParcelasManuais.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btVisualizar.setStyle(btVisualizar.getStyle() + ";-fx-cursor: default;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {   
        rbAvista.setToggleGroup(goup1);
        rbParcelado.setToggleGroup(goup1);
        rbAvista.setSelected(true);
        
        rbDesconto.setToggleGroup(goup2);
        rbJuros.setToggleGroup(goup2);
        rbDesconto.setSelected(true);
        
        rbPorcentagem.setToggleGroup(goup3);
        rbValor.setToggleGroup(goup3);
        rbValor.setSelected(true);
        
        MaskFieldUtil.monetaryField(tfEntrada);
        MaskFieldUtil.monetaryField(tfValorReajuste);
        MaskFieldUtil.numericField(tfPorcReajuste);
        MaskFieldUtil.numericField(tfNumeroParcelas);
        
        lbLimparParcelas.setVisible(false);
        
        dpVencimento.setValue(LocalDate.now());
        tfEntrada.setText("0");
        tfNumeroParcelas.setText("1");
        tfValorReajuste.setText("0");
        tfEntrada.setDisable(true);
        
        setListeners();
    }    

    @FXML
    private void clickVisualizar(ActionEvent event)
    {
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        dialog.getDialogPane().setPrefWidth(500);
        dialog.getDialogPane().setPrefHeight(500);
        dialog.setTitle("Visualizar Parcelas");

        TableView tvParcelas = new TableView();
        tvParcelas.setPrefWidth(440);
        tvParcelas.setPrefHeight(400);
        TableColumn<Objeto,String> tcValor = new TableColumn<>("Valor");
        tcValor.setPrefWidth(200);
        TableColumn<Objeto,String> tcNumero = new TableColumn<>("Nº Parcela");
        tcNumero.setPrefWidth(120);
        TableColumn<Objeto,String> tcVencimento = new TableColumn<>("Data vencimento");
        tcVencimento.setPrefWidth(tvParcelas.getPrefWidth() - 320);
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param1"));
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcVencimento.setCellValueFactory(new PropertyValueFactory<>("param3"));
        tvParcelas.getColumns().addAll(tcValor,tcNumero,tcVencimento);
        
        if(parcelas == null)
        {
            if(!tfNumeroParcelas.getText().trim().equals(""))
            {
                if(Integer.parseInt(tfNumeroParcelas.getText()) == 1)
                    tvParcelas.getItems().add(new Objeto(String.valueOf(total),String.valueOf(1),
                                String.valueOf(dpVencimento.getValue().toString())));
                else
                {
                    double val = 0.0;
                    for (int i = 0; i < Integer.parseInt(tfNumeroParcelas.getText()); i++)
                    {
                        if(i < Integer.parseInt(tfNumeroParcelas.getText()) - 1)
                        {
                            val += Utils.truncate(total/Integer.parseInt(tfNumeroParcelas.getText()));
                            tvParcelas.getItems().add(new Objeto(String.valueOf(Utils.truncate(total/Integer.parseInt(tfNumeroParcelas.getText()))),String.valueOf(i + 1),
                                    String.valueOf(dpVencimento.getValue().toString())));
                        }
                        else
                            tvParcelas.getItems().add(new Objeto(String.valueOf(total - val),String.valueOf(i + 1),
                                    String.valueOf(dpVencimento.getValue().toString())));
                    }
                }
            }
        }
            
        dialog.getDialogPane().setContent(tvParcelas);
        dialog.showAndWait();
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(compra && obj != null)
        {
            //if(valida())
            if(!dpVencimento.getEditor().getText().equals(""))
            {
                if(ctrComp.salvar(obj.getParam1(), Boolean.valueOf(obj.getParam2()), tfNumeroParcelas.getText(), 
                    String.valueOf(dpVencimento.getValue()),this.lbSubTotal.getText().substring(lbSubTotal.getText().indexOf("$") + 1),
                    calculaAjuste(), obj.getParam7(), obj.getParam8(), obj.getParam9(), 
                    tfEntrada.getText() ,obj.getList1()) > 0)
                {
                    resposta = true;
                    Stage stage = (Stage) btConfirmar.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        resposta = false;
        Stage stage = (Stage) btConfirmar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickGerarParcelas(ActionEvent event)
    {
    }

    @FXML
    private void clickJuros(ActionEvent event)
    {
        if(rbValor.isSelected())
        {
            tfValorReajuste.setPromptText("Juros(R$)");
            tfValorReajuste.setText("0");
        }
        else if(rbPorcentagem.isSelected())
        {
            tfPorcReajuste.setPromptText("Juros(%)");
            tfPorcReajuste.setText("0");
        }
        atualizaTotal();
    }

    @FXML
    private void clickDesconto(ActionEvent event)
    {
        if(rbValor.isSelected())
        {
            tfValorReajuste.setPromptText("Desconto(R$)");
            tfValorReajuste.setText("0");
        }
        else if(rbPorcentagem.isSelected())
        {
            tfPorcReajuste.setPromptText("Desconto(%)");
            tfPorcReajuste.setText("0");
        }
        atualizaTotal();
    }

    @FXML
    private void clickValor(ActionEvent event)
    {
        tfPorcReajuste.setVisible(false);
        tfValorReajuste.setVisible(true);
        tfValorReajuste.clear();
        
        if(rbDesconto.isSelected())
            clickDesconto(new ActionEvent());
        else
            clickJuros(new ActionEvent());
        atualizaTotal();
    }

    @FXML
    private void clickPorcentagem(ActionEvent event)
    {
        tfValorReajuste.setVisible(false);
        tfPorcReajuste.setVisible(true);
        tfPorcReajuste.clear();
        
        if(rbDesconto.isSelected())
            clickDesconto(new ActionEvent());
        else
            clickJuros(new ActionEvent());
        atualizaTotal();
    }

    @FXML
    private void clickAvista(ActionEvent event)
    {
        tfNumeroParcelas.setText("1");
        tfEntrada.setText("");
        tfEntrada.setDisable(true);
    }

    @FXML
    private void clickParcelado(ActionEvent event)
    {
        tfNumeroParcelas.selectAll();
        tfEntrada.setText("0");
        tfEntrada.setDisable(false);
    }

    @FXML
    private void gerarParcelasExit(MouseEvent event)
    {
    }

    @FXML
    private void gerarParcelasEnter(MouseEvent event)
    {
    }

    void setCompra(JFXTextField tfFornecedor,boolean cliente, JFXTextField tfNotaFiscal, JFXTextField tfVendedor, 
            JFXDatePicker dpEmissao, TableView<Objeto> tvVeiculos, Label lbSubTotal)
    {
        lbTitulo.setText(lbTitulo.getText() + "Compra");
        this.lbSubTotal.setText(lbSubTotal.getText().replace("%", "Compra"));
        total = Double.parseDouble(lbSubTotal.getText().substring(lbSubTotal.getText().indexOf("$") + 1)
                .replace(".", "").replace(",", "."));
        compra = true;
        
        obj = new Objeto();
        obj.setParam1(tfFornecedor.getText());
        obj.setParam2(String.valueOf(cliente));
        obj.setParam7(tfNotaFiscal.getText());
        obj.setParam8(String.valueOf(dpEmissao.getValue()));
        obj.setParam9(tfVendedor.getText());
        
        for(Objeto o : tvVeiculos.getItems())
            obj.addList1(o);
    }

    private void setListeners()
    {
        lbLimparParcelas.setOnMouseClicked((event) ->
        {
            parcelas = null;
            lbLimparParcelas.setVisible(false);
        });
        
        tfEntrada.textProperty().addListener((observable, oldValue, newValue) ->
        {
            double val = Double.parseDouble(tfEntrada.getText().replace(".", "").replace(",", "."));
            if(val < 0 || val > total)
                tfEntrada.setText(oldValue);
        });
        
        tfEntrada.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue && tfEntrada.getText().trim().equals(""))
                tfEntrada.setText("0");
        });
        
        tfNumeroParcelas.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(Integer.parseInt(tfNumeroParcelas.getText()) <= 0 || Integer.parseInt(tfNumeroParcelas.getText()) > 96)
                tfNumeroParcelas.setText(oldValue);
        });
        
        tfNumeroParcelas.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue && tfNumeroParcelas.getText().trim().equals(""))
                tfNumeroParcelas.setText("1");
        });
        
        tfValorReajuste.textProperty().addListener((observable, oldValue, newValue) ->
        {
            double val = Double.parseDouble(tfValorReajuste.getText().replace(".", "").replace(",", "."));
            if(val < 0 || val > total)
                tfValorReajuste.setText(oldValue);
            atualizaTotal();
        });
        
        tfValorReajuste.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(rbValor.isSelected() && !newValue && tfValorReajuste.getText().trim().equals(""))
                tfValorReajuste.setText("0");
        });
        
        tfPorcReajuste.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(Integer.parseInt(tfPorcReajuste.getText()) < 0 || Integer.parseInt(tfPorcReajuste.getText()) > 100)
                tfPorcReajuste.setText(oldValue);
            atualizaTotal();
        });
        
        tfPorcReajuste.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(rbPorcentagem.isSelected() && !newValue && tfPorcReajuste.getText().trim().equals(""))
                tfPorcReajuste.setText("0");
        });
    }

    private void atualizaTotal()
    {
        double total = calculaTotal(),nvalor;
        
        if(rbDesconto.isSelected())
        {
            if(rbValor.isSelected())
                total -= Double.parseDouble(tfValorReajuste.getText().replace(".", "").replace(",", "."));
            else if(rbPorcentagem.isSelected())
            {
                nvalor = 100 - (Integer.parseInt(tfPorcReajuste.getText()));
                total = Utils.truncate((total * (nvalor / 100)));
            }
        }
        else if(rbJuros.isSelected())
        {
            if(rbValor.isSelected())
                total += Double.parseDouble(tfValorReajuste.getText().replace(".", "").replace(",", "."));
            else if(rbPorcentagem.isSelected())
            {
                nvalor = 100 + (Integer.parseInt(tfPorcReajuste.getText()));
                total = Utils.truncate((total * (nvalor / 100)));
            }
        }
        
        lbSubTotal.setText("Total da Compra: " + NumberFormat.getCurrencyInstance().format(total));
    }

    private double calculaTotal()
    {
        double total = 0.0;
        if(obj.getList1() != null && !obj.getList1().isEmpty())
            for(Objeto obj : obj.getList1())
                total += Double.parseDouble(obj.getParam10().replace(".", "").replace(",", "."));
        return total;
    }

    boolean getResposta()
    {
        return resposta;
    }

    private double calculaAjuste()
    {
        return Double.parseDouble(lbSubTotal.getText().substring(lbSubTotal.getText().indexOf("$") + 1).
            replace(".", "").replace(",", ".")) - calculaTotal();
    }
    
}
