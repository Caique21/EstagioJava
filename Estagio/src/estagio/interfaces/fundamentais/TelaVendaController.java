/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrCliente;
import estagio.controladores.ctrFabricante;
import estagio.controladores.ctrFornecedor;
import estagio.controladores.ctrVeiculo;
import estagio.controladores.ctrVenda;
import estagio.interfaces.buscas.BuscarVeiculoController;
import estagio.utilidades.Banco;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.TooltippedTableCell;
import estagio.utilidades.Utils;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaVendaController implements Initializable
{
    private final JFXAutoCompletePopup<String> autoCompletePopupClientes = new JFXAutoCompletePopup<>();
    
    private final ctrCliente ctrCli = ctrCliente.instancia();
    private final ctrFornecedor ctrForn = ctrFornecedor.instancia();
    private final ctrVeiculo ctrVei = ctrVeiculo.instancia();
    private final ctrVenda ctrVen = ctrVenda.instancia();
    private final ctrFabricante ctrFab = ctrFabricante.instancia();
    
    private Objeto venda;
    private int acao;
    private double total_venda;
    
    private BorderPane paneCliente;
    
    private final ToggleGroup goup1 = new ToggleGroup();
    private final ToggleGroup goup2 = new ToggleGroup();
    private final ToggleGroup goup3 = new ToggleGroup();
    
    @FXML
    private BorderPane panePrincipal;
    @FXML
    private HBox hbInfoTabela;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane paneInfo;
    @FXML
    private VBox painelCentral;
    @FXML
    private VBox vboxCentral;
    @FXML
    private Pane paneDados;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbDados;
    @FXML
    private Label lbVeiculos;
    @FXML
    private Label lbVencimento;
    @FXML
    private Label lbEmissao;
    @FXML
    private Label lbSubTotal;
    @FXML
    private Label lbErroCliente;
    @FXML
    private Label lbErroNotaFiscal;
    @FXML
    private Label lbInfo;
    @FXML
    private JFXTextField tfNumeroParcelas;
    @FXML
    private JFXTextField tfValorReajuste;
    @FXML
    private JFXTextField tfPorcReajuste;
    @FXML
    private JFXTextField tfCliente;
    @FXML
    private JFXTextField tfNotaFiscal;
    @FXML
    private JFXTextField tfEntrada;
    @FXML
    private JFXRadioButton rbAvista;
    @FXML
    private JFXRadioButton rbParcelado;
    @FXML
    private JFXRadioButton rbJuros;
    @FXML
    private JFXRadioButton rbDesconto;
    @FXML
    private JFXRadioButton rbValor;
    @FXML
    private JFXRadioButton rbPorcentagem;
    @FXML
    private JFXDatePicker dpVencimento;
    @FXML
    private JFXDatePicker dpEmissao;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btAlterar;
    @FXML
    private JFXButton btRemover;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private JFXButton btPesquisarVeiculo;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btVisualizar;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faEdit;
    @FXML
    private FontAwesomeIconView faTrash;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private FontAwesomeIconView faView;
    @FXML
    private FontAwesomeIconView faSearchVeiculo;
    @FXML
    private FontAwesomeIconView faView1;
    @FXML
    private TableView<Objeto> tvVeiculos;
    @FXML
    private TableColumn<Objeto, String> tcMarca;
    @FXML
    private TableColumn<Objeto, String> tcModelo;
    @FXML
    private TableColumn<Objeto, String> tcPlaca;
    @FXML
    private TableColumn<Objeto, String> tcValor;
    @FXML
    private TableColumn<Objeto, String> tcChassi;
    @FXML
    private TableColumn<Objeto, String> tcAno;
    @FXML
    private TableColumn<Objeto, String> tcCor;
    @FXML
    private Label lbErroEmissao;
    @FXML
    private Label lbErroReajuste;
    @FXML
    private Label lbErroParcelas;
    @FXML
    private Label lbErroEntrada;

    /**
     * Initializes the controller class.
     */
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneInfo);
        nodes.add(paneDados);
        
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btNovo);
        nodes.add(btPesquisar);
        nodes.add(btRemover);
        nodes.add(btPesquisarVeiculo);
        nodes.add(btVisualizar);
        
        nodes.add(tfCliente);
        nodes.add(tfEntrada);
        nodes.add(tfNotaFiscal);
        nodes.add(tfNumeroParcelas);
        nodes.add(tfValorReajuste);
        nodes.add(tfPorcReajuste);
       
        nodes.add(lbEmissao);
        nodes.add(lbSubTotal);
        nodes.add(lbVeiculos);
        nodes.add(lbTitulo);
        nodes.add(lbDados);
        nodes.add(lbInfo);
        nodes.add(lbVencimento);
        
        nodes.add(rbAvista);
        nodes.add(rbDesconto);
        nodes.add(rbJuros);
        nodes.add(rbParcelado);
        nodes.add(rbPorcentagem);
        nodes.add(rbValor);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faSearch);
        nodes.add(faView);
        nodes.add(faView1);
        nodes.add(faTrash);
        nodes.add(faSearchVeiculo);
        
        Utils.setDesign(1, nodes);
        
        btNovo.setStyle(btNovo.getStyle() + ";-fx-cursor: default;");
        btAlterar.setStyle(btAlterar.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
        btRemover.setStyle(btRemover.getStyle() + ";-fx-cursor: default;");
        btPesquisarVeiculo.setStyle(btPesquisarVeiculo.getStyle() + ";-fx-cursor: default;");
        btVisualizar.setStyle(btVisualizar.getStyle() + ";-fx-cursor: default;");
        faView.setStyle(faView.getStyle() + ";-fx-cursor: default;");
        faView.setSize("18");
    }
    
    private void redimensiona()
    {
        ////////Tamanho da Tela
        panePrincipal.setPrefWidth(TelaPrincipalController.screenBounds.getMaxX() - 200);
        panePrincipal.setPrefHeight(TelaPrincipalController.screenBounds.getMaxY() - 80);
        
        //PAINEL CENTRAL TIRANDO TITULO E BOTÕES DE COMANDOS
        painelCentral.setPrefHeight(panePrincipal.getPrefHeight() - 47 - 45);
        //PAINEL DE PESQUISA FICA COM O RESTANTE DA TELA
        vboxCentral.setPrefHeight(painelCentral.getPrefHeight() - paneDados.getPrefHeight() -
                paneInfo.getPrefHeight() - 20);
        hbInfoTabela.setPrefWidth(vboxCentral.getPrefWidth());
        pane1.setPrefWidth(hbInfoTabela.getPrefWidth()/2);
        pane2.setPrefWidth(hbInfoTabela.getPrefWidth()/2);
        
        tvVeiculos.setPrefHeight(vboxCentral.getPrefHeight() - 47);
        tvVeiculos.setPrefWidth(vboxCentral.getPrefWidth() - 30);
        
        //REORGANIZA OS TAMANHOS DAS COLUNAS DA TABELA
        int sobra = (int)((tvVeiculos.getPrefWidth() - 1130)/2);
        tcMarca.setPrefWidth(tcMarca.getPrefWidth() + sobra);
        tcModelo.setPrefWidth(tcModelo.getPrefWidth() + sobra);
    }
    
    private void limpaLabels()
    {
        lbErroCliente.setText("");
        lbErroNotaFiscal.setText("");
        lbErroEmissao.setText("");
        lbErroEntrada.setText("");
        lbErroParcelas.setText("");
        lbErroReajuste.setText("");
    }
    
    private void limpaCampoDados()
    {
        tfCliente.clear();
        tfNotaFiscal.clear();
        dpEmissao.setValue(LocalDate.now());
    }
    
    private void atualizaListaFornecedores(String... nome)
    {   
        autoCompletePopupClientes.getSuggestions().clear();
        autoCompletePopupClientes.getSuggestions().add("FORNECEDORES");
        autoCompletePopupClientes.getSuggestions().addAll(ctrForn.getAllNames(nome));
        autoCompletePopupClientes.getSuggestions().addAll("","CLIENTES");
        autoCompletePopupClientes.getSuggestions().addAll(ctrCli.getAllNames(nome));
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6,Boolean b7,Boolean b8)
    {
        if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            paneDados.setDisable(true);
            vboxCentral.setDisable(true);
            paneInfo.setDisable(true);
            btNovo.setDisable(true);
            btConfirmar.setDisable(true);
            btRemover.setDisable(true);
            btAlterar.setDisable(true);
        }
        else
        {
            paneDados.setDisable(b1);
            vboxCentral.setDisable(b1);
            paneInfo.setDisable(b2);
            btNovo.setDisable(b3);
            btConfirmar.setDisable(b4);
            btRemover.setDisable(b5);
            btAlterar.setDisable(b6);
        }
        btPesquisar.setDisable(b7);
        btCancelar.setDisable(b8);
    }
    
    private void inicializa()
    {
        limpaLabels();
        limpaCampoDados();
        tvVeiculos.getItems().clear();
        dpEmissao.setValue(LocalDate.now());
        venda = null;
        tfEntrada.setText("0");
        tfNumeroParcelas.setText("1");
        tfValorReajuste.setText("0");
        tfEntrada.setDisable(true);
        
        atualizaListaFornecedores("");
        
        setEstado(true, true, false, true, true, true, false, true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesign();
        redimensiona();
        
        rbAvista.setToggleGroup(goup1);
        rbParcelado.setToggleGroup(goup1);
        rbAvista.setSelected(true);
        
        rbJuros.setToggleGroup(goup2);
        rbDesconto.setToggleGroup(goup2);
        rbJuros.setSelected(true);
        
        rbValor.setToggleGroup(goup3);
        rbPorcentagem.setToggleGroup(goup3);
        rbValor.setSelected(true);
        
        MaskFieldUtil.monetaryField(tfEntrada);
        MaskFieldUtil.monetaryField(tfValorReajuste);
        MaskFieldUtil.numericField(tfPorcReajuste);
        MaskFieldUtil.numericField(tfNumeroParcelas);
        
        tcAno.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcChassi.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcCor.setCellValueFactory(new PropertyValueFactory<>("param6"));
        tcMarca.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcModelo.setCellValueFactory(new PropertyValueFactory<>("param8"));
        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param10"));
        
        tcMarca.setCellFactory(TooltippedTableCell.forTableColumn());
        tcModelo.setCellFactory(TooltippedTableCell.forTableColumn());
        
        inicializa();
        setlisteners();
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        inicializa();
        acao = 0;
        setEstado(false, false, true, false, true, true, true, false);
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(validaVenda())
        {
            Alert a;
            if(acao == 0)
            {
                a = new Alert(Alert.AlertType.CONFIRMATION, "Confirmar cadastro da venda?", ButtonType.YES,ButtonType.NO);
                a.showAndWait();
                
                if(a.getResult() == ButtonType.YES)
                {
                    gerarObjeto();
                    if(ctrVen.salvar(venda, dpVencimento.getValue(), tfEntrada.getText()))
                    {
                        inicializa();
                        a = new Alert(Alert.AlertType.INFORMATION, "Venda cadastrada com sucesso!!!", ButtonType.OK);
                    }
                    else
                        a = new Alert(Alert.AlertType.ERROR, "Erro no cadastro da venda\n" + 
                                Banco.getCon().getMensagemErro(), ButtonType.OK);
                    a.showAndWait();
                }
            }
            else if(acao == 1 && venda != null)
            {
                a = new Alert(Alert.AlertType.CONFIRMATION, "Alterar cadastro da venda?", ButtonType.YES,ButtonType.NO);
                a.showAndWait();
                
                if(a.getResult() == ButtonType.YES)
                {
                    gerarObjeto();
                    if(ctrVen.alterar(venda, dpVencimento.getValue(), tfEntrada.getText()))
                    {
                        inicializa();
                        a = new Alert(Alert.AlertType.INFORMATION, "Venda alterada com sucesso!!!", ButtonType.OK);
                    }
                    else
                        a = new Alert(Alert.AlertType.ERROR, "Erro na alteração da venda\n" + 
                                Banco.getCon().getMensagemErro(), ButtonType.OK);
                    a.showAndWait();
                }
            }
        }
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        acao = 1;
        setEstado(false, false, true, false, true, true, true, false);
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja excluir venda?", ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        
        if(a.getResult() == ButtonType.YES && venda != null)
        {
            if(ctrVen.apagar(Integer.parseInt(venda.getParam1())))
            {
                inicializa();
                a = new Alert(Alert.AlertType.CONFIRMATION, "Venda excluída com sucesso!!!!", ButtonType.OK);
            }
            else
                a = new Alert(Alert.AlertType.ERROR, "Erro na remoção da venda\n" + 
                        Banco.getCon().getMensagemErro(), ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        inicializa();
    }

    @FXML
    private void clickPesquisarVeiculo(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/buscas/BuscarVeiculo.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            JFXDecorator decorator = new JFXDecorator(stage, root);

            BuscarVeiculoController controller = fxmlLoader.<BuscarVeiculoController>getController();
            decorator.setStyle("-fx-decorator-color: #040921;");
            Scene scene = new Scene(decorator);

            stage.setTitle("Buscar Compra");
            stage.setScene(scene);
            stage.showAndWait();
            
            tvVeiculos.setItems(controller.getVeiculos());
        }
        catch (IOException er)
        {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Pesquisa de Veículo! "
                    + "\nErro: " + er.getMessage(), ButtonType.OK).showAndWait();
        }
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
        
        TableColumn<Objeto, String> tcvalor = new TableColumn<>("Valor");
        tcvalor.setPrefWidth(200);
        tcvalor.setCellValueFactory(new PropertyValueFactory<>("param1"));
        
        TableColumn<Objeto, String> tcNumero = new TableColumn<>("Nº Parcela");
        tcNumero.setPrefWidth(120);
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("param2"));
        
        TableColumn<Objeto, String> tcVencimento = new TableColumn<>("Data vencimento");
        tcVencimento.setPrefWidth(tvParcelas.getPrefWidth() - 320);
        tcVencimento.setCellValueFactory(new PropertyValueFactory<>("param3"));
        
        tvParcelas.getColumns().addAll(tcvalor, tcNumero, tcVencimento);

        if (!tfNumeroParcelas.getText().trim().equals(""))
        {
            if (Integer.parseInt(tfNumeroParcelas.getText()) == 1)
                tvParcelas.getItems().add(new Objeto(String.valueOf(total_venda), String.valueOf(1),
                        String.valueOf(dpVencimento.getValue().toString())));
            else
            {
                double val = 0.0;
                for (int i = 0; i < Integer.parseInt(tfNumeroParcelas.getText()); i++)
                {
                    if (i < Integer.parseInt(tfNumeroParcelas.getText()) - 1)
                    {
                        val += Utils.truncate(total_venda / Integer.parseInt(tfNumeroParcelas.getText()));
                        tvParcelas.getItems().add(new Objeto(
                            String.valueOf(Utils.truncate(total_venda / Integer.parseInt(tfNumeroParcelas.getText()))), String.valueOf(i + 1),
                            String.valueOf(dpVencimento.getValue().toString())));
                    }
                    else
                    {
                        tvParcelas.getItems().add(new Objeto(String.valueOf(total_venda - val), 
                            String.valueOf(i + 1), String.valueOf(dpVencimento.getValue().toString())));
                    }
                }
            }
        }
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
    private void pesquisarExit(MouseEvent event)
    {
    }

    @FXML
    private void pesquisarEnter(MouseEvent event)
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
    private void clienteEnter(MouseEvent event)
    {
    }

    @FXML
    private void clienteExit(MouseEvent event)
    {
    }

    @FXML
    private void visualizarExit(MouseEvent event)
    {
    }

    @FXML
    private void visualizarEnter(MouseEvent event)
    {
    }
    
    private double calculaAjuste()
    {
        return total_venda - calculaTotal();
    }

    private void atualizaTotal()
    {
        total_venda = calculaTotal();
        double nvalor;
        
        if(rbDesconto.isSelected())
        {
            if(rbValor.isSelected())
                total_venda -= Double.parseDouble(tfValorReajuste.getText().replace(".", "").replace(",", "."));
            else if(rbPorcentagem.isSelected())
            {
                nvalor = 100 - (Integer.parseInt(tfPorcReajuste.getText()));
                total_venda = Utils.truncate((total_venda * (nvalor / 100)));
            }
        }
        else if(rbJuros.isSelected())
        {
            if(rbValor.isSelected())
                total_venda += Double.parseDouble(tfValorReajuste.getText().replace(".", "").replace(",", "."));
            else if(rbPorcentagem.isSelected())
            {
                nvalor = 100 + (Integer.parseInt(tfPorcReajuste.getText()));
                total_venda = Utils.truncate((total_venda * (nvalor / 100)));
            }
        }
        
        lbSubTotal.setText("Total da Compra: " + NumberFormat.getCurrencyInstance().format(total_venda));
    }

    private double calculaTotal()
    {
        double total = 0.0;
        if(venda.getList2() != null && !venda.getList2().isEmpty())
            for(Objeto obj : venda.getList2())
                total += Double.parseDouble(obj.getParam10().replace(".", "").replace(",", "."));
        return total;
    }

    private void setlisteners()
    {
        rbAvista.selectedProperty().addListener((observable) ->
        {
            if(rbAvista.isSelected())
            {
                tfNumeroParcelas.setText("1");
                tfEntrada.setEditable(false);
            }
        });
        
        rbParcelado.selectedProperty().addListener((observable) ->
        {
            if(rbParcelado.isSelected())
            {
                tfNumeroParcelas.requestFocus();
                tfEntrada.setEditable(true);
            }
        });
        
        tfEntrada.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!tvVeiculos.getItems().isEmpty() && !tfEntrada.getText().trim().equals(""))
            {
                if(Utils.convertStringToDouble(newValue) < 0 || Utils.convertStringToDouble(newValue) > total_venda)
                    tfEntrada.setText(oldValue);
            }
        });
        
        tfEntrada.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfEntrada.getText().trim().equals(""))
                    tfEntrada.setText("0");
            }
        });
        
        tfNumeroParcelas.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(Integer.parseInt(tfNumeroParcelas.getText()) < 1 || Integer.parseInt(tfNumeroParcelas.getText()) > 96)
                tfNumeroParcelas.setText(oldValue);
        });
        
        rbJuros.selectedProperty().addListener((observable) ->
        {
            if(rbJuros.isSelected())
            {
                if (rbValor.isSelected())
                {
                    tfValorReajuste.setPromptText("Juros(R$)");
                    tfValorReajuste.setText("0");
                }
                else if (rbPorcentagem.isSelected())
                {
                    tfPorcReajuste.setPromptText("Juros(%)");
                    tfPorcReajuste.setText("0");
                }
                atualizaTotal();
            }
        });
        
        rbDesconto.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue)
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
        });
        
        rbValor.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue)
            {
                tfPorcReajuste.setVisible(false);
                tfValorReajuste.setVisible(true);
                tfValorReajuste.clear();
                atualizaTotal();
            }
        });
        
        rbPorcentagem.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue)
            {
                tfPorcReajuste.setVisible(true);
                tfValorReajuste.setVisible(false);
                tfPorcReajuste.clear();
                atualizaTotal();
            }
        });
        
        tfValorReajuste.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(tfValorReajuste.isVisible() && !tfValorReajuste.getText().trim().equals(""))
            {
                if(Utils.convertStringToDouble(tfValorReajuste.getText()) < 0 || 
                    Utils.convertStringToDouble(tfValorReajuste.getText()) > total_venda)
                    tfValorReajuste.setText(oldValue);
            }
        });
        
        tfValorReajuste.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfValorReajuste.getText().trim().equals(""))
                    tfValorReajuste.setText("0");
            }
        });
        
        tfPorcReajuste.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(tfPorcReajuste.isVisible() && !tfPorcReajuste.getText().trim().equals(""))
                if(Integer.parseInt(newValue) < 0 || Integer.parseInt(newValue) > 100)
                    tfPorcReajuste.setText(oldValue);
        });
        
        tfPorcReajuste.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
           if(!newValue)
               if(tfPorcReajuste.getText().trim().equals(""))
                   tfPorcReajuste.setText("0");
        });
        
        autoCompletePopupClientes.setSelectionHandler(event ->
        {
            tfCliente.setText(event.getObject());
        });
        
        tfCliente.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
                if(tfCliente.getText().equals("FORNECEDORES") || tfCliente.getText().equals("CLIENTES"))
                    tfCliente.setText("");
        });

        tfCliente.textProperty().addListener((Observable observable) ->
        {
            //autoCompletePopupClientes.filter(string -> strings.toLowerCase().contains(tfCliente.getText().toLowerCase()));
            atualizaListaFornecedores(tfCliente.getText());
            
            if(autoCompletePopupClientes.getSuggestions().isEmpty())
            {
                autoCompletePopupClientes.hide();
            }
            else
            {    
                if(tfCliente.isFocused())
                {
                    autoCompletePopupClientes.show(tfCliente);
                    paneCliente = criarPaneFornecedor();
                }
            }
        });
        
        tfCliente.setOnMouseClicked((event) ->
        {
            tfCliente.requestFocus();
            if(!autoCompletePopupClientes.isShowing())
            {
                autoCompletePopupClientes.show(tfCliente);
            }
            else
                autoCompletePopupClientes.hide();
        });
    }

    private BorderPane criarPaneFornecedor()
    {
        if(!tfCliente.getText().equals("") && !tfCliente.getText().equals("FORNECEDORES") && 
            !tfCliente.getText().equals("CLIENTES"))
        {
            BorderPane pane = new BorderPane();
        
            Label titulo = new Label();
            HBox hbox1 = new HBox(10),hbox2 = new HBox(10);
            hbox1.setPadding(new Insets(10, 10, 10, 10));
            hbox2.setPadding(new Insets(0, 10, 0, 10));
            Objeto obj;

            JFXTextField nome = new JFXTextField();
            nome.setLabelFloat(true);
            nome.setPromptText("Nome");
            
            JFXTextField email = new JFXTextField();
            email.setLabelFloat(true);
            email.setPromptText("Email");
            email.setPrefWidth(200);
            
            TextArea endereco = new TextArea();
            endereco.setPrefWidth(pane.getWidth() - 20);
            endereco.setPrefHeight(100);
            endereco.setWrapText(true);
            
            if(isCliente())
            {
                titulo.setText("CLIENTE");
                obj = ctrCli.getByCPF(tfCliente.getText().substring(tfCliente.getText().indexOf(", ") + 2)
                    .trim()).get(0);
                
                JFXTextField cpf = new JFXTextField(obj.getParam3());
                cpf.setLabelFloat(true);
                cpf.setPromptText("CPF");
                
                JFXTextField rg = new JFXTextField(obj.getParam4());
                rg.setLabelFloat(true);
                rg.setPromptText("RG");
                
                email.setText(obj.getParam8());
                hbox1.getChildren().addAll(nome,cpf,rg,email);
            }
            else
            {
                titulo.setText("FORNECEDOR");
                
                obj = ctrForn.getByCNPJ(tfCliente.getText().substring(tfCliente.getText().indexOf(", ") + 2)
                    .trim()).get(0);
                
                JFXTextField cnpj = new JFXTextField(obj.getParam3());
                cnpj.setLabelFloat(true);
                cnpj.setPromptText("CNPJ");
                
                email.setText(obj.getParam4());
                hbox1.getChildren().addAll(nome,cnpj,email);
            }
            
            nome.setText(obj.getParam2());
            endereco.setText(obj.getParam7());
            
            VBox vbox = new VBox(5,hbox1,endereco);
            pane.setTop(titulo);
            pane.setBottom(vbox);
            return pane;
        }
        return null;
    }

    private boolean isCliente()
    {
        return !tfCliente.getText().contains("/");
    }

    private boolean validaVenda()
    {
        String erros = "";
        limpaLabels();
        
        if(tfCliente.getText().trim().equals("") || tfCliente.getText().equals("FORNECEDORES") || 
             tfCliente.getText().equals("CLIENTES"))
        {
            lbErroCliente.setText("Cliente/Fornecedor inválido");
            erros += "Cliente/Fornecedor inválido\n";
        }
        
        if(tfNotaFiscal.getText().trim().equals(""))
        {
            lbErroNotaFiscal.setText("Campo requerido");
            erros += "Digite a nota fiscal\n";
        }
        
        if(acao == 0 && dpEmissao.getValue().compareTo(LocalDate.now()) != 0)
        {
            lbErroEmissao.setText("Data inválida");
            erros += "Data de emissão de uma nova venda deve ser a data da realização da venda\n";
        }
        
        if(tvVeiculos.getItems().isEmpty())
            erros += "Tabela de veículos vazia\n";
        
        if(tfValorReajuste.isVisible())
        {
            if(tfValorReajuste.getText().trim().equals(""))
            {
                lbErroReajuste.setText("Campo requerido");
                erros += "Digite o valor do reajuste\n";
            }
            else if(Utils.convertStringToDouble(tfValorReajuste.getText()) < 0 || 
                Utils.convertStringToDouble(tfValorReajuste.getText()) > total_venda)
            {
                lbErroReajuste.setText("Valor inválido");
                erros += "Valor do reajuste inválido\n";
            }
        }
        else if(tfPorcReajuste.isVisible())
        {
            if(tfPorcReajuste.getText().trim().equals(""))
            {
                erros += "Digite o valor do reajuste\n";
                lbErroReajuste.setText("Campo requerido");
            }
            else if(Integer.parseInt(tfPorcReajuste.getText()) < 0 || Integer.parseInt(tfPorcReajuste.getText()) > 100)
            {
                erros += "Valor de reajuste inválido\n";
                lbErroReajuste.setText("Valor inválido");
            }
        }
        
        if(rbAvista.isSelected())
        {
            if(tfNumeroParcelas.getText().trim().equals(""))
            {
                erros += "Digite o número de parcelas\n";
                lbErroParcelas.setText("Campo requerido");
            }
            if(Integer.parseInt(tfNumeroParcelas.getText()) != 1)
            {
                erros += "Digite um número de parcelas válida\n";
                lbErroParcelas.setText("Número de parcelas inválida");
            }
        }
        else if(rbParcelado.isSelected())
        {
            if(tfNumeroParcelas.getText().trim().equals(""))
            {
                erros += "Digite o número de parcelas\n";
                lbErroParcelas.setText("Campo requerido");
            }
            if(Integer.parseInt(tfNumeroParcelas.getText()) < 1 || Integer.parseInt(tfNumeroParcelas.getText()) > 96)
            {
                erros += "Digite um número de parcelas válida\n";
                lbErroParcelas.setText("Número de parcelas inválida");
            }
        }
        
        if(!tfEntrada.getText().equals(""))
        {
            if(Utils.convertStringToDouble(tfEntrada.getText()) < 0 || 
                    Utils.convertStringToDouble(tfEntrada.getText()) > total_venda)
            {
                erros += "Digite um valor de entrada válida\n";
                lbErroEntrada.setText("Valor da entrada inválida");
            }
        }
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        return erros.trim().equals("");
    }

    private void gerarObjeto()
    {
        ///1 - CÓDIGO, 2 - TRUE SE FOR FORNECEDOR, 3 - CÓDIGO DO FORNECEDOR/CLIENTE, 4 - NOME DO FORNECEDOR/CLIENTE
        ///5 - QTD PARCELAS, 6 - VALOR TOTAL, 7 - AJUSTE, 8 - DATA DA COMPRA, 9 - NOTA FISCAL, 10 - DATA DE EMISSÃO
        ///LIST1 - PARCELAS, LIST2 - VEICULOS, 13 - CNPJ/CPF
        if(venda == null)
        {
            venda = new Objeto("0");
            venda.setParam8(String.valueOf(LocalDate.now()));
        }
        venda.setParam4(tfCliente.getText().substring(0, tfCliente.getText().indexOf(",")));
        venda.setParam5(tfNumeroParcelas.getText());
        venda.setParam6(String.valueOf(total_venda));
        venda.setParam7(String.valueOf(calculaAjuste()));
        venda.setParam9(tfNotaFiscal.getText());
        venda.setParam10(String.valueOf(dpEmissao.getValue()));
        venda.setParam13(tfCliente.getText().substring(tfCliente.getText().indexOf(", ") + 1).trim());
        
        if(isCliente())
        {
            venda.setParam2(String.valueOf(false));
            venda.setParam3(ctrForn.getByCNPJ(venda.getParam13()).get(0).getParam1());
        }
        else
        {
            venda.setParam2(String.valueOf(true));
            venda.setParam3(ctrCli.getByCPF(venda.getParam13()).get(0).getParam1());
        }
        
        tvVeiculos.getItems().forEach((o) ->
        {
            venda.addList2(o);
        });
    }
    
}
