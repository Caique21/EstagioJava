/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import estagio.entidades.Cliente;
import estagio.entidades.Compra;
import estagio.entidades.Fornecedor;
import estagio.entidades.ItensCompra;
import estagio.entidades.Modelo;
import estagio.entidades.Parcela;
import estagio.entidades.Veiculo;
import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 *
 * @author Carlos
 */
public class ctrCompra
{
    private static ctrCompra con;

    private ctrCompra()
    {
    }
    
    public static ctrCompra instancia()
    {
        if (con == null)
        {
            con = new ctrCompra();
        }
        return con;
    }
    
    public int salvar(String fornecedor, boolean cliente, String parcelas, String vencimento, String total, 
        double calculaAjuste, String notafiscal, String emissao, String vendedor,String entrada, ArrayList<Objeto> veiculos)
    {
        Compra compra = new Compra();
        compra.setQtd_parcelas(Integer.parseInt(parcelas));
        compra.setAjuste(calculaAjuste);
        compra.setData(Utils.convertToDate(LocalDate.now()));
        compra.setData_emissao(Utils.convertStringToDateUTC(emissao));
        compra.setNumero_nota_fiscal(notafiscal);
        compra.setVendedor(vendedor);
        compra.setValor_total(Double.parseDouble(total.substring(total.indexOf(":") + 1).replace("R$", "").
                replace(".", "").replace(",", ".")));
        
        if(cliente)
            compra.setCliente(new Cliente(fornecedor));
        else
            compra.setFornecedor(new Fornecedor(fornecedor));
        
        boolean flag = compra.salvar();
        compra.setCodigo(Banco.getCon().getMaxPK("compra", "comp_codigo"));
        
        if(compra.getQtd_parcelas() == 1 && flag)
            flag = new Parcela(Date.valueOf(vencimento),1,compra.getValor_total(),compra).salvar();
        else
        {
            int i = 0,qtd_parc = compra.getQtd_parcelas();
            double soma = 0.0, val = Utils.truncate(compra.getValor_total()/compra.getQtd_parcelas());
            
            ///PAGAMENTO COM ENTRADA
            if(entrada != null && !entrada.trim().equals("0") && !entrada.trim().equals(""))
            {
                flag = new Parcela(Date.valueOf(LocalDate.now()), 1, Double.parseDouble(entrada.replace(".", "").replace(",", ".")), 
                        compra).salvar();
                
                soma += Double.parseDouble(entrada.replace(".", "").replace(",", "."));
                val = Utils.truncate((compra.getValor_total() - soma)/compra.getQtd_parcelas());
                i = 1;
                qtd_parc += 1;//ACRESCENTA UMA PARCELA, A 1ª SENDO A ENTRADA
            }
            
            for(; i < qtd_parc && flag; i++)
            {
                Date aux = Date.valueOf(vencimento);
                if(i < qtd_parc - 1)
                    flag = new Parcela(Date.valueOf(aux.toLocalDate().plusMonths(i)),i + 1,val,compra).salvar();
                else
                    flag = new Parcela(Date.valueOf(aux.toLocalDate().plusMonths(i)),i + 1,
                        compra.getValor_total() - soma,compra).salvar();

                soma += val;
            }
        }
        
        for (int i = 0; i < veiculos.size() && flag; i++)
        {
            Objeto v = veiculos.get(i);
            ItensCompra item = new ItensCompra(convertToVeiculo(v), compra, 
                Double.parseDouble(v.getParam10().replace(".", "").replace(",", ".")));
            flag = flag && item.salvar();
        }
        
        if(!flag)
        {
            compra.apagar();
            new ItensCompra(compra).apagarVeiculos();
            return 0;
        }
        return compra.getCodigo();
    }

