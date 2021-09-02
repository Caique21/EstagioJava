/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import estagio.entidades.Cliente;

/**
 *
 * @author Carlos
 */
public class ctrCliente
{
    private static ctrCliente con;

    private ctrCliente()
    {
    }
    
    public static ctrCliente instancia()
    {
        if (con == null)
        {
            con = new ctrCliente();
        }
        return con;
    }

    public int cpfExists(String cpf)
    {
        Cliente cliente = new Cliente().getByCpf(cpf.replace(".", "").replace("-", ""));
        return cliente != null ?  cliente.getCodigo() : 0;
        //return new Cliente().getByCpf(cpf.replace(".", "").replace("-", "")).getCodigo();
    }
}
