/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import com.jfoenix.controls.JFXTextField;
import estagio.entidades.Marca;
import estagio.entidades.Modelo;
import estagio.utilidades.Objeto;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class ctrFabricante
{
    private static ctrFabricante con;

    private ctrFabricante()
    {
    }
    
    public static ctrFabricante instancia()
    {
        if (con == null)
        {
            con = new ctrFabricante();
        }
        return con;
    }

    public boolean salvarMarca(JFXTextField tfMarca)
    {
        Marca marca = new Marca();
        marca.setNome(tfMarca.getText());
        return marca.salvar();
    }
    
    public boolean salvarMarca(String m)
    {
        Marca marca = new Marca();
        marca.setNome(m);
        return marca.salvar();
    }

    public boolean alterarMarca(int codigo, JFXTextField tfMarca)
    {
        return new Marca(codigo, tfMarca.getText()).alterar();
    }
    
    public boolean alterarMarca(int codigo, String tfMarca)
    {
        return new Marca(codigo, tfMarca).alterar();
    }

    public boolean apagarMarca(Objeto item)
    {
        return new Marca().apagar(Integer.parseInt(item.getParam1()));
    }

    public boolean salvarModelo(JFXTextField tfMarca, JFXTextField tfModelo)
    {
        Modelo modelo = new Modelo(tfModelo.getText(), new Marca(tfMarca.getText()));
        return modelo.salvar();
    }
    
    public boolean salvarModelo(String marca_modelo)
    {
        Modelo modelo = new Modelo(marca_modelo.substring(marca_modelo.indexOf(":") + 1), 
            new Marca(marca_modelo.substring(0, marca_modelo.indexOf(":"))));
        return modelo.salvar();
    }

    public boolean alterarModelo(int codigo, JFXTextField tfModelo)
    {
        return new Modelo(codigo, tfModelo.getText()).alterar();
    }
    
    public boolean alterarModelo(int codigo, String tfModelo)
    {
        return new Modelo(codigo, tfModelo).alterar();
    }
    
    public boolean alterarModelo(int codigo, String tfModelo, String marca)
    {
        return new Modelo(codigo, tfModelo, new Marca(marca)).alterar();
    }

    public boolean apagarModelo(Objeto item)
    {
        return new Modelo().apagar(Integer.parseInt(item.getParam3()));
    }

    public ArrayList<Objeto> getAll()
    {
        return new Modelo().getAllMerged();
    }

    public ArrayList<Objeto> get(String... nomes)
    {
        switch (nomes.length)
        {
            case 0:
                return getAll();
            case 1:
                return new Modelo().get(nomes[0]);
            default:
                return new Modelo().get(nomes[0],nomes[1]);
        }
    }
    
    public ArrayList<Objeto> getByModelo(String nome)
    {
        return new Modelo().getByModelo(nome);
    }

    public ArrayList<String> getAllMarcas()
    {
        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<Marca> marcas = new Marca().getAll();
        
        for (int i = 0; i < marcas.size(); i++)
            nomes.add(marcas.get(i).getNome());
        
        return nomes;
    }

    public ArrayList<String> getAllModelosByMarca(String nome)
    {
        ArrayList<String> nomes = new ArrayList<>();
        Marca marca = new Marca(nome);
        ArrayList<Modelo> modelos = new Modelo().getByMarca(marca.getCodigo());
        
        for (int i = 0; i < modelos.size(); i++)
            nomes.add(modelos.get(i).getNome());
        
        return nomes;
    }

    public boolean existe(String marca, String modelo)
    {
        ArrayList<Objeto> m = new Modelo().get(marca,modelo);
        return Integer.parseInt(m.get(0).getParam1()) > 0;
    }
}
