/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Compra
{
    private int codigo;
    private Fornecedor fornecedor;
    private Cliente cliente;
    private int qtd_parcelas;
    private double valor_total;
    private double ajuste;
    private Date data;
    private String numero_nota_fiscal;
    private Date data_emissao;
    private String vendedor;

    public Compra()
    {
    }

    public Compra(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Compra(Fornecedor fornecedor)
    {
        this.fornecedor = fornecedor;
    }

    public Compra(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Compra(int codigo, Fornecedor fornecedor, int qtd_parcelas, double valor_total, double ajuste, Date data, String numero_nota_fiscal, Date data_emissao, String vendedor)
    {
        this.codigo = codigo;
        this.fornecedor = fornecedor;
        this.qtd_parcelas = qtd_parcelas;
        this.valor_total = valor_total;
        this.ajuste = ajuste;
        this.data = data;
        this.numero_nota_fiscal = numero_nota_fiscal;
        this.data_emissao = data_emissao;
        this.vendedor = vendedor;
    }

    public Compra(Fornecedor fornecedor, int qtd_parcelas, double valor_total, double ajuste, Date data, String numero_nota_fiscal, Date data_emissao, String vendedor)
    {
        this.fornecedor = fornecedor;
        this.qtd_parcelas = qtd_parcelas;
        this.valor_total = valor_total;
        this.ajuste = ajuste;
        this.data = data;
        this.numero_nota_fiscal = numero_nota_fiscal;
        this.data_emissao = data_emissao;
        this.vendedor = vendedor;
    }

    public Compra(int qtd_parcelas, double valor_total, double ajuste, Date data, String numero_nota_fiscal, Date data_emissao, String vendedor)
    {
        this.qtd_parcelas = qtd_parcelas;
        this.valor_total = valor_total;
        this.ajuste = ajuste;
        this.data = data;
        this.numero_nota_fiscal = numero_nota_fiscal;
        this.data_emissao = data_emissao;
        this.vendedor = vendedor;
    }

    public Compra(int codigo, Cliente cliente, int qtd_parcelas, double valor_total, double ajuste, Date data, String numero_nota_fiscal, Date data_emissao, String vendedor)
    {
        this.codigo = codigo;
        this.cliente = cliente;
        this.qtd_parcelas = qtd_parcelas;
        this.valor_total = valor_total;
        this.ajuste = ajuste;
        this.data = data;
        this.numero_nota_fiscal = numero_nota_fiscal;
        this.data_emissao = data_emissao;
        this.vendedor = vendedor;
    }

    public Compra(Cliente cliente, int qtd_parcelas, double valor_total, double ajuste, Date data, String numero_nota_fiscal, Date data_emissao, String vendedor)
    {
        this.cliente = cliente;
        this.qtd_parcelas = qtd_parcelas;
        this.valor_total = valor_total;
        this.ajuste = ajuste;
        this.data = data;
        this.numero_nota_fiscal = numero_nota_fiscal;
        this.data_emissao = data_emissao;
        this.vendedor = vendedor;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public Fornecedor getFornecedor()
    {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor)
    {
        this.fornecedor = fornecedor;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public int getQtd_parcelas()
    {
        return qtd_parcelas;
    }

    public void setQtd_parcelas(int qtd_parcelas)
    {
        this.qtd_parcelas = qtd_parcelas;
    }

    public double getValor_total()
    {
        return valor_total;
    }

    public void setValor_total(double valor_total)
    {
        this.valor_total = valor_total;
    }

    public double getAjuste()
    {
        return ajuste;
    }

    public void setAjuste(double ajuste)
    {
        this.ajuste = ajuste;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public String getNumero_nota_fiscal()
    {
        return numero_nota_fiscal;
    }

    public void setNumero_nota_fiscal(String numero_nota_fiscal)
    {
        this.numero_nota_fiscal = numero_nota_fiscal;
    }

    public Date getData_emissao()
    {
        return data_emissao;
    }

    public void setData_emissao(Date data_emissao)
    {
        this.data_emissao = data_emissao;
    }

    public String getVendedor()
    {
        return vendedor;
    }

    public void setVendedor(String vendedor)
    {
        this.vendedor = vendedor;
    }

    public boolean salvar()
    {
        String sql = "INSERT INTO compra($9,comp_qtd_parcelas,comp_valor_total,comp_ajuste,comp_data_compra,"
            + "comp_nota_fiscal,comp_data_emissao,comp_vendedor) VALUES($1,$2,$3,$4,'$5','$6','$7','$8')";
        
        if(this.fornecedor != null)
        {
            sql = sql.replace("$9", "forn_codigo");
            sql = sql.replace("$1", String.valueOf(this.fornecedor.getCodigo()));
        }
        else if(this.cliente != null)
        {
            sql = sql.replace("$9", "cli_codigo");
            sql = sql.replace("$1", String.valueOf(this.cliente.getCodigo()));
        }
        
        sql = sql.replace("$2", String.valueOf(this.qtd_parcelas));
        sql = sql.replace("$3", String.valueOf(this.valor_total));
        sql = sql.replace("$4", String.valueOf(this.ajuste));
        sql = sql.replace("$5", String.valueOf(this.data));
        sql = sql.replace("$6", this.numero_nota_fiscal);
        sql = sql.replace("$7", String.valueOf(this.data_emissao));
        sql = sql.replace("$8", this.vendedor);
        
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar()
    {
        String sql = "UPDATE compra SET $9 = $1, comp_qtd_parcelas = $2, comp_valor_total = $3, comp_ajuste = $4, "
            + "comp_data_compra = '$5', comp_nota_fiscal = '$6', comp_data_emissao = '$7', comp_vendedor = '$8' "
                + "WHERE comp_codigo = " + this.codigo;
        
        if(this.fornecedor != null)
        {
            sql = sql.replace("$9", "forn_codigo");
            sql = sql.replace("$1", String.valueOf(this.fornecedor.getCodigo()));
        }
        else if(this.cliente != null)
        {
            sql = sql.replace("$9", "cli_codigo");
            sql = sql.replace("$1", String.valueOf(this.cliente.getCodigo()));
        }
        
        sql = sql.replace("$2", String.valueOf(this.qtd_parcelas));
        sql = sql.replace("$3", String.valueOf(this.valor_total));
        sql = sql.replace("$4", String.valueOf(this.ajuste));
        sql = sql.replace("$5", String.valueOf(this.data));
        sql = sql.replace("$6", this.numero_nota_fiscal);
        sql = sql.replace("$7", String.valueOf(this.data_emissao));
        sql = sql.replace("$8", this.vendedor);
        
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar(Connection connection)
    {
        String sql = "UPDATE compra SET $9 = $1, comp_qtd_parcelas = $2, comp_valor_total = $3, comp_ajuste = $4, "
            + "comp_data_compra = '$5', comp_nota_fiscal = '$6', comp_data_emissao = '$7', comp_vendedor = '$8' "
                + "WHERE comp_codigo = " + this.codigo;
        
        if(this.fornecedor != null)
        {
            sql = sql.replace("$9", "forn_codigo");
            sql = sql.replace("$1", String.valueOf(this.fornecedor.getCodigo()));
        }
        else if(this.cliente != null)
        {
            sql = sql.replace("$9", "cli_codigo");
            sql = sql.replace("$1", String.valueOf(this.cliente.getCodigo()));
        }
        
        sql = sql.replace("$2", String.valueOf(this.qtd_parcelas));
        sql = sql.replace("$3", String.valueOf(this.valor_total));
        sql = sql.replace("$4", String.valueOf(this.ajuste));
        sql = sql.replace("$5", String.valueOf(this.data));
        sql = sql.replace("$6", this.numero_nota_fiscal);
        sql = sql.replace("$7", String.valueOf(this.data_emissao));
        sql = sql.replace("$8", this.vendedor);
        
        PreparedStatement statement;
        try
        {
            statement = connection.prepareStatement(sql);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean apagar()
    {
        return Banco.getCon().manipular("DELETE FROM compra WHERE comp_codigo = " + this.codigo);
    }

    private void get()
    {
        String sql = "SELECT * FROM compra WHERE ";
        
        if(this.codigo > 0)
            sql += "comp_codigo = " + this.codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.ajuste = rs.getDouble("comp_ajuste");
                this.data = rs.getDate("comp_data_compra");
                this.data_emissao = rs.getDate("comp_data_emissao");
                this.numero_nota_fiscal = rs.getString("comp_nota_fiscal");
                this.qtd_parcelas = rs.getInt("comp_qtd_parcelas");
                this.valor_total = rs.getDouble("comp_valor_total");
                this.vendedor = rs.getString("comp_vendedor");
                
                if(rs.getInt("cli_codigo") > 0)
                    this.cliente = new Cliente(rs.getInt("cli_codigo"));
                else
                    this.fornecedor = new Fornecedor(rs.getInt("forn_codigo"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
    }
    
    private ArrayList<Compra> ler(ResultSet rs)
    {
        ArrayList<Compra>compras = new ArrayList<>();
        try
        {
            while(rs != null && rs.next())            
            {
                Compra compra = new Compra();
                compra.setAjuste(rs.getDouble("comp_ajuste"));
                compra.setCodigo(rs.getInt("comp_codigo"));
                compra.setData(rs.getDate("comp_data_compra"));
                compra.setData_emissao(rs.getDate("comp_data_emissao"));
                compra.setNumero_nota_fiscal(rs.getString("comp_nota_fiscal"));
                compra.setQtd_parcelas(rs.getInt("comp_qtd_parcelas"));
                compra.setValor_total(rs.getDouble("comp_valor_total"));
                compra.setVendedor(rs.getString("comp_vendedor"));
                
                if(rs.getInt("cli_codigo") > 0)
                    compra.setCliente(new Cliente(rs.getInt("cli_codigo")));
                else
                    compra.setFornecedor(new Fornecedor(rs.getInt("forn_codigo")));
                
                compras.add(compra);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return compras;
    }

    public ArrayList<Compra> getAll()
    {
        return ler(Banco.getCon().consultar("SELECT * FROM compra"));
    }

    public ArrayList<Compra> getByNome(String nome)
    {
        ArrayList<Compra> ret = ler(Banco.getCon().consultar("SELECT * FROM compra WHERE cli_codigo IN "
                + "(SELECT cli_codigo FROM cliente WHERE cli_nome Ilike '%" + nome  + "%')")); 
        
        ret.addAll(ler(Banco.getCon().consultar("SELECT * FROM compra WHERE forn_codigo IN "
                + "(SELECT forn_codigo FROM fornecedor WHERE forn_nome Ilike '%" + nome  + "%')")));
        return ret;
    }

    public ArrayList<Compra> getByNotaFiscal(String nota_fiscal)
    {
        return ler(Banco.getCon().consultar("SELECT * FROM compra WHERE comp_nota_fiscal Ilike '%" + nota_fiscal + "%'"));
    }

    public ArrayList<Compra> getByVeiculo(String veiculo)
    {
        String sql = "SELECT DISTINCT(compra.comp_codigo) FROM compra INNER JOIN veiculo_compra ON compra.comp_codigo = veiculo_compra.comp_codigo \n"
                + "	AND veiculo_compra.vei_codigo IN (SELECT vei_codigo FROM veiculo WHERE vei_placa Ilike '%" + veiculo + "%') \n"
                + "UNION\n"
                + "SELECT DISTINCT(compra.comp_codigo) FROM compra INNER JOIN veiculo_compra ON compra.comp_codigo = veiculo_compra.comp_codigo \n"
                + "	AND veiculo_compra.vei_codigo IN (SELECT vei_codigo FROM veiculo INNER JOIN modelo \n"
                + "				ON veiculo.modelo_codigo = modelo.modelo_codigo AND modelo_nome ILike '%" + veiculo + "%')\n"
                + "UNION\n"
                + "SELECT DISTINCT(compra.comp_codigo) FROM compra INNER JOIN veiculo_compra ON compra.comp_codigo = veiculo_compra.comp_codigo \n"
                + "	AND veiculo_compra.vei_codigo IN (SELECT vei_codigo FROM veiculo WHERE modelo_codigo IN \n"
                + "			(SELECT modelo_codigo FROM marca INNER JOIN modelo ON modelo.marca_codigo = marca.marca_codigo \n"
                + "			 	AND marca_nome Ilike '%" + veiculo + "%'))";
        ArrayList<Compra> ret = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            while(rs != null && rs.next())            
                ret.add(new Compra(rs.getInt("comp_codigo")));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ret;
        /*return ler(Banco.getCon().consultar("SELECT * FROM compra INNER JOIN veiculo_compra "
                + "ON compra.comp_codigo = veiculo_compra.comp_codigo AND veiculo_compra.vei_codigo "
                + "IN (SELECT vei_codigo FROM veiculo WHERE vei_placa Ilike '%" + veiculo + "%') "
            + "UNION "
            + "SELECT * FROM compra INNER JOIN veiculo_compra ON compra.comp_codigo = veiculo_compra.comp_codigo "
                + "AND veiculo_compra.vei_codigo IN (SELECT vei_codigo FROM veiculo INNER JOIN modelo "
                + "ON veiculo.modelo_codigo = modelo.modelo_codigo AND modelo_nome ILike '%" + veiculo + "%') "
            + "UNION "
            + "SELECT * FROM compra INNER JOIN veiculo_compra ON compra.comp_codigo = veiculo_compra.comp_codigo "
                + "AND veiculo_compra.vei_codigo IN (SELECT vei_codigo FROM veiculo WHERE modelo_codigo IN "
                    + "(SELECT modelo_codigo FROM marca INNER JOIN modelo ON modelo.marca_codigo = marca.marca_codigo "
                + "AND marca_nome Ilike '%" + veiculo + "%'))"));*/
    }

    public ArrayList<Compra> getByVendedor(String vendedor)
    {
        return ler(Banco.getCon().consultar("SELECT * FROM compra WHERE comp_vendedor Ilike '%" + vendedor + "%'"));
    }
}
