/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrFuncionario;
import estagio.controladores.ctrTransporte;
import estagio.controladores.ctrVeiculo;
import estagio.interfaces.basicas.CadastroVeiculoController;
import estagio.interfaces.buscas.BuscarVeiculoController;
import estagio.utilidades.Banco;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.TooltippedTableCell;
import estagio.utilidades.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaTransporteController implements Initializable
{
    private final Tooltip tooltip = new Tooltip();
    private final JFXAutoCompletePopup<String> autoCompletePopupFuncionarios = new JFXAutoCompletePopup<>();
    
    private final ctrTransporte ctrTran = ctrTransporte.instancia();
    private final ctrFuncionario ctrFunc = ctrFuncionario.instancia();
    private final ctrVeiculo ctrVei = ctrVeiculo.instancia();
    
    private int acao;
    private Objeto transporte;
    private String transporte_selecionado;

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private VBox paneLateral;
    @FXML
    private Pane paneDados;
    @FXML
    private Pane paneVeiculos;
    @FXML
    private ListView<String> lvTransportes;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbTransporte;
    @FXML
    private Label lbDados;
    @FXML
    private Label lbSaida;
    @FXML
    private Label lbChegada;
    @FXML
    private Label lbVeiculos;
    @FXML
    private Label lbUltimaAlteracao;
    @FXML
    private Label lbErroMotorista;
    @FXML
    private Label lbErroPlacaCegonha;
    @FXML
    private Label lbErroSaida;
    @FXML
    private Label lbErroChegada;
    @FXML
    private Label lbErroStatus;
    @FXML
    private Label lbErroTipo;
    @FXML
    private JFXTextField tfMotorista;
    @FXML
    private JFXTextField tfPlacaCegonha;
    @FXML
    private JFXDatePicker dpSaida;
    @FXML
    private JFXDatePicker dpChegada;
    @FXML
    private JFXComboBox<String> cbStatus;
    @FXML
    private JFXComboBox<String> cbTipo;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private JFXButton btAdicionarVeiculo;
    @FXML
    private JFXButton btRemoverVeiculo;
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
    private FontAwesomeIconView faSearch;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private FontAwesomeIconView faMinus;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private FontAwesomeIconView faEdit;
    @FXML
    private FontAwesomeIconView faTrash;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private TableView<Objeto> tvVeiculos;
    @FXML
    private TableColumn<Objeto,String> tcMarca;
    @FXML
    private TableColumn<Objeto,String> tcModelo;
    @FXML
    private TableColumn<Objeto,String> tcPlaca;
    @FXML
    private TableColumn<Objeto,String> tcChassi;
    @FXML
    private TableColumn<Objeto,String> tcAno;
    @FXML
    private TableColumn<Objeto,String> tcCor;
    @FXML
    private VBox painelCentral;
    @FXML
    private VBox vboxTable;
    @FXML
    private FontAwesomeIconView faPlus2;
    @FXML
    private JFXCheckBox cbFinalizados;

    /**
     * Initializes the controller class.
     */
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneLateral);
        nodes.add(paneDados);
        nodes.add(paneVeiculos);
        
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btNovo);
        nodes.add(btPesquisar);
        nodes.add(btRemover);
        nodes.add(btAdicionarVeiculo);
        nodes.add(btRemoverVeiculo);
        
        nodes.add(tfMotorista);
        nodes.add(tfPlacaCegonha);
        
        nodes.add(cbStatus);
        nodes.add(cbTipo);
        
        nodes.add(lvTransportes);
        
        nodes.add(cbFinalizados);
       
        nodes.add(lbChegada);
        nodes.add(lbSaida);
        nodes.add(lbVeiculos);
        nodes.add(lbTitulo);
        nodes.add(lbDados);
        nodes.add(lbTransporte);
        nodes.add(lbUltimaAlteracao);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faPlus2);
        nodes.add(faSearch);
        nodes.add(faMinus);
        nodes.add(faTrash);
        
        Utils.setDesign(1, nodes);
        
        btNovo.setStyle(btNovo.getStyle() + ";-fx-cursor: default;");
        btConfirmar.setStyle(btConfirmar.getStyle() + ";-fx-cursor: default;");
        btAlterar.setStyle(btAlterar.getStyle() + ";-fx-cursor: default;");
        btRemover.setStyle(btRemover.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
        btRemoverVeiculo.setStyle(btRemoverVeiculo.getStyle() + ";-fx-cursor: default;");
        btAdicionarVeiculo.setStyle(btAdicionarVeiculo.getStyle() + ";-fx-cursor: default;");
    }
    
    private void redimensiona()
    {
        ////////Tamanho da Tela
        panePrincipal.setPrefWidth(TelaPrincipalController.screenBounds.getMaxX() - 200);
        panePrincipal.setPrefHeight(TelaPrincipalController.screenBounds.getMaxY() - 80);
        
        //PAINEL CENTRAL TIRANDO TITULO E BOTÕES DE COMANDOS
        painelCentral.setPrefHeight(panePrincipal.getPrefHeight() - 47 - 45);
        painelCentral.setPrefWidth(panePrincipal.getPrefWidth() - paneLateral.getPrefWidth() - 30);
        
        paneVeiculos.setPrefHeight(painelCentral.getPrefHeight() - paneDados.getPrefHeight() - 10);
        
        vboxTable.setPrefHeight(paneVeiculos.getPrefHeight() - 70);
        vboxTable.setPrefWidth(paneVeiculos.getPrefWidth());
        tvVeiculos.setPrefHeight(painelCentral.getPrefHeight() - 30);
        tvVeiculos.setPrefWidth(vboxTable.getPrefWidth()- 20);
        
        //REORGANIZA OS TAMANHOS DAS COLUNAS DA TABELA
        int sobra = (int)((tvVeiculos.getPrefWidth() - 953)/2);
        tcMarca.setPrefWidth(tcMarca.getPrefWidth() + sobra);
        tcModelo.setPrefWidth(tcModelo.getPrefWidth() + sobra);
    }
    
    private void limpaLabels()
    {
        lbErroChegada.setText("");
        lbErroMotorista.setText("");
        lbErroPlacaCegonha.setText("");
        lbErroSaida.setText("");
        lbErroStatus.setText("");
        lbErroTipo.setText("");
    }
    
    private void limpaCampos()
    {
        tfMotorista.clear();
        tfPlacaCegonha.clear();
        dpSaida.setValue(LocalDate.now());
        dpChegada.getEditor().clear();
        tvVeiculos.getItems().clear();
        cbStatus.getSelectionModel().select(-1);
        cbTipo.getSelectionModel().select(-1);
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6)
    {
        if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            painelCentral.setDisable(true);
            btNovo.setDisable(true);
            btConfirmar.setDisable(true);
            btRemover.setDisable(true);
            btAlterar.setDisable(true);
        }
        else
        {
            painelCentral.setDisable(b1);
            btNovo.setDisable(b2);
            btConfirmar.setDisable(b3);
            btRemover.setDisable(b5);
            btAlterar.setDisable(b4);
        }
        btCancelar.setDisable(b6);
    }

    private void atualizaListaFornecedores(String text)
    {
        autoCompletePopupFuncionarios.getSuggestions().clear();
        autoCompletePopupFuncionarios.getSuggestions().add("NOVO FUNCIONÁRIO");
        autoCompletePopupFuncionarios.getSuggestions().addAll(ctrFunc.getAllNames(text));
    }
    
    private void inicializa()
    {
        limpaLabels();
        limpaCampos();
        
        lvTransportes.getItems().clear();
        ctrTran.getAll().forEach((objeto) ->
        {
            lvTransportes.getItems().add("Código de Transporte: " + objeto.getParam1() + "\nStatus: " + 
                    objeto.getParam6());
        });
        
        acao = -1;
        transporte = null;
        transporte_selecionado = null;
        lbUltimaAlteracao.setText("Data da Última Alteração: ");
        
        atualizaListaFornecedores("");
        
        setEstado(true, false, true, true, true, false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesign();
        redimensiona();
        
        cbStatus.getItems().addAll("Á Iniciar","Em Progresso","Parado","Finalizado");
        cbTipo.getItems().addAll("Agregado","Terceirizado");
        
        MaskFieldUtil.toUpperCase(tfPlacaCegonha);
        MaskFieldUtil.maxField(tfPlacaCegonha, 8);
        
        tcAno.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcChassi.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcCor.setCellValueFactory(new PropertyValueFactory<>("param6"));
        tcMarca.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcModelo.setCellValueFactory(new PropertyValueFactory<>("param8"));
        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("param2"));
        
        tcMarca.setCellFactory(TooltippedTableCell.forTableColumn());
        tcModelo.setCellFactory(TooltippedTableCell.forTableColumn());
        
        inicializa();
        setlisteners();
        
        inicializaToolTipListView();
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        inicializa();
        acao = 0;
        setEstado(false, true, false, true, true, false);
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(validaTransporte())
        {
            Alert a;
            gerarTransporte();
            if(acao == 0 && transporte != null)
            {
                a = new Alert(Alert.AlertType.CONFIRMATION, "Confirmar cadastro da transporte", ButtonType.YES,
                        ButtonType.NO);
                a.showAndWait();
                
                if(a.getResult() == ButtonType.YES)
                {
                    if(ctrTran.salvar(transporte))
                    {
                        inicializa();
                        a = new Alert(Alert.AlertType.INFORMATION, "Transporte salvo com sucesso!!!", ButtonType.OK);
                    }
                    else
                        a = new Alert(Alert.AlertType.ERROR, "Erro no cadastro do transporte\n" + 
                                Banco.getCon().getMensagemErro(), ButtonType.OK);
                    a.showAndWait();
                }
            }
            else if(acao == 1 && transporte != null)
            {
                a = new Alert(Alert.AlertType.CONFIRMATION, "Alterar cadastro da transporte", ButtonType.YES,
                        ButtonType.NO);
                a.showAndWait();
                
                if(a.getResult() == ButtonType.YES)
                {
                    if(ctrTran.alterar(transporte))
                    {
                        inicializa();
                        a = new Alert(Alert.AlertType.INFORMATION, "Transporte alterado com sucesso!!!", 
                                ButtonType.OK);
                    }
                    else
                        a = new Alert(Alert.AlertType.ERROR, "Erro na alteração do transporte\n" + 
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
        setEstado(false, true, false, true, true, false);
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        if(!lvTransportes.getItems().isEmpty() && lvTransportes.getSelectionModel().getSelectedIndex() >= 0)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Remover transporte?", ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            
            if(a.getResult() == ButtonType.YES && transporte != null)
            {
                if(ctrTran.apagar(Integer.parseInt(transporte.getParam1())))
                {
                    inicializa();
                    a = new Alert(Alert.AlertType.INFORMATION, "Transporte removido com sucesso!!", ButtonType.OK);
                }
                else
                    a = new Alert(Alert.AlertType.ERROR, "Erro na remoção do transporte\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK);
                a.showAndWait();
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
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/buscas/BuscarVeiculo.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            JFXDecorator decorator = new JFXDecorator(stage, root);

            BuscarVeiculoController controller = fxmlLoader.<BuscarVeiculoController>getController();
            decorator.setStyle("-fx-decorator-color: #040921;");
            controller.setFlag(false);
            Scene scene = new Scene(decorator);

            stage.setTitle("Buscar Veículo");
            stage.setScene(scene);
            stage.showAndWait();
            stage.setOnCloseRequest((e) ->
            {
                controller.resetVeiculos();
            });
            
            if(controller.getVeiculos() != null && !controller.getVeiculos().isEmpty())
            {
                boolean repetido;
                
                for(Objeto aux1 : controller.getVeiculos())
                {
                    repetido = false;
                    
                    for (int i = 0; i < tvVeiculos.getItems().size(); i++)
                        if(Integer.parseInt(aux1.getParam1()) == 
                                Integer.parseInt(tvVeiculos.getItems().get(i).getParam1()))
                            repetido = true;
                    
                    if(!repetido)
                        tvVeiculos.getItems().add(aux1);
                }
            }
        }
        catch (IOException er)
        {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Pesquisa de Veículo! "
                    + "\nErro: " + er.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void clickAdd(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/basicas/CadastroVeiculo.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            JFXDecorator decorator = new JFXDecorator(stage, root);

            CadastroVeiculoController controller = fxmlLoader.<CadastroVeiculoController>getController();
            decorator.setStyle("-fx-decorator-color: #040921;");
            controller.setAcao(2);
            Scene scene = new Scene(decorator);

            stage.setTitle("Buscar Veículo");
            stage.setScene(scene);
            stage.showAndWait();
            stage.setOnCloseRequest((e) ->
            {
                controller.resetVeiculos();
            });
            tvVeiculos.getItems().add(controller.getVeiculo());
        }
        catch (IOException er)
        {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Pesquisa de Veículo! "
                    + "\nErro: " + er.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void clickDel(ActionEvent event)
    {
        if(!tvVeiculos.getItems().isEmpty() && tvVeiculos.getSelectionModel().getFocusedIndex() >= 0)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover veículo(s) do transporte?", 
                    ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            
            if(a.getResult() == ButtonType.YES)
                tvVeiculos.getSelectionModel().getSelectedItems().stream().map((obj) -> {
                    tvVeiculos.getItems().remove(obj);
                return obj;
            }).forEachOrdered((_item) -> {
                tvVeiculos.refresh();
            });
        }
    }

    @FXML
    private void selecionaTransporte(MouseEvent event)
    {
        if(!lvTransportes.getItems().isEmpty() && lvTransportes.getSelectionModel().getSelectedIndex() >= 0)
        {
            setEstado(true, false, true, false, false, false);
            limpaCampos();
            transporte_selecionado = lvTransportes.getSelectionModel().getSelectedItem();
            String s = transporte_selecionado.substring
                (transporte_selecionado.indexOf(":") + 1,transporte_selecionado.indexOf("\n"));
            
            transporte = ctrTran.getByCodigo(Integer.parseInt(s.replace(" ", "")));
            
            if(transporte != null)
            {
                tfMotorista.setText(transporte.getParam9());
                tfPlacaCegonha.setText(transporte.getParam3());
                
                dpSaida.setValue(LocalDate.parse(transporte.getParam4()));
                cbStatus.getSelectionModel().select(transporte.getParam6());
                cbTipo.getSelectionModel().select(transporte.getParam7());
                
                if(transporte.getParam5() != null && !transporte.getParam5().equals("") 
                        && !transporte.getParam5().equals("null"))
                {
                    dpChegada.setValue(LocalDate.parse(transporte.getParam5()));
                    //dpChegada.getEditor().setText(transporte.getParam5());
                }
                lbUltimaAlteracao.setText("Data da Última Alteração: " + transporte.getParam8());
                
                tvVeiculos.setItems(FXCollections.observableArrayList(transporte.getList1()));
            }
        }
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
        tooltip.setText("Novo Transporte");
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
        tooltip.setText("Alterar Transporte");
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
        tooltip.setText("Excluir Transporte");
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
        tooltip.setText("Pesquisar Venda");
        ToolTip.bindTooltip(btPesquisar, tooltip);
    }

    @FXML
    private void addVeiculoExit(MouseEvent event)
    {
        btAdicionarVeiculo.setStyle(btAdicionarVeiculo.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void addVeiculoEnter(MouseEvent event)
    {
        btAdicionarVeiculo.setStyle(btAdicionarVeiculo.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Adicionar Veículo");
        ToolTip.bindTooltip(btAdicionarVeiculo, tooltip);
    }

    @FXML
    private void delVeiculoExit(MouseEvent event)
    {
        btRemoverVeiculo.setStyle(btRemoverVeiculo.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void delVeiculoEnter(MouseEvent event)
    {
        btRemoverVeiculo.setStyle(btRemoverVeiculo.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Remover Veículo");
        ToolTip.bindTooltip(btRemoverVeiculo, tooltip);
    }

    @FXML
    private void transporteExit(MouseEvent event)
    {
    }

    @FXML
    private void transporteEnter(MouseEvent event)
    {
    }

    private void setlisteners()
    {
        cbFinalizados.setOnAction((event) ->
        {
            lvTransportes.getItems().clear();
            
            if(cbFinalizados.isSelected())
                ctrTran.getAll(true).forEach((objeto) ->
                {
                    lvTransportes.getItems().add("Código de Transporte: " + objeto.getParam1() + "\nStatus: " + 
                    objeto.getParam6());
                });
            else
                ctrTran.getAll().forEach((objeto) ->
                {
                    lvTransportes.getItems().add("Código de Transporte: " + objeto.getParam1() + "\nStatus: " + 
                    objeto.getParam6());
                });
            inicializaToolTipListView();
        });
        
        autoCompletePopupFuncionarios.setSelectionHandler(event ->
        {
            tfMotorista.setText(event.getObject());
        });
        
        tfMotorista.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
                if(tfMotorista.getText().equals("NOVO FUNCIONÁRIO"))
                    tfMotorista.setText("");
        });

        tfMotorista.textProperty().addListener((Observable observable) ->
        {
            //autoCompletePopupClientes.filter(string -> strings.toLowerCase().contains(tfCliente.getText().toLowerCase()));
            atualizaListaFornecedores(tfMotorista.getText());
            
            if(autoCompletePopupFuncionarios.getSuggestions().isEmpty())
            {
                autoCompletePopupFuncionarios.hide();
            }
            else
            {    
                if(tfMotorista.isFocused())
                {
                    autoCompletePopupFuncionarios.show(tfMotorista);
                    if(tfMotorista.getText().equals("NOVO FUNCIONÁRIO"))
                    tfMotorista.setText("");
                }
            }
        });
        
        tfMotorista.setOnMouseClicked((event) ->
        {
            tfMotorista.requestFocus();
            if(!autoCompletePopupFuncionarios.isShowing())
            {
                autoCompletePopupFuncionarios.show(tfMotorista);
            }
            else
                autoCompletePopupFuncionarios.hide();
        });
        
        tfPlacaCegonha.textProperty().addListener((observable, oldValue, newValue) ->
        {
            String texto = newValue;
            int pos = texto.length(), max = 8;
            String ultimo_caractere = texto.substring(pos - 1, pos);
            
            if(texto.contains("-"))
                max = 9;
                
            if(pos < max)
            {
                if(pos < 4)
                {
                    if(!ultimo_caractere.matches("[A-Z]"))
                        if(pos == 1)
                            texto = "";
                        else
                            texto = texto.substring(0, pos - 1);
                }
                else if(pos == 4 || pos == 6 || pos == 7)
                {
                    if(!ultimo_caractere.matches("[0-9]"))
                        texto = texto.substring(0, pos - 1);
                }
                else if(pos == 5)
                {
                    if(!ultimo_caractere.matches("[A-Z0-9]"))
                        texto = texto.substring(0, pos - 1);
                }
                tfPlacaCegonha.setText(texto);
            }
            else
                tfPlacaCegonha.setText(oldValue);
        });
        
        tfPlacaCegonha.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
            {
                if(tfPlacaCegonha.getText().length() == 7)
                    if(tfPlacaCegonha.getText().substring(4, 5).matches("[0-9]"))
                        tfPlacaCegonha.setText(tfPlacaCegonha.getText().substring(0, 3) + "-" + 
                                tfPlacaCegonha.getText().substring(3));
            }
        });
    }

    private boolean validaTransporte()
    {
        String erros = "";
        limpaLabels();
        
        if(tfMotorista.getText().trim().equals(""))
        {
            erros += "Digite o nome do Motorista\n";
            lbErroMotorista.setText("Campo requerido");
        }
        else if(!autoCompletePopupFuncionarios.getSuggestions().contains(tfMotorista.getText()))
        {
            erros += "Motorista não cadastrado\n";
            lbErroMotorista.setText("Motorista não cadastrado");
        }
        
        if(tfPlacaCegonha.getText().trim().equals(""))
        {
            erros += "Digite a placa do Veículo(Cegonha)\n";
            lbErroPlacaCegonha.setText("Campo requerido");
        }
        else if(tfPlacaCegonha.getText().length() < 7)
        {
            erros += "Digite a placa do Veículo(Cegonha)\n";
            lbErroPlacaCegonha.setText("Placa incompleta");
        }
        if(cbStatus.getSelectionModel().getSelectedIndex() < 0)
        {
            erros += "Selecione o status do transporte\n";
            lbErroStatus.setText("Campo requerido");
        }
        else if(!cbStatus.getSelectionModel().getSelectedItem().equals("Á Iniciar") && 
            dpSaida.getEditor().getText().trim().equals(""))
        {
            erros += "Seleciona a data de Saída\n";
            lbErroSaida.setText("Campo requerido");
        }
        else
        {
            if(cbStatus.getSelectionModel().getSelectedItem().equals("Finalizado") 
                && dpChegada.getEditor().getText().trim().equals(""))
            {
                erros += "Selecione a data do término do transporte\n";
                lbErroChegada.setText("Campo requerido");
            }
        }
        
        if(!dpSaida.getEditor().getText().trim().equals("") && 
            !dpChegada.getEditor().getText().trim().equals("") && 
            dpChegada.getValue().compareTo(dpSaida.getValue()) < 0)
        {
            erros += "Data de chegada inferior ao de saída\n";
            lbErroChegada.setText("Data inválida");
        }
        
        if(cbTipo.getSelectionModel().getSelectedIndex() < 0)
        {
            erros += "Selecione o tipo do transporte\n";
            lbErroTipo.setText("Campo requerido");
        }
        
        if(tvVeiculos.getItems().isEmpty())
            erros += "Tabela de veículos vazia\n";
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        
        return erros.trim().equals("");
    }

    private void gerarTransporte()
    {
        ///1 - CÓDIGO, 2 - CÓDIGO MOTORISTA, 3 - PLACA VEÍCULO, 4 - SAÍDA, 5 - CHEGADA, 6 - STATUS, 7 - TIPO
        ///8 - ALTERAÇÃO, 9 - NOME DO MOTORISTA
        ///LIST1 - VEÍCULOS TRANSPORTADOS
        
        if(transporte == null)
        {
            transporte = new Objeto("0");
        }
        
        transporte.setParam2(ctrFunc.getByName(tfMotorista.getText()).get(0).getParam1());
        transporte.setParam3(tfPlacaCegonha.getText());
        transporte.setParam4(String.valueOf(dpSaida.getValue()));
        transporte.setParam5(dpChegada.getEditor().getText().trim().equals("")? 
                "" : String.valueOf(dpChegada.getValue()));
        transporte.setParam6(cbStatus.getSelectionModel().getSelectedItem());
        transporte.setParam7(cbTipo.getSelectionModel().getSelectedItem());
        transporte.setParam8(String.valueOf(new Timestamp(new java.util.Date().getTime())));
        transporte.setParam9(tfMotorista.getText());
        
        transporte.setList1(new ArrayList<>());
        tvVeiculos.getItems().forEach((t) ->
        {
            transporte.addList1(t);
        });
    }

    private void inicializaToolTipListView()
    {
        lvTransportes.setCellFactory((ListView<String> param) ->
        {
            final Tooltip t = new Tooltip();
            final ListCell<String> cell = new ListCell<String>() {
                @Override
                public void updateItem(String item, boolean empty)
                {
                    super.updateItem(item, empty);
                    if (item != null)
                    {
                        setText(item);
                        t.setText(preencheTooltip(item.substring(0, item.indexOf("\n"))));
                        ToolTip.bindTooltip(this, t);
                    }
                    else
                        setText(null);
                }
                
                private String preencheTooltip(String item)
                {
                    ArrayList<Objeto>objetos = cbFinalizados.isSelected() ? ctrTran.getAll(true) : ctrTran.getAll();
                    for(Objeto objeto : objetos)
                    {
                        String s = "Motorista: " + objeto.getParam9() + " Com ";
                        s += objeto.getList1() != null && !objeto.getList1().isEmpty()
                                ? objeto.getList1().size() + "" : "0";
                        s += " Veículo(s)";
                        return s;
                    }
                    return null;
                }
            }; // ListCell
            return cell;
        }); 
    }
    
}
