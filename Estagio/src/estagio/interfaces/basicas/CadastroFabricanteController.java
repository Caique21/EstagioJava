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
import estagio.utilidades.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CadastroFabricanteController implements Initializable
{
    private final ButtonType btMarca = new ButtonType("Marca");
    private final ButtonType btModelo = new ButtonType("Modelo");
    
    private int acao;
    private boolean ismarca;
    private Objeto obj;

    private Alert alerta;
    
    private final ctrFabricante ctrFab = ctrFabricante.instancia();
    
    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Pane paneFabricantes;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbModelos;
    @FXML
    private Label lbErroMarca;
    @FXML
    private Label lbErroModelo;
    @FXML
    private JFXTextField tfMarca;
    @FXML
    private JFXTextField tfModelo;
    @FXML
    private HBox hbModelo;
    @FXML
    private FontAwesomeIconView faPlus;
    @FXML
    private FontAwesomeIconView faCheck;
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
    private FontAwesomeIconView faTrash;

    /**
     * Initializes the controller class.
     */
    //marca, modelo, pesquisa, novo, confirmar, alterar, remover, cancelar
    private void setEstado(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5,boolean b6,boolean b7,boolean b8)
    {
        if(TelaPrincipalController.usuario_logado.getParam5().equals("baixo"))
        {
            btNovo.setDisable(true);
            btAlterar.setDisable(true);
            btRemover.setDisable(true);
            tfModelo.setDisable(true);
            tfMarca.setDisable(true);
            btConfirmar.setDisable(true);
        }
        else
        {
            tfModelo.setDisable(b2);
            tfMarca.setDisable(b1);
            btConfirmar.setDisable(b5);
            btNovo.setDisable(b4);
            btAlterar.setDisable(b6);
            btRemover.setDisable(b7);
            btPesquisar.setDisable(b3);
        }
    }

    private void inicializaDesing()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        nodes.add(paneFabricantes);
        
        nodes.add(btAlterar);
        nodes.add(btCancelar);
        nodes.add(btConfirmar);
        nodes.add(btNovo);
        nodes.add(btPesquisar);
        nodes.add(btRemover);
        
        nodes.add(tfMarca);
        nodes.add(tfModelo);
        
        nodes.add(lbModelos);
        nodes.add(lbTitulo);
        
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faEdit);
        nodes.add(faPlus);
        nodes.add(faSearch);
        nodes.add(faTrash);
        
        Utils.setDesign(1, nodes);
    }
    
    private void inicializa()
    {
        setEstado(true, true, false, false, true, true, true, true);
        acao = -1;
        clickPesquisar(new ActionEvent());
        
        tfMarca.clear();
        lbErroMarca.setText("");
        
        tfModelo.clear();
        lbErroModelo.setText("");
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
        acao = 0;
        
        alerta = new Alert(Alert.AlertType.CONFIRMATION, "Selecione qual deseja cadastrar", btMarca,btModelo);
        alerta.showAndWait();
        
        if(alerta.getResult() == btMarca)
        {
            ismarca = true;
            setEstado(false, true,true, true, false, true, true, false);
        }
        else if(alerta.getResult() == btModelo)
        {
            ismarca = false;
            setEstado(true, false,true, true, false, true, true, false);
        }
    }

    @FXML
    private void clickConfirmar(ActionEvent event)
    {
        if(ismarca && tfMarca.getText().trim().equals("") || !ismarca && tfModelo.getText().trim().equals(""))
        {
            if(ismarca && tfMarca.getText().trim().equals(""))
                lbErroMarca.setText("Campo requerido");
            else
                lbErroModelo.setText("Campo requerido");
        }
        else
        {
            lbErroMarca.setText("");
            lbErroModelo.setText("");
            
            if(acao == 0)
            {
                if(ismarca)
                {
                    alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cadastrar marca " + tfMarca.getText() +
                        "?", ButtonType.YES,ButtonType.NO);
                    alerta.showAndWait();

                    if(alerta.getResult() == ButtonType.YES)
                    {
                        if(ctrFab.salvarMarca(tfMarca))
                        {
                            inicializa();
                            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Marca cadastrada com sucesso!!", 
                                    ButtonType.OK);
                        }
                        else
                            alerta = new Alert(Alert.AlertType.ERROR, "Erro no cadastra da marca\n" +
                                Banco.getCon().getMensagemErro(), ButtonType.OK);
                        alerta.showAndWait();
                    }
                }
                else
                {
                    lbErroModelo.setText("");
                    alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cadastrar modelo " + tfModelo.getText() +
                        "?", ButtonType.YES,ButtonType.NO);
                    alerta.showAndWait();

                    if(alerta.getResult() == ButtonType.YES)
                    {
                        if(ctrFab.salvarModelo(tfModelo))
                        {
                            inicializa();
                            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Modelo cadastrada com sucesso!!", 
                                    ButtonType.OK);
                        }
                        else
                            alerta = new Alert(Alert.AlertType.ERROR, "Erro no cadastra do modelo\n" +
                                Banco.getCon().getMensagemErro(), ButtonType.OK);
                        alerta.showAndWait();
                    } 
                }
            }
            else if (acao == 1 && obj != null)
            {
                if (ismarca)
                {
                    lbErroMarca.setText("");
                    alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cadastrar marca " + tfMarca.getText()
                            + "?", ButtonType.YES, ButtonType.NO);
                    alerta.showAndWait();

                    if (alerta.getResult() == ButtonType.YES)
                    {
                        if (ctrFab.alterarMarca(Integer.parseInt(obj.getParam1()),tfMarca))
                        {
                            inicializa();
                            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Marca cadastrada com sucesso!!",
                                    ButtonType.OK);
                        }
                        else
                            alerta = new Alert(Alert.AlertType.ERROR, "Erro no cadastra da marca\n"
                                    + Banco.getCon().getMensagemErro(), ButtonType.OK);
                        alerta.showAndWait();
                    }
                }
                else
                {
                    alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja cadastrar modelo " + tfModelo.getText()
                            + "?", ButtonType.YES, ButtonType.NO);
                    alerta.showAndWait();

                    if (alerta.getResult() == ButtonType.YES)
                    {
                        if (ctrFab.alterarModelo(Integer.parseInt(obj.getParam3()),tfModelo))
                        {
                            inicializa();
                            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Modelo cadastrado com sucesso!!",
                                    ButtonType.OK);
                        }
                        else
                            alerta = new Alert(Alert.AlertType.ERROR, "Erro no cadastro do modelo\n"
                                    + Banco.getCon().getMensagemErro(), ButtonType.OK);
                        alerta.showAndWait();
                    }
                }
            }
        }
    }

    @FXML
    private void clickAlterar(ActionEvent event)
    {
        acao = 1;
        obj = tvFabricantes.getSelectionModel().getSelectedItem();
        
        alerta = new Alert(Alert.AlertType.CONFIRMATION, "Selecione qual deseja alterar", btMarca,btModelo);
        alerta.showAndWait();
        
        if(alerta.getResult() == btMarca)
        {
            ismarca = true;
            setEstado(false, true,true, true, false, true, true, false);
        }
        else if(alerta.getResult() == btModelo)
        {
            ismarca = false;
            setEstado(true, false,true, true, false, true, true, false);
        }
    }

    @FXML
    private void clickRemover(ActionEvent event)
    {
        if(!tvFabricantes.getItems().isEmpty() && tvFabricantes.getSelectionModel().getSelectedIndex() >= 0)
        {
            alerta = new Alert(Alert.AlertType.CONFIRMATION, "Selecione qual deseja remover", btMarca,btModelo);
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
            tvFabricantes.setItems(FXCollections.observableArrayList(ctrFab.get(tfModelo.getText())));
    }

    @FXML
    private void selecionaFabricante(MouseEvent event)
    {
        if(!tvFabricantes.getItems().isEmpty() && tvFabricantes.getSelectionModel().getFocusedIndex() >= 0)
        {
            setEstado(true, true, true, false, true, false, false, false);
            //fillFields(tvFabricantes.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void pesquisarModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void pesquisarModeloEnter(MouseEvent event)
    {
    }

    @FXML
    private void novoModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void novoModeloEnter(MouseEvent event)
    {
    }

    @FXML
    private void confirmarModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void confirmarModeloEnter(MouseEvent event)
    {
    }

    @FXML
    private void alterarModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void alterarModeloEnter(MouseEvent event)
    {
    }

    @FXML
    private void removerModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void removerModeloEnter(MouseEvent event)
    {
    }

    @FXML
    private void cancelarModeloExit(MouseEvent event)
    {
    }

    @FXML
    private void cancelarModeloEnter(MouseEvent event)
    {
    }
    
}
