/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import estagio.entidades.Cliente;
import estagio.entidades.Fornecedor;
import estagio.entidades.ItensVenda;
import estagio.entidades.Modelo;
import estagio.entidades.Parcela;
import estagio.entidades.Veiculo;
import estagio.entidades.Venda;
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
public class ctrVenda
{
    private static ctrVenda con;

    private ctrVenda()
    {
    }
    
    public static ctrVenda instancia()
    {
        if (con == null)
        {
            con = new ctrVenda();
        }
        return con;
    }
    
    public boolean salvar(Objeto ven, LocalDate vencimento, String entrada)
    {
        Venda venda = convertToVenda(ven);
        
        boolean flag = venda.salvar();
        venda.setCodigo(Banco.getCon().getMaxPK("venda", "ven_codigo"));
        
        if(venda.getQtd_parcelas() == 1 && flag)
            flag = new Parcela(Date.valueOf(vencimento), 1, venda.getValor_total(),venda).salvar();
        else
        {
            int i = 0,qtd_parc = venda.getQtd_parcelas();
            double soma = 0.0, val = Utils.truncate(venda.getValor_total()/venda.getQtd_parcelas());
            
            ///PAGAMENTO COM ENTRADA
            if(entrada != null && !entrada.trim().equals("0") && !entrada.trim().equals(""))
            {
                flag = new Parcela(Date.valueOf(LocalDate.now()), 1, Double.parseDouble(entrada.replace(".", "").replace(",", ".")), 
                        venda).salvar();
                
                soma += Double.parseDouble(entrada.replace(".", "").replace(",", "."));
                val = Utils.truncate((venda.getValor_total() - soma)/venda.getQtd_parcelas());
                i = 1;
                qtd_parc += 1;//ACRESCENTA UMA PARCELA, A 1ª SENDO A ENTRADA
            }
            
            for(int j = 0; i < qtd_parc && flag; i++, j++)
            {
                Date aux = Date.valueOf(vencimento);
                if(i < qtd_parc - 1)
                    flag = new Parcela(Date.valueOf(aux.toLocalDate().plusMonths(j)),i + 1,val,venda).salvar();
                else
                    flag = new Parcela(Date.valueOf(aux.toLocalDate().plusMonths(j)),i + 1,
                        venda.getValor_total() - soma,venda).salvar();

                soma += val;
            }
        }
        
        for (int i = 0; i < ven.getList2().size() && flag; i++)
        {
            Objeto v = ven.getList2().get(i);
            ItensVenda item = new ItensVenda(convertToVeiculo(v), venda, 
                Double.parseDouble(v.getParam10().replace(".", "").replace(",", ".")));
            flag = flag && item.salvar();
        }
        
        if(!flag)
        {
            venda.apagar();
            return false;
        }
        return flag;
    }

