/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import estagio.entidades.Despesa;
import estagio.entidades.Pagamento;
import estagio.entidades.Parcela;
import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

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
        
        boolean flag = pagamento.pagar();
        
        if(pagamento.getValor() < despesa.getValor())
            flag = flag && new Despesa(despesa.getNome(), despesa.isFixo(), despesa.getValor(), 
                    despesa.getVencimento(), despesa.getDescricao()).salvar();
        
        return flag;
    }

    public boolean pagar(Objeto pag, String valor, String forma_pagamento, String aux_forma_pagamento)
    {
        Pagamento pagamento = new Pagamento();
        pagamento.setData(Date.valueOf(LocalDate.now()));
        pagamento.setValor(Double.parseDouble(valor.replace(".", "").replace(",", ".")));
        pagamento.setForma_pagamento(forma_pagamento);
        pagamento.setForma_pagamento_desc(aux_forma_pagamento.trim());
        
        if(pag.getList1() != null && !pag.getList1().isEmpty())//DESPESA
            pagamento.setDespesa(new Despesa(Integer.parseInt(pag.getList1(0).getParam1())));
        else//PARCELA
            pagamento.setParcela(new Parcela(Integer.parseInt(pag.getList2(0).getParam1())));
        
        return pagamento.getValor() == Double.parseDouble(pag.getParam3()) ? 
                pagamento.pagar() : pagamento.pagarParcial();
        
        /*if(flag && pagamento.getValor() < Double.parseDouble(pag.getParam3()))
        {
            if(pagamento.getDespesa() != null)
                flag = new Despesa(pagamento.getDespesa().getNome(), pagamento.getDespesa().isFixo(), 
                    pagamento.getDespesa().getValor(), pagamento.getDespesa().getVencimento(), 
                        pagamento.getDespesa().getDescricao()).salvar();
            else
            {
                Parcela p = new Parcela();
                p.setCompra(pagamento.getParcela().getCompra());
                p.setNumero(p.getQtdParcelas(p.getCompra()) + 1);
                p.setValor_parcela(pagamento.getParcela().getValor_parcela() - pagamento.getValor());
                p.setVencimento(pagamento.getParcela().getVencimento());
                flag = p.salvar();
            }
        }
        
        return flag;*/
    }

    public boolean estornar(Objeto pagamento)
    {
        return convertToPagamento(pagamento).estornar();
    }

    public boolean wasPaid(int codigo)
    {
        return new Pagamento(new Despesa(codigo)).getCodigo() > 0;
    }

    public boolean estornarDespesa(int codigo)
    {
        return new Pagamento(new Despesa(codigo)).estornar();
    }

    public ArrayList<Objeto> getAll(boolean... pago)
    {
        ArrayList<Pagamento>pagamentos = new Pagamento().getAll();
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Pagamento p : pagamentos)
        {
            if(pago.length > 0 && pago[0])
                ret.add(convertToObjeto(p));
            else if(!p.isAtivo())
                ret.add(convertToObjeto(p));
        } 
               
        return ret;
    }

    public ArrayList<Objeto> getByFornecedor(String fornecedor,boolean... pago)
    {
        ArrayList<Pagamento>pagamentos = new Pagamento().getByFornecedor(fornecedor);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Pagamento p : pagamentos)
        {
            if(pago.length > 0 && pago[0])
                ret.add(convertToObjeto(p));
            else if(!p.isAtivo())
                ret.add(convertToObjeto(p));
        }
               
        return ret;
    }

    public ArrayList<Objeto> getByNotaFiscal(String nota_fiscal,boolean... pago)
    {
        ArrayList<Pagamento>pagamentos = new Pagamento().getByNotaFiscal(nota_fiscal);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Pagamento p : pagamentos)
        {
            if(pago.length > 0 && pago[0])
                ret.add(convertToObjeto(p));
            else if(!p.isAtivo())
                ret.add(convertToObjeto(p));
        } 
               
        return ret;
    }

    public ArrayList<Objeto> getByDespesa(String despesa,boolean... pago)
    {
        ArrayList<Pagamento>pagamentos = new Pagamento().getByDespesa(despesa);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Pagamento p : pagamentos)
        {
            if(pago.length > 0 && pago[0])
                ret.add(convertToObjeto(p));
            else if(!p.isAtivo())
                ret.add(convertToObjeto(p));
        } 
               
        return ret;
    }

    public ArrayList<Objeto> getByData(LocalDate data,boolean... pago)
    {
        ArrayList<Pagamento>pagamentos = new Pagamento().getByVencimento(data);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Pagamento p : pagamentos)
        {
            if(pago.length > 0 && pago[0])
                ret.add(convertToObjeto(p));
            else if(!p.isAtivo())
                ret.add(convertToObjeto(p));
        } 
               
        return ret;
    }

    public ArrayList<Objeto> getByPeriodo(LocalDate inicial, LocalDate fim,boolean... pago)
    {
        ArrayList<Pagamento>pagamentos = new Pagamento().getByPeriodo(inicial,fim);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Pagamento p : pagamentos)
        {
            if(pago.length > 0 && pago[0])
                ret.add(convertToObjeto(p));
            else if(!p.isAtivo())
                ret.add(convertToObjeto(p));
        } 
               
        return ret;
    }

    private Objeto convertToObjeto(Pagamento p)
    {
        ///1 - DESPESA/PARCELA, 2 - NÚMERO DA PARCELA, 3 - VALOR, 4 - VENCIMENTO, 5 - BOOLEAN PAGO
        ///6 - FORMA DE PAGAMENTO, 7 - DESCRIÇÃO DA FORMA DE PAGAMENTO, 9 - CÓDIGO DO PAGAMENTO
        ///LIST1 - DADOS DA DESPESA, LIST2 - DADOS DA PARCELAS
        
        ///DESPESA
        ///1 - CÓDIGO, 2 - NOME, 3 - FIXO, 4 - VALOR, 5 - VENCIMENTO, 6 - DESCRIÇÃO
        
        ///PARCELAS
        ///1 - CÓDIGO, 2 - VENCIMENTO, 3 - NUMERO,4 - PAGAMENTO, 5 - VALOR, 6 - VALOR PAGO, 7 - CÓDIGO COMPRA, 
        ///8 - NOTA FISCAL, 9 - DATA DA COMPRA
        
        Objeto obj = new Objeto();
        Objeto aux = new Objeto();
        if(p.getDespesa() != null)
        {
            obj.setParam1("Despesa " + p.getDespesa().getNome());
            obj.setParam2("");
            obj.setParam3(Utils.exibeCentavos(p.getDespesa().getValor()));
            obj.setParam7(Utils.convertDataUTC(p.getDespesa().getVencimento()));
            if(p.getDespesa().getVencimento() == null)
                obj.setParam4("");
            else
                obj.setParam4(String.valueOf(p.getDespesa().getVencimento()));
            
            aux.setParam1(String.valueOf(p.getDespesa().getCodigo()));
            aux.setParam2(p.getDespesa().getNome());
            aux.setParam3(String.valueOf(p.getDespesa().isFixo()));
            aux.setParam4(String.valueOf(p.getDespesa().getValor()));
            aux.setParam5(String.valueOf(p.getDespesa().getVencimento()));
            aux.setParam6(p.getDespesa().getDescricao() != null && !p.getDespesa().getDescricao().equals("null") ?
                    p.getDespesa().getDescricao() : "");
            aux.setParam7(p.getDespesa().isFixo()? "Sim":"Não");
            aux.setParam8(String.valueOf(p.getValor()));
            obj.addList1(aux);
        }
        else
        {
            obj.setParam1("Parcela compra Nota Fiscal: " + p.getParcela().getCompra().getNumero_nota_fiscal());
            obj.setParam2(String.valueOf(p.getParcela().getNumero()));
            obj.setParam3(Utils.exibeCentavos(p.getParcela().getValor_parcela()));
            obj.setParam4(String.valueOf(p.getParcela().getVencimento()));
            obj.setParam7(Utils.convertDataUTC(p.getParcela().getVencimento()));
            
            aux.setParam1(String.valueOf(p.getParcela().getCodigo()));
            aux.setParam2(String.valueOf(p.getParcela().getVencimento()));
            aux.setParam3(String.valueOf(p.getParcela().getNumero()));
            aux.setParam4(String.valueOf(p.getParcela().getPagamento()));
            aux.setParam5(String.valueOf(p.getParcela().getValor_parcela()));
            aux.setParam6(String.valueOf(p.getParcela().getValor_pago()));
            aux.setParam7(String.valueOf(p.getParcela().getCompra().getCodigo()));
            aux.setParam8(p.getParcela().getCompra().getNumero_nota_fiscal());
            aux.setParam9(String.valueOf(p.getParcela().getCompra().getData()));
            aux.setParam10(Utils.convertDataUTC(p.getParcela().getCompra().getData()));
            
            obj.addList2(aux);
        }
        obj.setParam5(p.isAtivo() ? "Sim": "Não");
        obj.setParam6(p.getForma_pagamento());
        if(p.getForma_pagamento() != null)
            obj.setParam8(p.getForma_pagamento() + " " + p.getForma_pagamento_desc());
        else
            obj.setParam8("");
        obj.setParam9(p.isAtivo()? String.valueOf(p.getCodigo()) : "");
        
        return obj;
    }
    
    private Pagamento convertToPagamento(Objeto obj)
    {
        ///1 - DESPESA/PARCELA, 2 - NÚMERO DA PARCELA, 3 - VALOR, 4 - VENCIMENTO, 5 - BOOLEAN PAGO
        ///6 - FORMA DE PAGAMENTO, 7 - DESCRIÇÃO DA FORMA DE PAGAMENTO, 9 - CÓDIGO DO PAGAMENTO
        ///LIST1 - DADOS DA DESPESA, LIST2 - DADOS DA PARCELAS
        
        ///DESPESA
        ///1 - CÓDIGO, 2 - NOME, 3 - FIXO, 4 - VALOR, 5 - VENCIMENTO, 6 - DESCRIÇÃO
        
        ///PARCELAS
        ///1 - CÓDIGO, 2 - VENCIMENTO, 3 - NUMERO,4 - PAGAMENTO, 5 - VALOR, 6 - VALOR PAGO, 7 - CÓDIGO COMPRA, 
        ///8 - NOTA FISCAL, 9 - DATA DA COMPRA
        Pagamento p = new Pagamento();
        p.setAtivo(obj.getParam5().equals("Sim"));
        p.setCodigo(!obj.getParam9().equals("")? Integer.parseInt(obj.getParam9()) : 0);
        p.setData(Date.valueOf(obj.getParam4()));
        p.setForma_pagamento(obj.getParam6());
        p.setForma_pagamento_desc(obj.getParam7());
        p.setValor(Double.parseDouble(obj.getParam3()));
        
        if(obj.getList1() != null && !obj.getList1().isEmpty())
            p.setDespesa(new Despesa(Integer.parseInt(obj.getList1().get(0).getParam1())));
        else
            p.setParcela(new Parcela(Integer.parseInt(obj.getList2().get(0).getParam1())));
        return p;
    }
}
