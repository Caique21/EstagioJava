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
public class Venda
{
    private int codigo;
    private Cliente cliente;
    private int qtd_parcelas;
    private double valor_total;
    private double ajuste;
    private Date data;
    private String numero_nota_fiscal;
    private Date data_emissao;
}
