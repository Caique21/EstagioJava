/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Carlos
 */
public class JasperReportPrinter
{

    private static final String url = "jdbc:mysql://localhost/estagio";
    private static final String driver = "com.postgresql.jdbc.Driver";
    private static final String login = "";
    private static final String pwd = "";

    public JasperReportPrinter()
    {
    }

    public void gerar(String layout) throws JRException, SQLException, ClassNotFoundException
    {
        //gerando o jasper design
        JasperDesign desenho = JRXmlLoader.load("C:\\Users\\carlo\\OneDrive\\Documentos\\NetBeansProjects\\EstagioJava\\Estagio\\src\\estagio\\interfaces\\relatorios\\ContasPagarReport.jrxml");

        JRDesignQuery update = new JRDesignQuery();
        update.setText("select * from parcela inner join venda on parcela.ven_codigo = venda.ven_codigo inner join "
            + "fornecedor on venda.forn_codigo = fornecedor.forn_codigo where parc_datavencimento < current_date "
                + "and parc_datapagamento is null");
        desenho.setQuery(update);
        //compila o relatório
        JasperReport relatorio = JasperCompileManager.compileReport(desenho);
        JasperPrint print = JasperFillManager.fillReport(relatorio, null,Banco.getCon().getConnection());
        JasperViewer.viewReport(print);
    }

}