    public boolean alterar(Objeto ven, LocalDate vencimento, String entrada)
    {
        Venda venda = convertToVenda(ven);
        
        boolean flag = venda.alterar();
        
        ArrayList<Parcela>parcelas_antigas = new Parcela().getByVenda(venda);
        
        if(venda.getQtd_parcelas() == 1 && flag)
            flag = new Parcela(Date.valueOf(vencimento), 1, venda.getValor_total(),venda).salvar();
        else
        {
            int i = 0,qtd_parc = venda.getQtd_parcelas();
            double soma = 0.0, val = Utils.truncate(venda.getValor_total()/venda.getQtd_parcelas());
            
            ///PAGAMENTO COM ENTRADA
            if(entrada != null && !entrada.trim().equals("0") && !entrada.trim().equals(""))
            {
                flag = new Parcela(Date.valueOf(LocalDate.now()), 1, Double.parseDouble(entrada.replace(".", "").replace(",", ".")), 
                        venda).salvar();
                
                soma += Double.parseDouble(entrada.replace(".", "").replace(",", "."));
                val = Utils.truncate((venda.getValor_total() - soma)/venda.getQtd_parcelas());
                i = 1;
                qtd_parc += 1;//ACRESCENTA UMA PARCELA, A 1ª SENDO A ENTRADA
            }
            
            for(int j = 0; i < qtd_parc && flag; i++, j++)
            {
                Date aux = Date.valueOf(vencimento);
                if(i < qtd_parc - 1)
                    flag = new Parcela(Date.valueOf(aux.toLocalDate().plusMonths(j)),i + 1,val,venda).salvar();
                else
                    flag = new Parcela(Date.valueOf(aux.toLocalDate().plusMonths(j)),i + 1,
                        venda.getValor_total() - soma,venda).salvar();

                soma += val;
            }
        }
        
        new ItensVenda(venda).apagar();
        for (int i = 0; i < ven.getList2().size() && flag; i++)
        {
            Objeto v = ven.getList2().get(i);
            ItensVenda item = new ItensVenda(convertToVeiculo(v), venda, 
                Double.parseDouble(v.getParam10().replace(".", "").replace(",", ".")));
            flag = flag && item.salvar();
        }
        
        if(!flag)
            return false;
        else
        {
            parcelas_antigas.forEach((p) ->
            {
                p.apagar(p.getCodigo());
            });
        }
        return flag;
    }
    
