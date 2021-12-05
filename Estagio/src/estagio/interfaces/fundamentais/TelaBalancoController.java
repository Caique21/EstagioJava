/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces.fundamentais;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import estagio.controladores.ctrPagamento;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import estagio.utilidades.ToolTip;
import javafx.scene.control.Tooltip;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaBalancoController implements Initializable
{
    
    private final ctrPagamento ctrPag = ctrPagamento.instancia();
    private final Tooltip tooltip = new Tooltip();

    @FXML
    private Tab tabSumario;
    @FXML
    private Tab tabReceita;
    @FXML
    private Tab tabDespesa;
    @FXML
    private BorderPane panePrincipal;
    @FXML
    private FontAwesomeIconView faBalance;
    @FXML
    private Label lbSaldoGlobal;
    @FXML
    private Label lbSaldoGlobalValor;
    @FXML
    private FontAwesomeIconView faIn;
    @FXML
    private Label lbLucroPrejuizo;
    @FXML
    private Label lbLucroPrejuizoValor;
    @FXML
    private FontAwesomeIconView faOut;
    @FXML
    private FontAwesomeIconView faIn2;
    @FXML
    private Label lbReceitaSumario;
    @FXML
    private Label lbValorReceitaSumario;
    @FXML
    private FontAwesomeIconView faOut2;
    @FXML
    private Label lbDespesaSumario;
    @FXML
    private Label lbValorDespesaSumario;
    @FXML
    private LineChart<String, Number> chartGlobal;
    @FXML
    private LineChart<?, ?> chartLucroPrejuizo;
    @FXML
    private LineChart<?, ?> chartReceitaSumario;
    @FXML
    private LineChart<?, ?> chartDespesaSumario;
    
    
    @FXML
    private Pane paneReceitas;
    @FXML
    private Label lbTituloReceita;
    @FXML
    private LineChart<?, ?> chartReceita;
    @FXML
    private Label lbReceitaEsteMes;
    @FXML
    private Label lbValorReceitaEsteMes;
    @FXML
    private Label lbReceitaUltimoMes;
    @FXML
    private Label lbValorReceitaUltimoMes;
    @FXML
    private Label lbReceitaEsteAno;
    @FXML
    private Label lbValorReceitaEsteAno;
    @FXML
    private Label lbReceitaUltimoAno;
    @FXML
    private Label lbValorReceitaUltimoAno;
    @FXML
    private TableView<?> tvReceita;
    @FXML
    private TableColumn<?, ?> tcDetalheReceita;
    @FXML
    private TableColumn<?, ?> tcEsteMesReceita;
    @FXML
    private TableColumn<?, ?> tcUltimoMesReceita;
    @FXML
    private TableColumn<?, ?> tcEsteAnoReceita;
    @FXML
    private TableColumn<?, ?> tcUltimoAnoReceita;
    
    
    @FXML
    private Pane paneDespesa;
    @FXML
    private Label lbGastoTotal;
    @FXML
    private Label lbDespesaEsteMes;
    @FXML
    private Label lbValorDespesaEsteMes;
    @FXML
    private Label lbDespesaUltimoMes;
    @FXML
    private Label lbValorDespesaUltimoMes;
    @FXML
    private Label lbDespesaEsteAno;
    @FXML
    private Label lbValorDespesaEsteAno;
    @FXML
    private Label lbDespesaUltimoAno;
    @FXML
    private Label lbValorDespesaUltimoAno;
    @FXML
    private LineChart<?, ?> chartDespesa;
    @FXML
    private TableView<?> tvDespesa;
    @FXML
    private TableColumn<?, ?> tcDetalheDespesa;
    @FXML
    private TableColumn<?, ?> tcEsteMesDespesa;
    @FXML
    private TableColumn<?, ?> tcUltimoMesDespesa;
    @FXML
    private TableColumn<?, ?> tcEsteAnoDespesa;
    @FXML
    private TableColumn<?, ?> tcUltimoAnoDespesa;

    /**
     * Initializes the controller class.
     */
    private void inicializaDesign()
    {
        List<Node>nodes = new ArrayList<>();
        nodes.add(panePrincipal);        
        nodes.add(paneDespesa);        
        nodes.add(paneReceitas);        
        
        nodes.add(lbDespesaEsteAno);
        nodes.add(lbDespesaEsteMes);
        nodes.add(lbDespesaSumario);
        nodes.add(lbDespesaUltimoAno);
        nodes.add(lbDespesaUltimoMes);
        nodes.add(lbGastoTotal);
        nodes.add(lbLucroPrejuizo);
        nodes.add(lbLucroPrejuizoValor);
        nodes.add(lbReceitaEsteAno);
        nodes.add(lbReceitaEsteMes);
        nodes.add(lbReceitaSumario);
        nodes.add(lbReceitaUltimoAno);
        nodes.add(lbReceitaUltimoMes);
        nodes.add(lbSaldoGlobal);
        nodes.add(lbSaldoGlobalValor);
        nodes.add(lbTituloReceita);
        nodes.add(lbValorDespesaEsteAno);
        nodes.add(lbValorDespesaEsteMes);
        nodes.add(lbValorDespesaSumario);
        nodes.add(lbValorDespesaUltimoAno);
        nodes.add(lbValorDespesaUltimoMes);
        nodes.add(lbValorReceitaEsteAno);
        nodes.add(lbValorReceitaEsteMes);
        nodes.add(lbValorReceitaSumario);
        nodes.add(lbValorReceitaUltimoAno);
        nodes.add(lbValorReceitaUltimoMes);
                
        nodes.add(faBalance);
        nodes.add(faIn);
        nodes.add(faIn2);
        nodes.add(faOut);
        nodes.add(faOut2);
        
        Utils.setDesign(1, nodes);
        chartGlobal.setStyle("CHART_COLOR_1: #ff0000 ; CHART_COLOR_2: #0000FF ;");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        inicializaDesign();
        XYChart.Series global = new XYChart.Series();
        
        ArrayList<Objeto>re = ctrPag.getPagamentosAnual();
        int mes_atual = LocalDate.now().getMonthValue();
        double max = 0.0;
        for(Objeto o : re)
            if(Double.parseDouble(o.getParam2()) > max)
                max = Double.parseDouble(o.getParam2());
        NumberAxis axis = (NumberAxis) chartGlobal.getYAxis();
        axis.setUpperBound(max);
        
        for (int i = 0; i < re.size(); i++)
        {
            switch (i)
            {
                case 0:
                    global.getData().add(new XYChart.Data("Janeiro",Double.parseDouble(re.get(i).getParam2())));
                    break;
                case 5:
                    global.getData().add(new XYChart.Data("Junho",Double.parseDouble(re.get(i).getParam2())));
                    break;
                case 11:
                    global.getData().add(new XYChart.Data("Dezembro",Double.parseDouble(re.get(i).getParam2())));
                    break;
                default:
                    global.getData().add(new XYChart.Data("" + (i + 1),Double.parseDouble(re.get(i).getParam2())));
                    break;
            }
        }
        /*for (int i = 1; i <= mes_atual; i++)
        {
            if(i == 1)
                global.getData().add(new XYChart.Data("Janeiro",Double.parseDouble(re.get(i - 1).getParam2())));
            else if(i == 6)
                global.getData().add(new XYChart.Data("Junho",Double.parseDouble(re.get(i - 1).getParam2())));
            else if(i == 12)
                global.getData().add(new XYChart.Data("Dezembro",Double.parseDouble(re.get(i - 1).getParam2())));
            else
                global.getData().add(new XYChart.Data("" + i,Double.parseDouble(re.get(i - 1).getParam2())));
        }*/
        chartGlobal.getData().addAll(global);
        
        for (XYChart.Series<String, Number> s : chartGlobal.getData())
        {
            for (XYChart.Data<String, Number> d : s.getData())
            {
                //Adding class on hover
                d.getNode().setOnMouseEntered(event -> d.getNode().getStyleClass().add("onHover"));
                d.getNode().setOnMouseEntered((event) ->
                {
                    tooltip.setText("" + d.getYValue());
                    ToolTip.bindTooltip(d.getNode(), tooltip);
                });
                //Removing class on exit
                d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
            }
        }
        
        
        
        
        /*global.getData().add(new XYChart.Data("2",14));
        global.getData().add(new XYChart.Data("3",25));
        global.getData().add(new XYChart.Data("4",33));
        global.getData().add(new XYChart.Data("5",40));
        global.getData().add(new XYChart.Data("Junho",32));
        
        XYChart.Series global2 = new XYChart.Series();
        global2.getData().add(new XYChart.Data("Janeiro",30));
        global2.getData().add(new XYChart.Data("2",19));
        global2.getData().add(new XYChart.Data("3",15));
        global2.getData().add(new XYChart.Data("4",22));
        global2.getData().add(new XYChart.Data("5",20));
        global2.getData().add(new XYChart.Data("Junho",23));
        chartGlobal.setStyle("-fx-tick-label-fill white;");*/
        
        
    }    
    
}
