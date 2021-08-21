/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Carlos
 */
public class Funcionario
{
    private int codigo;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private Date data;
    private boolean ativo;
    private Timestamp alteracao;
    private Endereco endereco;
    private String endereco_completo;
    private Date vencimento;
    private BufferedImage cnh_frente;
    private BufferedImage cnh_verso;

    public Funcionario()
    {
    }

    public Funcionario(int codigo)
    {
        this.codigo = codigo;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
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

    public Date getVencimento()
    {
        return vencimento;
    }

    public void setVencimento(Date vencimento)
    {
        this.vencimento = vencimento;
    }

    public BufferedImage getCnh_frente()
    {
        return cnh_frente;
    }

    public void setCnh_frente(BufferedImage cnh_frente)
    {
        this.cnh_frente = cnh_frente;
    }

    public BufferedImage getCnh_verso()
    {
        return cnh_verso;
    }

    public void setCnh_verso(BufferedImage cnh_verso)
    {
        this.cnh_verso = cnh_verso;
    }
    
    
}
