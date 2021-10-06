/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Modelo
{
    private int codigo;
    private String nome;
    private Marca marca;

    public Modelo()
    {
    }

    public Modelo(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Modelo(String nome)
    {
        this.nome = nome;
        get();
    }

    public Modelo(Marca marca)
    {
        this.marca = marca;
        get();
    }

    public Modelo(String nome, Marca marca)
    {
        this.nome = nome;
        this.marca = marca;
    }

    public Modelo(int codigo, String nome, Marca marca)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
    }

    public Modelo(int codigo, String nome)
    {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public void setCodigo()
    {
        String sql = "SELECT modelo_codigo FROM modelo WHERE ";
        if(this.marca != null && this.nome != null)
            sql += "marca_codigo = " + this.marca.getCodigo() + " AND modelo_nome = '" + this.nome + "'";
        else if(this.marca != null)
            sql += "marca_codigo = " + this.marca.getCodigo();
        else if(this.nome != null)
            sql += "modelo_nome = '" + this.nome + "'";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.codigo = rs.getInt("modelo_codigo");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Marca getMarca()
    {
        return marca;
    }

    public void setMarca(Marca marca)
    {
        this.marca = marca;
    }

    private void get()
    {
        String sql = "SELECT * FROM modelo WHERE ";
        
        if(this.marca != null)
            sql += "marca_codigo = " + this.marca.getCodigo();
        else if(this.nome != null)
            sql += "modelo_nome = '" + this.nome + "'";
        else
            sql += "modelo_codigo = " + this.codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.codigo = rs.getInt("modelo_codigo");
                this.nome = rs.getString("modelo_nome");
                this.marca = new Marca(rs.getInt("marca_codigo"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean salvar()
    {
        String sql = "INSERT INTO modelo(modelo_codigo, modelo_nome, marca_codigo) VALUES($1,'$2',$3)";
        
        sql = sql.replace("$1", String.valueOf(Banco.getCon().getMaxPK("modelo", "modelo_codigo") + 1));
        sql = sql.replace("$2", this.nome);
        sql = sql.replace("$3", String.valueOf(this.marca.getCodigo()));
        
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar()
    {
        String sql = "UPDATE modelo SET modelo_nome = '$2' ";
        sql = sql.replace("$2", this.nome);
        
        if(this.marca != null)
            sql += ", marca_codigo = " + this.marca.getCodigo() + " ";
        
        sql += "WHERE modelo_codigo = " + this.codigo;
        
        return Banco.getCon().manipular(sql);
    }

    public boolean apagar(int... codigo)
    {
        if(codigo.length > 0)
            return Banco.getCon().manipular("DELETE FROM modelo WHERE modelo_codigo = " + codigo[0]);
        return Banco.getCon().manipular("DELETE FROM modelo WHERE modelo_codigo = " + this.codigo);
    }

    public ArrayList<Objeto> getAllMerged()
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        
        ResultSet rs = Banco.getCon().consultar("SELECT marca.marca_codigo,marca_nome, modelo_codigo, modelo_nome "
                + "FROM marca LEFT JOIN modelo ON marca.marca_codigo = modelo.marca_codigo ORDER BY marca_nome");
        
        try
        {
            while(rs != null && rs.next())            
            {
                Objeto o = new Objeto();
                o.setParam1(String.valueOf(rs.getInt("marca_codigo")));
                o.setParam2(rs.getString("marca_nome"));
                
                o.setParam3(String.valueOf(rs.getInt("modelo_codigo")));
                o.setParam4(rs.getString("modelo_nome"));
                ret.add(o);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return ret;
    }

    public ArrayList<Objeto> get(String marca, String...modelo)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        
        String sql = "SELECT marca.marca_codigo,marca_nome, modelo_codigo, modelo_nome FROM marca "
                + "INNER JOIN modelo ON marca.marca_codigo = modelo.marca_codigo AND marca_nome Ilike '%" + marca + "%'";
        
        if(modelo.length > 0)
            sql += " AND modelo_nome Ilike '%" + modelo[0] + "%'";
        
        sql += " order by marca_nome";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            while(rs != null && rs.next())            
            {
                Objeto o = new Objeto();
                o.setParam1(String.valueOf(rs.getInt("marca_codigo")));
                o.setParam2(rs.getString("marca_nome"));
                
                o.setParam3(String.valueOf(rs.getInt("modelo_codigo")));
                o.setParam4(rs.getString("modelo_nome"));
                ret.add(o);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return ret;
    }

    public ArrayList<Objeto> getByModelo(String nome)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        
        String sql = "SELECT marca.marca_codigo,marca_nome, modelo_codigo, modelo_nome FROM marca "
                + "INNER JOIN modelo ON marca.marca_codigo = modelo.marca_codigo AND modelo_nome Ilike '%" + nome + "%'"
                + " order by marca_nome";
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            while(rs != null && rs.next())            
            {
                Objeto o = new Objeto();
                o.setParam1(String.valueOf(rs.getInt("marca_codigo")));
                o.setParam2(rs.getString("marca_nome"));
                
                o.setParam3(String.valueOf(rs.getInt("modelo_codigo")));
                o.setParam4(rs.getString("modelo_nome"));
                ret.add(o);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return ret;
    }

    public ArrayList<Modelo> getByMarca(int marca)
    {
        ArrayList<Modelo>ret = new ArrayList<>();
        String sql = "SELECT * FROM modelo WHERE marca_codigo = " + marca;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            while(rs != null && rs.next())            
                ret.add(new Modelo(rs.getInt("modelo_codigo"), rs.getString("modelo_nome"), new Marca(marca)));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    
}
