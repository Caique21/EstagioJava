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
public class ctrCompra
{
    private static ctrCompra con;

    private ctrCompra()
    {
    }
    
    public static ctrCompra instancia()
    {
        if (con == null)
        {
            con = new ctrCompra();
        }
        return con;
    }
}