    public int salvar(String fornecedor, boolean cliente, String parcelas, String vencimento, String total, 
        double calculaAjuste, String notafiscal, String emissao, String vendedor,String entrada, 
        ArrayList<Objeto> veiculos, ArrayList<Objeto> list_parcelas)
    {
        Compra compra = new Compra();
        compra.setQtd_parcelas(Integer.parseInt(parcelas));
        compra.setAjuste(calculaAjuste);
        compra.setData(Utils.convertToDate(LocalDate.now()));
        compra.setData_emissao(Utils.convertStringToDateUTC(emissao));
        compra.setNumero_nota_fiscal(notafiscal);
        compra.setVendedor(vendedor);
        compra.setValor_total(Utils.convertStringToDouble(total.substring(total.indexOf(":") + 1).replace("R$", "")));
        
        if(cliente)
            compra.setCliente(new Cliente(fornecedor));
        else
            compra.setFornecedor(new Fornecedor(fornecedor));
        
        boolean flag = compra.salvar();
        compra.setCodigo(Banco.getCon().getMaxPK("compra", "comp_codigo"));
        
        ////PARCELAS////
        boolean has_entrada = false;
        
        //ENTRADA//
        if(entrada != null && !entrada.trim().equals("0") && !entrada.trim().equals(""))
            has_entrada = flag = new Parcela(Date.valueOf(LocalDate.now()), 1, 
                    Double.parseDouble(entrada.replace(".", "").replace(",", ".")), compra).salvar();
        //ENTRADA//
        
        for (int i = 0; i < list_parcelas.size() && flag; i++)
        {
            Objeto p = list_parcelas.get(i);
            if(has_entrada)
                p.setParam1(String.valueOf(Integer.parseInt(p.getParam1()) + 1));
            
            flag = new Parcela(Date.valueOf(p.getParam3()), Integer.parseInt(p.getParam1()), 
                    Utils.convertStringToDouble(p.getParam2()), compra).salvar();
        }
        ////PARCELAS////
        
        ////ITENS DA VENDA////
        for (int i = 0; i < veiculos.size() && flag; i++)
        {
            Objeto v = veiculos.get(i);
            ItensCompra item = new ItensCompra(convertToVeiculo(v), compra, 
                Double.parseDouble(v.getParam10().replace(".", "").replace(",", ".")));
            flag = flag && item.salvar();
        }
        ////ITENS DA VENDA////
        
        if(!flag)
        {
            compra.apagar();
            new ItensCompra(compra).apagarVeiculos();
            return 0;
        }
        return compra.getCodigo();
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

    /*public int salvar(JFXTextField fornecedor, boolean cliente, JFXTextField parcelas, JFXDatePicker vencimento, 
        Label total, Double calculaAjuste, JFXTextField notafiscal, JFXDatePicker emissao, JFXTextField vendedor, 
        JFXComboBox<String> pagamento, JFXTextField cheque, TableView<Objeto> veiculos)
    {
        Compra compra = new Compra();
        compra.setQtd_parcelas(Integer.parseInt(parcelas.getText()));
        compra.setAjuste(calculaAjuste);
        compra.setData(Utils.convertToDate(LocalDate.now()));
        compra.setData_emissao(Utils.convertToDate(emissao.getValue()));
        compra.setNumero_nota_fiscal(notafiscal.getText());
        compra.setVendedor(vendedor.getText());
        compra.setValor_total(Double.parseDouble(total.getText().substring(total.getText().indexOf(":") + 1).replace("R$", "")));
        
        if(cliente)
            compra.setCliente(new Cliente(fornecedor.getText()));
        else
            compra.setFornecedor(new Fornecedor(fornecedor.getText()));
        
        boolean flag = compra.salvar();
        compra.setCodigo(Banco.getCon().getMaxPK("compra", "comp_codigo"));
        
        if(flag)
        {
            Date aux = Utils.convertToSqlDate(vencimento.getValue());
            Double valor = compra.getValor_total()/compra.getQtd_parcelas();
            
            for (int i = 0; i < compra.getQtd_parcelas() && flag; i++)
            {
                Parcela p;
                if(i < compra.getQtd_parcelas() - 1)
                    p = new Parcela(aux,i + 1,valor,compra);
                else
                    p = new Parcela(aux,i + 1,compra.getValor_total() - valor,compra);
                
                flag = flag && p.salvar();
                aux = Date.valueOf(aux.toLocalDate().plusMonths(1));
            }
        }
        
        if(flag)
        {
            for (int i = 0; i < veiculos.getItems().size() && flag; i++)
            {
                Objeto v = veiculos.getItems().get(i);
                ItensCompra item = new ItensCompra(new Veiculo(Integer.parseInt(v.getParam1())), compra, 
                    Double.parseDouble(v.getParam10().replace(".", "").replace(",", ".")));
                flag = flag && item.salvar();
            }
        }
        
        if(!flag)
        {
            compra.apagar();
            return 0;
        }
        return compra.getCodigo();
    }*/
    
    public ArrayList<Objeto> getAll()
    {
        ArrayList<Compra>compras = new Compra().getAll();
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for (int i = 0; i < compras.size(); i++)
            ret.add(convertToObjeto(compras.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByNome(String nome)
    {
        ArrayList<Compra>compras = new Compra().getByNome(nome);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for (int i = 0; i < compras.size(); i++)
            ret.add(convertToObjeto(compras.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByNotaFiscal(String nota_fiscal)
    {
        ArrayList<Compra>compras = new Compra().getByNotaFiscal(nota_fiscal);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for (int i = 0; i < compras.size(); i++)
            ret.add(convertToObjeto(compras.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByVeiculo(String veiculo)
    {
        ArrayList<Compra>compras = new Compra().getByVeiculo(veiculo);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for (int i = 0; i < compras.size(); i++)
            ret.add(convertToObjeto(compras.get(i)));
        
        return ret;
    }

    public ArrayList<Objeto> getByVendedor(String vendedor)
    {
        ArrayList<Compra>compras = new Compra().getByVendedor(vendedor);
        ArrayList<Objeto>ret = new ArrayList<>();
        
        for (int i = 0; i < compras.size(); i++)
            ret.add(convertToObjeto(compras.get(i)));
        
        return ret;
    }

    private Objeto convertToObjeto(Compra comp)
    {
        ///1 - CÓDIGO, 2 - TRUE SE FOR FORNECEDOR, 3 - CÓDIGO DO FORNECEDOR/CLIENTE, 4 - NOME DO FORNECEDOR/CLIENTE
        ///5 - QTD PARCELAS, 6 - VALOR TOTAL, 7 - AJUSTE, 8 - DATA DA COMPRA, 9 - NOTA FISCAL, 10 - DATA DE EMISSÃO
        ///11 - VENDEDOR, LIST1 - PARCELAS, LIST2 - VEICULOS
        
        Objeto obj = new Objeto();
        obj.setParam1(String.valueOf(comp.getCodigo()));
        obj.setParam5(String.valueOf(comp.getQtd_parcelas()));
        obj.setParam6(String.valueOf(comp.getValor_total()));
        obj.setParam7(String.valueOf(comp.getAjuste()));
        obj.setParam8(String.valueOf(comp.getData()));
        obj.setParam9(comp.getNumero_nota_fiscal());
        obj.setParam10(String.valueOf(comp.getData_emissao()));
        obj.setParam11(comp.getVendedor());
        
        if(comp.getFornecedor() != null)
        {
            obj.setParam2(String.valueOf(true));
            obj.setParam3(String.valueOf(comp.getFornecedor().getCodigo()));
            obj.setParam4(comp.getFornecedor().getNome());
        }
        else if(comp.getCliente() != null)
        {
            obj.setParam2(String.valueOf(false));
            obj.setParam3(String.valueOf(comp.getCliente().getCodigo()));
            obj.setParam4(comp.getCliente().getNome());
        }
        
        ArrayList<Parcela>parcelas = new Parcela().getByCompra(comp);
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
            par.setParam7(String.valueOf(comp.getCodigo()));
            obj.addList1(par);
        }
        
        ArrayList<ItensCompra>veiculos = new ItensCompra().getByCompra(comp);
        obj.setList2ToString("");
        for(ItensCompra item : veiculos)
        {
            ///1 - VEICULO CODIGO, 2 - CODIGO COMPRA, 3 - VALOR
            obj.addList2(new Objeto(String.valueOf(item.getVeiculo().getCodigo()), String.valueOf(comp.getCodigo()), 
                String.valueOf(item.getValor())));
            obj.setList2ToString(obj.getList2ToString() + item.getVeiculo().toString() + "\n\n");
        }
        
        return obj;
    }
}
