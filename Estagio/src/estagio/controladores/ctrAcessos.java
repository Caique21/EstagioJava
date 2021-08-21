/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import estagio.utilidades.Objeto;
import java.sql.Timestamp;

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

    public void salvar(Timestamp data_login, Timestamp timestamp, Objeto usuario_logado)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
