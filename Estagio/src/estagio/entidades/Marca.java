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
public class Marca
{
    private int codigo;
    private String nome;

    public Marca()
    {
    }

    public Marca(int codigo, String nome)
    {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Marca(String nome)
    {
        this.nome = nome;
        get();
    }

    public Marca(int codigo)
    {
        this.codigo = codigo;
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

    private void get()
    {
        String sql = "SELECT * FROM marca WHERE ";
        
        if(this.nome != null)
            sql += "marca_nome = '" + this.nome + "'";
        else
            sql += "marca_codigo = " + this.codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.codigo = rs.getInt("marca_codigo");
                this.nome = rs.getString("marca_nome");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
    }

    public boolean salvar()
    {
        String sql = "INSERT INTO marca(marca_codigo,marca_nome) VALUES($1,'$2')";
        sql = sql.replace("$2", this.nome);
        sql = sql.replace("$1", String.valueOf(Banco.getCon().getMaxPK("marca", "marca_codigo") + 1));
        
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar()
    {
        String sql = "UPDATE marca SET marca_nome = '$2' WHERE marca_codigo = $1";
        sql = sql.replace("$2", this.nome);
        sql = sql.replace("$1", String.valueOf(this.codigo));
        
        return Banco.getCon().manipular(sql);
    }

    public boolean apagar(int... codigo)
    {
        int cod;
        if(codigo.length > 0)
            cod = codigo[0];
        else
            cod = this.codigo;
        
        Banco.getCon().manipular("DELETE FROM modelo WHERE marca_codigo = " + cod);
        return Banco.getCon().manipular("DELETE FROM marca WHERE marca_codigo = " + cod);
    }

    public ArrayList<Marca> getAll()
    {
        ArrayList<Marca>marcas = new ArrayList<>();
        
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM marca");
        
        try
        {
            while(rs != null & rs.next())            
                marcas.add(new Marca(rs.getInt("marca_codigo"), rs.getString("marca_nome")));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Marca.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return marcas;
    }
}
