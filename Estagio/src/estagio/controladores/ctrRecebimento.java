/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import estagio.entidades.Parcela;
import estagio.entidades.Recebimento;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class ctrRecebimento
{
    private static ctrRecebimento con;

    private ctrRecebimento()
    {
    }
    
    public static ctrRecebimento instancia()
    {
        if (con == null)
        {
            con = new ctrRecebimento();
        }
        return con;
    }
    
    public boolean pagar(Objeto rec, String valor, String forma_recebimento, String aux_forma_recebimento)
    {
        Recebimento recebimento = new Recebimento();
        recebimento.setData(Date.valueOf(LocalDate.now()));
        recebimento.setValor(Double.parseDouble(valor.replace(".", "").replace(",", ".")));
        recebimento.setForma_recebimento(forma_recebimento);
        recebimento.setForma_recebimento_desc(aux_forma_recebimento.trim());
        recebimento.setParcela(new Parcela(Integer.parseInt(rec.getList2(0).getParam1())));
        
        return recebimento.getValor() == Double.parseDouble(rec.getParam3()) ? 
                recebimento.pagar() : recebimento.pagarParcial();
    }

    public boolean estornar(Objeto recebimento, int usuario)
    {
        return convertToRecebimento(recebimento).estornar(usuario);
    }

    public ArrayList<Objeto> getAll(boolean... reco)
    {
        ArrayList<Recebimento>recebimentos = new Recebimento().getAll();
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Recebimento p : recebimentos)
        {
            if(reco.length > 0 && reco[0])//SE INDICAR QUE QUER EXIBIR OS PAGOS
            {
                if(p.getCodigo() > 0)//EXISTE UM RECEBIMENTO RELACIONADO A DESPESA/PARCELA CADASTRADA
                    ret.add(convertToObjeto(p));
            }
            else if(p.getCodigo() == 0)//NÃO POSSUI RECEBIMENTO RELACIONADO
                ret.add(convertToObjeto(p));
        } 
               
        return ret;
    }

    public ArrayList<Objeto> getByFornecedor(String fornecedor,boolean... reco)
    {
        ArrayList<Recebimento>recebimentos = new Recebimento().getByFornecedor(fornecedor);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Recebimento p : recebimentos)
        {
            if(reco.length > 0 && reco[0])//SE INDICAR QUE QUER EXIBIR OS PAGOS
            {
                if(p.getCodigo() > 0)//EXISTE UM RECEBIMENTO RELACIONADO A DESPESA/PARCELA CADASTRADA
                    ret.add(convertToObjeto(p));
            }
            else if(p.getCodigo() == 0)//NÃO POSSUI RECEBIMENTO RELACIONADO
                ret.add(convertToObjeto(p));
        } 
               
        return ret;
    }

    public ArrayList<Objeto> getByNotaFiscal(String nota_fiscal,boolean... reco)
    {
        ArrayList<Recebimento>recebimentos = new Recebimento().getByNotaFiscal(nota_fiscal);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Recebimento p : recebimentos)
        {
            if(reco.length > 0 && reco[0])//SE INDICAR QUE QUER EXIBIR OS PAGOS
            {
                if(p.getCodigo() > 0)//EXISTE UM RECEBIMENTO RELACIONADO A DESPESA/PARCELA CADASTRADA
                    ret.add(convertToObjeto(p));
            }
            else if(p.getCodigo() == 0)//NÃO POSSUI RECEBIMENTO RELACIONADO
                ret.add(convertToObjeto(p));
        } 
               
        return ret;
    }

    public ArrayList<Objeto> getByData(LocalDate data,boolean... reco)
    {
        ArrayList<Recebimento>recebimentos = new Recebimento().getByVencimento(data);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Recebimento p : recebimentos)
        {
            if(reco.length > 0 && reco[0])//SE INDICAR QUE QUER EXIBIR OS PAGOS
            {
                if(p.getCodigo() > 0)//EXISTE UM RECEBIMENTO RELACIONADO A DESPESA/PARCELA CADASTRADA
                    ret.add(convertToObjeto(p));
            }
            else if(p.getCodigo() == 0)//NÃO POSSUI RECEBIMENTO RELACIONADO
                ret.add(convertToObjeto(p));
        } 
               
        return ret;
    }

    public ArrayList<Objeto> getByPeriodo(LocalDate inicial, LocalDate fim,boolean... reco)
    {
        ArrayList<Recebimento>recebimentos = new Recebimento().getByPeriodo(inicial,fim);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for(Recebimento p : recebimentos)
        {
            if(reco.length > 0 && reco[0])//SE INDICAR QUE QUER EXIBIR OS PAGOS
            {
                if(p.getCodigo() > 0)//EXISTE UM RECEBIMENTO RELACIONADO A DESPESA/PARCELA CADASTRADA
                    ret.add(convertToObjeto(p));
            }
            else if(p.getCodigo() == 0)//NÃO POSSUI RECEBIMENTO RELACIONADO
                ret.add(convertToObjeto(p));
        } 
               
        return ret;
    }
    
    public ArrayList<Objeto> getInadimplentes()
    {
        return new Recebimento().getInadimplentes();
    }
    
    public ArrayList<Objeto> getContasVencer(LocalDate inicio, LocalDate...fim)
    {
        return new Recebimento().getContasVencer(inicio, fim);
    }

    private Objeto convertToObjeto(Recebimento p)
    {
        ///1 - PARCELA, 2 - NÚMERO DA PARCELA, 3 - VALOR, 4 - VENCIMENTO, 5 - BOOLEAN PAGO
        ///6 - FORMA DE PAGAMENTO, 7 - DESCRIÇÃO DA FORMA DE PAGAMENTO, 9 - CÓDIGO DO PAGAMENTO
        ///LIST1 - DADOS DA PARCELAS
        
        ///PARCELAS
        ///1 - CÓDIGO, 2 - VENCIMENTO, 3 - NUMERO,4 - PAGAMENTO, 5 - VALOR, 6 - VALOR PAGO, 7 - CÓDIGO COMPRA, 
        ///8 - NOTA FISCAL, 9 - DATA DA COMPRA,10 - DATA CONVERTIDA, 11 - NOME DO FORNECEDOR
        
        Objeto obj = new Objeto();
        Objeto aux = new Objeto();

        obj.setParam1("Parcela Venda Nota Fiscal: " + p.getParcela().getVenda().getNumero_nota_fiscal());
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
        aux.setParam7(String.valueOf(p.getParcela().getVenda().getCodigo()));
        aux.setParam8(p.getParcela().getVenda().getNumero_nota_fiscal());
        aux.setParam9(String.valueOf(p.getParcela().getVenda().getData()));
        aux.setParam10(Utils.convertDataUTC(p.getParcela().getVenda().getData()));
        if (p.getParcela().getVenda().getFornecedor() != null)
            aux.setParam11(p.getParcela().getVenda().getFornecedor().getNome());
        else
            aux.setParam11(p.getParcela().getVenda().getCliente().getNome());

        obj.addList2(aux);

        obj.setParam5(p.getCodigo() > 0 ? "Sim" : "Não");
        obj.setParam6(p.getForma_recebimento());
        if (p.getForma_recebimento() != null)
            obj.setParam8(p.getForma_recebimento() + " " + p.getForma_recebimento_desc());
        else
            obj.setParam8("");
        obj.setParam9(p.getCodigo() > 0 ? String.valueOf(p.getCodigo()) : "");
        
        return obj;
    }
    
    private Recebimento convertToRecebimento(Objeto obj)
    {
        ///1 - PARCELA, 2 - NÚMERO DA PARCELA, 3 - VALOR, 4 - VENCIMENTO, 5 - BOOLEAN PAGO
        ///6 - FORMA DE PAGAMENTO, 7 - DESCRIÇÃO DA FORMA DE PAGAMENTO, 9 - CÓDIGO DO PAGAMENTO
        ///LIST1 - DADOS DA PARCELAS
        
        ///PARCELAS
        ///1 - CÓDIGO, 2 - VENCIMENTO, 3 - NUMERO,4 - PAGAMENTO, 5 - VALOR, 6 - VALOR PAGO, 7 - CÓDIGO COMPRA, 
        ///8 - NOTA FISCAL, 9 - DATA DA COMPRA,10 - DATA CONVERTIDA, 11 - NOME DO FORNECEDOR
        Recebimento p = new Recebimento(!obj.getParam9().equals("")? Integer.parseInt(obj.getParam9()) : 0);
        p.setData(Date.valueOf(obj.getParam4()));
        p.setForma_recebimento(obj.getParam6());
        p.setForma_recebimento_desc(obj.getParam7());
        p.setParcela(new Parcela(Integer.parseInt(obj.getList1().get(0).getParam1())));
        return p;
    }
}
