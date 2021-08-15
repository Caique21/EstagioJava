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
public class ctrDespesa
{
    private static ctrDespesa con;

    private ctrDespesa()
    {
    }
    
    public static ctrDespesa instancia()
    {
        if (con == null)
        {
            con = new ctrDespesa();
        }
        return con;
    }
}
