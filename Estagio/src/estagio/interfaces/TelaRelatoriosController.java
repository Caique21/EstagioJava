/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.interfaces;

import com.jfoenix.controls.JFXDecorator;
import estagio.utilidades.Banco;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class TelaRelatoriosController implements Initializable
{

    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Label lbTitulo;
    @FXML
    private GridPane gridPane;
    @FXML
    private Pane folderContasPagar;
    @FXML
    private Pane paneContasPagar;
    @FXML
    private Pane folderInadimplentes;
    @FXML
    private Pane paneInadimplementes;
    @FXML
    private ImageView imgClientes1;
    @FXML
    private Pane folderPagamento;
    @FXML
    private Pane panePagamento;
    @FXML
    private Pane folderRecebimento;
    @FXML
    private Pane paneRecebimento;
    @FXML
    private Pane folderBalanco;
    @FXML
    private Pane paneBalanco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void aPagarExit(MouseEvent event)
    {
    }

    @FXML
    private void aPagarEnter(MouseEvent event)
    {
    }

    @FXML
    private void clickApagar(MouseEvent event)
    {
        JasperDesign desenho;
        try
        {
            desenho = JRXmlLoader.load("C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\EstagioJava\\Estagio\\src\\estagio\\interfaces\\relatorios\\ContasPagar.jrxml");
            
            JRDesignQuery update = new JRDesignQuery();
            /*update.setText("select comp_nota_fiscal as nome, cast(parc_numero as varchar), parc_datavencimento, "
                + "to_char(parc_valorparcela, 'L9G999G990D99') from parcela left join pagamento on parcela.parc_codigo = pagamento.parc_codigo "
                    + "inner join compra on parcela.comp_codigo = parcela.comp_codigo where parcela.comp_codigo > 0 "
                    + "and pag_codigo is null and parc_datavencimento >= '" + LocalDate.now().toString() + "' and "
                    + "parc_datavencimento <= '" + LocalDate.now().toString() + "' "
                + "union \n" +
                "select desp_nome as nome,'Despesa',desp_data_vencimento,to_char(desp_preco, 'L9G999G990D99') from despesa left join "
                    + "pagamento on despesa.desp_codigo = pagamento.desp_codigo where pag_codigo is null "
                    + " and desp_data_vencimento >= '" + LocalDate.now().toString() + "' and "
                     + "desp_data_vencimento <= '" + LocalDate.now().toString() + "'");
            
            update.setText("select comp_nota_fiscal as nome, cast(parc_numero as varchar), parc_datavencimento, to_char(parc_valorparcela, 'L9G999G990D99') from parcela left join pagamento on parcela.parc_codigo = pagamento.parc_codigo \n" +
"	inner join compra on parcela.comp_codigo = parcela.comp_codigo \n" +
"		where parcela.comp_codigo > 0 and pag_codigo is null and parc_datavencimento >= '2021-11-08' and \n" +
"		parc_datavencimento <= '2021-11-13'\n" +
"union \n" +
"select desp_nome as nome,'Despesa',desp_data_vencimento,to_char(desp_preco, 'L9G999G990D99')  from despesa left join pagamento on despesa.desp_codigo = pagamento.desp_codigo \n" +
"where pag_codigo is null and desp_data_vencimento >= '2021-11-16' and desp_data_vencimento <= '2021-11-16'");
            desenho.setQuery(update);*/
            //compila o relatório
            JasperReport relatorio = JasperCompileManager.compileReport(desenho);
            JasperPrint print = JasperFillManager.fillReport(relatorio, null,Banco.getCon().getConnection());
            JasperViewer.viewReport(print,false);
        }
        catch (JRException ex)
        {
            Logger.getLogger(TelaRelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void inadimpleteExit(MouseEvent event)
    {
    }

    @FXML
    private void inadimpleteEnter(MouseEvent event)
    {
    }

    @FXML
    private void clickIndadimplentes(MouseEvent event)
    {
	JasperDesign desenho;
        try
        {
            desenho = JRXmlLoader.load("C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\EstagioJava\\Estagio\\src\\estagio\\interfaces\\relatorios\\Inadimplentes.jrxml");
            
            JRDesignQuery update = new JRDesignQuery();
            update.setText("select ven_nota_fiscal, ven_data_compra,parc_numero,parc_valorparcela, forn_nome as nome, forn_cnpj as documento, parc_datavencimento from parcela inner join venda on parcela.ven_codigo = venda.ven_codigo inner join fornecedor on venda.forn_codigo = fornecedor.forn_codigo where parc_datavencimento < current_date and parc_datapagamento is null");
            desenho.setQuery(update);
            //compila o relatório
            JasperReport relatorio = JasperCompileManager.compileReport(desenho);
            JasperPrint print = JasperFillManager.fillReport(relatorio, null,Banco.getCon().getConnection());
            JasperViewer.viewReport(print,false);
        }
        catch (JRException ex)
        {
            Logger.getLogger(TelaRelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pagamentoExit(MouseEvent event)
    {
    }

    @FXML
    private void pagamentoEnter(MouseEvent event)
    {
    }

    @FXML
    private void clickPagamento(MouseEvent event)
    {
    }

    @FXML
    private void recebimenoExit(MouseEvent event)
    {
    }

    @FXML
    private void recebimentoEnter(MouseEvent event)
    {
    }

    @FXML
    private void clickRecebimento(MouseEvent event)
    {
    }

    @FXML
    private void balanceExit(MouseEvent event)
    {
    }

    @FXML
    private void balancoEnter(MouseEvent event)
    {
    }

    @FXML
    private void clickBalanco(MouseEvent event)
    {
    }
    
}
