/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import java.sql.Timestamp;

/**
 *
 * @author Carlos
 */
public class Acesso
{
    private int codigo;
    private Timestamp login;
    private Timestamp logout;
    private Usuario usuario;
}
