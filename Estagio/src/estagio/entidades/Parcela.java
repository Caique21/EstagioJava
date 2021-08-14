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
public class Parcela
{
    private int codigo;
    private String status;
    private Date vencimento;
    private int numero;
    private Date pagamento;
    private double valor_parcela;
    private double valor_pago;
    private Compra compra;
    private Venda venda;
}
