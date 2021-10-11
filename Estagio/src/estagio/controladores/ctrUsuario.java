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
import java.util.ArrayList;

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
        return new Usuario(new Funcionario(funcionario.getText()), nome.getText(), senha.getText(), 
                nivel.getSelectionModel().getSelectedItem()).salvar();
    }
    
    public boolean salvar(String funcionario, String nivel)
    {
        Usuario usuario = new Usuario();
        usuario.setFuncionario(new Funcionario(Banco.getCon().getMaxPK("funcionario", "func_codigo")));
        
        funcionario = funcionario.toLowerCase();
        if(funcionario.contains(" "))
        {
            usuario.setNome(funcionario.substring(0, funcionario.indexOf(" ") + 1));
            usuario.setNome(usuario.getNome() + funcionario.substring(funcionario.lastIndexOf(" ") + 1));
        }
        else
            usuario.setNome(funcionario);
        
        usuario.setSenha(usuario.getNome().substring(0,3));
        
        int j = 0;
        for (int i = usuario.getNome().indexOf(" ") + 1; i < usuario.getNome().length() && j < 3; i++)
        {
            usuario.setSenha(usuario.getSenha() + usuario.getNome().charAt(i));
            j++;
        }
        usuario.setSenha(usuario.getSenha() + usuario.getFuncionario().getCodigo());
        usuario.setNivel(nivel);
        
        /*usuario.setSenha(usuario.getNome().substring(0, 3));
        if(usuario.getNome().contains(" "))
        {
            //3 primeiras letras do primeiro nome + 3 primeras letras do ultimo nome + numero de cadastro
            String aux = usuario.getNome().substring(usuario.getNome().lastIndexOf(" ") + 1);
            if(aux.length() >= 3)
                usuario.setSenha(usuario.getSenha() + aux.substring(0, 3));
            else
                usuario.setSenha(usuario.getSenha() + aux);
        }
        usuario.setSenha(usuario.getSenha() + usuario.getFuncionario().getCodigo());*/
        
        return usuario.salvar();
    }
    
    public boolean alterar(int codigo,JFXTextField funcionario, JFXTextField nome, JFXPasswordField senha, JFXComboBox<String> nivel)
    {
        return new Usuario(codigo,new Funcionario(funcionario.getText()), nome.getText(), senha.getText(), 
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
            
            return new Objeto(String.valueOf(usuario.getCodigo()),"", usuario.getNome(), usuario.getSenha(), 
                        usuario.getNivel(),String.valueOf(usuario.isAtivo()));
        }
    }

    public Objeto getUsuarioByFuncionario(String nome)
    {
        Funcionario funcionario = new Funcionario(nome);
        Usuario usuario = new Usuario(funcionario);
        if(usuario != null && usuario.getCodigo() > 0)
            return convertToObjeto(usuario);
        return null;
    }

    public ArrayList<Objeto> getAll()
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ArrayList<Usuario>usuarios = new Usuario().getAll();
        
        for(Usuario u : usuarios)
            if(u.isAtivo())
                ret.add(convertToObjeto(u));
        return ret;
    }
    
    public ArrayList<Objeto> getAllInativo()
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ArrayList<Usuario>usuarios = new Usuario().getAll();
        
        for(Usuario u : usuarios)
            if(!u.isAtivo())
                ret.add(convertToObjeto(u));
        return ret;
    }

    public ArrayList<Objeto> getByFuncionario(String funcionario)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ArrayList<Funcionario> funcionarios = new Funcionario().getByName(funcionario, true);
        
        for(Funcionario f : funcionarios)
            if(f.isAtivo())
                ret.add(convertToObjeto(new Usuario(f)));
        return ret;
    }

    public ArrayList<Objeto> getByName(String nome)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ArrayList<Usuario>usuarios = new Usuario().getByName(nome);
        
        for(Usuario u : usuarios)
            if(u.isAtivo())
                ret.add(convertToObjeto(u));
        return ret;
    }

    public ArrayList<Objeto> getByNameInativo(String nome)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ArrayList<Usuario>usuarios = new Usuario().getByName(nome);
        
        for(Usuario u : usuarios)
            if(!u.isAtivo())
                ret.add(convertToObjeto(u));
        return ret;
    }

    public ArrayList<Objeto> getByNivel(String nivel)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ArrayList<Usuario>usuarios = new Usuario().getByNivel(nivel);
        
        for(Usuario u : usuarios)
            if(u.isAtivo())
                ret.add(convertToObjeto(u));
        return ret;
    }

    public ArrayList<Objeto> getByNivelInativo(String nivel)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ArrayList<Usuario>usuarios = new Usuario().getByNivel(nivel);
        
        for(Usuario u : usuarios)
            if(!u.isAtivo())
                ret.add(convertToObjeto(u));
        return ret;
    }

    private Objeto convertToObjeto(Usuario u)
    {
        Objeto o = new Objeto();
        o.setParam1(String.valueOf(u.getCodigo()));
        o.setParam2(u.getNome());
        o.setParam3(u.getSenha());
        o.setParam4(u.getNivel());
        o.setParam5(String.valueOf(u.isAtivo()));
        o.setParam6(String.valueOf(u.getFuncionario().getCodigo()));
        o.setParam7(String.valueOf(u.getFuncionario().getNome()));
        return o;
    }

    public boolean inativar(int codigo)
    {
        return new Usuario(codigo).inativar();
    }

   public Objeto get(int codigo)
    {
        Usuario usuario = new Usuario(codigo);
        return usuario.getCodigo() > 1? convertToObjeto(usuario) : null;
    }

    public boolean restaura(int codigo)
    {
        return new Usuario().restaurar(codigo);
    }
}
