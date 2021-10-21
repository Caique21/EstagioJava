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
import estagio.entidades.Parcela;
import estagio.entidades.Veiculo;
import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.sql.Date;
import java.time.LocalDate;
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

    public int salvar(JFXTextField fornecedor, boolean cliente, JFXTextField parcelas, JFXDatePicker vencimento, 
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
    }
}
