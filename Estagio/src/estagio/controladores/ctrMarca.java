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
public class ctrMarca
{
    private static ctrMarca con;

    private ctrMarca()
    {
    }
    
    public static ctrMarca instancia()
    {
        if (con == null)
        {
            con = new ctrMarca();
        }
        return con;
    }
}
