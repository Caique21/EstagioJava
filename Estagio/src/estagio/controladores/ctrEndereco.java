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
public class ctrEndereco
{
    private static ctrEndereco con;

    private ctrEndereco()
    {
    }
    
    public static ctrEndereco instancia()
    {
        if (con == null)
        {
            con = new ctrEndereco();
        }
        return con;
    }
}
