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
public class ctrAcessos
{
    private static ctrAcessos con;

    private ctrAcessos()
    {
    }
    
    public static ctrAcessos instancia()
    {
        if (con == null)
        {
            con = new ctrAcessos();
        }
        return con;
    }
}
