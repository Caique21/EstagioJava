/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXSlider;
import estagio.entidades.Design;
import estagio.utilidades.Utils;
import java.util.ArrayList;
import javafx.scene.control.Slider;

/**
 *
 * @author Carlos
 */
public class ctrDesign
{
    private static ctrDesign con;

    private ctrDesign()
    {
    }
    
    public static ctrDesign instancia()
    {
        if (con == null)
        {
            con = new ctrDesign();
        }
        return con;
    }

    public ArrayList<String> carrega()
    {
        Design d = new Design().get();
        ArrayList<String> retorno = new ArrayList<>();
        if(d != null)
        {
            retorno.add(d.getFundo());
            retorno.add(d.getFundo2());
            retorno.add(d.getFonte());
            retorno.add(d.getFundoBotao());
            retorno.add(d.getPreenchimento());
            retorno.add(d.getFonte());
            retorno.add(d.getTamanhoBotao() + "");
            retorno.add(d.getFonteTexto());
            retorno.add(d.getFocoTexto());
            retorno.add(d.getTamanhoTexto() + "");
            retorno.add(String.valueOf(d.getOpacidade()));
        }
        
        return retorno;
    }

    public boolean salvar(JFXColorPicker cpFundo1, JFXColorPicker cpFundo2, JFXColorPicker cpFonte, 
        String cpFundoBotao, JFXColorPicker cpPreenchimento, JFXColorPicker cpFonteBotao, 
        Slider slFonteBotao, JFXColorPicker cpFonteTexto, JFXColorPicker cpFoco, Slider slFonteTexto,Slider Opacidade)
    {
        Design design = new Design(Utils.toRGBCode(cpFundo1.getValue()), Utils.toRGBCode(cpFundo2.getValue()), 
            Utils.toRGBCode(cpFonte.getValue()), cpFundoBotao, 
            Utils.toRGBCode(cpPreenchimento.getValue()), Utils.toRGBCode(cpFonteBotao.getValue()), 
            (int)slFonteBotao.getValue(),Utils.toRGBCode(cpFonteTexto.getValue()), 
            Utils.toRGBCode(cpFoco.getValue()), (int)slFonteTexto.getValue(),(int)Opacidade.getValue());
        
        return design.salvar();
    }

    public boolean alterar(JFXColorPicker cpFundo1, JFXColorPicker cpFundo2, JFXColorPicker cpFonte, 
        String cpFundoBotao, JFXColorPicker cpPreenchimento, JFXColorPicker cpFonteBotao, 
        Slider slFonteBotao, JFXColorPicker cpFonteTexto, JFXColorPicker cpFoco, Slider slFonteTexto,Slider Opacidade)
    {
        Design design = new Design(Utils.toRGBCode(cpFundo1.getValue()), Utils.toRGBCode(cpFundo2.getValue()), 
            Utils.toRGBCode(cpFonte.getValue()), cpFundoBotao, 
            Utils.toRGBCode(cpPreenchimento.getValue()), Utils.toRGBCode(cpFonteBotao.getValue()), 
            (int)slFonteBotao.getValue(),Utils.toRGBCode(cpFonteTexto.getValue()), 
            Utils.toRGBCode(cpFoco.getValue()), (int)slFonteTexto.getValue(),(int)Opacidade.getValue());
        
        return design.alterar();
    }
}
