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
public class Recebimento
{
    private int codigo;
    private Date data;
    private double valor;
    private Parcela parcela;
    private FormaPagamento forma_pagamento;
    private boolean ativo;//TRUE == pagamento  | FALSE == estorno
}
