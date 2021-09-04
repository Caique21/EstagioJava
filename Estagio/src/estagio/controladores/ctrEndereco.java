/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import estagio.entidades.Endereco;
import estagio.utilidades.Objeto;

/**
 *
 * @author Carlos
 */
public class ctrEndereco
{
    private static ctrEndereco con;

    private ctrEndereco()
    {
    }
    
    public static ctrEndereco instancia()
    {
        if (con == null)
        {
            con = new ctrEndereco();
        }
        return con;
    }
    
    public Objeto getByCodigo(int codigo)
    {
        Endereco endereco = new Endereco(codigo);
        return convertEnderecoToObjeto(endereco);
    }
    
    public Endereco convertToEndereco(String ender)
    {
        //(rua) (numero), (bairro) - (complemento);(cidade) - (estado) - CEP (cep)
        String aux[] = ender.split(";");
        String aux2[] = aux[0].split(",");
        String aux3[] = aux[1].split(" - ");
        /*
        rua = rua.substring(0, rua.lastIndexOf(" "))
        bairro = ender.substring(ender.indexOf(",") + 2, ender.indexOf(";"))
        numero = Integer.parseInt(rua.substring(rua.lastIndexOf(" ") + 1).replace(" ", "").replace(",", "")), 
        cep = aux[2].replace(" ", "")
        estado = aux[1].replace(" ", "").replace("-", "")
        cidade = aux[0].substring(aux[0].indexOf("; ") + 2).replace("; ", "").replace(" -","")
        */
        String rua = aux2[0].substring(0,aux2[0].lastIndexOf(" ")).trim();
        int numero = Integer.parseInt(aux2[0].substring(aux2[0].lastIndexOf(" ") + 1));
        String bairro;
        String complemento;
        if(aux2[1].contains("-"))
        {
            complemento = aux2[1].substring(aux2[1].indexOf(" - ") + 2).trim();
            bairro = aux2[1].substring(0, aux2[1].indexOf(" - ")).trim();
        }
        else
        {
            complemento = "";
            bairro = aux2[1].trim();
        }
        
        Endereco endereco = new Endereco(aux3[2].replace("-", "").trim(), rua, numero, bairro, complemento, 
            aux3[0].trim(), aux3[1].trim());
        
        endereco.existe();
        return endereco;
    }
    
    public Objeto convertEnderecoToObjeto(String ender)
    {
        Endereco endereco = convertToEndereco(ender);
        Objeto ret = new Objeto();
        ret.setParam1(String.valueOf(endereco.getCodigo()));
        ret.setParam2(endereco.getCep());
        ret.setParam3(endereco.getRua());
        ret.setParam4(String.valueOf(endereco.getNumero()));
        ret.setParam5(endereco.getBairro());
        ret.setParam6(endereco.getComplemento());
        ret.setParam7(endereco.getCidade());
        ret.setParam8(endereco.getEstado());
        return ret;
    }
    
    public Objeto convertEnderecoToObjeto(Endereco endereco)
    {
        Objeto ret = new Objeto();
        ret.setParam1(String.valueOf(endereco.getCodigo()));
        ret.setParam2(endereco.getCep());
        ret.setParam3(endereco.getRua());
        ret.setParam4(String.valueOf(endereco.getNumero()));
        ret.setParam5(endereco.getBairro());
        ret.setParam6(endereco.getComplemento());
        ret.setParam7(endereco.getCidade());
        ret.setParam8(endereco.getEstado());
        return ret;
    }
}
