/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.Utils;

/**
 * FXML Controller class
 *
 * @author carlo
 */
public class ParcelasManuaisController implements Initializable
{
    private final Tooltip tooltip = new Tooltip();
    
    private ArrayList<Objeto> parcelas;
    private double val_acomulado = 0.0;
    private boolean controle;

    @FXML
    private TableView<Objeto> tvParcelas;
    @FXML
    private TableColumn<Objeto, String> tcNumero;
    @FXML
    private TableColumn<Objeto, String> tcValor;
    @FXML
    private TableColumn<Objeto, String> tcData;
    @FXML
    private JFXButton btGerarParcelas;
    @FXML
    private JFXTextField tfParcelas;
    @FXML
    private Label lbTotal;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private Pane panePrincipal;
    @FXML
    private Label lbTitulo;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private FontAwesomeIconView faEdit;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("param1"));
        tcNumero.setSortType(TableColumn.SortType.DESCENDING);
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcData.setCellValueFactory(new PropertyValueFactory<>("param3"));
        
        MaskFieldUtil.numericField(tfParcelas);
        
        tfParcelas.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(Integer.parseInt(newValue) < 0 || Integer.parseInt(newValue) > 96)
                tfParcelas.setText(oldValue);
        });
        
        tfParcelas.focusedProperty().addListener((ov, oldV, newV) ->
        {
            if (!newV)
                if(tfParcelas.getText().trim().equals(""))
                    tfParcelas.setText("1");
        });
        controle = false;
        
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: default;");
        btGerarParcelas.setStyle(btGerarParcelas.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
    }    

    @FXML
    private void gerarExit(MouseEvent event)
    {
        btGerarParcelas.setStyle("-fx-cursor: default;" + btGerarParcelas.getStyle());
    }

    @FXML
    private void gerarEnter(MouseEvent event)
    {
        btGerarParcelas.setStyle("-fx-cursor: hand;" + btGerarParcelas.getStyle());
        tooltip.setText("Clique aqui para gerar as parcelas");
        ToolTip.bindTooltip(btGerarParcelas, tooltip);
    }

    @FXML
    private void clickGerar(ActionEvent event)
    {
        if(!tfParcelas.getText().trim().equals(""))
        {
            double valor = Utils.convertStringToDouble(lbTotal.getText().substring(lbTotal.getText().indexOf("$") + 1));
            val_acomulado = 0.0;
            parcelas = new ArrayList<>();
            boolean flag = true;
            Dialog<String> dialog;
            final Label label = new Label();
            final Label subtotal = new Label();
            final TextField tf = new TextField();
            final DatePicker dp = new DatePicker();

            for (int i = 0; i < Integer.parseInt(tfParcelas.getText()) && flag; i++)
            {
                dialog = new Dialog<>();
                dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                VBox vbox = new VBox();
                vbox.setSpacing(10);

                label.setText("Parcela " + (i + 1));
                subtotal.setText("SubTotal: R$ " + val_acomulado);
                tf.setText("");
                tf.setPromptText("Valor da parcela");
                MaskFieldUtil.monetaryField(tf);
                dp.setValue(LocalDate.now());
                tf.requestFocus();
                
                if(i == (Integer.parseInt(tfParcelas.getText()) - 1))
                {
                    DecimalFormat df = new DecimalFormat("#.00");
                    String val = df.format(valor - val_acomulado);
                    tf.setText(val);
                    tf.setEditable(false);
                }
                
                tf.focusedProperty().addListener((ov, oldV, newV) ->
                {
                    if (!newV)
                    {
                        if(val_acomulado + 
                            Double.parseDouble(tf.getText().replace(".", "").replace(",", ".")) > valor
                            || tf.getText().equals(""))
                        {
                            new Alert(Alert.AlertType.ERROR, "Valor inválido", ButtonType.OK).showAndWait();
                            tf.clear();
                            tf.requestFocus();
                        }
                    }
                });

                vbox.getChildren().addAll(label,tf,dp,subtotal);
                dialog.getDialogPane().setContent(vbox);
                dialog.setResultConverter(dialogButton -> 
                {
                    if(Integer.parseInt(label.getText().substring(label.getText().indexOf(" ") + 1)) > 1
                            && dialogButton != ButtonType.CANCEL)
                    {
                        while(!validaData(dp.getValue(),label.getText().substring(label.getText().indexOf(" ") + 1))
                                && dialogButton != ButtonType.CANCEL)
                        {
                            new Alert(Alert.AlertType.ERROR, "Data inválida", ButtonType.OK).showAndWait();
                            dp.requestFocus();
                            try
                            {
                                dp.wait(10000);
                            }
                            catch (InterruptedException ex)
                            {
                                Logger.getLogger(ParcelasManuaisController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    if(Integer.parseInt(label.getText().substring(label.getText().indexOf(" ") + 1)) > 1
                        && validaData(dp.getValue(),label.getText().substring(label.getText().indexOf(" ") + 1))
                        || Integer.parseInt(label.getText().substring(label.getText().indexOf(" ") + 1)) == 1)
                    {
                       if (dialogButton == ButtonType.OK) 
                        {
                            add(Double.parseDouble(tf.getText().replace(".", "").replace(",", ".")));
                            return label.getText() + ":" + tf.getText() + ":" + dp.getValue();
                        } 
                    }
                    return null;
                });
                Optional<String> result = dialog.showAndWait();
                flag = result.isPresent();
                
                if(flag)
                {
                    String[] param = result.toString().split(":");
                    parcelas.add(new Objeto(param[0].substring(param[0].indexOf(" ") + 1), param[1], param[2].replace("]", "")));
                }
            }
            if(!flag)
                parcelas = new ArrayList<>();
            
            tvParcelas.setItems(FXCollections.observableArrayList(parcelas));
        }
        
    }

    private boolean validaData(LocalDate value,String i)
    {
        int pos = Integer.parseInt(i) - 2;
        return parcelas.get(pos).getParam3() != null && 
                    value.compareTo(LocalDate.parse(parcelas.get(pos).getParam3())) > 0;
    }

    @FXML
    private void parcelasPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickGerar(new ActionEvent());
    }

    void setTotal(String text)
    {
        lbTotal.setText(lbTotal.getText() + text.replace("R$", ""));
    }
    
    void setParcelas(ArrayList<Objeto> parcelas)
    {
        tvParcelas.getItems().clear();
        for(Objeto parcela : parcelas)
        {
            tvParcelas.getItems().add(new Objeto(parcela.getParam5(),parcela.getParam6(), 
                parcela.getParam3(),parcela.getParam4()));
        }
        tvParcelas.getItems();
    }

    private void add(double parseDouble)
    {
        val_acomulado += parseDouble;
    }

    ArrayList<Objeto> getParcelas()
    {
        if(controle)
            return parcelas;
        return null;
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(!tvParcelas.getItems().isEmpty())
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja confirmar cadastro das parcelas?", ButtonType.YES,ButtonType.NO);
            a.showAndWait();
        
            if(a.getResult() == ButtonType.YES)
            {
                Stage stage = (Stage) btConfirmar.getScene().getWindow();
                stage.close();
                controle = true;
            }
        }
        else
        {
            controle  = false;
            new Alert(Alert.AlertType.ERROR, "Nenhuma parcela cadastrada", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cancelar o cadastro das parcelas?", ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        
        if(a.getResult() == ButtonType.YES)
        {
            controle = false;
            
            Stage stage = (Stage) btConfirmar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void selecionaExit(MouseEvent event)
    {
        btConfirmar.setStyle("-fx-cursor: default;" + btConfirmar.getStyle());
    }

    @FXML
    private void selecionaEnter(MouseEvent event)
    {
        btConfirmar.setStyle("-fx-cursor: hand;" + btConfirmar.getStyle());
    }

    @FXML
    private void cancelaExit(MouseEvent event)
    {
        btCancelar.setStyle("-fx-cursor: default;" + btCancelar.getStyle());
    }

    @FXML
    private void cancelaEnter(MouseEvent event)
    {
        btCancelar.setStyle("-fx-cursor: hand;" + btCancelar.getStyle());
    }
}
