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
public class ctrTransporte
{
    private static ctrTransporte con;

    private ctrTransporte()
    {
    }
    
    public static ctrTransporte instancia()
    {
        if (con == null)
        {
            con = new ctrTransporte();
        }
        return con;
    }
}
