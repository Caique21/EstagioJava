/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Recebimento
{
    private int codigo;
    private Date data;
    private double valor;
    private Parcela parcela;
    private String forma_recebimento;
    private String forma_recebimento_desc;

    public Recebimento()
    {
    }

    public Recebimento(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Recebimento(Parcela parcela)
    {
        this.parcela = parcela;
        get();
    }

    public Recebimento(int codigo, Date data, double valor, Parcela parcela, String forma_recebimento, String forma_recebimento_desc)
    {
        this.codigo = codigo;
        this.data = data;
        this.valor = valor;
        this.parcela = parcela;
        this.forma_recebimento = forma_recebimento;
        this.forma_recebimento_desc = forma_recebimento_desc;
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

    public String getForma_recebimento()
    {
        return forma_recebimento;
    }

    public void setForma_recebimento(String forma_recebimento)
    {
        this.forma_recebimento = forma_recebimento;
    }

    public String getForma_recebimento_desc()
    {
        return forma_recebimento_desc;
    }

    public void setForma_recebimento_desc(String forma_recebimento_desc)
    {
        this.forma_recebimento_desc = forma_recebimento_desc;
    }

    private void get()
    {
        String sql;
        if(this.parcela != null)
            sql = "SELECT * FROM recebimento WHERE parc_codigo = " + this.parcela.getCodigo();
        else
            sql = "SELECT * FROM recebimento WHERE rec_codigo = " + this.codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            if(rs != null && rs.next())            
            {
                this.codigo = rs.getInt("rec_codigo");
                this.data = rs.getDate("rec_data");
                this.forma_recebimento = rs.getString("rec_form_pagamento");
                this.forma_recebimento_desc = rs.getString("rec_form_pagamento_desc");
                this.valor = rs.getDouble("rec_valor");
                this.parcela = this.parcela != null ? this.parcela : new Parcela(rs.getInt("parc_codigo"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
    }
    
    public boolean pagar()
    {
        Connection con = Banco.getCon().getConnection();
        PreparedStatement stmt,stmt2 = null;
        boolean flag = false;
        
        try
        {
            con.setAutoCommit(false);
            String sql;
            
            stmt = con.prepareStatement("INSERT INTO recebimento(rec_data,rec_valor,rec_form_pagamento,"
                    + "rec_form_pagamento_desc,parc_codigo) VALUES(?,?,?,?,?)");
            stmt.setDate(1, this.data);
            stmt.setDouble(2, this.valor);
            stmt.setString(3, this.forma_recebimento);
            stmt.setString(4, this.forma_recebimento_desc);
            stmt.setInt(5, this.parcela.getCodigo());

            sql = "UPDATE parcela SET parc_datarecebimento = '$1', parc_valorpago = $2 WHERE parc_codigo = $3";
            sql = sql.replace("$1", String.valueOf(Date.valueOf(LocalDate.now())));
            sql = sql.replace("$2", String.valueOf(this.valor));
            sql = sql.replace("$3", String.valueOf(this.parcela.getCodigo()));
            
            stmt2 = con.prepareStatement(sql);
            if(flag = stmt.executeUpdate() == 1 && stmt2.executeUpdate() == 1)
                con.commit();
            else
                con.rollback();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return flag;
    }
     
    public boolean pagarParcial()
    {
        Connection con = Banco.getCon().getConnection();
        PreparedStatement stmt,stmt2,stmt3 = null;
        boolean flag = false;
        
        try
        {
            con.setAutoCommit(false);
            String sql;
            
            stmt = con.prepareStatement("INSERT INTO recebimento(rec_data,rec_valor,rec_form_pagamento,"
                    + "rec_form_pagamento_desc,parc_codigo) VALUES(?,?,?,?,?)");
            stmt.setDate(1, this.data);
            stmt.setDouble(2, this.valor);
            stmt.setString(3, this.forma_recebimento);
            stmt.setString(4, this.forma_recebimento_desc);
            stmt.setInt(5, this.parcela.getCodigo());

            stmt2 = con.prepareStatement("INSERT INTO parcela(parc_datavencimento,parc_numero,parc_valorparcela,"
                + "ven_codigo) VALUES (?,?,?,?)");
            stmt2.setDate(1, this.getParcela().getVencimento());
            stmt2.setInt(2, new Parcela().getQtdParcelas(this.parcela.getCompra()) + 1);
            stmt2.setDouble(3, Utils.truncate(this.parcela.getValor_parcela() - this.valor));
            stmt2.setInt(4, this.parcela.getVenda().getCodigo());

            sql = "UPDATE parcela SET parc_datarecebimento = '$1', parc_valorpago = $2 WHERE parc_codigo = $3";
            sql = sql.replace("$1", String.valueOf(Date.valueOf(LocalDate.now())));
            sql = sql.replace("$2", String.valueOf(this.valor));
            sql = sql.replace("$3", String.valueOf(this.parcela.getCodigo()));
            stmt3 = con.prepareStatement(sql);
            
            if(flag = stmt.executeUpdate() == 1 && stmt2.executeUpdate() == 1 && stmt3.executeUpdate() == 1)
                con.commit();
            else
                con.rollback();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return flag;
    }
    
    public boolean estornar(int usuario)
    {
        Connection con = Banco.getCon().getConnection();
        PreparedStatement stmt,stmt2 = null,stmt3 = null;
        boolean flag = true;
        String sql;
        
        try
        {
            con.setAutoCommit(false);
            
            stmt = con.prepareStatement("DELETE FROM recebimento WHERE rec_codigo = " + this.codigo);

            stmt3 = con.prepareStatement("INSERT INTO registro_estorno (reg_data, user_codigo, parc_codigo) "
                    + "VALUES(?,?,?)");
            stmt3.setDate(1, Date.valueOf(LocalDate.now()));
            stmt3.setInt(2, usuario);
            stmt3.setInt(3, this.parcela.getCodigo());

            sql = "UPDATE parcela SET parc_datarecebimento = ?, parc_valorpago = ? WHERE parc_codigo = ?";
            stmt2 = con.prepareStatement(sql);
            stmt2.setNull(1, java.sql.Types.DATE);
            stmt2.setNull(2, java.sql.Types.DOUBLE);
            stmt2.setInt(3, this.parcela.getCodigo());

            flag = stmt2.executeUpdate() == 1;

            if (flag && this.valor != this.parcela.getValor_parcela())
            {
                sql = "SELECT parc_codigo FROM parcela WHERE ven_codigo = $1 AND parc_datavencimento = '$2' "
                        + "AND parc_valorparcela = $3";
                sql = sql.replace("$1", String.valueOf(this.parcela.getVenda().getCodigo()));
                sql = sql.replace("$2", String.valueOf(this.parcela.getVencimento()));
                sql = sql.replace("$3", String.valueOf(
                        Utils.truncate(this.parcela.getValor_parcela() - this.valor)));

                ResultSet rs = con.prepareStatement(sql).executeQuery();

                if (rs != null && rs.next())
                    flag = con.prepareStatement("DELETE FROM parcela WHERE parc_codigo = "
                            + rs.getInt("parc_codigo")).executeUpdate() == 1;
            }

            
            if(flag = flag && stmt3.executeUpdate() == 1 && stmt.executeUpdate() == 1)
                con.commit();
            else
                con.rollback();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        
       return flag;
    }
    
    public ArrayList<Recebimento> getAll()
    {
        ArrayList<Recebimento>recebimentos = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela LEFT JOIN recebimento "
                + "ON parcela.parc_codigo = recebimento.parc_codigo WHERE ven_codigo > 0");
        
        try            
        {
            while(rs != null && rs.next())
            {
                recebimentos.add(new Recebimento(rs.getInt("rec_codigo"), rs.getDate("rec_data"), 
                        rs.getDouble("rec_valor"), new Parcela(rs.getInt("parc_codigo")),
                        rs.getString("rec_form_pagamento"),rs.getString("rec_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return recebimentos;
    }

    public ArrayList<Recebimento> getByFornecedor(String fornecedor)
    {
        ArrayList<Recebimento>recebimentos = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela LEFT JOIN recebimento "
            + "ON recebimento.parc_codigo = parcela.parc_codigo WHERE parcela.parc_codigo "
                + "IN(SELECT parc_codigo from parcela INNER JOIN venda ON parcela.ven_codigo = venda.ven_codigo "
                + "AND venda.ven_codigo "
                    + "IN(SELECT ven_codigo FROM venda INNER JOIN cliente ON venda.cli_codigo = cliente.cli_codigo "
                        + "AND cli_nome Ilike '%" + fornecedor + "%' \n" 
                    +"UNION \n" 
                    + "SELECT ven_codigo FROM venda INNER JOIN fornecedor "
                        + "ON venda.forn_codigo = fornecedor.forn_codigo AND forn_nome Ilike '%"+fornecedor+"%'))");
        
        try            
        {
            while(rs != null && rs.next())
            {
                recebimentos.add(new Recebimento(rs.getInt("rec_codigo"), rs.getDate("rec_data"), 
                        rs.getDouble("rec_valor"), new Parcela(rs.getInt("parc_codigo")),
                        rs.getString("rec_form_pagamento"),rs.getString("rec_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return recebimentos;
    }

    public ArrayList<Recebimento> getByNotaFiscal(String nota_fiscal)
    {
        ArrayList<Recebimento>recebimentos = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela LEFT JOIN recebimento "
            + "ON parcela.parc_codigo = recebimento.parc_codigo WHERE parcela.parc_codigo IN "
                + "(SELECT parc_codigo FROM parcela INNER JOIN venda ON parcela.ven_codigo = venda.ven_codigo "
                + "AND ven_nota_fiscal Ilike '%" + nota_fiscal + "%')");
        
        try            
        {
            while(rs != null && rs.next())
            {
                recebimentos.add(new Recebimento(rs.getInt("rec_codigo"), rs.getDate("rec_data"), 
                        rs.getDouble("rec_valor"), new Parcela(rs.getInt("parc_codigo")),
                        rs.getString("rec_form_pagamento"),rs.getString("rec_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return recebimentos;
    }

    public ArrayList<Recebimento> getByVencimento(LocalDate data)
    {
        ArrayList<Recebimento>recebimentos = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela LEFT JOIN recebimento "
            + "ON parcela.parc_codigo = recebimento.parc_codigo WHERE ven_codigo > 0 AND "
                + "parc_datavencimento <= '" + data.toString() + "'");
        
        try            
        {
            while(rs != null && rs.next())
            {
                recebimentos.add(new Recebimento(rs.getInt("rec_codigo"), rs.getDate("rec_data"), 
                        rs.getDouble("rec_valor"), new Parcela(rs.getInt("parc_codigo")),
                        rs.getString("rec_form_pagamento"),rs.getString("rec_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return recebimentos;
    }

    public ArrayList<Recebimento> getByPeriodo(LocalDate inicial, LocalDate fim)
    {
        ArrayList<Recebimento>recebimentos = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela LEFT JOIN recebimento "
            + "ON parcela.parc_codigo = recebimento.parc_codigo "
               + "WHERE ven_codigo > 0 AND parc_datavencimento >= '" + inicial.toString() + "' "
               + "AND parc_datavencimento <= '" + fim.toString() + "'");
        
        try            
        {
            while(rs != null && rs.next())
            {
                recebimentos.add(new Recebimento(rs.getInt("rec_codigo"), rs.getDate("rec_data"), 
                        rs.getDouble("rec_valor"), new Parcela(rs.getInt("parc_codigo")),
                        rs.getString("rec_form_pagamento"),rs.getString("rec_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return recebimentos;
    }
    
    public ArrayList<Objeto> getContasVencer(LocalDate inicio, LocalDate... fim)
    {
        ArrayList<Objeto>recebimentos = new ArrayList<>();
        String sql = "SELECT forn_nome as nome, parc_numero,parc_datavencimento,"
            + "to_char(parc_valorparcela,'L999G999G990D99') AS valor, ven_nota_fiscal FROM parcela LEFT JOIN "
            + "pagamento ON parcela.parc_codigo = pagamento.parc_codigo INNER JOIN venda "
            + "ON parcela.ven_codigo = venda.ven_codigo INNER JOIN fornecedor "
            + "ON venda.forn_codigo = fornecedor.forn_codigo WHERE pag_codigo IS NULL AND $1 "
        + "UNION "
        + "SELECT cli_nome as nome, parc_numero,parc_datavencimento,"
            + "to_char(parc_valorparcela,'L999G999G990D99') AS valor, ven_nota_fiscal FROM parcela LEFT JOIN "
            + "pagamento ON parcela.parc_codigo = pagamento.parc_codigo INNER JOIN venda "
            + "ON parcela.ven_codigo = venda.ven_codigo INNER JOIN cliente "
            + "ON venda.cli_codigo = cliente.cli_codigo WHERE pag_codigo IS NULL AND $1";
        
        if(fim.length == 0)
            sql = sql.replace("$1", "parc_datavencimento <= '" + inicio.toString() + "'");
        else
            sql = sql.replace("$1", "parc_datavencimento >= '" + inicio.toString() + "' AND "
                + "parc_datavencimento <= '" + fim[0].toString() + "'");
        
        ResultSet rs = Banco.getCon().consultar(sql);
        try            
        {
            while(rs != null && rs.next())
            {
                Objeto o = new Objeto(rs.getString("nome"));
                o.setParam2(String.valueOf(rs.getInt("parc_numero")));
                o.setParam3(String.valueOf(rs.getDate("parc_datavencimento")));
                o.setParam4(rs.getString("valor"));
                o.setParam5(rs.getString("ven_nota_fiscal"));
                o.setParam6(Utils.convertDataUTC(rs.getDate("parc_datavencimento")));
                recebimentos.add(o);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return recebimentos;
    }
    
    public ArrayList<Objeto> getInadimplentes()
    {
        ArrayList<Objeto>recebimentos = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT forn_nome as nome, parc_numero,parc_datavencimento,"
            + "to_char(parc_valorparcela,'L999G999G990D99') AS valor, ven_nota_fiscal FROM parcela LEFT JOIN "
            + "pagamento ON parcela.parc_codigo = pagamento.parc_codigo INNER JOIN venda "
            + "ON parcela.ven_codigo = venda.ven_codigo INNER JOIN fornecedor "
            + "ON venda.forn_codigo = fornecedor.forn_codigo WHERE pag_codigo IS NULL AND "
            + "CURRENT_DATE > parc_datavencimento "
        + "UNION "
        + "SELECT cli_nome as nome, parc_numero,parc_datavencimento,"
            + "to_char(parc_valorparcela,'L999G999G990D99') AS valor, ven_nota_fiscal FROM parcela LEFT JOIN "
            + "pagamento ON parcela.parc_codigo = pagamento.parc_codigo INNER JOIN venda "
            + "ON parcela.ven_codigo = venda.ven_codigo INNER JOIN cliente "
            + "ON venda.cli_codigo = cliente.cli_codigo WHERE pag_codigo IS NULL AND "
            + "CURRENT_DATE > parc_datavencimento");
        
        try            
        {
            while(rs != null && rs.next())
            {
                Objeto o = new Objeto(rs.getString("nome"));
                o.setParam2(String.valueOf(rs.getInt("parc_numero")));
                o.setParam3(String.valueOf(rs.getDate("parc_datavencimento")));
                o.setParam4(rs.getString("valor"));
                o.setParam5(rs.getString("ven_nota_fiscal"));
                o.setParam6(Utils.convertDataUTC(rs.getDate("parc_datavencimento")));
                recebimentos.add(o);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Recebimento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return recebimentos;
    }
}
