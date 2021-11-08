/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import estagio.entidades.Transporte;
import estagio.entidades.Veiculo;
import estagio.utilidades.Objeto;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class ctrTransporte
{
    private static ctrTransporte con;

    private ctrTransporte()
    {
    }
    
    public static ctrTransporte instancia()
    {
        if (con == null)
        {
            con = new ctrTransporte();
        }
        return con;
    }

    public ArrayList<Objeto> getAll(Boolean... finalizado)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        
        for(Transporte t : new Transporte().getAll())
        {
            if(finalizado.length > 0 && finalizado[0])
                ret.add(convertToObjeto(t));
            else
                if(!t.getStatus().equals("Finalizado"))
                    ret.add(convertToObjeto(t));
        }    
        return ret;
    }

    private Objeto convertToObjeto(Transporte t)
    {
        ///1 - CÓDIGO, 2 - CÓDIGO MOTORISTA, 3 - CÓDIGO VEÍCULO, 4 - SAÍDA, 5 - CHEGADA, 6 - STATUS, 7 - TIPO
        ///8 - ALTERAÇÃO, 9 - NOME DO MOTORISTA,10 - PLACA DA CEGONHA,  11 - INFORMAÇÕES DO VÉICULO, 
        ///LIST1 - VEÍCULOS TRANSPORTADOS
        Objeto obj = new Objeto();
        obj.setParam1(String.valueOf(t.getCodigo()));
        obj.setParam2(String.valueOf(t.getMotorista().getCodigo()));
        obj.setParam3(String.valueOf(t.getCegonha().getCodigo()));
        obj.setParam4(String.valueOf(t.getSaida()));
        obj.setParam5(String.valueOf(t.getChegada()));
        obj.setParam6(t.getStatus());
        obj.setParam7(t.getTipo());
        obj.setParam8(String.valueOf(t.getAlteracao()));
        obj.setParam9(t.getMotorista().getNome());
        obj.setParam10(t.getCegonha().getPlaca());
        obj.setParam11(t.getCegonha().toString());
        
        for(Veiculo v : t.getVeiculos_transportados())
            obj.addList1(convertToObjeto(v));
            
        return obj;
    }

    private Objeto convertToObjeto(Veiculo v)
    {
        Objeto obj = new Objeto();
        obj.setParam1(String.valueOf(v.getCodigo()));
        obj.setParam2(v.getPlaca());
        obj.setParam3(String.valueOf(v.getModelo().getCodigo()));
        obj.setParam4(v.getChassi());
        obj.setParam5(String.valueOf(v.getAno()));
        obj.setParam6(v.getCor());
        obj.setParam7(v.getModelo().getMarca().getNome());
        obj.setParam8(v.getModelo().getNome());
        obj.setParam9(v.getDescricao());
        return obj;
    }

    public Objeto getByCodigo(int parseInt)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
