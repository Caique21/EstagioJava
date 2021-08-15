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
public class ctrParcela
{
    private static ctrParcela con;

    private ctrParcela()
    {
    }
    
    public static ctrParcela instancia()
    {
        if (con == null)
        {
            con = new ctrParcela();
        }
        return con;
    }
}
