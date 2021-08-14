/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;

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
            sql = sql.replace("$1", numero);
            sql = sql.replace("$2", String.valueOf(cliente.getCodigo()));
        }
        return Banco.getCon().manipular(sql);
    }
}
