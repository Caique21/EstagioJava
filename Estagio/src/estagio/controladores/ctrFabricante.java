/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import com.jfoenix.controls.JFXTextField;
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

    public boolean apagarMarca(Objeto item)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean apagarModelo(Objeto item)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Objeto> getAll()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Objeto> get(String... nomes)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean salvarMarca(JFXTextField tfMarca)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean salvarModelo(JFXTextField tfModelo)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean alterarMarca(int parseInt, JFXTextField tfMarca)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean alterarModelo(int parseInt, JFXTextField tfModelo)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