    public ArrayList<Objeto> getAll()
    {
        ArrayList<Venda>vendas = new Venda().getAll();
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for (int i = 0; i < vendas.size(); i++)
            ret.add(convertToObjeto(vendas.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByNome(String nome)
    {
        ArrayList<Venda>vendas = new Venda().getByNome(nome);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for (int i = 0; i < vendas.size(); i++)
            ret.add(convertToObjeto(vendas.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByNotaFiscal(String nota_fiscal)
    {
        ArrayList<Venda>vendas = new Venda().getByNotaFiscal(nota_fiscal);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for (int i = 0; i < vendas.size(); i++)
            ret.add(convertToObjeto(vendas.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByVeiculo(String veiculo)
    {
        ArrayList<Venda>vendas = new Venda().getByVeiculo(veiculo);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for (int i = 0; i < vendas.size(); i++)
            ret.add(convertToObjeto(vendas.get(i)));
        
        return ret;
    }

    public boolean apagar(int codigo)
    {
        Venda c = new Venda();
        c.setCodigo(codigo);
        return c.apagar();
    }

    private ArrayList<Veiculo>convertToVeiculo(ArrayList<Objeto>v)
    {
        ArrayList<Veiculo>veiculos = new ArrayList<>();
        
        for (int i = 0; i < v.size(); i++)
            veiculos.add(convertToVeiculo(v).get(i));
        
        return veiculos;
    }
    
    private Veiculo convertToVeiculo(Objeto v)
    {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(v.getParam2());
        veiculo.setAno(Integer.parseInt(v.getParam5()));
        veiculo.setChassi(v.getParam4());
        veiculo.setCor(v.getParam6());
        veiculo.setDescricao(v.getParam9());
        veiculo.setModelo(new Modelo(v.getParam8()));
        return veiculo;
    }

    private Venda convertToVenda(Objeto ven)
    {
        Venda venda = new Venda();
        venda.setAjuste(Double.parseDouble(ven.getParam7()));
        venda.setCodigo(Integer.parseInt(ven.getParam1()));
        venda.setData(Date.valueOf(ven.getParam8()));
        venda.setData_emissao(Date.valueOf(ven.getParam10()));
        venda.setNumero_nota_fiscal(ven.getParam9());
        venda.setQtd_parcelas(Integer.parseInt(ven.getParam5()));
        venda.setValor_total(Double.parseDouble(ven.getParam6()));
        
        if(Boolean.valueOf(ven.getParam2()) == false)
            venda.setCliente(new Cliente(Integer.parseInt(ven.getParam3())));
        else
            venda.setFornecedor(new Fornecedor(Integer.parseInt(ven.getParam3())));
        return venda;
    }
    
    private Objeto convertToObjeto(Venda ven)
    {
        ///1 - CÓDIGO, 2 - TRUE SE FOR FORNECEDOR, 3 - CÓDIGO DO FORNECEDOR/CLIENTE, 4 - NOME DO FORNECEDOR/CLIENTE
        ///5 - QTD PARCELAS, 6 - VALOR TOTAL, 7 - AJUSTE, 8 - DATA DA COMPRA, 9 - NOTA FISCAL, 10 - DATA DE EMISSÃO
        ///LIST1 - PARCELAS, LIST2 - VEICULOS, 13 - CNPJ/CPF
        
        Objeto obj = new Objeto();
        obj.setParam1(String.valueOf(ven.getCodigo()));
        obj.setParam5(String.valueOf(ven.getQtd_parcelas()));
        obj.setParam6(String.valueOf(ven.getValor_total()));
        obj.setParam7(String.valueOf(ven.getAjuste()));
        obj.setParam8(String.valueOf(ven.getData()));
        obj.setParam9(ven.getNumero_nota_fiscal());
        obj.setParam10(String.valueOf(ven.getData_emissao()));
        obj.setParam12(Utils.convertData(ven.getData_emissao()));
        
        if(ven.getFornecedor() != null)
        {
            obj.setParam2(String.valueOf(true));
            obj.setParam3(String.valueOf(ven.getFornecedor().getCodigo()));
            obj.setParam4(ven.getFornecedor().getNome());
            obj.setParam13(ven.getFornecedor().getCnpj());
        }
        else if(ven.getCliente() != null)
        {
            obj.setParam2(String.valueOf(false));
            obj.setParam3(String.valueOf(ven.getCliente().getCodigo()));
            obj.setParam4(ven.getCliente().getNome());
            obj.setParam13(ven.getCliente().getCpf());
        }
        
        ArrayList<Parcela>parcelas = new Parcela().getByVenda(ven);
        for(Parcela p : parcelas)
        {
            ///1 - CÓDIGO, 2 - VENCIMENTO, 3 - NÚMERO, 4 - PAGAMENTO, 5 - VALOR PARCELA, 6 - VALOR PAGO
            ///7 - COMPRA CÓDIGO, 8 - VENDA CÓDIGO 
            Objeto par = new Objeto();
            par.setParam1(String.valueOf(p.getCodigo()));
            par.setParam2(String.valueOf(p.getVencimento()));
            par.setParam3(String.valueOf(p.getNumero()));
            par.setParam4(String.valueOf(p.getPagamento()));
            par.setParam5(String.valueOf(p.getValor_parcela()));
            par.setParam6(String.valueOf(p.getValor_pago()));
            par.setParam7(String.valueOf(ven.getCodigo()));
            obj.addList1(par);
        }
        
        ArrayList<ItensVenda>veiculos = new ItensVenda().getByVenda(ven);
        obj.setList2ToString("");
        for(ItensVenda veiculo : veiculos)
        {
            ///1 - VEICULO CODIGO, 2 - CODIGO VENDA, 3 - VALOR
            Objeto item = ctrVeiculo.instancia().convertToObjeto(veiculo.getVeiculo());
            item.setParam10(String.valueOf(veiculo.getValor()));
            item.setParam11(String.valueOf(ven.getCodigo()));
            obj.addList2(item);
            obj.setList2ToString(obj.getList2ToString() + veiculo.getVeiculo().toString() + "\n\n");
            /*obj.addList2(new Objeto(String.valueOf(item.getVeiculo().getCodigo()), String.valueOf(ven.getCodigo()), 
                String.valueOf(item.getValor())));
            obj.setList2ToString(obj.getList2ToString() + item.getVeiculo().toString() + "\n\n");
            */
        }
        
        return obj;
    }
}
