/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.controladores;

import com.jfoenix.controls.JFXTextField;
import estagio.entidades.Endereco;
import estagio.entidades.Parametrizacao;
import estagio.utilidades.Objeto;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ListView;
import javax.imageio.ImageIO;

/**
 *
 * @author Carlos
 */
public class ctrParametrizacao
{
    private static ctrParametrizacao con;

    private ctrParametrizacao()
    {
    }
    
    public static ctrParametrizacao instancia()
    {
        if (con == null)
        {
            con = new ctrParametrizacao();
        }
        return con;
    }
    
    public Objeto carrega()
    {
        Parametrizacao para = new Parametrizacao().carrega();
        return para != null ? convertParametrizacao(para) : null;
    }

    private Objeto convertParametrizacao(Parametrizacao para)
    {
        Objeto ob = new Objeto(para.getNome(), para.getFantasia(), para.getEmail(), para.getRazao());
        ArrayList<Objeto> enderecos = new ArrayList<>();
        ArrayList<Objeto> telefones = new ArrayList<>();
        
        for(Endereco ender : para.getEnderecos())
            enderecos.add(convertEndereco(ender));
        
        for(String telefone : para.getTelefones())
            telefones.add(new Objeto(telefone));
        
        if(para.getLogoGrande() != null)
            ob.setParam5(para.getLogoGrande().toString());
        else
            ob.setParam5(null);
        
        if(para.getLogoPequeno() != null)
            ob.setParam6(para.getLogoPequeno().toString());
        else
            ob.setParam6(null);
        
        ob.setList1(enderecos);
        ob.setList2(telefones);
        return ob;
    }

    private Objeto convertEndereco(Endereco ender)
    {//cep,rua, numero, bairro,complemento, cidade, estado
        return new Objeto(ender.getCep(),ender.getRua(),String.valueOf(ender.getNumero()),ender.getBairro(), 
            ender.getComplemento(), ender.getCidade(), ender.getEstado());
    }

    public boolean salvar(JFXTextField nome, JFXTextField fantasia, JFXTextField email, JFXTextField razao, 
            ListView<String> enderecos, ListView<String> telefones, String caminhoGrande, String caminhoPequeno)
    {
        Parametrizacao para = new Parametrizacao(nome.getText(), fantasia.getText(), email.getText(), 
            razao.getText());
        
        for(String t : telefones.getItems())
            para.addTelefone(t.replace("(", "").replace(")", "").replace("-", ""));
        
        for(String e : enderecos.getItems())
            para.addEndereco(convertToEndereco(e));
        
        if(caminhoGrande != null && !caminhoGrande.equals("") && caminhoPequeno != null && !caminhoPequeno.equals(""))
            return para.salvar(caminhoGrande, caminhoPequeno);
        return para.salvar();
    }
    
    public boolean alterar(JFXTextField nome, JFXTextField fantasia, JFXTextField email, JFXTextField razao, 
            ListView<String> enderecos, ListView<String> telefones, String caminhoGrande, String caminhoPequeno)
    {
        Parametrizacao para = new Parametrizacao(nome.getText(), fantasia.getText(), email.getText(), 
            razao.getText());
        
        for(String t : telefones.getItems())
            para.addTelefone(t.replace("(", "").replace(")", "").replace("-", ""));
        
        for(String e : enderecos.getItems())
            para.addEndereco(convertToEndereco(e));
        
        return para.altera(caminhoGrande, caminhoPequeno);
    }

    private Endereco convertToEndereco(String ender)
    {
        //(rua) (numero), (bairro) - (complemento);(cidade) - (estado) - CEP (cep)
        String aux[] = ender.split(";");
        String aux2[] = aux[0].split(",");
        String aux3[] = aux[1].split(" - ");
        /*
        rua = rua.substring(0, rua.lastIndexOf(" "))
        bairro = ender.substring(ender.indexOf(",") + 2, ender.indexOf(";"))
        numero = Integer.parseInt(rua.substring(rua.lastIndexOf(" ") + 1).replace(" ", "").replace(",", "")), 
        cep = aux[2].replace(" ", "")
        estado = aux[1].replace(" ", "").replace("-", "")
        cidade = aux[0].substring(aux[0].indexOf("; ") + 2).replace("; ", "").replace(" -","")
        */
        String rua = aux2[0].substring(0,aux2[0].lastIndexOf(" "));
        int numero = Integer.parseInt(aux2[0].substring(aux2[0].lastIndexOf(" ") + 1));
        String bairro;
        String complemento;
        if(aux2[1].contains("-"))
        {
            complemento = aux2[1].substring(aux2[1].indexOf(" - ") + 2);
            bairro = aux2[1].substring(0, aux2[1].indexOf(" - "));
        }
        else
        {
            complemento = "";
            bairro = aux2[1];
        }
        
        Endereco endereco = new Endereco(aux3[2].replace("-", ""), rua, numero, bairro, complemento, 
            aux3[0], aux3[1]);
        
        endereco.existe();
        return endereco;
    }

    public ArrayList<String> carregaTelefones(String para)
    {
        return (ArrayList<String>) new Parametrizacao(para).getTelefones();
    }

    public BufferedImage carregaLogoGrande()
    {
        try
        {
            return ImageIO.read(new Parametrizacao().carregaLogoGrande());
        }
        catch (IOException ex)
        {
            Logger.getLogger(ctrParametrizacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public BufferedImage carregaLogoPequeno()
    {
        try
        {
            if(new Parametrizacao().carregaLogoPequeno() != null)
                return ImageIO.read(new Parametrizacao().carregaLogoPequeno());
        }
        catch (IOException ex)
        {
            Logger.getLogger(ctrParametrizacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
