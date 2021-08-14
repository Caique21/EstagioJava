/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Carlos
 */
public class Transporte
{
    private int codigo;
    private Funcionario motorista;
    private Veiculo cegonha;
    private Date saida;
    private Date chegada;
    private String status;
    private String tipo;
    private Timestamp alteracao;
}
