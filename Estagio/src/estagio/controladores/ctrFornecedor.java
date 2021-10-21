/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import estagio.entidades.Endereco;
import estagio.entidades.Fornecedor;
import estagio.entidades.Telefone;
import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import java.util.ArrayList;

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

    public boolean exiteCNPJ(String cnpj, Objeto fornecedor)
    {
        if(fornecedor == null)
            return new Fornecedor(cnpj, false).getCodigo() > 0;
        return new Fornecedor(cnpj, false).getCodigo() != Integer.parseInt(fornecedor.getParam1());
    }

    public boolean salvar(JFXTextField nome, JFXTextField cnpj, JFXTextField email, JFXListView<String> telefones, 
        JFXTextField cep, JFXTextField rua, JFXTextField numero, JFXTextField bairro, JFXTextField complemento, 
        JFXTextField cidade, JFXComboBox<String> estado)
    {
        Endereco endereco = new Endereco(cep.getText().replace("-", ""), rua.getText(), Integer.parseInt(numero.getText()), 
            bairro.getText(), complemento.getText(), cidade.getText(), estado.getSelectionModel().getSelectedItem());
        
        Fornecedor fornecedor = new Fornecedor(nome.getText(), cnpj.getText(), email.getText(), endereco);
        
        if(endereco.getCodigo() <= 0)
        {
            endereco.salvar();
            endereco.existe();
        }
        
        boolean flag = fornecedor.salvar();
        
        if(flag && !telefones.getItems().isEmpty())
        {
            fornecedor.setCodigo(Banco.getCon().getMaxPK("fornecedor", "forn_codigo"));
            for (int i = 0; i < telefones.getItems().size() && flag; i++)
                flag = flag && new Telefone(telefones.getItems().get(i).replace("(", "").replace(")", "")
                    .replace("-", ""), fornecedor).salvar();
        }
        
        return flag;
    }

    public boolean alterar(int codigo, JFXTextField nome, JFXTextField cnpj, JFXTextField email, 
        JFXListView<String> telefones, JFXTextField cep, JFXTextField rua, JFXTextField numero, JFXTextField bairro, 
        JFXTextField complemento, JFXTextField cidade, JFXComboBox<String> estado)
    {
        Endereco endereco = new Endereco(cep.getText().replace("-", ""), rua.getText(), Integer.parseInt(numero.getText()), 
            bairro.getText(), complemento.getText(), cidade.getText(), estado.getSelectionModel().getSelectedItem());
        
        Fornecedor fornecedor = new Fornecedor(codigo, nome.getText(), cnpj.getText(), email.getText(), endereco);
        fornecedor.setAtivo(true);
        
        if(endereco.getCodigo() <= 0)
        {
            endereco.salvar();
            endereco.existe();
        }
        
        boolean flag = fornecedor.alterar();
        
        if(flag && !telefones.getItems().isEmpty())
        {
            Banco.getCon().manipular("DELETE FROM telefone WHERE forn_codigo = " + fornecedor.getCodigo());
            for (int i = 0; i < telefones.getItems().size() && flag; i++)
                flag = flag && new Telefone(telefones.getItems().get(i).replace("(", "").replace(")", "")
                    .replace("-", ""), fornecedor).salvar();
        }
        
        return flag;
    }

    public boolean inativar(int codigo)
    {
        return new Fornecedor().inativar(codigo);
    }

    public ArrayList<Objeto> getAll()
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        ArrayList<Fornecedor> fornecedores = new Fornecedor().getAll();
        
        for (int i = 0; i < fornecedores.size(); i++)
            if(fornecedores.get(i).isAtivo())
                ret.add(convertToObjeto(fornecedores.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getAllInativo()
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        ArrayList<Fornecedor> fornecedores = new Fornecedor().getAll();
        
        for (int i = 0; i < fornecedores.size(); i++)
            if(!fornecedores.get(i).isAtivo())
                ret.add(convertToObjeto(fornecedores.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByName(String nome)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        ArrayList<Fornecedor> fornecedores = new Fornecedor().getByName(nome);
        
        for (int i = 0; i < fornecedores.size(); i++)
            if(fornecedores.get(i).isAtivo())
                ret.add(convertToObjeto(fornecedores.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByNameInativo(String nome)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        ArrayList<Fornecedor> fornecedores = new Fornecedor().getByName(nome);
        
        for (int i = 0; i < fornecedores.size(); i++)
            if(!fornecedores.get(i).isAtivo())
                ret.add(convertToObjeto(fornecedores.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByCNPJ(String cnpj)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        ArrayList<Fornecedor> fornecedores = new Fornecedor().getByCNPJ(cnpj);
        
        for (int i = 0; i < fornecedores.size(); i++)
            if(fornecedores.get(i).isAtivo())
                ret.add(convertToObjeto(fornecedores.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByCNPJInativo(String cnpj)
    {
        ArrayList<Objeto> ret = new ArrayList<>();
        ArrayList<Fornecedor> fornecedores = new Fornecedor().getByCNPJ(cnpj);
        
        for (int i = 0; i < fornecedores.size(); i++)
            if(!fornecedores.get(i).isAtivo())
                ret.add(convertToObjeto(fornecedores.get(i)));
        
        return ret;
    }

    private Objeto convertToObjeto(Fornecedor forn)
    {
        Objeto obj = new Objeto();
        obj.setParam1(String.valueOf(forn.getCodigo()));
        obj.setParam2(forn.getNome());
        obj.setParam3(forn.getCnpj());
        obj.setParam4(forn.getEmail());
        obj.setParam5(String.valueOf(forn.getAlteracao()));
        obj.setParam6(String.valueOf(forn.getEndereco().getCodigo()));
        obj.setParam7(forn.getEndereco_completo().replace("\n", ""));
        return obj;
    }

    public ArrayList<String> getTelefones(int codigo)
    {
        return new Fornecedor().getTelefones(codigo);
    }

    public boolean restaura(int codigo)
    {
        return new Fornecedor().restaurar(codigo);
    }

    public boolean apagar_fisico(int codigo)
    {
        return new Fornecedor().apagar_fisico(codigo);
    }

    public ArrayList<String> getAllNames(String... nome)
    {
        ArrayList<String>ret = new ArrayList<>();
        ArrayList<Fornecedor>fornecedores;
        
        if(nome.length == 0)
            fornecedores = new Fornecedor().getByName("");
        else
            fornecedores = new Fornecedor().getByName(nome[0]);
        
        for (int i = 0; i < fornecedores.size(); i++)
            if(fornecedores.get(i).isAtivo())
                ret.add(fornecedores.get(i).getNome());
        
        return ret;
    }
}
