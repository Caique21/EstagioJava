/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import java.util.Date;

/**
 *
 * @author Carlos
 */
public class Compra
{
    private int codigo;
    private Fornecedor fornecedor;
    private int qtd_parcelas;
    private double valor_total;
    private double ajuste;
    private Date data;
    private String numero_nota_fiscal;
    private Date data_emissao;
    private String vendedor;
}
