/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Venda
{
    private int codigo;
    private Cliente cliente;
    private Fornecedor fornecedor;
    private int qtd_parcelas;
    private double valor_total;
    private double ajuste;
    private Date data;
    private String numero_nota_fiscal;
    private Date data_emissao;

    public Venda()
    {
    }

    public Venda(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Venda(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Venda(Fornecedor fornecedor)
    {
        this.fornecedor = fornecedor;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Fornecedor getFornecedor()
    {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor)
    {
        this.fornecedor = fornecedor;
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
    
    public boolean salvar()
    {
        String sql = "INSERT INTO venda($8, ven_qtd_parcelas, ven_valor_total, ven_ajuste, ven_data_compra,"
            + "ven_nota_fiscal, ven_data_emissao) VALUES($1,$2,$3,$4,'$5','$6','$7')";
        
        if(this.fornecedor != null)
        {
            sql = sql.replace("$8", "forn_codigo");
            sql = sql.replace("$1", String.valueOf(this.fornecedor.getCodigo()));
        }
        else if(this.cliente != null)
        {
            sql = sql.replace("$8", "cli_codigo");
            sql = sql.replace("$1", String.valueOf(this.cliente.getCodigo()));
        }
        
        sql = sql.replace("$2", String.valueOf(this.qtd_parcelas));
        sql = sql.replace("$3", String.valueOf(this.valor_total));
        sql = sql.replace("$4", String.valueOf(this.ajuste));
        sql = sql.replace("$5", String.valueOf(this.data));
        sql = sql.replace("$6", this.numero_nota_fiscal);
        sql = sql.replace("$7", String.valueOf(this.data_emissao));
        
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar()
    {
        String sql = "UPDATE venda SET $8 = $1, ven_qtd_parcelas = $2, ven_valor_total = $3, ven_ajuste = $4, "
            + "ven_data_compra = '$5', ven_nota_fiscal = '$6', ven_data_emissao = '$7' "
                + "WHERE ven_codigo = " + this.codigo;
        
        if(this.fornecedor != null)
        {
            sql = sql.replace("$8", "forn_codigo");
            sql = sql.replace("$1", String.valueOf(this.fornecedor.getCodigo()));
        }
        else if(this.cliente != null)
        {
            sql = sql.replace("$8", "cli_codigo");
            sql = sql.replace("$1", String.valueOf(this.cliente.getCodigo()));
        }
        
        sql = sql.replace("$2", String.valueOf(this.qtd_parcelas));
        sql = sql.replace("$3", String.valueOf(this.valor_total));
        sql = sql.replace("$4", String.valueOf(this.ajuste));
        sql = sql.replace("$5", String.valueOf(this.data));
        sql = sql.replace("$6", this.numero_nota_fiscal);
        sql = sql.replace("$7", String.valueOf(this.data_emissao));
        
        return Banco.getCon().manipular(sql);
    }

    public boolean apagar()
    {
        return Banco.getCon().manipular("DELETE FROM venda WHERE ven_codigo = " + this.codigo);
    }

    private void get()
    {
        String sql = "SELECT * FROM venda WHERE ";
        
        if(this.codigo > 0)
            sql += "ven_codigo = " + this.codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.ajuste = rs.getDouble("ven_ajuste");
                this.data = rs.getDate("ven_data_compra");
                this.data_emissao = rs.getDate("ven_data_emissao");
                this.numero_nota_fiscal = rs.getString("ven_nota_fiscal");
                this.qtd_parcelas = rs.getInt("ven_qtd_parcelas");
                this.valor_total = rs.getDouble("ven_valor_total");
                
                if(rs.getInt("cli_codigo") > 0)
                    this.cliente = new Cliente(rs.getInt("cli_codigo"));
                else
                    this.fornecedor = new Fornecedor(rs.getInt("forn_codigo"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
    }
    
    private ArrayList<Venda> ler(ResultSet rs)
    {
        ArrayList<Venda>vendas = new ArrayList<>();
        try
        {
            while(rs != null && rs.next())            
            {
                Venda venda = new Venda();
                venda.setAjuste(rs.getDouble("ven_ajuste"));
                venda.setCodigo(rs.getInt("ven_codigo"));
                venda.setData(rs.getDate("ven_data_compra"));
                venda.setData_emissao(rs.getDate("ven_data_emissao"));
                venda.setNumero_nota_fiscal(rs.getString("ven_nota_fiscal"));
                venda.setQtd_parcelas(rs.getInt("ven_qtd_parcelas"));
                venda.setValor_total(rs.getDouble("ven_valor_total"));
                
                if(rs.getInt("cli_codigo") > 0)
                    venda.setCliente(new Cliente(rs.getInt("cli_codigo")));
                else
                    venda.setFornecedor(new Fornecedor(rs.getInt("forn_codigo")));
                
                vendas.add(venda);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return vendas;
    }

    public ArrayList<Venda> getAll()
    {
        return ler(Banco.getCon().consultar("SELECT * FROM venda"));
    }

    public ArrayList<Venda> getByNome(String nome)
    {
        ArrayList<Venda> ret = ler(Banco.getCon().consultar("SELECT * FROM venda WHERE cli_codigo IN "
                + "(SELECT cli_codigo FROM cliente WHERE cli_nome Ilike '%" + nome  + "%')")); 
        
        ret.addAll(ler(Banco.getCon().consultar("SELECT * FROM venda WHERE forn_codigo IN "
                + "(SELECT forn_codigo FROM fornecedor WHERE forn_nome Ilike '%" + nome  + "%')")));
        return ret;
    }

    public ArrayList<Venda> getByNotaFiscal(String nota_fiscal)
    {
        return ler(Banco.getCon().consultar("SELECT * FROM venda WHERE ven_nota_fiscal Ilike '%" + nota_fiscal + "%'"));
    }

    public ArrayList<Venda> getByVeiculo(String veiculo)
    {
        String sql = "SELECT DISTINCT(venda.ven_codigo) FROM venda INNER JOIN veiculo_venda ON venda.ven_codigo = veiculo_venda.ven_codigo \n"
                + "	AND veiculo_venda.vei_codigo IN (SELECT vei_codigo FROM veiculo WHERE vei_placa Ilike '%" + veiculo + "%') \n"
                + "UNION\n"
                + "SELECT DISTINCT(venda.ven_codigo) FROM venda INNER JOIN veiculo_venda ON venda.ven_codigo = veiculo_venda.ven_codigo \n"
                + "	AND veiculo_venda.vei_codigo IN (SELECT vei_codigo FROM veiculo INNER JOIN modelo \n"
                + "				ON veiculo.modelo_codigo = modelo.modelo_codigo AND modelo_nome ILike '%" + veiculo + "%')\n"
                + "UNION\n"
                + "SELECT DISTINCT(venda.ven_codigo) FROM venda INNER JOIN veiculo_venda ON venda.ven_codigo = veiculo_venda.ven_codigo \n"
                + "	AND veiculo_venda.vei_codigo IN (SELECT vei_codigo FROM veiculo WHERE modelo_codigo IN \n"
                + "			(SELECT modelo_codigo FROM marca INNER JOIN modelo ON modelo.marca_codigo = marca.marca_codigo \n"
                + "			 	AND marca_nome Ilike '%" + veiculo + "%'))";
        ArrayList<Venda> ret = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            while(rs != null && rs.next())            
                ret.add(new Venda(rs.getInt("ven_codigo")));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ret;
    }
}
