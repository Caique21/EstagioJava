/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Acesso
{
    private int codigo;
    private Timestamp login;
    private Timestamp logout;
    private Usuario usuario;

    public Acesso()
    {
    }

    public Acesso(int codigo)
    {
        this.codigo = codigo;
    }

    public Acesso(Timestamp login, Timestamp logout, Usuario usuario)
    {
        this.login = login;
        this.logout = logout;
        this.usuario = usuario;
    }

    public Acesso(int codigo, Timestamp login, Timestamp logout, Usuario usuario)
    {
        this.codigo = codigo;
        this.login = login;
        this.logout = logout;
        this.usuario = usuario;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public Timestamp getLogin()
    {
        return login;
    }

    public void setLogin(Timestamp login)
    {
        this.login = login;
    }

    public Timestamp getLogout()
    {
        return logout;
    }

    public void setLogout(Timestamp logout)
    {
        this.logout = logout;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }
    
    public boolean salvar()
    {
        String sql = "insert into acessos(acess_data_login,acess_data_logout,user_codigo) values ('$1','$2',$3)";
        
        sql = sql.replace("$1", String.valueOf(login));
        sql = sql.replace("$2", String.valueOf(logout));
        sql = sql.replace("$3", String.valueOf(usuario.getCodigo()));
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagarUsuario(int cod)
    {
        String sql = "delete from acessos where user_codigo = " + cod;
        
        return Banco.getCon().manipular(sql);
    }
    
    public Acesso getAcesso(int codigo)
    {
        ResultSet rs = Banco.getCon().consultar("select * from acessos where acess_codigo = " + codigo);
        
        try
        {
            if(rs != null && rs.next())            
            {
                return new Acesso(codigo, rs.getTimestamp("acess_data_login"), 
                        rs.getTimestamp("acess_data_logout"), new Usuario(rs.getInt("user_codigo")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Acesso> get(String data)
    {
        ArrayList<Acesso>acessos = new ArrayList<>();
        
        ResultSet rs = Banco.getCon().consultar("select * from acessos where DATE(acess_data_login) = '" + data + "'");
        
        try
        {
            while(rs != null && rs.next())            
            {
                acessos.add(new Acesso(rs.getInt("acess_codigo"), rs.getTimestamp("acess_data_login"), 
                        rs.getTimestamp("acess_data_logout"), new Usuario(rs.getInt("user_codigo"))));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acessos;
    }
    
    public ArrayList<Acesso> getAcessoUsuario(int cod)
    {
        ArrayList<Acesso>acessos = new ArrayList<>();
        
        ResultSet rs = Banco.getCon().consultar("select * from acessos where user_codigo = " + cod);
        
        try
        {
            while(rs != null && rs.next())            
            {
                acessos.add(new Acesso(rs.getInt("acess_codigo"), rs.getTimestamp("acess_data_login"), 
                        rs.getTimestamp("acess_data_logout"), new Usuario(rs.getInt("user_codigo"))));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acessos;
    }
}
