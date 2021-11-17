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
    private String forma_pagamento;
    private String forma_pagamento_desc;
}
