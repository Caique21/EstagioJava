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
public class Fornecedor
{
    private int codigo;
    private String nome;
    private String cnpj;
    private String email;
    private boolean ativo;
    private Timestamp alteracao;
    private Endereco endereco;
    private String endereco_completo;

    public Fornecedor()
    {
    }

    public Fornecedor(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Fornecedor(String nome_cnpj, boolean nome)
    {
        if(nome)
            this.nome = nome_cnpj;
        else
            this.cnpj = nome_cnpj;
        get();
    }

    public Fornecedor(int codigo, String nome, String cnpj, String email, boolean ativo, Timestamp alteracao, Endereco endereco)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.ativo = ativo;
        this.alteracao = alteracao;
        this.endereco = endereco;
        this.endereco_completo = this.endereco.toString();
    }

    public Fornecedor(String nome, String cnpj, String email, boolean ativo, Timestamp alteracao, Endereco endereco)
    {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.ativo = ativo;
        this.alteracao = alteracao;
        this.endereco = endereco;
        this.endereco_completo = this.endereco.toString();
    }

    public Fornecedor(String nome, String cnpj, String email, Timestamp alteracao, Endereco endereco)
    {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.alteracao = alteracao;
        this.endereco = endereco;
        this.endereco_completo = this.endereco.toString();
    }
    
    public Fornecedor(int codigo,String nome, String cnpj, String email, Endereco endereco)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.endereco = endereco;
        this.endereco_completo = this.endereco.toString();
    }

    public Fornecedor(String nome, String cnpj, String email, Endereco endereco)
    {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.endereco = endereco;
        this.endereco_completo = this.endereco.toString();
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

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }

    public Timestamp getAlteracao()
    {
        return alteracao;
    }

    public void setAlteracao(Timestamp alteracao)
    {
        this.alteracao = alteracao;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    public String getEndereco_completo()
    {
        return endereco_completo;
    }

    public void setEndereco_completo(String endereco_completo)
    {
        this.endereco_completo = endereco_completo;
    }

    private void get()
    {
        String sql = "SELECT * FROM fornecedor WHERE ";
        
        if(this.nome != null)
            sql += "forn_nome = '" + this.nome + "'";
        else if(this.cnpj != null)
            sql += "forn_cnpj = '" + this.cnpj + "'";
        else
            sql += "forn_codigo = " + this.codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.codigo = rs.getInt("forn_codigo");
                this.alteracao = rs.getTimestamp("forn_alteracao");
                this.ativo = rs.getBoolean("forn_ativo");
                this.cnpj = rs.getString("forn_cnpj");
                this.email = rs.getString("forn_email");
                this.endereco = new Endereco(rs.getInt("ender_codigo"));
                this.endereco_completo = this.endereco.toString();
                this.nome = rs.getString("forn_nome");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean salvar()
    {
        String sql = "INSERT INTO fornecedor(forn_nome, forn_cnpj, forn_email, forn_ativo, forn_alteracao, "
            + "ender_codigo) VALUES ('$1','$2','$3','$4','$5',$6)";
        
        sql = sql.replace("$1", this.nome);
        sql = sql.replace("$2", this.cnpj);
        sql = sql.replace("$3", this.email);
        sql = sql.replace("$4", String.valueOf(true));
        sql = sql.replace("$5", String.valueOf(new Timestamp(new java.util.Date().getTime())));
        sql = sql.replace("$6", String.valueOf(this.endereco.getCodigo()));
        
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar()
    {
        String sql = "UPDATE fornecedor SET forn_nome = '$2',forn_cnpj = '$3', forn_email = '$4', forn_ativo = '$5',"
            + "forn_alteracao = '$6', ender_codigo = $7 WHERE forn_codigo = $1";
        
        sql = sql.replace("$1", String.valueOf(this.codigo));
        sql = sql.replace("$2", this.nome);
        sql = sql.replace("$3", this.cnpj);
        sql = sql.replace("$4", this.email);
        sql = sql.replace("$5", String.valueOf(this.ativo));
        sql = sql.replace("$6", String.valueOf(new Timestamp(new java.util.Date().getTime())));
        sql = sql.replace("$7", String.valueOf(this.endereco.getCodigo()));
        
        return Banco.getCon().manipular(sql);
    }

    public boolean inativar(int... codigo)
    {
        if(codigo.length > 0)
            return Banco.getCon().manipular("UPDATE fornecedor SET forn_ativo = 'false' WHERE forn_codigo = " + codigo[0]);
        else
            return Banco.getCon().manipular("UPDATE fornecedor SET forn_ativo = 'false' WHERE forn_codigo = " + this.codigo);
    }
    
    private ArrayList<Fornecedor> ler(ResultSet rs)
    {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        
        try
        {
            while(rs != null && rs.next())            
                fornecedores.add(new Fornecedor(rs.getInt("forn_codigo"), rs.getString("forn_nome"), 
                    rs.getString("forn_cnpj"), rs.getString("forn_email"), rs.getBoolean("forn_ativo"), 
                        rs.getTimestamp("forn_alteracao"), new Endereco(rs.getInt("ender_codigo"))));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fornecedores;
    }

    public ArrayList<Fornecedor> getAll()
    {
        return ler(Banco.getCon().consultar("SELECT * FROM fornecedor"));
    }

    public ArrayList<Fornecedor> getByName(String nome)
    {
        return ler(Banco.getCon().consultar("SELECT * FROM fornecedor WHERE forn_nome Ilike '%" + nome + "%'"));
    }

    public ArrayList<Fornecedor> getByCNPJ(String cnpj)
    {
        return ler(Banco.getCon().consultar("SELECT * FROM fornecedor WHERE forn_cnpj = '" + cnpj + "'"));
    }

    public ArrayList<String> getTelefones(int... codigo)
    {
        String sql = "SELECT tel_numero FROM telefone WHERE forn_codigo = ";
        ArrayList<String> telefones = new ArrayList<>();
        
        if(codigo.length > 0)
            sql += codigo[0];
        else
            sql += this.codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while(rs != null && rs.next())            
                telefones.add(rs.getString("tel_numero"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Fornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return telefones;
    }
    
    
}
