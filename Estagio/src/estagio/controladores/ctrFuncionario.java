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
public class ctrFuncionario
{
    private static ctrFuncionario con;

    private ctrFuncionario()
    {
    }
    
    public static ctrFuncionario instancia()
    {
        if (con == null)
        {
            con = new ctrFuncionario();
        }
        return con;
    }
}
