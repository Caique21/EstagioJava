/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;

/**
 *
 * @author Carlos
 */
public class ItensCompra
{
    private Veiculo veiculo;
    private Compra compra;
    private Double valor;

    public ItensCompra(Veiculo veiculo, Compra compra, Double valor)
    {
        this.veiculo = veiculo;
        this.compra = compra;
        this.valor = valor;
    }

    public ItensCompra()
    {
    }

    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }

    public Compra getCompra()
    {
        return compra;
    }

    public void setCompra(Compra compra)
    {
        this.compra = compra;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    public boolean salvar()
    {
        String sql = "INSERT INTO veiculo_compra(comp_codigo,vei_codigo,vei_comp_valor) VALUES ($1,$2,$3)";
        
        sql = sql.replace("$1", String.valueOf(compra.getCodigo()));
        sql = sql.replace("$2", String.valueOf(veiculo.getCodigo()));
        sql = sql.replace("$3", String.valueOf(valor));
        
        return Banco.getCon().manipular(sql);
    }
    
    
}
