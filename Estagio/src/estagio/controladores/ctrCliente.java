/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import estagio.entidades.Cliente;
import estagio.entidades.Endereco;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

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
        Cliente cliente = new Cliente().getByCpf(cpf);
        return cliente != null ?  cliente.getCodigo() : 0;
        //return new Cliente().getByCpf(cpf.replace(".", "").replace("-", "")).getCodigo();
    }

    public boolean salvar(JFXTextField nome, JFXTextField cpf, JFXTextField rg,JFXDatePicker data,JFXTextField cep,
        JFXTextField rua, JFXTextField numero, JFXTextField bairro, JFXTextField complemento,JFXTextField cidade, 
            JFXComboBox<String> estado, JFXTextField email, JFXListView<String> telefones)
    {
        Endereco endereco = new Endereco(cep.getText().replace("-", ""), rua.getText(), 
            Integer.parseInt(numero.getText()), bairro.getText(), complemento.getText(), cidade.getText(), 
                estado.getSelectionModel().getSelectedItem());
        
        Cliente cliente = new Cliente(nome.getText(), cpf.getText(), rg.getText(), Date.valueOf(data.getValue()), 
            endereco, email.getText(),telefones.getItems(),
                new Timestamp(new java.util.Date().getTime()));
        
        if(endereco.getCodigo() <= 0)
        {
            endereco.salvar();
            endereco.existe();
        }
        
        return cliente.salvar();
    }

    public boolean alterar(int codigo, JFXTextField nome, JFXTextField cpf, JFXTextField rg,JFXDatePicker data,JFXTextField cep,
        JFXTextField rua, JFXTextField numero, JFXTextField bairro, JFXTextField complemento,JFXTextField cidade, 
            JFXComboBox<String> estado, JFXTextField email, JFXListView<String> telefones)
    {
        Endereco endereco = new Endereco(cep.getText().replace("-", ""), rua.getText(), 
            Integer.parseInt(numero.getText()), bairro.getText(), complemento.getText(), cidade.getText(), 
                estado.getSelectionModel().getSelectedItem());
        
        Cliente cliente = new Cliente(codigo, nome.getText(), cpf.getText(), rg.getText(), 
            Date.valueOf(data.getValue()), endereco, email.getText(),telefones.getItems(),
                new Timestamp(new java.util.Date().getTime()));
        
        if(endereco.getCodigo() <= 0)
        {
            endereco.salvar();
            endereco.existe();
        }
        return cliente.alterar();
    }

    public boolean inativar(int codigo)
    {
        Cliente cliente = new Cliente();
        cliente.setCodigo(codigo);
        
        return cliente.tornarInativo();
    }

    public ArrayList<Objeto> getByName(String nome)
    {
        ArrayList<Objeto>retorno = new ArrayList<>();
        ArrayList<Cliente>clientes = new Cliente().getByName(nome,true);
        
        for (int i = 0; i < clientes.size(); i++)
            retorno.add(convertClienteToObjeto(clientes.get(i)));
        
        return retorno;
    }
    
    public ArrayList<Objeto> getByNameInativo(String nome)
    {
        ArrayList<Objeto>retorno = new ArrayList<>();
        ArrayList<Cliente>clientes = new Cliente().getByName(nome,false);
        
        for (int i = 0; i < clientes.size(); i++)
            retorno.add(convertClienteToObjeto(clientes.get(i)));
        
        return retorno;
    }
    
    public ArrayList<Objeto> getByCPF(String cpf)
    {
        ArrayList<Objeto>retorno = new ArrayList<>();
        Cliente cliente = new Cliente().getByCpf(cpf);
        
        if(cliente.isAtivo())
            retorno.add(convertClienteToObjeto(cliente));
        
        return retorno;
    }
    
    public ArrayList<Objeto> getByCPFInativo(String cpf)
    {
        ArrayList<Objeto>retorno = new ArrayList<>();
        Cliente cliente = new Cliente().getByCpf(cpf);
        
        if(!cliente.isAtivo())
            retorno.add(convertClienteToObjeto(cliente));
        
        return retorno;
    }

    private Objeto convertClienteToObjeto(Cliente cliente)
    {
        Objeto obj = new Objeto();
        obj.setParam1(String.valueOf(cliente.getCodigo()));
        obj.setParam2(cliente.getNome());
        obj.setParam3(cliente.getCpf());
        obj.setParam4(cliente.getRg());
        obj.setParam5(String.valueOf(Utils.convertData(cliente.getData())));
        obj.setParam6(String.valueOf(cliente.getEndereco().getCodigo()));
        obj.setParam7(cliente.getEndereco_completo().replace("\n", " "));
        obj.setParam8(cliente.getEmail());
        obj.setParam9(String.valueOf(cliente.isAtivo()));
        obj.setParam10(String.valueOf(cliente.getData_alteracao()));
        
        cliente.getTelefones().forEach((t) ->
        {
            obj.addList1(new Objeto(t));
        });
        
        return obj;
    }
    
    private Cliente convertObjetoToCliente(Objeto obj)
    {
        Cliente cliente = new Cliente();
        cliente.setNome(obj.getParam2());
        cliente.setCpf(obj.getParam3());
        cliente.setRg(obj.getParam4());
        cliente.setData(Date.valueOf(obj.getParam5()));
        cliente.setEndereco(new Endereco(Integer.parseInt(obj.getParam6())));
        cliente.setEndereco_completo(cliente.getEndereco().toString());
        cliente.setEmail(obj.getParam8());
        cliente.setAtivo(obj.getParam9().equals("true"));
        cliente.setData_alteracao(Timestamp.valueOf(obj.getParam10()));
        
        if(!obj.getParam1().equals(""))
            cliente.setCodigo(Integer.parseInt(obj.getParam1()));
        return cliente;
    }

    public boolean restaura(int codigo)
    {
        return new Cliente().restaurar(codigo);
    }

    public boolean apagar_fisico(int codigo)
    {
        return new Cliente().apagar_fisico(codigo);
    }

    public ArrayList<String> getAllNames(String... nome)
    {
        ArrayList<String>ret = new ArrayList<>();
        ArrayList<Cliente>clientes;
        
        if(nome.length == 0)
            clientes = new Cliente().getByName("", true);
        else
            clientes = new Cliente().getByName(nome[0], true);
        
        for (int i = 0; i < clientes.size(); i++)
            ret.add(clientes.get(i).getNome());
        
        return ret;
    }

    public boolean isCliente(String nome)
    {
        return new Cliente(nome).getCodigo() > 0;
    }
}
