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
public class ctrFornecedor
{
    private static ctrFornecedor con;

    private ctrFornecedor()
    {
    }
    
    public static ctrFornecedor instancia()
    {
        if (con == null)
        {
            con = new ctrFornecedor();
        }
        return con;
    }
}
