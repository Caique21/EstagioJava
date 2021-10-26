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
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public ItensCompra(Compra compra)
    {
        this.compra = compra;
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
        try
        {
            Connection connection = Banco.getCon().getConnection();
            connection.setAutoCommit(false);
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO veiculo(vei_placa,modelo_codigo,"
                + "vei_chassi,vei_ano,vei_cor,vei_descricao) VALUES(?,?,?,?,?,?)");
            
            if(this.getVeiculo().getPlaca() == null || this.getVeiculo().getPlaca().trim().equals(""))
                statement.setNull(1, Types.VARCHAR);
            else
                statement.setString(1, this.getVeiculo().getPlaca());
            statement.setInt(2, this.getVeiculo().getModelo().getCodigo());
            statement.setString(3, this.getVeiculo().getChassi());
            statement.setInt(4, this.getVeiculo().getAno());
            statement.setString(5, this.getVeiculo().getCor());
            statement.setString(6, this.getVeiculo().getDescricao());
            boolean flag = statement.executeUpdate() == 1;
            
            if(flag)
            {
                statement = connection.prepareStatement("INSERT INTO veiculo_compra(comp_codigo,vei_codigo,"
                    + "vei_comp_valor) VALUES(?,CURRVAL('veiculo_vei_codigo_seq'),?)");
                statement.setInt(1, this.compra.getCodigo());
                statement.setDouble(2, this.valor);
                flag = statement.executeUpdate() == 1;
            }
            
            if(flag)
            {
                connection.setAutoCommit(flag);
                connection.commit();
            }
            else
                connection.rollback();
            return flag;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItensCompra.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public void apagarVeiculos()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT vei_codigo FROM veiculo_compra WHERE comp_codigo = " + 
                this.compra.getCodigo());
        
        try
        {
            while(rs != null && rs.next())            
                Banco.getCon().manipular("DELETE FROM veiculo WHERE vei_codigo = " + rs.getInt("vei_codigo"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ItensCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
