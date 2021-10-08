/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Pagamento
{
    private int codigo;
    private Date data;
    private double valor;
    private Parcela parcela;
    private Despesa despesa;
    private String forma_pagamento;
    private String forma_pagamento_desc;
    private boolean ativo;

    public Pagamento()
    {
    }

    public Pagamento(int codigo)
    {
        this.codigo = codigo;
    }

    public Pagamento(Despesa despesa)
    {
        this.despesa = despesa;
        get();
    }

    public Pagamento(int codigo, Date data, double valor, Despesa despesa, String forma_pagamento, boolean ativo)
    {
        this.codigo = codigo;
        this.data = data;
        this.valor = valor;
        this.despesa = despesa;
        this.forma_pagamento = forma_pagamento;
        this.ativo = ativo;
    }

    public Pagamento(Date data, double valor, Despesa despesa, String forma_pagamento)
    {
        this.data = data;
        this.valor = valor;
        this.despesa = despesa;
        this.forma_pagamento = forma_pagamento;
    }
    
    public Pagamento(Date data, double valor, Despesa despesa, String forma_pagamento,String forma_pagamento_desc)
    {
        this.data = data;
        this.valor = valor;
        this.despesa = despesa;
        this.forma_pagamento = forma_pagamento;
        this.forma_pagamento_desc = forma_pagamento_desc;
    }

    public Pagamento(int codigo, Date data, double valor, Parcela parcela, String forma_pagamento)
    {
        this.codigo = codigo;
        this.data = data;
        this.valor = valor;
        this.parcela = parcela;
        this.forma_pagamento = forma_pagamento;
    }
    
    public Pagamento(int codigo, Date data, double valor, Parcela parcela, String forma_pagamento,String forma_pagamento_desc)
    {
        this.codigo = codigo;
        this.data = data;
        this.valor = valor;
        this.parcela = parcela;
        this.forma_pagamento = forma_pagamento;
    }

    public Pagamento(Date data, double valor, Parcela parcela, String forma_pagamento)
    {
        this.data = data;
        this.valor = valor;
        this.parcela = parcela;
        this.forma_pagamento = forma_pagamento;
        this.forma_pagamento_desc = forma_pagamento_desc;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public Parcela getParcela()
    {
        return parcela;
    }

    public void setParcela(Parcela parcela)
    {
        this.parcela = parcela;
    }

    public Despesa getDespesa()
    {
        return despesa;
    }

    public void setDespesa(Despesa despesa)
    {
        this.despesa = despesa;
    }

    public String getForma_pagamento()
    {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento)
    {
        this.forma_pagamento = forma_pagamento;
    }

    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }

    public String getForma_pagamento_desc()
    {
        return forma_pagamento_desc;
    }

    public void setForma_pagamento_desc(String forma_pagamento_desc)
    {
        this.forma_pagamento_desc = forma_pagamento_desc;
    }

    public boolean pagarDespesa()
    {
        String sql = "INSERT INTO pagamento(pag_data,pag_valor,pag_form_pagamento,pag_form_pagamento_desc,pag_ativo,"
                + "$aux) VALUES('$1',$2,'$3','$4','$5',$6)";
        
        if(this.despesa != null)
            sql = sql.replace("$aux", "desp_codigo").replace("$6", String.valueOf(this.despesa.getCodigo()));
        //else
            //sql = sql.replace("$aux", "parc_codigo").replace("$6", String.valueOf(this.parcela.getCodigo()));
            
       return Banco.getCon().manipular(sql);
    }

    private void get()
    {
        String sql = "SELECT * FROM pagamento WHERE ";
        
        if(this.despesa != null)
            sql += "desp_codigo = " + this.despesa.getCodigo();
        //else if(this.parcela != null)
            //sql += "par_codigo = " + this.parcela.getCodigo();
        else
            sql += "pag_codigo = " + this.codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.codigo = rs.getInt("pag_codigo");
                this.data = rs.getDate("pag_data");
                this.valor = rs.getDouble("pag_valor");
                //this.parcela = this.parcela != null ? this.parcela : new Parcela(rs.getInt("parc_codigo"));
                this.forma_pagamento = rs.getString("pag_form_pagamento");
                this.forma_pagamento_desc = rs.getString("pag_form_pagamento_desc");
                this.ativo = rs.getBoolean("pag_ativo");
                if(this.despesa == null)
                    this.despesa = new Despesa(rs.getInt("desp_codigo"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
    }

    public boolean estornar()
    {
        String sql = "UPDATE pagamento SET pag_ativo = 'false' WHERE ";
        
        if(this.despesa != null)
            sql += "desp_codigo = " + this.despesa.getCodigo();
        //else
            //sql += "parc_codigo = " + this.parcela.getCodigo();
       return Banco.getCon().manipular(sql);
    }
    
    
}
