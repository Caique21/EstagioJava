/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import java.sql.Date;

/**
 *
 * @author Carlos
 */
public class Despesa
{
    private int codigo;
    private String nome;
    private boolean fixo;
    private double valor;
    private Date vencimento;
    private String descricao;
    private Transporte transporte;
}
