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
public class Pagamento
{
    private int codigo;
    private Date data;
    private double valor;
    private Parcela parcela;
    private Despesa despesa;
    private String forma_pagamento;
    private String forma_pagamento_desc;

    public Pagamento()
    {
    }

    public Pagamento(int codigo, Date data, double valor, Parcela parcela, String forma_pagamento, String forma_pagamento_desc)
    {
        this.codigo = codigo;
        this.data = data;
        this.valor = valor;
        this.parcela = parcela;
        this.forma_pagamento = forma_pagamento;
        this.forma_pagamento_desc = forma_pagamento_desc;
    }

    public Pagamento(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Pagamento(Despesa despesa)
    {
        this.despesa = despesa;
        get();
    }

    public Pagamento(int codigo, Date data, double valor, Despesa despesa, String forma_pagamento,String forma_pagamento_desc)
    {
        this.codigo = codigo;
        this.data = data;
        this.valor = valor;
        this.despesa = despesa;
        this.forma_pagamento = forma_pagamento;
        this.forma_pagamento_desc = forma_pagamento_desc;
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

    public Pagamento(Date data, double valor, Parcela parcela, String forma_pagamento)
    {
        this.data = data;
        this.valor = valor;
        this.parcela = parcela;
        this.forma_pagamento = forma_pagamento;
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

    public String getForma_pagamento_desc()
    {
        return forma_pagamento_desc;
    }

    public void setForma_pagamento_desc(String forma_pagamento_desc)
    {
        this.forma_pagamento_desc = forma_pagamento_desc;
    }

    public boolean pagar()
    {
        Connection con = Banco.getCon().getConnection();
        PreparedStatement stmt,stmt2 = null;
        boolean flag = true;
        
        try
        {
            con.setAutoCommit(false);
            String sql;
        
            if(this.despesa != null || this.parcela != null)
            {    
                if(this.despesa != null)
                {
                    stmt = con.prepareStatement("INSERT INTO pagamento(pag_data,pag_valor,pag_form_pagamento,"
                        + "pag_form_pagamento_desc,desp_codigo) VALUES(?,?,?,?,?)");
                    stmt.setInt(5, this.despesa.getCodigo());
                }
                else
                {
                    stmt = con.prepareStatement("INSERT INTO pagamento(pag_data,pag_valor,pag_form_pagamento,"
                        + "pag_form_pagamento_desc,parc_codigo) VALUES(?,?,?,?,?)");
                    stmt.setInt(5, this.parcela.getCodigo());
                    
                    sql = "UPDATE parcela SET parc_datapagamento = '$1', parc_valorpago = $2 WHERE parc_codigo = $3";
                    sql = sql.replace("$1", String.valueOf(Date.valueOf(LocalDate.now())));
                    sql = sql.replace("$2", String.valueOf(this.valor));
                    sql = sql.replace("$3", String.valueOf(this.parcela.getCodigo()));
                    stmt2 = con.prepareStatement(sql);
                    flag = stmt2.executeUpdate() == 1;
                }
                stmt.setDate(1, this.data);
                stmt.setDouble(2, this.valor);
                stmt.setString(3, this.forma_pagamento);
                stmt.setString(4, this.forma_pagamento_desc);
                
                
                if(flag = flag && stmt.executeUpdate() == 1)
                    con.commit();
                else
                    con.rollback();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return flag;
        /*
        
        
        
        String sql = "INSERT INTO pagamento(pag_data,pag_valor,pag_form_pagamento,pag_form_pagamento_desc,pag_ativo,"
                + "$aux) VALUES('$1',$2,'$3','$4','$5',$6)";
        
        if(this.despesa != null)
            sql = sql.replace("$aux", "desp_codigo").replace("$6", String.valueOf(this.despesa.getCodigo()));
        else
            sql = sql.replace("$aux", "parc_codigo").replace("$6", String.valueOf(this.parcela.getCodigo()));
        sql = sql.replace("$1", String.valueOf(this.data));
        sql = sql.replace("$2", String.valueOf(this.valor));
        sql = sql.replace("$3", this.forma_pagamento);
        sql = sql.replace("$4", this.forma_pagamento_desc);
        sql = sql.replace("$5", String.valueOf(true));
            
       return Banco.getCon().manipular(sql);*/
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
        
            if(this.despesa != null || this.parcela != null)
            {    
                if(this.despesa != null)
                {
                    stmt = con.prepareStatement("INSERT INTO pagamento(pag_data,pag_valor,pag_form_pagamento,"
                        + "pag_form_pagamento_desc,desp_codigo) VALUES(?,?,?,?,?)");
                    stmt.setInt(5, this.despesa.getCodigo());
                    
                    sql = "INSERT INTO despesa(desp_nome,desp_fixo,desp_preco,desp_data_vencimento,desp_descricao $7)"
                        + " VALUES(?,?,?,?,?,$6)";
                    if(this.despesa.getTransporte() == null)
                        sql = sql.replace(" $7", "").replace(",$6", "");
                    else
                        sql = sql.replace(" $7", ",trans_codigo").replace(",$6", "?");
                    
                    stmt2 = con.prepareStatement(sql);
                    stmt2.setString(1, this.despesa.getNome());
                    stmt2.setBoolean(2, this.despesa.isFixo());
                    stmt2.setDouble(3, Utils.truncate(this.despesa.getValor() - this.valor));
                    stmt2.setDate(4, this.getDespesa().getVencimento());
                    stmt2.setString(5, this.getDespesa().getDescricao());
                    
                    if(this.getDespesa().getTransporte() != null)
                        stmt.setInt(6, this.getDespesa().getTransporte().getCodigo());
                }
                else
                {
                    stmt = con.prepareStatement("INSERT INTO pagamento(pag_data,pag_valor,pag_form_pagamento,"
                        + "pag_form_pagamento_desc,parc_codigo) VALUES(?,?,?,?,?)");
                    stmt.setInt(5, this.parcela.getCodigo());
                    
                    sql = "INSERT INTO parcela(parc_datavencimento,parc_numero,parc_valorparcela,comp_codigo)"
                        + "VALUES (?,?,?,?)";
                    stmt2 = con.prepareStatement(sql);
                    stmt2.setDate(1, this.getParcela().getVencimento());
                    stmt2.setInt(2, new Parcela().getQtdParcelas(this.parcela.getCompra()) + 1);
                    stmt2.setDouble(3, Utils.truncate(this.parcela.getValor_parcela() - this.valor));
                    stmt2.setInt(4, this.parcela.getCompra().getCodigo());
                    
                    sql = "UPDATE parcela SET parc_datapagamento = '$1', parc_valorpago = $2 WHERE parc_codigo = $3";
                    sql = sql.replace("$1", String.valueOf(Date.valueOf(LocalDate.now())));
                    sql = sql.replace("$2", String.valueOf(this.valor));
                    sql = sql.replace("$3", String.valueOf(this.parcela.getCodigo()));
                    stmt3 = con.prepareStatement(sql);
                }
                stmt.setDate(1, this.data);
                stmt.setDouble(2, this.valor);
                stmt.setString(3, this.forma_pagamento);
                stmt.setString(4, this.forma_pagamento_desc);
                
                if(flag = stmt.executeUpdate() == 1 && stmt2.executeUpdate() == 1 && 
                        (stmt3 != null? stmt3.executeUpdate() == 1 : true))
                    con.commit();
                else
                    con.rollback();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return flag;
    }

    private void get()
    {
        String sql = "SELECT * FROM pagamento WHERE ";
        
        if(this.despesa != null)
            sql += "desp_codigo = " + this.despesa.getCodigo();
        else if(this.parcela != null)
            sql += "parc_codigo = " + this.parcela.getCodigo();
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
                this.parcela = rs.getInt("parc_codigo") > 0? new Parcela(rs.getInt("parc_codigo")) : null;
                this.despesa = rs.getInt("desp_codigo") > 0? new Despesa(rs.getInt("desp_codigo")) : null;
                this.forma_pagamento = rs.getString("pag_form_pagamento");
                this.forma_pagamento_desc = rs.getString("pag_form_pagamento_desc");
                //if(this.despesa == null)
                  //  this.despesa = new Despesa(rs.getInt("desp_codigo"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
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
            
            stmt = con.prepareStatement("DELETE FROM pagamento WHERE pag_codigo = " + this.codigo);
            
            if(this.parcela != null)
            {
                stmt3 = con.prepareStatement("INSERT INTO registro_estorno (reg_data, user_codigo, parc_codigo) "
                    + "VALUES(?,?,?)");
                stmt3.setDate(1, Date.valueOf(LocalDate.now()));
                stmt3.setInt(2, usuario);
                stmt3.setInt(3, this.parcela.getCodigo());
                
                sql = "UPDATE parcela SET parc_datapagamento = ?, parc_valorpago = ? WHERE parc_codigo = ?";
                stmt2 = con.prepareStatement(sql);
                stmt2.setNull(1, java.sql.Types.DATE);
                stmt2.setNull(2, java.sql.Types.DOUBLE);
                stmt2.setInt(3, this.parcela.getCodigo());
                
                flag = stmt2.executeUpdate() == 1;
                
                if(flag && this.valor != this.parcela.getValor_parcela())
                {
                    sql = "SELECT parc_codigo FROM parcela WHERE comp_codigo = $1 AND parc_datavencimento = '$2' "
                        + "AND parc_valorparcela = $3";
                    sql = sql.replace("$1", String.valueOf(this.parcela.getCompra().getCodigo()));
                    sql = sql.replace("$2", String.valueOf(this.parcela.getVencimento()));
                    sql = sql.replace("$3", String.valueOf(
                            Utils.truncate(this.parcela.getValor_parcela() - this.valor)));
                    
                    ResultSet rs = con.prepareStatement(sql).executeQuery();
                    
                    if(rs != null && rs.next())
                        flag = con.prepareStatement("DELETE FROM parcela WHERE parc_codigo = " + 
                                rs.getInt("parc_codigo")).executeUpdate() == 1;
                }
            }
            else if(this.despesa != null)
            {
                stmt3 = con.prepareStatement("INSERT INTO registro_estorno (reg_data, user_codigo, desp_codigo) "
                    + "VALUES(?,?,?)");
                stmt3.setDate(1, Date.valueOf(LocalDate.now()));
                stmt3.setInt(2, usuario);
                stmt3.setInt(3, this.despesa.getCodigo());
                
                if(this.valor < this.despesa.getValor())
                {
                    sql = "SELECT desp_codigo FROM despesa WHERE desp_nome = '$1' AND desp_data_vencimento = '$2' "
                        + "AND desp_preco = $3";
                    sql = sql.replace("$1", this.getDespesa().getNome());
                    sql = sql.replace("$2", String.valueOf(this.getDespesa().getVencimento()));
                    sql = sql.replace("$3", String.valueOf(
                            Utils.truncate(this.despesa.getValor() - this.valor)));
                    
                    ResultSet rs = con.prepareStatement(sql).executeQuery();
                    if(rs != null && rs.next())
                        flag = con.prepareStatement("DELETE FROM despesa WHERE desp_codigo = " + 
                                rs.getInt("desp_codigo")).executeUpdate() == 1;
                }
            }
            
            if(flag = flag && (stmt3 != null ? stmt3.executeUpdate() == 1 : false) && stmt.executeUpdate() == 1)
                con.commit();
            else
                con.rollback();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        
       return flag;
    }

    public ArrayList<Pagamento> getAll()
    {
        ArrayList<Pagamento>pagamentos = new ArrayList<>();
        /*ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela LEFT JOIN pagamento "
                + "ON parcela.parc_codigo = pagamento.parc_codigo WHERE comp_codigo > 0 ORDER BY parc_datavencimento");*/
        
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM (SELECT pag_codigo, pag_data, pag_valor,pag_form_pagamento, pag_form_pagamento_desc, despesa.desp_codigo as cod, 'Despesa' as cat, desp_data_vencimento as data FROM despesa LEFT JOIN pagamento ON despesa.desp_codigo = pagamento.desp_codigo\n"
                + "UNION \n"
                + "SELECT pag_codigo, pag_data, pag_valor,pag_form_pagamento, pag_form_pagamento_desc, parcela.parc_codigo as cod, 'Parcela' as cat, parc_datavencimento as data FROM parcela LEFT JOIN pagamento ON parcela.parc_codigo = pagamento.parc_codigo WHERE comp_codigo > 0) as tab order by data");
        Pagamento pagamento;
        
        try            
        {
            while(rs != null && rs.next())
            {
                if(rs.getString("cat").equals("Parcela"))
                    pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Parcela(rs.getInt("cod")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
                else
                    pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Despesa(rs.getInt("cod")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        /*
        rs = Banco.getCon().consultar("SELECT * FROM despesa LEFT JOIN pagamento "
                + "ON despesa.desp_codigo = pagamento.desp_codigo order by desp_data_vencimento");
        
        try            
        {
            while(rs != null && rs.next())
            {
                pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Despesa(rs.getInt("desp_codigo")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }*/
        return pagamentos;
    }

    public ArrayList<Pagamento> getByFornecedor(String fornecedor)
    {
        ArrayList<Pagamento>pagamentos = new ArrayList<>();
        Pagamento pagamento;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela LEFT JOIN pagamento "
            + "ON pagamento.parc_codigo = parcela.parc_codigo WHERE parcela.parc_codigo "
                + "IN(SELECT parc_codigo from parcela INNER JOIN compra ON parcela.comp_codigo = compra.comp_codigo "
                + "AND compra.comp_codigo "
                    + "IN(SELECT comp_codigo FROM compra INNER JOIN cliente ON compra.cli_codigo = cliente.cli_codigo "
                        + "AND cli_nome Ilike '%" + fornecedor + "%' \n" 
                    +"UNION \n" 
                    + "SELECT comp_codigo FROM compra INNER JOIN fornecedor "
                        + "ON compra.forn_codigo = fornecedor.forn_codigo AND forn_nome Ilike '%"+fornecedor+"%')) ORDER BY parc_datavencimento");
        
        try            
        {
            while(rs != null && rs.next())
            {
                pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Parcela(rs.getInt("parc_codigo")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return pagamentos;
    }

    public ArrayList<Pagamento> getByNotaFiscal(String nota_fiscal)
    {
        ArrayList<Pagamento>pagamentos = new ArrayList<>();
        Pagamento pagamento;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela LEFT JOIN pagamento "
            + "ON parcela.parc_codigo = pagamento.parc_codigo WHERE parcela.parc_codigo IN "
                + "(SELECT parc_codigo FROM parcela INNER JOIN compra ON parcela.comp_codigo = compra.comp_codigo "
                + "AND comp_nota_fiscal Ilike '%" + nota_fiscal + "%') ORDER BY parc_datavencimento");
        
        try            
        {
            while(rs != null && rs.next())
            {
                pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Parcela(rs.getInt("parc_codigo")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return pagamentos;
    }

    public ArrayList<Pagamento> getByDespesa(String despesa)
    {
        ArrayList<Pagamento>pagamentos = new ArrayList<>();
        Pagamento pagamento;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM despesa LEFT JOIN pagamento "
            + "ON despesa.desp_codigo = pagamento.desp_codigo WHERE desp_nome Ilike '%" + despesa + "%' "
                    + "ORDER BY desp_data_vencimento");
        
        try            
        {
            while(rs != null && rs.next())
            {
                pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Despesa(rs.getInt("desp_codigo")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return pagamentos;
    }

    public ArrayList<Pagamento> getByVencimento(LocalDate data)
    {
        ArrayList<Pagamento>pagamentos = new ArrayList<>();
        Pagamento pagamento;
        /*ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela LEFT JOIN pagamento "
            + "ON parcela.parc_codigo = pagamento.parc_codigo WHERE comp_codigo > 0 AND "
                + "parc_datavencimento <= '" + data.toString() + "'");*/
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM (SELECT pag_codigo, pag_data, pag_valor,"
                + "pag_form_pagamento, pag_form_pagamento_desc, despesa.desp_codigo as cod, 'Despesa' as cat, "
                + "desp_data_vencimento as data FROM despesa LEFT JOIN pagamento "
                + "ON despesa.desp_codigo = pagamento.desp_codigo "
                +   "WHERE desp_data_vencimento <= '" + data.toString() + "' " +
            "UNION " +
            "SELECT pag_codigo, pag_data, pag_valor,pag_form_pagamento, pag_form_pagamento_desc, "
                + "parcela.parc_codigo as cod, 'Parcela' as cat, parc_datavencimento as data FROM parcela "
                + "LEFT JOIN pagamento ON parcela.parc_codigo = pagamento.parc_codigo WHERE comp_codigo > 0 "
                + "AND parc_datavencimento <= '" + data.toString() + "') as tab order by data");
        
        try            
        {
            while(rs != null && rs.next())
            {
                /*pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Parcela(rs.getInt("parc_codigo")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));*/
                if(rs.getString("cat").equals("Parcela"))
                    pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Parcela(rs.getInt("cod")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
                else
                    pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Despesa(rs.getInt("cod")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        
        /*rs = Banco.getCon().consultar("SELECT * FROM despesa LEFT JOIN pagamento "
            + "ON despesa.desp_codigo = pagamento.desp_codigo WHERE desp_data_vencimento <= '"+data.toString()+"'");
        
        try            
        {
            while(rs != null && rs.next())
            {
                pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Despesa(rs.getInt("desp_codigo")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }*/
        return pagamentos;
    }

    public ArrayList<Pagamento> getByPeriodo(LocalDate inicial, LocalDate fim)
    {
        ArrayList<Pagamento>pagamentos = new ArrayList<>();
        Pagamento pagamento;
        /*ResultSet rs = Banco.getCon().consultar("SELECT * FROM parcela LEFT JOIN pagamento "
            + "ON parcela.parc_codigo = pagamento.parc_codigo "
               + "WHERE comp_codigo > 0 AND parc_datavencimento >= '" + inicial.toString() + "' "
               + "AND parc_datavencimento <= '" + fim.toString() + "'");*/
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM (SELECT pag_codigo, pag_data, pag_valor,"
            + "pag_form_pagamento, pag_form_pagamento_desc, despesa.desp_codigo as cod, 'Despesa' as cat, "
            + "desp_data_vencimento as data FROM despesa LEFT JOIN pagamento "
            + "ON despesa.desp_codigo = pagamento.desp_codigo WHERE "
            + "desp_data_vencimento >= '" + inicial.toString() + "' AND "
                    + "desp_data_vencimento <= '" + fim.toString() + "' " +
        "UNION " +
        "SELECT pag_codigo, pag_data, pag_valor,pag_form_pagamento, pag_form_pagamento_desc, "
            + "parcela.parc_codigo as cod, 'Parcela' as cat, parc_datavencimento as data FROM parcela "
            + "LEFT JOIN pagamento ON parcela.parc_codigo = pagamento.parc_codigo WHERE comp_codigo > 0 "
            + "AND parc_datavencimento >= '" + inicial.toString() + "' AND "
                + "parc_datavencimento <= '" + fim.toString() + "') as tab order by data");
        
        try            
        {
            while(rs != null && rs.next())
            {
                /*pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Parcela(rs.getInt("parc_codigo")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));*/
                if(rs.getString("cat").equals("Parcela"))
                    pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Parcela(rs.getInt("cod")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
                else
                    pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Despesa(rs.getInt("cod")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        
        /*rs = Banco.getCon().consultar("SELECT * FROM despesa LEFT JOIN pagamento "
            + "ON despesa.desp_codigo = pagamento.desp_codigo "
            + "WHERE desp_data_vencimento >= '" + inicial.toString() +"' "
            + "AND desp_data_vencimento <= '" + fim.toString() + "'");
        
        try            
        {
            while(rs != null && rs.next())
            {
                pagamentos.add(new Pagamento(rs.getInt("pag_codigo"), rs.getDate("pag_data"), 
                        rs.getDouble("pag_valor"), new Despesa(rs.getInt("desp_codigo")),
                        rs.getString("pag_form_pagamento"),rs.getString("pag_form_pagamento_desc")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }*/
        return pagamentos;
    }

    public ArrayList<Objeto> getContasPagar(LocalDate inicial, LocalDate fim)
    {
        ArrayList<Objeto>pagamentos = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT forn_nome AS fornecedor, CONCAT('Compra ', comp_nota_fiscal) AS nome,"
            + "parc_numero AS descricao, to_char(parc_valorparcela,'L999G999G990D99') AS valor,"
            + "parc_datavencimento AS data FROM parcela LEFT JOIN pagamento "
            + "ON parcela.parc_codigo = pagamento.parc_codigo INNER JOIN compra "
            + "ON parcela.comp_codigo = compra.comp_codigo INNER JOIN fornecedor "
            + "ON compra.forn_codigo = fornecedor.forn_codigo WHERE pag_codigo IS NULL AND parcela.comp_codigo > 0 "
            + "AND parc_datavencimento >= '" + inicial.toString() + "' "
                    + "AND parc_datavencimento <= '" + fim.toString() + "' "
        + "UNION "
        + "SELECT cli_nome AS fornecedor, CONCAT('Compra ', comp_nota_fiscal) AS nome,parc_numero AS descricao, "
            + "to_char(parc_valorparcela,'L999G999G990D99') AS valor,parc_datavencimento AS data FROM parcela "
            + "LEFT JOIN pagamento ON parcela.parc_codigo = pagamento.parc_codigo INNER JOIN compra "
            + "ON parcela.comp_codigo = compra.comp_codigo INNER JOIN cliente "
            + "ON compra.cli_codigo = cliente.cli_codigo WHERE pag_codigo IS NULL AND parcela.comp_codigo > 0 "
            + "AND parc_datavencimento >= '" + inicial.toString() + "' "
                + "AND parc_datavencimento <= '" + fim.toString() + "' "
        + "UNION "
        + "SELECT 'Despesa' AS fornecedor,CONCAT('Despesa ', desp_nome) AS nome,1 AS descricao,to_char(desp_preco,'L999G999G990D99') AS valor,"
            + "desp_data_vencimento AS data FROM despesa LEFT JOIN pagamento "
            + "ON despesa.desp_codigo = pagamento.desp_codigo WHERE "
            + "desp_data_vencimento >= '" + inicial.toString() + "' "
                + "AND desp_data_vencimento  <= '" + fim.toString() + "'");
        
        try            
        {
            while(rs != null && rs.next())
            {
                pagamentos.add(new Objeto(rs.getString("fornecedor"), rs.getString("nome"), 
                        rs.getString("descricao"), rs.getString("valor"), rs.getString("data"),Utils.convertDataUTC(rs.getDate("data"))));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return pagamentos;
    }

    public ArrayList<Objeto> getContasPagar(String nome)
    {
        ArrayList<Objeto>pagamentos = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT forn_nome AS fornecedor, CONCAT('Compra ', comp_nota_fiscal) AS nome,"
            + "CAST(parc_numero AS varchar) AS descricao, to_char(parc_valorparcela,'L999G999G990D99') AS valor,"
            + "parc_datavencimento AS data FROM parcela INNER JOIN compra "
            + "ON parcela.comp_codigo = compra.comp_codigo INNER JOIN fornecedor "
            + "ON compra.forn_codigo = fornecedor.forn_codigo LEFT JOIN pagamento "
            + "ON parcela.parc_codigo = pagamento.parc_codigo WHERE pag_codigo IS NULL AND "
                + "forn_nome Ilike '%" + nome + "%' "
        + "UNION "
        + "SELECT cli_nome AS fornecedor, CONCAT('Compra ', comp_nota_fiscal) AS nome,CAST(parc_numero AS varchar) AS descricao, "
            + "to_char(parc_valorparcela,'L999G999G990D99') AS valor,parc_datavencimento AS data FROM parcela "
            + "INNER JOIN compra ON parcela.comp_codigo = compra.comp_codigo INNER JOIN cliente "
            + "ON compra.cli_codigo = cliente.cli_codigo LEFT JOIN pagamento "
            + "ON parcela.parc_codigo = pagamento.parc_codigo WHERE pag_codigo IS NULL AND "
                        + "cli_nome Ilike '%" + nome + "%'");
        
        try            
        {
            while(rs != null && rs.next())
            {
                pagamentos.add(new Objeto(rs.getString("fornecedor"), rs.getString("nome"), 
                    rs.getString("descricao"), rs.getString("valor"), rs.getString("data"),Utils.convertDataUTC(rs.getDate("data"))));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return pagamentos;
    }
    
    public ArrayList<Objeto> getMovimentacao(LocalDate... periodo)
    {
        ArrayList<Objeto>recebimentos = new ArrayList<>();
        String sql = "SELECT CONCAT('Compra: ',comp_nota_fiscal) AS nome, parc_numero, "
                + "to_char(pag_valor,'L999G999G990D99') AS valor, pag_data, "
                + "CONCAT(pag_form_pagamento,pag_form_pagamento_desc) AS forma_pagamento FROM parcela "
                + "INNER JOIN pagamento on parcela.parc_codigo = pagamento.parc_codigo INNER JOIN compra "
                + "ON parcela.comp_codigo = compra.comp_codigo WHERE pag_codigo IS NOT NULL AND "
                + "parcela.comp_codigo > 0 AND $1 "
            + "UNION "
            + "SELECT CONCAT('Despesa ',desp_nome) AS nome, 1,to_char(pag_valor,'L999G999G990D99') AS valor, "
                + "pag_data, CONCAT(pag_form_pagamento,' ',pag_form_pagamento_desc) AS forma_pagamento FROM despesa "
                + "INNER JOIN pagamento on despesa.desp_codigo = pagamento.desp_codigo WHERE "
                + "pag_codigo IS NOT NULL AND $2";
        
        if(periodo.length == 0 || periodo.length != 2)
            sql = sql.replace("$1", "parc_datapagamento >= "
                + "(SELECT CAST(date_trunc('month', CURRENT_DATE) AS date)) AND "
                + "parc_datapagamento <= CURRENT_DATE").replace("$2", "pag_data >= "
                + "(SELECT CAST(date_trunc('month', CURRENT_DATE) AS date)) AND pag_data <= CURRENT_DATE");
        else
            sql = sql.replace("$1", "parc_datapagamento >= '" + periodo[0].toString() 
                + "' AND parc_datapagamento <= '" + periodo[1].toString() + "'")
                .replace("$2", "pag_data >= '" + periodo[0].toString() + "' "
                + "AND pag_data <= '" + periodo[1].toString() + "'");
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try            
        {
            while(rs != null && rs.next())
            {
                Objeto o = new Objeto(rs.getString("nome"));
                o.setParam2(String.valueOf(rs.getInt("parc_numero")));
                o.setParam3(rs.getString("valor"));
                o.setParam4(String.valueOf(rs.getDate("pag_data")));
                o.setParam5(rs.getString("forma_pagamento"));
                o.setParam6(Utils.convertDataUTC(rs.getDate("pag_data")));
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

    public ArrayList<Objeto> getPagamentosAnual()
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("select pag_valor, to_char from (select pag_valor, to_char(pag_data,'MM') from despesa inner join pagamento on pagamento.desp_codigo = despesa.desp_codigo and desp_data_vencimento >= to_date(concat('01','01',to_char(now(),'YYYY')),'DDMMYYY')\n" +
"union\n" +
"select pag_valor, to_char(pag_data,'MM') from parcela inner join pagamento on pagamento.parc_codigo = parcela.parc_codigo where comp_codigo > 0 and parc_datapagamento >= to_date(concat('01','01',to_char(now(),'YYYY')),'DDMMYYY'))as tab order by to_char");
        
        try
        {
            while(rs != null && rs.next())            
            {
                ret.add(new Objeto(String.valueOf(rs.getDouble("pag_valor")), rs.getString("to_char")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return ret;
    }
}
