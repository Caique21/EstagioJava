/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import estagio.entidades.Acesso;
import estagio.entidades.Usuario;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Carlos
 */
public class ctrAcesso
{
    private static ctrAcesso con;

    private ctrAcesso()
    {
    }
    
    public static ctrAcesso instancia()
    {
        if (con == null)
        {
            con = new ctrAcesso();
        }
        return con;
    }

     public boolean salvar(Timestamp login,Timestamp logout,Objeto usr)
    {
        Usuario usuario = new Usuario(Integer.parseInt(usr.getParam1()), usr.getParam2(), usr.getParam3(), 
            usr.getParam4());
        Acesso acessos = new Acesso(login, logout, usuario);
        
        return acessos.salvar();
    }
    
    private Objeto getUsuario(Usuario usr)
    {
        return new Objeto(String.valueOf(usr.getCodigo()), usr.getNome(), usr.getSenha(), 
                String.valueOf(usr.getNivel()));
    }
    
    public String TimestamptoDate(Timestamp ts)
    {
        String aux = String.valueOf(ts);
        aux = Utils.convertData(new Date(ts.getTime())) + " " + 
                aux.substring(aux.indexOf(" ") + 1,aux.lastIndexOf("."));
        return aux;
    }
    
    public Objeto getAcesso(int cod)
    {
        Acesso acesso = new Acesso().getAcesso(cod);
        String login,logout;
        
        login = TimestamptoDate(acesso.getLogin());
        logout = TimestamptoDate(acesso.getLogout());
        
        return new Objeto(String.valueOf(acesso.getCodigo()), login, logout, String.valueOf(acesso.getUsuario().getCodigo()));
    }
    
    public ArrayList<Objeto> get(String data)
    {
        ArrayList<Acesso>acessos = new ArrayList<>();
        ArrayList<Objeto>objs = new ArrayList<>();
        String login,logout;
        
        acessos = new Acesso().get(data);
        
        for(Acesso a : acessos)
        {
            login = TimestamptoDate(a.getLogin());
            logout = TimestamptoDate(a.getLogout());
            
            objs.add(new Objeto(String.valueOf(a.getCodigo()),login,logout,String.valueOf(a.getUsuario().getCodigo())));
        }
        return objs;
    }

    public Objeto getDataCompleta(String data)
    {
        Timestamp ts = Timestamp.valueOf(data);
        Acesso acesso = new Acesso().get(ts);
        String login,logout;
        
        if(acesso != null)
        {
            login = TimestamptoDate(acesso.getLogin());
            logout = TimestamptoDate(acesso.getLogout());
            
            return new Objeto(String.valueOf(acesso.getCodigo()),login,logout,
                 String.valueOf(acesso.getUsuario().getCodigo()));
        }
        return null;
    }
    
    public ArrayList<Objeto> getAcessoUsuario(int cod)
    {
        ArrayList<Acesso>acessos = new ArrayList<>();
        ArrayList<Objeto>objs = new ArrayList<>();
        String login,logout;
        
        acessos = new Acesso().getAcessoUsuario(cod);
        
        for(Acesso a : acessos)
        {
            login = TimestamptoDate(a.getLogin());
            logout = TimestamptoDate(a.getLogout());
            
            objs.add(new Objeto(String.valueOf(a.getCodigo()),login,logout,String.valueOf(a.getUsuario().getCodigo())));
        }
        return objs;
    }

    public boolean firstOfDay(LocalDate now)
    {
        return get(now.toString()).isEmpty();
    }

    public boolean firstOfMonth(LocalDate now)
    {
        Acesso acess = new Acesso().getLastAcess();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(acess.getLogout().getTime());
        
        return LocalDate.now().getMonthValue() > cal.get(Calendar.MONTH) + 1;
    }

    public ArrayList<Objeto> getAcessosUsuario(int cod)
    {
        ArrayList<Acesso>acessos = new ArrayList<>();
        ArrayList<Objeto>objs = new ArrayList<>();
        String login,logout;
        
        acessos = new Acesso().getAcessosUsuario(cod);
        
        for(Acesso a : acessos)
        {
            login = TimestamptoDate(a.getLogin());
            logout = TimestamptoDate(a.getLogout());
            objs.add(new Objeto(String.valueOf(a.getCodigo()),login,logout,String.valueOf(a.getUsuario().getCodigo())));
        }
        return objs;
    }
}
