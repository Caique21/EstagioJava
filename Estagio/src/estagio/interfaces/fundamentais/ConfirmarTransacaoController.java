/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrCompra;
import estagio.utilidades.Banco;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.Utils;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private final Tooltip tooltip = new Tooltip();
    
    private boolean iscompra;
    private boolean resposta = false;
    private Objeto compra;
    private double total;
    private int acao = -1;

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
    private Label lbVencimento;
    @FXML
    private Label lbSubTotal;
    @FXML
    private Label lbTitulo;
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
        
        dpVencimento.setValue(LocalDate.now());
        tfEntrada.setText("0");
        tfNumeroParcelas.setText("1");
        tfValorReajuste.setText("0");
        tfEntrada.setDisable(true);
        
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btVisualizar.setStyle(btVisualizar.getStyle() + ";-fx-cursor: default;");
        
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
        
        if (!tfNumeroParcelas.getText().trim().equals(""))
        {
            if (Integer.parseInt(tfNumeroParcelas.getText()) == 1)
            {
                tvParcelas.getItems().add(new Objeto(String.valueOf(total), String.valueOf(1),
                        String.valueOf(dpVencimento.getValue().toString())));
            }
            else
            {
                double val = 0.0;
                for (int i = 0; i < Integer.parseInt(tfNumeroParcelas.getText()); i++)
                {
                    if (i < Integer.parseInt(tfNumeroParcelas.getText()) - 1)
                    {
                        val += Utils.truncate(total / Integer.parseInt(tfNumeroParcelas.getText()));
                        tvParcelas.getItems().add(new Objeto(String.valueOf(Utils.truncate(total / Integer.parseInt(tfNumeroParcelas.getText()))), String.valueOf(i + 1),
                                String.valueOf(dpVencimento.getValue().toString())));
                    }
                    else
                    {
                        tvParcelas.getItems().add(new Objeto(String.valueOf(total - val), String.valueOf(i + 1),
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
        if(iscompra && compra != null)
        {
            if(!dpVencimento.getEditor().getText().equals(""))
            {
                completarCompra();
                if(acao == 0)
                    resposta = ctrComp.salvar(compra, dpVencimento.getValue(), tfEntrada.getText(),
                            compra.getList2());
                else if(acao == 1)
                    resposta = ctrComp.alterar(compra, dpVencimento.getValue(),tfEntrada.getText(),
                                compra.getList2());
                
                if(resposta)
                {
                    Stage stage = (Stage) btConfirmar.getScene().getWindow();
                    stage.close();
                }
                else
                    new Alert(Alert.AlertType.ERROR, "Erro\n" + Banco.getCon().getMensagemErro(), ButtonType.OK)
                            .showAndWait();
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
    private void visualizarExit(MouseEvent event)
    {
        btVisualizar.setStyle(btVisualizar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void visualizarEnter(MouseEvent event)
    {
        btVisualizar.setStyle(btVisualizar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Visualizar Parcelas");
        ToolTip.bindTooltip(btVisualizar, tooltip);
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
        tooltip.setText("Confirmar Informações");
        ToolTip.bindTooltip(btConfirmar, tooltip);
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
        tooltip.setText("Cancelar");
        ToolTip.bindTooltip(btCancelar, tooltip);
    }

    void setCompra(Objeto compra, Label lbSubTotal, int acao)
    {
        lbTitulo.setText(lbTitulo.getText() + "Compra");
        this.lbSubTotal.setText(lbSubTotal.getText().replace("%", "Compra"));
        total = Double.parseDouble(lbSubTotal.getText().substring(lbSubTotal.getText().indexOf("$") + 1)
                .replace(".", "").replace(",", "."));
        this.iscompra = true;
        this.acao = acao;
        this.compra = compra;
        
        if(compra.getParam7() != null && !compra.getParam7().equals(""))
        {
            double val = Double.parseDouble(compra.getParam7());
            rbValor.setSelected(true);
            if(val < 0)
                rbDesconto.setSelected(true);
            else
                rbJuros.setSelected(true);
            tfValorReajuste.setText(Utils.exibeCentavos(val));
        }
    }

    private void setListeners()
    {
        
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
        if(compra.getList2() != null && !compra.getList2().isEmpty())
            for(Objeto obj : compra.getList2())
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

    private void completarCompra()
    {
        ///1 - CÓDIGO, 2 - TRUE SE FOR FORNECEDOR, 3 - CÓDIGO DO FORNECEDOR/CLIENTE, 4 - NOME DO FORNECEDOR/CLIENTE
        ///5 - QTD PARCELAS, 6 - VALOR TOTAL, 7 - AJUSTE, 8 - DATA DA COMPRA, 9 - NOTA FISCAL, 10 - DATA DE EMISSÃO
        ///11 - VENDEDOR, LIST1 - PARCELAS, LIST2 - VEICULOS, 13 - CNPJ/CPF
        
        compra.setParam5(tfNumeroParcelas.getText());
        compra.setParam6(lbSubTotal.getText().substring(lbSubTotal.getText().indexOf(":") + 1).replace("R$", "")
                .replace(".", "").replace(",", "."));
        compra.setParam7(String.valueOf(calculaAjuste()));
    }
    
}
