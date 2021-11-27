/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.basicas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrFabricante;
import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.Utils;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastroFabricanteController implements Initializable
{
    private final ButtonType btMarca = new ButtonType("Marca");
    private final ButtonType btModelo = new ButtonType("Modelo");
    
    private ArrayList<String>marcas = new ArrayList<>();

    private Alert alerta;
    
    private final ctrFabricante ctrFab = ctrFabricante.instancia();
    
    private final Tooltip tooltip = new Tooltip();
    
    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneFabricantes;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbModelos;
    @FXML
    private JFXTextField tfMarca;
    @FXML
    private JFXTextField tfModelo;
    @FXML
    private HBox hbModelo;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private FontAwesomeIconView faEdit;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private TableView<Objeto> tvFabricantes;
    @FXML
    private TableColumn<Objeto,String> tcMarca;
    @FXML
    private TableColumn<Objeto,String> tcModelo;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btAlterar;
    @FXML
    private JFXButton btRemover;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private FontAwesomeIconView faTrash;

    /**
     * Initializes the controller class.
     */
    private void setEstado(boolean b1, boolean b2, boolean b3)
    {
        if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            btNovo.setDisable(true);
            btAlterar.setDisable(true);
            btRemover.setDisable(true);
        }
        else
        {
            btNovo.setDisable(b1);
            btAlterar.setDisable(b2);
            btRemover.setDisable(b3);
        }
    }

    private void inicializaDesing()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneFabricantes);
        
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btNovo);
        nodes.add(btPesquisar);
        nodes.add(btRemover);
        
        nodes.add(tfMarca);
        nodes.add(tfModelo);
        
        nodes.add(lbModelos);
        nodes.add(lbTitulo);
        
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faSearch);
        nodes.add(faTrash);
        
        Utils.setDesign(1, nodes);
        
        btNovo.setStyle(btNovo.getStyle() + ";-fx-cursor: default;");
        btAlterar.setStyle(btAlterar.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
        btRemover.setStyle(btRemover.getStyle() + ";-fx-cursor: default;");
    }
    
    private void inicializa()
    {
        tfMarca.clear();
        
        tfModelo.clear();
        
        setEstado(false,true,true);
        clickPesquisar(new ActionEvent());
        
        marcas = ctrFab.getAllMarcas();
        marcas.sort(String::compareToIgnoreCase);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesing();
        inicializa();
        
        tcMarca.setCellValueFactory(new PropertyValueFactory<>("param2"));
        tcModelo.setCellValueFactory(new PropertyValueFactory<>("param4"));
    }    

    @FXML
    private void clickNovo(ActionEvent event)
    {
        alerta = new Alert(Alert.AlertType.CONFIRMATION, "Selecione qual deseja cadastrar", btMarca,btModelo,ButtonType.CANCEL);
        alerta.showAndWait();
        
        if(alerta.getResult() != ButtonType.CANCEL)
        {
            Dialog<String> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
            dialog.getDialogPane().setPrefWidth(500);
            
            VBox vbox = new VBox();
            vbox.setPrefWidth(500);
            vbox.setPadding(new Insets(10, 10, 10, 10));
            
            if(alerta.getResult() == btMarca)
            {
                dialog.setTitle("Nova Marca");
                dialog.setHeaderText("Novo Cadastro de Marca");
               
                TextField marca = new JFXTextField();
                marca.setPromptText("Digite o nome da marca(*)");
                marca.setPrefWidth(350);
                
                marca.textProperty().addListener((observable, oldValue, newValue) ->
                {
                    if(marcas.contains(newValue.toUpperCase()))
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
                        return marca.getText();
                    return null;
                });
                Optional<String> result = dialog.showAndWait();
                
                if(result.isPresent())
                {
                    if(!marcas.contains(result.get().toUpperCase()) && ctrFab.salvarMarca(result.get()))
                    {
                        new Alert(Alert.AlertType.INFORMATION, "Marca Salva com sucesso!!", ButtonType.OK).showAndWait();
                        inicializa();
                    }
                    else if(!marcas.contains(result.get().toUpperCase()))
                        new Alert(Alert.AlertType.ERROR, "Erro no cadastramento da marca\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK).showAndWait();
                }   
            }
            else if(alerta.getResult() == btModelo)
            {
                dialog.setTitle("Novo Modelo");
                dialog.setHeaderText("Novo Cadastro de Modelo");
               
                TextField modelo = new JFXTextField();
                modelo.setPromptText("Digite o nome do modelo(*)");
                modelo.setPrefWidth(350);
                
                ComboBox<String>m = new ComboBox<>();
                m.getItems().addAll(marcas);
                m.getSelectionModel().select(0);
                
                ArrayList<String>modelos = ctrFab.getAllModelosByMarca(m.getSelectionModel().getSelectedItem());
                
                m.selectionModelProperty().addListener((observable) ->
                {
                    modelos.clear();
                    modelos.addAll(ctrFab.getAllModelosByMarca(m.getSelectionModel().getSelectedItem()));
                });
                
                modelo.textProperty().addListener((observable) ->
                {
                    if(modelos.stream().anyMatch(x -> x.equalsIgnoreCase(modelo.getText())))
                    {
                        new Alert(Alert.AlertType.ERROR, "Modelo " + modelo.getText() + " já cadastrado", 
                            ButtonType.OK).showAndWait();
                        modelo.setText("");
                    }
                });
                
                vbox.setSpacing(10);
                vbox.getChildren().addAll(m,modelo);
                dialog.getDialogPane().setContent(vbox);
                dialog.setResultConverter(dialogButton -> 
                {
                    if (dialogButton == ButtonType.YES && !modelo.getText().trim().equals(""))
                        return m.getSelectionModel().getSelectedItem() + ":" + modelo.getText();
                    return null;
                });
                Optional<String> result = dialog.showAndWait();
                
                if(result.isPresent())
                {
                    if(ctrFab.salvarModelo(result.get()))
                    {
                        new Alert(Alert.AlertType.INFORMATION, "Modelo Salvo com sucesso!!", ButtonType.OK).showAndWait();
                        inicializa();
                    }
                    else 
                        new Alert(Alert.AlertType.ERROR, "Erro no cadastramento do modelo\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK).showAndWait();
                }   
            }
        }
        /*if(alerta.getResult() == btMarca)
        {
            
            dialog.setTitle(titulo);
            dialog.setHeaderText(header);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            ismarca = true;
            setEstado(false, true,true, true, false, true, true, false);
        }
        else if(alerta.getResult() == btModelo)
        {
            ismarca = false;
            setEstado(false, false,true, true, false, true, true, false);
        }*/
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        alerta = new Alert(Alert.AlertType.CONFIRMATION, "Selecione o que deseja alterar", btMarca,btModelo,
            ButtonType.CANCEL);
        alerta.showAndWait();
        
        if(alerta.getResult() != ButtonType.CANCEL)
        {
            String altMarca = tvFabricantes.getSelectionModel().getSelectedItem().getParam2();
            String codigo = tvFabricantes.getSelectionModel().getSelectedItem().getParam1();
            
            Dialog<String> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
            dialog.getDialogPane().setPrefWidth(500);
            
            VBox vbox = new VBox();
            vbox.setPrefWidth(500);
            vbox.setPadding(new Insets(10, 10, 10, 10));
            
            if(alerta.getResult() == btMarca)
            {
                dialog.setTitle("Alterar Marca");
                dialog.setHeaderText("Alterar Cadastro da Marca");
               
                TextField marca = new JFXTextField();
                marca.setPromptText("Digite o nome da marca(*)");
                marca.setText(altMarca);
                marca.setPrefWidth(350);
                
                marca.textProperty().addListener((observable, oldValue, newValue) ->
                {
                    if(!marca.getText().toUpperCase().equals(altMarca) && marcas.contains(newValue.toUpperCase()))
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
                        return marca.getText();
                    return null;
                });
                Optional<String> result = dialog.showAndWait();
                
                if(result.isPresent())
                {
                    if(!marcas.contains(result.get().toUpperCase()) && 
                            ctrFab.alterarMarca(Integer.parseInt(codigo),result.get()))
                    {
                        new Alert(Alert.AlertType.INFORMATION, "Marca alterada com sucesso!!", ButtonType.OK).showAndWait();
                        inicializa();
                    }
                    else if(!marcas.contains(result.get().toUpperCase()))
                        new Alert(Alert.AlertType.ERROR, "Erro no cadastramento da marca\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK).showAndWait();
                }   
            }
            ////////////////////////////////////////////////////////////////////
            ///////////////////////////////MODELO///////////////////////////////
            ////////////////////////////////////////////////////////////////////
            else if(alerta.getResult() == btModelo)
            {
                String altModelo = tvFabricantes.getSelectionModel().getSelectedItem().getParam4();
                String codigo2 = tvFabricantes.getSelectionModel().getSelectedItem().getParam3();
                
                dialog.setTitle("Novo Modelo");
                dialog.setHeaderText("Novo Cadastro de Modelo");
               
                TextField modelo = new JFXTextField();
                modelo.setPromptText("Digite o nome do modelo(*)");
                modelo.setText(altModelo);
                modelo.setPrefWidth(350);
                
                ComboBox<String>m = new ComboBox<>();
                m.getItems().addAll(marcas);
                m.getSelectionModel().select(altMarca);
                
                vbox.setSpacing(10);
                vbox.getChildren().addAll(m,modelo);
                dialog.getDialogPane().setContent(vbox);
                dialog.setResultConverter(dialogButton -> 
                {
                    if (dialogButton == ButtonType.YES && !modelo.getText().trim().equals(""))
                        return m.getSelectionModel().getSelectedItem() + ":" + modelo.getText();
                    return null;
                });
                Optional<String> result = dialog.showAndWait();
                
                if(result.isPresent())
                {
                    if(altMarca.equals(result.get().substring(0, result.get().indexOf(":"))))
                    {
                        if(ctrFab.alterarModelo(Integer.parseInt(codigo2), result.get().substring(result.get().indexOf(":") + 1)))
                        {
                            new Alert(Alert.AlertType.INFORMATION, "Modelo alterado com sucesso!!", ButtonType.OK).showAndWait();
                            inicializa();
                        }
                        else
                            new Alert(Alert.AlertType.ERROR, "Erro na alteração do modelo\n" + 
                                    Banco.getCon().getMensagemErro(), ButtonType.OK).showAndWait();
                    }
                    else
                    {
                        altModelo = result.get().substring(result.get().indexOf(":") + 1);
                        String aux = result.get().substring(0, result.get().indexOf(":"));
                        if(ctrFab.alterarModelo(Integer.parseInt(codigo2), altModelo,aux))
                        {
                            new Alert(Alert.AlertType.INFORMATION, "Modelo alterado com sucesso!!", ButtonType.OK).showAndWait();
                            inicializa();
                        }
                        else
                            new Alert(Alert.AlertType.ERROR, "Erro na alteração do modelo\n" + 
                                    Banco.getCon().getMensagemErro(), ButtonType.OK).showAndWait();
                    }
                }   
            }
        }
    }
    
    @FXML
    private void clickRemover(ActionEvent event)
    {
        if(!tvFabricantes.getItems().isEmpty() && tvFabricantes.getSelectionModel().getSelectedIndex() >= 0)
        {
            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Selecione qual deseja remover", btMarca,btModelo,ButtonType.CANCEL);
            alerta.showAndWait();

            if(alerta.getResult() == btMarca)
            {
                alerta = new Alert(Alert.AlertType.WARNING, "Remover a marca apagará todos os modelos vinculados a ela,"
                    + "deseja confirmar", ButtonType.YES,ButtonType.NO);
                alerta.showAndWait();
                
                if(alerta.getResult() == ButtonType.YES)
                {
                    if(ctrFab.apagarMarca(tvFabricantes.getSelectionModel().getSelectedItem()))
                    {
                        inicializa();
                        alerta = new Alert(Alert.AlertType.INFORMATION, "Marca removida com sucesso!!!", ButtonType.OK);
                    }
                    else
                        alerta = new Alert(Alert.AlertType.ERROR, "Erro na remoção da marca\n" + 
                                Banco.getCon().getMensagemErro(), ButtonType.OK);
                    alerta.showAndWait();
                }
            }
            else if(alerta.getResult() == btModelo)
            {
                if(ctrFab.apagarModelo(tvFabricantes.getSelectionModel().getSelectedItem()))
                {
                    inicializa();
                    alerta = new Alert(Alert.AlertType.INFORMATION, "Modelo removida com sucesso!!!", ButtonType.OK);
                }
                else
                    alerta = new Alert(Alert.AlertType.ERROR, "Erro na remoção da modelo\n" + 
                            Banco.getCon().getMensagemErro(), ButtonType.OK);
                alerta.showAndWait();
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
        if(tfMarca.getText().trim().equals("") && tfModelo.getText().trim().equals(""))
            tvFabricantes.setItems(FXCollections.observableArrayList(ctrFab.getAll()));
        else if(!tfMarca.getText().trim().equals("") && !tfModelo.getText().trim().equals(""))
            tvFabricantes.setItems(FXCollections.observableArrayList(ctrFab.get(tfMarca.getText(),tfModelo.getText())));
        else if(!tfMarca.getText().trim().equals(""))
            tvFabricantes.setItems(FXCollections.observableArrayList(ctrFab.get(tfMarca.getText())));
        else
            tvFabricantes.setItems(FXCollections.observableArrayList(ctrFab.getByModelo(tfModelo.getText())));
    }

    @FXML
    private void selecionaFabricante(MouseEvent event)
    {
        if(!tvFabricantes.getItems().isEmpty() && tvFabricantes.getSelectionModel().getFocusedIndex() >= 0)
        {
            setEstado(false,false,false);
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
        tooltip.setText("Novo Registro");
        ToolTip.bindTooltip(btNovo, tooltip);
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
        tooltip.setText("Alterar");
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
        tooltip.setText("Remover");
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
        tooltip.setText("Pesquisar");
        ToolTip.bindTooltip(btPesquisar, tooltip);
    }
}
