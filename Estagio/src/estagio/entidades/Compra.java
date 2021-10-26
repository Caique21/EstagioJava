/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.util.Date;

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

    public boolean apagar()
    {
        return Banco.getCon().manipular("DELETE FROM compra WHERE comp_codigo = " + this.codigo);
    }
    
    
}
