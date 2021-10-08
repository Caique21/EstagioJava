/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import estagio.entidades.Despesa;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class ctrDespesa
{
    private static ctrDespesa con;

    private ctrDespesa()
    {
    }
    
    public static ctrDespesa instancia()
    {
        if (con == null)
        {
            con = new ctrDespesa();
        }
        return con;
    }

    public boolean salvar(JFXTextField nome, JFXRadioButton fixo, JFXTextField valor, JFXDatePicker data, JFXTextArea descricao)
    {
        Despesa despesa = new Despesa(nome.getText(), fixo.isSelected(), 
             Double.parseDouble(valor.getText().replace(".", "").replace(",", ".")), Date.valueOf(data.getValue()), descricao.getText());
        
        return despesa.salvar();
    }

    public boolean alterar(int cod, JFXTextField nome, JFXRadioButton fixo, JFXTextField valor, JFXDatePicker data, JFXTextArea descricao)
    {
        Despesa despesa = new Despesa(nome.getText(), fixo.isSelected(), 
             Double.parseDouble(valor.getText().replace(".", "").replace(",", ".")), Date.valueOf(data.getValue()), descricao.getText());
        
        return despesa.alterar();
    }

    public boolean apagar(int cod)
    {
        Despesa despesa = new Despesa();
        despesa.setCodigo(cod);
        return despesa.apagar();
    }

    public ArrayList<Objeto> getByNome(String nome)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ArrayList<Despesa>despesas = new Despesa().getByNome(nome);
        
        for (int i = 0; i < despesas.size(); i++)
            ret.add(convertToObjeto(despesas.get(i)));
        
        return ret;
    }

    public boolean verificaGeracaoAutomatica()
    {
        ArrayList<Despesa>despesas = new Despesa().getFixos();
        int mes = LocalDate.now().getMonthValue();
        
        if(despesas.isEmpty())
            return true;
        for (int i = 0; i < despesas.size(); i++)
            if(mes < Utils.convertToLocalDate(despesas.get(i).getVencimento()).getMonthValue())
                return false;
        return true;
    }

    public void gerarDespesasAutomatica()
    {
        ArrayList<Despesa>despesas = new Despesa().getFixos();
        
        for(Despesa d : despesas)
        {
            if(LocalDate.now().getMonthValue() > Utils.convertToLocalDate(d.getVencimento()).getMonthValue())
            {
                Despesa aux = d;
                aux.setCodigo(0);
                aux.setVencimento((Date) Utils.addMonth(aux.getVencimento(), 1));
                aux.salvar();
            }
        }
    }

    private Objeto convertToObjeto(Despesa desp)
    {
        Objeto obj = new Objeto();
        obj.setParam1(String.valueOf(desp.getCodigo()));
        obj.setParam2(desp.getNome());
        obj.setParam3(String.valueOf(desp.isFixo()));
        obj.setParam4(String.valueOf(desp.getValor()));
        obj.setParam5(String.valueOf(desp.getVencimento()));
        obj.setParam6(desp.getDescricao());
        //obj.setParam2(String.valueOf(desp.getTransporte().getCodigo()));
        return obj;
    }
}
