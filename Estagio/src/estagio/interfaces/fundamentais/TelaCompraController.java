/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrCliente;
import estagio.controladores.ctrCompra;
import estagio.controladores.ctrFabricante;
import estagio.controladores.ctrFornecedor;
import estagio.controladores.ctrVeiculo;
import estagio.interfaces.basicas.CadastroClienteController;
import estagio.interfaces.basicas.CadastroFornecedorController;
import estagio.interfaces.buscas.BuscarCompraController;
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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import org.controlsfx.control.PopOver;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaCompraController implements Initializable
{
    private final ctrCliente ctrCli = ctrCliente.instancia();
    private final ctrFornecedor ctrForn = ctrFornecedor.instancia();
    private final ctrFabricante ctrFab = ctrFabricante.instancia();
    private final ctrCompra ctrComp = ctrCompra.instancia();
    private final ctrVeiculo ctrVei = ctrVeiculo.instancia();
    
    private final JFXAutoCompletePopup<String> autoCompletePopupMarcas = new JFXAutoCompletePopup<>();
    private final JFXAutoCompletePopup<String> autoCompletePopupModelos = new JFXAutoCompletePopup<>();
    private final JFXAutoCompletePopup<String> autoCompletePopupFornecedores = new JFXAutoCompletePopup<>();
    
    private ArrayList<String>fornecedores = new ArrayList<>();
    private ArrayList<Objeto>parcelas = new ArrayList<>();
    
    private PopOver pop;
    private BorderPane pane;
    
    private int acao;
    private Objeto compra;

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneVeiculo;
    @FXML
    private Pane paneDadosCompra;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbDadosVeiculos;
    @FXML
    private Label lbVeiculos;
    @FXML
    private Label lbEmissao;
    @FXML
    private Label lbSubTotal;
    @FXML
    private Label lbErroPlaca;
    @FXML
    private Label lbErroMarca;
    @FXML
    private Label lbErroModelo;
    @FXML
    private Label lbErroChassi;
    @FXML
    private Label lbErroAno;
    @FXML
    private Label lbErroCor;
    @FXML
    private Label lbErroValorVeiculo;
    @FXML
    private Label lbErroFornecedor;
    @FXML
    private Label lbErroNotaFiscal;
    @FXML
    private Label lbErroVendedor;
    @FXML
    private JFXTextField tfPlaca;
    @FXML
    private JFXCheckBox rbMercosul;
    @FXML
    private JFXTextField tfMarca;
    @FXML
    private JFXTextField tfModelo;
    @FXML
    private JFXTextField tfChassi;
    @FXML
    private JFXTextField tfAno;
    @FXML
    private JFXTextField tfCor;
    @FXML
    private JFXTextField tfValorVeiculo;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private JFXButton btRemover;
    @FXML
    private FontAwesomeIconView faMinus;
    @FXML
    private TableView<Objeto> tvVeiculos;
    @FXML
    private TableColumn<Objeto,String> tcMarca;
    @FXML
    private TableColumn<Objeto,String> tcModelo;
    @FXML
    private TableColumn<Objeto,String> tcPlaca;
    @FXML
    private TableColumn<Objeto,String> tcValor;
    @FXML
    private TableColumn<Objeto,String> tcChassi;
    @FXML
    private TableColumn<Objeto,String> tcAno;
    @FXML
    private TableColumn<Objeto,String> tcCor;
    @FXML
    private TableColumn<Objeto,String> tcDescricao;
    @FXML
    private JFXDatePicker dpEmissao;
    @FXML
    private JFXTextField tfFornecedor;
    @FXML
    private JFXTextField tfNotaFiscal;
    @FXML
    private JFXTextField tfVendedor;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private JFXButton btAlterar;
    @FXML
    private FontAwesomeIconView faEdit;
    @FXML
    private FontAwesomeIconView faTrash;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private JFXButton btAdicionarVeiculo;
    @FXML
    private JFXButton btRemoverVeiculo;
    @FXML
    private FontAwesomeIconView faView;
    @FXML
    private FontAwesomeIconView faPlus2;
    @FXML
    private VBox painelCentral;
    @FXML
    private VBox vboxCentral;
    @FXML
    private JFXTextField tfPlacaMercosul;
    @FXML
    private JFXTextArea taDescricao;
    @FXML
    private Label lbDadosVeiculos1;

    /**
     * Initializes the controller class.
     */
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneDadosCompra);
        nodes.add(paneVeiculo);
        
        nodes.add(btAdicionarVeiculo);
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btNovo);
        nodes.add(btRemoverVeiculo);
        nodes.add(btPesquisar);
        nodes.add(btRemover);
        
        nodes.add(tfAno);
        nodes.add(tfChassi);
        nodes.add(tfCor);
        nodes.add(tfFornecedor);
        nodes.add(tfMarca);
        nodes.add(tfModelo);
        nodes.add(tfNotaFiscal);
        nodes.add(tfPlaca);
        nodes.add(tfPlacaMercosul);
        nodes.add(tfValorVeiculo);
        nodes.add(tfVendedor);
        
        nodes.add(taDescricao);
        
        nodes.add(lbDadosVeiculos);
        nodes.add(lbEmissao);
        nodes.add(lbSubTotal);
        nodes.add(lbVeiculos);
        nodes.add(lbTitulo);
        nodes.add(lbDadosVeiculos1);
        
        nodes.add(rbMercosul);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faPlus2);
        nodes.add(faSearch);
        nodes.add(faView);
        nodes.add(faTrash);
        nodes.add(faMinus);
        
        Utils.setDesign(1, nodes);
        
        btNovo.setStyle(btNovo.getStyle() + ";-fx-cursor: default;");
        btAlterar.setStyle(btAlterar.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
        btRemover.setStyle(btRemover.getStyle() + ";-fx-cursor: default;");
        btAdicionarVeiculo.setStyle(btAdicionarVeiculo.getStyle() + ";-fx-cursor: default;");
        btRemoverVeiculo.setStyle(btRemoverVeiculo.getStyle() + ";-fx-cursor: default;");
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
        vboxCentral.setPrefHeight(painelCentral.getPrefHeight() - paneVeiculo.getPrefHeight() -
                paneDadosCompra.getPrefHeight() - 20);
        
        tvVeiculos.setPrefHeight(vboxCentral.getPrefHeight() - 30);
        tvVeiculos.setPrefWidth(vboxCentral.getPrefWidth() - 30);
        
        //REORGANIZA OS TAMANHOS DAS COLUNAS DA TABELA
        int sobra = (int)((tvVeiculos.getPrefWidth() - 1130)/2);
        tcMarca.setPrefWidth(tcMarca.getPrefWidth() + sobra);
        tcModelo.setPrefWidth(tcModelo.getPrefWidth() + sobra);
    }
    
    private void limpaLabelsVeiculo()
    {
        lbErroAno.setText("");
        lbErroChassi.setText("");
        lbErroCor.setText("");
        lbErroMarca.setText("");
        lbErroModelo.setText("");
        lbErroValorVeiculo.setText("");
        lbErroPlaca.setText("");
    }
    
    private void limpaCamposVeiculo()
    {
        tfPlaca.clear();
        tfPlacaMercosul.clear();
        rbMercosul.setSelected(false);
        tfMarca.clear();
        tfModelo.clear();
        tfChassi.clear();
        tfAno.clear();
        tfCor.clear();
        tfValorVeiculo.clear();
    }
    
    private void limpaLabelsDados()
    {
        lbErroFornecedor.setText("");
        lbErroNotaFiscal.setText("");
        lbErroVendedor.setText("");
    }
    
    private void limpaCampoDados()
    {
        tfFornecedor.clear();
        tfNotaFiscal.clear();
        tfVendedor.clear();
    }
    
    private void atualizaListaFornecedores(String... nome)
    {
        fornecedores.clear();
        fornecedores.add("FORNECEDORES");
        fornecedores.addAll(ctrForn.getAllNames());
        fornecedores.add("");
        fornecedores.add("CLIENTES");
        fornecedores.addAll(ctrCli.getAllNames());
        
        if(nome.length == 0)
            autoCompletePopupFornecedores.getSuggestions().addAll(fornecedores);
        
        autoCompletePopupFornecedores.getSuggestions().clear();
        autoCompletePopupFornecedores.getSuggestions().add("FORNECEDORES");
        autoCompletePopupFornecedores.getSuggestions().addAll(ctrForn.getAllNames(nome));
        autoCompletePopupFornecedores.getSuggestions().addAll("","CLIENTES");
        autoCompletePopupFornecedores.getSuggestions().addAll(ctrCli.getAllNames(nome));
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6,Boolean b7,Boolean b8)
    {
        if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            paneDadosCompra.setDisable(true);
            paneVeiculo.setDisable(true);
            btNovo.setDisable(true);
            btConfirmar.setDisable(true);
            btRemover.setDisable(true);
            btAlterar.setDisable(true);
        }
        else
        {
            paneDadosCompra.setDisable(b1);
            paneVeiculo.setDisable(b2);
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
        limpaLabelsDados();
        limpaCampoDados();
        limpaLabelsVeiculo();
        limpaCamposVeiculo();
        tvVeiculos.getItems().clear();
        parcelas = new ArrayList<>();
        dpEmissao.setValue(LocalDate.now());
        compra = null;
        
        atualizaListaFornecedores("");
        
        autoCompletePopupMarcas.getSuggestions().clear();
        autoCompletePopupMarcas.getSuggestions().add("Nova Marca");
        autoCompletePopupMarcas.getSuggestions().addAll(ctrFab.getAllMarcas());
        
        setEstado(true, true, false, true, true, true, false, true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesign();
        redimensiona();
        
        tcAno.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcChassi.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcCor.setCellValueFactory(new PropertyValueFactory<>("param6"));
        tcMarca.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcModelo.setCellValueFactory(new PropertyValueFactory<>("param8"));
        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("param10"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("param9"));
        
        tcMarca.setCellFactory(TooltippedTableCell.forTableColumn());
        tcModelo.setCellFactory(TooltippedTableCell.forTableColumn());
        tcDescricao.setCellFactory(TooltippedTableCell.forTableColumn());
        
        setPopUpLocation(autoCompletePopupFornecedores,tfFornecedor);
        
        dpEmissao.getEditor().setEditable(false);
        
        MaskFieldUtil.monetaryField(tfValorVeiculo);
        MaskFieldUtil.yearField(tfAno);
        MaskFieldUtil.toUpperCase(tfPlaca);
        MaskFieldUtil.toUpperCase(tfPlacaMercosul);
        MaskFieldUtil.placaNormalField(tfPlaca);
        MaskFieldUtil.placaMercosulField(tfPlacaMercosul);
        
        inicializa();
        setListeners();
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
       if(validaCompra())
       {
           Alert a = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES,ButtonType.NO);
           boolean cliente = isCliente();
           
           if(acao == 0)
           {
               a.setContentText("Confirmar cadastro da compra?");
               a.showAndWait();
            
               if(a.getResult() == ButtonType.YES)
               {
                    try
                    {
                       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/fundamentais/ConfirmarTransacao.fxml"));
                       Parent root = (Parent) fxmlLoader.load();
                       Stage stage = new Stage();
                       JFXDecorator decorator = new JFXDecorator(stage, root);

                       decorator.setStyle("-fx-decorator-color: #040921;");
                       ConfirmarTransacaoController controller = fxmlLoader.<ConfirmarTransacaoController>getController();
                       controller.setCompra(tfFornecedor,cliente,tfNotaFiscal,tfVendedor,dpEmissao,tvVeiculos,lbSubTotal);
                       Scene scene = new Scene(decorator);

                       stage.setTitle("Cadastro de Fornecedor");
                       stage.setScene(scene);
                       stage.showAndWait();
                       
                       if(controller.getResposta())
                       {
                           inicializa();
                           a = new Alert(Alert.AlertType.INFORMATION, "Compra efetuada com sucesso!!!", ButtonType.OK);
                       }
                       else
                           a = new Alert(Alert.AlertType.ERROR, "Erro, compra não efetuado", ButtonType.OK);
                       a.showAndWait();
                   }
                   catch (IOException er)
                   {
                       a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Pagamento! \nErro: " + er.getMessage(), ButtonType.OK);
                       System.out.println(er.getMessage());
                       a.showAndWait();
                   }
               }
           }
       }
    }

    private boolean validaCompra()
    {
        String erros = "";
        limpaLabelsDados();
        
        if(tvVeiculos.getItems().isEmpty())
            erros += "Lista de Veículos vazia\n";
        
        if(tfFornecedor.getText().trim().equals(""))
        {
            erros += "Digite o nome do Fornecedor\n";
            lbErroFornecedor.setText("Campo requerido");
        }
        else if(!fornecedores.contains(tfFornecedor.getText()))
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Fornecedor não cadastrado, deseja cadastrar?", 
                    ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            
            if(a.getResult() == ButtonType.NO)
            {
                erros += "Fornecedor não cadastrado\n";
                lbErroFornecedor.setText("Fornecedor não cadastrado");
            }
            else
            {
                ButtonType forn = new ButtonType("Fornecedor"),cli = new ButtonType("Cliente");
                a.getButtonTypes().clear();
                a.getButtonTypes().addAll(forn,cli);
                a.setContentText("Selecione o que deseja cadastrar");
                a.showAndWait();
                
                if(a.getResult() == forn)
                {
                    try
                    {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/basicas/CadastroFornecedor.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        JFXDecorator decorator = new JFXDecorator(stage, root);

                        decorator.setStyle("-fx-decorator-color: #040921;");

                        Scene scene = new Scene(decorator);

                        stage.setTitle("Cadastro de Fornecedor");
                        stage.setScene(scene);
                        CadastroFornecedorController controller = fxmlLoader.<CadastroFornecedorController>getController();
                        controller.setForn(tfFornecedor.getText());
                        stage.showAndWait();
                        atualizaListaFornecedores(tfFornecedor.getText());
                    }
                    catch (IOException er)
                    {
                        a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Forncedores! \nErro: " + er.getMessage(), ButtonType.OK);
                        System.out.println(er.getMessage());
                        a.showAndWait();
                    }
                }
                else
                {
                    try
                    {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/basicas/CadastroCliente.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        JFXDecorator decorator = new JFXDecorator(stage, root);

                        decorator.setStyle("-fx-decorator-color: #040921;");

                        Scene scene = new Scene(decorator);

                        stage.setTitle("Cadastro de Cliente");
                        stage.setScene(scene);
                        CadastroClienteController controller = fxmlLoader.<CadastroClienteController>getController();
                        controller.setCli(tfFornecedor.getText());
                        stage.showAndWait();
                        atualizaListaFornecedores(tfFornecedor.getText());
                    }
                    catch (IOException er)
                    {
                        a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Clientes! \nErro: " + er.getMessage(), ButtonType.OK);
                        System.out.println(er.getMessage());
                        a.showAndWait();
                    }
                }
                
                if(!autoCompletePopupFornecedores.getSuggestions().contains(tfFornecedor.getText()))
                {
                    erros += "Fornecedor não cadastrado\n";
                    lbErroFornecedor.setText("Fornecedor não cadastrado");
                }
            }
        }
        
        if(tfNotaFiscal.getText().trim().equals(""))
        {
            erros += "Digite a nota fiscal da compra\n";
            lbErroNotaFiscal.setText("Campo requerido");
        }
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        return erros.trim().equals("");
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        acao = 2;
        setEstado(false, false, true, false, true, true, true, false);
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja excluir a compra?", ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        
        if(a.getResult() == ButtonType.YES && compra != null)
        {
            if(ctrComp.apagar(Integer.parseInt(compra.getParam1())))
            {
                inicializa();
                a = new Alert(Alert.AlertType.CONFIRMATION, "Compra excluída com sucesso!!!!", ButtonType.OK);
            }
            else
                a = new Alert(Alert.AlertType.ERROR, "Erro na remoção da compra", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/estagio/interfaces/buscas/BuscarCompra.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            JFXDecorator decorator = new JFXDecorator(stage, root);

            decorator.setStyle("-fx-decorator-color: #040921;");
            BuscarCompraController controller = fxmlLoader.<BuscarCompraController>getController();
            Scene scene = new Scene(decorator);

            stage.setTitle("Buscar Compra");
            stage.setScene(scene);
            stage.showAndWait();
            
            inicializa();
            compra = controller.getResposta();
            if(compra != null)
            {
                tfFornecedor.setText(compra.getParam4() + ", " + compra.getParam13());
                pane = criarPaneFornecedor();
                tfNotaFiscal.setText(compra.getParam9());
                tfVendedor.setText(compra.getParam11());
                dpEmissao.setValue(LocalDate.parse(compra.getParam10()));
                
                for (int i = 0; i < compra.getList2().size(); i++)
                {
                    Objeto veiculo = ctrVei.getByCodigo(Integer.parseInt(compra.getList2().get(i).getParam1()));
                    veiculo.setParam10(compra.getList2().get(i).getParam3());
                    tvVeiculos.getItems().add(veiculo);
                }
                setEstado(false, false, false, true, false, false, false, false);
            }
        }
        catch (IOException er)
        {
            new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela de Pesquisa! "
                    + "\nErro: " + er.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        inicializa();
    }

    @FXML
    private void clickAdicionarVeiculo(ActionEvent event)
    {
        if(validaVeiculo())
        {
            Objeto obj = new Objeto();
            obj.setParam4(tfChassi.getText());
            obj.setParam5(tfAno.getText());
            obj.setParam6(tfCor.getText());
            obj.setParam7(tfMarca.getText());
            obj.setParam8(tfModelo.getText());
            obj.setParam9(taDescricao.getText());
            obj.setParam10(tfValorVeiculo.getText());
            
            if(rbMercosul.isSelected())
                obj.setParam2(tfPlacaMercosul.getText());
            else
                obj.setParam2(tfPlaca.getText());
            
            tvVeiculos.getItems().add(obj);
            limpaCamposVeiculo();
            limpaLabelsVeiculo();
        }
        else
        {
            if(!tfMarca.getText().trim().equals("") && 
                !autoCompletePopupMarcas.getSuggestions().contains(tfMarca.getText()))
            {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Marca não cadastrada, deseja cadastrar?",
                        ButtonType.YES, ButtonType.NO);
                a.showAndWait();

                if (a.getResult() == ButtonType.YES)
                {
                    if (ctrFab.salvarMarca(tfMarca.getText()))
                    {
                        autoCompletePopupMarcas.getSuggestions().clear();
                        autoCompletePopupMarcas.getSuggestions().add("Nova Marca");
                        autoCompletePopupMarcas.getSuggestions().addAll(ctrFab.getAllMarcas());
                        Notifications.create()
                                .darkStyle()
                                //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                                .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                                .text("Marca cadastrada com sucesso!!!")
                                .showInformation();
                        lbErroMarca.setText("");
                    }
                    else
                        Notifications.create()
                                .darkStyle()
                                //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                                .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                                .text("Erro no cadastramento da marca")
                                .showError();
                }
            }
            else if(!tfModelo.getText().trim().equals("") && 
                !autoCompletePopupModelos.getSuggestions().contains(tfModelo.getText()))
            {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Modelo não cadastrado, deseja cadastrar?",
                        ButtonType.YES, ButtonType.NO);
                a.showAndWait();

                if (a.getResult() == ButtonType.YES)
                {
                    if (ctrFab.salvarModelo(tfMarca.getText() + ":" + tfModelo.getText()))
                    {
                        autoCompletePopupModelos.getSuggestions().clear();
                        autoCompletePopupModelos.getSuggestions().add("Novo Modelo");
                        autoCompletePopupModelos.getSuggestions().addAll(
                                ctrFab.getAllModelosByMarca(tfMarca.getText()));

                        Notifications.create()
                                .darkStyle()
                                //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                                .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                                .text("Modelo cadastrado com sucesso!!!")
                                .showInformation();
                        lbErroModelo.setText("");
                    }
                    else
                        Notifications.create()
                                .darkStyle()
                                //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                                .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                                .text("Erro no cadastramento do modelo")
                                .showError();
                }
            }
        }
    }

    private boolean validaVeiculo()
    {
        String erros = "";
        limpaLabelsVeiculo();
        
        if(rbMercosul.isSelected())
        {
            if(tfPlacaMercosul.getText().trim().equals(""))
            {
                erros += "Digite a placa do veículo\n";
                lbErroPlaca.setText("Campo requerido");
            }
            else if(tfPlacaMercosul.getText().length() < 7)
            {
                erros += "Placa incompleto\n";
                lbErroPlaca.setText("Placa incompleta");
            }
        }
        else
        {
            if(!tfPlaca.getText().trim().equals("") && tfPlaca.getText().length() < 8)
            {
                erros += "Placa incompleto\n";
                lbErroPlaca.setText("Placa incompleta");
            }
        }
        
        if(tfMarca.getText().trim().equals(""))
        {
            erros += "Digite a marca\n";
            lbErroMarca.setText("Campo requerido");
        }
        else if(!autoCompletePopupMarcas.getSuggestions().contains(tfMarca.getText().toUpperCase()))
        {
            erros += "Marca não cadatrada\n";
            lbErroMarca.setText("Marca não cadastrada");
        }
        
        if(tfModelo.getText().trim().equals(""))
        {
            erros += "Digite o modelo\n";
            lbErroModelo.setText("Campo requerido");
        }
        else if(!autoCompletePopupModelos.getSuggestions().contains(tfModelo.getText().toUpperCase()))
        {
            erros += "Modelo não cadastrado\n";
            lbErroModelo.setText("Modelo não cadastrado");
        }
        
        if(tfAno.getText().trim().equals(""))
        {
            erros += "Digite o ano do veículo\n";
            lbErroAno.setText("Campo requerido");
        }
        else if(tfAno.getText().length() != 4)
        {
            erros += "Digite o ano completo\n";
            lbErroAno.setText("Ano inválido");
        }
        else if(Integer.parseInt(tfAno.getText()) < 1900 
                || Integer.parseInt(tfAno.getText()) > (LocalDate.now().getYear() + 2))
        {
            erros += "Digite um ano válido\n";
            lbErroAno.setText("Ano inválido");
        }
        
        if(tfCor.getText().trim().equals(""))
        {
            erros += "Digite a cor do veículo\n";
            lbErroCor.setText("Campo requerido");
        }
        
        if(tfValorVeiculo.getText().trim().equals("") || 
                Double.parseDouble(tfValorVeiculo.getText().replace(".", "").replace(",", ".")) <= 0)
        {
            erros += "Digite um valor válido\n";
            lbErroValorVeiculo.setText("Campo inválido");
        }
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        
        return erros.trim().equals("");
    }

    @FXML
    private void clickRemoverVeiculo(ActionEvent event)
    {
        if(!tvVeiculos.getItems().isEmpty() && tvVeiculos.getSelectionModel().getSelectedIndex() >= 0)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover veículo da lista de compra?", 
                    ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            
            if(a.getResult() == ButtonType.YES)
            {
                tvVeiculos.getItems().remove(tvVeiculos.getSelectionModel().getSelectedIndex());
                tvVeiculos.getSelectionModel().clearSelection();
            }
        }
    }

    @FXML
    private void selecionaVeiculo(MouseEvent event)
    {
        if(event.getClickCount() == 2 && !tvVeiculos.getItems().isEmpty() && tvVeiculos.getSelectionModel().getSelectedIndex() >= 0)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar dados do veículo?", ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            
            if(a.getResult() == ButtonType.YES)
            {
                Objeto o = tvVeiculos.getSelectionModel().getSelectedItem();
                tfMarca.setText(o.getParam7());
                tfModelo.setText(o.getParam8());
                tfChassi.setText(o.getParam4());
                tfAno.setText(o.getParam5());
                tfCor.setText(o.getParam6());
                tfValorVeiculo.setText(o.getParam10());
                taDescricao.setText(o.getParam9());
                
                if(o.getParam2() != null && !o.getParam2().trim().equals(""))
                {
                    if(o.getParam2().contains("-"))
                    {
                        rbMercosul.setSelected(false);
                        tfPlaca.setVisible(true);
                        tfPlacaMercosul.setVisible(false);
                        tfPlaca.setText(o.getParam2());
                    }
                    else
                    {
                        rbMercosul.setSelected(true);
                        tfPlaca.setVisible(false);
                        tfPlacaMercosul.setVisible(true);
                        tfPlacaMercosul.setText(o.getParam2());
                    }
                }
                tvVeiculos.getItems().remove(o);
                autoCompletePopupMarcas.hide();
                autoCompletePopupModelos.hide();
                tvVeiculos.getSelectionModel().clearSelection();
            }
        }
    }

    @FXML
    private void clickMercosul(ActionEvent event)
    {
        if(rbMercosul.isSelected())
        {
            tfPlaca.setVisible(false);
            tfPlacaMercosul.setVisible(true);
            tfPlacaMercosul.clear();
        }
        else
        {
            tfPlaca.setVisible(true);
            tfPlacaMercosul.setVisible(false);
            tfPlaca.clear();
        }
    }

    @FXML
    private void addVeiculoExit(MouseEvent event)
    {
    }

    @FXML
    private void addVeiculoEnter(MouseEvent event)
    {
    }

    @FXML
    private void delVeiculoExit(MouseEvent event)
    {
    }

    @FXML
    private void delVeiculoEnter(MouseEvent event)
    {
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
    private void fornecedorExit(MouseEvent event)
    {
        faView.setStyle(faView.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        
        if(pop != null && pop.isShowing())
            pop.hide();
    }

    @FXML
    private void fornecedorEnter(MouseEvent event)
    {
        faView.setStyle(faView.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        
        if(pane != null)
        {
            pop = new PopOver(pane);
            pop.show(faView);
        }
    }

    @FXML
    private void fornecedorClicked(MouseEvent event)
    {
        if(pop != null && !pop.isShowing())
            pop.show(faView);
        else
            pop.hide();
    }

    private BorderPane criarPaneFornecedor()
    {
        if(!tfFornecedor.getText().equals("") && !tfFornecedor.getText().equals("FORNECEDORES") && 
            !tfFornecedor.getText().equals("CLIENTES"))
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
                obj = ctrCli.getByCPF(tfFornecedor.getText().substring(tfFornecedor.getText().indexOf(", ") + 2)
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
                
                obj = ctrForn.getByCNPJ(tfFornecedor.getText().substring(tfFornecedor.getText().indexOf(", ") + 2)
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
    
    private void setListeners()
    {
        autoCompletePopupFornecedores.setSelectionHandler(event ->
        {
            tfFornecedor.setText(event.getObject());
        });
        
        tfFornecedor.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue)
                if(tfFornecedor.getText().equals("FORNECEDORES") || tfFornecedor.getText().equals("CLIENTES"))
                    tfFornecedor.setText("");
        });

        tfFornecedor.textProperty().addListener(observable ->
        {
            //autoCompletePopupFornecedores.filter(string -> string.toLowerCase().contains(tfFornecedor.getText().toLowerCase()));
            atualizaListaFornecedores(tfFornecedor.getText());
            
            if(autoCompletePopupFornecedores.getSuggestions().isEmpty())
            {
                autoCompletePopupFornecedores.hide();
            }
            else
            {    
                if(tfFornecedor.isFocused())
                {
                    autoCompletePopupFornecedores.show(tfFornecedor);
                    pane = criarPaneFornecedor();
                }
            }
        });
        
        tfFornecedor.setOnMouseClicked((event) ->
        {
            tfFornecedor.requestFocus();
            if(!autoCompletePopupFornecedores.isShowing())
            {
                autoCompletePopupFornecedores.show(tfFornecedor);
            }
            else
                autoCompletePopupFornecedores.hide();
        });
        
        ChangeListener dataEmissao = (ChangeListener<String>) (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            if(acao == 0)
            {
                dpEmissao.setValue(LocalDate.now());
            }
        };
        
        dpEmissao.getEditor().textProperty().addListener(dataEmissao);
        dpEmissao.valueProperty().addListener(dataEmissao);
        
        autoCompletePopupMarcas.setSelectionHandler(event ->
        {
            tfMarca.setText(event.getObject());
        });

        tfMarca.textProperty().addListener(observable ->
        {
            autoCompletePopupMarcas.filter(string -> string.toLowerCase().contains(tfMarca.getText().toLowerCase()));
            if (autoCompletePopupMarcas.getFilteredSuggestions().isEmpty() || tfMarca.getText().isEmpty())
            {
                autoCompletePopupMarcas.hide();
            }
            else
            {
                autoCompletePopupMarcas.show(tfMarca);
            }
        });
        
        tfMarca.focusedProperty().addListener((ov, oldV, newV) ->
        {
            if(newV)
                autoCompletePopupMarcas.show(tfMarca);
            else
            {
                autoCompletePopupMarcas.hide();
                
                autoCompletePopupModelos.getSuggestions().clear();
                autoCompletePopupModelos.getSuggestions().add("Novo Modelo");
                if(autoCompletePopupMarcas.getSuggestions().contains(tfMarca.getText()))
                    autoCompletePopupModelos.getSuggestions().addAll(ctrFab.getAllModelosByMarca(tfMarca.getText()));
            }
        });
        
        tfMarca.setOnMouseClicked((event) ->
        {
            tfMarca.requestFocus();
            if(!autoCompletePopupMarcas.isShowing())
                autoCompletePopupMarcas.show(tfMarca);
        });
        
        tfMarca.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue.toLowerCase().equals("nova marca"))
            {
                tfMarca.setText("");
                CadastrarMarca();
            }
        });
        
        
        autoCompletePopupModelos.setSelectionHandler(event ->
        {
            tfModelo.setText(event.getObject());

            // you can do other actions here when text completed
        });

        // filtering options
        tfModelo.textProperty().addListener(observable ->
        {
            autoCompletePopupModelos.filter(string -> string.toLowerCase().contains(tfModelo.getText().toLowerCase()));
            if (autoCompletePopupModelos.getFilteredSuggestions().isEmpty() || tfModelo.getText().isEmpty())
            {
                autoCompletePopupModelos.hide();
                // if you remove textField.getText.isEmpty() when text field is empty it suggests all options
                // so you can choose
            }
            else
            {
                autoCompletePopupModelos.show(tfModelo);
            }
        });
        
        tfModelo.focusedProperty().addListener((ov, oldV, newV) ->
        {
            if(newV)
                autoCompletePopupModelos.show(tfModelo);
            else
                autoCompletePopupModelos.hide();
        });
        
        tfModelo.setOnMouseClicked((event) ->
        {
            tfModelo.requestFocus();
            if(!autoCompletePopupModelos.isShowing())
                autoCompletePopupModelos.show(tfModelo);
        });
        
        tfModelo.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue.toLowerCase().equals("novo modelo"))
            {
                tfModelo.setText("");
                CadastrarModelo();
            }
        });
        
        tvVeiculos.getItems().addListener((ListChangeListener.Change<? extends Objeto> c) ->
        {
            calculaTotal();
        });
    }
    
    private void setPopUpLocation(JFXAutoCompletePopup<String> popup, Node node)
    {
        //popup.setAnchorY(node.localToScene(0, 0).getY() - popup.getHeight() - 15);
        //popup.setAnchorX(node.localToScene(0, 0).getX());
    }

    private void CadastrarModelo()
    {
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
        dialog.getDialogPane().setPrefWidth(500);

        VBox vbox = new VBox();
        vbox.setPrefWidth(500);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        dialog.setTitle("Novo Modelo");
        dialog.setHeaderText("Novo Cadastro de Modelo");

        TextField modelo = new JFXTextField();
        modelo.setPromptText("Digite o nome do modelo(*)");
        modelo.setPrefWidth(350);

        ComboBox<String> m = new ComboBox<>();
        m.getItems().addAll(autoCompletePopupMarcas.getSuggestions());
        m.getSelectionModel().select(tfMarca.getText());

        vbox.setSpacing(10);
        vbox.getChildren().addAll(m, modelo);
        dialog.getDialogPane().setContent(vbox);
        dialog.setResultConverter(dialogButton ->
        {
            if (dialogButton == ButtonType.YES && !modelo.getText().trim().equals(""))
            {
                return m.getSelectionModel().getSelectedItem() + ":" + modelo.getText();
            }
            return null;
        });
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            if (ctrFab.salvarModelo(result.get()))
            {
                new Alert(Alert.AlertType.INFORMATION, "Modelo Salvo com sucesso!!", ButtonType.OK).showAndWait();
                autoCompletePopupModelos.getSuggestions().clear();
                autoCompletePopupModelos.getSuggestions().add("Novo Modelo");
                autoCompletePopupModelos.getSuggestions().addAll(ctrFab.getAllModelosByMarca(tfMarca.getText()));
            }
            else
            {
                new Alert(Alert.AlertType.ERROR, "Erro no cadastramento do modelo\n"
                        + Banco.getCon().getMensagemErro(), ButtonType.OK).showAndWait();
            }
        }
    }

    private void CadastrarMarca()
    {
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
        dialog.getDialogPane().setPrefWidth(500);

        VBox vbox = new VBox();
        vbox.setPrefWidth(500);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        dialog.setTitle("Nova Marca");
        dialog.setHeaderText("Novo Cadastro de Marca");

        TextField marca = new JFXTextField();
        marca.setPromptText("Digite o nome da marca(*)");
        marca.setPrefWidth(350);

        marca.textProperty().addListener((observable, oldValue, newValue) ->
        {
            if (autoCompletePopupMarcas.getSuggestions().contains(newValue.toUpperCase()))
            {
                new Alert(Alert.AlertType.ERROR, "Marca já cadastrada", ButtonType.OK).showAndWait();
                marca.setText("");
            }
        });

        vbox.getChildren().add(marca);
        dialog.getDialogPane().setContent(vbox);
        dialog.setResultConverter(dialogButton ->
        {
            if (dialogButton == ButtonType.YES && !marca.getText().trim().equals(""))
            {
                return marca.getText();
            }
            return null;
        });
        
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            if (!autoCompletePopupMarcas.getSuggestions().contains(result.get().toUpperCase()) && 
                ctrFab.salvarMarca(result.get()))
            {
                new Alert(Alert.AlertType.INFORMATION, "Marca Salva com sucesso!!", ButtonType.OK).showAndWait();
                
                autoCompletePopupMarcas.getSuggestions().clear();
                autoCompletePopupMarcas.getSuggestions().add("Nova Marca");
                autoCompletePopupMarcas.getSuggestions().addAll(ctrFab.getAllMarcas());
                
                autoCompletePopupModelos.getSuggestions().clear();
                autoCompletePopupModelos.getSuggestions().add("Novo Modelo");
            }
            else if (!autoCompletePopupMarcas.getSuggestions().contains(result.get().toUpperCase()))
            {
                new Alert(Alert.AlertType.ERROR, "Erro no cadastramento da marca\n"
                        + Banco.getCon().getMensagemErro(), ButtonType.OK).showAndWait();
            }
        }
    }

    private double calculaTotal()
    {
        double total = 0.0;
        for(Objeto obj : tvVeiculos.getItems())
            total += Double.parseDouble(obj.getParam10().replace(".", "").replace(",", "."));
        lbSubTotal.setText("Total da Compra: " + NumberFormat.getCurrencyInstance().format(total));
        return total;
    }

    @FXML
    private void veiculoEnterPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickAdicionarVeiculo(new ActionEvent());
    }

    private boolean isCliente()
    {
        if(!tfFornecedor.getText().equals("FORNECEDORES") && !tfFornecedor.getText().equals("CLIENTES") &&
                !tfFornecedor.getText().equals(""))
        {
            if(tfFornecedor.getText().contains("/"))
                return false;
            return true;
        }
        return false;
    }
}
