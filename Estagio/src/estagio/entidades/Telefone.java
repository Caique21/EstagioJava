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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Telefone
{
    private int codigo;
    private String numero;
    private Cliente cliente;
    //private Funcionario funcionario;
    //private Fornecedor fornecedor;
    private Parametrizacao parametrizacao;

    public Telefone()
    {
    }

    public Telefone(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public Telefone(String numero, Cliente cliente)
    {
        this.numero = numero;
        this.cliente = cliente;
    }

    public Telefone(int codigo, String numero, Cliente cliente)
    {
        this.codigo = codigo;
        this.numero = numero;
        this.cliente = cliente;
    }

    public Telefone(int codigo, String numero, Parametrizacao parametrizacao)
    {
        this.codigo = codigo;
        this.numero = numero;
        this.parametrizacao = parametrizacao;
    }

    public Telefone(String numero, Parametrizacao parametrizacao)
    {
        this.numero = numero;
        this.parametrizacao = parametrizacao;
    }
    
    public boolean salvar()
    {
        String sql = "";
        if(cliente != null)
        {
            sql = "INSERT INTO telefone(tel_numero,cli_codigo) VALUES('$1',$2)";
            sql = sql.replace("$2", String.valueOf(cliente.getCodigo()));
        }
        else if(parametrizacao != null)
        {
            sql = "insert into telefone (tel_numero, para_nome) values('$1','$2')";
            sql = sql.replace("$2", parametrizacao.getNome());
        }
            
        sql = sql.replace("$1", numero);
        return Banco.getCon().manipular(sql);
    }

    public ArrayList<String> getAllByCliente(Cliente cliente)
    {
        ArrayList<String> telefones = new ArrayList<>();
        
        ResultSet rs = Banco.getCon().consultar("SELECT tel_numero FROM telefone WHERE cli_codigo" + 
                cliente.getCodigo());
        
        try
        {
            while(rs != null && rs.next())            
            {
                telefones.add(rs.getString("tel_numero"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Telefone.class.getName()).log(Level.SEVERE, null, ex);
        }
        return telefones;
    }
}
