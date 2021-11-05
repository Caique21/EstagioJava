/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import estagio.entidades.Marca;
import estagio.entidades.Modelo;
import estagio.entidades.Veiculo;
import estagio.utilidades.Objeto;
import java.util.ArrayList;

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

    public boolean apagar(int cod)
    {
        Veiculo v = new Veiculo();
        v.setCodigo(cod);
        return v.apagar();
    }

    public ArrayList<Objeto> getAll(Boolean... estoque)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        ArrayList<Veiculo> veiculos;
        
        if(estoque.length == 0)
            veiculos = new Veiculo().getAll();
        else
            veiculos = new Veiculo().getAllInInventory();
        
        for (int i = 0; i < veiculos.size(); i++)
            ret.add(convertToObjeto(veiculos.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByMarca(String marca,Boolean... estoque)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        ArrayList<Veiculo> veiculos;
        
        if(estoque.length == 0)
            veiculos = new Veiculo().getByMarca(marca);
        else
            veiculos = new Veiculo().getByMarcaInInventory(marca);
        
        for (int i = 0; i < veiculos.size(); i++)
            ret.add(convertToObjeto(veiculos.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByModelo(String modelo,Boolean... estoque)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        ArrayList<Veiculo> veiculos;
        
        if(estoque.length == 0)
            veiculos = new Veiculo().getByModelo(modelo);
        else
            veiculos = new Veiculo().getByModeloInVentory(modelo);
        
        for (int i = 0; i < veiculos.size(); i++)
            ret.add(convertToObjeto(veiculos.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByPlaca(String placa,Boolean... estoque)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        ArrayList<Veiculo> veiculos;
        
        if(estoque.length == 0)
            veiculos = new Veiculo().getByPlaca(placa);
        else
            veiculos = new Veiculo().getByPlacaInInventory(placa);
        
        for (int i = 0; i < veiculos.size(); i++)
            ret.add(convertToObjeto(veiculos.get(i)));
        
        return ret;
    }
    
    public Objeto getByCodigo(int codigo)
    {
        return convertToObjeto(new Veiculo(codigo));
    }

    public boolean salvar(String placa, JFXTextField marca, JFXTextField modelo, JFXTextField chassi, JFXTextField cor,
        JFXTextField ano, JFXTextArea descricao)
    {
        Veiculo veiculo = new Veiculo(placa, new Modelo(modelo.getText(), new Marca(marca.getText())), 
            chassi.getText(), Integer.parseInt(ano.getText()), cor.getText(), descricao.getText());
        veiculo.getModelo().setCodigo();
        
        return veiculo.salvar();
    }

    public boolean alterar(int cod, String placa, JFXTextField marca, JFXTextField modelo, JFXTextField chassi, 
        JFXTextField cor, JFXTextField ano, JFXTextArea descricao)
    {
        Veiculo veiculo = new Veiculo(cod, placa, new Modelo(modelo.getText(), new Marca(marca.getText())), 
            chassi.getText(), Integer.parseInt(ano.getText()), cor.getText(), descricao.getText());
        veiculo.getModelo().setCodigo();
        
        return veiculo.alterar();
    }
    
    public Double getPrecoSugerido(int codigo)
    {
        return new Veiculo(codigo).getPrecoSugerido();
    }

    public Objeto convertToObjeto(Veiculo v)
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
