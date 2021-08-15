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
public class ctrVeiculo
{
    private static ctrVeiculo con;

    private ctrVeiculo()
    {
    }
    
    public static ctrVeiculo instancia()
    {
        if (con == null)
        {
            con = new ctrVeiculo();
        }
        return con;
    }
}
