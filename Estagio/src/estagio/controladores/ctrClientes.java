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
public class ctrClientes
{
    private static ctrClientes con;

    private ctrClientes()
    {
    }
    
    public static ctrClientes instancia()
    {
        if (con == null)
        {
            con = new ctrClientes();
        }
        return con;
    }
}
