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
public class ctrVenda
{
    private static ctrVenda con;

    private ctrVenda()
    {
    }
    
    public static ctrVenda instancia()
    {
        if (con == null)
        {
            con = new ctrVenda();
        }
        return con;
    }
}
