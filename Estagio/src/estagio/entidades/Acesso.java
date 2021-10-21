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
        
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM acessos WHERE DATE(acess_data_login) = '" + data + "'"
            + " AND user_codigo <> 1");
        
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

    public Acesso get(Timestamp ts)
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM acessos WHERE timestamp '" + ts + "' >= "
             + "acess_data_login AND timestamp '" + ts + "' <= acess_data_logout AND user_codigo <> 1");
        
        try
        {
            if(rs != null && rs.next())
                return new Acesso(rs.getInt("acess_codigo"), rs.getTimestamp("acess_data_login"), 
                        rs.getTimestamp("acess_data_logout"), new Usuario(rs.getInt("user_codigo")));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    public ArrayList<Acesso> getAcessosUsuario(int cod)
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

    public Acesso getLastAcess()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM acessos WHERE acess_data_logout IN "
                + "(SELECT MAX(acess_data_logout) as acess_data_logout FROM acessos)");
        
        try
        {
            if(rs != null && rs.next())            
                return new Acesso(rs.getInt("acess_codigo"), rs.getTimestamp("acess_data_login"), 
                        rs.getTimestamp("acess_data_logout"), new Usuario(rs.getInt("user_codigo")));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return null;
    }
}
