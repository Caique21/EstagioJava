/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import estagio.entidades.Despesa;
import estagio.entidades.Pagamento;
import estagio.utilidades.Banco;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Carlos
 */
public class ctrPagamento
{
    private static ctrPagamento con;

    private ctrPagamento()
    {
    }
    
    public static ctrPagamento instancia()
    {
        if (con == null)
        {
            con = new ctrPagamento();
        }
        return con;
    }

    public boolean pagarDespesa(LocalDate data, String valor, String forma_pagamento, String aux_forma_pagamento)
    {
        Despesa despesa = new Despesa(Banco.getCon().getMaxPK("despesa", "desp_codigo"));
        
        Pagamento pagamento = new Pagamento();
        pagamento.setData(Date.valueOf(data));
        pagamento.setValor(Double.parseDouble(valor.replace(".", "").replace(",", ".")));
        pagamento.setForma_pagamento(forma_pagamento);
        pagamento.setForma_pagamento_desc(aux_forma_pagamento);
        pagamento.setDespesa(despesa);
        
        boolean flag = pagamento.pagarDespesa();
        
        if(pagamento.getValor() < despesa.getValor())
            flag = flag && new Despesa(despesa.getNome(), despesa.isFixo(), despesa.getValor(), 
                    despesa.getVencimento(), despesa.getDescricao()).salvar();
        
        return flag;
    }

    public boolean wasPaid(int codigo)
    {
        return new Pagamento(new Despesa(codigo)).getCodigo() > 0;
    }

    public boolean estornarDespesa(int codigo)
    {
        return new Pagamento(new Despesa(codigo)).estornar();
    }
}
