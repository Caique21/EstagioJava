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
public class ctrRecebimento
{
    private static ctrRecebimento con;

    private ctrRecebimento()
    {
    }
    
    public static ctrRecebimento instancia()
    {
        if (con == null)
        {
            con = new ctrRecebimento();
        }
        return con;
    }
}
