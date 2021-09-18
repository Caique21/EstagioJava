/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import estagio.entidades.Funcionario;
import estagio.entidades.Usuario;
import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;

/**
 *
 * @author Carlos
 */
public class ctrUsuario
{
    private static ctrUsuario con;

    private ctrUsuario()
    {
    }
    
    public static ctrUsuario instancia()
    {
        if (con == null)
        {
            con = new ctrUsuario();
        }
        return con;
    }
    
    public boolean salvar(JFXTextField funcionario, JFXTextField nome, JFXPasswordField senha, JFXComboBox<String> nivel)
    {
        return new Usuario(new Funcionario(), nome.getText(), senha.getText(), 
                nivel.getSelectionModel().getSelectedItem()).salvar();
    }
    
    public boolean salvar(String funcionario, String nivel)
    {
        Usuario usuario = new Usuario();
        usuario.setFuncionario(new Funcionario(Banco.getCon().getMaxPK("funcionario", "func_codigo")));
        usuario.setNome(funcionario.toLowerCase());
        usuario.setNivel(nivel);
        
        usuario.setSenha(usuario.getNome().substring(0, 3));
        if(usuario.getNome().contains(" "))
        {
            //3 primeiras letras do primeiro nome + 3 primeras letras do ultimo nome + numero de cadastro
            usuario.setSenha(usuario.getSenha() + 
                usuario.getNome().substring(usuario.getNome().lastIndexOf(" ") + 1, 
                        usuario.getNome().lastIndexOf(" ") + 4));
        }
        usuario.setSenha(usuario.getSenha() + usuario.getFuncionario().getCodigo());
        
        return usuario.salvar();
    }
    
    public boolean alterar(int codigo,JFXTextField funcionario, JFXTextField nome, JFXPasswordField senha, JFXComboBox<String> nivel)
    {
        return new Usuario(codigo,new Funcionario(), nome.getText(), senha.getText(), 
                nivel.getSelectionModel().getSelectedItem()).alterar();
    }
    
    public boolean apagar(int codigo)
    {
        return new Usuario(codigo).apagar();
    }
    
    public boolean apagar_fisico(int codigo)
    {
        return new Usuario(codigo).apagar_fisico();
    }

    public boolean isUsuario(String nome)
    {
        return new Usuario().isUsuario(nome);
    }

    public boolean matchPassword(String nome, String senha)
    {
        return new Usuario().matchPassword(nome,senha);
    }

    public Objeto get(String nome, String senha)
    {
        Usuario usuario = new Usuario(nome, senha);
        if(usuario.getCodigo() <= 0)
            return null;
        else
        {
            if(usuario.getFuncionario() != null)
                return new Objeto(String.valueOf(usuario.getCodigo()), 
                    String.valueOf(usuario.getFuncionario().getCodigo()),usuario.getNome(), usuario.getSenha(), 
                        usuario.getNivel(),String.valueOf(usuario.isAtivo()));
            
            return new Objeto(String.valueOf(usuario.getCodigo()), usuario.getNome(), usuario.getSenha(), 
                        usuario.getNivel(),String.valueOf(usuario.isAtivo()));
        }
    }
}
