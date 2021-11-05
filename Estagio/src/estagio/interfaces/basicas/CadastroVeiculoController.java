/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrFabricante;
import estagio.controladores.ctrVeiculo;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastroVeiculoController implements Initializable
{
    private int acao;
    private Objeto veiculo;
    
    private final Tooltip tooltip = new Tooltip();
    private final ToggleGroup goup = new ToggleGroup();
    
    private final ctrVeiculo ctrVei = ctrVeiculo.instancia();
    private final ctrFabricante ctrFab = ctrFabricante.instancia();
    
    private JFXAutoCompletePopup<String> autoCompletePopupMarcas = new JFXAutoCompletePopup<>();
    private JFXAutoCompletePopup<String> autoCompletePopupModelos = new JFXAutoCompletePopup<>();

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneInfo;
    @FXML
    private Pane panePesquisa;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbVeiculo;
    @FXML
    private Label lbPesquisa;
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
    private JFXTextField tfPlaca;
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
    private JFXTextField tfPlacaPesquisa;
    @FXML
    private JFXTextField tfMarcaPesquisa;
    @FXML
    private JFXTextField tfModeloPesquisa;
    @FXML
    private JFXCheckBox rbMercosul;
    @FXML
    private JFXRadioButton rbPlaca;
    @FXML
    private JFXRadioButton rbMarca;
    @FXML
    private JFXRadioButton rbModelo;
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
    private TableView<Objeto> tvVeiculos;
    @FXML
    private TableColumn<Objeto,String> tcPlaca;
    @FXML
    private TableColumn<Objeto,String> tcMarca;
    @FXML
    private TableColumn<Objeto,String> tcModelo;
    @FXML
    private TableColumn<Objeto,String> tcChassi;
    @FXML
    private TableColumn<Objeto,String> tcAno;
    @FXML
    private TableColumn<Objeto,String> tcCor;
    @FXML
    private JFXRadioButton rbTodos;
    @FXML
    private VBox painelCentral;
    @FXML
    private JFXTextField tfPlacaMercosul;
    @FXML
    private JFXTextArea taDescricao;
    @FXML
    private JFXCheckBox cbVendidos;

    /**
     * Initializes the controller class.
     */
    private void inicializaLabels()
    {
        lbErroAno.setText("");
        lbErroChassi.setText("");
        lbErroCor.setText("");
        lbErroMarca.setText("");
        lbErroModelo.setText("");
        lbErroPlaca.setText("");
    }
    
    public void setEstado(Boolean b1,Boolean b2,Boolean b3,Boolean b4,Boolean b5,Boolean b6,Boolean b7)
    {
        if(!TelaPrincipalController.usuario_logado.getParam5().equals("alto"))
            btRemover.setDisable(true);
        else if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            paneInfo.setDisable(true);
            btNovo.setDisable(true);
            btAlterar.setDisable(true);
            btRemover.setDisable(true);
        }
        else
        {
            paneInfo.setDisable(b1);
            panePesquisa.setDisable(b2);
            btNovo.setDisable(b3);
            btAlterar.setDisable(b5);
            btRemover.setDisable(b6);
        }
        btConfirmar.setDisable(b4);
        btCancelar.setDisable(b7);
    }
    
    private void limparCampos()
    {
        tfAno.clear();
        tfChassi.clear();
        tfCor.clear();
        tfMarca.clear();
        tfModelo.clear();
        tfPlaca.clear();
        tfPlacaMercosul.clear();
        taDescricao.clear();
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
        
        nodes.add(tfAno);
        nodes.add(tfChassi);
        nodes.add(tfCor);
        nodes.add(tfMarcaPesquisa);
        nodes.add(tfMarca);
        nodes.add(tfModelo);
        nodes.add(tfModeloPesquisa);
        nodes.add(tfPlaca);
        nodes.add(tfPlacaMercosul);
        nodes.add(tfPlacaPesquisa);
        
        nodes.add(taDescricao);
        
        nodes.add(cbVendidos);
        
        nodes.add(lbVeiculo);
        nodes.add(lbPesquisa);
        nodes.add(lbTitulo);
        
        nodes.add(rbMarca);
        nodes.add(rbTodos);
        nodes.add(rbMercosul);
        nodes.add(rbModelo);
        nodes.add(rbPlaca);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faSearch);
        nodes.add(faTrash);
        
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
        
        tvVeiculos.setPrefHeight(panePesquisa.getPrefHeight() - 142);
        tvVeiculos.setPrefWidth(panePesquisa.getPrefWidth() - 80);
        
        //REORGANIZA OS TAMANHOS DAS COLUNAS DA TABELA
        int sobra = (int)((tvVeiculos.getPrefWidth() - 1060)/2);
        tcMarca.setPrefWidth(tcMarca.getPrefWidth() + sobra);
        tcModelo.setPrefWidth(tcModelo.getPrefWidth() + sobra);
    }
    
    private void inicializa()
    {
        acao = -1;
        veiculo = null;
        clickPesquisar(new ActionEvent());
        limparCampos();
        inicializaLabels();
        setEstado(true, false, false, true, true, true, true);
        cbVendidos.setSelected(false);
        
        autoCompletePopupMarcas.getSuggestions().clear();
        autoCompletePopupMarcas.getSuggestions().add("Nova Marca");
        autoCompletePopupMarcas.getSuggestions().addAll(ctrFab.getAllMarcas());
        
        autoCompletePopupModelos.getSuggestions().clear();
        autoCompletePopupModelos.getSuggestions().add("Novo Modelo");
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
        
        rbMarca.setToggleGroup(goup);
        rbModelo.setToggleGroup(goup);
        rbPlaca.setToggleGroup(goup);
        rbTodos.setToggleGroup(goup);
        rbTodos.setSelected(true);
        
        MaskFieldUtil.toUpperCase(tfPlaca);
        MaskFieldUtil.toUpperCase(tfPlacaPesquisa);
        MaskFieldUtil.toUpperCase(tfPlacaMercosul);
        MaskFieldUtil.placaNormalField(tfPlaca);
        MaskFieldUtil.placaMercosulField(tfPlacaMercosul);
        MaskFieldUtil.placaField(tfPlacaPesquisa);
        MaskFieldUtil.yearField(tfAno);
        
        inicializa();
        setListeners();
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        inicializa();
        acao = 0;
        setEstado(false, true, true, false, true, true, false);
        tfPlaca.requestFocus();
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(valida())
        {
            Alert alerta;
            String placa = rbMercosul.isSelected()? tfPlacaMercosul.getText() : tfPlaca.getText();
            
            if(acao == 0)
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cadastrar véiculo?", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrVei.salvar(placa,tfMarca,tfModelo,tfChassi,tfCor,tfAno,taDescricao))
                    {
                        inicializa();
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Veículo cadastrado com sucesso!!!", 
                                ButtonType.OK);
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro no cadastramento do veículo\n" + 
                                Banco.getCon().getMensagemErro(), ButtonType.OK);
                    alerta.showAndWait();
                }
            }
            else if(acao == 1)
            {
                alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja alterar véiculo " + tfMarca.getText() + 
                    " " + tfModelo.getText() + " com placa " + (rbMercosul.isSelected()? tfPlacaMercosul.getText() : 
                        tfPlaca.getText()) + "?", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrVei.alterar(Integer.parseInt(veiculo.getParam1()),placa,tfMarca,tfModelo,tfChassi,tfCor,
                            tfAno,taDescricao))
                    {
                        inicializa();
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Veículo alterado com sucesso!!!", 
                                ButtonType.OK);
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro na alteração do veículo\n" + 
                                Banco.getCon().getMensagemErro(), ButtonType.OK);
                    alerta.showAndWait();
                }
            }
        }
    }

    private boolean valida()
    {
        String erros = "";
        inicializaLabels();
        
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
            if(tfPlaca.getText().trim().equals(""))
            {
                Alert a = new Alert(Alert.AlertType.WARNING, "Campo placa vazio, deseja continuar?", ButtonType.YES,ButtonType.NO);
                a.showAndWait();
                
                if(a.getResult() == ButtonType.NO)
                {
                    erros += "Digite a placa do veículo\n";
                    lbErroPlaca.setText("Campo requerido");
                }
            }
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
        
        if(!erros.trim().equals(""))
            new Alert(Alert.AlertType.ERROR, erros, ButtonType.OK).showAndWait();
        
        return erros.trim().equals("");
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        if(!tvVeiculos.getItems().isEmpty() && tvVeiculos.getSelectionModel().getSelectedIndex() >= 0)
        {
            veiculo = tvVeiculos.getSelectionModel().getSelectedItem();
            acao = 1;
            setEstado(false, true, true, false, true, true, false);
        }
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        if(!tvVeiculos.getItems().isEmpty() && tvVeiculos.getSelectionModel().getSelectedIndex() >= 0)
        {
            String modelo = tfModelo.getText(),marca = tfMarca.getText();
            String placa = rbMercosul.isSelected()? tfPlacaMercosul.getText() : tfPlaca.getText();
            Alert al = new Alert(Alert.AlertType.CONFIRMATION, "Deseja remover veículo " + marca + " " + modelo + 
                 " com placa " + placa + "?", ButtonType.YES,ButtonType.NO);
            al.showAndWait();
            
            if(al.getResult() == ButtonType.YES)
            {
                if(ctrVei.apagar(Integer.parseInt(tvVeiculos.getSelectionModel().getSelectedItem().getParam1())))
                {
                    inicializa();
                    al = new Alert(Alert.AlertType.INFORMATION, "Veículo removido com sucesso!!!", ButtonType.OK);
                }
                else
                    al = new Alert(Alert.AlertType.ERROR, "Erro na remoção do veículo\n" + 
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
        if(rbTodos.isSelected())
            if(cbVendidos.isSelected())
                tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getAll()));
            else
                tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getAll(true)));
        else if(rbMarca.isSelected())
            if(cbVendidos.isSelected())
                tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getByMarca(tfMarcaPesquisa.getText())));
            else
                tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getByMarca(tfMarcaPesquisa.getText(),true)));
        else if(rbModelo.isSelected())
            if(cbVendidos.isSelected()) 
                tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getByModelo(tfModeloPesquisa.getText())));
            else
                tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getByModelo(tfModeloPesquisa.getText(),true)));
        else if(rbPlaca.isSelected())
            if(cbVendidos.isSelected()) 
                tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getByPlaca(tfPlacaPesquisa.getText())));
            else
                tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getByPlaca(tfPlacaPesquisa.getText(),true)));
    }

    @FXML
    private void selecionaVeiculo(MouseEvent event)
    {
        if(!tvVeiculos.getItems().isEmpty() && tvVeiculos.getSelectionModel().getSelectedIndex() >= 0)
        {
            setEstado(true, false, false, true, false, false, false);
            fillFields(tvVeiculos.getSelectionModel().getSelectedItem());
        }
    }

    private void fillFields(Objeto vei)
    {
        limparCampos();
        
        if(vei.getParam2() != null && !vei.getParam2().trim().equals(""))
        {
            if(vei.getParam2().contains("-"))
            {
                rbMercosul.setSelected(false);
                clickMercosul(new ActionEvent());
                tfPlaca.setText(vei.getParam2());
            }
            else
            {
                rbMercosul.setSelected(true);
                clickMercosul(new ActionEvent());
                tfPlacaMercosul.setText(vei.getParam2());
            }
        }
        
        tfMarca.setText(vei.getParam7());
        tfModelo.setText(vei.getParam8());
        tfChassi.setText(vei.getParam4());
        tfAno.setText(vei.getParam5());
        tfCor.setText(vei.getParam6());
        taDescricao.setText(vei.getParam9());
        
        autoCompletePopupModelos.getSuggestions().clear();
        autoCompletePopupModelos.getSuggestions().add("Novo Modelo");
        autoCompletePopupModelos.getSuggestions().addAll(ctrFab.getAllModelosByMarca(tfMarca.getText()));
        
        autoCompletePopupMarcas.hide();
        autoCompletePopupModelos.hide();
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
        tooltip.setText("Remover Veículo");
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
        tooltip.setText("Remover Veículo");
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
        tooltip.setText("Pesquisar Veículo");
        ToolTip.bindTooltip(btPesquisar, tooltip);
    }

    @FXML
    private void clickTodos(ActionEvent event)
    {
        tfMarcaPesquisa.setEditable(false);
        tfModeloPesquisa.setEditable(false);
        tfPlacaPesquisa.setEditable(false);
        clickPesquisar(new ActionEvent());
    }

    @FXML
    private void clickPlaca(ActionEvent event)
    {
        tfModeloPesquisa.setVisible(false);
        tfMarcaPesquisa.setVisible(false);
        tfPlacaPesquisa.setVisible(true);
        tfPlacaPesquisa.setEditable(true);
        tfPlacaPesquisa.clear();
    }

    @FXML
    private void clickMarca(ActionEvent event)
    {
        tfModeloPesquisa.setVisible(false);
        tfMarcaPesquisa.setVisible(true);
        tfMarcaPesquisa.setEditable(true);
        tfPlacaPesquisa.setVisible(false);
        tfMarcaPesquisa.clear();
    }

    @FXML
    private void clickModelo(ActionEvent event)
    {
        tfModeloPesquisa.setVisible(true);
        tfModeloPesquisa.setEditable(true);
        tfMarcaPesquisa.setVisible(false);
        tfPlacaPesquisa.setVisible(false);
        tfModeloPesquisa.clear();
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
    
    private void setListeners()
    {
        cbVendidos.selectedProperty().addListener((observable) ->
        {
            clickPesquisar(new ActionEvent());
        });
        
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
                autoCompletePopupMarcas.hide();
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
        
        tfMarca.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue && !tfMarca.getText().trim().equals(""))
            {
                if(autoCompletePopupMarcas.getSuggestions().contains(tfMarca.getText()))
                {
                    autoCompletePopupModelos.getSuggestions().clear();
                    autoCompletePopupModelos.getSuggestions().add("Novo Modelo");
                    autoCompletePopupModelos.getSuggestions().addAll(ctrFab.getAllModelosByMarca(tfMarca.getText()));
                }
                if(!autoCompletePopupMarcas.getSuggestions().contains(tfMarca.getText()) 
                    && !lbErroMarca.getText().equals("Marca não cadastrada"))
                {
                    autoCompletePopupModelos.getSuggestions().clear();
                    autoCompletePopupModelos.getSuggestions().add("Novo Modelo");
                    
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Marca não cadastrada, deseja cadastrar?", 
                            ButtonType.YES,ButtonType.NO);
                    a.showAndWait();
                    
                    if(a.getResult() == ButtonType.YES)
                    {
                        if(ctrFab.salvarMarca(tfMarca.getText()))
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
                        }
                        else
                            Notifications.create()
                                .darkStyle()
                                //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                                .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                                .text("Erro no cadastramento da marca")
                                .showError();
                    }
                    else
                        lbErroMarca.setText("Marca não cadastrada");
                }
            }
            else
            {
                if(autoCompletePopupMarcas.getSuggestions().contains(tfMarca.getText()))
                    lbErroMarca.setText("");
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
        
        tfModelo.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if(!newValue && !tfModelo.getText().trim().equals(""))
            {
                if(!autoCompletePopupModelos.getSuggestions().contains(tfModelo.getText()) 
                    && !lbErroModelo.getText().equals("Modelo não cadastrado"))
                {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Modelo não cadastrado, deseja cadastrar?", 
                            ButtonType.YES,ButtonType.NO);
                    a.showAndWait();
                    
                    if(a.getResult() == ButtonType.YES)
                    {
                        if(ctrFab.salvarModelo(tfMarca.getText() + ":" + tfModelo.getText()))
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
                        }
                        else
                            Notifications.create()
                                .darkStyle()
                                //.graphic(new Rectangle(300, 200, Color.BLACK)) // sets node to display
                                .hideAfter(Duration.seconds(2)).position(Pos.BOTTOM_CENTER)
                                .text("Erro no cadastramento do modelo")
                                .showError();
                    }
                    else
                        lbErroModelo.setText("Modelo não cadastrado");
                }
            }
            else
            {
                if(autoCompletePopupModelos.getSuggestions().contains(tfModelo.getText()))
                    lbErroModelo.setText("");
            }
        });
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

    @FXML
    private void confirmarPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
            clickConfirmar(new ActionEvent());
    }
}
