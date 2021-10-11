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
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Carlos
 */
public class Cliente
{
    private int codigo;
    private String nome;
    private String cpf;
    private String rg;
    private Date data;
    private Endereco endereco;
    private String endereco_completo;
    private String email;
    private ArrayList<String>telefones;
    private boolean ativo;
    private Timestamp data_alteracao;

    public Cliente()
    {
    }

    public Cliente(int codigo)
    {
        this.codigo = codigo;
        this.telefones = new ArrayList<>();
        get();
    }

    public Cliente(String nome)
    {
        this.nome = nome;
        this.telefones = new ArrayList<>();
        get();
    }

    public Cliente(int codigo, String nome, String cpf, String rg, Date data, Endereco endereco, String email, boolean ativo, Timestamp data_alteracao)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data = data;
        this.endereco = endereco;
        this.email = email;
        this.ativo = ativo;
        this.data_alteracao = data_alteracao;
        this.endereco_completo = endereco.toString();
        this.telefones = new ArrayList<>();
        setTelefones(new Telefone().getAllByCliente(this));
    }

    public Cliente(int codigo, String nome, String cpf, String rg, Date data, Endereco endereco, String email, ArrayList<String> telefone, boolean ativo, Timestamp data_alteracao)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data = data;
        this.endereco = endereco;
        this.email = email;
        this.telefones = telefone;
        this.ativo = ativo;
        this.data_alteracao = data_alteracao;
        this.endereco_completo = endereco.toString();
    }

    public Cliente(String nome, String cpf, String rg, Date data, Endereco endereco, String email, ArrayList<String> telefone, boolean ativo, Timestamp data_alteracao)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data = data;
        this.endereco = endereco;
        this.email = email;
        this.telefones = telefone;
        this.ativo = ativo;
        this.data_alteracao = data_alteracao;
        this.endereco_completo = endereco.toString();
    }

    public Cliente(int codigo, String nome, String cpf, String rg, Date data, Endereco endereco, String email, ArrayList<String> telefone, Timestamp data_alteracao)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data = data;
        this.endereco = endereco;
        this.email = email;
        this.telefones = telefone;
        this.data_alteracao = data_alteracao;
        this.endereco_completo = endereco.toString();
    }

    public Cliente(String nome, String cpf, String rg, Date data, Endereco endereco, String email, ArrayList<String> telefone, Timestamp data_alteracao)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data = data;
        this.endereco = endereco;
        this.email = email;
        this.telefones = telefone;
        this.data_alteracao = data_alteracao;
        this.endereco_completo = endereco.toString();
    }
    
    public Cliente(String nome, String cpf, String rg, Date data, Endereco endereco, String email, ObservableList<String> telefone, Timestamp data_alteracao)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data = data;
        this.endereco = endereco;
        this.email = email;
        this.data_alteracao = data_alteracao;
        this.endereco_completo = endereco.toString();
        this.telefones = new ArrayList<>();
        
        telefone.forEach((tel) ->
        {
            telefones.add(tel);
        });
    }
    
    public Cliente(int codigo, String nome, String cpf, String rg, Date data, Endereco endereco, String email, ObservableList<String> telefone, Timestamp data_alteracao)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data = data;
        this.endereco = endereco;
        this.email = email;
        this.data_alteracao = data_alteracao;
        this.endereco_completo = endereco.toString();
        this.telefones = new ArrayList<>();
        
        telefone.forEach((tel) ->
        {
            telefones.add(tel);
        });
    }

    public Cliente(String nome, String cpf, String rg, Date data, Endereco endereco, String email, ArrayList<String> telefone)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.data = data;
        this.endereco = endereco;
        this.email = email;
        this.telefones = telefone;
        this.endereco_completo = endereco.toString();
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

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getRg()
    {
        return rg;
    }

    public void setRg(String rg)
    {
        this.rg = rg;
    }

    public ArrayList<String> getTelefones()
    {
        return telefones;
    }

    public void setTelefones(ArrayList<String> telefones)
    {
        this.telefones = telefones;
    }

    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public void addTelefone(String telefone)
    {
        if(this.telefones == null)
            this.telefones = new ArrayList<>();
        this.telefones.add(telefone);
    }

    public Timestamp getData_alteracao()
    {
        return data_alteracao;
    }

    public void setData_alteracao(Timestamp data_alteracao)
    {
        this.data_alteracao = data_alteracao;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + this.codigo;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.cpf);
        hash = 89 * hash + Objects.hashCode(this.rg);
        hash = 89 * hash + Objects.hashCode(this.data);
        hash = 89 * hash + Objects.hashCode(this.endereco);
        hash = 89 * hash + Objects.hashCode(this.endereco_completo);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.telefones);
        hash = 89 * hash + Objects.hashCode(this.data_alteracao);
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
        final Cliente other = (Cliente) obj;
        if (this.codigo != other.codigo)
        {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome))
        {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf))
        {
            return false;
        }
        if (!Objects.equals(this.rg, other.rg))
        {
            return false;
        }
        if (!Objects.equals(this.endereco_completo, other.endereco_completo))
        {
            return false;
        }
        if (!Objects.equals(this.email, other.email))
        {
            return false;
        }
        if (!Objects.equals(this.data, other.data))
        {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco))
        {
            return false;
        }
        if (!Objects.equals(this.telefones, other.telefones))
        {
            return false;
        }
        if (!Objects.equals(this.data_alteracao, other.data_alteracao))
        {
            return false;
        }
        return true;
    }
    
    public boolean salvar()
    {
        String sql = "INSERT INTO cliente(cli_nome,cli_cpf,cli_rg,cli_datacadastro,cli_email,cli_ativo,"
                + "cli_alteracao,ender_codigo) VALUES('$1','$2','$3','$4','$5','$6','$7',$8)";
        sql = sql.replace("$1", nome);
        sql = sql.replace("$2", cpf);
        sql = sql.replace("$3", rg);
        sql = sql.replace("$4", String.valueOf(data));
        sql = sql.replace("$5", email);
        sql = sql.replace("$6", String.valueOf(true));
        sql = sql.replace("$7", String.valueOf(data_alteracao));
        sql = sql.replace("$8", String.valueOf(endereco.getCodigo()));
        
        return Banco.getCon().manipular(sql) && salvarTelefones();
    }
    
    public boolean salvarTelefones()
    {
        boolean flag = true;
        String sql;
        for (String tel : telefones)
        {
            sql = "insert into telefone (tel_numero, cli_codigo) values('$1',$2)";
            sql = sql.replace("$1", tel.replace("(", "").replace(")", "").replace("-", ""));
            
            if(this.codigo > 0)
                sql = sql.replace("$2", String.valueOf(codigo));
            else
                sql = sql.replace("$2", String.valueOf(Banco.getCon().getMaxPK("cliente", "cli_codigo")));

            flag = flag && Banco.getCon().manipular(sql);
        }
        return flag;
    }
    
    public boolean alterar()
    {
        String sql = "UPDATE cliente SET cli_nome = '$2', cli_cpf = '$3', cli_rg = '$4', cli_datacadastro = '$5',"
            + "cli_email = '$6', cli_ativo = '$7', cli_alteracao = '$8', ender_codigo = $9 WHERE cli_codigo = $1";
        sql = sql.replace("$1", String.valueOf(codigo));
        sql = sql.replace("$2", nome);
        sql = sql.replace("$3", cpf);
        sql = sql.replace("$4", rg);
        sql = sql.replace("$5", String.valueOf(data));
        sql = sql.replace("$6", email);
        sql = sql.replace("$7", String.valueOf(true));
        sql = sql.replace("$8", String.valueOf(data_alteracao));
        sql = sql.replace("$9", String.valueOf(endereco.getCodigo()));
        
        return Banco.getCon().manipular(sql) && alteraTelefones();
    }

    private boolean alteraTelefones()
    {
        Banco.getCon().manipular("DELETE FROM telefone WHERE cli_codigo = " + this.codigo);
        return salvarTelefones();
    }
    
    public boolean tornarInativo()
    {
        return Banco.getCon().manipular("UPDATE cliente SET cli_ativo = 'false' WHERE cli_codigo = " + this.codigo);
    }
    
    public boolean apagar()
    {
        return Banco.getCon().manipular("DELETE FROM cliente WHERE cli_codigo = " + this.codigo);
    }

    private void get()
    {
        String sql = "SELECT * FROM cliente WHERE ";
        
        if(nome != null)
            sql += "cli_nome = '" + this.nome + "'";
        else
            sql += "cli_codigo = " + this.codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.codigo = rs.getInt("cli_codigo");
                this.nome = rs.getString("cli_nome");
                this.cpf = rs.getString("cli_cpf");
                this.rg = rs.getString("cli_rg");
                this.data = rs.getDate("cli_datacadastro");
                this.endereco = new Endereco(rs.getInt("ender_codigo"));
                this.endereco_completo = endereco.toString();
                this.email = rs.getString("cli_email");
                this.ativo = rs.getBoolean("cli_ativo");
                this.data_alteracao = rs.getTimestamp("cli_alteracao");
                setTelefones(new Telefone().getAllByCliente(this));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<Cliente> le(ResultSet rs)
    {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente;
        ResultSet rs2;
        try
        {
            while(rs != null && rs.next())            
            {
                Cliente cli = new Cliente(rs.getInt("cli_codigo"),rs.getString("cli_nome"),rs.getString("cli_cpf"), 
                    rs.getString("cli_rg"),rs.getDate("cli_datacadastro"),new Endereco(rs.getInt("ender_codigo")), 
                        rs.getString("cli_email"), rs.getBoolean("cli_ativo"), rs.getTimestamp("cli_alteracao"));
                
                rs2 = Banco.getCon().consultar("SELECT * FROM telefone WHERE cli_codigo = " + cli.getCodigo());
                while(rs2 != null && rs2.next())
                    cli.addTelefone(rs2.getString("tel_numero"));
                
                clientes.add(cli);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    public ArrayList<Cliente> getByName(String nome, boolean ativo)
    {
        return le(Banco.getCon().consultar("SELECT * FROM cliente WHERE cli_nome Ilike '%" + nome + "%' AND "
            + "cli_ativo = '" + ativo + "'"));
    }
    
    public Cliente getByCpf(String cpf)
    {
        String sql = "SELECT * FROM cliente WHERE cli_cpf = '" + cpf + "'";

        ResultSet rs = Banco.getCon().consultar(sql);
            
        try
        {
            if(rs != null && rs.next())
                return new Cliente(rs.getInt("cli_codigo"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public boolean restaurar(int... codigo)
    {
        String sql = "UPDATE cliente SET cli_ativo = 'true' WHERE cli_codigo = ";
        
        if(codigo.length == 0)
            sql += this.codigo;
        else
            sql += codigo[0];
        
        return Banco.getCon().manipular(sql);
    }

    public boolean apagar_fisico(int... codigo)
    {
        String sql = "DELETE FROM cliente WHERE cli_codigo = ";
        
        if(codigo.length == 0)
            sql += this.codigo;
        else
            sql += codigo[0];
        
        return Banco.getCon().manipular(sql);
    }
}
