/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import estagio.utilidades.Utils;
import java.sql.Date;
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
        
        try
        {
            while(rs != null && rs.next())            
                despesas.add(new Despesa(rs.getInt("desp_codigo"), rs.getString("desp_nome"), rs.getBoolean("desp_fixo"), 
                    rs.getDouble("desp_preco"), rs.getDate("desp_data_vencimento"), rs.getString("desp_descricao")));
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

    public boolean salvar()
    {
        String sql = "INSERT INTO despesa (desp_nome,desp_fixo,desp_preco,desp_data_vencimento,desp_descricao) "
            + "VALUES ('$1','$2',$3,'$4','$5')";
        
        sql = sql.replace("$1", this.nome);
        sql = sql.replace("$2", String.valueOf(this.fixo));
        sql = sql.replace("$3", String.valueOf(this.valor));
        sql = sql.replace("$4", String.valueOf(this.vencimento));
        sql = sql.replace("$5", this.descricao);
        
        /*if(this.fixo)
        {
            for (int i = 1; i < 12 - LocalDate.now().getMonthValue(); i++)
            {
                sql += ",('$1','$2',$3,'$4','$5')";
                
                sql = sql.replace("$1", this.nome);
                sql = sql.replace("$2", String.valueOf(this.fixo));
                sql = sql.replace("$3", String.valueOf(this.valor));
                sql = sql.replace("$4", String.valueOf(this.vencimento));
                sql = sql.replace("$5", this.descricao);
            }
        }*/
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar()
    {
        String sql = "UPDATE despesa SET desp_nome = '$1', desp_fixo = '$2', desp_preco = $3, "
                + "desp_data_vencimento = '$4', desp_descricao = '$5' WHERE desp_codigo = " + this.codigo;
        
        sql = sql.replace("$1", this.nome);
        sql = sql.replace("$2", String.valueOf(this.fixo));
        sql = sql.replace("$3", String.valueOf(this.valor));
        sql = sql.replace("$4", String.valueOf(this.vencimento));
        sql = sql.replace("$5", this.descricao);
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(String nome_antigo)
    {
        String sql2 = "UPDATE despesa SET desp_data_vencimento = '$4' WHERE desp_codigo = " + this.codigo;
        
        String sql = "UPDATE despesa SET desp_nome = '$1', desp_fixo = '$2', desp_preco = $3, "
            + "desp_descricao = '$5' WHERE desp_nome = '" + nome_antigo + "'";
        
        sql = sql.replace("$1", this.nome);
        sql = sql.replace("$2", String.valueOf(this.fixo));
        sql = sql.replace("$3", String.valueOf(this.valor));
        sql = sql.replace("$5", this.descricao);
        sql2 = sql2.replace("$4", String.valueOf(this.vencimento));
        
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
    
}
