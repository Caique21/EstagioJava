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
public class ctrDesign
{
    private static ctrDesign con;

    private ctrDesign()
    {
    }
    
    public static ctrDesign instancia()
    {
        if (con == null)
        {
            con = new ctrDesign();
        }
        return con;
    }
}
