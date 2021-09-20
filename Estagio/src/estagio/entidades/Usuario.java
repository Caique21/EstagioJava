/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Usuario
{
    private int codigo;
    private Funcionario funcionario;
    private String nome;
    private String senha;
    private String nivel;
    private boolean ativo;

    public Usuario()
    {
    }

    public Usuario(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Usuario(Funcionario funcionario)
    {
        this.funcionario = funcionario;
        get();
    }

    public Usuario(String nome)
    {
        this.nome = nome;
        get();
    }

    public Usuario(String nome, String senha)
    {
        this.nome = nome;
        this.senha = senha;
        get();
    }

    public Usuario(int codigo, Funcionario funcionario, String nome, String senha, String nivel, boolean ativo)
    {
        this.codigo = codigo;
        this.funcionario = funcionario;
        this.nome = nome;
        this.senha = senha;
        this.nivel = nivel;
        this.ativo = ativo;
    }

    public Usuario(int codigo, Funcionario funcionario, String nome, String senha, String nivel)
    {
        this.codigo = codigo;
        this.funcionario = funcionario;
        this.nome = nome;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario(Funcionario funcionario, String nome, String senha, String nivel, boolean ativo)
    {
        this.funcionario = funcionario;
        this.nome = nome;
        this.senha = senha;
        this.nivel = nivel;
        this.ativo = ativo;
    }

    public Usuario(Funcionario funcionario, String nome, String senha, String nivel)
    {
        this.funcionario = funcionario;
        this.nome = nome;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario(int codigo, String nome, String senha, String nivel)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.senha = senha;
        this.nivel = nivel;
        
        getFunc();
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public Funcionario getFuncionario()
    {
        return funcionario;
    }

    private void getFunc()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT func_codigo FROM usuario WHERE user_codigo = " + 
            this.codigo + " AND func_codigo IS NOT NULL");
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.funcionario = new Funcionario(rs.getInt("func_codigo"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFuncionario(Funcionario funcionario)
    {
        this.funcionario = funcionario;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public String getNivel()
    {
        return nivel;
    }

    public void setNivel(String nivel)
    {
        this.nivel = nivel;
    }

    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 73 * hash + this.codigo;
        hash = 73 * hash + Objects.hashCode(this.funcionario);
        hash = 73 * hash + Objects.hashCode(this.nome);
        hash = 73 * hash + Objects.hashCode(this.nivel);
        hash = 73 * hash + (this.ativo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.ativo != other.ativo)
        {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome))
        {
            return false;
        }
        if (!Objects.equals(this.nivel, other.nivel))
        {
            return false;
        }
        if (!Objects.equals(this.funcionario, other.funcionario))
        {
            return false;
        }
        return true;
    }

    public boolean salvar()
    {
        String sql = "INSERT INTO usuario (func_codigo,user_nome, user_senha, user_nivel, user_ativo) "
                + "values ($1,'$2','$3','$4','$5')";
        
        sql = sql.replace("$1", String.valueOf(funcionario.getCodigo()));
        sql = sql.replace("$2", nome);
        sql = sql.replace("$3", senha);
        sql = sql.replace("$4", nivel);
        sql = sql.replace("$5", String.valueOf(true));
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar()
    {
        String sql = "UPDATE usuario SET func_codigo = $2, user_nome = '$3', user_senha = '$4', user_nivel = '$5'"
                + " WHERE user_codigo = $1";
        
        sql = sql.replace("$1", String.valueOf(codigo));
        sql = sql.replace("$2", String.valueOf(funcionario.getCodigo()));
        sql = sql.replace("$3", nome);
        sql = sql.replace("$4", senha);
        sql = sql.replace("$5", nivel);
        
        return Banco.getCon().manipular(sql);
    }

    public boolean apagar()
    {
        return Banco.getCon().manipular("UPDATE usuario SET user_ativo = 'false' WHERE user_codigo = " + codigo);
    }

    public boolean apagar_fisico()
    {
        return Banco.getCon().manipular("DELETE FROM usuario WHERE user_codigo = " + this.codigo);
    }
    
    private void get()
    {
        String sql = "";
        ResultSet rs;
        if(funcionario != null)
            sql = "SELECT * from usuario WHERE func_codigo = " + this.funcionario.getCodigo();
        else if(nome != null && !nome.equals("") && senha != null && !senha.equals(""))
            sql = "SELECT * from usuario WHERE user_nome = '" + this.nome + 
                    "' AND user_senha = '" + this.senha + "'";
        else if(nome != null && !nome.equals(""))
            sql = "SELECT * from usuario WHERE user_nome = '" + this.nome + "'";
        else
            sql = "SELECT * from usuario WHERE user_codigo = " + this.codigo;
        
        rs = Banco.getCon().consultar(sql);
        try
        {
            if(rs != null && rs.next())            
            {
                this.codigo = rs.getInt("user_codigo");
                this.nome = rs.getString("user_nome");
                if(this.funcionario == null && !this.nome.equals("admin"))
                    this.funcionario = new Funcionario(rs.getInt("func_codigo"));
                this.senha = rs.getString("user_senha");
                this.nivel = rs.getString("user_nivel");
                this.ativo = rs.getBoolean("user_ativo");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<Usuario>ler(ResultSet rs)
    {
        ArrayList<Usuario>usuarios = new ArrayList<>();
        try
        {
            while(rs != null && rs.next())            
            {
                usuarios.add(new Usuario(rs.getInt("user_codigo"), new Funcionario(rs.getInt("func_codigo")), 
                    rs.getString("user_nome"), rs.getString("user_senha"), rs.getString("user_nivel"), 
                        rs.getBoolean("user_ativo")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    

    public boolean isUsuario(String nome)
    {
        return !ler(Banco.getCon().consultar("SELECT * FROM usuario WHERE user_nome = '" + nome + "'"
                + " AND user_ativo = true")).isEmpty();
    }

    public boolean matchPassword(String nome, String senha)
    {
        return !ler(Banco.getCon().consultar("SELECT * FROM usuario WHERE user_nome = '" + nome + "'"
                + " AND user_senha = '" + senha + "'")).isEmpty();
    }

    public boolean inativar()
    {
        return funcionario != null? Banco.getCon().manipular("UPDATE usuario SET user_ativo = 'false' "
            + "WHERE func_codigo = " + this.funcionario.getCodigo()) : false;
    }
}
