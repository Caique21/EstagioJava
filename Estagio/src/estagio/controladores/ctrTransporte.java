/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import estagio.entidades.Funcionario;
import estagio.entidades.Transporte;
import estagio.entidades.Veiculo;
import estagio.utilidades.Objeto;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public Objeto getByCodigo(int codigo)
    {
        return convertToObjeto(new Transporte(codigo));
    }

    public boolean salvar(Objeto t)
    {
        Transporte transporte = convertToTransporte(t);
        
        return transporte.salvar();
    }

    public boolean alterar(Objeto t)
    {
        Transporte transporte = convertToTransporte(t);
        
        return transporte.alterar();
    }
    
    public boolean apagar(int codigo)
    {
        return new Transporte().apagar(codigo);
    }

    public ArrayList<Objeto> getAll(Boolean... finalizado)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        
        for(Transporte t : new Transporte().getAll())
        {
            if(finalizado.length == 0 || !finalizado[0])
            {
                if(!t.getStatus().equals("Finalizado"))
                    ret.add(convertToObjeto(t));
            }
            else
                if(t.getStatus().equals("Finalizado"))
                    ret.add(convertToObjeto(t));
        }    
        return ret;
    }
    
    private Transporte convertToTransporte(Objeto transporte)
    {
        ///1 - CÓDIGO, 2 - CÓDIGO MOTORISTA, 3 - PLACA VEÍCULO, 4 - SAÍDA, 5 - CHEGADA, 6 - STATUS, 7 - TIPO
        ///8 - ALTERAÇÃO, 9 - NOME DO MOTORISTA
        ///LIST1 - VEÍCULOS TRANSPORTADOS
        
        Transporte t = new Transporte();
        t.setCodigo(Integer.parseInt(transporte.getParam1()));
        t.setMotorista(new Funcionario(Integer.parseInt(transporte.getParam2())));
        t.setCegonha(transporte.getParam3());
        t.setStatus(transporte.getParam6());
        t.setTipo(transporte.getParam7());
        
        for(Objeto vei : transporte.getList1())
            t.addVeiculo(Integer.parseInt(vei.getParam1()));
        
        try
        {
            t.setSaida(new SimpleDateFormat("yyy-MM-dd").parse(transporte.getParam4()));
            t.setChegada(new SimpleDateFormat("yyy-MM-dd").parse(transporte.getParam5()));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(transporte.getParam8());
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            t.setAlteracao(timestamp);
        }
        catch (ParseException e)
        { //this generic but you can control another types of exception
            // look the origin of excption 
        }
        return t;
    }

    private Objeto convertToObjeto(Transporte t)
    {
        ///1 - CÓDIGO, 2 - CÓDIGO MOTORISTA, 3 - PLACA VEÍCULO, 4 - SAÍDA, 5 - CHEGADA, 6 - STATUS, 7 - TIPO
        ///8 - ALTERAÇÃO, 9 - NOME DO MOTORISTA
        ///LIST1 - VEÍCULOS TRANSPORTADOS
        Objeto obj = new Objeto();
        obj.setParam1(String.valueOf(t.getCodigo()));
        obj.setParam2(String.valueOf(t.getMotorista().getCodigo()));
        obj.setParam3(String.valueOf(t.getCegonha()));
        obj.setParam4(String.valueOf(t.getSaida()));
        obj.setParam5(String.valueOf(t.getChegada()));
        obj.setParam6(t.getStatus());
        obj.setParam7(t.getTipo());
        obj.setParam8(String.valueOf(t.getAlteracao()));
        obj.setParam9(t.getMotorista().getNome());
        
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
}
