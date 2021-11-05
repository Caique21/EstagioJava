/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.buscas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrVeiculo;
import estagio.utilidades.MaskFieldUtil;
import estagio.utilidades.Objeto;
import estagio.utilidades.ToolTip;
import estagio.utilidades.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class BuscarVeiculoController implements Initializable
{
    private ArrayList<Objeto>veiculos;
    private final ToggleGroup goup1 = new ToggleGroup();
    private final ctrVeiculo ctrVei = ctrVeiculo.instancia();
    private final Tooltip tooltip = new Tooltip();

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Label lbTitulo;
    @FXML
    private JFXButton btSelecionar;
    @FXML
    private FontAwesomeIconView faCheck;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private FontAwesomeIconView faClose;
    @FXML
    private JFXTextField tfPlaca;
    @FXML
    private JFXTextField tfMarca;
    @FXML
    private JFXTextField tfModelo;
    @FXML
    private JFXRadioButton rbTodos;
    @FXML
    private JFXRadioButton rbPlaca;
    @FXML
    private JFXRadioButton rbMarca;
    @FXML
    private JFXRadioButton rbModelo;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private FontAwesomeIconView faSearch;
    @FXML
    private TableView<Objeto> tvVeiculos;
    @FXML
    private TableColumn<Objeto, String> tcPlaca;
    @FXML
    private TableColumn<Objeto, String> tcModelo;
    @FXML
    private TableColumn<Objeto, String> tcMarca;
    @FXML
    private TableColumn<Objeto, String> tcChassi;
    @FXML
    private TableColumn<Objeto, String> tcAno;
    @FXML
    private TableColumn<Objeto, String> tcCor;
    @FXML
    private TableColumn<Objeto, String> tcDescricao;

    /**
     * Initializes the controller class.
     */
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);
        
        nodes.add(btSelecionar);
        nodes.add(btCancelar);
        nodes.add(btPesquisar);
        
        nodes.add(tfMarca);
        nodes.add(tfModelo);
        nodes.add(tfPlaca);
       
        nodes.add(lbTitulo);
        
        nodes.add(rbModelo);
        nodes.add(rbMarca);
        nodes.add(rbPlaca);
        nodes.add(rbTodos);
        
        nodes.add(faCheck);
        nodes.add(faClose);
        nodes.add(faSearch);
        
        Utils.setDesign(1, nodes);
        
        btSelecionar.setStyle(btSelecionar.getStyle() + ";-fx-cursor: default;");
        btCancelar.setStyle(btCancelar.getStyle() + ";-fx-cursor: default;");
        btPesquisar.setStyle(btPesquisar.getStyle() + ";-fx-cursor: default;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesign();
        
        tvVeiculos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tcAno.setCellValueFactory(new PropertyValueFactory<>("param5"));
        tcChassi.setCellValueFactory(new PropertyValueFactory<>("param4"));
        tcCor.setCellValueFactory(new PropertyValueFactory<>("param6"));
        tcMarca.setCellValueFactory(new PropertyValueFactory<>("param7"));
        tcModelo.setCellValueFactory(new PropertyValueFactory<>("param8"));
        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("param2"));
        //tcValor.setCellValueFactory(new PropertyValueFactory<>("param10"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("param9"));
        
        rbTodos.setToggleGroup(goup1);
        rbMarca.setToggleGroup(goup1);
        rbModelo.setToggleGroup(goup1);
        rbPlaca.setToggleGroup(goup1);
        rbTodos.setSelected(true);
        tfPlaca.setEditable(false);
        
        clickPesquisar(new ActionEvent());
    }    

    @FXML
    private void clickSelecionar(ActionEvent event)
    {
        if(!tvVeiculos.getItems().isEmpty() && !tvVeiculos.getSelectionModel().getSelectedItems().isEmpty())
        {
            veiculos = new ArrayList<>();
            Dialog<String> dialog = new Dialog<>();
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
            dialog.getDialogPane().setPrefWidth(500);
            
            JFXTextField valor = new JFXTextField();
            valor.setLabelFloat(true);
            valor.setPromptText("Valor sugerido");
            MaskFieldUtil.monetaryField(valor);
            
            Label descricao = new Label();
            
            VBox vbox = new VBox(10);
            
            boolean flag = true;
            vbox.getChildren().addAll(descricao, valor);
            for (int i = 0; i < tvVeiculos.getSelectionModel().getSelectedItems().size() && flag; i++)
            {
                Objeto vei = tvVeiculos.getSelectionModel().getSelectedItems().get(i);
                descricao.setText("Veículo " + (i + 1) + " de " + tvVeiculos.getSelectionModel().getSelectedItems().size());
                descricao.setText(descricao.getText() + "\nPlaca: ");
                if(vei.getParam2() == null || (vei.getParam2() != null && vei.getParam2().equals("")))
                    descricao.setText(descricao.getText() + " ");
                else
                    descricao.setText(descricao.getText() + vei.getParam2());
                descricao.setText(descricao.getText() + ", Marca: " + vei.getParam7());
                descricao.setText(descricao.getText() + ", Modelo: " + vei.getParam8() + ", Chassi: " + vei.getParam4());
                descricao.setText(descricao.getText() + ", Ano: " + vei.getParam5() + ", Cor: " + vei.getParam6());
                
                valor.clear();
                valor.setText("" + Utils.exibeCentavos(ctrVei.getPrecoSugerido(Integer.parseInt(vei.getParam1()))));
                
                dialog.getDialogPane().setContent(vbox);
                dialog.setResultConverter(dialogButton ->
                {
                    if (dialogButton == ButtonType.YES)
                    {
                        if(!valor.getText().trim().equals("") && Utils.convertStringToDouble(valor.getText()) > 0)
                            return valor.getText();
                    }
                    return null;
                });
                Optional<String> result = dialog.showAndWait();

                if (result.isPresent())
                {
                    vei.setParam10(result.get());
                    veiculos.add(vei);
                }
                else
                {
                    flag = false;
                    veiculos = new ArrayList<>();
                }
            }
            
            if(flag)
            {
                Stage stage = (Stage) btSelecionar.getScene().getWindow();
                stage.close();
            }
            else
                new Alert(Alert.AlertType.ERROR, "Erro durante preenchimento dos valores, digite novamente", ButtonType.OK)
                        .showAndWait();
        }
        else if(tvVeiculos.getItems().isEmpty())
            new Alert(Alert.AlertType.ERROR, "Pesquisa vazia", ButtonType.OK).showAndWait();
        else
            new Alert(Alert.AlertType.ERROR, "Selecione um ou mais veículos", ButtonType.OK).showAndWait();
    }

    @FXML
    private void clickCancelar(ActionEvent event)
    {
        veiculos = null;
        Stage stage = (Stage) btSelecionar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickPesquisar(ActionEvent event)
    {
        if(rbTodos.isSelected())
            tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getAll(true)));
        else if(rbMarca.isSelected())
            tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getByMarca(tfMarca.getText(),true)));
        else if(rbModelo.isSelected())
            tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getByModelo(tfModelo.getText(),true)));
        else if(rbPlaca.isSelected())
            tvVeiculos.setItems(FXCollections.observableArrayList(ctrVei.getByPlaca(tfPlaca.getText(),true)));
    }

    @FXML
    private void clickTodos(ActionEvent event)
    {
        tfMarca.setEditable(false);
        tfModelo.setEditable(false);
        tfPlaca.setEditable(false);
        clickPesquisar(new ActionEvent());
    }

    @FXML
    private void clickPlaca(ActionEvent event)
    {
        tfPlaca.setVisible(true);
        tfPlaca.setEditable(true);
        tfPlaca.clear();
        tfMarca.setVisible(false);
        tfModelo.setVisible(false);
    }

    @FXML
    private void clickMarca(ActionEvent event)
    {
        tfMarca.setVisible(true);
        tfMarca.setEditable(true);
        tfMarca.clear();
        tfPlaca.setVisible(false);
        tfModelo.setVisible(false);
    }

    @FXML
    private void clickModelo(ActionEvent event)
    {
        tfModelo.setVisible(true);
        tfModelo.setEditable(true);
        tfModelo.clear();
        tfPlaca.setVisible(false);
        tfMarca.setVisible(false);
    }

    @FXML
    private void selecionarExit(MouseEvent event)
    {
        btSelecionar.setStyle(btSelecionar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
    }

    @FXML
    private void selecionarEnter(MouseEvent event)
    {
        btSelecionar.setStyle(btSelecionar.getStyle().replace("-fx-cursor: default;", "-fx-cursor: hand;"));
        tooltip.setText("Selecionar veículo(s)");
        ToolTip.bindTooltip(btSelecionar, tooltip);
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
        tooltip.setText("Cancelar pesquisa");
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
        tooltip.setText("Pesquisa veículo");
        ToolTip.bindTooltip(btPesquisar, tooltip);
    }

    public ObservableList<Objeto> getVeiculos()
    {
        return FXCollections.observableArrayList(veiculos);
    }

    public void resetVeiculos()
    {
        veiculos = new ArrayList<>();
    }
    
}
