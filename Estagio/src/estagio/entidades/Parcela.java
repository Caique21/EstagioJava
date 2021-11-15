/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Parcela
{
    private int codigo;
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

    public Parcela(int codigo, Date vencimento, int numero, Date pagamento, double valor_parcela, double valor_pago, Venda venda)
    {
        this.codigo = codigo;
        this.vencimento = vencimento;
        this.numero = numero;
        this.pagamento = pagamento;
        this.valor_parcela = valor_parcela;
        this.valor_pago = valor_pago;
        this.venda = venda;
    }

    public Parcela(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Parcela(int codigo, Date vencimento, int numero, Date pagamento, double valor_parcela, double valor_pago, Compra compra)
    {
        this.codigo = codigo;
        this.vencimento = vencimento;
        this.numero = numero;
        this.pagamento = pagamento;
        this.valor_parcela = valor_parcela;
        this.valor_pago = valor_pago;
        this.compra = compra;
    }

    public Parcela(Compra compra)
    {
        this.compra = compra;
    }

    public Parcela(Date vencimento, int numero, double valor_parcela, Compra compra)
    {
        this.vencimento = vencimento;
        this.numero = numero;
        this.valor_parcela = valor_parcela;
        this.compra = compra;
    }
    
    public Parcela(Date vencimento, int numero, double valor_parcela, Venda venda)
    {
        this.vencimento = vencimento;
        this.numero = numero;
        this.valor_parcela = valor_parcela;
        this.venda = venda;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
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
        String sql = "INSERT INTO parcela(parc_datavencimento,parc_numero,parc_valorparcela,$5) "
                + "VALUES ('$1',$2,$3,$4)";
        
        if(this.compra != null)
        {
            sql = sql.replace("$5", "comp_codigo");
            sql = sql.replace("$4", String.valueOf(this.compra.getCodigo()));
        }
        else if(this.venda != null)
        {
            sql = sql.replace("$5", "ven_codigo");
            sql = sql.replace("$4", String.valueOf(this.venda.getCodigo()));
        }
        
        sql = sql.replace("$1", String.valueOf(this.vencimento));
        sql = sql.replace("$2", String.valueOf(this.numero));
        sql = sql.replace("$3", String.valueOf(this.valor_parcela));
        
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar()
    {
        Connection connection = Banco.getCon().getConnection();
        PreparedStatement statement = null;

        try
        {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement("DELETE FROM parcela WHERE comp_codigo = " + 
                    this.compra.getCodigo());
            statement.executeUpdate();
            
            String sql = "INSERT INTO parcela(parc_datavencimento,parc_numero,parc_valorparcela,$5) "
                + "VALUES ('$1',$2,$3,$4)";
        
            if(this.compra != null)
            {
                sql = sql.replace("$5", "comp_codigo");
                sql = sql.replace("$4", String.valueOf(this.compra.getCodigo()));
            }
            else if(this.venda != null)
            {
                sql = sql.replace("$5", "ven_codigo");
                //sql = sql.replace("$7", String.valueOf(this.venda.getClass()));
            }

            sql = sql.replace("$1", String.valueOf(this.vencimento));
            sql = sql.replace("$2", String.valueOf(this.numero));
            sql = sql.replace("$3", String.valueOf(this.valor_parcela));
            
            statement = connection.prepareStatement(sql);
            
            if(statement.executeUpdate() == 1)
            {
                connection.setAutoCommit(true);
                connection.commit();
                statement.close();
                return true;
            }
            else
                connection.rollback();
            statement.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean apagar()
    {
        String sql = "DELETE FROM parcela WHERE ";
        
        if(this.compra != null)
            sql += "comp_codigo = " + this.compra.getCodigo();
        
        return Banco.getCon().manipular(sql);
    }

    public boolean apagar(int codigo)
    {
        return Banco.getCon().manipular("DELETE FROM parcela WHERE parc_codigo = " + codigo);
    }

    private void get()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela WHERE parc_codigo = " + this.codigo);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.numero = rs.getInt("parc_numero");
                this.pagamento = rs.getDate("parc_datapagamento");
                this.valor_pago = rs.getDouble("parc_valorpago");
                this.valor_parcela = rs.getDouble("parc_valorparcela");
                this.vencimento = rs.getDate("parc_datavencimento");
                
                if(rs.getInt("comp_codigo") > 0)
                    this.compra = new Compra(rs.getInt("comp_codigo"));
                else
                    this.venda = new Venda(rs.getInt("ven_codigo"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
    }

    public ArrayList<Parcela> getByCompra(Compra compra)
    {
        ArrayList<Parcela> parcelas = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela WHERE comp_codigo = " + compra.getCodigo());
        
        try
        {
            while(rs != null && rs.next())            
            {
                Parcela p = new Parcela();
                p.setCodigo(rs.getInt("parc_codigo"));
                p.setValor_pago(rs.getDouble("parc_valorpago"));
                p.setCompra(compra);
                p.setNumero(rs.getInt("parc_numero"));
                p.setPagamento(rs.getDate("parc_datapagamento"));
                p.setValor_parcela(rs.getDouble("parc_valorparcela"));
                p.setVencimento(rs.getDate("parc_datavencimento"));
                parcelas.add(p);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parcelas;
    }

    public ArrayList<Parcela> getByVenda(Venda venda)
    {
        ArrayList<Parcela> parcelas = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela WHERE ven_codigo = " + venda.getCodigo());
        
        try
        {
            while(rs != null && rs.next())            
            {
                Parcela p = new Parcela();
                p.setCodigo(rs.getInt("parc_codigo"));
                p.setValor_pago(rs.getDouble("parc_valorpago"));
                p.setVenda(venda);
                p.setNumero(rs.getInt("parc_numero"));
                p.setPagamento(rs.getDate("parc_datapagamento"));
                p.setValor_parcela(rs.getDouble("parc_valorparcela"));
                p.setVencimento(rs.getDate("parc_datavencimento"));
                parcelas.add(p);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parcelas;
    }
    
    public int getQtdParcelas(Compra compra)
    {
        ResultSet rs = Banco.getCon().consultar("SELECT MAX(parc_numero) AS qtd FROM parcela WHERE comp_codigo = "
            + compra.getCodigo());
        
        try
        {
            if(rs != null && rs.next())            
                return rs.getInt("qtd");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
