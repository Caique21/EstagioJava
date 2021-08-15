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
public class ctrModelo
{
    private static ctrModelo con;

    private ctrModelo()
    {
    }
    
    public static ctrModelo instancia()
    {
        if (con == null)
        {
            con = new ctrModelo();
        }
        return con;
    }
}
