/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import estagio.utilidades.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Despesa
{
    private int codigo;
    private String nome;
    private boolean fixo;
    private double valor;
    private Date vencimento;
    private String descricao;
    private Transporte transporte;

    public Despesa()
    {
    }

    public Despesa(int codigo, String nome, boolean fixo, double valor, Date vencimento, String descricao, Transporte transporte)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.fixo = fixo;
        this.valor = valor;
        this.vencimento = vencimento;
        this.descricao = descricao;
        this.transporte = transporte;
    }
    
    public Despesa(int codigo, String nome, boolean fixo, double valor, Date vencimento, String descricao)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.fixo = fixo;
        this.valor = valor;
        this.vencimento = vencimento;
        this.descricao = descricao;
    }

    public Despesa(String nome, boolean fixo, double valor, Date vencimento, String descricao, Transporte transporte)
    {
        this.nome = nome;
        this.fixo = fixo;
        this.valor = valor;
        this.vencimento = vencimento;
        this.descricao = descricao;
        this.transporte = transporte;
    }

    public Despesa(String nome, boolean fixo, double valor, Date vencimento, String descricao)
    {
        this.nome = nome;
        this.fixo = fixo;
        this.valor = valor;
        this.vencimento = vencimento;
        this.descricao = descricao;
    }

    public Despesa(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Despesa(String nome)
    {
        this.nome = nome;
        get();
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public boolean isFixo()
    {
        return fixo;
    }

    public void setFixo(boolean fixo)
    {
        this.fixo = fixo;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public Date getVencimento()
    {
        return vencimento;
    }

    public void setVencimento(Date vencimento)
    {
        this.vencimento = vencimento;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Transporte getTransporte()
    {
        return transporte;
    }

    public void setTransporte(Transporte transporte)
    {
        this.transporte = transporte;
    }

    private void get()
    {
        ResultSet rs;
        if(this.nome == null) 
            rs = Banco.getCon().consultar("SELECT * FROM despesa WHERE desp_codigo = " + this.codigo);
        else
            rs = Banco.getCon().consultar("SELECT * FROM despesa WHERE desp_nome = '" + this.nome + "'");
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.codigo = rs.getInt("desp_codigo");
                this.nome = rs.getString("desp_nome");
                this.fixo = rs.getBoolean("desp_fixo");
                this.valor = rs.getDouble("desp_preco");
                this.vencimento = rs.getDate("desp_data_vencimento");
                //this.transporte = new Transporte(rs.getInt("trans_codigo"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
    }    

    public ArrayList<Despesa> ler(ResultSet rs)
    {
        ArrayList<Despesa>despesas = new ArrayList<>();
        Despesa despesa;
        
        try
        {
            while(rs != null && rs.next())   
            {
                despesa = new Despesa(rs.getInt("desp_codigo"), rs.getString("desp_nome"), rs.getBoolean("desp_fixo"), 
                    rs.getDouble("desp_preco"), rs.getDate("desp_data_vencimento"), rs.getString("desp_descricao"));
                
                if(rs.getInt("trans_codigo") > 0)
                    despesa.setTransporte(new Transporte(rs.getInt("trans_codigo")));
                despesas.add(despesa);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return despesas;
    }
    
    public ArrayList<Despesa> getByNome(String nome)
    {
        return ler(Banco.getCon().consultar("SELECT * FROM despesa WHERE desp_nome Ilike '%" + nome + "%'"));
    }

    public ArrayList<Despesa> getByNomeDistinct(String nome)
    {
        ArrayList<String>aux = new ArrayList<>();
        ArrayList<Despesa>despesas = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT DISTINCT desp_nome AS desp_nome FROM despesa WHERE "
            + "desp_nome Ilike '%" + nome + "%' GROUP BY desp_nome");
        
        try
        {
            while(rs != null && rs.next())            
                despesas.addAll(ler(Banco.getCon().consultar("SELECT * FROM despesa WHERE desp_codigo IN "
                    + "(SELECT max(desp_codigo) FROM despesa WHERE desp_nome = '" + rs.getString("desp_nome") + "')")));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        
        return despesas;
    }

    public ArrayList<Despesa> getByTransporte(int codigo)
    {
        return ler(Banco.getCon().consultar("SELECT * FROM despesa WHERE trans_codigo = " + codigo));
    }

    public boolean salvar()
    {
        String sql = "INSERT INTO despesa (desp_nome,desp_fixo,desp_preco,desp_data_vencimento,desp_descricao$7) "
            + "VALUES ('$1','$2',$3,'$4','$5'$6)";
        
        sql = sql.replace("$1", this.nome);
        sql = sql.replace("$2", String.valueOf(this.fixo));
        sql = sql.replace("$3", String.valueOf(this.valor));
        sql = sql.replace("$5", this.descricao);
        
        if(this.vencimento != null)
            sql = sql.replace("$4", String.valueOf(this.vencimento));
        else
            sql = sql.replace("'$4',", "").replace("desp_data_vencimento,", "");
        
        if(this.transporte != null)
            sql = sql.replace("$7", ",trans_codigo").replace("$6", "," + this.transporte.getCodigo());
        else
            sql = sql.replace("$7", "").replace("$6", "");
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar2(String... nome)
    {
        String sql;
        Connection con = Banco.getCon().getConnection();
        PreparedStatement st,st2 = null,st3 = null, st4;
        boolean flag = false;
        
        try
        {
            con.setAutoCommit(false);
            
            if(nome.length == 0)
            {
                st = con.prepareStatement("UPDATE despesa SET desp_nome = ?, desp_fixo = ?, desp_preco = ?, "
                + "desp_data_vencimento = ?, desp_descricao = ?, trans_codigo = ? WHERE desp_codigo = ?");
                st.setString(1, this.nome);
                st.setBoolean(2, this.fixo);
                st.setDouble(3, this.valor);
                st.setString(5, this.descricao);
                st.setInt(7, this.codigo);
                
                if(this.vencimento != null)
                    st.setDate(4, this.vencimento);
                else
                    st.setNull(4, Types.DATE);
                
                if(this.transporte != null)
                    st.setInt(6, this.transporte.getCodigo());
                else
                    st.setNull(6, Types.INTEGER);
                
                if(flag = st.executeUpdate() > 0)
                    con.commit();
                else
                    con.rollback();
            }
            
            else
            {
                st = con.prepareStatement("UPDATE despesa SET desp_data_vencimento = ? WHERE desp_codigo = ?");
                st.setDate(1, this.vencimento);
                st.setInt(2, this.codigo);
                
                sql = "UPDATE despesa SET desp_nome = '$1', desp_fixo = '$2', desp_preco = $3, "
                    + " desp_descricao = '$5' $6 WHERE desp_nome = '" + nome[0] + "'";

                sql = sql.replace("$1", this.nome);
                sql = sql.replace("$2", String.valueOf(this.fixo));
                sql = sql.replace("$3", String.valueOf(this.valor));
                sql = sql.replace("$5", this.descricao);

                if(this.transporte != null)
                    sql = sql.replace("$6", ", trans_codigo = " + this.transporte.getCodigo());
                else
                    sql = sql.replace("$6", "");
                
                st2 = con.prepareStatement(sql);
                
                if(flag = st.executeUpdate() == 1 && (st2 != null ? st2.executeUpdate() > 0 : true))
                {
                    con.commit();
                    if(gerarDespesas(con))
                        con.commit();
                    else
                        con.rollback();
                }
                else
                    con.rollback();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return flag;
    }

    private boolean gerarDespesas(Connection con)
    {
        boolean flag = true;
        try
        {
            PreparedStatement st,st2,st3;
            
            st = con.prepareStatement("select desp_nome, min(desp_data_vencimento),max(desp_data_vencimento) "
                    + "from despesa where desp_nome = 'teste' group by desp_nome");
            
            st2 = con.prepareStatement("select * from despesa where desp_nome = ? and desp_fixo = ? and "
                + "desp_preco = ? and desp_descricao = ? and desp_data_vencimento = ?");
            st2.setString(1, this.nome);
            st2.setBoolean(2, fixo);
            st2.setDouble(3, valor);
            st2.setString(4, descricao);
            
            ResultSet rs = st.executeQuery();
            rs.next();
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(rs.getDate("max"));
            int max = cal.get(Calendar.MONTH);
            
            cal.setTime(rs.getDate("min"));
            int min = cal.get(Calendar.MONTH);
            cal.add(Calendar.MONTH, 1);
            java.sql.Date aux = Utils.convertToSqlDate(Utils.convertCalendarToLocalDate(cal));
            
            
            for (int i = min + 1, j = 1; i < max && flag; i++, j++)
            {
                st2.setDate(5, aux);
                rs = st2.executeQuery();
                
                if(!rs.next())
                {
                    st3 = con.prepareStatement("INSERT INTO despesa(desp_nome,desp_fixo,desp_preco,"
                        + "desp_data_vencimento,desp_descricao, trans_codigo) VALUES(?,?,?,?,?,?)");
                    st3.setString(1, nome);
                    st3.setBoolean(2, fixo);
                    st3.setDouble(3, valor);
                    st3.setDate(4, aux);
                    st3.setString(5, descricao);
                    
                    if(this.transporte != null)
                        st3.setInt(6, transporte.getCodigo());
                    else
                        st3.setNull(6, Types.INTEGER);
                    flag = st3.executeUpdate() == 1;
                }
                cal.add(Calendar.MONTH, 1);
                aux =  Utils.convertToSqlDate(Utils.convertCalendarToLocalDate(cal));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean alterar()
    {
        String sql = "UPDATE despesa SET desp_nome = '$1', desp_fixo = '$2', desp_preco = $3, "
                + "desp_data_vencimento = '$4', desp_descricao = '$5'$6 WHERE desp_codigo = " + this.codigo;
        
        sql = sql.replace("$1", this.nome);
        sql = sql.replace("$2", String.valueOf(this.fixo));
        sql = sql.replace("$3", String.valueOf(this.valor));
        sql = sql.replace("$5", this.descricao);
        
        if(this.vencimento != null)
            sql = sql.replace("$4", String.valueOf(this.vencimento));
        else
            sql = sql.replace("desp_data_vencimento = '$4',", "");
        
        if(this.transporte != null)
            sql = sql.replace("$6", ", trans_codigo = " + this.transporte.getCodigo());
        else
            sql = sql.replace("$6", "");
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(String nome_antigo)
    {
        String sql2 = "UPDATE despesa SET desp_data_vencimento = '$4' WHERE desp_codigo = " + this.codigo;
        
        String sql = "UPDATE despesa SET desp_nome = '$1', desp_fixo = '$2', desp_preco = $3, "
            + "desp_descricao = '$5'$6 WHERE desp_nome = '" + nome_antigo + "'";
        
        sql = sql.replace("$1", this.nome);
        sql = sql.replace("$2", String.valueOf(this.fixo));
        sql = sql.replace("$3", String.valueOf(this.valor));
        sql = sql.replace("$5", this.descricao);
        sql2 = sql2.replace("$4", String.valueOf(this.vencimento));
        
        if(this.transporte != null)
            sql = sql.replace("$6", ", trans_codigo = " + this.transporte.getCodigo());
        else
            sql = sql.replace("$6", "");
        
        if(validaMes())
            return Banco.getCon().manipular(sql) && Banco.getCon().manipular(sql2);
        return Banco.getCon().manipular(sql) && Banco.getCon().manipular("DELETE FROM despesa WHERE desp_codigo = "
            + this.codigo);
    }

    public boolean apagar()
    {
        if(this.nome == null)
            return Banco.getCon().manipular("DELETE FROM despesa WHERE desp_codigo = "+ this.codigo);
        else
            return Banco.getCon().manipular("DELETE FROM despesa WHERE desp_nome = '" + this.nome + "'");
    }

    public ArrayList<Despesa> getFixos()
    {
        ArrayList<Despesa> despesas = new ArrayList<>();
        String sql = "SELECT MAX(desp_data_vencimento) AS desp_data_vencimento, desp_nome "
            + "FROM despesa WHERE desp_nome IN (SELECT DISTINCT desp_nome FROM despesa WHERE desp_fixo = 'true') "
                + "GROUP BY desp_nome";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            while(rs != null && rs.next())            
                despesas.add(new Despesa(rs.getString("desp_nome")));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return despesas;
    }

    public int count(String nome)
    {
        ResultSet rs = Banco.getCon().consultar("SELECT count(desp_nome) FROM despesa WHERE desp_nome = '" + nome + "'");
        
        try
        {
            if(rs != null && rs.next())
                return rs.getInt("count");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return 0;
    }

    public int getMinMonthByName(String outro)
    {
        String sql = "SELECT MIN(desp_data_vencimento) AS desp_data_vencimento FROM despesa "
                + "WHERE desp_nome = '" + outro + "'";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs != null && rs.next()) 
                return rs.getDate("desp_data_vencimento").toLocalDate().getMonthValue();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private boolean validaMes()
    {
        String sql = "" + this.vencimento.toLocalDate().getMonthValue();
        
        if(this.vencimento.toLocalDate().getMonthValue() < 10)
            sql = "0" + sql;
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM despesa WHERE to_char(desp_data_vencimento, 'MM') = '" 
            + sql + "' AND desp_nome = '" + this.nome + "'");
        
        try
        {
            if(rs != null && rs.next())            
                return false;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return true;
    }

    public ArrayList<Integer> getMonthsByName(String nome)
    {
        ArrayList<Integer>meses = new ArrayList<>();
        ResultSet rs = Banco.getCon().consultar("SELECT to_char(desp_data_vencimento,'MM') FROM despesa WHERE "
            + "desp_nome = '" + nome + "' ORDER BY to_char");
        
        try
        {
            while(rs != null && rs.next())            
                meses.add(Integer.parseInt(rs.getString("to_char")));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return meses;
    }

    public boolean paga(int codigo)
    {
        String sql = "SELECT pag_codigo FROM despesa INNER JOIN pagamento "
            + "ON despesa.desp_codigo = pagamento.desp_codigo WHERE pag_codigo IS NOT NULL "
                + "AND despesa.desp_codigo = " + codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            if(rs != null && rs.next())            
                return rs.getInt("pag_codigo") > 0;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Despesa.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return false;
    }
    
}
