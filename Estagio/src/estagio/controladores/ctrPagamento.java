/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

/**
 *
 * @author Carlos
 */
public class ctrPagamento
{
    private static ctrPagamento con;

    private ctrPagamento()
    {
    }
    
    public static ctrPagamento instancia()
    {
        if (con == null)
        {
            con = new ctrPagamento();
        }
        return con;
    }
}
