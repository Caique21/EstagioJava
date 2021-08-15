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
public class ctrUsuario
{
    private static ctrUsuario con;

    private ctrUsuario()
    {
    }
    
    public static ctrUsuario instancia()
    {
        if (con == null)
        {
            con = new ctrUsuario();
        }
        return con;
    }
}
