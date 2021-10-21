/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.sql.Date;

/**
 *
 * @author Carlos
 */
public class Parcela
{
    private int codigo;
    private String status;
    private Date vencimento;
    private int numero;
    private Date pagamento;
    private double valor_parcela;
    private double valor_pago;
    private Compra compra;
    private Venda venda;

    public Parcela()
    {
    }

    public Parcela(Date vencimento, int numero, double valor_parcela, Compra compra)
    {
        this.vencimento = vencimento;
        this.numero = numero;
        this.valor_parcela = valor_parcela;
        this.compra = compra;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Date getVencimento()
    {
        return vencimento;
    }

    public void setVencimento(Date vencimento)
    {
        this.vencimento = vencimento;
    }

    public int getNumero()
    {
        return numero;
    }

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public Date getPagamento()
    {
        return pagamento;
    }

    public void setPagamento(Date pagamento)
    {
        this.pagamento = pagamento;
    }

    public double getValor_parcela()
    {
        return valor_parcela;
    }

    public void setValor_parcela(double valor_parcela)
    {
        this.valor_parcela = valor_parcela;
    }

    public double getValor_pago()
    {
        return valor_pago;
    }

    public void setValor_pago(double valor_pago)
    {
        this.valor_pago = valor_pago;
    }

    public Compra getCompra()
    {
        return compra;
    }

    public void setCompra(Compra compra)
    {
        this.compra = compra;
    }

    public Venda getVenda()
    {
        return venda;
    }

    public void setVenda(Venda venda)
    {
        this.venda = venda;
    }

    public boolean salvar()
    {
        String sql = "INSERT INTO parcela(parc_status,parc_datavencimento,parc_numero,parc_datapagamento,"
            + "parc_valorpago,parc_valorparcela,$8) VALUES ('$1','$2',$3,'$4',$5,$6,$7)";
        
        if(this.compra != null)
        {
            sql = sql.replace("$8", "comp_codigo");
            sql = sql.replace("$7", String.valueOf(this.compra.getCodigo()));
        }
        else if(this.venda != null)
        {
            sql = sql.replace("$8", "ven_codigo");
            //sql = sql.replace("$7", String.valueOf(this.venda.getClass()));
        }
        
        sql = sql.replace("$1", this.status);
        sql = sql.replace("$2", String.valueOf(this.vencimento));
        sql = sql.replace("$3", String.valueOf(this.numero));
        sql = sql.replace("$4", String.valueOf(this.pagamento));
        sql = sql.replace("$5", String.valueOf(this.valor_pago));
        sql = sql.replace("$6", String.valueOf(this.valor_parcela));
        
        return Banco.getCon().manipular(sql);
    }
    
    
}
