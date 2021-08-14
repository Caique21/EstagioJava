/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Fornecedor
{
    private int codigo;
    private String nome;
    private String cnpj;
    private String email;
    private boolean ativo;
    private Timestamp alteracao;
    private Endereco endereco;
    private String endereco_completo;
    private ArrayList<String>telefones;
}
