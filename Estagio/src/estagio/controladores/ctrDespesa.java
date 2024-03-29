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
import estagio.entidades.Transporte;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

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

    public boolean salvar(JFXTextField nome, JFXRadioButton fixo, JFXTextField valor, JFXDatePicker data, 
        JFXTextArea descricao, int... transporte)
    {
        Despesa despesa = new Despesa();
        despesa.setNome(nome.getText());
        despesa.setFixo(fixo.isSelected());
        despesa.setDescricao(descricao.getText());
        despesa.setValor(Double.parseDouble(valor.getText().replace(".", "").replace(",", ".")));
        
        if(data != null && data.getValue() != null)
            despesa.setVencimento(Date.valueOf(data.getValue()));
        if(transporte.length > 0)
            despesa.setTransporte(new Transporte(transporte[0]));
        
        boolean flag = despesa.salvar();
        
        if(despesa.getVencimento() == null)
            return flag;
        
        int qtd = LocalDate.now().getMonthValue() - data.getValue().getMonthValue();
        if(qtd > 0 && flag && fixo.isSelected())
        {
            for (int i = 0; i < qtd && flag; i++)
                flag = flag && new Despesa(nome.getText(), fixo.isSelected(), 
                    Double.parseDouble(valor.getText().replace(".", "").replace(",", ".")), 
                        Date.valueOf(data.getValue().plusMonths(i + 1)), descricao.getText()).salvar();
        }
        return flag;
    }

    public boolean alterar(int cod, JFXTextField nome, JFXRadioButton fixo, JFXTextField valor, JFXDatePicker data, 
        JFXTextArea descricao, int transporte,String...outros)
    {
        Despesa despesa = new Despesa();
        despesa.setCodigo(cod);
        despesa.setNome(nome.getText());
        despesa.setFixo(fixo.isSelected());
        despesa.setDescricao(descricao.getText());
        despesa.setValor(Double.parseDouble(valor.getText().replace(".", "").replace(",", ".")));
        
        if(data != null && data.getValue() != null)
            despesa.setVencimento(Date.valueOf(data.getValue()));
        if(transporte > 0)
            despesa.setTransporte(new Transporte(transporte));
        
        boolean flag = despesa.alterar2(outros);
        /*int qtd = 0;
        if(outros.length == 0)
        {
            flag = despesa.alterar();
            if(despesa.getVencimento() != null)
                qtd = LocalDate.now().getMonthValue() - data.getValue().getMonthValue();
        }
        else 
        {
            if(despesa.getVencimento() != null)
                qtd = despesa.getMinMonthByName(outros[0]) - data.getValue().getMonthValue() - 1;
            flag = despesa.alterar(outros[0]);
        }
        
        if(flag && fixo.isSelected())
        {
            ArrayList<Integer>meses = despesa.getMonthsByName(nome.getText()); 
            int min = (meses.get(0) + 1), max = meses.get(meses.size() - 1);
            for (int i = min; i <= 12 && flag && i < max; i++)
                if(!meses.contains(i))
                {
                    Despesa d = new Despesa(nome.getText(), fixo.isSelected(), 
                        Double.parseDouble(valor.getText().replace(".", "").replace(",", ".")), 
                            Utils.setMonth(Date.valueOf(data.getValue()),i - 1), descricao.getText());
                    d.setTransporte(despesa.getTransporte());
                    flag = d.salvar();
                    /*flag = flag && new Despesa(nome.getText(), fixo.isSelected(), 
                        Double.parseDouble(valor.getText().replace(".", "").replace(",", ".")), 
                            Utils.setMonth(Date.valueOf(data.getValue()),i - 1), descricao.getText()).salvar();*/
                //}
        //}
        return flag;
    }

    public boolean apagar(int cod)
    {
        Despesa despesa = new Despesa();
        despesa.setCodigo(cod);
        return despesa.apagar();
    }
    
    public boolean apagar(String nome)
    {
        Despesa despesa = new Despesa();
        despesa.setNome(nome);
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
    
    public ArrayList<Objeto> getByNomeDistinct(String nome)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ArrayList<Despesa>despesas = new Despesa().getByNomeDistinct(nome);
        
        for (int i = 0; i < despesas.size(); i++)
            ret.add(convertToObjeto(despesas.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByTransporte(int codigo)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        ArrayList<Despesa>despesas = new Despesa().getByTransporte(codigo);
        
        for (int i = 0; i < despesas.size(); i++)
            ret.add(convertToObjeto(despesas.get(i)));
        
        return ret;
    }

    public void gerarDespesasAutomatica()
    {
        ArrayList<Despesa>despesas = new Despesa().getFixos();
        
        for(Despesa d : despesas)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(d.getVencimento());
            int i = LocalDate.now().getMonthValue() - (cal.get(Calendar.MONTH) + 1);
            
            while(i > 0)
            {
                new Despesa(d.getNome(), d.isFixo(), d.getValor(), 
                    new Date(Utils.addMonth(d.getVencimento(), i).getTime()), "").salvar();
                i--;
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
        obj.setParam6(desp.getDescricao() != null ? desp.getDescricao() : "");
        obj.setParam7(desp.isFixo()? "Sim" : "Não");
        
        if(desp.getTransporte() != null)
            obj.setParam9(String.valueOf(desp.getTransporte().getCodigo()));
        else
            obj.setParam9("0");
        
        if(desp.getVencimento() != null)
        {
            obj.setParam5(String.valueOf(desp.getVencimento()));
            obj.setParam8(Utils.convertData(desp.getVencimento()));
        }
        else
        {
            obj.setParam5("");
            obj.setParam8("");
        }
        return obj;
    }

    public int count(String nome)
    {
        return new Despesa().count(nome);
    }

    public boolean foiPaga(int codigo)
    {
        return new Despesa().paga(codigo);
    }
}
