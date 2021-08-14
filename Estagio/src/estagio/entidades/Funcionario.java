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
}
