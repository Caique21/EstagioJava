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
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class ItensVenda
{
    private Veiculo veiculo;
    private Venda venda;
    private Double valor;

    public ItensVenda()
    {
    }

    public ItensVenda(Venda venda)
    {
        this.venda = venda;
    }

    public ItensVenda(Veiculo veiculo, Venda venda, Double valor)
    {
        this.veiculo = veiculo;
        this.venda = venda;
        this.valor = valor;
    }

    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }

    public Venda getVenda()
    {
        return venda;
    }

    public void setVenda(Venda venda)
    {
        this.venda = venda;
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
        String sql = "INSERT INTO veiculo_venda(ven_codigo,vei_codigo,"
                    + "vei_ven_valor) VALUES($1,$2,$3)";
        sql = sql.replace("$1", String.valueOf(this.venda.getCodigo()));
        sql = sql.replace("$2", String.valueOf(this.veiculo.getCodigo()));
        sql = sql.replace("$3", String.valueOf(this.valor));

        return Banco.getCon().manipular(sql);
    }

    public boolean apagar()
    {
        String sql = "DELETE FROM veiculo_venda WHERE ";
        
        if(this.venda != null)
            sql += "comp_codigo = " + this.venda.getCodigo();
        
        return Banco.getCon().manipular(sql);
    }

    public ArrayList<ItensVenda> getByVenda(Venda venda)
    {
        ArrayList<ItensVenda>veiculos = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM veiculo_venda WHERE ven_codigo = " + venda.getCodigo());
        
        try
        {
            while(rs != null && rs.next())            
            {
                veiculos.add(new ItensVenda(new Veiculo(rs.getInt("vei_codigo")), venda, 
                        rs.getDouble("vei_ven_valor")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItensCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veiculos;
    }
}
