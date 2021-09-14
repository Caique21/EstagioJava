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
import estagio.entidades.Endereco;
import estagio.entidades.Funcionario;
import estagio.entidades.Telefone;
import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class ctrFuncionario
{
    private static ctrFuncionario con;

    private ctrFuncionario()
    {
    }
    
    public static ctrFuncionario instancia()
    {
        if (con == null)
        {
            con = new ctrFuncionario();
        }
        return con;
    }

    public ArrayList<Objeto> getByName(String nome)
    {
        ArrayList<Objeto>retorno = new ArrayList<>();
        ArrayList<Funcionario>funcionarios = new Funcionario().getByName(nome,true);
        
        for (int i = 0; i < funcionarios.size(); i++)
            retorno.add(convertClienteToObjeto(funcionarios.get(i)));
        
        return retorno;
    }

    public ArrayList<Objeto> getByCPF(String cpf)
    {
        ArrayList<Objeto>retorno = new ArrayList<>();
        Funcionario funcionario = new Funcionario().getByCpf(cpf);
        
        if(funcionario != null && funcionario.isAtivo())
            retorno.add(convertClienteToObjeto(funcionario));
        
        return retorno;
    }
    
    public ArrayList<String> getTelefones(int codigo)
    {
        return new Funcionario().getTelefones(codigo);
    }

    public int cpfExists(String cpf)
    {
        Funcionario funcionario = new Funcionario().getByCpf(cpf);
        return funcionario != null ?  funcionario.getCodigo() : 0;
    }

    private Objeto convertClienteToObjeto(Funcionario funcionario)
    {
        Objeto objeto = new Objeto();
        objeto.setParam1(String.valueOf(funcionario.getCodigo()));
        objeto.setParam2(funcionario.getNome());
        objeto.setParam3(funcionario.getCpf());
        objeto.setParam4(funcionario.getRg());
        objeto.setParam5(funcionario.getEmail());
        objeto.setParam6(funcionario.getFuncao());
        objeto.setParam7(String.valueOf(funcionario.getData()));
        objeto.setParam8(String.valueOf(funcionario.isAtivo()));
        objeto.setParam9(String.valueOf(funcionario.getAlteracao()));
        objeto.setParam10(String.valueOf(funcionario.getEndereco().getCodigo()));
        objeto.setParam11(funcionario.getEndereco_completo());
        objeto.setParam12(String.valueOf(funcionario.getVencimento()));
        
        if(funcionario.getCnh_frente()!= null)
            objeto.setParam13(funcionario.getCnh_frente().toString());
        else
            objeto.setParam13(null);
        
        if(funcionario.getCnh_verso() != null)
            objeto.setParam14(funcionario.getCnh_verso().toString());
        else
            objeto.setParam14(null);
        return objeto;
        /*private int codigo;
        private String nome;
        private String cpf;
        private String rg;
        private String email;
        private Date data;
        private boolean ativo;
        private Timestamp alteracao;
        private Endereco endereco;
        private String endereco_completo;
        private Date vencimento;
        private BufferedImage cnh_frente;
        private BufferedImage cnh_verso;*/
    }

    public boolean salvar(JFXTextField nome, JFXTextField cpf, JFXTextField rg, JFXTextField email, 
        JFXComboBox<String> funcao,JFXDatePicker data, JFXTextField cep, JFXTextField rua, JFXTextField numero, 
        JFXTextField bairro, JFXTextField complemento, JFXTextField cidade, JFXComboBox<String> estado, 
        JFXListView<String> telefones,  JFXDatePicker vencimento,String frente,String verso)
    {
        Endereco endereco = new Endereco(cep.getText().replace("-", ""), rua.getText(), Integer.parseInt(numero.getText()), 
            bairro.getText(), complemento.getText(), cidade.getText(), estado.getSelectionModel().getSelectedItem());
        
        Funcionario funcionario = new Funcionario(nome.getText(),cpf.getText(),rg.getText(),email.getText(),
            funcao.getSelectionModel().getSelectedItem(),Date.valueOf(data.getValue()), endereco, 
                Date.valueOf(vencimento.getValue()));
        
        if(endereco.getCodigo() <= 0)
        {
            endereco.salvar();
            endereco.existe();
        }
        
        boolean flag = funcionario.salvar(frente,verso);
        if(flag)
        {
            funcionario.setCodigo(Banco.getCon().getMaxPK("funcionario", "func_codigo"));
            for (int i = 0; i < telefones.getItems().size() && flag; i++)
                flag = flag && new Telefone(telefones.getItems().get(i), funcionario).salvar();
        }
        return flag;
    }

    public boolean alterar(int codigo, JFXTextField nome, JFXTextField cpf, JFXTextField rg, JFXTextField email, 
        JFXComboBox<String> funcao,JFXDatePicker data, JFXTextField cep, JFXTextField rua, JFXTextField numero, 
        JFXTextField bairro, JFXTextField complemento, JFXTextField cidade, JFXComboBox<String> estado, 
        JFXListView<String> telefones,  JFXDatePicker vencimento,String frente,String verso)
    {
        Endereco endereco = new Endereco(cep.getText().replace("-", ""), rua.getText(), Integer.parseInt(numero.getText()), 
            bairro.getText(), complemento.getText(), cidade.getText(), estado.getSelectionModel().getSelectedItem());
        
        Funcionario funcionario = new Funcionario(nome.getText(),cpf.getText(),rg.getText(),email.getText(),
            funcao.getSelectionModel().getSelectedItem(),Date.valueOf(data.getValue()), endereco, 
                Date.valueOf(vencimento.getValue()));
        
        if(endereco.getCodigo() <= 0)
        {
            endereco.salvar();
            endereco.existe();
        }
        
        boolean flag = funcionario.alterar(frente, verso);
        if(flag)
        {
            Banco.getCon().manipular("DELETE FROM telefone WHERE func_codigo = " + funcionario.getCodigo());
            for (int i = 0; i < telefones.getItems().size() && flag; i++)
                flag = flag && new Telefone(telefones.getItems().get(i), funcionario).salvar();
        }
        return flag;
    }

    public boolean inativar(int codigo)
    {
        Funcionario funcionario = new Funcionario();
        funcionario.setCodigo(codigo);
        return funcionario.inativar();
    }
}
